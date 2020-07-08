create table Customer
(
c_Id INT AUTO_INCREMENT,
c_FName VARCHAR(20) NOT NULL,
c_LName VARCHAR(20) NOT NULL,
c_Address VARCHAR(50)NOT NULL,
c_Email VARCHAR(20) NOT NULL,
c_AltEmail VARCHAR(20),
c_ContactNo NUMERIC(10,0) NOT NULL,
userName VARCHAR(20) UNIQUE NOT NULL,
pswd VARCHAR(20) NOT NULL,
PRIMARY KEY( c_Id )
);

create table Flights
(
f_Id VARCHAR(20),
f_Name VARCHAR(20) NOT NULL,
f_source VARCHAR(20) NOT NULL,
f_dest VARCHAR(20) NOT NULL,
arr_time VARCHAR(20) NOT NULL,
dept_time VARCHAR(20) NOT NULL,
seats_avail INT,
fare INT,
PRIMARY KEY( f_Id )
);

create table Tickets
(
t_Id INT AUTO_INCREMENT,
c_Name VARCHAR(20) NOT NULL,
c_Age INT NOT NULL,
c_Gender VARCHAR(20) NOT NULL,
c_IdCardType VARCHAR(20) NOT NULL,
c_IdCardNo VARCHAR(20) NOT NULL,
c_Food VARCHAR(20) NOT NULL,
c_Senior VARCHAR(20) NOT NULL,
doj Date NOT NULL,
dor Date,
classOfJourney VARCHAR(20),
PRIMARY KEY( t_Id ),
c_Id INT,
f_Id VARCHAR(20),
FOREIGN KEY (c_Id) REFERENCES Customer(c_Id),
FOREIGN KEY (f_Id) REFERENCES Flights(f_Id)
);

create table Payment
(
c_id INT NOT NULL,
cardNo NUMERIC(12,0) NOT NULL,
cardName VARCHAR(20) NOT NULL,
cvvNo INT NOT NULL,
issueNo INT,
amt INT,
FOREIGN KEY(c_id)REFERENCES Customer(c_Id)
);

/*create or replace procedure insertVal(fName in VARCHAR,lName in VARCHAR,address in VARCHAR,email in VARCHAR,altEmail in VARCHAR,contact in INT,userName in VARCHAR,pswd in VARCHAR)
is
begin
  insert INTo Customer(c_Id,c_FName,c_LName,c_Address,c_Email,c_AltEmail,c_ContactNo,userName,pswd) values(Cust_Ticket.nextVal,fName,lName,address,email,altEmail,contact,userName,pswd);
end;*/

insert INTO Flights values('F01','Fighter Jet','Mumbai','Goa','04:30','01:30',30,3000);
insert INTO Flights values('F02','Walker Jet','Mumbai','Delhi','17:30','14:30',30,5000);
insert INTO Flights values('F03','Running Jet','Mumbai','Chandigarh','12:30','08:30',30,2500);
insert INTO Flights values('F04','Flying Jet','Mumbai','Chennai','20:30','14:30',30,4500);
insert INTO Flights values('F05','Swimmer Jet','Mumbai','Mangalore','16:00','13:00',30,6300);
          
insert INTO Flights values('F06','Flight 6','Mumbai','Goa','07:30','04:30',30,1500);
insert INTO Flights values('F07','Flight 7','Mumbai','Delhi','20:30','17:30',30,1200);
insert INTO Flights values('F08','Flight 8','Mumbai','Chandigarh','15:30','11:30',30,1600);
insert INTO Flights values('F09','Flight 9','Mumbai','Chennai','23:30','17:30',30,3100);
insert INTO Flights values('F10','Flight 10','Mumbai','Mangalore','19:00','16:00',30,1500);
          
insert INTO Flights values('F11','Flight 11','Goa','Chennai','08:30','05:30',30,3000);
insert INTO Flights values('F12','Flight 12','Goa','Chandigarh','21:30','18:30',30,5000);
insert INTO Flights values('F13','Flight 13','Goa','Mumbai','16:30','12:30',30,9000);
insert INTO Flights values('F14','Flight 14','Goa','Delhi','00:30','18:30',30,2500);
insert INTO Flights values('F15','Flight 15','Goa','Mangalore','20:00','17:00',30,4100);
          
insert INTO Flights values('F16','Flight 16','Goa','Chennai','09:30','06:30',30,5600);
insert INTO Flights values('F17','Flight 17','Goa','Chandigarh','22:30','19:30',30,6500);
insert INTO Flights values('F18','Flight 18','Goa','Mumbai','17:30','13:30',30,4500);
insert INTO Flights values('F19','Flight 19','Goa','Delhi','01:30','19:30',30,5500);
insert INTO Flights values('F20','Flight 20','Goa','Mangalore','21:00','18:00',30,4590);
          
insert INTO Flights values('F21','Flight 21','Mumbai','Goa','01:30','22:30',30,1250);
insert INTO Flights values('F22','Flight 22','Mumbai','Delhi','14:30','11:30',30,1200);
insert INTO Flights values('F23','Flight 23','Mumbai','Chandigarh','09:30','05:30',30,10000);
insert INTO Flights values('F24','Flight 24','Mumbai','Chennai','17:30','11:30',30,6500);
insert INTO Flights values('F25','Flight 25','Mumbai','Mangalore','13:00','10:00',30,7000);
          
insert INTO Flights values('F26','Flight 26','Mumbai','Goa','07:30','04:30',30,5600);
insert INTO Flights values('F27','Flight 27','Mumbai','Delhi','20:30','17:30',30,3300);
insert INTO Flights values('F28','Flight 28','Mumbai','Chandigarh','15:30','11:30',30,2100);
insert INTO Flights values('F29','Flight 29','Mumbai','Chennai','23:30','17:30',30,4560);
insert INTO Flights values('F30','Flight 30','Mumbai','Mangalore','19:00','16:00',30,7800);

