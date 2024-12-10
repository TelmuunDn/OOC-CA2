
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
public class DatabaseSetup extends DB_Connection{
    // on this class we place our first connection to the SQL database
    // and create our tables 
        public static boolean setupDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
            // when something goes wrong while establishing connection with the database, throw the error
            
                // create an instance of the driver class 
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
                // try to connect to the database
                try(
                        Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD);
                        Statement stmt = conn.createStatement();
                        ){
                        // check if the database does exist, if not create one
                        stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";");
                        // using the database 
                        stmt.execute("USE " + DB_NAME + ";"); // database schema pointer
                        // sqlUser query to create the user_data table
                        String sqlUser = 
                                // CREATE TABLE IF NOT EXISTS user_data first_name VARCHAR(255)
                                "CREATE TABLE IF NOT EXISTS " + TABLE + "("
                                + "first_name VARCHAR(255),"
                                + "last_name VARCHAR(255),"
                                + "birthdate DATE," // YYYY-MM-DD format
                                + "email VARCHAR(255),"
                                + "phone_number INT(12),"
                                + "password VARCHAR(255),"
                                + "id INT AUTO_INCREMENT PRIMARY KEY"
                                // the user id is auto incremented in sql so we do not have to worry about, user IDs repeating due to runtime errors
                                +");";
                        // execute query
                        stmt.execute(sqlUser);
                        
                        // create admin_data table that we will use to login to the admin privilige system
                        String sqlAdmin = 
                                // CREATE TABLE IF NOT EXISTS admin_data name VARCHAR(255)
                                "CREATE TABLE IF NOT EXISTS " + TABLE2 + "("
                                + "admin_name VARCHAR(255),"
                                + "admin_password VARCHAR(255)"
                                +");";
                        // take this query and execute it
                        stmt.execute(sqlAdmin);
                        
                        // executing the following query into the admin_data table
                        // As it is required to have CCT, Dublin credentials for the examiner to log into the admin system
                        String sqlAddAdmin = "INSERT INTO " + TABLE2 + " (admin_name, admin_password) "
                                + "SELECT 'CCT', 'Dublin' WHERE NOT EXISTS ( SELECT 1 FROM " + TABLE2 
                                + " WHERE admin_name = 'CCT' AND admin_password = 'Dublin');";
                        stmt.execute(sqlAddAdmin);
                        
                        // return true if all goes well, so we can print a message
                        return true;
                
                }catch(Exception e){
                    // if catch exception then print why, and return false so we can print to the terminal that it did not work
                    e.printStackTrace();
                    return false;                
                }
        
        
        
        
        
        
        }
            
    
    // create some logic to ensure we do not run into issues with the db connection
    
    
    
    
    
    
}
