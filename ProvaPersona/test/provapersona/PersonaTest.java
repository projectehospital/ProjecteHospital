/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package provapersona;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kidos
 */
public class PersonaTest {
    
    
    public PersonaTest() {
    }

    /**
     * Test of setDni method, of class Persona.
     */
    @Test
    public void testSetDni() {
        System.out.println("setDni");
        String nouDni = "000000";
        Persona instance = new Persona();
        instance.setDni(nouDni);
        assertEquals(instance.getDni() , nouDni);
      
    }

    /**
     * Test of setNom method, of class Persona.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        Persona instance = new Persona();
        String nouNom = "carles";
        instance.setNom(nouNom);
        assertEquals(instance.getNom() , nouNom);
        
    }

    /**
     * Test of setEdat method, of class Persona.
     */
    @Test
    public void testSetEdat() {
        System.out.println("setEdat");
        Persona instance = new Persona();
        short novaEdat = 35;
        instance.setEdat(novaEdat);
        assertEquals(instance.getEdat() , novaEdat);
        
    }
    
    /**
     * Test of getDni method, of class Persona.
     */
    @Test
    public void testGetDni() {
        System.out.println("getDni");
        Persona instance = new Persona();
        String expResult = "00000";
        instance.setDni(expResult);
        String result = instance.getDni();
        assertEquals(expResult, result);

  
    }

    /**
     * Test of getNom method, of class Persona.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Persona instance = new Persona();
        String expResult = "carles";
        instance.setNom(expResult);
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getEdat method, of class Persona.
     */
    @Test
    public void testGetEdat() {
        System.out.println("getEdat");
        Persona instance = new Persona();
        short expResult = (short)35;
        instance.setEdat(expResult);
        short result = instance.getEdat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of mostrar method, of class Persona.
     */
   
}
