/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Rol;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victo
 */
public class JDBCRolDAOIT {
    
    public JDBCRolDAOIT() {
    }

    @Test
    public void testGet_long() throws Exception {
        System.out.println("get");
        long id = 0L;
        JDBCRolDAO instance = new JDBCRolDAO();
        Rol expResult = null;
        Rol result = instance.get(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        JDBCRolDAO instance = new JDBCRolDAO();
        List<Rol> expResult = null;
        List<Rol> result = instance.getAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Rol t = null;
        JDBCRolDAO instance = new JDBCRolDAO();
        instance.delete(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        Rol t = null;
        JDBCRolDAO instance = new JDBCRolDAO();
        instance.add(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Rol t = null;
        JDBCRolDAO instance = new JDBCRolDAO();
        instance.update(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGet_String() throws Exception {
        System.out.println("get");
        String s = "";
        JDBCRolDAO instance = new JDBCRolDAO();
        Rol expResult = null;
        Rol result = instance.get(s);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
