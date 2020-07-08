/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlineDB;
import airline.reservation.system.Login;
import airline.reservation.system.Search;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michael
 */
public class DatabaseConnection {
    public static int total;
    Connection con;
    Statement stmt;
    ResultSet rs;
    PreparedStatement pst,pst_seq;
    //CallableStatement cst;
    public DatabaseConnection() throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");  
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airline","root","");
    }
    
    public boolean logIn(String userName,String password) throws SQLException{
        boolean isDetailsValid=false;
        String logInQuery="Select c_Id from Customer where userName=? and pswd=?";
        pst=con.prepareStatement(logInQuery);
        pst.setString(1,userName);
        pst.setString(2,password);
        //pst.registerOutParameter(3, java.sql.Types.INTEGER);
        
        rs=pst.executeQuery();
        
        if(rs.next()){
            Login.Cust_Id=rs.getInt(1);
            isDetailsValid=true;
        }   
        return isDetailsValid;
    }
    
    public boolean register(String fName,String lName,String address,String email,String altEmail,long contact,String userName,String pswd) throws SQLException{
       boolean isRegistered=false;
       
       if(altEmail.equals("Alternate Email (Optional)")){
           altEmail=null;
       }
       
       String registerQuery = "insert into Customer(c_FName,c_LName,c_Address,c_Email,c_AltEmail,c_ContactNo,userName,pswd) values(?,?,?,?,?,?,?,?)";
       //insert INTo Customer(c_Id,c_FName,c_LName,c_Address,c_Email,c_AltEmail,c_ContactNo,userName,pswd) values(?,?,?,?,?,?,?,?);
       pst=con.prepareStatement(registerQuery);
       
       
       pst.setString(1,fName);
       pst.setString(2,lName);
       pst.setString(3, address);
       pst.setString(4,email);
       pst.setString(5,altEmail);
       pst.setLong(6, contact);
       pst.setString(7,userName);
       pst.setString(8, pswd);
       //cst.registerOutParameter(9, java.sql.Types.BOOLEAN);
       
       pst.executeUpdate();
       isRegistered=true;
       
       con.close();
       return isRegistered;
    }
    
    public boolean checkUsername(String userName) throws SQLException{
        boolean isUserNameValid=false;
        
        String name="select userName from Customer";
        stmt=con.createStatement();
        rs=stmt.executeQuery(name);
        
        if(!rs.isBeforeFirst()){
            isUserNameValid=true;
        }
        else{
            while(rs.next()){
                if(!(rs.getString(1).equals(userName))){
                    isUserNameValid=true;
                }
                else{
                    isUserNameValid=false;
                    break;
                }
            }  
        }
        
        //con.close();
        return isUserNameValid;
    }
    
    public DefaultComboBoxModel<String> populateSource() throws SQLException{
        DefaultComboBoxModel<String> dcbmSource=new DefaultComboBoxModel<>();
       
        String source="select distinct f_source from Flights";
        stmt=con.createStatement();
        rs=stmt.executeQuery(source);
        
        while(rs.next()){
        dcbmSource.addElement(rs.getString(1));
        }
        
        return dcbmSource;
    } 
    
    public DefaultComboBoxModel<String> populateDest() throws SQLException{
        DefaultComboBoxModel<String> dcbmDest=new DefaultComboBoxModel<>();
       
        String source="select distinct f_dest from Flights";
        stmt=con.createStatement();
        rs=stmt.executeQuery(source);
        
        while(rs.next()){
        dcbmDest.addElement(rs.getString(1));
        }
        
        return dcbmDest;
    }
    
    public DefaultTableModel populateTable(String source,String dest) throws SQLException{
       Vector<String> cols=new Vector<>();
       cols.add("Flight ID");
       cols.add("Flight Name");
       cols.add("Source");
       cols.add("Destination");
       cols.add("Fare");
       
       Vector<Vector<String>> rows=new Vector<>();
        DefaultTableModel dtmSearch;
        
        String retreive="select f_Id,f_Name,f_source,f_dest,fare from Flights where f_source=? and f_dest=?";
        
        pst=con.prepareStatement(retreive);
        pst.setString(1,source);
        pst.setString(2,dest);
        
        rs=pst.executeQuery();
        
        if(!rs.isBeforeFirst()){
            dtmSearch=null;
        }
        else{
            while(rs.next()){
                Vector<String> row=new Vector<>();
                row.add(rs.getString(1)); // Flight ID
                row.add(rs.getString(2)); // Flight Name
                row.add(rs.getString(3)); // Source
                row.add(rs.getString(4)); // Destination
                row.add(Integer.toString(rs.getInt(5))+" \u20B9"); // Amount
                rows.add(row);
            }
            dtmSearch=new DefaultTableModel(rows,cols);
        }
        return dtmSearch;
    }
    
    public boolean bookTicketandPayment(String[] name,int[] age,String[] gender,String[] idCardType,String[] idCardNo, String[] food, String[] senior, Date doj, Date dor,String classOfJourney,int cid,String fid, long cardNo, String cardName,int cvvNo,int issueNo,int amt) throws SQLException{
        boolean isSuccessful=false;
        String sqlIdentifier = "select Tick.NEXTVAL from dual";
        long myId=0;
        
        
        String book="insert into Tickets(c_Name,c_Age,c_Gender,c_IdCardType,c_IdCardNo,c_Food,c_Senior,doj,dor,classOfJourney,c_Id,f_Id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(book);
        for(int i=0;i<name.length;i++){
            pst.setString(1,name[i]);
            pst.setInt(2,age[i]);
            pst.setString(3,gender[i]);
            pst.setString(4,idCardType[i]);
            pst.setString(5,idCardNo[i]);
            pst.setString(6,food[i]);
            pst.setString(7,senior[i]);
            pst.setDate(8,doj);
            pst.setDate(9,dor);
            pst.setString(10,classOfJourney);
            pst.setInt(11,cid);
            pst.setString(12,fid);
            pst.executeUpdate();
        }
        pst.close();
        
        
        String pay="insert into Payment(c_id,cardNo,cardName,cvvNo,issueNo,amt) values(?,?,?,?,?,?)";
        
        if(issueNo==0){
            pst=con.prepareStatement(pay);
            pst.setInt(1,cid);
            pst.setLong(2,cardNo);
            pst.setString(3,cardName);
            pst.setInt(4,cvvNo);
            pst.setNull(5,java.sql.Types.INTEGER);
            pst.setInt(6,amt);
            pst.executeUpdate();
        }
        else{
            pst=con.prepareStatement(pay);
            pst.setInt(1,cid);
            pst.setLong(2,cardNo);
            pst.setString(3,cardName);
            pst.setInt(4,cvvNo);
            pst.setInt(5,issueNo);
            pst.setInt(6,amt);
            pst.executeUpdate();
        }
        
        pst.close();
        
        String getSeats="select seats_avail from Flights where f_Id=?";
        String updateSeats="update Flights set seats_avail=? where f_Id=?";
        int seats=0;
        
        pst=con.prepareStatement(getSeats);
        pst.setString(1,Search.f_id);
        rs=pst.executeQuery();
        
        if(rs.next()){
            seats=rs.getInt(1);
            seats-=Search.sum;
        }
        pst.close();
        
        pst=con.prepareStatement(updateSeats);
        pst.setInt(1,seats);
        pst.setString(2,Search.f_id);
        pst.executeUpdate();
        
        isSuccessful=true;
        
        return isSuccessful;
    }
    
    public String[] retreiveDetails(String fid) throws SQLException{
        String[] details=new String[8];
        
        String query="select f_Id,f_Name,f_source,f_dest,dept_time,arr_time,seats_avail,fare from Flights where f_id=?";
        pst=con.prepareStatement(query);
        pst.setString(1,fid);
        rs=pst.executeQuery();
        
        if(rs.next()){
            details[0]=rs.getString(1);
            details[1]=rs.getString(2);
            details[2]=rs.getString(3);
            details[3]=rs.getString(4);
            details[4]=rs.getString(5);
            details[5]=rs.getString(6);
            details[6]=Integer.toString(rs.getInt(7));
            details[7]=Integer.toString(rs.getInt(8)); 
        }
        return details;
    }
    
    public String[] getFlightDetails(int cid) throws SQLException{
        String[] det=new String[6];
        String query="select f_Id,f_Name,f_source,f_dest,dept_time,arr_time from Flights where f_id IN(select distinct f_Id from Tickets where c_Id=?)";
        pst=con.prepareStatement(query);
        pst.setInt(1,cid);
        rs=pst.executeQuery();
        
        
        if(rs.next()){
           det[0]=rs.getString(1);
           det[1]=rs.getString(2);
           det[2]=rs.getString(3);
           det[3]=rs.getString(4);
           det[4]=rs.getString(5);
           det[5]=rs.getString(6);
        }
        else{
            det[0]=null;
        }
        
        return det;
    }
}
    
    
    
    
    

