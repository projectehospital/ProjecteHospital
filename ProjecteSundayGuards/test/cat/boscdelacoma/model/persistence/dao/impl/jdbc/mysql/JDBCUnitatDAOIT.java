/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Unitat;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victo
 */
public class JDBCUnitatDAOIT {
    
    public JDBCUnitatDAOIT() {
    }

    @Test
    public void testGet_long() throws Exception {
        System.out.println("get");
        long id = 0L;
        JDBCUnitatDAO instance = new JDBCUnitatDAO();
        Unitat expResult = null;
        Unitat result = instance.get(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGet_String() throws Exception {
        System.out.println("get");
        String t = "";
        JDBCUnitatDAO instance = new JDBCUnitatDAO();
        Unitat expResult = null;
        Unitat result = instance.get(t);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        JDBCUnitatDAO instance = new JDBCUnitatDAO();
        List<Unitat> expResult = null;
        List<Unitat> result = instance.getAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Unitat u = null;
        JDBCUnitatDAO instance = new JDBCUnitatDAO();
        instance.delete(u);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        Unitat u = null;
        JDBCUnitatDAO instance = new JDBCUnitatDAO();
        instance.add(u);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Unitat u = null;
        JDBCUnitatDAO instance = new JDBCUnitatDAO();
        instance.update(u);
        fail("The test case is a prototype.");
    }
    
}
