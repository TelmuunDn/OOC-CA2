
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author telmuun
 */
public class TaxOffce {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    
        // using the db setup class we can check if the operation went ok
        if(DatabaseSetup.setupDB() ){
            // if after we run the setupdb method all is ok
            // we have now created a db schem and created a table
            
            System.out.println("Database has been created successfully or already exists...");
    
            } else{
            // there is an issue connecting to the db or creating the schema
            System.out.println("There was a problem creating or connecting to the db... \n Please check db credentials");
            return;
        }
    
    
    
    
    
    
    
    
    
    
    
    
    }   
    
}
