
package com.mycompany.IGU;

import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CCondicionPago {
    public void mostrarCondicionPago(JTable paramTablaEmpleados){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("Condicion ID");
        modelo.addColumn("Detalles");
        paramTablaEmpleados.setModel(modelo);
        sql = "select * from CondicionPago";
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
    String consulta = "insert into CondicionPago values (?);";
    try {
        CallableStatement cs = C1.establecerConexion().prepareCall(consulta);
        cs.setString(1, paramDetalles.getText());
        
        
        cs.execute();
        JOptionPane.showMessageDialog(null, "Se insertó la condicion de pago correctamente");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
}
}
