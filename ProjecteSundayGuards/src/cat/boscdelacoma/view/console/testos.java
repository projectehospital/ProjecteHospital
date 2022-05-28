/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTornDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.MYSQLConnection;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import static cat.boscdelacoma.view.console.Menu.entrada;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author osama
 */
public class testos {

    public static void main(String[] args) throws DAOException {
        
       
        Torn torn = triarTornAdmin();
        System.out.println(torn.getTipusTorn());
        
    }
 private static Torn triarTornAdmin() {
        Torn torn = new Torn();
        System.out.println("Torn de la guàrdia:");
        System.out.println("1. Dia");
        System.out.println("2. Nit");
        int x = entrada.nextShort();
        switch (x) {
            case 1:
                torn.setTipusTorn("Dia");
                break;
            case 2:
                torn.setTipusTorn("Nit");
                break;
            default:
                System.out.println("Tria Dia(1) o Nit(2)");
                triarTornAdmin();
        }
        
        return torn;
    }
}
