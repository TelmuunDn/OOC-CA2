
//import static DB_Connection.DB_URL;
//import static DB_Connection.PASSWORD;
//import static DB_Connection.TABLE;
//import static DB_Connection.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author telmuun
 */
public class UserLogin extends DB_Connection{
    
    public boolean UserLoginAction() throws SQLException {                                              

       Scanner sc = new Scanner(System.in);
       
       
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()){
            
            
                   String email;
                   String password;


                   System.out.println("\nPlease enter your email:");
                   email = sc.nextLine();
                   System.out.println("Please enter your password:");
                   password = sc.nextLine();
           
           
                    String sqlUser = String.format("SELECT * FROM " + TABLE + " WHERE email = " + "'%s'" + " AND password = "
                    + "'%s';",
                            //"SELECT * FROM user_data WHERE username = ? AND password = ?"
                            email,
                            password
                    );
                            
//                    String sql1 = String.format("INSERT INTO " + TABLE2 + " VALUES ("
//                    + "'%s', '%s', '%s', '%s', %d, %d);",
//                            user.getFirstName(),
//                            user.getLastName(),
//                            user.getBirthDate(),
//                            user.getEmail(),
//                            user.getPhoneNumber(),
//                            
//                            user.getUserID()        
//                    );
                try (ResultSet rs = stmt.executeQuery(sqlUser)) {
                // Check if a result exists
                if (rs.next()) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Invalid email or password.");
                    System.out.println("CASE SENSITIVE!!!\n");
                    return false;
                }
       
       
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
       

    } 
    }
}
