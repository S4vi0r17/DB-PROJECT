/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package IGU;

import com.mycompany.IGU.CFactura;
import com.mycompany.IGU.CGuiaAlmacen;
import com.mycompany.IGU.RellenarCombos;

/**
 *
 * @author User
 */
public class CrearGuiaAlmacen extends javax.swing.JFrame {

    /**
     * Creates new form CrearGuiaAlmacen
     */
    RellenarCombos C1= new RellenarCombos();
    public CrearGuiaAlmacen() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Sistema de cuentas por pagar");
        C1.RellenarComboBox("Proveedor","ProveedorID" , jComboBox1);
        CGuiaAlmacen GuiaAlmacen = new CGuiaAlmacen ();
        GuiaAlmacen.mostrarGuiaAlmacen(jTable1);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Factura");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(java.awt.SystemColor.inactiveCaptionBorder);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Guía almacén");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jLabel4.setText("Proveedor");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 82, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 79, 150, -1));

        jLabel5.setText("Número guía");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 150, -1));

        jLabel6.setText("Fecha");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, -1));
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 140, -1));

        jLabel7.setText("Peso");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 130, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("  Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 420, 120, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 110, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, 210));

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);
        jPanel1.setForeground(java.awt.SystemColor.activeCaption);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CGuiaAlmacen GuiaAlmacen = new CGuiaAlmacen();
        GuiaAlmacen.InsertarGuia(jComboBox1, jTextField6, jDateChooser1, jTextField7);
        
        jComboBox1.setSelectedIndex(0);
        jTextField6.setText("");
        jDateChooser1.setDate(null);
        jTextField7.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal principal = new Principal();
        principal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearGuiaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearGuiaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearGuiaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearGuiaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearGuiaAlmacen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
