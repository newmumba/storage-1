/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Антон
 */
public class CarTest {
    
    public CarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setName method, of class Car.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Car instance = new Car();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSize method, of class Car.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        double size = 0.0;
        Car instance = new Car();
        instance.setSize(size);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class Car.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        boolean state = false;
        Car instance = new Car();
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Car.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        Car instance = new Car();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Car.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Car instance = new Car();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Car.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Car instance = new Car();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class Car.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getSize();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Car.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Car instance = new Car();
        boolean expResult = false;
        boolean result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class Car.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Car instance = new Car();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Car.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Car instance = new Car();
        int expResult = 0;
        int result = instance.add();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class Car.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        Car instance = new Car();
        ArrayList<Car> expResult = null;
        ArrayList<Car> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class Car.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        Car instance = new Car();
        Car expResult = null;
        Car result = instance.getById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class Car.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Car instance = new Car();
        instance.save();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFree method, of class Car.
     */
    @Test
    public void testGetFree() {
        System.out.println("getFree");
        Car instance = new Car();
        ArrayList<Car> expResult = null;
        ArrayList<Car> result = instance.getFree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Approve method, of class Car.
     */
    @Test
    public void testApprove() {
        System.out.println("Approve");
        Car instance = new Car();
        instance.Approve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of InFree method, of class Car.
     */
    @Test
    public void testInFree() {
        System.out.println("InFree");
        Car instance = new Car();
        instance.InFree();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPackingList method, of class Car.
     */
    @Test
    public void testGetPackingList() {
        System.out.println("getPackingList");
        Car instance = new Car();
        PackingList expResult = null;
        PackingList result = instance.getPackingList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
