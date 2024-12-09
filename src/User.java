/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author telmuun
 */
public class User {
    // this user information will utilized by te system to add or retreive patient data from the database.
    
    // attributes
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private int phoneNumber;
    private int grossIncome;
    private int userID;
    private String password;
    
    // static attribute
    // static attributes are a class variable
    // it will be shared by all the objects within the class
    
    
    // create 2 constructors
    // one constructor will be the user data
    public User(String firstName, String lastName, String birthDate, String email, int phoneNumber, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        
        
        
        System.out.println("\n" + firstName +" "+ lastName + " has a birthday on " + birthDate);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Password: " + password);
        
    }
    // constructor 2 will be when adding a new user to the database
    public User(String firstName, String lastName, String birthDate, String email, int phoneNumber, String password, int userID){
        // add a userwith and an ID
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userID = userID;
        
        
    }
    
    
    // getters and setters

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    
    public int getUserID() {
        return userID;
    }

    

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

   

    public int getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
}
