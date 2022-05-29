/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Rol;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victo
 */
public class JDBCCategoriaDAOIT {

    public JDBCCategoriaDAOIT() {
    }

    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        long id = 1;
        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
        Categoria expResult = new Categoria(1, "Infermer/a");
        Categoria result = instance.get(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
        Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
        ResultSet resultat = query.executeQuery("select * from categoria");
        List<Categoria> expResult = new ArrayList<>();
        while (resultat.next()) {
            expResult.add(new Categoria(resultat.getLong("id"), resultat.getString("tipus_categoria")));
        }
        List<Categoria> result = instance.getAll();
        assertEquals(expResult, result);
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Categoria c = new Categoria(3, "Doctor");
        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
        instance.delete(c);
        assertNull(c);
    }

    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        Categoria c = new Categoria(3, "Doctor");
        Categoria expResult = new Categoria();
        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
        instance.add(c);
        Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
        ResultSet resultat = query.executeQuery("select * from categoria where id=3 and tipus_categoria='Doctor'");
        expResult.setTipusCategoria(resultat.getString("tipus_categoria"));
        expResult.setId(resultat.getLong("id"));
        assertEquals(expResult, c);
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Categoria c = new Categoria(4, "Dermatòleg");
        Categoria expResult = new Categoria();
        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
        instance.update(c);
        Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
        ResultSet resultat = query.executeQuery("select * from categoria where id=4 and tipus_categoria='Dermatòleg'");
        expResult.setTipusCategoria(resultat.getString("tipus_categoria"));
        expResult.setId(resultat.getLong("id"));
        assertEquals(expResult, c);
    }

    @Test
    public void testGet_String() throws Exception {
        System.out.println("get_string");
        String s = "Infermer/a";
        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
        Categoria expResult = new Categoria(1, "Infermer/a");
        Categoria result = instance.getPerString("Infermer/a");
        assertEquals(expResult, result);
    }

}
