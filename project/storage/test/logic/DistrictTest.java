/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.CreatingConnection;

/**
 *
 * @author Антон
 */
public class DistrictTest {
    
    public DistrictTest() {
        CreatingConnection con = CreatingConnection.getInstance();
        con.Connection();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setDistrict method, of class District.
     */
    @Test
    public void testSetDistrict() {
        System.out.println("setDistrict");
        String district = "";
        District instance = new District();
        instance.setDistrict(district);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class District.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        District instance = new District();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDistrict method, of class District.
     */
    @Test
    public void testGetDistrict() {
        System.out.println("getDistrict");
        District instance = new District("123");
        String expResult = "123";
        String result = instance.getDistrict();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class District.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        District instance = new District("Невский");
        int expResult = 0;
        int result = instance.add();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class District.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        District instance = new District();
        District expResult = null;
        District result = instance.getById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
