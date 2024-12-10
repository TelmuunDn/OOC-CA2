/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author telmuun
 */
public class AdminLogin extends DB_Connection{
    // admin login class that contains the functions necessary for the admin to login to the system
    // and achieve priviliged commands
    
    
    // variables
    private String adminName;
    private String adminPassword;
    
    // admin login action method that queries the SQL server to check if the login information entered matches with the data on the table
    public boolean AdminLoginAction() throws SQLException {                                              

        // try and connect to the database
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()){

                // asking the admin to enter the credentials    
                System.out.println("\nPlease enter your Admin username:");
                // regex validate the admin login info, so there are no numbers and special characters and store them
                adminName = regexvalidation.askForAdminLogin();
                System.out.println("Please enter your Admin password:");
                // regex validate the admin login info, so there are no numbers and special characters and store them
                adminPassword = regexvalidation.askForAdminLogin();
                
                    // query the SQL server and check if our stored information matches the table information
                    // 'CCT' 'Dublin'
                    String sqlAdmin = String.format("SELECT * FROM " + TABLE2 + " WHERE admin_name = '%s' AND admin_password = '%s';",
                            //"SELECT * FROM user_data WHERE username = ? AND password = ?"
                            adminName,
                            adminPassword
                    );
                    
                    

                try (ResultSet rs = stmt.executeQuery(sqlAdmin)) {
                // Check if a result exists
                if (rs.next()) {
                    // if result exists SUCCESS!!!
                    System.out.println("Login successful!");
                    return true;
                } else {
                    // if not we try again                        
                    System.out.println("Invalid email or password.");
                    System.out.println("CASE SENSITIVE!!!\n");
                    return false;
                }
       
       
       }catch(Exception e){
           // catch any errors, especially SQL exceptions and print them
           e.printStackTrace();
           return false;
       }
       
                

    } 
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
