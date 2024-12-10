
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author telmuun
 */

public class History {
    // History class that will format our user changes and store them in a collection to be printed when requested by the admin
    
    // variables
    private int taskID;
    private String tableName;
    private String timeStamp = new SimpleDateFormat("yyyy/MM/dd--HH:mm:ss").format(new java.util.Date());
    // timestamp variables that will record when exactly the change was made.
    private String details;
    private int currentTaskID =1;
    // current task to keep track of our task ID and increment them when required
    
    
    public History(){
        // constructor to be used in the main method
        this.taskID = currentTaskID;
        this.tableName = tableName;
        this.timeStamp = new SimpleDateFormat("yyyy/MM/dd--HH:mm:ss").format(new java.util.Date());
        this.details = details;
        currentTaskID++;
    }
        // a second constructor to be used in the collection and store the table name and details data
    public History(String tableName, String details) {
        this.taskID = currentTaskID;
        this.tableName = tableName;
        this.timeStamp = new SimpleDateFormat("yyyy/MM/dd--HH:mm:ss").format(new java.util.Date());
        this.details = details;
        currentTaskID++;
        
    }

    
    // getters to get the variable information when required.
    public int getTaskID() {
        return taskID;
        
    }

    public String getTableName() {
        return tableName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getDetails() {
        return details;
    }
    
    
    
    // an arraylist that will store the user changes and to be printed and promted by the admin
    public ArrayList<History> userHistory = new ArrayList<>();
    
    // a method to add history data to the arraylist
    public void addUserHistory(String tableName, String details){
        
        History history = new History(tableName, details);
        
        userHistory.add(history);
        
        currentTaskID++;
        
    }
    
    // a method to print out the stored used changes made while the program was running
    public void readUserHistory(){
    
        if(userHistory.isEmpty()){
            // if empty print out that its empty
            System.out.println("No data was found");

        }else{
            System.out.printf("%-5s | %-10s | %-25s | %-40s", "\nID", "Table Name", "Time of change", "Details");
            System.out.println("\n----------------------------------------------------------------------------------------");
            // print the history information according to the specified format
            // and iterate through all the information in the arraylist
            for(History history: userHistory){
                System.out.printf("%-5s | %-10s | %-25s | %-40s\n", 
                        history.getTaskID(),
                        history.getTableName(),
                        history.getTimeStamp(),
                        history.getDetails()                     
                        
                );
            }   
        }
    
    
    
    }
}
