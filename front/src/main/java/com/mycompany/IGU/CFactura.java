package com.mycompany.IGU;

import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CFactura {
    private String FechaVencimiento;
    private String FechaEmision;

    public void mostrarFactu(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("FacturaID");
        modelo.addColumn("ProveedorID");
        modelo.addColumn("Condicion de Pago ID");
        modelo.addColumn("Fecha de Emision");
        modelo.addColumn("Fecha de Vencimiento");
        
        paramTablaAlumnos.setModel(modelo);
        
        sql = "SELECT * FROM Factura";
        String [] datos = new String [5];
        
        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:" + e.toString());
        }        
    }

    public void mostrarFactu30Dias(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("FacturaID");
        modelo.addColumn("ProveedorID");
        modelo.addColumn("Condicion de Pago ID");
        modelo.addColumn("Fecha de Emision");
        modelo.addColumn("Fecha de Vencimiento");
        
        paramTablaAlumnos.setModel(modelo);
        
        sql = "SELECT * FROM Factura WHERE FechaVencimiento <= DATEADD(DAY, 30, GETDATE());";
        String [] datos = new String [5];
        
        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:" + e.toString());
        }
    }

    public void mostrarFactu60Dias(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("FacturaID");
        modelo.addColumn("ProveedorID");
        modelo.addColumn("Condicion de Pago ID");
        modelo.addColumn("Fecha de Emision");
        modelo.addColumn("Fecha de Vencimiento");
        
        paramTablaAlumnos.setModel(modelo);
        
        sql = "SELECT * FROM Factura WHERE FechaVencimiento <= DATEADD(DAY, 60, GETDATE());";
        String [] datos = new String [5];
        
        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:" + e.toString());
        }
    }

    public void mostrarFactu90Dias(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("FacturaID");
        modelo.addColumn("ProveedorID");
        modelo.addColumn("Condicion de Pago ID");
        modelo.addColumn("Fecha de Emision");
        modelo.addColumn("Fecha de Vencimiento");
        
        paramTablaAlumnos.setModel(modelo);
        
        sql = "SELECT * FROM Factura WHERE FechaVencimiento <= DATEADD(DAY, 90, GETDATE());";
        String [] datos = new String [5];
        
        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:" + e.toString());
        }
    }

    public void InsertarFactura(JComboBox paramProveedor_ID, JComboBox paramCondicion_ID, JDateChooser paramFecha_de_Emision, JDateChooser paramFecha_de_Vencimiento) {
        CConexion C1 = new CConexion();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaVencimiento = dateFormat.format(paramFecha_de_Vencimiento.getDate());
        String fechaEmision = dateFormat.format(paramFecha_de_Emision.getDate());

        // Verifica que el ProveedorID existe en la tabla Proveedor
        String proveedorIDStr = (String) paramProveedor_ID.getSelectedItem();
        if (!proveedorExiste(proveedorIDStr)) {
            JOptionPane.showMessageDialog(null, "El ProveedorID no existe.");
            return;
        }

        String consulta = "INSERT INTO Factura (ProveedorID, CondicionPagoID, FechaEmision, FechaVencimiento) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = C1.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(proveedorIDStr));
            ps.setInt(2, Integer.parseInt((String) paramCondicion_ID.getSelectedItem()));
            ps.setString(3, fechaEmision);
            ps.setString(4, fechaVencimiento);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó la factura correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la factura: " + e.toString());
        }
    }

    private boolean proveedorExiste(String proveedorIDStr) {
        CConexion C1 = new CConexion();
        String consulta = "SELECT COUNT(*) FROM Proveedor WHERE ProveedorID = ?";
        try {
            PreparedStatement ps = C1.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(proveedorIDStr));
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public String getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(String FechaEmision) {
        this.FechaEmision = FechaEmision;
    }
}
