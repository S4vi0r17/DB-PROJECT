
package com.mycompany.IGU;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion {
        Connection conectar = null;
        
        String Usuario ="root";
        String Contraseña ="root";
        String bd = "Sistema_CxP";
        String ip ="localhost";
        String puerto = "1433";
        
        
        public Connection establecerConexion(){
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + ";" + "databaseName=" + bd + ";" +
                            "encrypt=true;trustServerCertificate=true";    
                conectar = DriverManager.getConnection(cadena,Usuario, Contraseña);
            }catch  (Exception e){
                System.out.println(e);
                
            }
            return conectar;
        }
}
