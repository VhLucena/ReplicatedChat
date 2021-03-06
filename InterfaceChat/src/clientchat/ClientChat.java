/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author viniciuslucena
 */
public class ClientChat extends javax.swing.JFrame {

    private static ClientAdapter oClient;

    /**
     * Creates new form NewJFrame
     */
    public ClientChat(String username) throws IOException {
        initComponents();

        ClientChat.oClient = new ClientAdapter(username, getIdByName(username));
        
        System.out.println("Logado como: " + username);
        lblUsername.setText(username);
        
        System.out.println("Iniciando Threads");
        startThreadsHistoryAndUsers();
    }
    
    ClientChat(JTextField txtName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHistoricoConversa = new javax.swing.JLabel();
        lblUsuariosOnline = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        lblOnlineUsers = new javax.swing.JLabel();
        lblHistoryConversation = new javax.swing.JLabel();
        lblOnlineUsersBackground = new javax.swing.JLabel();
        lblHistoryConversationBackground = new javax.swing.JLabel();
        pnlEnviar = new javax.swing.JPanel();
        btnSend = new javax.swing.JButton();
        txtInputMessage = new javax.swing.JTextField();
        lblLogadoComo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Replicated Chat");
        setPreferredSize(new java.awt.Dimension(790, 650));
        setResizable(false);
        getContentPane().setLayout(null);

        lblHistoricoConversa.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblHistoricoConversa.setForeground(new java.awt.Color(255, 255, 255));
        lblHistoricoConversa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHistoricoConversa.setText("Histórico de conversa");
        getContentPane().add(lblHistoricoConversa);
        lblHistoricoConversa.setBounds(106, 110, 340, 22);

        lblUsuariosOnline.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblUsuariosOnline.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuariosOnline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuariosOnline.setText("Usuários Online");
        getContentPane().add(lblUsuariosOnline);
        lblUsuariosOnline.setBounds(496, 110, 190, 22);

        lblTitle.setFont(new java.awt.Font("Copperplate", 1, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Replicated Chat");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(110, 30, 580, 28);

        pnlPrincipal.setOpaque(false);
        pnlPrincipal.setLayout(null);

        lblUsername.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlPrincipal.add(lblUsername);
        lblUsername.setBounds(580, 430, 180, 20);

        lblOnlineUsers.setForeground(new java.awt.Color(255, 255, 255));
        lblOnlineUsers.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlPrincipal.add(lblOnlineUsers);
        lblOnlineUsers.setBounds(480, 20, 180, 390);

        lblHistoryConversation.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblHistoryConversation.setForeground(new java.awt.Color(255, 255, 255));
        lblHistoryConversation.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlPrincipal.add(lblHistoryConversation);
        lblHistoryConversation.setBounds(100, 20, 320, 380);

        lblOnlineUsersBackground.setForeground(new java.awt.Color(255, 255, 255));
        lblOnlineUsersBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clientchat/background-sides.jpg"))); // NOI18N
        lblOnlineUsersBackground.setText("jLabel1");
        lblOnlineUsersBackground.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));
        pnlPrincipal.add(lblOnlineUsersBackground);
        lblOnlineUsersBackground.setBounds(470, 10, 200, 400);

        lblHistoryConversationBackground.setBackground(new java.awt.Color(255, 255, 255));
        lblHistoryConversationBackground.setForeground(new java.awt.Color(255, 255, 255));
        lblHistoryConversationBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clientchat/background-sides.jpg"))); // NOI18N
        lblHistoryConversationBackground.setText("jLabel1");
        lblHistoryConversationBackground.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));
        pnlPrincipal.add(lblHistoryConversationBackground);
        lblHistoryConversationBackground.setBounds(90, 10, 340, 400);

        pnlEnviar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEnviarLayout = new javax.swing.GroupLayout(pnlEnviar);
        pnlEnviar.setLayout(pnlEnviarLayout);
        pnlEnviarLayout.setHorizontalGroup(
            pnlEnviarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnviarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtInputMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend))
        );
        pnlEnviarLayout.setVerticalGroup(
            pnlEnviarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnviarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEnviarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtInputMessage))
                .addContainerGap())
        );

        pnlPrincipal.add(pnlEnviar);
        pnlEnviar.setBounds(90, 420, 338, 47);

        lblLogadoComo.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblLogadoComo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogadoComo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLogadoComo.setText("Logado como: ");
        pnlPrincipal.add(lblLogadoComo);
        lblLogadoComo.setBounds(440, 430, 140, 17);

        getContentPane().add(pnlPrincipal);
        pnlPrincipal.setBounds(20, 140, 755, 477);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clientchat/background-center.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-3, 0, 800, 630);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        try {
            ClientChat.oClient.sendMessage(this.txtInputMessage.getText());
            this.txtInputMessage.setText("");
        } catch (IOException ex) {
            Logger.getLogger(ClientChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    
    private void startThreadsHistoryAndUsers() {
        new Thread() {
            
            public void run() {
                
                GetterHistoryThread getterHistoryThread = new GetterHistoryThread(ClientChat.oClient);
                GetterUsersThread getterUsersThread = new GetterUsersThread(ClientChat.oClient);

                while(true) {
                    lblHistoryConversation.setText(getterHistoryThread.history);
                    lblOnlineUsers.setText(getterUsersThread.listUsers);
                }
            }
            
            
        }.start();
    }
   
    private static int getIdByName(String usr) {
        int answer = 0;
        
        for(int i = 0; i < usr.length(); i++)
            answer += Character.getNumericValue(usr.charAt(i));
        
        return answer;
    }
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
            java.util.logging.Logger.getLogger(ClientChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClientChat("").setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ClientChat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblHistoricoConversa;
    private javax.swing.JLabel lblHistoryConversation;
    private javax.swing.JLabel lblHistoryConversationBackground;
    private javax.swing.JLabel lblLogadoComo;
    private javax.swing.JLabel lblOnlineUsers;
    private javax.swing.JLabel lblOnlineUsersBackground;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsuariosOnline;
    private javax.swing.JPanel pnlEnviar;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtInputMessage;
    // End of variables declaration//GEN-END:variables
}
