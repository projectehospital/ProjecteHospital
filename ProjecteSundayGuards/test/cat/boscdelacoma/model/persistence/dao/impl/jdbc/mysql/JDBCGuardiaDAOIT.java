/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Guardia;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victo
 */
public class JDBCGuardiaDAOIT {
    
    public JDBCGuardiaDAOIT() {
    }

    @Test
    public void testGetAllIGet() throws Exception {
        System.out.println("getAll");
        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
        List<Guardia> expResult = new ArrayList();
        List<Guardia> result = instance.getAll();
        for (int i = 0; i < result.size(); i++) {
            expResult.add(i, instance.get(i));
            System.out.println(instance.get(i));
        }
        assertEquals(expResult, result);
    }

//    @Test
//    public void testGetPerData() throws Exception {
//        System.out.println("getPerData");
//        LocalDate dia = null;
//        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
//        Guardia expResult = null;
//        Guardia result = instance.getPerData(dia);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGet() throws Exception {
//        System.out.println("get");
//        long id = 0L;
//        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
//        Guardia expResult = null;
//        Guardia result = instance.get(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAdd() throws Exception {
//        System.out.println("add");
//        Guardia g = null;
//        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
//        instance.add(g);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        Guardia g = null;
//        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
//        instance.update(g);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        System.out.println("delete");
//        Guardia g = null;
//        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
//        instance.delete(g);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeDateALocalDate() {
//        System.out.println("deDateALocalDate");
//        Date dateToConvert = null;
//        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
//        LocalDate expResult = null;
//        LocalDate result = instance.deDateALocalDate(dateToConvert);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeLocalDateADate() {
//        System.out.println("deLocalDateADate");
//        LocalDate dateToConvert = null;
//        JDBCGuardiaDAO instance = new JDBCGuardiaDAO();
//        Date expResult = null;
//        Date result = instance.deLocalDateADate(dateToConvert);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//    
}
