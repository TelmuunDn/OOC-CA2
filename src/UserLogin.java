

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
    
    private String email;
    private String password;
    
    public boolean UserLoginAction() throws SQLException {                                              

       Scanner sc = new Scanner(System.in);
       
       
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()){
            
            
                   


                   
                   email = regexvalidation.askForEmail();
                   
                   password = regexvalidation.askForPassword();
           
           
                    String sqlUser = String.format("SELECT * FROM " + TABLE + " WHERE email = " + "'%s'" + " AND password = "
                    + "'%s';",
                            //"SELECT * FROM user_data WHERE username = ? AND password = ?"
                            email,
                            password
                    );
                            
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
    
    public ArrayList<User> getUserData(){
       // this method will hand retreival and storage of patient data
       // in order for us to retreive the information, we first must connect to the DB
       // this method will act as a "SELECT * FROOM TABLE" SQL command
       
       ArrayList<User> userData = new ArrayList<>();
       
       // try to connect to the DB and retreive the info
       
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){           
           // in this block we will handle any requirements for pulling the data from the db
           // now that the connection is established we begin creating the logic for reading the data from the db
           // there will be multiple records and we would like to iterate through all of the records
            ResultSet results = stmt.executeQuery(String.format("SELECT * FROM "+ TABLE + " WHERE email = " + "'%s';",email));
            // create a check for results an dcreate a while loop to iterate through them
            // for every result we have the data will look like this:
            // Name, BirthDate, Bloodtype, ID
            
            // as long as we have more rows to go through we will keep iterating
            while(results.next()){
                
                String firstName = results.getString("first_name"); // patient name
                String lastName = results.getString("last_name");
                String birthdate = results.getString("birthdate"); // birthdate
                String email = results.getString("email");
                int phoneNumber = results.getInt("phone_number"); // patient id
                String password = results.getString("password");
                int id = results.getInt("id"); // patient id
            
                // formate the output from the query and insert into collection
                User user = new User(firstName, lastName, birthdate, email, phoneNumber, password, id);
                userData.add(user);
                
            }
       
       
       }catch(Exception e){
           e.printStackTrace();
       }
       // arraylist data
       return userData;  
    
    }
    
    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    
}
