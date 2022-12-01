/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poly.nhom4.view;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
        System.out.println(date);
    }
    
}
