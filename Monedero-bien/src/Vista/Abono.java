/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author 8a
 */
public class Abono extends javax.swing.JFrame {

    /**
     * Creates new form Abono
     */
    public Abono() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        id_cargo = new javax.swing.JLabel();
        id_premio = new javax.swing.JLabel();
        num = new javax.swing.JLabel();
        txtIdAbono = new javax.swing.JTextField();
        txtPuntos = new javax.swing.JTextField();
        txtIdTicket = new javax.swing.JTextField();
        num1 = new javax.swing.JLabel();
        txtNumCuenta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_cargo.setText("Id_abono");
        jPanel1.add(id_cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 59, -1, -1));

        id_premio.setText("Puntos");
        jPanel1.add(id_premio, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 97, -1, -1));

        num.setText("Id_ticket:");
        jPanel1.add(num, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));
        jPanel1.add(txtIdAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 56, 198, -1));
        jPanel1.add(txtPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 94, 198, -1));
        jPanel1.add(txtIdTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 132, 198, -1));

        num1.setText("Número de cuenta:");
        jPanel1.add(num1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));
        jPanel1.add(txtNumCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 198, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(180, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(169, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Abono().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel id_cargo;
    public javax.swing.JLabel id_premio;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel num;
    public javax.swing.JLabel num1;
    public javax.swing.JTextField txtIdAbono;
    public javax.swing.JTextField txtIdTicket;
    public javax.swing.JTextField txtNumCuenta;
    public javax.swing.JTextField txtPuntos;
    // End of variables declaration//GEN-END:variables
}