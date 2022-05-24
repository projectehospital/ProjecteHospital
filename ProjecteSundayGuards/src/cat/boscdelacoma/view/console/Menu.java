/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.MYSQLConnection;

/**
 *
 * @author Victo
 */
public class Menu {
    public static void main(String[] args) {
        
        MYSQLConnection con = MYSQLConnection.getInstance();
        System.out.println(con.getConnection());
        
        
        
    }
    
    
    
    
    
}
