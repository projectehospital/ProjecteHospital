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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
                t.setDataNaixement(deDateALocalDate(resultat.getDate("data_Naixement")));
                t.setPasswd(resultat.getString("passwd"));
                t.setGuardiesFetes(resultat.getShort("guardies_fetes"));
                t.setGuardiesPrevistes(resultat.getShort("guardies_previstes"));
                t.setTipusContracte(resultat.getString("tipus_contracte"));
                t.setCategoriaTreballador(c.getFromTipusCategoria(resultat.getString("tipus_categoria")));
                t.setRolTreballador(r.getFromTipusRol(resultat.getString("tipus_rol")));
                t.setEsCapDeUnitat(resultat.getShort("es_cap_de_unitat"));
                
                return t;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public List<Treballador> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from treballador");
            List<Treballador> list = new ArrayList<>();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCRolDAO r = new JDBCRolDAO();
            while (resultat.next()) {
                list.add(new Treballador(resultat.getLong("id"), resultat.getString("DNI"), 
                        resultat.getString("nom"), deDateALocalDate(resultat.getDate("data_Naixement")), 
                        resultat.getString("passwd"), resultat.getShort("guardies_fetes"), 
                        resultat.getShort("guardies_previstes"), resultat.getString("tipus_contracte"), 
                        c.getFromTipusCategoria(resultat.getString("tipus_categoria")),
                        r.getFromTipusRol(resultat.getString("tipus_rol")),
                        resultat.getShort("es_cap_de_unitat")));
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

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into treballador VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            JDBCRolDAO r = new JDBCRolDAO();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            query.setLong(1, t.getId());
            query.setString(2, t.getDni());
            query.setString(3, t.getNom());
            query.setDate(4, (java.sql.Date) deLocalDateADate(t.getDataNaixement()));
            query.setString(5, t.getPasswd());
            query.setShort(6, t.getGuardiesFetes());
            query.setShort(7, t.getGuardiesPrevistes());
            query.setString(8, t.getTipusContracte());
            query.setString(9, t.getCategoriaTreballador().getTipusCategoria());
            query.setString(10, t.getRolTreballador().getTipusRol());
            query.setLong(11, t.getEsCapDeUnitat());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                t.setId(rst.getLong(1));
                t.setDni(rst.getString(2));
                t.setNom(rst.getString(3));
                t.setDataNaixement(deDateALocalDate(rst.getDate(4)));
                t.setPasswd(rst.getString(5));
                t.setGuardiesFetes(rst.getShort(6));
                t.setGuardiesPrevistes(rst.getShort(7));
                t.setTipusContracte(rst.getString(8));
                t.setCategoriaTreballador(c.getFromTipusCategoria(rst.getString(9)));
                t.setRolTreballador(r.getFromTipusRol(rst.getString(10)));
                t.setEsCapDeUnitat(rst.getShort(11));
            }

        } catch (SQLException ex) {
            throw new DAOException();
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
            query.setDate(3, (java.sql.Date) deLocalDateADate(t.getDataNaixement()));
            query.setString(4, t.getPasswd());
            query.setShort(5, t.getGuardiesFetes());
            query.setShort(6, t.getGuardiesPrevistes());
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
    
    public LocalDate deDateALocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date deLocalDateADate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
    
}
