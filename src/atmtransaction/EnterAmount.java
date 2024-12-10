package atmtransaction;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class EnterAmount extends javax.swing.JFrame {

    //store the account number of the currently logged-in user and transaction type (withdraw/deposit)
    private String accountNumber, action;
    
    //default constructor that initializes GUI components
    public EnterAmount() {
        initComponents();
    }
    
    //constructor that initializes user's account number and action (transaction type)
    public EnterAmount(String action, String accNum) {
        this.accountNumber = accNum; //save the account number of the user
        this.action = action; //withdraw or deposit
        initComponents();
        addNumberButtonActions(); //action listeners to number buttons (0-9)
    }
   
    //method to add action listeners to number buttons (0-9)
    private void addNumberButtonActions() {
        javax.swing.JButton[] numberButtons = {
            jButton0, jButton1, jButton2, jButton3, jButton4, 
            jButton5, jButton6, jButton7, jButton8, jButton9, 
        };
        
        //loop through buttons and add action listeners
        for(int i = 0; i < numberButtons.length; i++) {
            int number = i; //map the array index to the corresponding number
            numberButtons[i].addActionListener(evt -> addNumberToAmountField(number));
        }
    }
    
    //add the clicked number to the amount input field
    private void addNumberToAmountField(int number) {
        String currentText = jTextFieldEnterAmount.getText();
        if(currentText.equals("0")) {
            //replace initial "0" with the clicked number
            jTextFieldEnterAmount.setText(String.valueOf(number));
        }else{
            //add the number to the existing text
            jTextFieldEnterAmount.setText(currentText + number);
        }
    }
    
    //method to check if the balance is sufficient for withdrawal
    private boolean isBalanceSufficient(double amount) throws SQLException {
        String query = "SELECT balance FROM account WHERE account_number = ?";
        PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query);
        pstmt.setString(1, accountNumber);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            //retrieve the current balance
            double balance = rs.getDouble("balance");
            if(balance >= amount) {
                return true; //if balance is sufficient
            }
        }
        return false; //if insufficient balance
    }

    //method for withdrawal process
    private boolean withdraw(double amount) {
        try {
            //check if balance is sufficient
            if(isBalanceSufficient(amount)) {
                //deduct amount from balance in database
                String updateQuery = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
                PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery);
                pstmt.setDouble(1, amount); //set the withdrawal amount
                pstmt.setString(2, accountNumber); //set the account number
                pstmt.executeUpdate(); //update the database
                JOptionPane.showMessageDialog(this, "Your account balance has been updated successfully.");
                return true; //successful transaction
            }else{
                JOptionPane.showMessageDialog(this, "Insufficient balance. Please enter a new amount.");
                jTextFieldEnterAmount.setText("");
                return false; //transaction failed
            }
        }catch (SQLException ex) {
            Logger.getLogger(EnterAmount.class.getName()).log(Level.SEVERE, null, ex);
            return false; 
        }
    }
      
    //method for deposit process
    private void deposit(double amount) {
        try {
            //add amount to balance in database
            String updateQuery = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery);
            pstmt.setDouble(1, amount); //set the deposit amount
            pstmt.setString(2, accountNumber); //set the account number
            pstmt.executeUpdate(); //update the database
            JOptionPane.showMessageDialog(this, "Your account balance has been updated successfully.");
        }catch (SQLException ex) {
            Logger.getLogger(EnterAmount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //method to log the transaction details in the database
    private void logTransaction(String action, double amount, String userAccountNumber) {
        try {
            //insert transaction record
            String sql = "INSERT INTO transaction (account_number, type, amount, date_time) VALUES (?, ?, ?, NOW())";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, accountNumber); //set user's account number
            pstmt.setString(2, action); //set transaction type
            pstmt.setDouble(3, amount); //set transaction amount

            //execute the insert query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Transaction logged successfully.");
            }
        }catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error logging transaction: " + ex.getMessage());
        }
    }
    
    //method to process the transaction based on the action (withdraw or deposit)
    private void processTransaction() {
        try {
            double amount = Double.parseDouble(jTextFieldEnterAmount.getText());

            if(amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return; //return if amount is invalid
            }

            boolean transactionSuccessful = false;
            if(action.equals("withdraw")) {
                transactionSuccessful = withdraw(amount); //withdrawal process
            }else if (action.equals("deposit")) {
                deposit(amount); //deposit process
                transactionSuccessful = true;
            }
            
            if(transactionSuccessful) {
                //log the transaction in the database and proceed to another screen
                logTransaction(action, amount, accountNumber);
                AnotherTransaction anotherTransaction = new AnotherTransaction(accountNumber);
                anotherTransaction.setVisible(true);
                this.dispose(); 
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            jTextFieldEnterAmount.setText("");
        }
    }
    
    //method for cancelled transaction
    private void cancelTransaction() {
        new CancelledTransaction().setVisible(true);
        this.dispose();
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
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldEnterAmount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton0 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButtonEnter = new javax.swing.JButton();
        jLabelNo = new javax.swing.JLabel();
        jLabelYes = new javax.swing.JLabel();
        jLabelBalanceInquiry = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(752, 855));
        setPreferredSize(new java.awt.Dimension(752, 855));
        setResizable(false);
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("YES");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("NO");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("AND CONFIRM");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("PLEASE CONFIRM DESIRED AMOUNT");

        jTextFieldEnterAmount.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jTextFieldEnterAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEnterAmount.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 3, true));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jSeparator2))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(188, 188, 188))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEnterAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jTextFieldEnterAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(36, 36, 36)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel8)
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

        jButton0.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton0.setText("0");
        getContentPane().add(jButton0, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 752, 120, 55));

        jButton15.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 752, 120, 55));

        jButton16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 752, 120, 55));

        jButtonClear.setBackground(new java.awt.Color(255, 255, 0));
        jButtonClear.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jButtonClear.setText("CLEAR");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
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
        jButtonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnterActionPerformed(evt);
            }
        });
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

        jLabelBalanceInquiry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabelBalanceInquiry.setText("jLabel5");
        getContentPane().add(jLabelBalanceInquiry, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 330, 73, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel12.setText("jLabel5");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 250, 73, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel16.setText("jLabel5");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 73, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel14.setText("jLabel5");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 73, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel13.setText("jLabel5");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 73, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        jLabel15.setText("jLabel5");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 73, -1));

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
        //if "YES" label is clicked, call processTransaction method
        processTransaction();
    }//GEN-LAST:event_jLabelYesMouseClicked

    private void jLabelNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNoMouseClicked
        //if "NO" label is click, call cancelTransaction method
        cancelTransaction();
    }//GEN-LAST:event_jLabelNoMouseClicked

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        //if "CLEAR" btn is clicked, clear amount input field
        jTextFieldEnterAmount.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        //if "CANCEL" btn is click, call cancelTransaction method
        cancelTransaction();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnterActionPerformed
        //if "ENTER" btn is clicked, call processTransaction method
        processTransaction();
    }//GEN-LAST:event_jButtonEnterActionPerformed
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
            java.util.logging.Logger.getLogger(EnterAmount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterAmount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterAmount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterAmount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnterAmount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton0;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBalanceInquiry;
    private javax.swing.JLabel jLabelNo;
    private javax.swing.JLabel jLabelYes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextFieldEnterAmount;
    // End of variables declaration//GEN-END:variables
}
