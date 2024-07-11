-- 1 CONSULTA / 1ER MULTITABLA
DECLARE @FechaActual DATE = '2024-01-01';

SELECT F.FacturaID, P.Nombre_Producto, DATEDIFF(
        day, @FechaActual, F.FechaVencimiento
    ) DiasParaPagar
FROM
    Factura F
    INNER JOIN FacturaProducto FP ON F.FacturaID = FP.FacturaID
    INNER JOIN Producto P ON FP.ProductoID = P.ProductoID
WHERE
    F.FechaVencimiento BETWEEN @FechaActual AND DATEADD  (day, 60, @FechaActual);

-- 2 CONSULTA / 2DA MULTITABLA
DECLARE @ProductoID INT = 1

SELECT P.ProductoID, P.Nombre_Producto, E.EmpleadoID, GA.Fecha
FROM
    Producto P
    INNER JOIN FacturaProducto FP ON P.ProductoID = FP.ProductoID
    INNER JOIN Factura F ON FP.FacturaID = F.FacturaID
    INNER JOIN GuiaAlmacen GA ON F.ProveedorID = GA.ProveedorID
    INNER JOIN EmpleadoAlmacen EA ON GA.GuiaID = EA.GuiaID
    INNER JOIN Empleado E ON EA.EmpleadoID = E.EmpleadoID
WHERE
    P.ProductoID = @ProductoID
-- 3 CONSULTA / 1ER FUNCION
CREATE OR ALTER FUNCTION Contacto_Proveedor(@ProveedorID INT)
RETURNS VARCHAR(200)
AS
BEGIN
	DECLARE @DetallesContacto VARCHAR(200)
	SELECT @DetallesContacto = CONCAT(Nombre_Proveedor,' / ', Telefono, ' / ', Direccion)
	FROM Proveedor
	WHERE ProveedorID = @ProveedorID
	RETURN @DetallesContacto
END;

SELECT dbo.Contacto_Proveedor (2) AS 'Informacion_del_Proveedor';

-- 4 CONSULTA / 2DA FUNCION

CREATE OR ALTER FUNCTION ProductosProveedor(@Nombre_Proveedor VARCHAR(100))
RETURNS TABLE
AS 
RETURN
(	SELECT
		P.ProductoID,
		P.Nombre_Producto,
		P.PrecioUnidad,
		P.Cantidad,
		P.MontoTotal,
		PR.Nombre_Proveedor
	FROM Proveedor PR
	INNER JOIN Factura F ON PR.ProveedorID = F.ProveedorID
	INNER JOIN FacturaProducto FP ON F.FacturaID = FP.FacturaID
	INNER JOIN Producto P ON FP.ProductoID = P.ProductoID
	WHERE PR.Nombre_Proveedor = @Nombre_Proveedor
	);

SELECT *
FROM ProductosProveedor ('Emertica SA')
    -- 5 CONSULTA / 3ER MULTITABLA

SELECT
    F.FacturaID,
    SUM(P.PrecioUnidad * P.Cantidad) AS MontoTotalFctura,
    ISNULL(SUM(CB.MontoPagado), 0) AS TotalPagado,
    SUM(P.PrecioUnidad * P.Cantidad) - ISNULL(SUM(CB.MontoPagado), 0) AS SaldoRestante
FROM
    Factura F
    LEFT JOIN FacturaProducto FP ON F.FacturaID = FP.FacturaID
    LEFT JOIN Producto P ON FP.ProductoID = P.ProductoID
    LEFT JOIN CajaBanco CB ON F.FacturaID = CB.FacturaID
GROUP BY
    F.FacturaID
ORDER BY F.FacturaID;

select *
from Producto
    -- 6 CONSULTA / 4TA MULTITABLA
    DECLARE @FacturaID INT = 1;

SELECT F.FacturaID, CB.PagoID, CB.FechaPago, CB.MontoPagado, MP.Detalles AS MedioPago
FROM
    Factura F
    LEFT JOIN CajaBanco CB ON F.FacturaID = CB.FacturaID
    LEFT JOIN MedioPago MP ON CB.MedioID = MP.MedioID
WHERE
    F.FacturaID = @FacturaID;

-- 7 CONSULTA / 1ER PROCEDIMIENTO
CREATE PROCEDURE ConsultaProductosMasComprados
AS
BEGIN 
	SELECT TOP 3 
		P.ProductoID,
		P.Nombre_Producto,
		SUM(P.Cantidad) AS TotalComprado
	FROM Producto P
	INNER JOIN FacturaProducto FP ON P.ProductoID = FP.ProductoID
	GROUP BY P.ProductoID, P.Nombre_Producto
	ORDER BY SUM(P.Cantidad) DESC;
END;

EXEC ConsultaProductosMasComprados
-- 8 CONSULTA / 2DO PROCEDIMIENTRO
CREATE PROCEDURE PesosProductos
AS
BEGIN
	SELECT 
		P.ProductoID,
		P.Nombre_Producto,
		GA.Peso AS 'PesosTotales'
	FROM Producto P
	INNER JOIN FacturaProducto FP ON P.ProductoID = FP.ProductoID
	INNER JOIN Factura F ON FP.FacturaID = F.FacturaID
	INNER JOIN GuiaAlmacen GA ON F.FacturaID = GA.GuiaID;
END;

EXEC PesosProductos;

-- 9 CONSULTA/ 1ER SUBCONSULTAS
SELECT Proveedor.Nombre_Proveedor, (
        SELECT COUNT(*)
        FROM Factura
        WHERE
            Factura.ProveedorID = Proveedor.ProveedorID
    ) AS FacturasEmitidas
FROM Proveedor;

SELECT *
FROM Proveedor
SELECT *
FROM CondicionPago
SELECT *
FROM Factura
SELECT *
FROM Empleado
SELECT *
FROM GuiaAlmacen
SELECT *
FROM EmpleadoAlmacen
    -- 10 CONSULTA / 2DA SUBCONSULTA
SELECT p.ProveedorID, p.Nombre_Proveedor, (
        SELECT COUNT(ga.GuiaID)
        FROM GuiaAlmacen ga
        WHERE
            ga.ProveedorID = p.ProveedorID
    ) AS Total_Guias
FROM Proveedor p;

SELECT *
FROM Proveedor
    -- 11 CONSULTA / 3ERA SUBCONSULTA
SELECT F.FacturaID, (
        SELECT SUM(CB.MontoPagado)
        FROM CajaBanco CB
        WHERE
            CB.FacturaID = F.FacturaID
    ) AS TotalPagado
From Factura f
    --12 CONSULTA / 4TA SUBCONSULTA
SELECT
    MP.MedioID,
    MP.Detalles AS Medio_Pago,
    (
        SELECT SUM(cb.MontoPagado)
        FROM CajaBanco cb
        WHERE
            cb.MedioID = mp.MedioID
    ) AS Monto_Total_Gastado
FROM MedioPago mp;
--13 CONSULTA / 3ERA FUNCION
CREATE OR ALTER FUNCTION dbo.ObtenerMontoPagadoPorNombreProveedor (@NombreProveedor NVARCHAR(100))
RETURNS DECIMAL(18, 2)
AS
BEGIN
    DECLARE @MontoPagado DECIMAL(18, 2);

    SELECT @MontoPagado = SUM(cb.MontoPagado)
    FROM CajaBanco cb
    INNER JOIN Factura f ON cb.FacturaID = f.FacturaID
    INNER JOIN Proveedor p ON f.ProveedorID = p.ProveedorID
    WHERE p.Nombre_Proveedor = @NombreProveedor;

    RETURN ISNULL(@MontoPagado, 0);
END;

DECLARE @NombreProveedor NVARCHAR (100) = 'Emertica SA';

SELECT dbo.ObtenerMontoPagadoPorNombreProveedor (@NombreProveedor) AS Monto_Pagado_Al_Proveedor;

--14 CONSULTA / 3ER PROCEDIMIENTO
-- Crear el procedimiento almacenado
CREATE PROCEDURE VerificarLlegadaProductoAlmacenPorID
    @ProductoID INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @FechaLlegada DATE;

    SELECT @FechaLlegada = ga.Fecha
    FROM GuiaAlmacen ga
    JOIN Factura f ON ga.ProveedorID = f.ProveedorID
    JOIN FacturaProducto fp ON f.FacturaID = fp.FacturaID
    WHERE fp.ProductoID = @ProductoID;

    IF (@FechaLlegada IS NOT NULL)
    BEGIN

        DECLARE @NombreProducto NVARCHAR(100);
        SELECT @NombreProducto = Nombre_Producto
        FROM Producto
        WHERE ProductoID = @ProductoID;

        SELECT @NombreProducto AS NombreProducto, @FechaLlegada AS FechaLlegada;
    END
    ELSE
    BEGIN

        PRINT 'NO REGISTRADO';

    END;
END;

GO

EXEC VerificarLlegadaProductoAlmacenPorID @ProductoID = 1;

--15 CONSULTA / TRANSACCION 1
BEGIN TRANSACTION;

DECLARE @FacturaID INT;

INSERT INTO
    Factura (
        CondicionPagoID,
        ProveedorID,
        FechaEmision,
        FechaVencimiento
    )
VALUES (
        1,
        1,
        '2024-07-10',
        '2024-08-10'
    );

SET @FacturaID = SCOPE_IDENTITY ();

BEGIN TRY
INSERT INTO
    FacturaProducto (ProductoID, FacturaID)
VALUES (1, @FacturaID);

INSERT INTO
    FacturaProducto (ProductoID, FacturaID)
VALUES (2, @FacturaID);

COMMIT;

END TRY BEGIN CATCH ROLLBACK;

PRINT ERROR_MESSAGE ();

END CATCH;

--16 CONSULTA / TRANSACCION 2
BEGIN TRY BEGIN TRANSACTION;

DECLARE @PagoID INT;

INSERT INTO
    CajaBanco (
        MedioID,
        FacturaID,
        FechaPago,
        MontoPagado
    )
VALUES (1, 1, '2024-07-10', 500.00);

SET @PagoID = SCOPE_IDENTITY ();

COMMIT;

END TRY BEGIN CATCH

IF @@TRANCOUNT > 0 ROLLBACK;

PRINT ERROR_MESSAGE ();

END CATCH;

--17 CONSULTA / OPTIMIZACION
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
--18 CONSULTA / OPTIMIZACION
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
