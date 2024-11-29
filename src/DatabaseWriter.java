/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//import static DB_Connection.DB_URL;
//import static DB_Connection.USER;
//import static db_connection.DB_Connection.PASSWORD;
//import static db_connection.DB_Connection.TABLE;

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
    // this method will write information to the database
    
    public boolean addUser(User user) throws SQLException {
       // the patiet instance == (name, birthdate, bloodtype, id)
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){
                    
                    String sql = String.format("INSERT INTO " + TABLE + " VALUES ("
                    + "'%s', '%s', '%s', '%s', %d, %d, %d);",
                            user.getFirstName(),
                            user.getLastName(),
                            user.getBirthDate(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getGrossIncome(),
                            user.getUserID()
                    );
                            
//                    String sql1 = String.format("INSERT INTO " + TABLE2 + " VALUES ("
//                    + "'%s', '%s', '%s', '%s', %d, %d, %d);",
//                            user.getFirstName(),
//                            user.getLastName(),
//                            user.getBirthDate(),
//                            user.getEmail(),
//                            user.getPhoneNumber(),
//                            user.getGrossIncome(),
//                            user.getUserID()        
//                    );
                stmt.execute(sql);
                return true;
       
       
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
    
    
    
    
    }
}
