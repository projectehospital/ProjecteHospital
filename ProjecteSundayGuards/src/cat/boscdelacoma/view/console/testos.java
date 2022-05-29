/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.GuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCCategoriaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTornDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCUnitatDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.MYSQLConnection;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
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
        try {
            var guardia = new JDBCGuardiaDAO();
//            var categoria = new JDBCCategoriaDAO();
//            var torn = new JDBCTornDAO();
//            var unitat = new JDBCUnitatDAO();
//            var c = new Categoria();
//            var t = new Torn();
//            var u = new Unitat();
//            c = categoria.get(1);
//            t = torn.get(1);
//            u = unitat.get(1);
//            var g = new Guardia(4, LocalDate.now(), u, t, c, (short)4);
//            guardia.add(g);
//            List llistaGuardies = new ArrayList<Guardia>();
//            llistaGuardies = guardia.getAll();
            var gu = new Guardia();
            gu = guardia.get(1);
            System.out.println(gu.getId() + " " + gu.getDia() + " " + gu.getUnitat().getTipusUnitat() + " " + gu.getTorn().getTipusTorn() + " " + gu.getCategoria().getTipusCategoria() + " " + gu.getPlacesDisponibles());
//        for (int i = 0; i < llistaGuardies.size(); i++) {
//            gu = (Guardia)llistaGuardies.get(i);
//            System.out.println(gu.getId() + " " + gu.getDia() + " " + gu.getUnitat() + " " + gu.getTorn() + " " + gu.getCategoria() + " " + gu.getPlacesDisponibles());
//        }
        } catch (DAOException e) {
            System.out.println("Error:" + e.getMessage());
        }

    }
}
