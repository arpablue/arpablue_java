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
 * @author engau
 */
public class MatrixListTest {
    MatrixList mTarget;
    public MatrixListTest() {
        int cols = 3;
        int rows = 3;
        mTarget = new MatrixList(  cols );
        int pos = 1; 
        for( int r=0; r < rows; r++){
            for( int c=0; c < cols; c++ ){
                mTarget.set( r,c, pos+"");
                pos++;
            }
        }
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
    public static boolean compare(ArrayList<String> v1, ArrayList<String> v2 ){
        if( ( v1 == null) && ( v2 == null ) ){
            return true;
        }
        if( v1 == null ){
            return false;
        }
        if( v2 == null ){
            return false;
        }
        if( ( v1.size() == 0 ) && ( v2.size() == 0 ) ){
            return true;
        }
        if( v1.size() == 0 ){
            return false;
        }
        if( v2.size() == 0 ){
            return false;
        }
        if( v1.size() != v2.size() ){
            return false;
        }
        for( int i=0; i<v1.size(); i++){
            if( !v1.get( i ).equalsIgnoreCase( v2.get(i) ) ){
                return false;
            }
        }
        
        return true;
    }
    public static void print( ArrayList<String> target ){
        if( target == null ){
            System.out.println("List: The list is NULL.");
            return;
        }
        if( target.size()==0 ){
            System.out.println("List: The list is EMPTY.");
            return;
        }
        System.out.print("List: [");
        boolean flag = true;
        for( int i = 0; i < target.size(); i++ ){
            if( flag ){
                flag = false;
            }else{
                System.out.print(",");
            }
            System.out.print(target.get(i));
        }
        System.out.println("]");
    }

    /**
     * Test of clear method, of class MatrixList.
     */
    @Test
    public void test_MatrixList_set_element() {
        System.out.println("----- test_MatrixList_set_element");
        int cols = 3;
        MatrixList test = new MatrixList(  cols );
        test.set(0,0,"0");
        test.set(1,1,"1");
        test.set(2,2,"2");
        test.set(4,0,"3");
        System.out.println(test.toPrint());
        
    }
    /**
     * Test of clear method, of class MatrixList.
     */
    @Test
    public void test_MatrixList_getColumn() {
        System.out.println("----- test_MatrixList_getColumn");
        ArrayList<ArrayList<String>> lol = new ArrayList<ArrayList<String>>(); 
        ArrayList<String> exp1 = new ArrayList();
        exp1.add( "1" );
        exp1.add( "4" );
        exp1.add( "7" );
        lol.add(exp1);
        
        ArrayList<String> exp2 = new ArrayList();
        exp2.add( "2" );
        exp2.add( "5" );
        exp2.add( "8" );
        lol.add(exp2);
        
        ArrayList<String> exp3 = new ArrayList();
        exp3.add( "3" );
        exp3.add( "6" );
        exp3.add( "9" );
        lol.add(exp3);
        
        ArrayList<String> column ;
        for( int i = 0; i < lol.size(); i++){
            column = mTarget.getColum(i);
            if( !compare(lol.get(i), column) ){
                System.out.println("\nThe following list are not equals:");
                System.out.print("Exp - ");
                print( lol.get(i) );
                System.out.print("Cur - ");
                print( column );
            }
        }
        
    }
    @Test
    public void test_MatrixList_build() {
        System.out.println("----- test_MatrixList_build");
        int cols = 3;
        int rows = 3;
        MatrixList test = new MatrixList(  cols );
        int pos = 1; 
        for( int r=0; r < rows; r++){
            for( int c=0; c < cols; c++ ){
                test.set( r,c, pos+"");
                pos++;
            }
        }
        System.out.println(test.toPrint());
    }
    
}
