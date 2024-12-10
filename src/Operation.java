

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
    // add
    // update
    public boolean changeUser(User user) throws SQLException {
       // the patiet instance == (name, birthdate, bloodtype, id)
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sqlUser = String.format("UPDATE " + TABLE + " SET first_name = "+
                            "'%s', last_name = '%s', birthdate =  '%s', phone_number = %d, password = '%s' WHERE email = '%s';",
                            user.getFirstName(),
                            user.getLastName(),
                            user.getBirthDate(),
                            user.getPhoneNumber(),
                            user.getPassword(),
                            user.getEmail()     
                    );
                            

                stmt.execute(sqlUser);
                return true;
       
       
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
    }
    
    // delete user data
    public boolean changeUserByAdmin (User user, int userID) throws SQLException {
       // the patiet instance == (name, birthdate, bloodtype, id)
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sqlUser = String.format("UPDATE " + TABLE + " SET first_name = "+
                            "'%s', last_name = '%s', birthdate =  '%s', email = '%s', phone_number = %d, password = '%s' WHERE id = %d;",
                            user.getFirstName(),
                            user.getLastName(),
                            user.getBirthDate(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getPassword(),
                            userID
                    );
                            

                stmt.execute(sqlUser);
                return true;
       
       
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }  
    }
    
       
       public boolean changeAdmin (String adminName, String adminPassword) throws SQLException {
       // the patiet instance == (name, birthdate, bloodtype, id)
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sqlAdmin = String.format("UPDATE " + TABLE2 + " SET admin_name = "+
                            "'%s', admin_password = '%s';",
                            adminName,
                            adminPassword
                    );
                            

                stmt.execute(sqlAdmin);
                return true;
       
       
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }  
       }
       
       
       public boolean removeUser (int userID) throws SQLException {
       // the patiet instance == (name, birthdate, bloodtype, id)
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sqlAdmin = String.format("DELETE FROM " + TABLE + " WHERE id= %d;",
                            userID
                    );
                            

                stmt.execute(sqlAdmin);
                return true;
       
       
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }  
    
    }
}
