/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTornDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.MYSQLConnection;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author osama
 */
public class testos {


    public static void main(String[] args) {
        
             MYSQLConnection con = MYSQLConnection.getInstance();
             System.out.println(con.getConnection());
        
             JDBCTornDAO torns = new JDBCTornDAO();
             
             List llistaTorns = new ArrayList<Torn>();
        try {
            llistaTorns = torns.getAll();
            
            for (int i = 0; i < llistaTorns.size(); i++) {
                
                    System.out.println(llistaTorns.get(i).toString());
                
            }
            
        } catch (DAOException ex) {
            System.out.println("error:" + ex);
        }
         
    }
    
}
