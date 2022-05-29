/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.persistence.dao.contracts.TornDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author osama
 */
public class JDBCTornDAO implements TornDAO {

    @Override
    public List<Torn> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from torn");
            List<Torn> list = new ArrayList<>();
            while (resultat.next()) {
                list.add(new Torn( resultat.getLong("id"), resultat.getString("tipus_torn")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }

    @Override
    public Torn get(long id) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from torn where id=?");
            query.setLong(1, id);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Torn t = new Torn();
                t.setTipusTorn(resultat.getString("tipus_torn"));
                t.setId(resultat.getLong("id"));
                return t;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void add(Torn t) throws DAOException {

        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into torn VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

            query.setString(1, t.getTipusTorn());
            query.setLong(2, t.getId());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                t.setTipusTorn(rst.getString("tipus_torn"));
                t.setId(rst.getLong("id"));
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void update(Torn t) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update torn set tipus_torn=? where id=?");
            query.setString(1, t.getTipusTorn());
            query.setLong(2, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

    @Override
    public void delete(Torn t) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from torn where id=?");
            query.setLong(1, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }

    public Torn getPerNom(String s) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from torn where tipus_torn=?");
            query.setString(1, s);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Torn u = new Torn();
                u.setTipusTorn(resultat.getString("tipus_torn"));
                u.setId(resultat.getLong("id"));
                return u;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }
}
