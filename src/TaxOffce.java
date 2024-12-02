
import java.sql.SQLException;
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
public class TaxOffce {
    
    // the main method will try to insert the data into db
    // check the db connection status
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    
        // using the db setup class we can check if the operation went ok
        if(DatabaseSetup.setupDB() ){
            // if after we run the setupdb method all is ok
            // we have now created a db schem and created a table
            
            System.out.println("Database has been created successfully or already exists...");
        
            //instantiating the write and reader for the db
            DatabaseWriter dbw = new DatabaseWriter(); // this will allow us to write to the db
            DatabaseReader dbr = new DatabaseReader(); // this will allow us to read from the db
            Scanner scanner = new Scanner(System.in); // this will capture the user input
            
            // we want to present the menu to the user and we want the user to interact as long as they please;
            
            while(true){
                
                // like all menus, we need a couple of print statements to present to the user
                System.out.println("\nThe tax calculation system (HAS)");
                System.out.println("Please select from the following options: \n");
                System.out.println("1. Insert a user record");
                System.out.println("2. Read user data from the DB");
                System.out.println("3. Exit");
                System.out.println("\nEnter your choice: \n");
                
                // capture the user choice
                int choice = scanner.nextInt();
                scanner.nextLine(); // add another line
                
                // take in the user choice ad provide a functionality according to the user selection
                // theres going to be 3 options
                
                switch(choice){
                    // since we know its a numbr the cases will be numbers
                    case 1: // if the user selects 1
                        //insert new data to the db
                        System.out.println("Enter user data: ");
                        System.out.println("First Name: ");
                        String firstName =scanner.nextLine(); // name of the patient
                        System.out.println("Last Name: ");
                        String lastName =scanner.nextLine();
                        System.out.println("Birthdate: YYYY-MM-DD format");
                        String birthDate = scanner.nextLine();
                        System.out.println("Email:");
                        String email = scanner.nextLine();
                        System.out.println("Phone Number: ");
                        int phoneNumber = scanner.nextInt();
                        
                       
                        // collect all the user input
                        User newUser = new User(firstName,lastName, birthDate, email, phoneNumber);
                        // try adding the patient record 
                        // check if successful otherwise let the user know
                        if(dbw.addUser(newUser)){
                            System.out.println("User added successfully");
                        }else{
                            System.out.println("Unable to add user to table, please check all inputs");                        
                        } 
                        break;
                        
                    case 2:
                        // read the data from the db
                        ArrayList<User> users = dbr.getAllData();
                        // retreive the data from the db, store it as the patients arraylist;
                        
                        //check if empty
                        if(users.isEmpty()){
                            System.out.println("No data was found");
                        }else{
                            System.out.printf("%-5s | %-10s | %-10s | %-12s | %-15s | %-15s", "\nID", "First Name", "Last Name", "Birth Date", "Email", "Phone Number");
                            System.out.println("\n---------------------------------------------------");
                            // print out all the patient records in to the table
                            // this will require iterating  therough the patients arraylist
                            for(User user: users){
                                System.out.printf("%-5s | %-10s | %-10s | %-12s | %-15s | %-15s\n", 
                                        user.getUserID(),
                                        user.getFirstName(),
                                        user.getLastName(),
                                        user.getBirthDate(),
                                        user.getEmail(),
                                        user.getPhoneNumber()                                
                                );
                            }   
                        }
                        break;
                        
                    case 3:
                        // exit
                        System.out.println("Thank you for using our system");
                        System.out.println("Exiting....");
                        scanner.close(); // scanner is our IO stream we dont want the user to be able to interact with the system when its closed
                        return;
                        
                    default:
                        System.out.println("Wrong input, please select form the menu choices");
                
                }
 
            }
   
        } else{
            // there is an issue connecting to the db or creating the schema
            System.out.println("There was a problem creating or connecting to the db... \n Please check db credentials");
            return;
        }
       
        // add
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    }   
    

