/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.Rol;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Treballador;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCCategoriaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCCategoriaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCRolDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTornDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTornDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTreballadorDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCUnitatDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCUnitatDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victo
 */
public class testingManual {

    public static void main(String[] args) throws DAOException {
        System.out.println("prova");
        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
        JDBCUnitatDAO unitat = new JDBCUnitatDAO();
        JDBCTornDAO torn = new JDBCTornDAO();
        JDBCCategoriaDAO categoria = new JDBCCategoriaDAO();
        Unitat u = unitat.get(2);
        Torn t = torn.get(2);
        LocalDate ld = LocalDate.now();
        Categoria c = categoria.get(1);
        Guardia gu = new Guardia();
//        gu.setId(2);
//        gu.setDia(ld);
//        gu.setUnitat(u);
//        gu.setTorn(t);
//        gu.setCategoria(c);
//        gu.setPlacesDisponibles((short)4);
//        //instance.add(gu);
//        //instance.update(gu);
//        instance.delete(gu);

        JDBCRolDAO rol = new JDBCRolDAO();
        Rol r = rol.get(1);
        LocalDate dn = LocalDate.of(1990, 1, 8);
        var tr = new JDBCTreballadorDAO();
        var treb = new Treballador();
        treb.setId(12);
        treb.setDni("23456212W");
        treb.setNom("Pepeta");
        treb.setDataNaixement(dn);
        treb.setPasswd("Patata123");
        treb.setEsCapDeUnitat(0);
        treb.setGuardiesFetes(0);
        treb.setGuardiesPrevistes(0);
        treb.setTipusContracte("Indefinit");
        treb.setCategoriaTreballador(c);
        treb.setRolTreballador(r);
        //tr.add(treb);
        tr.cancelarGuardia(1, 1);
        //System.out.println("afegit");
        //tr.reservarGuardia(1, 1);
        ArrayList<Treballador> at = instance.obtenirLlistaTreballadors(1);
        Guardia g = new Guardia();
        var tre = new Treballador();
        for (int i = 0; i < at.size(); i++) {
            tre = at.get(i);
            System.out.println(tre.getId() + " " + tre.getDni() + " " + tre.getNom() + " " + tre.getDataNaixement() + " " + tre.getPasswd() + " " + tre.getGuardiesFetes() + " " + tre.getGuardiesPrevistes() + " " + tre.getEsCapDeUnitat() + " " + tre.getTipusContracte() +  " "/* + tre.getCategoriaTreballador().getTipusCategoria() + " " */+ tre.getRolTreballador().getTipusRol());
        }
//        ArrayList<Guardia> result = instance.getAll();
//        for (int i = 0; i < result.size(); i++) {
//            //g = result.get(i);
//            g = instance.getPerDades(ld, u, t, c);
//            //g = instance.getPerData(ld, c);
//            System.out.println(g.getId() + " " + g.getDia() + " " + g.getUnitat().getTipusUnitat() + " " + g.getTorn().getTipusTorn() + " " + g.getCategoria().getTipusCategoria() + " " + g.getPlacesDisponibles());
//        }
    }
}
