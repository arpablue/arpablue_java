/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

import java.util.ArrayList;
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
public class LDLTest {
    
    public LDLTest() {
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
     * Test of add method, of class LDL.
     */
    @Test
    public void test_LDL_Add_simetric_list() {
        System.out.println("------------------- test_LDL_Add_simetric_list");
        LDL<Integer> instance = new LDL<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(4);
        list.add(5);
        list.add(6);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(7);
        list.add(8);
        list.add(9);
        instance.add( list );
        // TODO review the generated test code and remove the default call to fail.
       instance.println();
    }
        /**
     * Test of add method, of class LDL.
     */
    @Test
    public void test_LDL_Add_asimetric_list() {
        System.out.println("------------------------------------- test_LDL_Add_asimetric_list");
        LDL<Integer> instance = new LDL<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(3);
        list.add(4);
        list.add(5);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        instance.add( list );
        // TODO review the generated test code and remove the default call to fail.
       instance.println();
    }
      /**
     * Test of add method, of class LDL.
     */
    @Test
    public void test_LDL_Add_Null_elements() {
        System.out.println("------------------- test_LDL_Add_Null_elements");
        LDL<Integer> instance = new LDL<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(null);
        list.add(2);
        list.add(3);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(4);
        list.add(null);
        list.add(6);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(7);
        list.add(8);
        list.add(null);
        instance.add( list );
        // TODO review the generated test code and remove the default call to fail.
       instance.println();
    } 
      /**
     * Test of add method, of class LDL.
     */
    @Test
    public void test_LDL_Add_Null_list() {
        System.out.println("------------------- test_LDL_Add_Null_list");
        LDL<Integer> instance = new LDL<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(null);
        list.add(2);
        list.add(3);
        instance.add( list );
        instance.add( null );
        instance.add( new ArrayList<Integer>() );
        list = new ArrayList<Integer>();
        list.add(7);
        list.add(8);
        list.add(null);
        instance.add( list );
        // TODO review the generated test code and remove the default call to fail.
       instance.println();
    } 
    /**
     * Test of add method, of class LDL.
     */
    @Test
    public void test_LDL_getColumn() {
        System.out.println("------------------- test_LDL_getColumn");
        
        LDL<Integer> instance = new LDL<Integer>();
        ArrayList<Integer> exp = new ArrayList<Integer>();
        exp.add( 2 );
        exp.add( 5 );
        exp.add( 8 );
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(4);
        list.add(5);
        list.add(6);
        instance.add( list );
        list = new ArrayList<Integer>();
        list.add(7);
        list.add(8);
        list.add(9);
        instance.add( list );
        instance.println();
        ArrayList<Integer> cur = instance.getColumn(1);
        if( cur.size() != 3)
        {
            System.out.println("The quantity of elements it is wrong, it is ["+cur.size()+"] when should be [3].");
            fail("The quantity of elements it is wrong, it is ["+cur.size()+"] when should be [3].");
            
        }
               
        int e = 0;
        int c = 0;
        String res = "";
        boolean flag = true;
        String curL = "";
        for( int i=0; i < exp.size(); i++)
        {
            curL = curL + cur.get(i) + " - ";
            if( cur.get(i) != exp.get(i) )
            {
               res = res + "The elments are different: cur["+cur.get(i)+"] != exp["+exp.get(i)+"]";
               flag = false;
            }
        }
        System.out.println("Cur List:- "+curL+" ");
        if( !flag )
        {
            System.out.println(res);
            fail( res );
        }


    }

}
