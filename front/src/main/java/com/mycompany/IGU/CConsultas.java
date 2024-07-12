
package com.mycompany.IGU;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CConsultas {
public void Consulta1(JDateChooser paramHoy) {//-- 1 CONSULTA / 1ER MULTITABLA
    CConexion C1 = new CConexion();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String FechaActual = dateFormat.format(paramHoy.getDate());
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("FacturaID");
    modelo.addColumn("Nombre de Producto");
    modelo.addColumn("Dias Para Pagar");
    
    String sql = "DECLARE @FechaActual DATE = ?; " +
                 "SELECT F.FacturaID, P.Nombre_Producto, " +
                 "DATEDIFF(day, @FechaActual, F.FechaVencimiento) AS DiasParaPagar " +
                 "FROM Factura f " +
                 "INNER JOIN FacturaProducto fp ON f.FacturaID = fp.FacturaID " +
                 "INNER JOIN Producto p ON fp.ProductoID = p.ProductoID " +
                 "WHERE f.FechaVencimiento BETWEEN @FechaActual AND DATEADD(day, 60, @FechaActual)";
    
    try (Connection con = C1.establecerConexion(); 
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setString(1, FechaActual);
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String[] datos = new String[3];
            datos[0] = rs.getString("FacturaID");
            datos[1] = rs.getString("Nombre_Producto");
            datos[2] = rs.getString("DiasParaPagar");
            
            modelo.addRow(datos);
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta2(JTextField ID) {//-- 2 CONSULTA / 2DA MULTITABLA
    String productoIDText = ID.getText();
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("ProductoID");
    modelo.addColumn("Nombre de Producto");
    modelo.addColumn("EmpleadoID");
    modelo.addColumn("Fecha");
    
    String sql = "DECLARE @ProductoID INT = ?; " +
                 " " +
                 "SELECT P.ProductoID, P.Nombre_Producto, E.EmpleadoID, GA.Fecha " +
                 "FROM Producto P " +
                 "INNER JOIN FacturaProducto fp ON P.ProductoID = fp.ProductoID " +
                 "INNER JOIN Factura F ON fp.FacturaID = F.FacturaID " +
                 "INNER JOIN GuiaAlmacen GA ON F.ProveedorID = GA.ProveedorID " +
                 "INNER JOIN EmpleadoAlmacen EA ON GA.GuiaID = EA.GuiaID " +
                 "INNER JOIN Empleado E ON EA.EmpleadoID = E.EmpleadoID " +
                 "WHERE P.ProductoID = @ProductoID";
    
    try (Connection con = C1.establecerConexion(); 
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        int productoID = Integer.parseInt(productoIDText); // Convertir el texto a entero
        
        pst.setInt(1, productoID);
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String[] datos = new String[4];
            datos[0] = rs.getString("ProductoID");
            datos[1] = rs.getString("Nombre_Producto");
            datos[2] = rs.getString("EmpleadoID");
            datos[3] = rs.getString("Fecha");
            
            modelo.addRow(datos);
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta3(JTextField ID) {//-- 3 CONSULTA / 1ER FUNCION 
    String productoIDText = ID.getText();
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Informacion_del_proveedor");
    
    String sql = "select dbo.Contacto_Proveedor(?) as 'Informacion_del_proveedor';";
    
    try (Connection con = C1.establecerConexion(); 
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        int productoID = Integer.parseInt(productoIDText); // Convertir el texto a entero
        
        pst.setInt(1, productoID);
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String[] datos = new String[1];
            datos[0] = rs.getString("Informacion_del_proveedor");
            
            modelo.addRow(datos);
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta5() {//-- 5 CONSULTA / 3ER MULTITABLA 
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("FacturaID");
    modelo.addColumn("Monto Total Factura");
    modelo.addColumn("Total Pagado");
    modelo.addColumn("Saldo Restante");
    
    String sql = "SELECT f.FacturaID, " +
                 "       SUM(p.PrecioUnidad * p.Cantidad) AS MontoTotalFactura, " +
                 "       ISNULL(SUM(cb.MontoPagado), 0) AS TotalPagado, " +
                 "       SUM(p.PrecioUnidad * p.Cantidad) - ISNULL(SUM(cb.MontoPagado), 0) AS SaldoRestante " +
                 "FROM Factura f " +
                 "LEFT JOIN FacturaProducto FP ON f.FacturaID = FP.FacturaID " +
                 "LEFT JOIN Producto p ON FP.ProductoID = p.ProductoID " +
                 "LEFT JOIN CajaBanco CB ON f.FacturaID = CB.FacturaID " +
                 "GROUP BY f.FacturaID " +
                 "ORDER BY f.FacturaID";
    
    try (Connection con = C1.establecerConexion();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            String facturaID = rs.getString("FacturaID");
            String montoTotal = rs.getString("MontoTotalFactura");
            String totalPagado = rs.getString("TotalPagado");
            String saldoRestante = rs.getString("SaldoRestante");
            
            modelo.addRow(new Object[]{facturaID, montoTotal, totalPagado, saldoRestante});
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta6(String facturaIDText) {//- 6 CONSULTA / 4TA MULTITABLA 
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("FacturaID");
    modelo.addColumn("PagoID");
    modelo.addColumn("FechaPago");
    modelo.addColumn("MontoPagado");
    modelo.addColumn("MedioPago");
    
    String sql = "DECLARE @FacturaID INT = ?; " +
                 " " +
                 "SELECT F.FacturaID, " +
                 "       CB.PagoID, " +
                 "       CB.FechaPago, " +
                 "       CB.MontoPagado, " +
                 "       MP.Detalles AS MedioPago " +
                 "FROM Factura F " +
                 "LEFT JOIN CajaBanco CB ON F.FacturaID = CB.FacturaID " +
                 "LEFT JOIN MedioPago MP ON CB.MedioID = MP.MedioID " +
                 "WHERE F.FacturaID = @FacturaID";
    
    try (Connection con = C1.establecerConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        int facturaID = Integer.parseInt(facturaIDText); // Convertir el texto a entero
        
        pst.setInt(1, facturaID);
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String facturaId = rs.getString("FacturaID");
            String pagoId = rs.getString("PagoID");
            String fechaPago = rs.getString("FechaPago");
            String montoPagado = rs.getString("MontoPagado");
            String medioPago = rs.getString("MedioPago");
            
            modelo.addRow(new Object[]{facturaId, pagoId, fechaPago, montoPagado, medioPago});
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta7() {//- 7 CONSULTA / 1ER PROCEDIMIENTO 
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("ProductoID");
    modelo.addColumn("Nombre Producto");
    modelo.addColumn("Total Comprado");
    
    String sql = "EXEC ConsultarProductosMasComprados;" ;

    
    try (Connection con = C1.establecerConexion();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            String ProductoID = rs.getString("ProductoID");
            String Nombre_Producto = rs.getString("Nombre_Producto");
            String TotalComprado = rs.getString("TotalComprado");
            
            modelo.addRow(new Object[]{ProductoID, Nombre_Producto, TotalComprado});
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}

public void Consulta4(JTextField ID) {//-- 4 CONSULTA / 2DA FUNCION 
    String productoIDText = ID.getText();
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("ProductoID");
    modelo.addColumn("Nombre_Producto");
    modelo.addColumn("PrecioUnidad");
    modelo.addColumn("Cantidad");
    modelo.addColumn("MontoTotal");
    modelo.addColumn("Nombre_Proveedor");

    
    
    
        
    
    String sql = "select * from ProductosProveedor(?);";
    
    try (Connection con = C1.establecerConexion(); 
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        
        
        pst.setString(1, productoIDText);  
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String[] datos = new String[6];
            datos[0] = rs.getString("ProductoID");
             datos[1] = rs.getString("Nombre_Producto");
            datos[2] = rs.getString("PrecioUnidad");
            datos[3] = rs.getString("Cantidad");
            datos[4] = rs.getString("MontoTotal");
            datos[5] = rs.getString("Nombre_Proveedor");
           
            modelo.addRow(datos);
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}


public void Consulta8() {// -- 8 CONSULTA / 2DO PROCEDIMIENTRO 
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("ProductoID");
    modelo.addColumn("Nombre_Producto");
    modelo.addColumn("pesostotales");

        
    
    String sql = "EXEC PesosProductos;";
    
    try (Connection con = C1.establecerConexion(); 
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        
        
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String[] datos = new String[3];
            datos[0] = rs.getString("ProductoID");
             datos[1] = rs.getString("Nombre_Producto");
            datos[2] = rs.getString("pesostotales");

           
            modelo.addRow(datos);
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta9() {//- 9 CONSULTA/ 1ER SUBCONSULTAS 
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Nombre_Proveedor");
    modelo.addColumn("FacturasEmitidas");
    
    String sql = "SELECT Proveedor.Nombre_Proveedor, " +
                 "(SELECT COUNT(*) " +
                 " FROM Factura " +
                 " WHERE Factura.ProveedorID = Proveedor.ProveedorID) " +
                 "AS FacturasEmitidas " +
                 "FROM Proveedor;";
    
    try (Connection con = C1.establecerConexion(); 
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String[] datos = new String[2];
            datos[0] = rs.getString("Nombre_Proveedor");
            datos[1] = rs.getString("FacturasEmitidas");
            modelo.addRow(datos);
        }
        
        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);
        
        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta10() { // 10 CONSULTA / 2DA SUBCONSULTA
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("ProveedorID");
    modelo.addColumn("Nombre del Proveedor");
    modelo.addColumn("Total Guias");

    String sql = "SELECT " +
                 "p.ProveedorID, " +
                 "p.Nombre_Proveedor, " +
                 "(SELECT COUNT(ga.GuiaID) " +
                 " FROM GuiaAlmacen ga " +
                 " WHERE ga.ProveedorID = p.ProveedorID) AS Total_Guias " +
                 "FROM Proveedor p";

    try (Connection con = C1.establecerConexion();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String proveedorID = rs.getString("ProveedorID");
            String nombreProveedor = rs.getString("Nombre_Proveedor");
            String totalGuias = rs.getString("Total_Guias");

            modelo.addRow(new Object[]{proveedorID, nombreProveedor, totalGuias});
        }

        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);

        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta11() {// 11 CONSULTA / 3ERA SUBCONSULTA
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("FacturaID");
    modelo.addColumn("Total Pagado");

    String sql = "SELECT " +
                 "F.FacturaID, " +
                 "(SELECT SUM(CB.MontoPagado) " +
                 " FROM CajaBanco CB " +
                 " WHERE CB.FacturaID = F.FacturaID) AS TotalPagado " +
                 "FROM Factura F";

    try (Connection con = C1.establecerConexion();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String facturaID = rs.getString("FacturaID");
            String totalPagado = rs.getString("TotalPagado");

            modelo.addRow(new Object[]{facturaID, totalPagado});
        }

        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);

        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta12() {// 12 CONSULTA / 4TA SUBCONSULTA
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("MedioID");
    modelo.addColumn("Medio de Pago");
    modelo.addColumn("Monto Total Gastado");

    String sql = "SELECT " +
                 "MP.MedioID, " +
                 "MP.Detalles AS Medio_Pago, " +
                 "(SELECT SUM(cb.MontoPagado) " +
                 " FROM CajaBanco cb " +
                 " WHERE cb.MedioID = mp.MedioID) AS Monto_Total_Gastado " +
                 "FROM MedioPago mp";

    try (Connection con = C1.establecerConexion();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String medioID = rs.getString("MedioID");
            String medioPago = rs.getString("Medio_Pago");
            String montoTotalGastado = rs.getString("Monto_Total_Gastado");

            modelo.addRow(new Object[]{medioID, medioPago, montoTotalGastado});
        }

        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);

        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta13(JTextField nombreProveedorTextField) {// --13 CONSULTA / 3ERA FUNCION
    String nombreProveedor = nombreProveedorTextField.getText();
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("Monto Pagado al Proveedor");

    String sql = "DECLARE @NombreProveedor NVARCHAR(100) = ?; " +
                 "SELECT dbo.ObtenerMontoPagadoPorNombreProveedor(@NombreProveedor) AS Monto_Pagado_Al_Proveedor";

    try (Connection con = C1.establecerConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, nombreProveedor);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String montoPagado = rs.getString("Monto_Pagado_Al_Proveedor");

            modelo.addRow(new Object[]{montoPagado});
        }

        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);

        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}
public void Consulta14(JTextField productoIDTextField) {//--14 CONSULTA / 3ER PROCEDIMIENTO 
    String productoIDText = productoIDTextField.getText();
    CConexion C1 = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("Nombre Producto");
    modelo.addColumn("Fecha Llegada");

    String sql = "EXEC VerificarLlegadaProductoAlmacenPorID @ProductoID = ?";

    try (Connection con = C1.establecerConexion();
         PreparedStatement pst = con.prepareStatement(sql)) {

        int productoID = Integer.parseInt(productoIDText); // Convertir el texto a entero

        pst.setInt(1, productoID);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String nombreProducto = rs.getString("NombreProducto");
            String fechaLlegada = rs.getString("FechaLlegada");

            modelo.addRow(new Object[]{nombreProducto, fechaLlegada});
        } else {
            modelo.addRow(new Object[]{"NO REGISTRADO", ""});
        }

        // Crear la tabla a partir del modelo
        JTable table = new JTable(modelo);

        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados de la Consulta", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
    }
}



}

