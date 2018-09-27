/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;

import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author viniciuslucena
 */
public class Initial extends javax.swing.JFrame {

    /**
     * Creates new form Initial
     */
    public Initial() {
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

        lblInsertYourName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnEnter = new javax.swing.JButton();
        lblWelcomeMessage = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Replicated Chat");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setName("Replicated Chat"); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 300));
        setResizable(false);
        getContentPane().setLayout(null);

        lblInsertYourName.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblInsertYourName.setForeground(new java.awt.Color(255, 255, 255));
        lblInsertYourName.setText("Digite seu nome:");
        getContentPane().add(lblInsertYourName);
        lblInsertYourName.setBounds(100, 130, 160, 16);

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtName);
        txtName.setBounds(260, 120, 130, 30);

        btnEnter.setText("Entrar");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnter);
        btnEnter.setBounds(210, 190, 100, 50);

        lblWelcomeMessage.setFont(new java.awt.Font("Copperplate", 1, 48)); // NOI18N
        lblWelcomeMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcomeMessage.setText("Replicated Chat");
        getContentPane().add(lblWelcomeMessage);
        lblWelcomeMessage.setBounds(40, 30, 440, 28);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clientchat/background-center.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 512, 301);

        getAccessibleContext().setAccessibleName("Replicated Chat");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        ClientChat oClientChat = null;
        Component frame = null;
        String username = txtName.getText();
        
        if( username.equals("") ) {
            JOptionPane.showMessageDialog(frame, "Digite um nome válido!");
        } else {
            this.setVisible(false);
            try {
                oClientChat = new ClientChat(username);
            } catch (IOException ex) {
                Logger.getLogger(Initial.class.getName()).log(Level.SEVERE, null, ex);
            }
            oClientChat.setVisible(true);
        }
    }//GEN-LAST:event_btnEnterActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

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
            java.util.logging.Logger.getLogger(Initial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Initial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Initial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Initial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Initial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnter;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblInsertYourName;
    private javax.swing.JLabel lblWelcomeMessage;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
