/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author engau
 */
public class IniFileSectionTest {
    
    public IniFileSectionTest() {
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
     * Test of getSectionName method, of class IniFileSection.
     */
    @Test
    public void test_getSectionName_0() {
        String line = "[miSection]";
        String exp = "miSection";
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( line.equals(exp) ){
            System.out.println("---------- test_getSectionName");
            System.out.println(" Current: "+cur);
            System.out.println("Expectd: "+exp);
            fail("The current["+cur+"] and expected["+exp+"] are not equals");
        }
    }
    @Test
    public void test_getSectionName_1() {
        String line = "[  miSection  ]";
        String exp = "miSection";
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( line.equals(exp) ){
            System.out.println("---------- test_getSectionName");
            System.out.println(" Current: "+cur);
            System.out.println("Expectd: "+exp);
            fail("The current["+cur+"] and expected["+exp+"] are not equals");
        }
    }
    @Test
    public void test_getSectionName_2() {
        String line = "[mi  Section]";
        String exp = "mi  Section";
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( line.equals(exp) ){
            System.out.println("---------- test_getSectionName_2");
            System.out.println(" Current: "+cur);
            System.out.println("Expectd: "+exp);
            fail("The current["+cur+"] and expected["+exp+"] are not equals");
        }
    }
    @Test
    public void test_getSectionName_3() {
        String line = "[miSection] majsd kas";
        String exp = "miSection";
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( line.equals(exp) ){
            System.out.println("---------- test_getSectionName_3");
            System.out.println(" Current: "+cur);
            System.out.println("Expectd: "+exp);
            fail("The current["+cur+"] and expected["+exp+"] are not equals");
        }
    }

    @Test
    public void test_getSectionName_4() {
        String line = "miSection majsd kas";
        String exp = null;
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( cur != null ){
            System.out.println("---------- test_getSectionName_4");
            fail("The current is not null when should be it. cur["+cur+"]");
        }
    }
    @Test
    public void test_getSectionName_5() {
        String line = "miSect]ion maj[sd kas";
        String exp = null;
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( cur != null ){
            System.out.println("---------- test_getSectionName_5");
            fail("The current is not null when should be it. cur["+cur+"]");
        }
    }
    @Test
    public void test_getSectionName_6() {
        
        String line = "miSection [majsd kas";
        String exp = null;
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( cur != null ){
            System.out.println("---------- test_getSectionName_6");
            fail("The current is not null when should be it. cur["+cur+"]");
        }
    }
    @Test
    public void test_getSectionName_7() {
        
        String line = "[miSe=ction]";
        String exp = null;
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( cur != null ){
            System.out.println("---------- test_getSectionName_6");
            fail("The current is not null when should be it. cur["+cur+"]");
        }
    }
    @Test
    public void test_getSectionName_8() {
        
        String line = "[miSection]=";
        String exp = null;
        String cur = "";
        
        cur = IniFileSection.getSectionName( line );
        if( cur != null ){
            System.out.println("---------- test_getSectionName_6");
            fail("The current is not null when should be it. cur["+cur+"]");
        }
    }
}
