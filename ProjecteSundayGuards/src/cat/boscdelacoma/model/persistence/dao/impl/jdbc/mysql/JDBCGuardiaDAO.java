/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victo
 */
public class JDBCGuardiaDAO {
    @Override
    public Persona get(long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM mysqlconnection");
            var llista = new ArrayList<Persona>();
            while(result.next()){
                llista.add(new Persona(result.getString("nom"),
                    result.getString("dni"),
                    result.getShort("edat")));
            }
            return llista;
        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void add(Persona p) throws DAOException {
        try {
            var query = MYSQLConnection.getInstance().getConnection().prepareStatement("INSERT INTO mysqlconnection(nom, edat, dni) VALUES(?, ?, ?)");
            query.setString(1, p.getNom());
            query.setInt(2, p.getEdat());
            query.setString(3, p.getDni());
            query.executeUpdate();
            
            var rst = query.getGeneratedKeys();
            if (rst.next()) {
                p.setId(rst.getInt(1));
            }
        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void update(Persona p) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Persona p) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
