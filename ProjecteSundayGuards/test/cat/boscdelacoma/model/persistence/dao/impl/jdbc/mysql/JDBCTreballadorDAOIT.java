/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Treballador;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victo
 */
public class JDBCTreballadorDAOIT {
    
    public JDBCTreballadorDAOIT() {
    }

    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        long id = 0L;
        JDBCTreballadorDAO instance = new JDBCTreballadorDAO();
        Treballador expResult = null;
        Treballador result = instance.get(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        JDBCTreballadorDAO instance = new JDBCTreballadorDAO();
        List<Treballador> expResult = null;
        List<Treballador> result = instance.getAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Treballador t = null;
        JDBCTreballadorDAO instance = new JDBCTreballadorDAO();
        instance.delete(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        Treballador t = null;
        JDBCTreballadorDAO instance = new JDBCTreballadorDAO();
        instance.add(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Treballador t = null;
        JDBCTreballadorDAO instance = new JDBCTreballadorDAO();
        instance.update(t);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeDateALocalDate() {
        System.out.println("deDateALocalDate");
        Date dateToConvert = null;
        JDBCTreballadorDAO instance = new JDBCTreballadorDAO();
        LocalDate expResult = null;
        LocalDate result = instance.deDateALocalDate(dateToConvert);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeLocalDateADate() {
        System.out.println("deLocalDateADate");
        LocalDate dateToConvert = null;
        JDBCTreballadorDAO instance = new JDBCTreballadorDAO();
        Date expResult = null;
        Date result = instance.deLocalDateADate(dateToConvert);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
