/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Treballador;
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
            JDBCTreballadorDAO treb = new JDBCTreballadorDAO();
            // fem la conversio de la data
            
            while (resultat.next()) {
                LocalDate locdat = resultat.getDate("dia").toLocalDate();
                Guardia g = new Guardia();
                g.setId(resultat.getLong("id"));
                g.setDia(locdat);
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                g.setUnitat(u.getPerNom("tipus_unitat"));
                g.setTorn(t.getPerNom("tipus_torn"));
                g.setCategoria(c.getPerNom("tipus_categoria"));
                g.setLlistaTreballadors(g.getId());
                
                list.add(g);
            }

            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }
    
    public List<Guardia> getPerData(LocalDate dia , Categoria categoria) throws DAOException, SQLException{
        
        // el parametre categoria ens permet mostrar nomes les guardies de la categoria del treballador
        
        try {
            // obtenim les guardies d'un dia en especific         
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from guardia where dia = ? and tipus_categoria = ?");
            query.setDate(1, Date.valueOf(dia));
            query.setString(2, categoria.getTipusCategoria());
            ResultSet resultat = query.executeQuery();
            List<Guardia> list = new ArrayList<>();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            // fem la conversio de la data

            while (resultat.next()) {
             
                LocalDate locdat = resultat.getDate("dia").toLocalDate();
                Guardia g = new Guardia();
                g.setId(resultat.getLong("id"));
                g.setDia(locdat);
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                g.setUnitat(u.getPerNom("tipus_unitat"));
                g.setTorn(t.getPerNom("tipus_torn"));
                g.setCategoria(c.getPerNom("tipus_categoria"));
                g.setLlistaTreballadors(g.getId());
                
                list.add(g);

            }
            return list;
        }catch(SQLException ex){
            
            System.out.println("Error al obtenir guardia per data");
            throw new DAOException("Error al obtenir guardia per data");
        }
        
    
    
    }

  
    public Guardia getPerDades(LocalDate dia , Unitat unitat , Torn torn , Categoria categoria ) throws DAOException{
        
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from guardia where dia=? and tipus_unitat = ? tipus_torn = ? tipus_categoria = ?");
            
            query.setDate(1,Date.valueOf(dia));
            query.setString(2,unitat.getTipusUnitat());
            query.setString(3,torn.getTipusTorn());
            query.setString(4,categoria.getTipusCategoria());
            
            
            ResultSet resultat = query.executeQuery();
            // daos per obtenir les dades de unitat torn i guardia
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();

            if (resultat.next()) {
                LocalDate locdat = resultat.getDate("dia").toLocalDate();
                Guardia g = new Guardia();
                g.setId(resultat.getLong("id"));
                g.setDia(locdat);
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                g.setUnitat(u.getPerNom("tipus_unitat"));
                g.setTorn(t.getPerNom("tipus_torn"));
                g.setCategoria(c.getPerNom("tipus_categoria"));
                g.setLlistaTreballadors(g.getId());
                
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
                JDBCTreballadorDAO treb = new JDBCTreballadorDAO();
                g.setId(resultat.getLong("id"));
                g.setDia(ld);
                g.setPlacesDisponibles(resultat.getShort("places_disponibles"));
                g.setUnitat(u.getPerNom("tipus_unitat"));
                g.setTorn(t.getPerNom("tipus_torn"));
                g.setCategoria(c.getPerNom("tipus_categoria"));
                g.setLlistaTreballadors(g.getId());
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

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into guardia(dia, tipus_unitat, tipus_torn, tipus_categoria, places_disponibles) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            // l'id s'assigna per auto increment query.setLong(1, g.getId());
            query.setDate(1, Date.valueOf(g.getDia()));
            query.setString(2, g.getUnitat().getTipusUnitat());
            query.setString(3, g.getTorn().getTipusTorn());
            query.setString(4, g.getCategoria().getTipusCategoria());
            query.setLong(5, g.getPlacesDisponibles());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                LocalDate ld = rst.getDate("dia").toLocalDate();
                g.setId(rst.getLong("id"));
                g.setDia(ld);
                g.setUnitat(u.getPerNom(rst.getString("tipus_unitat")));
                g.setTorn(t.getPerNom(rst.getString("tipus_torn")));
                g.setCategoria(c.getPerNom(rst.getString("tipus_categoria")));
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
            query.setLong(5, g.getPlacesDisponibles());
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
    
    public void restarPlaces(long idGuardia) throws DAOException {
      try {
          // metode que resta les places ja que un treballador ha fet una reserva
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update guardia set places_disponibles = places_disponibles - 1 where id=?");
            query.setLong(1, idGuardia);
            query.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al restar places" + ex.getMessage());
            throw new DAOException();

        }
      
    }
    
    public void sumarPlaces(long idGuardia) throws DAOException {
      try {
          // metode que resta les places ja que un treballador ha fet una reserva
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update guardia set places_disponibles = places_disponibles + 1 where id=?");
            query.setLong(1, idGuardia);
            query.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al restar places" + ex.getMessage());
            throw new DAOException();

        }
      
    }
    
    
    public ArrayList<Treballador> obtenirLlistaTreballadors(long idGuardia) throws DAOException {
        
        try {
                  // metode en el qual obtenim una llista de treballador d'una determinada guardia
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from guardies_treballador where id_guardia = ?");
            query.setLong(1, idGuardia);
            ResultSet resultat = query.executeQuery();
            ArrayList<Treballador> list = new ArrayList<Treballador>();
            JDBCTreballadorDAO treb = new JDBCTreballadorDAO();
      
            while (resultat.next()) {
                 // obtenim el treballador per l'id de la taula guardies_treballador i l'obtenim gracies al JDBCTreballadorDAO
                list.add(new Treballador(treb.get(resultat.getLong("id_treballador"))));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }
    
    

 
}
