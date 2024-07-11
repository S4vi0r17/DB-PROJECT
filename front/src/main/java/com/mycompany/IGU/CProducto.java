
package com.mycompany.IGU;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CProducto {
        public void InsertarProducto (JTextField paramNombre_proveedor, JTextField paramPrecioUnidad, JTextField paramCantidad) {
        CConexion C1 = new CConexion();
        String consulta = "insert into Producto values(?, ?, ?)";
        try {
            CallableStatement cs = C1.establecerConexion().prepareCall(consulta);
            cs.setString(1, paramNombre_proveedor.getText());
            BigDecimal precio = new BigDecimal(paramPrecioUnidad.getText());
            cs.setBigDecimal(2, precio.setScale(2, RoundingMode.HALF_UP)); // Escalamos a 2 decimales
            cs.setInt(3, Integer.parseInt(paramCantidad.getText()));
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se insertó el proveedor correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
        public void mostrarProductos(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("Producto ID");
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Monto Total");
  
        
        paramTablaAlumnos.setModel(modelo);
        
        sql = "select * from Producto";
        String [] datos = new String [5];
        
        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);

                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:"+e.toString());
        }
        
}
}

