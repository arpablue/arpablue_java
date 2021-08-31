/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.errors;

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
public class ErrorCollectorTest {
    
    public ErrorCollectorTest() {
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
    protected void wrong(Object exp, Object current, String msg){
        System.out.println(" Current: ["+current.toString().replaceAll("\\n", "|")+"]");
        System.out.println("Expected: ["+exp.toString().replaceAll("\\n", "|")+"]");
        fail(msg);
    }
    /**
     * Test of setError method, of class ErrorCollector.
     */
    @Test
    public void test_ErrorMsg_SetError_GetError_OneError() {
        System.out.println("----------test_ErrorMsg_SetError_GetError_OneError");
        String current = "This Error 1";
        String exp  = "This Error 1";
        ErrorCollector target = new ErrorCollector();
        target.setError( current );
        current = target.getError();
        if( !current.equalsIgnoreCase( exp )){
            wrong(exp, current,  " The current and the expected result are not the same.");
        }
    }

    /**
     * Test of setError method, of class ErrorCollector.
     */
    @Test
    public void test_ErrorMsg_SetError_GetError_ThreErrors() {
        System.out.println("----------test_ErrorMsg_SetError_GetError_ThreErrors");
        String current = "This Error ";
        String exp  = "This Error 3";
        ErrorCollector target = new ErrorCollector();
        target.setError( current +"1" );
        target.setError( current +"2");
        target.setError( current +"3");
        current = target.getError();
        if( !current.equalsIgnoreCase( exp )){
            wrong(exp, current,  " The current and the expected result are not the same.");
        }
    }

    /**
     * Test of setError method, of class ErrorCollector.
     */
    @Test
    public void test_ErrorMsg_SetError_GetError_WithIndex() {
        System.out.println("----------test_ErrorMsg_SetError_GetError_WithIndex");
        String current = "This Error ";
        String exp  = "This Error 2";
        ErrorCollector target = new ErrorCollector();
        target.setError( current +"1" );
        target.setError( current +"2");
        target.setError( current +"3");
        current = target.getError(1);
        if( !current.equalsIgnoreCase( exp )){
            wrong(exp, current,  " The current and the expected result are not the same.");
        }
    }

    /**
     * Test of setError method, of class ErrorCollector.
     */
    @Test
    public void test_ErrorMsg_SizeErrors() {
        System.out.println("----------test_ErrorMsg_SizeErrors");
        int current = 0;
        int exp  = 3;
        ErrorCollector target = new ErrorCollector();
        target.setError( current +"1" );
        target.setError( current +"2");
        target.setError( current +"3");
        current = target.sizeErrors();
        if( current != exp ){
            wrong(exp, current,  "The quantity of the errors are not the same.");
        }
    }

    /**
     * Test of clearErrors method, of class ErrorCollector.
     */
    @Test
    public void test_ErrorMsg_ClearErrors_size() {
        System.out.println("----------test_ErrorMsg_ClearErrors_size");
        int current = 0;
        int exp  = 0;
        ErrorCollector target = new ErrorCollector();
        target.setError( "Error 1" );
        target.setError( "Error 2");
        target.setError( "Error 3");
        target.clearErrors();
        current = target.sizeErrors();
        if( current != exp ){
            wrong(exp, current,  "The error are not cleaned.");
        }
    }

    /**
     * Test of clearErrors method, of class ErrorCollector.
     */
    @Test
    public void test_ErrorMsg_ClearErrors_GetErrors() {
        System.out.println("----------test_ErrorMsg_ClearErrors_GetError");
        String current = "";
        String exp  = "";
        ErrorCollector target = new ErrorCollector();
        target.setError( "Error 1" );
        target.setError( "Error 2");
        target.setError( "Error 3");
        target.clearErrors();
        current = target.getErrors();
        if( current != exp ){
            wrong(exp, current,  "The error are not cleaned.");
        }
    }


    /**
     * Test of sizeErrors method, of class ErrorCollector.
     */
    @Test
    public void test_ErrorMsg_getErrors() {
        System.out.println("----------test_ErrorMsg_getErrors");
        String current = "";
        String exp  = "Error 1\n" +
                                "Error 2\n" +
                                "Error 3\n";
        ErrorCollector target = new ErrorCollector();
        target.setError( "Error 1" );
        target.setError( "Error 2");
        target.setError( "Error 3");
        current = target.getErrors();
        if( !current.equalsIgnoreCase(exp) ){
            wrong(exp, current,  "The errors list are not the same ");
        }
    }
    
}
