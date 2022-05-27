/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.CategoriaDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victo
 */
public class JDBCCategoriaDAO implements CategoriaDAO {

    @Override
    public Categoria get(long id) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from categoria where id=?");
            query.setLong(1, id);
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
    public List<Categoria> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from categoria");
            List<Categoria> list = new ArrayList<>();
            while (resultat.next()) {
                list.add(new Categoria(resultat.getLong("id"), resultat.getString("tipus_categoria")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void delete(Categoria c) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("delete from categoria where id=?");
            query.setLong(1, c.getId());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException();

        }
    }

    @Override
    public void add(Categoria c) throws DAOException {
        try {

            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("insert into categoria VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            query.setLong(1, c.getId());
            query.setString(2, c.getTipusCategoria());
            query.executeUpdate();
            ResultSet rst = query.getGeneratedKeys();
            if (rst.next()) {
                c.setTipusCategoria(rst.getString("tipus_categoria"));
                c.setId(rst.getLong("id"));
            }

        } catch (SQLException ex) {
            throw new DAOException();
        }
    }

    @Override
    public void update(Categoria c) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("update categoria set tipus_categoria=? where id=?");
            query.setString(1, c.getTipusCategoria());
            query.setLong(2, c.getId());
            query.executeUpdate();

        } catch (SQLException ex) {

            throw new DAOException();

        }
    }

    @Override
    public Categoria get(String s) throws DAOException {
        try {
            PreparedStatement query = MYSQLConnection.getInstance().getConnection().prepareStatement("select * from categoria where tipus_categoria=?");
            query.setString(1, s);
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
    
}
