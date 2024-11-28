/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static db_connection.DB_Connection.DB_URL;
import static db_connection.DB_Connection.PASSWORD;
import static db_connection.DB_Connection.USER;
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
    
    // we will need to be able to read data back
    // from the database too the user
    // since we formatted the patient data, we can use the collection method to store and retreive all of the patient data
    
    public ArrayList<User> getAllData(){
       // this method will hand retreival and storage of patient data
       // in order for us to retreive the information, we first must connect to the DB
       // this method will act as a "SELECT * FROOM TABLE" SQL command
       
       ArrayList<User> users = new ArrayList<>();
       
       // try to connect to the DB and retreive the info
       
       try(
               Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               Statement stmt = conn.createStatement();
               ){           
           // in this block we will handle any requirements for pulling the data from the db
           // now that the connection is established we begin creating the logic for reading the data from the db
           // there will be multiple records and we would like to iterate through all of the records
            ResultSet results = stmt.executeQuery(String.format("SELECT * FROM %s;", TABLE));
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
                int id = results.getInt("id"); // patient id
            
                // formate the output from the query and insert into collection
                User user = new User(firstName, lastName, birthdate, email, phoneNumber, id);
                users.add(user);
                
            }
       
       
       }catch(Exception e){
           e.printStackTrace();
       }
       // arraylist data
       return users;  
    
    }    
    
}
