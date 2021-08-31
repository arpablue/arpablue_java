/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools.treepath;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS
 */
public class TreePathTest {
    
    public TreePathTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class TreePath.
     */
    @Test
    public void test_TreePath_Save() {
        System.out.println("------------------ test_TreePath_Save");
        String filePath = "test.json";
        TreePath target = new TreePath();
        target.set("name", "Jhonny");
        target.set("lastname", "Prove");
        target.set("database/host", "localhost");
        target.set("database/database", "localhost");
        target.set("database/user", "noroot");
        target.set("database/password", "noroot");
        if( !target.save(filePath )){
            System.out.println( "ERRORS:" );
            System.out.println( target.getErrors() );
            fail("It is not possible create the file");
        }
    }

    /**
     * Test of load method, of class TreePath.
     */
    @Test
    public void testLoad() {
        System.out.println("------------------ test_TreePath_Save");
        String filePath = "test.json";
        TreePath target = new TreePath();
        if( !target.load( filePath )){
            System.out.println( "ERRORS:" );
            System.out.println( target.getErrors() );
            fail("It is not possible create the file");
        }
        System.out.println( target.toPrint() );
        fail("OIt needs review, the inut is not correct");
    }
    
}
