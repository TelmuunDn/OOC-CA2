
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
    // once extended we can make use of the db information and create some logic to process the db creation
    
    // create a method to setup the connection
        public static boolean setupDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
            // any crud operation on the db couple of things can go wrong
            // the driver we have will manage the connection and make sure there is server feedback to the client
            
                // create an instance of the driver class 
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
                // try to connect to the database
                try(
                        Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD);
                        Statement stmt = conn.createStatement();
                        ){
                        // this is the query or the statement.
                        // check if we have the db and otherwise create it
                        stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";");
                        // query the db using the USE keyword
                        stmt.execute("USE " + DB_NAME + ";"); // database schema pointer
                        // create a query to insert into the database
                        String sqlUser = 
                                // CREATE TABLE IF NOT EXISTS patient_data name VARCHAR(255)
                                "CREATE TABLE IF NOT EXISTS " + TABLE + "("
                                + "first_name VARCHAR(255),"
                                + "last_name VARCHAR(255),"
                                + "birthdate DATE," // YYYY-MM-DD format
                                + "email VARCHAR(255),"
                                + "phone_number INT(12),"
                                + "password VARCHAR(255),"
                                + "id INT AUTO_INCREMENT PRIMARY KEY"
                                +");";
                        // take this query and execute it
                        stmt.execute(sqlUser);
                        
                        String sqlAdmin = 
                                // CREATE TABLE IF NOT EXISTS patient_data name VARCHAR(255)
                                "CREATE TABLE IF NOT EXISTS " + TABLE2 + "("
                                + "admin_name VARCHAR(255),"
                                + "admin_password VARCHAR(255)"
                                +");";
                        // take this query and execute it
                        stmt.execute(sqlAdmin);
                        
                        String sqlAddAdmin = "INSERT INTO " + TABLE2 + " (admin_name, admin_password) "
                                + "SELECT 'CCT', 'Dublin' WHERE NOT EXISTS ( SELECT 1 FROM " + TABLE2 
                                + " WHERE admin_name = 'CCT' AND admin_password = 'Dublin');";
                        stmt.execute(sqlAddAdmin);
                                
                        
                        String sqlAddUserHistory = 
                                "CREATE TABLE IF NOT EXISTS tax_office.user_data_history (\n" +
                                "    change_id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Unique ID for each change\n" +
                                "    action ENUM('insert', 'update', 'delete') NOT NULL, -- Type of operation\n" +
                                "    change_timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Time of the change\n" +
                                "    user_id INT NOT NULL, -- Assuming user_data has a primary key column named 'id'\n" +
                                "    old_data JSON DEFAULT NULL, -- Store old values (for updates and deletes)\n" +
                                "    new_data JSON DEFAULT NULL -- Store new values (for inserts and updates)\n" +
                                ") ENGINE=InnoDB;";
                        stmt.execute(sqlAddUserHistory);
                        
                        String sqlHistoryTriggers = 
                                "DELIMITER $$\n" +
                                "\n" +
                                "-- Trigger for INSERT\n" +
                                "CREATE TRIGGER tax_office.user_data_after_insert\n" +
                                "AFTER INSERT ON tax_office.user_data\n" +
                                "FOR EACH ROW\n" +
                                "BEGIN\n" +
                                "    INSERT INTO tax_office.user_data_history (action, user_id, new_data)\n" +
                                "    VALUES ('insert', NEW.id, JSON_OBJECT('id', NEW.id, 'column1', NEW.column1, 'column2', NEW.column2)); -- Replace column names as needed\n" +
                                "END$$\n" +
                                "\n" +
                                "-- Trigger for UPDATE\n" +
                                "CREATE TRIGGER tax_office.user_data_after_update\n" +
                                "AFTER UPDATE ON tax_office.user_data\n" +
                                "FOR EACH ROW\n" +
                                "BEGIN\n" +
                                "    INSERT INTO tax_office.user_data_history (action, user_id, old_data, new_data)\n" +
                                "    VALUES (\n" +
                                "        'update',\n" +
                                "        OLD.id,\n" +
                                "        JSON_OBJECT('id', OLD.id, 'column1', OLD.column1, 'column2', OLD.column2), -- Replace column names as needed\n" +
                                "        JSON_OBJECT('id', NEW.id, 'column1', NEW.column1, 'column2', NEW.column2)  -- Replace column names as needed\n" +
                                "    );\n" +
                                "END$$\n" +
                                "\n" +
                                "-- Trigger for DELETE\n" +
                                "CREATE TRIGGER tax_office.user_data_before_delete\n" +
                                "BEFORE DELETE ON tax_office.user_data\n" +
                                "FOR EACH ROW\n" +
                                "BEGIN\n" +
                                "    INSERT INTO tax_office.user_data_history (action, user_id, old_data)\n" +
                                "    VALUES (\n" +
                                "        'delete',\n" +
                                "        OLD.id,\n" +
                                "        JSON_OBJECT('id', OLD.id, 'column1', OLD.column1, 'column2', OLD.column2) -- Replace column names as needed\n" +
                                "    );\n" +
                                "END$$\n" +
                                "\n" +
                                "DELIMITER ;";
                        //stmt.execute(sqlHistoryTriggers);
                        return true;
                
                }catch(Exception e){
                    e.printStackTrace();
                    return false;                
                }
        
        
        
        
        
        
        }
            
    
    // create some logic to ensure we do not run into issues with the db connection
    
    
    
    
    
    
}
