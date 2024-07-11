
package com.mycompany.IGU;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class RellenarCombos {
public void RellenarComboBox (String Tabla, String valor, JComboBox combo){
    String sql="select * from "+ Tabla;
    Statement st;
    CConexion con = new CConexion();
    Connection conexion=con.establecerConexion();
    try {
        st=conexion.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while(rs.next()){
            combo.addItem(rs.getString(valor));
        }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error : "+e.toString());
    }
    
    
    
}
}
