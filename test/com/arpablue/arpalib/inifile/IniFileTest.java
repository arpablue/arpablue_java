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
public class IniFileTest {
    
    public IniFileTest() {
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
     * This verify the keys for attributes of the general section or a section without a name, these keys 
     * should be contains only the name of the attribute.
     */
//    @Test
//    public void  test_getKeys_WithGeneral_sections(){
//        
//        System.out.println("---------------test_getKeys_WithGeneral_sections");
//        IniFile target = new IniFile();
//        ArrayList<String> errors = new ArrayList<String>();
//        target.set("fieldA", "ValueA");
//        target.set("fieldB", "ValueB");
//        target.set("fieldD", "ValueD");
//        target.set("fieldE", "ValueE");
//        target.set("fieldF", "ValueF");
//        
//        ArrayList<String> keys = target.getKeys();
//        if( !keys.get(0).equalsIgnoreCase("fieldA")){
//            errors.add("The key is not equlas: exp: [fieldA] cur: ["+keys.get(0)+"]");
//        }
//        if( !keys.get(1).equalsIgnoreCase("fieldB")){
//            errors.add("The key is not equlas: exp: [fieldB] cur: ["+keys.get(1)+"]");
//        }
//        if( !keys.get(2).equalsIgnoreCase("fieldD")){
//            errors.add("The key is not equlas: exp: [fieldD] cur: ["+keys.get(2)+"]");
//        }
//        if( !keys.get(3).equalsIgnoreCase("fieldE")){
//            errors.add("The key is not equlas: exp: [fieldE] cur: ["+keys.get(3)+"]");
//        }
//        if( !keys.get(4).equalsIgnoreCase("fieldF")){
//            errors.add("The key is not equlas: exp: [fieldF] cur: ["+keys.get(4)+"]");
//        }
//        if( errors.size() > 0 ){
//            System.out.println("The following keys are not equals:");
//            for( String e: errors ){
//                System.out.println("     "+e);
//            }
//            fail("Some keys are not equalk to the expected result.");
//        }
//        
//    }
//    /**
//     * It is the verify the keys for a section are correct.
//     */
//    @Test
//    public void  test_getKeys_With_sections(){
//        
//        System.out.println("---------------test_getKeys_WithGeneral_sections");
//        IniFile target = new IniFile();
//        ArrayList<String> errors = new ArrayList<String>();
//        target.set("sectionA/fieldA", "ValueA");
//        target.set("sectionA/fieldB", "ValueB");
//        target.set("sectionA/fieldD", "ValueD");
//        target.set("sectionA/fieldE", "ValueE");
//        target.set("sectionA/fieldF", "ValueF");
//        
//        ArrayList<String> keys = target.getKeys();
//        if( !keys.get(0).equalsIgnoreCase("sectionA/fieldA")){
//            errors.add("The key is not equlas: exp: [fieldA] cur: ["+keys.get(0)+"]");
//        }
//        if( !keys.get(1).equalsIgnoreCase("sectionA/fieldB")){
//            errors.add("The key is not equlas: exp: [fieldB] cur: ["+keys.get(1)+"]");
//        }
//        if( !keys.get(2).equalsIgnoreCase("sectionA/fieldD")){
//            errors.add("The key is not equlas: exp: [fieldD] cur: ["+keys.get(2)+"]");
//        }
//        if( !keys.get(3).equalsIgnoreCase("sectionA/fieldE")){
//            errors.add("The key is not equlas: exp: [fieldE] cur: ["+keys.get(3)+"]");
//        }
//        if( !keys.get(4).equalsIgnoreCase("sectionA/fieldF")){
//            errors.add("The key is not equlas: exp: [fieldF] cur: ["+keys.get(4)+"]");
//        }
//        if( errors.size() > 0 ){
//            System.out.println("The following keys are not equals:");
//            for( String e: errors ){
//                System.out.println("     "+e);
//            }
//            fail("Some keys are not equalk to the expected result.");
//        }
//        
//    }
//    /**
//     *It verify the values and the keys are correct for general sections and other sections
//     */
//    @Test
//    public void  test_getKeys_With_sections_and_general(){
//        
//        System.out.println("---------------test_getKeys_With_sections_and_general");
//        IniFile target = new IniFile();
//        ArrayList<String> errors = new ArrayList<String>();
//        ArrayList<String> keys = new ArrayList<String>();
//        keys.add("fieldA");
//        keys.add("fieldB");
//        keys.add("fieldD");
//        keys.add("fieldE");
//        keys.add("fieldF");
//
//        keys.add("sectionA/fieldA");
//        keys.add("sectionA/fieldB");
//        keys.add("sectionA/fieldD");
//        keys.add("sectionA/fieldE");
//        keys.add("sectionA/fieldF");
//        
//        keys.add("sectionB/fieldA");
//        keys.add("sectionB/fieldB");
//        keys.add("sectionB/fieldD");
//        keys.add("sectionB/fieldE");
//        keys.add("sectionB/fieldF");
//
//        for( int i = 0; i < keys.size(); i++ ){
//            target.set( keys.get(i), i );
//        }
//        int value = 0;
//        for( int i = 0; i < keys.size(); i++ ){
//            
//            value = (int) target.get( keys.get(i) );
//            if( value != i ){
//                errors.add("For the key ["+keys.get(i)+"] the values are not equals: exp["+i+"] cur["+value+"]");
//            }
//        }
//
//         
//        if( errors.size() > 0 ){
//            System.out.println("The following keys are not equals:");
//            for( String e: errors ){
//                System.out.println("     "+e);
//            }
//            fail("Some keys are not equals to the expected result.");
//        }
//        
//    }
//    /**
//     * It create two IniFile objects with the same structure and values to compare that are similar.
//     */
//    @Test
//    public void test_IniFile_compare_2_Ini_Objects(){
//        System.out.println("---------------test_IniFile_compare_2_Ini_Objects");
//        IniFile target = new IniFile();
//        IniFile clone = new IniFile();
//        ArrayList<String> keys = new ArrayList<String>();
//        keys.add("fieldA");
//        keys.add("fieldB");
//        keys.add("fieldD");
//        keys.add("fieldE");
//        keys.add("fieldF");
//
//        keys.add("sectionA/fieldA");
//        keys.add("sectionA/fieldB");
//        keys.add("sectionA/fieldD");
//        keys.add("sectionA/fieldE");
//        keys.add("sectionA/fieldF");
//        
//        keys.add("sectionB/fieldA");
//        keys.add("sectionB/fieldB");
//        keys.add("sectionB/fieldD");
//        keys.add("sectionB/fieldE");
//        keys.add("sectionB/fieldF");
//
//        for( int i = 0; i < keys.size(); i++ ){
//            target.set( keys.get(i), i );
//            clone.set( keys.get(i), i );
//        }
//        
//        boolean res = target.compare( clone );
//        if( !res ){
//            fail("The both Ini objects are not equals, when should be it.");
//        }
//        
//    }
//    /**
//     * It create a IniFile object and get the clone of the object, it test case verify the original and the clone object are equals.
//     */
//    @Test
//    public void test_IniFile_compare_clone(){
//        System.out.println("---------------test_IniFile_compare_clone");
//        IniFile target = new IniFile();
//        IniFile clone = null;
//        ArrayList<String> keys = new ArrayList<String>();
//        keys.add("fieldA");
//        keys.add("fieldB");
//        keys.add("fieldD");
//        keys.add("fieldE");
//        keys.add("fieldF");
//
//        keys.add("sectionA/fieldA");
//        keys.add("sectionA/fieldB");
//        keys.add("sectionA/fieldD");
//        keys.add("sectionA/fieldE");
//        keys.add("sectionA/fieldF");
//        
//        keys.add("sectionB/fieldA");
//        keys.add("sectionB/fieldB");
//        keys.add("sectionB/fieldD");
//        keys.add("sectionB/fieldE");
//        keys.add("sectionB/fieldF");
//
//        for( int i = 0; i < keys.size(); i++ ){
//            target.set( keys.get(i), i );
//        }
//        clone = target.clone();
//        
//        boolean res = target.compare( clone );
//        if( !res ){
//            fail("The both Ini objects are not equals, when should be it.");
//        }
//        
//    }
//    /**
//     * It create a IniFile object and get the clone of the object, it test case verify the original and the clone object are equals.
//     */
//    @Test
//    public void test_IniFile_compare_notEquals(){
//        System.out.println("---------------test_IniFile_compare_notEquals");
//        IniFile target = new IniFile();
//        IniFile clone = null;
//        ArrayList<String> keys = new ArrayList<String>();
//        keys.add("fieldA");
//        keys.add("fieldB");
//        keys.add("fieldD");
//        keys.add("fieldE");
//        keys.add("fieldF");
//
//        keys.add("sectionA/fieldA");
//        keys.add("sectionA/fieldB");
//        keys.add("sectionA/fieldD");
//        keys.add("sectionA/fieldE");
//        keys.add("sectionA/fieldF");
//        
//        keys.add("sectionB/fieldA");
//        keys.add("sectionB/fieldB");
//        keys.add("sectionB/fieldD");
//        keys.add("sectionB/fieldE");
//        keys.add("sectionB/fieldF");
//
//        for( int i = 0; i < keys.size(); i++ ){
//            target.set( keys.get(i), i );
//        }
//        clone = target.clone();
//        clone.set("extra", "extraValue");
//        
//        boolean res = target.compare( clone );
//        if( res ){
//            fail("The both Ini objects are equals, when should not be it.");
//        }
//        
//    }
//    /**
//     * Test of init method, of class IniFile.
//     */
//    @Test
//    public void test_fill_data_general() {
//        System.out.println("---------------test_fill_data_general");
//        String res = "fieldA = ValueA\n" +
//            "fieldB = ValueB\n" +
//            "fieldD = ValueD\n" +
//            "fieldE = ValueE\n" +
//            "fieldF = ValueF";
//        IniFile target = new IniFile();
//        
//        target.set("fieldA", "ValueA");
//        target.set("fieldB", "ValueB");
//        target.set("fieldD", "ValueD");
//        target.set("fieldE", "ValueE");
//        target.set("fieldF", "ValueF");
//        
//        System.out.println(target.toString());
//    }
    /**
     * Hi team
     */
    @Test
    public void  test_Create_Ini_File(){
        System.out.println("---------------test_fill_data_general");
        IniFile exp = new IniFile();
        IniFile current = new IniFile();
        String file = "./test.ini";
        
        exp.set("fieldA", "ValueA");
        exp.set("fieldB", "ValueB");
        exp.set("fieldD", "ValueD");
        exp.set("fieldE", "ValueE");
        exp.set("fieldF", "ValueF");
        if(!exp.save( file )){
            fail("It is not possible create the ini file:["+"./test.ini"+"]");
        }
        
        current.load(file);
        boolean flag = exp.compare( current );
        if( !flag ){
            System.out.println("Expected: ");
            System.out.println(exp.toJSONNicely());
            System.out.println("");
            System.out.println("Current: ");
            System.out.println(current.toJSONNicely());
            fail("The saved and loaded IniFile object are different when should be equals.");
        }
    }


}
