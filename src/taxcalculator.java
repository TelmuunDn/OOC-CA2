/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author felip
 */
public class taxcalculator {

    // Total reductions (usc + prsi)
    
    public double calculateUSC(double annualIncome) {
        
        //Income tax/PAYE, 
        //USC  (Universal Social Charge) 
        // PRSI  (Pay-Related Social Insurance) 

        // USC 
            //€12,012 × 0.5% = €60.06
            //(€21,295 - €12,012) × 2% = €
            //(€70,044 - €21,295) × 4.5% = €
            // Over 70,044 * 8%
            //Total USC: €1,536.85/year.

        double usc = 0;
        int classA = 12012;
        int classB = 9283;
        int classC = 48749;
    
        if(12012 >= annualIncome){
            usc = classA *0.005;
            
        
        }else if ((12012 < annualIncome) && (21295 >= annualIncome)){
            
            usc = classA * 0.005 + classB * 0.02;
              
        }else if ((21295 < annualIncome) && (70044 >= annualIncome)){
            
            usc = classA * 0.005 + classB * 0.02 + (annualIncome - 21295) * 0.045;
            
        }else if(70044 < annualIncome){
            
            usc = classA * 0.005 + classB * 0.02 + classC * 0.045 + (annualIncome - 70044) * 0.08;
        }else{
            ///
        }
        
 
      return usc;
    }
 
     public double calculatePRSI(double annualIncome) {
          
        //PRSI
            //Weekly salary = €50,000 ÷ 52 = €961.54
            //PRSI = €961.54 × 4% = €38.46/week.
            //Total PRSI: €38.46 × 52 = €2,000/year.
      
       double PRSI;
       double weeklyIncome = annualIncome / 52;
       
       if (weeklyIncome > 353){
           PRSI = annualIncome * 0.04;
           
       }else{
           PRSI = 0;
       
       }
         
         return PRSI;
     }
    
}
