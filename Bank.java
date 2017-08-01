/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bankrefractor;

/**
 *
 * @author viki
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.Connection;



 class Bank extends BankUtilities  {
    private static Connection Connection;



Account account = new Account();
ResultSet rs = null;
    Statement stmt = null;



int accountnumber;
Float cashdeposit;
String address;
String city;
int pincode;
String state;


    private String sqlverification;
    private float cashwithdraw;
    private float cashdepositamount;

    private String sql;
static void getConnection()throws SQLException{
    Connection =DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
}
    private Connection conn;
    private Object ex;
  void existingaccount() {

 try {
            System.out.println("Enter your accont number");
            account.setAccNum(in.nextInt());
            System.out.print("Enter your  Pin Number: ");
			 account.setPinNum(in.next());
            getConnection();
           conn=Connection;
            sqlverification = ("SELECT * FROM banking.logbook where pinNum = ? AND accountnumber = ?");
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlverification);
            ps.setString(1, account.getPinNum() );
            ps.setInt(2,  account.getAccNum());


            ResultSet rs2 = (ResultSet) ps.executeQuery();
         if(rs2.next()) {
              System.out.println("The user is valid");


                sql = ("SELECT * FROM logbook where accountnumber = ? ");
            PreparedStatement preparedSelect = (PreparedStatement) conn.prepareStatement(sql);
            preparedSelect.setInt(1, account.getAccNum());
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {
                int accountnumber1 = rs1.getInt("accountnumber");
                String name1 = rs1.getString("name");
                String address1 = rs1.getString("address");
                String state1 = rs1.getString("state");
                String city1 = rs1.getString("city");
                int pincode1 = rs1.getInt("pincode");
                System.out.format("%s, %s, %s, %s, %s, %s\n", accountnumber1, name1, address1, state1, city1, pincode1);
            }
            {
                System.out.println("\n\n\tmenu option");
                try {
                    while (true) {
                        System.out.println("1.CashDepost\t2.CashWithdraw\t3.checkbalance\t4.transactionDetails.");
                        int ch = in.nextInt();
                        switch (ch) {
                            case 1:
                                deposit();
                                break;
                            case 2:
                                withdraw();
                                break;
                            case 3:
                                checkBalance();
                                break;
                         case 4:
                                lasttransaction();
                                break;

                        }
                    }
                } catch (Exception e) {
                    System.out.println("SELF THROWN EXCEPTION IS--->" );
                } finally {
                    in.close();
                }
            }
            conn.close();

        } else {
               System.out.println("You are not valid");
             }
 }catch (SQLException ex2) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex2);
        }

  }








	void getInfo() {
		try {



			System.out.print("Enter your full Name: ");
			 account.setName(in.nextLine() ) ;

             System.out.print("Enter your  Pin Number: ");
			 account.setPinNum(in.next());

			System.out.print("Enter account number: ");
			account.setAccNo(in.nextInt());
             AccNo=rnd.nextInt(1000)+1;
			System.out.print("Enter your  address: ");
			 account.setAddress(in.next());

			System.out.println("Enter your city");
			account.setcity(in.next());

			System.out.println("Enter your state");
			account.setstate(in.next());

			System.out.println("Enter your pin code");
			account.setpinCode(in.nextInt());

			System.out.println("Mention account type:\n1.Savings (7% intr )\t2.Current (5% intr) ");
			acctype = in.nextInt();
			switch (acctype) {
			case 1:
				System.out.println("Enter the initial amount of deposit:");
				Cashdeposit = in.nextFloat();
				if (Cashdeposit < 0) {
					System.out.println("Invalid Amount\nTry again:\n");
					System.out.println("Enter the initial amount of deposit:");
					Cashdeposit = in.nextFloat();
				}
				account.setCashDeposit(Cashdeposit);
				System.out.println("Enter no of years:");
				year = in.nextInt();
				if (year <= 0) {
					System.out.println("Invalid year\n Try again.");
					System.out.println("Enter no of years:");
					year = in.nextInt();
				}

				break;


			case 2:
				System.out.println("Enter the initial amount of deposit:");
				Cashdeposit = in.nextFloat();
				if (Cashdeposit < 0) {
					System.out.println("Invalid Amount\nTry again:\n");
					System.out.println("Enter the initial amount of deposit:");
					Cashdeposit = in.nextFloat();
				}
				break;

			default:
				System.out.println("Invalid Option");
			}
			createAccountInDB( account );

		} catch (Exception e) {

			System.exit(0);
		}

	}

	public void createAcc() {
		try {
			getInfo();
			System.out.println("\nAccount Successfully Created!");

			System.out.println("Hello " + account.getName() + " your account no is " + account.getAccNo() + ".\n");
		} catch (Exception e) {
			System.out.println("Fatal Error");
		}
	}



    private void checkBalance() throws SQLException {
     try {

            getConnection();
           conn=Connection;
           System.out.println("Creating statement...");
            conn.setAutoCommit(false);
            sqlverification = ("SELECT balance FROM banking.logbook where accountnumber = ? ");
            PreparedStatement preparedSelect = (PreparedStatement) conn.prepareStatement(sqlverification);
            preparedSelect.setInt(1, account.getAccNum());
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {

                int Balance1 = rs1.getInt("balance");
System.out.println("your current balance is");
               System.out.format("%s, %s, %s, %s, %s,\n" ,Balance1);
            }
        } catch (Exception e) {
                                 }
conn.close();
    }

	private void createAccountInDB( Account account ) throws SQLException{
	 try {
            getConnection();
              conn=Connection;
            String insertTableSQL = "INSERT INTO banking.logbook" + "(accountnumber,pinNum, name,address,state,city,pincode, cashdeposit,cashwithdraw, balance) VALUES" + "(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, account.getAccNo());
            preparedStatement.setString(2, account.getPinNum());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getAddrees());
            preparedStatement.setString(5, account.getstate());
            preparedStatement.setString(6, account.getcity());
            preparedStatement.setInt(7, account.getpincode());
            preparedStatement.setFloat(8, account.getCashDeposit());
            preparedStatement.setFloat(9, account.getcashwithdraw());
            preparedStatement.setFloat(10, account.getCashDeposit());
            preparedStatement.executeUpdate();


            sqlverification = ("SELECT * FROM banking.logbook where accountnumber = ? ");
PreparedStatement preparedSelect = (PreparedStatement) conn.prepareStatement(sqlverification);
            preparedSelect.setInt(1, account.getAccNo());
             System.out.println("accountnumber\t\tpassword\t\tname\t\taddress\t\tstate\t\tcity \t\tpincode\t\tcashdeposit\t\tcashwithdraw\t\tbalance");
             System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {
                 int accountnumber1 = rs1.getInt("accountnumber");
                 String pinnumber1 = rs1.getString("pinNum");
                 String name1 = rs1.getString("name");
                  String address1 = rs1.getString("address");
                  String state1 = rs1.getString("state");
                    String city1 = rs1.getString("city");
                    int pincode1 = rs1.getInt("pincode");
                    int cashdeposit1=rs1.getInt("cashdeposit");
                      int cashwithdraw1=rs1.getInt("cashwithdraw");
                    int Balance2=rs1.getInt("balance");
          System.out.format("%s, %s, %s, %s, %s,%s,%s,%s,%s, %s\n", accountnumber1,pinnumber1, name1,address1,state1,city1,pincode1,cashdeposit1,cashwithdraw1,Balance2 );
    }

          

             } catch (SQLException ex) {
 ex.printStackTrace();
        }
     conn.close();

}


	public void deposit() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("enter your ammount to deposit::");
            float cashdepositamount1 = Float.parseFloat(br.readLine());
            {
           
        getConnection();
               conn=Connection;
               conn.setAutoCommit(false);
                System.out.println("Creating statement...");
                sqlverification = ("SELECT balance FROM banking.logbook where accountnumber = ? ");
                PreparedStatement preparedSelect = (PreparedStatement) conn.prepareStatement(sqlverification);
                preparedSelect.setInt(1, account.getAccNum());
                ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
                while (rs1.next()) {
                    int Balance5 = rs1.getInt("balance");
                    PreparedStatement ps = conn.prepareStatement("update banking.logbook set cashdeposit= ?,balance=? where accountnumber=?");
                    while (true) {

                        ps.setFloat(1, cashdepositamount1);
                        ps.setFloat(2, cashdepositamount1 + Balance5);
                        ps.setInt(3, account.getAccNum());
                        int i = ps.executeUpdate();
                        System.out.println(i + " records affected");
                        System.out.println("commit/rollback");
                        String answer = br.readLine();
                        if (answer.equals("commit")) {
                            conn.commit();
                        }
                        if (answer.equals("rollback")) {
                            conn.rollback();
                        }
                        System.out.println("Want to add more records y/n");
                        String ans = br.readLine();
                        if (ans.equals("n")) {
                            break;
                        }
                    }
                    conn.commit();
                    System.out.println("record successfully saved");

                    String insertTransacationTable = "INSERT INTO banking.transactiondetails" + "(AccNum,  cahdeposit,cashwithdraw, balance,Date1) VALUES" + "(?,?,?,?,curdate())";
                    PreparedStatement preparedStatement1 = conn.prepareStatement(insertTransacationTable);
                    preparedStatement1.setInt(1, account.getAccNum());
                    preparedStatement1.setFloat(2, cashdepositamount1);
                    preparedStatement1.setFloat(3, cashwithdraw);
                    preparedStatement1.setFloat(4, cashdepositamount1 + Balance5);
                    preparedStatement1.executeUpdate();
                }  conn.close();

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void withdraw() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the amount to be withdrawn :");
            float cashwithdraw1 = Float.parseFloat(br.readLine());
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
                e.printStackTrace();
                return;
            }
            getConnection();
            conn=Connection;

            sqlverification = ("SELECT balance FROM banking.logbook where accountnumber = ? ");
            PreparedStatement preparedSelect = (PreparedStatement) conn.prepareStatement(sqlverification);
            preparedSelect.setInt(1,  account.getAccNum());
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {
                int Balance6 = rs1.getInt("balance");

                PreparedStatement ps = conn.prepareStatement("update logbook set cashwithdraw = ?, balance = ? where accountnumber = ?");
                while (rs1.next()) {
                    ps.setFloat(1, cashwithdraw1);
                    ps.setFloat(1, Balance6-cashwithdraw1- Balance6);
                    ps.setInt(2,  account.getAccNum());
                    ps.executeUpdate();

                }
                String insertTransacationTable = "INSERT INTO banking.transactiondetails" + "(AccNum,  cahdeposit,cashwithdraw, balance,Date1) VALUES" + "(?,?,?,?,curdate())";
                PreparedStatement preparedStatement1 = conn.prepareStatement(insertTransacationTable);
                preparedStatement1.setInt(1,  account.getAccNum());
                preparedStatement1.setFloat(2, cashdepositamount);
                preparedStatement1.setFloat(3, cashwithdraw1);
                preparedStatement1.setFloat(4,   Balance6-cashwithdraw1);
                preparedStatement1.executeUpdate();

               System.out.println("collect your cash");
            }
        } catch (SQLException e) {
      e.printStackTrace();
        } catch (IOException ex1) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex1);
        }


                        }

    private void lasttransaction() throws SQLException {
        try {
            getConnection();
            conn=Connection;
            System.out.println("Creating statement...");
            sqlverification = ("select * from banking.transactiondetails where AccNum = ? order by balance limit 5 ");
            PreparedStatement preparedSelect = (PreparedStatement) conn.prepareStatement(sqlverification);
            preparedSelect.setInt(1,  account.getAccNum());
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {

                int accountnumber1 = rs1.getInt("AccNum");
                int cashdeposit1=rs1.getInt("cahdeposit");
                int cashwithdraw1=rs1.getInt("cashwithdraw");
                int balance1=rs1.getInt("balance");
                Date date1=rs1.getDate("Date1");

                System.out.format("%s, %s, %s, %s %s\n", accountnumber1, cashdeposit1, cashwithdraw1, balance1, date1);

            }


        } catch (SQLException ex) {
        System.out.println("sql error");
         ex.printStackTrace();
        }



conn.close();
        }

}
