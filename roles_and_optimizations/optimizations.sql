/*
Optimización de la consulta de facturas que vencen en 30 días:
*/

-- Crear un índice para mejorar el rendimiento
CREATE INDEX IX_Factura_FechaVencimiento ON Factura (FechaVencimiento);

-- Consulta optimizada
DECLARE @FechaActual DATE = '2024-01-01';

DECLARE @FechaLimite DATE = DATEADD (day, 60, @FechaActual);

SELECT F.FacturaID, P.Nombre_Producto, DATEDIFF(
        day, @FechaActual, F.FechaVencimiento
    ) AS DiasParaPagar
FROM
    Factura F
    INNER JOIN FacturaProducto FP ON F.FacturaID = FP.FacturaID
    INNER JOIN Producto P ON FP.ProductoID = P.ProductoID
WHERE
    F.FechaVencimiento >= @FechaActual
    AND F.FechaVencimiento <= @FechaLimite;

/*
Optimización de la consulta de saldo restante de cada factura:
*/

-- Crear índices para mejorar el rendimiento
CREATE INDEX IX_FacturaProducto_FacturaID ON FacturaProducto (FacturaID);

CREATE INDEX IX_CajaBanco_FacturaID ON CajaBanco (FacturaID);

-- Consulta optimizada
WITH
    FacturaTotal AS (
        SELECT F.FacturaID, SUM(P.PrecioUnidad * P.Cantidad) AS MontoTotalFactura
        FROM
            Factura F
            INNER JOIN FacturaProducto FP ON F.FacturaID = FP.FacturaID
            INNER JOIN Producto P ON FP.ProductoID = P.ProductoID
        GROUP BY
            F.FacturaID
    ),
    PagoTotal AS (
        SELECT FacturaID, ISNULL(SUM(MontoPagado), 0) AS TotalPagado
        FROM CajaBanco
        GROUP BY
            FacturaID
    )
SELECT
    FT.FacturaID,
    FT.MontoTotalFactura,
    COALESCE(PT.TotalPagado, 0) AS TotalPagado,
    FT.MontoTotalFactura - COALESCE(PT.TotalPagado, 0) AS SaldoRestante
FROM FacturaTotal FT
    LEFT JOIN PagoTotal PT ON FT.FacturaID = PT.FacturaID
ORDER BY FT.FacturaID;

/*
Optimización de la consulta para saber qué empleado guardó un producto en el almacén:
*/

-- Crear índices para mejorar el rendimiento
CREATE INDEX IX_FacturaProducto_ProductoID ON FacturaProducto (ProductoID);

CREATE INDEX IX_GuiaAlmacen_ProveedorID ON GuiaAlmacen (ProveedorID);

CREATE INDEX IX_EmpleadoAlmacen_GuiaID ON EmpleadoAlmacen (GuiaID);

-- Consulta optimizada
CREATE PROCEDURE ObtenerEmpleadoAlmacenPorProducto
    @ProductoID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT TOP 1 
        P.ProductoID, 
        P.Nombre_Producto, 
        E.EmpleadoID, 
        E.Nombre_Empleado,
        GA.Fecha
    FROM Producto P
    INNER JOIN FacturaProducto FP ON P.ProductoID = FP.ProductoID
    INNER JOIN Factura F ON FP.FacturaID = F.FacturaID
    INNER JOIN GuiaAlmacen GA ON F.ProveedorID = GA.ProveedorID 
    INNER JOIN EmpleadoAlmacen EA ON GA.GuiaID = EA.GuiaID
    INNER JOIN Empleado E ON EA.EmpleadoID = E.EmpleadoID
    WHERE P.ProductoID = @ProductoID
    ORDER BY GA.Fecha DESC;
END;

-- Ejecutar el procedimiento
EXEC ObtenerEmpleadoAlmacenPorProducto @ProductoID = 1;

/*
Optimización de la consulta de productos más comprados
*/

-- Crear un índice para mejorar el rendimiento
CREATE INDEX IX_FacturaProducto_ProductoID_Incluye_Cantidad ON FacturaProducto (ProductoID) INCLUDE (Cantidad);

-- Consulta optimizada
CREATE PROCEDURE ConsultarProductosMasComprados
    @TopN INT = 3
AS
BEGIN
    SET NOCOUNT ON;

    SELECT TOP (@TopN)
           p.ProductoID,
           p.Nombre_Producto,
           SUM(FP.Cantidad) AS TotalComprado
    FROM Producto p
    INNER JOIN FacturaProducto FP ON p.ProductoID = FP.ProductoID
    GROUP BY p.ProductoID, p.Nombre_Producto
    ORDER BY SUM(FP.Cantidad) DESC
    OPTION (OPTIMIZE FOR UNKNOWN);
END;

-- Ejecutar el procedimiento
EXEC ConsultarProductosMasComprados @TopN = 3;
