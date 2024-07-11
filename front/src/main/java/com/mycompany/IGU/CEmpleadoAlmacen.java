
package com.mycompany.IGU;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CEmpleadoAlmacen {
    public void mostrarEmpleados(JTable paramTablaEmpleados){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("Empleado ID");
        modelo.addColumn("Almacen ID");
        
        paramTablaEmpleados.setModel(modelo);
        sql = "select * from EmpleadoAlmacen";
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
            paramTablaEmpleados.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:"+e.toString());
        }
    }
        public void InsertarGuia(JComboBox paramEmpleado_ID, JComboBox paramGuia_ID) {
        CConexion C1 = new CConexion();
        String consulta = "insert into EmpleadoAlmacen values (?,?)";
        try {
            PreparedStatement ps = C1.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt((String) paramEmpleado_ID.getSelectedItem()));
            ps.setInt(2, Integer.parseInt((String) paramGuia_ID.getSelectedItem()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó la guia correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la guia: " + e.toString());
        }
    }
}
