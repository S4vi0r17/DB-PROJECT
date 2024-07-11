package com.mycompany.IGU;

import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CCajaBanco {

    private String Fecha;

    public void mostrarCajaBanco(JTable paramTablaAlumnos) {
        CConexion C1 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";

        modelo.addColumn("PagoID");
        modelo.addColumn("MedioID");
        modelo.addColumn("FacturaID");
        modelo.addColumn("Fecha Pago");
        modelo.addColumn("Monto Pagado");

        paramTablaAlumnos.setModel(modelo);

        sql = "SELECT * FROM CajaBanco";
        String[] datos = new String[5];

        Statement st;
        try {
            st = C1.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error:" + e.toString());
        }
    }

    public void InsertarCajaBanco(JComboBox paramMedioPago, JComboBox paramFactura_ID, JDateChooser paramFecha_de_Pago, JTextField MontoPagadoField) {
        CConexion C1 = new CConexion();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPago = dateFormat.format(paramFecha_de_Pago.getDate());

        String FacturaID_IDStr = (String) paramFactura_ID.getSelectedItem();
        if (!facturaExiste(FacturaID_IDStr)) {
            JOptionPane.showMessageDialog(null, "La Factura ID no existe.");
            return;
        }

        String consulta = "INSERT INTO CajaBanco (MedioID, FacturaID, FechaPago, MontoPagado) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = C1.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt((String) paramMedioPago.getSelectedItem()));
            ps.setInt(2, Integer.parseInt(FacturaID_IDStr));
            ps.setString(3, fechaPago);
            float MontoPagado = Float.parseFloat(MontoPagadoField.getText());

            ps.setFloat(4, MontoPagado);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se realizó el pago correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar el pago: " + e.toString());
        }
    }

    private boolean facturaExiste(String facturaIDStr) {
        CConexion C1 = new CConexion();
        String consulta = "SELECT COUNT(*) FROM Factura WHERE FacturaID = ?";
        try {
            PreparedStatement ps = C1.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(facturaIDStr));
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false;
    }
}
