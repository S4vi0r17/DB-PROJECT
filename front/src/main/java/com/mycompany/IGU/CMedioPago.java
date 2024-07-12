
package com.mycompany.IGU;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CMedioPago {
    public void mostrarCondicionPago(JTable paramTablaEmpleados){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("Medio ID");
        modelo.addColumn("Detalles");
        
        paramTablaEmpleados.setModel(modelo);
        sql = "select * from MedioPago";
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
        public void InsertarCondicionPago (JTextField paramDetalles) {
    CConexion C1 = new CConexion();
    String consulta = "insert into MedioPago values (?);";
    try {
        CallableStatement cs = C1.establecerConexion().prepareCall(consulta);
        cs.setString(1, paramDetalles.getText());
        
        
        cs.execute();
        JOptionPane.showMessageDialog(null, "Se insertó la Medio de Pago correctamente");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
}
}
