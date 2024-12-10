/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author telmuun
 */
public class DatabaseReader extends DB_Connection{
    
    // we need to be able to read data from the database
    // and print it to the user when needed
    
    public ArrayList<User> getAllData(){
       // this method is used to retreive the data from the SQL database by utiziling
       // "SELECT * FROOM TABLE" SQL command
       
       ArrayList<User> users = new ArrayList<>();
       // making an collection of users we can iterate through later
       
       
       
       try( // try to connect to the database, this is important to ensure there are no sql exceptions
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){           
            // we will iterate through all the data by using resultset
            ResultSet results = stmt.executeQuery(String.format("SELECT * FROM %s;", TABLE));
            

            // creating a while loop to be able to iterate through all the info
            // as long as we have more rows to go through we will keep iterating
            while(results.next()){
                
                String firstName = results.getString("first_name"); // first name column
                String lastName = results.getString("last_name");   // last name
                String birthdate = results.getString("birthdate"); // birthdate
                String email = results.getString("email");          // email
                int phoneNumber = results.getInt("phone_number"); // phone number
                String password = results.getString("password");    // password
                int id = results.getInt("id"); // user id
            
                // format the output from the query and add them to the collection
                User user = new User(firstName, lastName, birthdate, email, phoneNumber, password, id);
                users.add(user);
                
            }
       
       
       }catch(Exception e){
           // if exception, then return why
           e.printStackTrace();
       }
       // return the collection data
       return users;  
    
    }  
    
    
    
    
    
}
