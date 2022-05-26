/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.persistence.dao.contracts.GuardiaDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Victo
 */
public class JDBCGuardiaDAO implements GuardiaDAO {

    @Override
    public List<Guardia> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from guardia");
            List<Guardia> list = new ArrayList<>();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            while (resultat.next()) {
                list.add(new Guardia(resultat.getLong("id"), deDateALocalDate(resultat.getDate("dia")), u.getFromTipusUnitat(resultat.getString("tipus_unitat")), t.getFromTipusTorn(resultat.getString("tipus_torn")), c.getFromTipusCategoria(resultat.getString("tipus_categoria")), resultat.getShort("places_disponibles")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }

    @Override
    public Guardia get(long id) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from guardia where id=?");
            query.setLong(1, id);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Guardia g = new Guardia();
                JDBCUnitatDAO u = new JDBCUnitatDAO();
                JDBCTornDAO t = new JDBCTornDAO();
                JDBCCategoriaDAO c = new JDBCCategoriaDAO();
                g.setId(resultat.getLong("id"));
                g.setDia(deDateALocalDate(resultat.getDate("dia")));
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                g.setUnitat(u.getFromTipusUnitat("tipus_unitat"));
                g.setTorn(t.getFromTipusTorn("tipus_torn"));
                g.setCategoria(c.getFromTipusCategoria("tipus_categoria"));
                return g;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void add(Guardia g) throws DAOException {

        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into guardia(id, dia, tipus_unitat, tipus_torn, tipus_categoria, places_disponibles) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            query.setLong(1, g.getId());
            query.setDate(2, (java.sql.Date) deLocalDateADate(g.getDia()));
            query.setString(3, g.getUnitat().getTipusUnitat());
            query.setString(4, g.getTorn().getTipusTorn());
            query.setString(5, g.getCategoria().getTipusCategoria());
            query.setShort(6, g.getPlacesDisponibles());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                g.setId(rst.getLong(1));
                g.setDia(deDateALocalDate(rst.getDate(2)));
                g.setUnitat(u.getFromTipusUnitat(rst.getString(3)));
                g.setTorn(t.getFromTipusTorn(rst.getString(4)));
                g.setCategoria(c.getFromTipusCategoria(rst.getString(5)));
                g.setPlacesDisponibles(rst.getShort(6));
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void update(Guardia g) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update guardia set dia=?, tipus_unitat=?, tipus_torn=?, tipus_categoria=?, places_disponibles=? where id=?");
            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            query.setDate(1, (java.sql.Date) deLocalDateADate(g.getDia()));
            query.setString(2, g.getUnitat().getTipusUnitat());
            query.setString(3, g.getTorn().getTipusTorn());
            query.setString(4, g.getCategoria().getTipusCategoria());
            query.setShort(5, g.getPlacesDisponibles());
            query.setLong(6, g.getId());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

    @Override
    public void delete(Guardia g) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from guardia where id=?");
            query.setLong(1, g.getId());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }

    public LocalDate deDateALocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date deLocalDateADate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
