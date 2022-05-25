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

    public static void main(String[] args) throws DAOException {
        try {
            MYSQLConnection con = MYSQLConnection.getInstance();
            System.out.println(con.getConnection());

            JDBCTornDAO torns = new JDBCTornDAO();
            Torn t = new Torn();
            t.setTipusTorn("Nit");
            torns.add(t);

            List llistaTorns = new ArrayList<Torn>();

            llistaTorns = torns.getAll();
<<<<<<< Updated upstream

            System.out.println(llistaTorns.toString());

=======
            
            for (int i = 0; i < llistaTorns.size(); i++) {
                
                    Torn torn = new Torn("");
                    torn = (Torn)llistaTorns.get(i);
                    System.out.println(torn.getTipusTorn());
            }
            
>>>>>>> Stashed changes
        } catch (DAOException ex) {
            System.out.println("error:" + ex);
        }

    }

}
