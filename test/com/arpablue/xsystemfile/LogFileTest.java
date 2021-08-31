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
 * @author ASUS
 */
public class LogFileTest {
    protected String mStrLogFile = "./tresults/logs/logger";
    protected FileList mExp;
    public LogFileTest() {
       mExp = new FileList();
        mExp.add("This for test");
        mExp.add("Hello world!!");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       SystemFile.delete(mStrLogFile+".log");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of writeln method, of class LogFile.
     */
    @Test
    public void test_LogFile_createLog() {
       System.out.println("--------------------- test_LogFile_createLog");
       FileList cur = new FileList(); 
       LogFile log = new LogFile( mStrLogFile , false);
       
       
       for( String e: mExp ){
           log.writeln(e);
       }
       if( !cur.load( log.getFile() ) ){
           fail("The log file not exists,");
       }
       cur.println();
       cur.contains(mExp);
       
    }
    /**
     * Test of writeln method, of class LogFile.
     */
    @Test
    public void test_LogFile_pass() {
       System.out.println("--------------------- test_LogFile_createLog");
       FileList cur = new FileList(); 
       LogFile log = new LogFile( mStrLogFile , false);
       
       
       for( String e: mExp ){
           log.pass(e);
       }
       if( !cur.load( log.getFile() ) ){
           fail("The log file not exists,");
       }
       cur.println();
       cur.contains(mExp);
       
    }
}
