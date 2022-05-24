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
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author osama
 */
public class JDBCTornDAO implements TornDAO {

    @Override
    public Torn get(long id) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("SELECT * from hospitalProva.torn where tipus_torn=?");
            query.setLong(1, id);
            ResultSet resultat = query.executeQuery();
            
            if (resultat.next()) {
                Torn t = new Torn(resultat.getString("modalitat"));
                t.setTipusTorn(resultat.getString("tipus_torn"));
                return t;
            }else{
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public List<Torn> getAll() throws DAOException {

        try {

            Statement consulta = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = consulta.executeQuery("select * from torn");
            var torns = new ArrayList<Torn>();
            JDBCTornDAO torn = new JDBCTornDAO();
            while (resultat.next()) {

                torns.add(new Torn(resultat.getString("tipus_torn")));
            }
            return torns;
        } catch (SQLException ex) {

            throw new DAOException();

        }

    }


    @Override
    public void add(Torn t) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("INSERT INTO provaHospital.torn(tipus_torn) VALUES('Dia')", Statement.RETURN_GENERATED_KEYS);

            query.setString(1, t.getTipusTorn());
            query.executeUpdate();
            ResultSet res = query.getGeneratedKeys();
            if (res.next()) {
                t.setTipusTorn(res.getString(1));
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }

    }

  @Override
    public void update(Torn t) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("UPDATE hospitalProva.TORN SET tipus_torn=? WHERE ID=?");
            query.setString(1, t.getModalitat());
            query.setLong(2, t.getID());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

    @Override
    public void delete(Torn t) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("DELETE FROM hospiolot.TORNS WHERE ID=?");
            query.setLong(1, t.getID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }
   
        public Torn searchByName(String name) throws DAOException {
        try {

            PreparedStatement query = Connection.getInstance().getConnection().prepareStatement("SELECT id FROM hospiolot.torns WHERE modalitat=?");
            query.setString(1, name);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                return get(result.getLong("id"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException();

        }

}
