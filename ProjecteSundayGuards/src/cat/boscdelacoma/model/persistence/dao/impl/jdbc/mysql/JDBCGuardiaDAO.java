/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.GuardiaDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

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
            LocalDate ld = resultat.getDate("dia").toLocalDate();
            while (resultat.next()) {
                list.add(new Guardia(resultat.getLong("id"), ld, u.getPerString(resultat.getString("tipus_unitat")), t.getPerString(resultat.getString("tipus_torn")), c.getPerString(resultat.getString("tipus_categoria")), resultat.getShort("places_disponibles")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }

  
    public Guardia getPerData(LocalDate dia) throws DAOException{
        
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from guardia where dia=?");
            
            query.setDate(1,Date.valueOf(dia));
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Guardia g = new Guardia();
                Torn t = new Torn();
                Categoria c = new Categoria();
                Unitat u = new Unitat();
                LocalDate ld = resultat.getDate("dia").toLocalDate();
                g.setId(resultat.getLong("id"));
                g.setDia(ld);
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                //Fem un objecte Unitat per poder accedir a unitat a Guardia
                u.setTipusUnitat(resultat.getString("tipus_unitat"));
                g.setUnitat(u);
                //Fem un objecte Torn per poder accedir a torn a Guardia
                t.setTipusTorn(resultat.getString("tipus_torn"));
                g.setTorn(t);
                //Fem un objecte Categoria per poder accedir a categoria a Guardia
                c.setTipusCategoria(resultat.getString("tipus_categoria"));
                g.setCategoria(c);
                return g;
            } else {
                return null;
            }
  
        }catch(SQLException ex){
            
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
                LocalDate ld = resultat.getDate("dia").toLocalDate();
                Guardia g = new Guardia();
                JDBCUnitatDAO u = new JDBCUnitatDAO();
                JDBCTornDAO t = new JDBCTornDAO();
                JDBCCategoriaDAO c = new JDBCCategoriaDAO();
                g.setId(resultat.getLong("id"));
                g.setDia(ld);
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                g.setUnitat(u.getPerString("tipus_unitat"));
                g.setTorn(t.getPerString("tipus_torn"));
                g.setCategoria(c.getPerString("tipus_categoria"));
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
            query.setDate(2, Date.valueOf(g.getDia()));
            query.setString(3, g.getUnitat().getTipusUnitat());
            query.setString(4, g.getTorn().getTipusTorn());
            query.setString(5, g.getCategoria().getTipusCategoria());
            query.setShort(6, g.getPlacesDisponibles());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                LocalDate ld = rst.getDate("dia").toLocalDate();
                g.setId(rst.getLong("id"));
                g.setDia(ld);
                g.setUnitat(u.getPerString(rst.getString("tipus_unitat")));
                g.setTorn(t.getPerString(rst.getString("tipus_torn")));
                g.setCategoria(c.getPerString(rst.getString("tipus_categoria")));
                g.setPlacesDisponibles(rst.getShort("places_disponibles"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(Guardia g) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update guardia set dia=?, tipus_unitat=?, tipus_torn=?, tipus_categoria=?, places_disponibles=? where id=?");
            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            query.setDate(1, Date.valueOf(g.getDia()));
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
}
