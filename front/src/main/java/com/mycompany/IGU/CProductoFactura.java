
package com.mycompany.IGU;

import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CProductoFactura {
public void InsertarFactura(JComboBox paramProveedor_ID, JComboBox paramCondicion_ID) {
    CConexion C1 = new CConexion();


    // Verifica que el ProveedorID existe en la tabla Proveedor


    String consulta = "insert into FacturaProducto values (?,?)";
    try {
        PreparedStatement ps = C1.establecerConexion().prepareStatement(consulta);
        ps.setInt(1, Integer.parseInt((String) paramProveedor_ID.getSelectedItem()));
        ps.setInt(2, Integer.parseInt((String) paramCondicion_ID.getSelectedItem()));


        
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Se insertó la factura correctamente");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al insertar la factura: " + e.toString());
    }
}
        public void mostrarProductos(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("Factura ID");
        modelo.addColumn("Producto ID");
        
        paramTablaAlumnos.setModel(modelo);
        sql = "select * from FacturaProducto";
        String [] datos = new String [2];
        
        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);


                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:"+e.toString());
        }
        
}
}
