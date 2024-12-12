
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author felip
 */
public class regexvalidation {
    
    /**
    * simple code to ask user to enter the first name
    * do not allow numbers at all
    * if any numbers entered - display an error and ask again
    * @return the user first name as String
    */
    public static String askForFirstName() { // FIRST NAME VALIDATION
        
        Scanner myKB = new Scanner(System.in);
        
        String userFirstName;
        
        do {
            //this gets repeated
            System.out.println("Please enter your first name: ");
            System.out.println("~~only letters");
            userFirstName = myKB.nextLine().toLowerCase(); //read in 1 line from keyboard

        }while (!userFirstName.matches("[a-zA-Z .,?!\"]+"));
        // at this point the loop guard is FALSE
            
        
        return userFirstName.substring(0, 1).toUpperCase() + userFirstName.substring(1);

        
    } 

    /**
    * simple code to ask user to enter the second name
    * do not allow numbers at all
    * if any numbers entered - display an error and ask again
    * @return the user second name as String
    */
    public static String askForSecondName() { // SECOND NAME VALIDATION
        
        Scanner myKB = new Scanner(System.in);
        
        String userSecondName;
        
        do {
            //this gets repeated
            System.out.println("Please enter your second name: ");
            System.out.println("~~only letters");
            userSecondName = myKB.nextLine().toLowerCase(); //read in 1 line from keyboard
            
        }while (!userSecondName.matches("[a-zA-Z .,?!\"]+"));
        // at this point the loop guard is FALSE
        
        return userSecondName.substring(0, 1).toUpperCase() + userSecondName.substring(1);
        
    } 
      
    /**
    * simple code to ask user to enter the password
    * only allows capital letters, lower case and some symbols @#$%^!&+=
    * if any the password does not match - display an error and ask again
    * @return the password as String
    */
    public static String askForPassword() { // PASSWORD VALIDATION
        
        Scanner myKB = new Scanner(System.in);
        
        String userPassword;
        
        do {
            //this gets repeated
            System.out.println("Please enter your password: ");
            System.out.println("~~At least one upper case, one lower and a special character such as !@#$%");
            System.out.println("Minimum of 8 characters required.");
            userPassword = myKB.nextLine(); //read in 1 line from keyboard
            
        }while (!userPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^!&+=]).{8,}$"));

//        Breaking down its components:
//            ^: indicates the string’s beginning
//            (?=.*[a-z]): makes sure that there is at least one small letter
//            (?=.*[A-Z]): needs at least one capital letter
//            (?=.*\\d): requires at least one digit
//            (?=.*[@#$%^&+=]): provides a guarantee of at least one special symbol
//            .{8,20}: imposes the minimum length of 8 characters and the maximum length of 20 characters
//            $: terminates the string

        
        return userPassword;
        
    } 
    
    /**
    * simple code to ask user to enter the phone number
    * do not allow letters at all, and only 9 numbers
    * if any letter entered - display an error and ask again
    * @return the user phone number as integer
    */
    public static int askForPhoneNumber() { // PHONE NUMBER VALDATION
        
        Scanner myKB = new Scanner(System.in);
        
        int userPhone = 0;
        boolean flag = true;
        
        do {
            
            try{
                //this gets repeated
                System.out.println("Please enter your phonenumber: ");
                System.out.println("~~Only 9 numbers, example 861231234");
                userPhone = myKB.nextInt();
                String num = String.valueOf(userPhone);
                if(num.length()==9){
                   userPhone = Integer.valueOf(num);
                    flag = true;

                }else{

                    System.out.println("Only nine numbers");
                    myKB.nextLine();
                    flag = false;

                }             
            }catch(Exception e){
            
                System.out.println("No letters, example 123456789");
                myKB.nextLine();
                flag = false;
            
            }

        }while(!flag);
        // at this point the loop guard is FALSE
          
        
    return userPhone;}
    
    /**
    * simple code to ask user to enter the email
    * only allow email format with @
    * if does not have the @  - display an error and ask again
    * @return the user email as String
    */
    public static String askForEmail() { // EMAIL VALIDATION
        
        Scanner myKB = new Scanner(System.in);
        
        String userEmail;
        // This code is not working properly.... has to be fixed 
        
        
        do {
            //this gets repeated
            System.out.println("Please enter your email: ");
            System.out.println("~~ example username@example.com");
            userEmail= myKB.nextLine().toLowerCase(); //read in 1 line from keyboard
            
        }while (!userEmail.matches("^\\S+@\\S+\\.\\S+$"));

        
    return userEmail;
        
    } 
    
    /**
    * simple code to ask user to enter the year of birthday
    * do not allow letters at all and only four numbers
    * if any letter or less than four numbers entered - display an error and ask again
    * @return the user year of birthday as String
    */
    public static String askForYear() { // YEAR OF BIRTHDAY VALIDATION
        
        Scanner myKB = new Scanner(System.in);
        int userYear;
        String year = "";
        boolean flag = true;
        
        do {
            
            try{
                //this gets repeated
                System.out.println("Year of your birthday: ");
                System.out.println("~~only 4 numbers, only over 18 years old.");
                userYear = myKB.nextInt();//read in 1 line from keyboard
                int length = String.valueOf(userYear).length();

                if((userYear >= 1940)&&(userYear <= 2006)&&(length == 4)){
                    year = String.valueOf(userYear);
                    flag = true;

                }else{

                    System.out.println("Only four numbers, example 1995.");
                    myKB.nextLine();
                    flag = false;

                } 
            }catch(Exception e){
            
                System.out.println("No letters, example 1999");
                myKB.nextLine();
                flag = false;
            
            }


        }while(!flag);
        // at this point the loop guard is FALSE
        
        return year;

        
    }   
    
    /**
    * simple code to ask user to enter the month of birthday
    * do not allow letter at all and only two numbers
    * if any letter entered - display an error and ask again
    * @return the user month of birthday as String
    */
    public static String askForMonth() { // MONTH OF BIRTHDAY VALIDATION
        
        
        Scanner myKB = new Scanner(System.in);
        int userMonth;
        String month = "";
        boolean flag = true;
        
        do {
            
            try{
                //this gets repeated
                System.out.println("Month of your birthday: ");
                System.out.println("~~only 2 numbers");
                userMonth = myKB.nextInt();//read in 1 line from keyboard
                int length = String.valueOf(userMonth).length();

                if((userMonth >= 1)&&(userMonth <= 12)&&(length == 2)){
                    month = String.valueOf(userMonth);
                    flag = true;

                }else{

                    System.out.println("Only months of the year, example 05");
                    myKB.nextLine();
                    flag = false;

                } 
            }catch(Exception e){
            
                System.out.println("No letters, example 12");
                myKB.nextLine();
                flag = false;
            
            }


        }while(!flag);
        // at this point the loop guard is FALSE
        
        return month;
  
    } 
    
    /**
    * simple code to ask user to enter the day of birthday
    * do not allow letters at all and only two numbers
    * if any letter entered - display an error and ask again
    * @return the user day of birthday as String
    */      
    public static String askForDay() { // DAY OF BIRTHDAY VALIDATION
        
        
        Scanner myKB = new Scanner(System.in);
        int userDay;
        String day = "";
        boolean flag = true;
        
        // array list 31 days months January , March, May, July, August, October, and December
        // array list 30 days months April, June, September, and November
        // February 28
        // February leap year 29 days
        
        
        
        do {
            
            try{
                //this gets repeated
                System.out.println("Day of your birthday: ");
                System.out.println("~~only 2 numbers");
                userDay = myKB.nextInt();//read in 1 line from keyboard
                int length = String.valueOf(userDay).length();

                if((userDay >= 1)&&(userDay <= 31)&&(length == 2)){
                    day = String.valueOf(userDay);
                    flag = true;

                }else{

                    System.out.println("Only days of the month, example 05");
                    myKB.nextLine();
                    flag = false;

                } 
            }catch(Exception e){
            
                System.out.println("No letters, example 12");
                myKB.nextLine();
                flag = false;
            
            }


        }while(!flag);
        // at this point the loop guard is FALSE
        
        return day;

    } 
    
    /**
    * simple code to ask user to enter the menu option
    * do not allow letters at all
    * if any letter or more than number 6 entered - display an error and ask again
    * @return the user menu choice as integer
    */
    public static int askForMenu() { // VALIDATION FOR MENU CHOICE NUMBER
     
        Scanner myKB = new Scanner(System.in);
        int userChoice = 0;
        boolean flag = true;
        
        do {
            
            try{
                userChoice = myKB.nextInt();
                
                if((userChoice <= 6)&&(userChoice >= 1)){
                   
                    flag = true;

                }else{

                    System.out.println("Only numbers from 1 to 6");
                    myKB.nextLine();
                    flag = false;

                } 
            }catch(Exception e){
            
                System.out.println("Only numbers allows");
                myKB.nextLine();
                flag = false;
            
            }

        }while(!flag);
        // at this point the loop guard is FALSE
        
        return userChoice;}
    
        /**
    * simple code to ask user to enter their year income
    * do not allow letters at all
    * if any letter entered - display an error and ask again
    * @return the user income as double
    */ 
    public static double askForIncome() { // VALIDATION FOR INCOME VALUE
     
        Scanner myKB = new Scanner(System.in);
        double userIncome = 0;
        boolean flag = true;
        
        do {
            
            try{
                userIncome = myKB.nextInt();
                
                if(userIncome >= 0){
                   
                    flag = true;

                }else{

                    System.out.println("Only numbers.");
                    myKB.nextLine();
                    flag = false;

                } 
            }catch(Exception e){
            
                System.out.println("Only numbers allows");
                myKB.nextLine();
                flag = false;
            
            }

        }while(!flag);
        // at this point the loop guard is FALSE
        
        return userIncome;}
    
    /**
    * simple code to ask user to enter the admin login
    * do not allow numbers at all
    * if any numbers entered - display an error and ask again
    * @return the admin login as String
    */
    public static String askForAdminLogin() { // VALIDATION FOR ADMIN LOGIN
        
        Scanner myKB = new Scanner(System.in);
        
        String adminLogin;
        
        do {
            //this gets repeated
            
            System.out.println("~~only letters");
            adminLogin = myKB.nextLine(); //read in 1 line from keyboard

        }while (!adminLogin.matches("[a-zA-Z .,?!\"]+"));
        // at this point the loop guard is FALSE
            
        
        return adminLogin;

    } 

}   

