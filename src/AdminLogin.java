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
    
    private String adminName;
    private String adminPassword;
    
    public boolean AdminLoginAction() throws SQLException {                                              

       Scanner sc = new Scanner(System.in);
       
       
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()){
            
            
                   


                   System.out.println("\nPlease enter your Admin username:");
                   adminName = sc.nextLine();
                   System.out.println("Please enter your Admin password:");
                   adminPassword = sc.nextLine();
           
           
                    String sqlAdmin = String.format("SELECT * FROM " + TABLE2 + " WHERE admin_name = '%s' AND admin_password = '%s';",
                            //"SELECT * FROM user_data WHERE username = ? AND password = ?"
                            adminName,
                            adminPassword
                    );
                    
                    

                try (ResultSet rs = stmt.executeQuery(sqlAdmin)) {
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
