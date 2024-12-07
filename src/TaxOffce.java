
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
            UserLogin ul = new UserLogin(); // this will allow the users to login
            Scanner scanner = new Scanner(System.in); // this will capture the user input
            
            // we want to present the menu to the user and we want the user to interact as long as they please;
            
            while(true){
                
                // like all menus, we need a couple of print statements to present to the user
                System.out.println("\nThe tax calculation system (HAS)");
                System.out.println("Please select from the following options: \n");
                System.out.println("1. Login as User");
                System.out.println("2. Register as User");
                System.out.println("3. Login as Admin");
                System.out.println("4. Exit");
                System.out.println("\nEnter your choice: \n");
                
                
                System.out.println("1. Insert a user record");
                System.out.println("2. Read user data from the DB");
                System.out.println("3. Enter the tax calculator");
                System.out.println("4. User Login");
                System.out.println("5. Exit");
                System.out.println("\nEnter your choice: \n");
                
                // capture the user choice 
                int choice = scanner.nextInt();
                scanner.nextLine(); // add another line
                
                // take in the user choice ad provide a functionality according to the user selection
                // theres going to be 3 options
                
                switch(choice){
                    
                    case 1:
                        
                        
                        ul.UserLoginAction();
                        break;
                        
                    // since we know its a numbr the cases will be numbers
                    case 2: // if the user selects 2
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
                        System.out.println("Password: ");
                        String password = scanner.nextLine();
                        System.out.println("Phone Number: ");
                        int phoneNumber = scanner.nextInt();
                        
                        
                        
                        // collect all the user input
                        User newUser = new User(firstName,lastName, birthDate, email, phoneNumber, password);
                        // try adding the patient record 
                        // check if successful otherwise let the user know
                        if(dbw.addUser(newUser)){
                            System.out.println("User added successfully");
                        }else{
                            System.out.println("Unable to add user to table, please check all inputs");                        
                        } 
                        break;
                        
                        case 3:
                        
                        // to make an admin action here
//                        ul.AdminLoginAction();
                        
                            System.out.println("Welcome to the admin menu, please choose from below:\n");
                            System.out.println("1. List all users and their information");
                            System.out.println("2. Change user information");
                            System.out.println("3. Change admin information");
                            System.out.println("4. Remove a user from the system");
                            System.out.println("5. Review the database history");
                            System.out.println("6. Exit");
                            System.out.println("\nEnter your choice: \n");
                            switch(choice){
                                case 1:
                                                // read the data from the db
                                    ArrayList<User> users = dbr.getAllData();
                                    // retreive the data from the db, store it as the patients arraylist;

                                    //check if empty
                                    if(users.isEmpty()){
                                        System.out.println("No data was found");
                                    }else{
                                        System.out.printf("%-5s | %-10s | %-10s | %-12s | %-25s | %-15s | %-20s", "\nID", "First Name", "Last Name", "Birth Date", "Email", "Phone Number", "Password");
                                        System.out.println("\n----------------------------------------------------------------------------------------");
                                        // print out all the patient records in to the table
                                        // this will require iterating  therough the patients arraylist
                                        for(User user: users){
                                            System.out.printf("%-5s | %-10s | %-10s | %-12s | %-25s | %-15s | %-20s\n", 
                                                    user.getUserID(),
                                                    user.getFirstName(),
                                                    user.getLastName(),
                                                    user.getBirthDate(),
                                                    user.getEmail(),
                                                    user.getPhoneNumber(), 
                                                    user.getPassword()
                                            );
                                        }   
                                    }
                                    break;
                                
                                case 2:
                                    System.out.println("Work in progress");
                                    break;
                                    
                                case 3:
                                    System.out.println("work in progress");
                                    break;
                                    
                                case 4:
                                    System.out.println("work in progress");
                                    break;
                                    
                                case 5:
                                    System.out.println("work in progress");
                                    break;
                                    
                                case 6:
                                    // exit
                                    System.out.println("Thank you for using our system");
                                    System.out.println("Exiting....");
                                    scanner.close(); // scanner is our IO stream we dont want the user to be able to interact with the system when its closed
                                    return;

                                default:
                                    System.out.println("Wrong input, please select form the menu choices");
                                    
                                
                                    
                                    
                            
                            
                            
                            
                            
                            
                            }
                            
                        break;
                        
                    
                        
                    
                        
                        
                        
                        
                    case 4:
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
    

