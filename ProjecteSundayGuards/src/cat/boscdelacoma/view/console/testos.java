/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Rol;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.CategoriaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCCategoriaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCRolDAO;
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

        try{
        var rol = new JDBCRolDAO();
        var u = new Rol();
        u.setId(3);
        u.setTipusRol("Jefe");
        rol.add(u);
        List llistaRols = new ArrayList<Rol>();
        llistaRols = rol.getAll();
        var t = new Rol();
//        t = categoria.get(3);
//        System.out.println(t.getId() + " " + t.getTipusCategoria());
        for (int i = 0; i < llistaRols.size(); i++) {
            t = (Rol)llistaRols.get(i);
            System.out.println(t.getId() + " " + t.getTipusRol());
        }
        }catch(DAOException e){
            System.out.println("Error:" + e);
        }

    }

}
