

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author telmuun
 */
public class Operation extends DB_Connection{
    // the Operation class is used to give functionality to the users and admin 
    // such as UPDATE, INSERT OR DELETE information from the database.
    
    public boolean changeUser(User user) throws SQLException {
       // changeUser method that we will use from the user menu
       try( // try to connect to the database.
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    String sqlUser = String.format("UPDATE " + TABLE + " SET first_name = "+
                            "'%s', last_name = '%s', birthdate =  '%s', phone_number = %d, password = '%s' WHERE email = '%s';",
                            // updating the already existing user information that is logged in right now.
                            user.getFirstName(),
                            user.getLastName(),
                            user.getBirthDate(),
                            user.getPhoneNumber(),
                            user.getPassword(),
                            user.getEmail()     
                    );
                            

                stmt.execute(sqlUser);
                // execute the sql query
                return true;
       
       
       }catch(Exception e){
           // if catch exception then print why, and return false so we can print to the terminal that it did not work
           e.printStackTrace();
           return false;
       }
    }
    
    
    public boolean changeUserByAdmin (User user, int userID) throws SQLException {
        // change user method only to be used by the admin
        // what makes this method different from the ChangeUser method is that it queries by userID 
        // and changes all of the information including the email which regular users can not
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sqlUser = String.format("UPDATE " + TABLE + " SET first_name = "+
                            "'%s', last_name = '%s', birthdate =  '%s', email = '%s', phone_number = %d, password = '%s' WHERE id = %d;",
                            // sql syntax to update already existign user information depending on what their user ID is
                            user.getFirstName(),
                            user.getLastName(),
                            user.getBirthDate(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getPassword(),
                            userID
                    );
                            

                stmt.execute(sqlUser);
                // execute query
                return true;
       
       
       }catch(Exception e){
           // if catch exception then print why, and return false so we can print to the terminal that it did not work
           e.printStackTrace();
           return false;
       }  
    }
    
       
       public boolean changeAdmin (String adminName, String adminPassword) throws SQLException {
       // The method to change the username and password for our admin
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sqlAdmin = String.format("UPDATE " + TABLE2 + " SET admin_name = "+
                            "'%s', admin_password = '%s';",
                            // updating the information on TABLE2, which is our admin_data table
                            adminName,
                            adminPassword
                    );
                            

                stmt.execute(sqlAdmin);
                // execute query
                return true;
       
       
       }catch(Exception e){
           e.printStackTrace();
           // if catch exception then print why, and return false so we can print to the terminal that it did not work
           return false;
       }  
       }
       
       
       public boolean removeUser (int userID) throws SQLException {
       // A method to remove user from the user_data table using their userID
       // only can be used by admin
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sqlAdmin = String.format("DELETE FROM " + TABLE + " WHERE id= %d;",
                            // SQL syntax to delete a row froma table with a condition
                            userID
                    );
                            

                stmt.execute(sqlAdmin);
                // execute query
                return true;
       
       
       }catch(Exception e){
           // if catch exception then print why, and return false so we can print to the terminal that it did not work
           e.printStackTrace();
           return false;
       }  
    
    }
}
