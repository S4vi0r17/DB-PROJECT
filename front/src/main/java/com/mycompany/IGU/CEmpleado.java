package com.mycompany.IGU;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CEmpleado {
    public void InsertarEmpleado (JTextField paramNombre_Empleado, JTextField paramDireccion, JTextField paramTelefono) {
        CConexion C1 = new CConexion();
        String consulta = "insert into Empleado (Nombre_Empleado, Direccion, Telefono) values (?, ?, ?)";
        try {
            CallableStatement cs = C1.establecerConexion().prepareCall(consulta);
            cs.setString(1, paramNombre_Empleado.getText());
            cs.setString(2, paramDireccion.getText());
            cs.setString(3, paramTelefono.getText());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se insertó el empleado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public void mostrarEmpleado(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("EmpleadoID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");      
        paramTablaAlumnos.setModel(modelo);
        
        sql = "select * from Empleado";
        String [] datos = new String [4];
        
        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);                        
                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:"+e.toString());
        }
    }
}
