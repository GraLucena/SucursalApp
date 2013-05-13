package ventanas;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.*;
import sucursalapp.*;


/**
 * Menú principal de la franquicia.
 *
 * @author daniel
 */
public class LoginSucursal extends javax.swing.JFrame {

    public LoginSucursal() {
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        jTnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jBlogin = new javax.swing.JButton();
        jLsucursal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Password:");
        jLabel5.setBounds(130, 210, 70, 15);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTnombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTnombreActionPerformed(evt);
            }
        });
        jTnombre.setBounds(200, 170, 120, 21);
        jLayeredPane1.add(jTnombre, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carrinho supermercado.png"))); // NOI18N
        jLabel3.setText("Logo");
        jLabel3.setBounds(320, 20, 90, 100);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setText("Login Sucursal ");
        jLabel1.setBounds(170, 50, 150, 30);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPassword.setBounds(200, 210, 120, 20);
        jLayeredPane1.add(jPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBlogin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jBlogin.setText("Login");
        jBlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBloginActionPerformed(evt);
            }
        });
        jBlogin.setBounds(220, 260, 73, 23);
        jLayeredPane1.add(jBlogin, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLsucursal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLsucursal.setText("Sucursal:");
        jLsucursal.setBounds(130, 170, 60, 15);
        jLayeredPane1.add(jLsucursal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondos-verdes.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBounds(0, 0, 540, 350);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 545, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 348, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnombreActionPerformed

    private void jBloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBloginActionPerformed
            XMLSucursal sucursales = new XMLSucursal();
           SucursalApp.nombresucursal =  this.jTnombre.getText(); 
            if(sucursales.comprobarUsuarios(this.jTnombre.getText(), this.jPassword.getText())!=false)
            {
                try
                {
                    java.net.InetAddress i = java.net.InetAddress.getLocalHost();
                    //System.out.println(i.getHostAddress());
                    XMLInventario actualizarXml = new XMLInventario(SucursalApp.nombresucursal);
                    actualizarXml.actualizarIpSucursales(this.jTnombre.getText(), i.getHostAddress());
                    
                }
                catch (IOException ex) 
                {
                    System.out.println("no obtuve el ip");
                }
                
                
                
                Coordinador validarCoordinador = new Coordinador();
                Sucursal sucursal = new Sucursal();
                validarCoordinador.buscarCoordinador(this.jTnombre.getText());
                
                MenuSucursal menu = new MenuSucursal(this.jTnombre.getText());
                menu.setVisible(true);
            }
            else
            JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jBloginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginSucursal().setVisible(true);
            }
        });
    }
    /**
     * Inicializa los componentes principales de la ventana
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBlogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel jLsucursal;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jTnombre;
    // End of variables declaration//GEN-END:variables
}
