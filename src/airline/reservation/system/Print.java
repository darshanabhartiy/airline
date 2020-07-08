/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline.reservation.system;

import airlineDB.DatabaseConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.print.*;
/**
 *
 * @author Michael
 */
public class Print extends javax.swing.JFrame implements Printable{

    /**
     * Creates new form Print
     */
    JPanel[] pnlTicketId,pnlName,pnlAge,pnlIdCardType,pnlIdCardNo;
    JPanel pnlTicketIdCol,pnlNameCol,pnlAgeCol,pnlIdCardTypeCol,pnlIdCardNoCol;
    JLabel lblTicketIdCol,lblNameCol,lblAgeCol,lblIdCardTypeCol,lblIdCardNoCol;
    JLabel[] lblTicketId,lblName,lblAge,lblIdCardType,lblIdCardNo;
    
    JPopupMenu popupChoice;
    JMenuItem signOut;
    
    String[][] res;
    
    int rows,total;
    
    public Print() {
        initComponents();
        pnlTitle.setBackground(new Color(0,0,0,210));
        
        signOut=new JMenuItem("Sign Out");
        popupChoice=new JPopupMenu();
        popupChoice.add(signOut);
        
        signOut.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               Login.isLoggedIn=false;
               Login.logInFrom=null;
               Login.Cust_Id=0;
               new Search();
               dispose();
           } 
        });
        
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airline","root","");
            String query="select t_Id,c_Name,c_Age,c_IdCardType,c_IdCardNo from Tickets where c_Id=?";
            PreparedStatement pst=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1,Login.Cust_Id);
            ResultSet rs=pst.executeQuery();
            rs.last();
            total=rs.getRow();
        rs.beforeFirst();
        
        res=new String[total][5];
        int i=0;
        
        while(rs.next()){
            int j=0;
                if(i<total){
                    if(j==0){
                        res[i][j]=Integer.toString(rs.getInt(1));
                        j++;
                    }
                    if(j==1){
                        res[i][j]=rs.getString(2);
                        j++;
                    }
                        
                    if(j==2){
                        res[i][j]=Integer.toString(rs.getInt(3));
                        j++;
                    }
                    if(j==3){
                        res[i][j]=rs.getString(4);
                        j++;
                    }
                    if(j==4){
                        res[i][j]=rs.getString(5);
                        j++;
                    }
                }
            i++;
        } 
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,e.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
        }
        
        rows=total;
        
        pnlCustDet.setLayout(new GridLayout(rows+1,5));
        
        initLabelCols();
        initPanelCols();
        initArrays();
        initLabels();
        initPanels();
        
        try{
            String[] flightDet=new DatabaseConnection().getFlightDetails(Login.Cust_Id);
            
            if(flightDet[0]==null){
                JOptionPane.showMessageDialog(this,"No Records Found","Exception",JOptionPane.ERROR_MESSAGE);
                new Search();
                
            }
            else{
                tfFlightId.setText(flightDet[0]);
                tfFlightName.setText(flightDet[1]);
                tfSource.setText(flightDet[2]);
                tfDest.setText(flightDet[3]);
                tfDeptTime.setText(flightDet[4]);
                tfArrTime.setText(flightDet[5]);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,e.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
        }
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<5;j++){
                if(j==0)
                        lblTicketId[i].setText(res[i][j]);
                    else if(j==1)
                        lblName[i].setText(res[i][j]);
                    else if(j==2)
                        lblAge[i].setText(res[i][j]);
                    else if(j==3)
                        lblIdCardType[i].setText(res[i][j]);
                    else if(j==4)
                        lblIdCardNo[i].setText(res[i][j]);
            }
        }
        addLabels();
        addLabelCols();
        
        
        
        
        
       pnlCustDet.add(pnlTicketIdCol);
       pnlCustDet.add(pnlNameCol);
       pnlCustDet.add(pnlAgeCol);
       pnlCustDet.add(pnlIdCardTypeCol);
       pnlCustDet.add(pnlIdCardNoCol);
       
       for(int i=0;i<rows;i++){
           pnlCustDet.add(pnlTicketId[i]);
           pnlCustDet.add(pnlName[i]);
           pnlCustDet.add(pnlAge[i]);
           pnlCustDet.add(pnlIdCardType[i]);
           pnlCustDet.add(pnlIdCardNo[i]);
       }
       
       
       setVisible(true);
    }
    @Override
    public int print(Graphics g, PageFormat pf, int page)
    throws PrinterException {
    if (page > 0) {
        return NO_SUCH_PAGE;
    }
    pf.setOrientation(PageFormat.LANDSCAPE);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());

    // Print the entire visible contents of a
    // java.awt.Frame.
    pnlPrint.printAll(g);

    return PAGE_EXISTS;
}
    
    void initLabelCols(){
        lblTicketIdCol=new JLabel("Ticket Id");
        lblTicketIdCol.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblNameCol=new JLabel("Name");
        lblNameCol.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblAgeCol=new JLabel("Age");
        lblAgeCol.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblIdCardTypeCol=new JLabel("ID Card Type");
        lblIdCardTypeCol.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblIdCardNoCol=new JLabel("ID Card No");
        lblIdCardNoCol.setFont(new Font("Tahoma",Font.PLAIN,14));
    }
    
    void initPanelCols(){
        pnlTicketIdCol=new JPanel();
        pnlTicketIdCol.setBackground(new Color(255,255,255));
        pnlNameCol=new JPanel();
        pnlNameCol.setBackground(new Color(255,255,255));
        pnlAgeCol=new JPanel();
        pnlAgeCol.setBackground(new Color(255,255,255));
        pnlIdCardTypeCol=new JPanel();
        pnlIdCardTypeCol.setBackground(new Color(255,255,255));
        pnlIdCardNoCol=new JPanel();
        pnlIdCardNoCol.setBackground(new Color(255,255,255));
    }

    void initArrays(){
        lblTicketId=new JLabel[rows];
        lblName=new JLabel[rows];
        lblAge=new JLabel[rows];
        lblIdCardType=new JLabel[rows];
        lblIdCardNo=new JLabel[rows];
        
        pnlTicketId=new JPanel[rows];
        pnlName=new JPanel[rows];
        pnlAge=new JPanel[rows];
        pnlIdCardType=new JPanel[rows];
        pnlIdCardNo=new JPanel[rows];
    }
        
    void initLabels(){
        for(int i=0;i<rows;i++){
            lblTicketId[i]=new JLabel();
            lblTicketId[i].setFont(new Font("Tahoma",Font.PLAIN,14));
            lblName[i]=new JLabel();
            lblName[i].setFont(new Font("Tahoma",Font.PLAIN,14));
            lblAge[i]=new JLabel();
            lblAge[i].setFont(new Font("Tahoma",Font.PLAIN,14));
            lblIdCardType[i]=new JLabel();
            lblIdCardType[i].setFont(new Font("Tahoma",Font.PLAIN,14));
            lblIdCardNo[i]=new JLabel();
            lblIdCardNo[i].setFont(new Font("Tahoma",Font.PLAIN,14));
            
        }
        
        
    }
    
    void initPanels(){
        for(int i=0;i<rows;i++){
            pnlTicketId[i]=new JPanel();
            pnlTicketId[i].setBackground(new Color(255,255,255));
            pnlName[i]=new JPanel();
            pnlName[i].setBackground(new Color(255,255,255));
            pnlAge[i]=new JPanel();
            pnlAge[i].setBackground(new Color(255,255,255));
            pnlIdCardType[i]=new JPanel();
            pnlIdCardType[i].setBackground(new Color(255,255,255));
            pnlIdCardNo[i]=new JPanel();
            pnlIdCardNo[i].setBackground(new Color(255,255,255));
        }
    }
    
    void addLabels(){
        for(int i=0;i<rows;i++){
            pnlTicketId[i].add(lblTicketId[i]);
            pnlName[i].add(lblName[i]);
            pnlAge[i].add(lblAge[i]);
            pnlIdCardType[i].add(lblIdCardType[i]);
            pnlIdCardNo[i].add(lblIdCardNo[i]);
        }
    }
    
    void addLabelCols(){
        pnlTicketIdCol.add(lblTicketIdCol);
        pnlNameCol.add(lblNameCol);
        pnlAgeCol.add(lblAgeCol);
        pnlIdCardTypeCol.add(lblIdCardTypeCol);
        pnlIdCardNoCol.add(lblIdCardNoCol);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrintButton = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        pnlPrint = new javax.swing.JPanel();
        pnlDetails = new javax.swing.JPanel();
        pnlFlightDet = new javax.swing.JPanel();
        pnlFlightId = new javax.swing.JPanel();
        lblFlightId = new javax.swing.JLabel();
        tfFlightId = new javax.swing.JTextField();
        pnlFlightName = new javax.swing.JPanel();
        lblFlightName = new javax.swing.JLabel();
        tfFlightName = new javax.swing.JTextField();
        pnlSource = new javax.swing.JPanel();
        lblSource = new javax.swing.JLabel();
        tfSource = new javax.swing.JTextField();
        pnlDest = new javax.swing.JPanel();
        lblDest = new javax.swing.JLabel();
        tfDest = new javax.swing.JTextField();
        pnlDeptTime = new javax.swing.JPanel();
        lblDeptTime = new javax.swing.JLabel();
        tfDeptTime = new javax.swing.JTextField();
        pnlArrTime = new javax.swing.JPanel();
        lblArrTime = new javax.swing.JLabel();
        tfArrTime = new javax.swing.JTextField();
        pnlCustDet = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblChoiceIcon = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPrintButton.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrintButton.setPreferredSize(new java.awt.Dimension(50, 95));
        pnlPrintButton.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 25));

        btnPrint.setBackground(new java.awt.Color(0, 102, 255));
        btnPrint.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("Print");
        btnPrint.setBorder(null);
        btnPrint.setContentAreaFilled(false);
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setOpaque(true);
        btnPrint.setPreferredSize(new java.awt.Dimension(140, 50));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        pnlPrintButton.add(btnPrint);

        getContentPane().add(pnlPrintButton, java.awt.BorderLayout.PAGE_END);

        pnlDetails.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetails.setLayout(new java.awt.GridLayout(2, 1));

        pnlFlightDet.setBackground(new java.awt.Color(255, 255, 255));
        pnlFlightDet.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)), "Flight Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        pnlFlightDet.setLayout(new java.awt.GridLayout(2, 3));

        pnlFlightId.setBackground(new java.awt.Color(255, 255, 255));

        lblFlightId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFlightId.setText("Flight Id:");

        tfFlightId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfFlightId.setBorder(null);

        javax.swing.GroupLayout pnlFlightIdLayout = new javax.swing.GroupLayout(pnlFlightId);
        pnlFlightId.setLayout(pnlFlightIdLayout);
        pnlFlightIdLayout.setHorizontalGroup(
            pnlFlightIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFlightIdLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblFlightId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfFlightId, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pnlFlightIdLayout.setVerticalGroup(
            pnlFlightIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFlightIdLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlFlightIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFlightId)
                    .addComponent(tfFlightId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pnlFlightDet.add(pnlFlightId);

        pnlFlightName.setBackground(new java.awt.Color(255, 255, 255));

        lblFlightName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFlightName.setText("Flight Name:");

        tfFlightName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfFlightName.setBorder(null);

        javax.swing.GroupLayout pnlFlightNameLayout = new javax.swing.GroupLayout(pnlFlightName);
        pnlFlightName.setLayout(pnlFlightNameLayout);
        pnlFlightNameLayout.setHorizontalGroup(
            pnlFlightNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFlightNameLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblFlightName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfFlightName, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFlightNameLayout.setVerticalGroup(
            pnlFlightNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFlightNameLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlFlightNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFlightName)
                    .addComponent(tfFlightName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pnlFlightDet.add(pnlFlightName);

        pnlSource.setBackground(new java.awt.Color(255, 255, 255));

        lblSource.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSource.setText("Source:");

        tfSource.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfSource.setBorder(null);

        javax.swing.GroupLayout pnlSourceLayout = new javax.swing.GroupLayout(pnlSource);
        pnlSource.setLayout(pnlSourceLayout);
        pnlSourceLayout.setHorizontalGroup(
            pnlSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSourceLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblSource)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfSource, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlSourceLayout.setVerticalGroup(
            pnlSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSourceLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSource)
                    .addComponent(tfSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pnlFlightDet.add(pnlSource);

        pnlDest.setBackground(new java.awt.Color(255, 255, 255));

        lblDest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDest.setText("Destination:");

        tfDest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfDest.setBorder(null);

        javax.swing.GroupLayout pnlDestLayout = new javax.swing.GroupLayout(pnlDest);
        pnlDest.setLayout(pnlDestLayout);
        pnlDestLayout.setHorizontalGroup(
            pnlDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfDest, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlDestLayout.setVerticalGroup(
            pnlDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDestLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(pnlDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDest)
                    .addComponent(tfDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pnlFlightDet.add(pnlDest);

        pnlDeptTime.setBackground(new java.awt.Color(255, 255, 255));

        lblDeptTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDeptTime.setText("Dept Time:");

        tfDeptTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfDeptTime.setBorder(null);

        javax.swing.GroupLayout pnlDeptTimeLayout = new javax.swing.GroupLayout(pnlDeptTime);
        pnlDeptTime.setLayout(pnlDeptTimeLayout);
        pnlDeptTimeLayout.setHorizontalGroup(
            pnlDeptTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeptTimeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblDeptTime)
                .addGap(10, 10, 10)
                .addComponent(tfDeptTime, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDeptTimeLayout.setVerticalGroup(
            pnlDeptTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDeptTimeLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(pnlDeptTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeptTime)
                    .addComponent(tfDeptTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pnlFlightDet.add(pnlDeptTime);

        pnlArrTime.setBackground(new java.awt.Color(255, 255, 255));

        lblArrTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblArrTime.setText("Arr Time:");

        tfArrTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfArrTime.setBorder(null);

        javax.swing.GroupLayout pnlArrTimeLayout = new javax.swing.GroupLayout(pnlArrTime);
        pnlArrTime.setLayout(pnlArrTimeLayout);
        pnlArrTimeLayout.setHorizontalGroup(
            pnlArrTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArrTimeLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblArrTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfArrTime, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlArrTimeLayout.setVerticalGroup(
            pnlArrTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlArrTimeLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(pnlArrTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArrTime)
                    .addComponent(tfArrTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pnlFlightDet.add(pnlArrTime);

        pnlDetails.add(pnlFlightDet);

        pnlCustDet.setBackground(new java.awt.Color(255, 255, 255));
        pnlCustDet.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)), "Ticket Details", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N

        javax.swing.GroupLayout pnlCustDetLayout = new javax.swing.GroupLayout(pnlCustDet);
        pnlCustDet.setLayout(pnlCustDetLayout);
        pnlCustDetLayout.setHorizontalGroup(
            pnlCustDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
        );
        pnlCustDetLayout.setVerticalGroup(
            pnlCustDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );

        pnlDetails.add(pnlCustDet);

        pnlTitle.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe Print", 0, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("SkyCab");

        lblChoiceIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/airline/reservation/system/down-arrow-white.png"))); // NOI18N
        lblChoiceIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChoiceIconMouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("[ X ]");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                        .addComponent(lblChoiceIcon)
                        .addGap(53, 53, 53))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTitleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblChoiceIcon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPrintLayout = new javax.swing.GroupLayout(pnlPrint);
        pnlPrint.setLayout(pnlPrintLayout);
        pnlPrintLayout.setHorizontalGroup(
            pnlPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrintLayout.createSequentialGroup()
                .addGroup(pnlPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlPrintLayout.setVerticalGroup(
            pnlPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrintLayout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnlPrint, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            if (job.printDialog() == true) {
                try {job.print();} catch (PrinterException ex){
                }
            }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void lblChoiceIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChoiceIconMouseClicked
        // TODO add your handling code here:
        if(Login.isLoggedIn){
            popupChoice.removeAll();
            popupChoice.add(signOut);
        }
        
        popupChoice.show(lblChoiceIcon, lblChoiceIcon.getWidth()-lblChoiceIcon.getWidth()*3-lblChoiceIcon.getWidth()/2, lblChoiceIcon.getHeight()/2);
        popupChoice.setVisible(true);
    }//GEN-LAST:event_lblChoiceIconMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Print().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblArrTime;
    private javax.swing.JLabel lblChoiceIcon;
    private javax.swing.JLabel lblDeptTime;
    private javax.swing.JLabel lblDest;
    private javax.swing.JLabel lblFlightId;
    private javax.swing.JLabel lblFlightName;
    private javax.swing.JLabel lblSource;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlArrTime;
    private javax.swing.JPanel pnlCustDet;
    private javax.swing.JPanel pnlDeptTime;
    private javax.swing.JPanel pnlDest;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlFlightDet;
    private javax.swing.JPanel pnlFlightId;
    private javax.swing.JPanel pnlFlightName;
    private javax.swing.JPanel pnlPrint;
    private javax.swing.JPanel pnlPrintButton;
    private javax.swing.JPanel pnlSource;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTextField tfArrTime;
    private javax.swing.JTextField tfDeptTime;
    private javax.swing.JTextField tfDest;
    private javax.swing.JTextField tfFlightId;
    private javax.swing.JTextField tfFlightName;
    private javax.swing.JTextField tfSource;
    // End of variables declaration//GEN-END:variables
}
