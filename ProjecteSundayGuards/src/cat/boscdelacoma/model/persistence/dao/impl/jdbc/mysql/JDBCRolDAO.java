/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Rol;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.RolDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victo
 */
public class JDBCRolDAO implements RolDAO{

    @Override
    public Rol get(long id) throws DAOException {
         try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from rol where id=?");
            query.setLong(1, id);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Rol r = new Rol();
                r.setTipusRol(resultat.getString("tipus_rol"));
                r.setId(resultat.getLong("id"));
                return r;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public ArrayList<Rol> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from rol");
            ArrayList<Rol> list = new ArrayList<>();
            while (resultat.next()) {
                list.add(new Rol(resultat.getLong("id"), resultat.getString("tipus_rol")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void delete(Rol t) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from rol where id=?");
            query.setLong(1, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }

    @Override
    public void add(Rol t) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into rol(id, tipus_rol) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            query.setLong(1, t.getId());
            query.setString(2, t.getTipusRol());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                t.setId(rst.getLong("id"));
                t.setTipusRol(rst.getString("tipus_rol"));
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void update(Rol t) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update rol set tipus_rol=? where id=?");
            query.setString(1, t.getTipusRol());
            query.setLong(2, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

    public Rol getPerNom(String s) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from rol where tipus_rol=?");
            query.setString(1, s);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Rol r = new Rol();
                r.setTipusRol(resultat.getString("tipus_rol"));
                r.setId(resultat.getLong("id"));
                return r;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }
    
}
