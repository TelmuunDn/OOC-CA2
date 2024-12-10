/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author telmuun
 */
public class User {
    // user class that contains all the appropriate attributes, constructors and getters and setters.
    
    // attributes
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private int phoneNumber;
    private int grossIncome;
    private int userID;
    private String password;
        
    // the first constructor is to construct a user without using inputing their user ID
    public User(String firstName, String lastName, String birthDate, String email, int phoneNumber, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        
        
        // print an appropriate message containing the user information
        System.out.println("\n" + firstName +" "+ lastName + " has a birthday on " + birthDate);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Password: " + password);
        
    }
    // the second constructor inclued the user ID when we want to manually set the user ID to our desired value
    public User(String firstName, String lastName, String birthDate, String email, int phoneNumber, String password, int userID){
        
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
