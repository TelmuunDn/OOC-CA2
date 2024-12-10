
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
    private int taskID;
    private String tableName;
    private String timeStamp = new SimpleDateFormat("yyyy/MM/dd--HH:mm:ss").format(new java.util.Date());
    private String details;
    private int currentTaskID =1;
    
    public History(){}

    public History(String tableName, String details) {
        this.taskID = currentTaskID;
        this.tableName = tableName;
        this.timeStamp = new SimpleDateFormat("yyyy/MM/dd--HH:mm:ss").format(new java.util.Date());
        this.details = details;
        
        
    }

    public int getTaskID() {
        return taskID++;
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
    
    
    
    
    public ArrayList<History> userHistory = new ArrayList<>();
    
    
    public void addUserHistory(String tableName, String details){
        
        History history = new History(tableName, details);
        
        userHistory.add(history);
        
        currentTaskID++;
        
    }
    
    
    public void readUserHistory(){
    
        if(userHistory.isEmpty()){
            System.out.println("No data was found");

        }else{
            System.out.printf("%-5s | %-10s | %-25s | %-40s", "\nID", "Table Name", "Time of change", "Details");
            System.out.println("\n----------------------------------------------------------------------------------------");
            // print out all the patient records in to the table
            // this will require iterating  therough the patients arraylist
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
