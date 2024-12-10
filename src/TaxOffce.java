
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
    
    // the main method contains the menu the users will interact with
    // and serve as a connection of all the classes
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    
        // variables
        int choice;
        // menu choice variable that will store the user inputs
        boolean flag = true;
        // a boolean flag that will control our menu loop and will stop when prompted
        
        if(DatabaseSetup.setupDB() ){
            // setup the databaase using the DB_Connection information we have
            
            System.out.println("Database has been created successfully or already exists...");
        
                //instantiating the write and reader for the db
            DatabaseWriter dbw = new DatabaseWriter(); 
                // this will allow us to write to the db
            DatabaseReader dbr = new DatabaseReader(); 
                // this will allow us to read from the db
            UserLogin ul = new UserLogin(); 
                // this will allow the users to login
            Scanner scanner = new Scanner(System.in); 
                // this will capture the user input
            AdminLogin al = new AdminLogin(); 
                // this will allow the admin to login to the system
            Operation op= new Operation(); 
                // instantiating the operations that the users will need
            History hs = new History();
                // this will allow us to track the changes the users make in our system
            
            do{ // do while loop that does not stop as long as the boolean check is false
                
                // we need a couple of print statements to present to the user
                System.out.println("\nPlease select from the following options: \n");
                System.out.println("1. Login as User");
                System.out.println("2. Register as User");
                System.out.println("3. Login as Admin");
                System.out.println("4. Exit");
                System.out.println("\nEnter your choice: \n");

                
                // capture the user choice 
                choice = regexvalidation.askForMenu();
                
                // take in the user choice and provide functionality according to the request
                
                switch(choice){
                    
                    case 1:
                        
                        if (ul.UserLoginAction()){
                            // user login and checking their credentials with our database
                        
                            // the below will print out their user information automatically
                            // they can continue if they want to change any of the information
                            System.out.println("Please find below your user  information:");
                            ArrayList<User> userData = ul.getUserData();
                            System.out.printf("%-5s | %-10s | %-10s | %-12s | %-25s | %-15s | %-20s", "\nID", "First Name", "Last Name", "Birth Date", "Email", "Password", "Phone Number");
                                        System.out.println("\n----------------------------------------------------------------------------------------");
                                        // print out the user data
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
                            
                            // once logged in the users can choose from the following options            
                            System.out.println("\n1. Change own information");
                            System.out.println("2. Enter the tax calculator");
                            System.out.println("3. Exit");
                            System.out.println("\nEnter your choice: \n");
                            
                            choice = regexvalidation.askForMenu();
                            // regex the input so we only accept their integer inputs
                            
                            switch(choice){
                                case 1:
                                    //change the user's own information
                                    System.out.println("Change own user data");                            
                                    String firstName = regexvalidation.askForFirstName(); 
                                        // first name of the user with regex validation                                    
                                    String lastName = regexvalidation.askForSecondName();
                                        // last name of the user with regex validation                                    
                                    String birthDate = regexvalidation.askForYear() +"-"+ regexvalidation.askForMonth() +"-"+ regexvalidation.askForDay();
                                        // gather the date of birth input from user with regex validation                                    
                                    String email = ul.getEmail(); 
                                        // we know the email for the user already, 
                                        // and it should not be changed because it is the username that is used for login
                                        // but it can be changed by the admin                                    
                                    String password = regexvalidation.askForPassword();
                                        // password input from user with validation                                    
                                    int phoneNumber = regexvalidation.askForPhoneNumber();
                                        // phone number input from user with validation
                                    
                                    // collect all the user input
                                    User changeUser = new User(firstName,lastName, birthDate, email, phoneNumber, password);
                                    // try adding the patient record 
                                    // check if successful otherwise let the user know
                                    if(op.changeUser(changeUser)){
                                        System.out.println("User information updated successfully");
                                        hs.addUserHistory("user_data", "CHANGE USER DATA - Name: " + firstName +" "+ lastName + " DOB: " + birthDate + " Email: "+email + " PN: "+phoneNumber + " PWD: "+password);
                                        // adding the details from the change to our arraylist for the admin to print and view
                                        flag = false;
                                    }else{
                                        System.out.println("Unable to add user to table, please check all inputs, try again");
                                        // if unsuccessfull try again
                                        choice = regexvalidation.askForMenu();
                                        flag = false;
                                    }
                                    break;
                                    
                                case 2:
                                    // this is our tax calculator
                                    // it asks the user for their annual income and displays their appropriate tax
                                    // we do not save any of the income and tax information as it should never be saved
                                    System.out.println("Please write your annual income: ");
                                    double income = regexvalidation.askForIncome();
                                    // prompting for income with regex validation
                                    taxcalculator tax = new taxcalculator();// calling the calculator
                                    System.out.println("~");
                                    System.out.println("Your USC (Universal Social Charge) is: "+ tax.getcalculatePRSI(income));
                                    System.out.println("Your PRSI (Pay-Related Social Insurance) is: " + tax.getcalculateUSC(income));
                                    System.out.println("~");
                                    flag = false;
                                    break;

                                case 3: 
                                    // when the user chooses to exit the user credentials we return to the main menu
                                    System.out.println("Thank you for using our system");
                                    System.out.println("Returning to the Main Menu....");
                                    flag = false;
                                    break;
                                    
                                default:
                                    // default in case the user puts the wrong number input
                                    System.out.println("Wrong input, please select form the menu choices");
                                    flag = false;

                            }
                                                
                        }else{
                            
                            System.out.println("Invalid!!!!");
                            flag = false;
                        }
                                     
                        break;
                        
                    
                    case 2: 
                        // register as a new user
                        // the user ID is auto incremented and not required to be input
                        String firstName = regexvalidation.askForFirstName();// first name of the user with regex
                        String lastName = regexvalidation.askForSecondName(); // last name with regex
                        String birthDate = regexvalidation.askForYear() +"-"+ regexvalidation.askForMonth() +"-"+ regexvalidation.askForDay(); // birth date with regex
                        String email = regexvalidation.askForEmail(); // email with regex
                        int phoneNumber = regexvalidation.askForPhoneNumber(); // phone number with regex
                        String password = regexvalidation.askForPassword(); // password with regex
                        
                        User newUser = new User(firstName,lastName, birthDate, email, phoneNumber, password);
                        // add the stored new user information to the database
                        // by using database writer
                        if(dbw.addUser(newUser)){
                            System.out.println("User added successfully");
                            hs.addUserHistory("user_data", "INSERT NEW USER - Name: " + firstName +" "+ lastName + " DOB: " + birthDate + " Email: "+email + " PN: "+phoneNumber + " PWD: "+password);
                            // add the changes to the user history arraylist 
                            flag = false;
                        }else{
                            System.out.println("Unable to add user to table, please check all inputs, try again");
                            choice = regexvalidation.askForMenu();
                            flag = false;
                        } 
                        break;
                        
                    case 3:
                        // admin section of the system
                        if(al.AdminLoginAction()){
                        // calling admin login action method from the instantiated admin login class
                        System.out.println("\nWelcome to the admin menu, please choose from below:\n");
                        System.out.println("1. List all users and their information");
                        System.out.println("2. Change user information");
                        System.out.println("3. Change admin information");
                        System.out.println("4. Remove a user from the system");
                        System.out.println("5. Review the database history");
                        System.out.println("6. Exit");
                        System.out.println("\nEnter your choice: \n");
                        // once logged in successfully present the options
                        choice = regexvalidation.askForMenu();
                        // get the menu choice input with regex validation
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
                                    // print out all the user records in to the table
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
                                // user id variable
                                System.out.println("\nChanging user data...Please stand by");
                                System.out.println("Please enter the USER ID of the target user:");
                                userID = regexvalidation.askForMenu();
                                //store the user id to be changed
                                System.out.println("Enter user data for user ID-" + userID);                                
                                System.out.println("Please enter the new First Name for user ID-" + userID);
                                firstName = regexvalidation.askForFirstName(); // new first name of the user
                                System.out.println("Please enter the new Last Name for user ID-" + userID);
                                lastName = regexvalidation.askForSecondName(); // new last name of user
                                System.out.println("Please enter the new Birthdate for user ID-" + userID);
                                System.out.println("Birthdate: YYYY-MM-DD format"); // new birthdate of user
                                birthDate = regexvalidation.askForYear() +"-"+ regexvalidation.askForMonth() +"-"+ regexvalidation.askForDay();
                                System.out.println("Please enter the new Email for user ID-" + userID);
                                email = regexvalidation.askForEmail(); // new email of user
                                System.out.println("Please enter the new Password for user ID-" + userID);
                                password = regexvalidation.askForPassword(); // new password of user
                                System.out.println("Please enter the new Phone Number for user ID-" + userID);
                                phoneNumber = regexvalidation.askForPhoneNumber(); // new phone numebr of user

                                User userChange = new User(firstName,lastName, birthDate, email, phoneNumber, password);
                                // store the new user 
                                if(op.changeUserByAdmin(userChange, userID)){
                                    // if the change of user information operation is successfull
                                    hs.addUserHistory("user_data", "CHANGE USER DATA BY ADMIN - Name: " + firstName +" "+ lastName + " DOB: " + birthDate + " Email: "+email + " PN: "+phoneNumber + " PWD: "+password);
                                    // add the changes to the user history arraylist
                                    System.out.println("User information updated successfully");
                                    flag = false;
                                }else{
                                    // else try again
                                    System.out.println("Unable to add user to table, please check all inputs, try again");
                                    choice = regexvalidation.askForMenu();
                                    flag = false;
                                }




                                flag = false;
                                break;

                            case 3:
                                // this will let the admin change their own login name and password
                                System.out.println("Please insert the new Admin Username:");
                                String adminName = regexvalidation.askForAdminLogin();
                                // new admin login
                                System.out.println("Please insert the new Admin Password:");
                                String adminPassword = regexvalidation.askForAdminLogin();
                                // new admin password
                                if(op.changeAdmin(adminName, adminPassword)){
                                    System.out.println("Your new username is: " + adminName);
                                    System.out.println("Your new password is: " + adminPassword);
                                    // print out new admin credentials
                                    hs.addUserHistory("admin_data", "CHANGE ADMIN DATA - Name: " +adminName + " Password: " + adminPassword);
                                    // and store the changes
                                }else{
                                    System.out.println("Something went wrong, please try again!");}
                                flag = false;
                                break;

                            case 4:
                                // this will let us delete a user from the database by their ID
                                System.out.println("Please insert the User ID of the target to delete: ");
                                userID = regexvalidation.askForMenu();
                                // ask for user id input with regex validation
                                if(op.removeUser(userID)){
                                    // if successfull
                                    System.out.println(userID + " has been successfully deleted from the database...");
                                    hs.addUserHistory("user_data", "DELETE USER FROM DATABASE BY ADMIN -- USER ID: " + userID);
                                    // add to the user changes history
                                }else{
                                    System.out.println("Error, please check your input and try again...");
                                }
                                flag = false;
                                break;

                            case 5:
                                // this method will print out all the changes made to the user_data and admin_data tables by our system
                                hs.readUserHistory();
                                // print out the user changes history
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
                    scanner.close(); 
                    // close the scanner so our system can not be used by malicous individuals
                    return;

                default:
                    System.out.println("Wrong input, please select form the menu choices");
                    flag = false;

            }

        }while(!flag);
            // while flag loop to return to the menu after every change

    }else{
            // when there is a problem connecting to the database
        System.out.println("There was a problem creating or connecting to the db... \n Please check db credentials");
    }
}
}


