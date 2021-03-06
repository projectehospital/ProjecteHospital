/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.Treballador;
import cat.boscdelacoma.model.persistence.dao.contracts.TreballadorDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
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
                JDBCCategoriaDAO c = new JDBCCategoriaDAO();
                JDBCRolDAO r = new JDBCRolDAO();
                t.setId(resultat.getLong("id"));
                t.setDni(resultat.getString("DNI"));
                t.setNom(resultat.getString("nom"));
                t.setDataNaixement((resultat.getDate("data_Naixement").toLocalDate()));
                t.setPasswd(resultat.getString("passwd"));
                t.setGuardiesFetes(resultat.getLong("guardies_fetes"));
                t.setGuardiesPrevistes(resultat.getLong("guardies_previstes"));
                t.setEsCapDeUnitat(resultat.getLong("es_cap_de_unitat"));
                t.setTipusContracte(resultat.getString("tipus_contracte"));
                t.setCategoriaTreballador(c.getPerNom(resultat.getString("tipus_categoria")));
                t.setRolTreballador(r.getPerNom(resultat.getString("tipus_rol")));
                t.setEsCapDeUnitat(resultat.getLong("es_cap_de_unitat"));
                
                return t;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public ArrayList<Treballador> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from treballador");
            ArrayList<Treballador> list = new ArrayList<>();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCRolDAO r = new JDBCRolDAO();
            while (resultat.next()) {
                list.add(new Treballador(resultat.getLong("id"), resultat.getString("DNI"),
                        resultat.getString("nom"), (resultat.getDate("data_Naixement").toLocalDate()),
                        resultat.getString("passwd"), resultat.getLong("guardies_fetes"),
                        resultat.getLong("guardies_previstes"), resultat.getString("tipus_contracte"),
                        c.getPerNom(resultat.getString("tipus_categoria")),
                        r.getPerNom(resultat.getString("tipus_rol")),
                        resultat.getLong("es_cap_de_unitat")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void delete(Treballador t) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from treballador where id=?");
            query.setLong(1, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }

    @Override
    public void add(Treballador t) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into treballador(DNI, nom, data_Naixement, passwd, guardies_fetes, guardies_previstes, es_cap_de_unitat, tipus_contracte, tipus_categoria, tipus_rol) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            JDBCRolDAO r = new JDBCRolDAO();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            //query.setLong(1, t.getId());
            query.setString(1, t.getDni());
            query.setString(2, t.getNom());
            query.setDate(3, Date.valueOf(t.getDataNaixement()));
            query.setString(4, t.getPasswd());
            query.setLong(5, t.getGuardiesFetes());
            query.setLong(6, t.getGuardiesPrevistes());
            query.setLong(7, t.getEsCapDeUnitat());
            query.setString(8, t.getTipusContracte());
            query.setString(9, t.getCategoriaTreballador().getTipusCategoria());
            query.setString(10, t.getRolTreballador().getTipusRol());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            //No es fa cap assignaci?? perqu?? ??s un add!
            if (rst.next()) {
                System.out.println("Treballador afegit");
            }
           
        } catch (SQLException ex) {
            System.out.println("Error de id: " + ex.getMessage());
        }
    }

    @Override
    public void update(Treballador t) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update treballador set DNI=?, "
                    + "nom=?, data_Naixement=?, passwd=?, guardies_fetes=?, guardies_previstes=?, "
                    + "tipus_contracte=?, tipus_categoria=?, tipus_rol=?, es_cap_de_unitat=? where id=?");
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCRolDAO r = new JDBCRolDAO();
            query.setString(1, t.getDni());
            query.setString(2, t.getNom());
            query.setDate(3, Date.valueOf(t.getDataNaixement()));
            query.setString(4, t.getPasswd());
            query.setLong(5, t.getGuardiesFetes());
            query.setLong(6, t.getGuardiesPrevistes());
            query.setString(7, t.getTipusContracte());
            query.setString(8, t.getCategoriaTreballador().getTipusCategoria());
            query.setString(9, t.getRolTreballador().getTipusRol());
            query.setLong(10, t.getEsCapDeUnitat());
            query.setLong(11, t.getId());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

    public Treballador getTreballadorLogin(String dni, String passwd) throws DAOException {
        try {

            // obtenim un treballador per les dades que entra al login
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from treballador where DNI = ? and passwd = ?");
            query.setString(1, dni);
            query.setString(2, passwd);
            ResultSet resultat = query.executeQuery();

            if (resultat.next()) {
                Treballador t = new Treballador();
                JDBCCategoriaDAO c = new JDBCCategoriaDAO();
                JDBCRolDAO r = new JDBCRolDAO();
                t.setId(resultat.getLong("id"));
                t.setDni(resultat.getString("DNI"));
                t.setNom(resultat.getString("nom"));
                t.setDataNaixement((resultat.getDate("data_Naixement").toLocalDate()));
                t.setPasswd(resultat.getString("passwd"));
                t.setGuardiesFetes(resultat.getLong("guardies_fetes"));
                t.setGuardiesPrevistes(resultat.getLong("guardies_previstes"));
                t.setEsCapDeUnitat(resultat.getLong("es_cap_de_unitat"));
                t.setTipusContracte(resultat.getString("tipus_contracte"));
                t.setCategoriaTreballador(c.getPerNom(resultat.getString("tipus_categoria")));
                t.setRolTreballador(r.getPerNom(resultat.getString("tipus_rol")));
                return t;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    public boolean reservarGuardia(long idTreballador, long idGuardia) throws DAOException {
        try {

            // reservem la guardia guardant un registre en la taula guardies treballador
            // retorna true o fals si s'ha fet correctament 
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into guardies_treballador (id_treballador , id_guardia) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

            query.setLong(1, idTreballador);
            query.setLong(2, idGuardia);
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();

            JDBCGuardiaDAO guardia = new JDBCGuardiaDAO();
            // al reservar guardia restem les places disponibles
            guardia.restarPlaces(idGuardia);
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al reservar guardia" + ex.getMessage());
            throw new DAOException();
        }

    }

    public boolean cancelarGuardia(long idTreballador, long idGuardia) throws DAOException {
        try {

            // cancelem la guardia eliminant un registre en la taula guardies treballador
            // retorna true o fals si s'ha fet correctament 
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from guardies_treballador where id_treballador = ? and id_guardia = ? ", Statement.RETURN_GENERATED_KEYS);

            query.setLong(1, idTreballador);
            query.setLong(2, idGuardia);
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();

            // al reservar guardia restem les places disponibles 
            JDBCGuardiaDAO guardia = new JDBCGuardiaDAO();
            guardia.sumarPlaces(idGuardia);
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al reservar guardia" + ex.getMessage());
            throw new DAOException();
        }

    }

    public ArrayList<Guardia> obtenirLlistaGuardies(long idTreballador) throws DAOException {
        try {

            // cancelem la guardia eliminant un registre en la taula guardies treballador
            // retorna true o fals si s'ha fet correctament 
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from guardies_treballador where id_treballador = ?");

            query.setLong(1, idTreballador);

            ResultSet rst = query.executeQuery();
            ArrayList<Guardia> llistaGuardies = new ArrayList<Guardia>();
            JDBCGuardiaDAO guardia = new JDBCGuardiaDAO();

            while (rst.next()) {

                llistaGuardies.add(new Guardia(guardia.get(rst.getLong("id_guardia"))));

            }
            return llistaGuardies;
        } catch (SQLException ex) {
            System.out.println("Error al reservar guardia" + ex.getMessage());
            throw new DAOException();
        }

    }

}
