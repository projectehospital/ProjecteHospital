/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Torn;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victo
 */
public class JDBCTornDAOIT {
    
    public JDBCTornDAOIT() {
    }

    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        JDBCTornDAO instance = new JDBCTornDAO();
        List<Torn> expResult = null;
        List<Torn> result = instance.getAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGet_long() throws Exception {
        System.out.println("get");
        long id = 0L;
        JDBCTornDAO instance = new JDBCTornDAO();
        Torn expResult = null;
        Torn result = instance.get(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        Torn t = null;
        JDBCTornDAO instance = new JDBCTornDAO();
        instance.add(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Torn t = null;
        JDBCTornDAO instance = new JDBCTornDAO();
        instance.update(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Torn t = null;
        JDBCTornDAO instance = new JDBCTornDAO();
        instance.delete(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGet_String() throws Exception {
        System.out.println("get");
        String s = "";
        JDBCTornDAO instance = new JDBCTornDAO();
        Torn expResult = null;
        Torn result = instance.get(s);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
