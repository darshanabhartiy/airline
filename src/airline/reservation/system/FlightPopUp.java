/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline.reservation.system;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import airlineDB.DatabaseConnection;

/**
 *
 * @author Michael
 */
public class FlightPopUp extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form FlightPopUp
     */
    Search s;
    static int amt;
    static String fid;
    public FlightPopUp(String f_id,Search s){
        this.s=s;
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(255, 255, 255)));
        initComponents();
        changeProperty();
        pnlTitle.setBackground(new Color(0,0,0,210));
        setLocationRelativeTo(null);
        
        //btnCancel.setBackground(new Color(0,0,0,190));
        //tfFlightID.setBackground(Color.white);
        /*tfFlightID.setText(f_id);
        tfFlightName.setText("My Flight");
        tfSource.setText("Mumbai");
        tfDestination.setText("Mangalore");
        tfDeptTime.setText("4:30");
        tfArrTime.setText("14:30");
        tfSeats.setText("30");
        tfAmt.setText("3000Rs");*/
        try{
            String[] det=new DatabaseConnection().retreiveDetails(f_id);
            
            amt=Integer.parseInt(det[7])*Search.sum;
            
            tfFlightID.setText(det[0]);
            tfFlightName.setText(det[1]);
            tfSource.setText(det[2]);
            tfDestination.setText(det[3]);
            tfDeptTime.setText(det[4]);
            tfArrTime.setText(det[5]);
            tfSeats.setText(det[6]);
            tfAmt.setText(Integer.toString(amt)+" \u20B9"); 
            
            fid=det[0];
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,e.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
        }
        setOpacity(0.0f);
        Thread t1=new Thread(this);
        t1.start();
        setVisible(true);
    }
    
    public void run(){
        try{
            for(float i=0.0f;i<=0.92f;i+=0.01){
                setOpacity(i);
                Thread.sleep(5);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void changeProperty(){
        tfFlightID.setEditable(false);
        tfFlightName.setEditable(false);
        tfSource.setEditable(false);
        tfDestination.setEditable(false);
        tfDeptTime.setEditable(false);
        tfArrTime.setEditable(false);
        tfSeats.setEditable(false);
        tfAmt.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        lblFlightID = new javax.swing.JLabel();
        lblFlightName = new javax.swing.JLabel();
        tfFlightID = new javax.swing.JTextField();
        tfFlightName = new javax.swing.JTextField();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        lblSource = new javax.swing.JLabel();
        tfSource = new javax.swing.JTextField();
        lblDestination = new javax.swing.JLabel();
        tfDestination = new javax.swing.JTextField();
        lblDeptTime = new javax.swing.JLabel();
        tfDeptTime = new javax.swing.JTextField();
        lblArrTime = new javax.swing.JLabel();
        tfArrTime = new javax.swing.JTextField();
        lblSeats = new javax.swing.JLabel();
        tfSeats = new javax.swing.JTextField();
        lblAmt = new javax.swing.JLabel();
        tfAmt = new javax.swing.JTextField();
        btnBook = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));

        lblFlightID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFlightID.setText("Flight ID:");

        lblFlightName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFlightName.setText("Flight Name:");

        tfFlightID.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfFlightID.setForeground(new java.awt.Color(102, 102, 102));
        tfFlightID.setBorder(null);
        tfFlightID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFlightIDActionPerformed(evt);
            }
        });

        tfFlightName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfFlightName.setForeground(new java.awt.Color(102, 102, 102));
        tfFlightName.setBorder(null);
        tfFlightName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFlightNameActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe Print", 0, 45)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("SkyCab");

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("[ X ]");
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addComponent(btnCancel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSource.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSource.setText("Source:");

        tfSource.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfSource.setForeground(new java.awt.Color(102, 102, 102));
        tfSource.setBorder(null);

        lblDestination.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDestination.setText("Destination:");

        tfDestination.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfDestination.setForeground(new java.awt.Color(102, 102, 102));
        tfDestination.setBorder(null);

        lblDeptTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDeptTime.setText("Dept Time:");

        tfDeptTime.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfDeptTime.setForeground(new java.awt.Color(102, 102, 102));
        tfDeptTime.setBorder(null);

        lblArrTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblArrTime.setText("Arr Time:");

        tfArrTime.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfArrTime.setForeground(new java.awt.Color(102, 102, 102));
        tfArrTime.setBorder(null);

        lblSeats.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSeats.setText("<html>\n<pre>\n<font face=\"Tahoma\" size=\"4\">\nSeats\nAvailable:\n</font>\n</pre>\n</html>");

        tfSeats.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfSeats.setForeground(new java.awt.Color(102, 102, 102));
        tfSeats.setBorder(null);

        lblAmt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAmt.setText("Payable Amt:");

        tfAmt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tfAmt.setForeground(new java.awt.Color(102, 102, 102));
        tfAmt.setBorder(null);

        btnBook.setBackground(new java.awt.Color(0, 102, 255));
        btnBook.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnBook.setForeground(new java.awt.Color(255, 255, 255));
        btnBook.setText("Book Now");
        btnBook.setBorder(null);
        btnBook.setContentAreaFilled(false);
        btnBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBook.setOpaque(true);
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfFlightID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSource, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDeptTime, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSource)
                            .addComponent(lblDeptTime)
                            .addComponent(lblFlightID))
                        .addGap(195, 195, 195)))
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblDestination)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblFlightName)
                                .addGap(23, 23, 23))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblArrTime)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfDestination, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                .addComponent(tfFlightName))
                            .addComponent(tfArrTime, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(lblAmt)
                        .addGap(18, 18, 18)
                        .addComponent(tfAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFlightID)
                    .addComponent(tfFlightID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFlightName)
                    .addComponent(tfFlightName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSource)
                    .addComponent(tfSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDestination)
                    .addComponent(tfDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeptTime)
                    .addComponent(tfDeptTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArrTime)
                    .addComponent(tfArrTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAmt)
                            .addComponent(tfAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfFlightIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFlightIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFlightIDActionPerformed

    private void tfFlightNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFlightNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFlightNameActionPerformed

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        // TODO add your handling code here:
        if(Login.isLoggedIn){
            new Booking(Search.sum);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"We detected that you are not logged In.Please Log In to Continue","Booking",JOptionPane.INFORMATION_MESSAGE);
            Login.logInFrom="book";
            new Login();
            setVisible(false);
        }
    }//GEN-LAST:event_btnBookActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
        s.setVisible(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel lblAmt;
    private javax.swing.JLabel lblArrTime;
    private javax.swing.JLabel lblDeptTime;
    private javax.swing.JLabel lblDestination;
    private javax.swing.JLabel lblFlightID;
    private javax.swing.JLabel lblFlightName;
    private javax.swing.JLabel lblSeats;
    private javax.swing.JLabel lblSource;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTextField tfAmt;
    private javax.swing.JTextField tfArrTime;
    private javax.swing.JTextField tfDeptTime;
    private javax.swing.JTextField tfDestination;
    private javax.swing.JTextField tfFlightID;
    private javax.swing.JTextField tfFlightName;
    private javax.swing.JTextField tfSeats;
    private javax.swing.JTextField tfSource;
    // End of variables declaration//GEN-END:variables
}
