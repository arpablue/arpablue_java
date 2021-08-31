/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

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
public class MatrixTest {
    
    public MatrixTest() {
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
     * Test of evalueteSize method, of class Matrix.
     */
    @Test
    public void test_Matrix_EvalueteSize() {
        System.out.println("------- test_Matrix_EvalueteSize");
        Matrix<String> target = new Matrix<String>();
        int rows = 4;
        int cols = 4;
        target.setSize(rows, cols);
        int size = target.size();
        int currentSize = target.getElments().size();
        
        if( size != currentSize ){
            fail("The size  are not equals, the matriz size is: "+size+" - the list size is: "+currentSize);
        }
    }
    /**
     * Test of setSize method, of class Matrix.
     */
    @Test
    public void test_Matrix_getPosition() {
        System.out.println("-------------------- test_Matrix_getPosition");
        int cols = 3;
        int rows = 3;
        Matrix<Integer> target;
        target = new Matrix<Integer>();
        target.setSize(cols, rows);
        Integer e = null;
        for( int i = 0; i < 15; i++){
            e = new Integer(i+"");
            target.addToEnd( e );
        }
        boolean flag = true;
        if( target.getPosition(0, 1) != 1 ){
            System.out.println("\t The expected position should be 1 bu is "+target.getPosition(0, 1) );
            flag = false;
        }
        if( target.getPosition(1, 1) != 4 ){
            System.out.println("\t The expected position should be 1 bu is "+target.getPosition(1, 1) );
            flag = false;
        }
        if( target.getPosition(2, 1) != 7 ){
            System.out.println("\t The expected position should be 1 bu is "+target.getPosition(2, 1) );
            flag = false;
        }
        if( target.getPosition(1,0) != 3 ){
            System.out.println("\t The expected position should be 1 bu is "+target.getPosition( 1, 0) );
            flag = false;
        }
        if( target.getPosition(1,2) != 5 ){
            System.out.println("\t The expected position should be 1 bu is "+target.getPosition(1, 2) );
            flag = false;
        }
        if( !flag ){
            fail("The expected position are not correct.");
        }
        
    }

    
    /**
     * Test of setSize method, of class Matrix.
     */
    @Test
    public void test_Matrix_print() {
        System.out.println("-------- test_Matrix_print");
        int cols = 4;
        int rows = 4;
        int size = cols * rows;
        Matrix<String> target = new Matrix<String>();
        target.setSize( rows, cols);
        for( int i = 0; i < rows; i++ ){
            for (int j = 0; j < cols ;  j++ ) {
                target.set(i,j,"a_"+i+j );
            }
        }
        
        System.out.println( ""+target.toPrint() );
    }
    
}
