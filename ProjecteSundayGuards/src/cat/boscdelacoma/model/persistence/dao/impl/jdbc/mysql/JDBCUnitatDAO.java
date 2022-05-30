/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.UnitatDAO;
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
public class JDBCUnitatDAO implements UnitatDAO {

    @Override
    public Unitat get(long id) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from unitat where id=?");
            query.setLong(1, id);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Unitat u = new Unitat();
                u.setTipusUnitat(resultat.getString("tipus_unitat"));
                u.setId(resultat.getLong("id"));
                return u;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }
    
    public Unitat getPerNom(String unitat) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from unitat where tipus_unitat=?");
            query.setString(1, unitat);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Unitat u = new Unitat();
                u.setTipusUnitat(resultat.getString("tipus_unitat"));
                u.setId(resultat.getLong("id"));
                return u;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtenir unitat per nom");
            throw new DAOException();
        }
    }

    @Override
    public ArrayList<Unitat> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from unitat");
            ArrayList<Unitat> list = new ArrayList<>();
            while (resultat.next()) {
                list.add(new Unitat(resultat.getLong("id"), resultat.getString("tipus_unitat")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }

    @Override
    public void delete(Unitat u) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from unitat where id=?");
            query.setLong(1, u.getId());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }

    @Override
    public void add(Unitat u) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into unitat VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            query.setLong(1, u.getId());
            query.setString(2, u.getTipusUnitat());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                u.setTipusUnitat(rst.getString("tipus_unitat"));
                u.setId(rst.getLong("id"));
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void update(Unitat u) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update unitat set tipus_unitat=? where id=?");
            query.setString(1, u.getTipusUnitat());
            query.setLong(2, u.getId());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

}
