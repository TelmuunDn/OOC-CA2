
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
    
   
    // #######################################################
    // FIRST NAME VALIDATION
    public static String askForFirstName() {
        
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
    
    // #######################################################
    // SECOND NAME VALIDATION
    public static String askForSecondName() {
        
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
      
    // #######################################################
    // PASSWORD VALIDATION
    public static String askForPassword() {
        
        Scanner myKB = new Scanner(System.in);
        
        String userPassword;
        
        do {
            //this gets repeated
            System.out.println("Please enter your password: ");
            System.out.println("~~uppercase letters, lower letters and numbers");
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
    
    // #######################################################
    // PHONE NUMBER VALIDATION 
    public static int askForPhoneNumber() {
        
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
                if(num.matches("9")){
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
          
    return userPhone;
        
    } 
    
    
    // #######################################################
    // EMAIL VALIDATION
    public static String askForEmail() {
        
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
    
    // #######################################################
    // DAY OF BIRTHDAY VALIDATION
    
    public static String askForYear() {
        
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
     
    public static String askForMonth() {
        
        
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
          
    public static String askForDay() {
        
        
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

}   

