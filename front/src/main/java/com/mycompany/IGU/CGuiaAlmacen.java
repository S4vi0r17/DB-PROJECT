
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

public class CGuiaAlmacen {
public void InsertarGuia (JComboBox paramProveedor, JTextField paramNroGuia, JDateChooser paramFecha, JTextField paramPeso) {
    CConexion C1 = new CConexion();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = dateFormat.format(paramFecha.getDate());
    String consulta = "insert into GuiaAlmacen (ProveedorID, NumeroGuia, Fecha, Peso) values (?, ?, ?, ?)";
    try {
        CallableStatement cs = C1.establecerConexion().prepareCall(consulta);
        cs.setString(1, (String) paramProveedor.getSelectedItem());
        cs.setString(2, paramNroGuia.getText());
        cs.setString(3, fecha);
        
        BigDecimal peso = new BigDecimal(paramPeso.getText());
        cs.setBigDecimal(4, peso);
        
        cs.execute();
        JOptionPane.showMessageDialog(null, "Se insertó la guia almacen correctamente");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
}
   
   public void mostrarGuiaAlmacen(JTable paramTablaAlumnos){
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        
        modelo.addColumn("GuiaID");
        modelo.addColumn("ProveedorID");
        modelo.addColumn("Numero Guia");
        modelo.addColumn("Fecha");
        modelo.addColumn("Peso"); 
        
        paramTablaAlumnos.setModel(modelo);
        
        sql = "select * from GuiaAlmacen";
        String [] datos = new String [6];
        
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
