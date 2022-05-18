/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.view.console;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Victo
 */
public class Menu {
    static Scanner entrada;
    public static void MenuInicial(){
        System.out.println("Tria el número del mes vols reservar una guàrdia:");
        System.out.println("1. Gener");
        System.out.println("2. Febrer");
        System.out.println("3. Març");
        System.out.println("4. Abril");
        System.out.println("5. Maig");
        System.out.println("6. Juny");
        System.out.println("7. Juliol");
        System.out.println("8. Agost");
        System.out.println("9. Setembre");
        System.out.println("10. Octubre");
        System.out.println("11. Novembre");
        System.out.println("12. Desembre");
        
        entrada = new Scanner(System.in);
        int mes = entrada.nextInt();
        LocalDate d = new LocalDate();
        
        while(mes == d.getMonthValue()){
        
        }
//        switch(mes){
//            case 1:
//                
//                break;
//            case 2:
//                
//                break;
//            case 3:
//                
//                break;
//            case 4:
//                
//                break;
//            case 5:
//                
//                break;
//            case 6:
//                
//                break;
//            case 7:
//                
//                break;
//            case 8:
//                
//                break;
//            case 9:
//                
//                break;
//            case 10:
//                
//                break;
//            case 11:
//                
//                break;
//            case 12:
//                
//                break;
//            default:
//                System.out.println("Tria un mes de l'1 al 12");
//                break;
        }
    }
}
