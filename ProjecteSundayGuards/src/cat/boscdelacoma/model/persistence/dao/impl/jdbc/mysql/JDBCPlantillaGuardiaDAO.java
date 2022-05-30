/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.PlantillaGuardia;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.PlantillaGuardiaDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oussama
 */
public class JDBCPlantillaGuardiaDAO implements PlantillaGuardiaDAO  {
    
    public ArrayList<PlantillaGuardia> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from plantilla_guardia");
            ArrayList<PlantillaGuardia> list = new ArrayList<>();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            while (resultat.next()) {
                list.add(new PlantillaGuardia(resultat.getLong("id"), resultat.getString("tipus_unitat"), resultat.getString("tipus_categoria"),resultat.getString("tipus_torn"), resultat.getLong("places")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException("Error al obtenir totes les files de plantilla_guardia");
        }

    }

   public PlantillaGuardia get( String categoria , String unitat , String torn) throws DAOException {
       // obtenim una plantilla entrant per parametre dades
       try {
     PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement(
        "select * from plantilla_guardia where tipus_categoria = '?' and tipus_torn = '?' and tipus_unitat = '?'" );
        query.setString(1, categoria);
        query.setString(2, torn);
        query.setString(3, unitat);
        
        ResultSet rs = query.executeQuery();
           if (rs.next()) {
               
           PlantillaGuardia plG = new PlantillaGuardia(rs.getLong("id") , rs.getString("tipus_unitat"), rs.getString("tipus_categoria"),rs.getString("tipus_torn"), rs.getLong("places"));
           return plG;
           } else {
               return null;
           }
         
       }catch (SQLException e) {
           throw new DAOException("Error al obtenir Plantilla");
       }
        

   }
   

    @Override
    public PlantillaGuardia get(long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PlantillaGuardia t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(PlantillaGuardia t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PlantillaGuardia t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
