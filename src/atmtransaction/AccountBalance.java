package atmtransaction;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AccountBalance extends javax.swing.JFrame {
    
    //store the account number of the currently logged-in user
    private String accountNumber;
    
    //default constructor that initializes GUI components
    public AccountBalance() {
        initComponents();
    }
    
    //constructor that initializes user's account number
    public AccountBalance(String accNum) {
        this.accountNumber = accNum; //save the account number of the user
        initComponents();
        displayAccNumAndBalance(); //display user account number and balance
    }
    
    //method to retrieve and display the account number and current balance
    private void displayAccNumAndBalance() {
        try {
            //fetch account details (account number and balance) from the database
            String query = "SELECT account_number, balance FROM account WHERE account_number = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
            ps.setString(1, accountNumber); //set the account number in the query
            ResultSet rs = ps.executeQuery(); //execute the query

            //check if account exists in the database
            if(rs.next()) {
                //retrieve account number and balance
                String accNumber = rs.getString("account_number");
                double balance = rs.getDouble("balance");
                
                //display account number and balance
                jLabelAccNum.setText("" + accNumber);
                jLabelCurrent.setText("PHP " + balance + "0");
            }else{ //if account does not exists
                jLabelCurrent.setText("Account not found");
            }
        }catch (SQLException ex) {
            Logger.getLogger(AccountBalance.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabelAccNum = new javax.swing.JLabel();
        jLabelCurrent = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButtonEnter = new javax.swing.JButton();
        jLabelNo = new javax.swing.JLabel();
        jLabelYes = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(762, 855));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MEGA BANK");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("AUTOMATED TELLERING MACHINE");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jLabel1.setText("jLabel17");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 752, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("YOUR BALANCE IS:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("ACCOUNT NO.:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("CURRENT:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("YES");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("NO");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setText("WOULD YOU LIKE TO DO ANOTHER TRANSACTION?");

        jLabelAccNum.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelAccNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelAccNum.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 3, true));

        jLabelCurrent.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelCurrent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCurrent.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 3, true));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(383, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jSeparator2))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(80, 80, 80))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelCurrent, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jLabelAccNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 42, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelAccNum, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 165, 570, 390));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton1.setText("1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 568, 120, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton5.setText("5");
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 629, 120, -1));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton6.setText("6");
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 629, 120, -1));

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton7.setText("7");
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 690, 120, -1));

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton8.setText("8");
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 690, 120, -1));

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton9.setText("9");
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 690, 120, -1));

        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton10.setText("0");
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 752, 120, 55));

        jButton15.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 752, 120, 55));

        jButton16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 752, 120, 55));

        jButtonClear.setBackground(new java.awt.Color(255, 255, 0));
        jButtonClear.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jButtonClear.setText("CLEAR");
        getContentPane().add(jButtonClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 568, 170, 56));

        jButtonCancel.setBackground(new java.awt.Color(255, 0, 0));
        jButtonCancel.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jButtonCancel.setText("CANCEL");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 629, 170, 56));

        jButton14.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 752, 170, 55));

        jButtonEnter.setBackground(new java.awt.Color(0, 204, 51));
        jButtonEnter.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jButtonEnter.setText("ENTER");
        getContentPane().add(jButtonEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 690, 170, 56));

        jLabelNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabelNo.setText("jLabel5");
        jLabelNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelNoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 490, 73, -1));

        jLabelYes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabelYes.setText("jLabel5");
        jLabelYes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelYesMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 410, 73, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel14.setText("jLabel5");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 330, 73, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel15.setText("jLabel5");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 250, 73, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel19.setText("jLabel5");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 73, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel17.setText("jLabel5");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 73, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel16.setText("jLabel5");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 73, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel18.setText("jLabel5");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 73, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton4.setText("4");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 629, 120, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton2.setText("2");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 568, 120, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton3.setText("3");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 568, 120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelYesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelYesMouseClicked
        //if "YES" label is clicked
        //return to main menu screen and pass the account number
        MainMenu mainMenuScreen = new MainMenu(accountNumber);
        mainMenuScreen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelYesMouseClicked

    private void jLabelNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNoMouseClicked
        //if "NO" label is clicked
        //open the transaction successful screen
        new TransactionSuccessful().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelNoMouseClicked

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        //if "CANCEL" btn is clicked
        //open the cancelled transaction screen
        new CancelledTransaction().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed
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
            java.util.logging.Logger.getLogger(AccountBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountBalance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAccNum;
    private javax.swing.JLabel jLabelCurrent;
    private javax.swing.JLabel jLabelNo;
    private javax.swing.JLabel jLabelYes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables

}