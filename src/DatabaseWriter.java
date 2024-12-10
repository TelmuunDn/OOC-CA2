/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
/**
 *
 * @author telmuun
 */
public class DatabaseWriter extends DB_Connection{
    // for writing information to the databse or specifically adding users to the table
    
    public boolean addUser(User user) throws SQLException {
       // the user instance with their attributes
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               // everytime when connecting to the database, it is important to try and catch any exceptions
               ){
                    String sqlUser = String.format("INSERT INTO " + TABLE + " VALUES ("
                    + "'%s', '%s', '%s', '%s', %d, '%s', %d);",
                            // the sql syntax INSERT INTO TABLE 
                            // with all the appropriate attributes to populate the new row
                            user.getFirstName(),
                            user.getLastName(),
                            user.getBirthDate(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getPassword(),
                            user.getUserID()
                    );
                         
                    // the following syntax is created in case if we need to add a new admin user to the admin_data table in the future
                    // but for now it is not needed.
                    
//                    String sqlAdmin = String.format("INSERT INTO " + TABLE2 + " VALUES ("
//                    + "'%s', '%s');",
//                            admin.getAdminName(),
//                            admin.getAdminPassword()
//                    );
                stmt.execute(sqlUser);
                // execute the query above
                
                return true;
                // return true if successfull so we can print an appropriate message to the user
       
       }catch(Exception e){
           // if catch exception then print why, and return false so we can print to the terminal that it did not work
           e.printStackTrace();
           return false;
       }
   
    }
    
    
}
