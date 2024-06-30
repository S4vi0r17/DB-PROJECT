-------------------------------------------------------------------------------------------
--CONSULTA MULTITABLA PARA SABER LAS FACTURAS QUE VENCEN EN 30 DIAS

DECLARE @FechaActual DATE = '2024-01-01'; 

SELECT 
	F.FacturaID,
	P.Nombre_Producto, 
	DATEDIFF(day, @FechaActual, F.FechaVencimiento) AS DiasParaPagar
FROM Factura f
INNER JOIN FacturaProducto fp ON f.FacturaID = fp.FacturaID
INNER JOIN Producto p ON fp.ProductoID = p.ProductoID
WHERE f.FechaVencimiento BETWEEN @FechaActual AND DATEADD(day, 60, @FechaActual)

-------------------------------------------------------------------------------------------
--CONSULTA MULTITABLA PARA SABER QUE EMPLEADO GUARDO TU PRODUCTO EN EL ALMACEN
DECLARE @ProductoID INT = 1

SELECT P.ProductoID, P.Nombre_Producto, E.EmpleadoID, GA.Fecha
FROM Producto P
INNER JOIN FacturaProducto fp ON p.ProductoID = fp.ProductoID
INNER JOIN Factura f ON fp.FacturaID = f.FacturaID
INNER JOIN GuiaAlmacen ga ON f.ProveedorID = ga.ProveedorID 
INNER JOIN EmpleadoAlmacen ea ON ga.GuiaID = ea.GuiaID
INNER JOIN Empleado e ON ea.EmpleadoID = e.EmpleadoID
WHERE p.ProductoID = @ProductoID; 

-------------------------------------------------------------------------------------------
-- CONSULTA MULTITABLA PARA SABER EL SALDO RESTANTE DE CADA FACTURA

SELECT f.FacturaID, 
       SUM(p.PrecioUnidad * p.Cantidad) AS MontoTotalFactura,
	   ISNULL(SUM(cb.MontoPagado), 0) AS TotalPagado,
       SUM(p.PrecioUnidad * p.Cantidad) - ISNULL(SUM(cb.MontoPagado), 0) AS SaldoRestante
FROM Factura f
LEFT JOIN FacturaProducto FP ON F.FacturaID = FP.FacturaID
LEFT JOIN Producto p ON FP.ProductoID = P.ProductoID
LEFT JOIN CajaBanco CB ON F.FacturaID = CB.FacturaID
GROUP BY F.FacturaID, F.ProveedorID, F.FechaEmision, F.FechaVencimiento
ORDER BY F.FacturaID;

select* from Factura
select* from CajaBanco
select* from FacturaProducto
select* from Producto
-------------------------------------------------------------------------------------------
-- CONSULTA MULTITABLA QUE MUESTRA LOS PAGOS QUE SE REALIZARON A UNA FACTURA
DECLARE @FacturaID INT = 1; 

SELECT F.FacturaID, 
       CB.PagoID, 
       CB.FechaPago, 
       CB.MontoPagado,
       MP.Detalles AS MedioPago
FROM Factura f
LEFT JOIN CajaBanco cb ON f.FacturaID = cb.FacturaID
LEFT JOIN MedioPago mp ON cb.MedioID = mp.MedioID
WHERE f.FacturaID = @FacturaID;

---------------------------------------------------------------------------------
--PROCEDIMIENTO QUE MUESTRA LOS PRODUCTOS MAS COMPRADOS
CREATE PROCEDURE ConsultarProductosMasComprados
AS
BEGIN
    SELECT TOP 3 p.ProductoID,
           p.Nombre_Producto,
           SUM(P.Cantidad) AS TotalComprado
    FROM Producto p
    INNER JOIN FacturaProducto fp ON p.ProductoID = fp.ProductoID
    GROUP BY p.ProductoID, p.Nombre_Producto
    ORDER BY SUM(P.Cantidad) DESC;
END;

EXEC ConsultarProductosMasComprados;
