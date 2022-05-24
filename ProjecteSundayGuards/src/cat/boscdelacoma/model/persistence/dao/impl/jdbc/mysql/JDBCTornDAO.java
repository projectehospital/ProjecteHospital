/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.persistence.dao.contracts.TornDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author osama
 */
public class JDBCTornDAO implements TornDAO{

    @Override
    public Torn get(long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Torn> getAll() throws DAOException {
       
        try {
        
            Statement consulta = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = consulta.executeQuery("select * from torn");
        
            var torns = new ArrayList<Torn>();
            
            while(resultat.next()){
                
                torns.add( new Torn(resultat.getString("tipus_torn")));
            
            }
            return torns;
        } catch (SQLException ex){
        
                   throw new DAOException();
        
        }
        
        
    }

    @Override
    public void delete(Torn t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Torn t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Torn t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
