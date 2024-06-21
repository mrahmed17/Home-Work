package cafemanagementsystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LogIn extends javax.swing.JFrame {

    public LogIn() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logInBoard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnLogIn = new javax.swing.JButton();
        visible = new javax.swing.JLabel();
        invisible = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        logInBoard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logInBoard.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                logInBoardMouseDragged(evt);
            }
        });
        logInBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logInBoardMousePressed(evt);
            }
        });
        logInBoard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Royal Cafe");
        logInBoard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 400, 40));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Welcome");
        logInBoard.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 40));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("To");
        logInBoard.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 400, 40));

        userName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        userName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logInBoard.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 200, 30));

        password.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        password.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logInBoard.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 200, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Password");
        logInBoard.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("User Name");
        logInBoard.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        btnCancel.setBackground(new java.awt.Color(204, 0, 0));
        btnCancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        logInBoard.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 80, -1));

        btnLogIn.setBackground(new java.awt.Color(0, 153, 0));
        btnLogIn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLogIn.setForeground(new java.awt.Color(255, 255, 255));
        btnLogIn.setText("LogIn");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });
        logInBoard.add(btnLogIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 80, -1));

        visible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/visible.png"))); // NOI18N
        visible.setText("   ");
        visible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visibleMouseClicked(evt);
            }
        });
        logInBoard.add(visible, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 30, 30));

        invisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invisible.png"))); // NOI18N
        invisible.setText("   ");
        invisible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invisibleMouseClicked(evt);
            }
        });
        logInBoard.add(invisible, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logInBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logInBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        if (isEmpty()) {
            String uname = userName.getText();
            String pass = String.valueOf(password.getPassword());
            if (uname.equals("admin") && pass.equals("admin")) {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dashboard.pack();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "warning", 2);
            }
        }
    }//GEN-LAST:event_btnLogInActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    int xx, xy;
    private void logInBoardMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInBoardMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_logInBoardMouseDragged

    private void logInBoardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInBoardMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_logInBoardMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        for (double i = 0.0; i <= 1.0; i += 0.1) {  //correct but shows error (less than or equal)
        for (double i = 0.0; i > 1.0; i += 0.1) {
            float f = (float) i;
            this.setOpacity(f);
            try {
                 Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_formWindowOpened

    private void visibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visibleMouseClicked
        password.setEchoChar((char) 0);
        visible.setVisible(false);
        invisible.setVisible(true);
    }//GEN-LAST:event_visibleMouseClicked

    private void invisibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invisibleMouseClicked
        password.setEchoChar('*');
        visible.setVisible(true);
        invisible.setVisible(false);
    }//GEN-LAST:event_invisibleMouseClicked

    public boolean isEmpty() {
        if (userName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter user name.", "warning", 2);
            return false;
        }
        if (String.valueOf(password.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter password.", "warning", 2);
            return false;
        }

        return true;
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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JLabel invisible;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel logInBoard;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel visible;
    // End of variables declaration//GEN-END:variables
}
