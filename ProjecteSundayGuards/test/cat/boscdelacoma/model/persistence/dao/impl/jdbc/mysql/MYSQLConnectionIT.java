/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victo
 */
public class MYSQLConnectionIT {
    
    public MYSQLConnectionIT() {
    }

    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        MYSQLConnection expResult = null;
        MYSQLConnection result = MYSQLConnection.getInstance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        MYSQLConnection instance = null;
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDisconnect() {
        System.out.println("disconnect");
        MYSQLConnection instance = null;
        instance.disconnect();
        fail("The test case is a prototype.");
    }
    
}
