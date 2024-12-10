

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    // user login class that contains a method to query the database and connect the user if their credentials match
    
    // login information variables
    private String email;
    private String password;
    
    // query the server and see if the information matches
    public boolean UserLoginAction() throws SQLException {                                              
        
        // try and connect to the database
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()){
                    // regex validate the input and store them
                    email = regexvalidation.askForEmail();
                    // regex validate the password input and store them
                    password = regexvalidation.askForPassword();
           
                    // create the query using the below format
                    String sqlUser = String.format("SELECT * FROM " + TABLE + " WHERE email = " + "'%s'" + " AND password = "
                    + "'%s';",
                            //"SELECT * FROM user_data WHERE email = ? AND password = ?"
                            email,
                            password
                    );
                            
                try (ResultSet rs = stmt.executeQuery(sqlUser)) {
                // Check if a result exists
                if (rs.next()) {
                    // if result exists we get to the next menu
                    System.out.println("Login successful!");
                    return true;
                } else {
                    // else try again
                    System.out.println("Invalid email or password.");
                    System.out.println("CASE SENSITIVE!!!\n");
                    return false;
                }
       
       
       }catch(Exception e){
           // catch exceptions and print them
           e.printStackTrace();
           return false;
       }
    }  
    }
    
    
    public ArrayList<User> getUserData(){
       // this method with retreive the logged in user's information from the database

       ArrayList<User> userData = new ArrayList<>();
       // make a collection we can store our user information
       // while it is not necessary, in case there are more than one user with the same email
       // this will display all of the informaton under the same email address

       // try to connect to the DB and retreive the info
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){           
            // query the database with the appropriate email address as a condition
            // and get all of the information filtered
            ResultSet results = stmt.executeQuery(String.format("SELECT * FROM "+ TABLE + " WHERE email = " + "'%s';",email));
            
            // iterate through the results
            while(results.next()){
                // collect all the table data as variables
                String firstName = results.getString("first_name"); // patient name
                String lastName = results.getString("last_name");
                String birthdate = results.getString("birthdate"); // birthdate
                String email = results.getString("email");
                int phoneNumber = results.getInt("phone_number"); // patient id
                String password = results.getString("password");
                int id = results.getInt("id"); // patient id
            
                // and add them to the collection
                User user = new User(firstName, lastName, birthdate, email, phoneNumber, password, id);
                userData.add(user);
                
            }
       
       
       }catch(Exception e){
           e.printStackTrace();
       }
       // return the collection data to be printed
       return userData;  
    
    }
    
    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    
}
