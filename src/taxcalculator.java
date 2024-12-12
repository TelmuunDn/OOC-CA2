/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author felip
 */
public class taxcalculator {
    
    // This class is only for calculate the USC and PRSI from the income
    // that the user write
    
    //Income tax/PAYE, 
    //USC  (Universal Social Charge) 
    // PRSI  (Pay-Related Social Insurance) 
    
    public double getcalculateUSC(double annualIncome) { // USC CLASS 
        
        // USC Calculation
            //€12,012 × 0.5% = €60.06
            //(€21,295 - €12,012) × 2% = €
            //(€70,044 - €21,295) × 4.5% = €
            // Over 70,044 * 8%
            //Total USC: €1,536.85/year.

        // Depend of the class of income, this calculator will use a diferent formula
        // The validation of only numers is done in the regexvalidation class 
        double usc = 0;
        int classA = 12012;
        int classB = 9283;
        int classC = 48749;
    
        if(12012 >= annualIncome){ // if the annual income is under or equal at 12,012
            usc = classA *0.005;

        }else if ((12012 < annualIncome) && (21295 >= annualIncome)){  // if the annual income is over 12,012 and less than 21,295
            
            usc = classA * 0.005 + classB * 0.02;
              
        }else if ((21295 < annualIncome) && (70044 >= annualIncome)){ // if the annual income is over 21,295 and less and equal than 70044
            
            usc = classA * 0.005 + classB * 0.02 + (annualIncome - 21295) * 0.045;
            
        }else if(70044 < annualIncome){ // if the annual income is over 21,295 and less and equal than 70044
            
            usc = classA * 0.005 + classB * 0.02 + classC * 0.045 + (annualIncome - 70044) * 0.08; 
        }
        
      return usc;
      
    }
    
    public double getcalculatePRSI(double annualIncome) { // PRSI CLASS 

       //PRSI
           //Weekly salary = €50,000 ÷ 52 = €961.54
           //PRSI = €961.54 × 4% = €38.46/week.
           //Total PRSI: €38.46 × 52 = €2,000/year.

        double PRSI;
        double weeklyIncome = annualIncome / 52; // first we have to transform to a weekly income

        if (weeklyIncome > 353){ // if the weekly income is over than 353 the user has to pay 
              PRSI = annualIncome * 0.04;
 
        }else{ // if is not, the user don't have to pay
            PRSI = 0;

        }

        return PRSI;
    }
     
    
}
