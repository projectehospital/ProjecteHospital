/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Treballador;
import cat.boscdelacoma.model.persistence.dao.contracts.TreballadorDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Victo
 */
public class JDBCTreballadorDAO implements TreballadorDAO {

    @Override
    public Treballador get(long id) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from treballador where id=?");
            query.setLong(1, id);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Treballador t = new Treballador();
                t.setTipusCategoria(resultat.getString("tipus_categoria"));
                c.setId(resultat.getLong("id"));
                return c;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }
    
    public Treballador getFromTipusTreballador(String t) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from unitat where tipus_categoria=?");
            query.setString(1, t);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Categoria c = new Categoria();
                c.setTipusCategoria(resultat.getString("tipus_categoria"));
                c.setId(resultat.getLong("id"));
                return c;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public List<Treballador> getAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Treballador t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Treballador t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Treballador t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
