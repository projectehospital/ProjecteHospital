/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Unitat;
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
public class JDBCGuardiaDAO {

    @Override
    public List<Guardia> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from guardia");
            List<Guardia> list = new ArrayList<>();
            Torn t = new Torn();
            Categoria c = new Categoria();
            Unitat u = new Unitat();
            while (resultat.next()) {
                list.add(new Guardia(resultat.getLong("id"), convertToLocalDateViaInstant(resultat.getDate("dia")), resultat.getString("tipus_unitat")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }

    @Override
    public Guardia get(long id) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from hospitalProva.guardia where id=?");
            query.setLong(1, id);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Guardia g = new Guardia();
                Torn t = new Torn();
                Categoria c = new Categoria();
                Unitat u = new Unitat();
                g.setId(resultat.getLong("id"));
                g.setDia(convertToLocalDateViaInstant(resultat.getDate("dia")));
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                //Fem un objecte Unitat per poder accedir a unitat a Guardia
                u.setTipusUnitat(resultat.getString("tipus_unitat"));
                g.setUnitat(u);
                //Fem un objecte Torn per poder accedir a torn a Guardia
                t.setTipusTorn(resultat.getString("tipus_torn"));
                g.setTorn(t);
                //Fem un objecte Categoria per poder accedir a categoria a Guardia
                c.setTipusCategoria(resultat.getString("tipus_categoria"));
                g.setCategroia(c);
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

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into hospitalProva.guardia(id, dia, tipus_unitat, tipus_torn, tipus_categoria, places_disponibles) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            query.setLong(1, g.getId());
            query.setDate(1, (java.sql.Date) convertToDateViaSqlDate(g.getDia()));
            query.setLong(1, g.getId());
            query.setLong(1, g.getId());
            query.setLong(1, g.getId());
            query.setLong(1, g.getId());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                t.setId(rst.getShort(1));
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void update(Guardia g) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update hospitalProva.guardia set tipus_torn=? where id=?");
            query.setString(1, t.getTipusTorn());
            query.setShort(2, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

    @Override
    public void delete(Guardia g) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from hospitalProva.torn where id=?");
            query.setShort(1, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
