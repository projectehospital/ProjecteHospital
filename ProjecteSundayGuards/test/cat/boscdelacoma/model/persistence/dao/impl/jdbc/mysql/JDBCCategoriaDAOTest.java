/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Categoria;
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
public class JDBCCategoriaDAOTest {
    
    public JDBCCategoriaDAOTest() {
    }

    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        long id = 1;
        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
        Categoria expResult = new Categoria(1, "Infermer/a");
        Categoria result = instance.get(id);
        assertEquals(expResult.getId(), result.getId());
    }
    
//    @Test
//    public void testGetAll() throws Exception {
//        System.out.println("getAll");
//        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
//        List<Categoria> expResult = null;
//        List<Categoria> result = instance.getAll();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        System.out.println("delete");
//        Categoria c = null;
//        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
//        instance.delete(c);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAdd() throws Exception {
//        System.out.println("add");
//        Categoria c = null;
//        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
//        instance.add(c);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        Categoria c = null;
//        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
//        instance.update(c);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetPerString() throws Exception {
//        System.out.println("getPerString");
//        String s = "";
//        JDBCCategoriaDAO instance = new JDBCCategoriaDAO();
//        Categoria expResult = null;
//        Categoria result = instance.getPerString(s);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
}
