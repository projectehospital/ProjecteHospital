/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.exceptions;

/**
 *
 * @author osama
 */
public class DAOException extends Exception {
    
    // es pot entrar el missatge de l'error
    
    public DAOException (){}
    public DAOException (String msg) {
    
        System.out.println(msg);
    
    }
    
}
