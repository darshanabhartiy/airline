package airline.reservation.system;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EtchedBorder;
import java.util.Vector;
import javax.swing.text.*;

public class Booking extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form Booking
     */
    
    int rows;
    //JLabel lblNo,lblName,lblGender,lblIdCardType,lblIcardNo,lblFood,lblSenior;
    JTextField[] tfName;
    JTextField[] tfAge;
    JComboBox[] cbGender;
    JComboBox[] cbIdCardType;
    JTextField[] tfIdCardNo;
    JComboBox[] cbFood;
    JCheckBox[] chkSenior;
    JPanel pnlNo[], pnlName[],pnlAge[],pnlGender[],pnlIdCardType[],pnlIdCardNo[],pnlFood[],pnlSenior[];
    JTable tblDetails;
    Vector<String> vGender,vIdCardType,vFood;
    JScrollPane sp;
    Component comp;
    JLabel lblNo[];
    JButton btnPnp;
    JPanel[] pnlColNames;
    JLabel[] lblColNames;
    
    static String name[],gender[],IdCardType[],Food[],Senior[],IdCardNo[];
    static int age[];
    
    boolean checkName=false,checkAge=false,checkIdCardNo=false;
    

    public Booking(int rows) {
        this.rows=rows;
        initComponents();
        pnlTitle.setBackground(new Color(0,0,0,210));
        
        initArray(); // to initialize size to arrays
        cbInitValues(); // to fill the comboboxes
        initComp(); // to initialize individual components
        addComp(); // to add individual components to panels
        pnlSetBackground(); // to set background to panels
        pnlResize(); // to resize the panels
        initColumnNames(); // to initialize labels and panels
        setColumnNames(); // to initialize column names to labels
        addColumnNames(); // to add labels to panels
        dispPanels(); // to display all panels on the main panel
        initInputArr(); // to initialize size to input array variables
        
        
        /*btnSubmit=new JButton("Proceed to Pay");
        btnSubmit.setBorder(BorderFactory.createLineBorder(Color.black));
        btnSubmit.setBackground(new Color(255,255,255));
        btnSubmit.setForeground(new Color(0,0,0));
        btnSubmit.setFont(new Font("Arial", Font.PLAIN, 25));
        pnlSubmit.add(btnSubmit);*/
        pack();
        setLocationRelativeTo(null);
        //setResizable(false);
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
    
    void initArray(){
        lblNo=new JLabel[rows];
        tfName=new JTextField[rows];
        tfAge=new JTextField[rows];
        cbGender=new JComboBox[rows];
        cbIdCardType=new JComboBox[rows];
        tfIdCardNo=new JTextField[rows];
        cbFood=new JComboBox[rows];
        chkSenior=new JCheckBox[rows];
        
        pnlNo=new JPanel[rows];
        pnlName=new JPanel[rows];
        pnlAge=new JPanel[rows];
        pnlGender=new JPanel[rows];
        pnlIdCardType=new JPanel[rows];
        pnlIdCardNo=new JPanel[rows];
        pnlFood=new JPanel[rows];
        pnlSenior=new JPanel[rows];
        lblColNames=new JLabel[8];
        pnlColNames=new JPanel[8];        
    }
    
    void cbInitValues(){
        vGender=new Vector<>();
        vGender.add("Male");
        vGender.add("Female");
        vGender.add("Others");
        
        vIdCardType=new Vector<>();
        vIdCardType.add("Aadhar Card");
        vIdCardType.add("Pan Card");
        vIdCardType.add("Driving Lisence");
        
        vFood=new Vector<>();
        vFood.add("Veg");
        vFood.add("Non-Veg");
    }
    
    void initComp(){
        for(int i=0;i<rows;i++){
            lblNo[i]=new JLabel(Integer.toString(i+1));
            tfName[i]=new JTextField(10);
            ((AbstractDocument)tfName[i].getDocument()).setDocumentFilter(new CharDocumentFilter());
            tfAge[i]=new JTextField(5);
            ((AbstractDocument)tfAge[i].getDocument()).setDocumentFilter(new NumberDocumentFilter());
            cbGender[i]=new JComboBox(vGender);
            cbIdCardType[i]=new JComboBox(vIdCardType);
            tfIdCardNo[i]=new JTextField(10);
            //((AbstractDocument)tfIdCardNo[i].getDocument()).setDocumentFilter(new NumberDocumentFilter());
            cbFood[i]=new JComboBox(vFood);
            chkSenior[i]=new JCheckBox();
            
            pnlNo[i]=new JPanel(new FlowLayout(FlowLayout.CENTER));
            pnlName[i]=new JPanel();           
            pnlAge[i]=new JPanel();           
            pnlGender[i]=new JPanel();            
            pnlIdCardType[i]=new JPanel();            
            pnlIdCardNo[i]=new JPanel();            
            pnlFood[i]=new JPanel();            
            pnlSenior[i]=new JPanel(); 
            pnlSenior[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            
            
        }
    }
    
    void addComp(){
        for(int i=0;i<rows;i++){
            pnlNo[i].add(lblNo[i]);
            pnlName[i].add(tfName[i]);
            pnlAge[i].add(tfAge[i]);
            pnlGender[i].add(cbGender[i]);
            pnlIdCardType[i].add(cbIdCardType[i]);
            pnlIdCardNo[i].add(tfIdCardNo[i]);
            pnlFood[i].add(cbFood[i]);
            pnlSenior[i].add(chkSenior[i]);
        }
    }
    
    void pnlSetBackground(){
        for(int i=0;i<rows;i++){
            pnlNo[i].setBackground(Color.white);
            pnlName[i].setBackground(Color.white);
            pnlAge[i].setBackground(Color.white);
            pnlGender[i].setBackground(Color.white);
            pnlIdCardType[i].setBackground(Color.white);
            pnlIdCardNo[i].setBackground(Color.white);
            pnlFood[i].setBackground(Color.white);
            pnlSenior[i].setBackground(Color.white);
        }
    }
    
    void pnlResize(){
        for(int i=0;i<rows;i++){
            pnlNo[i].revalidate();
            pnlName[i].revalidate();
            pnlAge[i].revalidate();
            pnlGender[i].revalidate();
            pnlIdCardType[i].revalidate();
            pnlIdCardNo[i].revalidate();
            pnlFood[i].revalidate();
            pnlSenior[i].revalidate();
        }
    }
    
    void initColumnNames(){
        for(int i=0;i<8;i++){
            lblColNames[i]=new JLabel();
            pnlColNames[i]=new JPanel(new FlowLayout(FlowLayout.CENTER));
        }
    }
    
    void setColumnNames(){
        lblColNames[0].setText("Sr.No");
        lblColNames[1].setText("Name");
        lblColNames[2].setText("Age");
        lblColNames[3].setText("Gender");
        lblColNames[4].setText("Id Card Type");
        lblColNames[5].setText("Id Card No");
        lblColNames[6].setText("Food");
        lblColNames[7].setText("Senior");
    }
    
    void addColumnNames(){
        for(int i=0;i<lblColNames.length;i++){
            pnlColNames[i].setBackground(Color.white);
            pnlColNames[i].add(lblColNames[i]);
        }
    }
    
    void dispPanels(){
        for(int i=0;i<8;i++){
            pnlDetails.add(pnlColNames[i]);
        }
        
        for(int i=0;i<rows;i++){
            pnlDetails.add(pnlNo[i]);
            pnlDetails.add(pnlName[i]);
            pnlDetails.add(pnlAge[i]);
            pnlDetails.add(pnlGender[i]);
            pnlDetails.add(pnlIdCardType[i]);
            pnlDetails.add(pnlIdCardNo[i]);
            pnlDetails.add(pnlFood[i]);
            pnlDetails.add(pnlSenior[i]);
            //pack();
        }
    }
    
    void initInputArr(){
        name=new String[rows];
        age=new int[rows];
        gender=new String[rows];
        IdCardType=new String[rows];
        IdCardNo=new String[rows];
        Food=new String[rows];
        Senior=new String[rows];
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnlSubmit = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        pnlDetails = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlTitle.setPreferredSize(new java.awt.Dimension(452, 104));

        lblTitle.setFont(new java.awt.Font("Segoe Print", 0, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("SkyCab");

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
                .addGap(5, 5, 5)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTitleLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblTitle))
                    .addComponent(jButton1))
                .addGap(9, 9, 9))
        );

        getContentPane().add(pnlTitle, java.awt.BorderLayout.PAGE_START);

        pnlSubmit.setBackground(new java.awt.Color(255, 255, 255));
        pnlSubmit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlSubmit.setPreferredSize(new java.awt.Dimension(452, 104));
        pnlSubmit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        btnSubmit.setBackground(new java.awt.Color(0, 102, 255));
        btnSubmit.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Proceed to Pay");
        btnSubmit.setBorder(null);
        btnSubmit.setContentAreaFilled(false);
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.setOpaque(true);
        btnSubmit.setPreferredSize(new java.awt.Dimension(170, 50));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        pnlSubmit.add(btnSubmit);

        getContentPane().add(pnlSubmit, java.awt.BorderLayout.PAGE_END);

        pnlDetails.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Passenger Details", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 51, 255))); // NOI18N
        pnlDetails.setLayout(new java.awt.GridLayout(rows+1, 8, 5, 15));
        getContentPane().add(pnlDetails, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        checkName=isNameValid();
        checkAge=isAgeValid();
        checkIdCardNo=isNoValid();
        
        if(!(checkName || checkAge || checkIdCardNo)){
            for(int i=0;i<rows;i++){
                name[i]=tfName[i].getText();
                age[i]=Integer.parseInt(tfAge[i].getText());
                gender[i]=cbGender[i].getSelectedItem().toString();
                IdCardType[i]=cbIdCardType[i].getSelectedItem().toString();
                IdCardNo[i]=tfIdCardNo[i].getText();
                Food[i]=cbFood[i].getSelectedItem().toString();
                
                if(chkSenior[i].getModel().isSelected()){
                    Senior[i]="Yes";
                }
                else{
                    Senior[i]="No";
                }    
            }
            /*for(int i=0;i<rows;i++){
                System.out.println(name[i]+"\t"+age[i]+"\t"+gender[i]+"\t"+IdCardType[i]+"\t"+IdCardNo[i]+"\t"+Food[i]+"\t"+Senior[i]);
            }*/
            new Payment();
            dispose();
            
        }
        else{
            JOptionPane.showMessageDialog(this,"Please Fill all the Details","Booking",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    boolean isNameValid(){
        boolean val[]=new boolean[rows];
        boolean res=false;
        for(int i=0;i<rows;i++){
            if(tfName[i].getText().equals("")){
                tfName[i].setBackground(new Color(255,255,204));
                tfName[i].setBorder(BorderFactory.createLineBorder(Color.red,1,false));
                val[i]=true;
            }
            else{
                char[] names=tfName[i].getText().toCharArray();
                for(char n:names){
                    int asc=n;
                    if((asc>=65 && asc<=90) || (asc>=97 && asc<=122)){
                        tfName[i].setBackground(Color.white);
                        tfName[i].setBorder(BorderFactory.createLineBorder(new Color(153,153,153),1,false));
                        val[i]= false;
                    }
                    else{
                        val[i]= true;
                        tfName[i].setBackground(new Color(255,255,204));
                        tfName[i].setBorder(BorderFactory.createLineBorder(Color.red,1,false));
                        break;
                    }
                
                }
            }
        }
        for(int i=0;i<rows;i++){
            if(val[i]){
                res=true;
                break;
            }
            else{
                res=false;
            }
        }
        return res;
    }
    
    boolean isAgeValid(){
        boolean val[]=new boolean[rows];
        boolean res=false;
        for(int i=0;i<rows;i++){
            if(tfAge[i].getText().equals("")){
                tfAge[i].setBackground(new Color(255,255,204));
                tfAge[i].setBorder(BorderFactory.createLineBorder(Color.red,1,false));
                val[i]=true;
            }
            else{
                char[] age=tfAge[i].getText().toCharArray();
                for(char n:age){
                    int asc=n;
                    if(asc>=48 && asc<=57){
                        tfAge[i].setBackground(Color.white);
                        tfAge[i].setBorder(BorderFactory.createLineBorder(new Color(153,153,153),1,false));
                        val[i]=false;
                    }
                    else{
                        val[i]=true;
                        tfAge[i].setBackground(new Color(255,255,204));
                        tfAge[i].setBorder(BorderFactory.createLineBorder(Color.red,1,false));
                        break;
                    }
                }
            }
        }
        for(int i=0;i<rows;i++){
            if(val[i]){
                res=true;
                break;
            }
            else{
                res=false;
            }
        }
        return res;
    }
    
     boolean isNoValid(){
        boolean val[]=new boolean[rows];
        boolean res=false;
        for(int i=0;i<rows;i++){
            if(tfIdCardNo[i].getText().equals("")){
                val[i]=true;
                tfIdCardNo[i].setBackground(new Color(255,255,204));
                tfIdCardNo[i].setBorder(BorderFactory.createLineBorder(Color.red,1,false));
            }
            else{
                val[i]=false;
                tfIdCardNo[i].setBackground(new Color(255,255,255));
                tfIdCardNo[i].setBorder(BorderFactory.createLineBorder(new Color(153,153,153),1,false));
            }
        }
        for(int i=0;i<rows;i++){
            if(val[i]){
                res=true;
                break;
            }
            else{
                res=false;
            }
        }
        return res;
    }
    /**
     * @param args the command line arguments
     */
    
     public class NumberDocumentFilter extends DocumentFilter{
        public void insert(DocumentFilter.FilterBypass fp,int off,String string,AttributeSet attr) throws BadLocationException{
            int len=string.length();
            boolean isValidNum=true;
            
            for(int i=0;i<len;i++){
                if(!(Character.isDigit(string.charAt(i)))){
                    isValidNum=false;
                    break;
                }    
            }
            if(isValidNum){
                super.insertString(fp, off, string, attr);
                //fp.insertString(off,string.replaceAll("\\d",""),as);
            }
            else{
                Toolkit.getDefaultToolkit().beep();
            }  
        }
        
        public void replace(DocumentFilter.FilterBypass fp,int off,int length,String string,AttributeSet attr) throws BadLocationException{
            int len=string.length();
            boolean isValidNum=true;
            
            for(int i=0;i<len;i++){
                if(!(Character.isDigit(string.charAt(i)))){
                    isValidNum=false;
                    break;
                }
            }
            if(isValidNum){
                super.insertString(fp, off, string, attr);
                //fp.insertString(off,string.replaceAll("\\d",""),as);
            }
            else{
                Toolkit.getDefaultToolkit().beep();
            }  
        }
        
    }
    
    public class CharDocumentFilter extends DocumentFilter{
        public void insert(DocumentFilter.FilterBypass fp,int off,String string,AttributeSet attr) throws BadLocationException{
            int len=string.length();
            boolean isValidChar=true;
            
            for(int i=0;i<len;i++){
                if(!( Character.isWhitespace(string.charAt(i)) || ((int)string.charAt(i))==39 || Character.isLetter(string.charAt(i)))){
                    isValidChar=false;
                    break;
                }    
            }
            if(isValidChar){
                super.insertString(fp, off, string, attr);
                //fp.insertString(off,string.replaceAll("\\d",""),as);
            }
            else{
                Toolkit.getDefaultToolkit().beep();
            }  
        }
        
        public void replace(DocumentFilter.FilterBypass fp,int off,int length,String string,AttributeSet attr) throws BadLocationException{
            int len=string.length();
            boolean isValidChar=true;
            
            for(int i=0;i<len;i++){
                if(!(Character.isWhitespace(string.charAt(i)) || ((int)string.charAt(i))==39 || Character.isLetter(string.charAt(i)))){
                    isValidChar=false;
                    break;
                }
            }
            if(isValidChar){
                super.insertString(fp, off, string, attr);
                //fp.insertString(off,string.replaceAll("\\d",""),as);
            }
            else{
                Toolkit.getDefaultToolkit().beep();
            }  
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlSubmit;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables

   
}
