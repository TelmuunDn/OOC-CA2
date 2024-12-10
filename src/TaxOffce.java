
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
    
        int choice;
        boolean flag = true;
        
        // using the db setup class we can check if the operation went ok
//        if(DatabaseSetup.setupDB() ){
            // if after we run the setupdb method all is ok
            // we have now created a db schem and created a table
            
            System.out.println("Database has been created successfully or already exists...");
        
            //instantiating the write and reader for the db
            DatabaseWriter dbw = new DatabaseWriter(); // this will allow us to write to the db
            DatabaseReader dbr = new DatabaseReader(); // this will allow us to read from the db
            UserLogin ul = new UserLogin(); // this will allow the users to login
            Scanner scanner = new Scanner(System.in); // this will capture the user input
            AdminLogin al = new AdminLogin();
            Operation op= new Operation();
            
            History hs = new History();
            ArrayList<History> userHistory = new ArrayList<>();
            // we want to present the menu to the user and we want the user to interact as long as they please;
            
            do{
                
                // like all menus, we need a couple of print statements to present to the user
                System.out.println("\nThe tax calculation system (HAS)");
                System.out.println("Please select from the following options: \n");
                System.out.println("1. Login as User");
                System.out.println("2. Register as User");
                System.out.println("3. Login as Admin");
                System.out.println("4. Exit");
                System.out.println("\nEnter your choice: \n");

                
                // capture the user choice 
                choice = scanner.nextInt();
                
                // take in the user choice ad provide a functionality according to the user selection
                // theres going to be 3 options
                
                switch(choice){
                    
                    case 1:
                        
                        if (ul.UserLoginAction()){
                        
                            System.out.println("Please find below your user  information:");
                            ArrayList<User> userData = ul.getUserData();
                            System.out.printf("%-5s | %-10s | %-10s | %-12s | %-25s | %-15s | %-20s", "\nID", "First Name", "Last Name", "Birth Date", "Email", "Password", "Phone Number");
                                        System.out.println("\n----------------------------------------------------------------------------------------");
                                        // print out all the patient records in to the table
                                        // this will require iterating  therough the patients arraylist
                                        for(User user: userData){
                                            System.out.printf("%-5s | %-10s | %-10s | %-12s | %-25s | %-15s | %-20s\n", 
                                                    user.getUserID(),
                                                    user.getFirstName(),
                                                    user.getLastName(),
                                                    user.getBirthDate(),
                                                    user.getEmail(), 
                                                    user.getPassword(),
                                                    user.getPhoneNumber()
                                            );
                                        } 
                            System.out.println("\n1. Change own information");
                            System.out.println("2. Enter the tax calculator");
                            System.out.println("3. Exit");
                            System.out.println("\nEnter your choice: \n");
                            scanner.nextLine();
                            choice = scanner.nextInt();
                            
                            switch(choice){
                                case 1:
                                    //insert new data to the db
                                    System.out.println("Enter user data");
                                    scanner.nextLine();
                                    System.out.println("First Name:");
                                    String firstName = scanner.nextLine(); // name of the user
                                    System.out.println("Last Name:");
                                    String lastName = scanner.nextLine();

                                    System.out.println("Birthdate: YYYY-MM-DD format");
                                    String birthDate = scanner.nextLine();
                                    
                                    String email = ul.getEmail(); // we know the email for the user already, and it should not be changed because it is the username that is used for login
                                    
                                    System.out.println("Password: ");
                                    String password = scanner.nextLine();
                                    System.out.println("Phone Number: ");
                                    int phoneNumber = scanner.nextInt();
                                    
                                    // collect all the user input
                                    User changeUser = new User(firstName,lastName, birthDate, email, phoneNumber, password);
                                    // try adding the patient record 
                                    // check if successful otherwise let the user know
                                    if(op.changeUser(changeUser)){
                                        System.out.println("User information updated successfully");
                                        flag = false;
                                    }else{
                                        System.out.println("Unable to add user to table, please check all inputs, try again");
                                        choice = scanner.nextInt();
                                        flag = false;
                                    }
                                    break;
                                    
                                case 2:
                                    System.out.println("Please write your annual income: ");
                                    double income = scanner.nextDouble();
                                    taxcalculator tax = new taxcalculator();// calling the calculator
                                    System.out.println("~");
                                    System.out.println("Your USC (Universal Social Charge) is: "+ tax.getcalculatePRSI(income));
                                    System.out.println("Your PRSI (Pay-Related Social Insurance) is: " + tax.getcalculateUSC(income));
                                    System.out.println("~");
                                    flag = false;
                                    break;

                                case 3: 
                                    System.out.println("Thank you for using our system");
                                    System.out.println("Returning to the Main Menu....");
                                    flag = false;
                                    break;
                                    
                                default:
                                    System.out.println("Wrong input, please select form the menu choices");
                                    flag = false;

                            }
                                                
                        }else{
                            
                            System.out.println("Invalid!!!!");
                            flag = false;
                        }
                                     
                        break;
                        
                    // since we know its a numbr the cases will be numbers
                    case 2: // if the user selects 2
                        //insert new data to the db
                        System.out.println("Enter user data");
                        
//                       
                        String firstName = regexvalidation.askForFirstName();// name of the user
                        String lastName = regexvalidation.askForSecondName();
                        String birthDate = regexvalidation.askForYear() +"-"+ regexvalidation.askForMonth() +"-"+ regexvalidation.askForDay();
                        String email = regexvalidation.askForEmail();
                        int phoneNumber = regexvalidation.askForPhoneNumber();
                        String password = regexvalidation.askForPassword();
                        
                        
                        // collect all the user input
                        User newUser = new User(firstName,lastName, birthDate, email, phoneNumber, password);
                        // try adding the patient record 
                        // check if successful otherwise let the user know
                        if(dbw.addUser(newUser)){
                            System.out.println("User added successfully");
                            hs.addUserHistory("user_data", "INSERT NEW USER - Name: " + firstName +" "+ lastName + " DOB: " + birthDate + " Email: "+email + " PN: "+phoneNumber + " PWD: "+password);
                            flag = false;
                        }else{
                            System.out.println("Unable to add user to table, please check all inputs, try again");
                            choice = scanner.nextInt();
                            flag = false;
                        } 
                        break;
                        
                        case 3:
                        
                        // to make an admin action here
                            if(al.AdminLoginAction()){
                        
                            System.out.println("\nWelcome to the admin menu, please choose from below:\n");
                            System.out.println("1. List all users and their information");
                            System.out.println("2. Change user information");
                            System.out.println("3. Change admin information");
                            System.out.println("4. Remove a user from the system");
                            System.out.println("5. Review the database history");
                            System.out.println("6. Exit");
                            System.out.println("\nEnter your choice: \n");
                            scanner.nextLine();
                            choice = scanner.nextInt();
                            
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
                                    flag = false;
                                    break;
                                
                                case 2:
                                    int userID;
                                    System.out.println("\nChanging user data...Please stand by");
                                    
                                    System.out.println("Please enter the USER ID of the target user:");
                                    userID = scanner.nextInt();
                                    //insert new data to the db
                                    System.out.println("Enter user data for user ID-" + userID);
                                    scanner.nextLine();
                                    System.out.println("Please enter the new First Name for user ID-" + userID);
                                    firstName = scanner.nextLine(); // name of the user
                                    System.out.println("Please enter the new Last Name for user ID-" + userID);
                                    lastName = scanner.nextLine();
                                    System.out.println("Please enter the new Birthdate for user ID-" + userID);
                                    System.out.println("Birthdate: YYYY-MM-DD format");
                                    birthDate = scanner.nextLine();
                                    System.out.println("Please enter the new Email for user ID-" + userID);
                                    email = scanner.nextLine(); 
                                    System.out.println("Please enter the new Password for user ID-" + userID);
                                    password = scanner.nextLine();
                                    System.out.println("Please enter the new Phone Number for user ID-" + userID);
                                    phoneNumber = scanner.nextInt();
                                    
                                    
                                    User userChange = new User(firstName,lastName, birthDate, email, phoneNumber, password);
                                    
                                    if(op.changeUserByAdmin(userChange, userID)){
                                        System.out.println("User information updated successfully");
                                        flag = false;
                                    }else{
                                        System.out.println("Unable to add user to table, please check all inputs, try again");
                                        choice = scanner.nextInt();
                                        flag = false;
                                    }
                                    
                                    
                                    
                                    
                                    flag = false;
                                    break;
                                    
                                case 3:
                                                                              
                                    System.out.println("Please insert the new Admin Username:");
                                    scanner.nextLine();
                                    String adminName = scanner.nextLine();
                                    
                                    System.out.println("Please insert the new Admin Password:");
                                    String adminPassword = scanner.nextLine();
                                    
                                    
                                    if(op.changeAdmin(adminName, adminPassword)){
                                        System.out.println("Your new username is: " + adminName);
                                        System.out.println("Your new password is: " + adminPassword);
                                    }else{
                                        System.out.println("Something went wrong, please try again!");}
                                    
                                    flag = false;
                                    break;
                                    
                                case 4:
                                    System.out.println("Please insert the User ID of the target to delete: ");
                                    userID = scanner.nextInt();
                                    
                                    if(op.removeUser(userID)){
                                        System.out.println(userID + " has been successfully deleted from the database...");
                                    }else{
                                        System.out.println("Error, please check your input and try again...");
                                    }
                                    flag = false;
                                    break;
                                    
                                case 5:
                                    System.out.println("work in progress 5");
                                    hs.readUserHistory();
                                    flag = false;
                                    break;
                                    
                                case 6:
                                    // exit
                                    System.out.println("Thank you for using our system");
                                    System.out.println("Returning to main menu....");
                                    flag = false;
                                    break;

                                default:
                                    System.out.println("Wrong input, please select form the menu choices");
                                    flag = false;

                            }
                            }else{
                            
                            System.out.println("TRY AGAIN!");
                            flag = false;
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
                        flag = false;
                
                }
 
            }while(!flag);

//        }else{
//            // there is an issue connecting to the db or creating the schema
//            System.out.println("There was a problem creating or connecting to the db... \n Please check db credentials");
//        }
    }
}


