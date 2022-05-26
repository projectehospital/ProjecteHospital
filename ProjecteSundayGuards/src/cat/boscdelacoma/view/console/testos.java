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
        MYSQLConnection con = MYSQLConnection.getInstance();
        System.out.println(con.getConnection());

        JDBCTornDAO torn = new JDBCTornDAO();
        Torn tor = new Torn();
        tor.setId(3);
        tor.setTipusTorn("Nito");
        torn.delete(tor);
        List llistaTorns = new ArrayList<Torn>();
        llistaTorns = torn.getAll();
        Torn t = new Torn();
        for (int i = 0; i < llistaTorns.size(); i++) {
            t = (Torn)llistaTorns.get(i);
            System.out.println(t.getTipusTorn() + t.getId());
        }

    }

}
