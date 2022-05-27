/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTornDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCUnitatDAO;
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

        JDBCUnitatDAO unitat = new JDBCUnitatDAO();
        Unitat u = new Unitat();
        u.setId(6);
        u.setTipusUnitat("Unitat 7");
        unitat.delete(u);
        List llistaUnitats = new ArrayList<Unitat>();
        llistaUnitats = unitat.getAll();
        Unitat t = new Unitat();
        for (int i = 0; i < llistaUnitats.size(); i++) {
            t = (Unitat)llistaUnitats.get(i);
            System.out.println(t.getId() + " " + t.getTipusUnitat());
        }

    }

}
