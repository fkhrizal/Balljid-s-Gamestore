/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.security.util.Password;

/**
 *
 * @author ASUS
 */
public class LoginDesign extends javax.swing.JFrame {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pat = null;
    
    public LoginDesign() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        this.setTitle("GameCenter All of Games are Here!");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbl_usr = new javax.swing.JLabel();
        lbl_pswd = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        TxtUsername = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        TxtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 480));
        setPreferredSize(new java.awt.Dimension(1280, 470));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_usr.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        lbl_usr.setForeground(new java.awt.Color(255, 33, 33));
        getContentPane().add(lbl_usr, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 250, 20));

        lbl_pswd.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        lbl_pswd.setForeground(new java.awt.Color(248, 50, 50));
        getContentPane().add(lbl_pswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 250, 20));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 102, 255));
        jLabel6.setText("create one!");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 70, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel5.setText("No account?");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 80, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setText("Sign in");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 100, 50));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Password");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 80, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 250, 20));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 250, 10));

        TxtUsername.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtUsername.setText("Username");
        TxtUsername.setBorder(null);
        TxtUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtUsernameMouseClicked(evt);
            }
        });
        TxtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtUsernameKeyPressed(evt);
            }
        });
        getContentPane().add(TxtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 250, 30));

        jButton1.setBackground(new java.awt.Color(51, 198, 29));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Next");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 70, -1));

        TxtPassword.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtPassword.setBorder(null);
        TxtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtPasswordMouseClicked(evt);
            }
        });
        TxtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPasswordActionPerformed(evt);
            }
        });
        TxtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(TxtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 250, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 310, 250));

        jLabel7.setBackground(new java.awt.Color(43, 96, 100));
        jLabel7.setFont(new java.awt.Font("Bebas", 0, 100)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 370, 250, 70));

        jLabel9.setBackground(new java.awt.Color(54, 85, 102));
        jLabel9.setFont(new java.awt.Font("Bebas", 0, 100)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("BALJID ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 250, 120));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/STEAM.png.3228d01e2cfac78e14af570fd080815d.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 470));

        jLabel8.setBackground(new java.awt.Color(54, 85, 102));
        jLabel8.setFont(new java.awt.Font("Bebas", 0, 120)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BALJID ");
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 310, 120));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPasswordActionPerformed

    private void TxtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtPasswordMouseClicked
        jLabel1.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_TxtPasswordMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(TxtUsername.getText().equals("")) {
            lbl_usr.setText("Username is empty!");

        }
        if(TxtPassword.getText().equals("")) {
            lbl_pswd.setText("Password is empty!");

        }
        else{
            String SQL = "Select * From Game.Login where Username = ? and Password =?";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=GameStore;integratedSecurity=true";
                String Username="";
                String Password="";
                con=DriverManager.getConnection(url,Username,Password);
                pat = con.prepareStatement(SQL);
                pat.setString(1, TxtUsername.getText());
                pat.setString(2, TxtPassword.getText());
                rs=pat.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Username and Password Matched!");
                    hide();
                     Dashboard su = new Dashboard();
                    su.show();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username and Password do not Matched!");
                    TxtUsername.setText("");
                    TxtPassword.setText("");}
                con.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
            }
        }

        // TODO add your handling code here:
        //String Username,Password,ee;
        //Username=TxtUsername.getText();
        //Password=TxtPassword.getText();

        //if(Username.equals("admin")&& Password.equals("admin")){
            //JOptionPane.showMessageDialog(null, "Username and Password Matched!");}
        //else{
            //JOptionPane.showMessageDialog(null, "Username and Password do not Matched!");}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtUsernameMouseClicked
        TxtUsername.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_TxtUsernameMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        hide();
        Register reg = new Register();
        reg.show();// TODO add your handling code here:

    }//GEN-LAST:event_jLabel6MouseClicked

    private void TxtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUsernameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_TAB) {TxtUsername.setText("");}
    }//GEN-LAST:event_TxtUsernameKeyPressed

    private void TxtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPasswordKeyPressed
        jLabel1.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPasswordKeyPressed

    /**
     * @param args the command line arguments
     */
    
    

    
    private int validasi() {
        int flag = 0;
        if(TxtUsername.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Username is empty!");
            flag = 1;
        }
        if(TxtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Password is empty!");
            flag = 1;
        }
        return flag;
    }






     
     
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
            java.util.logging.Logger.getLogger(LoginDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginDesign().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField TxtPassword;
    private javax.swing.JTextField TxtUsername;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_pswd;
    private javax.swing.JLabel lbl_usr;
    // End of variables declaration//GEN-END:variables

   
    
}
