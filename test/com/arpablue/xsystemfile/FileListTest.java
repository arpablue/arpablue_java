/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

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
public class FileListTest {
    
    public FileListTest() {
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
     * Test of loadFile method, of class FileList.
     */
    @Test
    public void test_FileList_Contains() {
      FileList exp = new FileList();
      FileList current = new FileList();
      String file = ".\\tresults\\target_FileList.txt";
      
      exp.add("Line 1");
      exp.add("Line 2");
      exp.add("Line 3");
      exp.add("Line 4");
      exp.add("Line 5");
      exp.add("Line 6");
      exp.add("Line 7");
      
      current.add("ne 1");
      current.add("ne 2");
      current.add("ne 3");
      current.add("ne 4");
      current.add("ne 5");
      current.add("ne 6");
      current.add("ne 7");
      
      if( !exp.contains(current)){
          fail("The two structure are not equals.");
      }
    }

    /**
     * Test of saveFile method, of class FileList.
     */
    @Test
    public void test_FileList_Compare() {
      FileList target = new FileList();
      FileList current = new FileList();
      String file = ".\\tresults\\target_FileList.txt";
      
      target.add("Line 1");
      target.add("Line 2");
      target.add("Line 3");
      target.add("Line 4");
      target.add("Line 5");
      target.add("Line 6");
      target.add("Line 7");
      
      if( !target.save( file ) ){
          fail("IT is not possible create the file: "+ file );
      }
      
      if( !current.load(file) ){
          fail("It is not possible load the file: " + file );
      }
      
      if( !current.compare(target)){
          fail("The two structure are not equals.");
      }
    }
    @Test
    public void test_FileList_Clone(){
      FileList expected = new FileList();
      FileList current =null;
      
      
      expected.add("Line 1");
      expected.add("Line 2");
      expected.add("Line 3");
      expected.add("Line 4");
      expected.add("Line 5");
      expected.add("Line 6");
      expected.add("Line 7");
      
      current = expected.clone();
      
      if( !expected.compare( current ) ){
          fail("The expected and current are not equals.");
      }
    }
}
