/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools;

/**
 * Contain different function for String comparison.
 * @author root
 */
public class StringComparer {
    /**
     * Return only alphanumeric characters.
     */
    public static String getAlphanumeric(String target){
        String res = null;
        if(target == null){
            return res;
        }
        res = "";
        char [] v = target.toCharArray();
        
        for (int i = 0; i < v.length; i++) {
            char w = v[i];
            if(Character.isAlphabetic(w) || Character.isDigit(w)){
                res = res + Character.toString(w);
            }
            
        }
        
        return res;
    }
    /**
     * Return only alphabetic characters.
     */
    public static String getAlphabetic(String target){
        String res = null;
        if(target == null){
            return res;
        }
        res = "";
        char [] v = target.toCharArray();
        
        for (int i = 0; i < v.length; i++) {
            char w = v[i];
            if( Character.isAlphabetic(w) ){
                res = res + Character.toString(w);
            }
        }
        return res;
    }
    /**
     * compare two string only in case unsensitive mode.
     * @author root
     */
    public static boolean StricComparison(String t1, String t2){
        return t1.equalsIgnoreCase(t2);
    }
    /**
     * return only the digits of the string.
     */
    public static String getNumeric(String target){
        String res = null;
        if(target == null){
            return res;
        }
        res = "";
        char [] v = target.toCharArray();
        
        for (int i = 0; i < v.length; i++) {
            char w = v[i];
            if( Character.isDigit(w )){
                res = res + Character.toString(w);
            }
            
        }
        
        return res;
    }
    
    /**
     * Compare only in a alphanumeric level two string, special characters and 
     * white spaces are discriminated in this comparison.
     */
    public static boolean AlphanumericComparizon(String t1, String t2){
        if( (t1 == null)&&(t2 == null) ){
            return true;
        }
        if( (t1 == null)||(t2 == null) ){
            return false;
        }
        String r1 = StringComparer.getAlphanumeric(t1);
        String r2 = StringComparer.getAlphanumeric(t2);
        
        
        return r1.equalsIgnoreCase(r2);
    }
    /**
     * Compare only in a alphabetic level two string, special characters and 
     * white spaces are discriminated in this comparison.
     */
    public static boolean AlphabeticComparizon(String t1, String t2){
        if( (t1 == null)&&(t2 == null) ){
            return true;
        }
        if( (t1 == null)||(t2 == null) ){
            return false;
        }
        String r1 = StringComparer.getAlphabetic(t1);
        String r2 = StringComparer.getAlphabetic(t2);
        
        
        return r1.equalsIgnoreCase(r2);
    }
    /**
     * Compare if one string is inside in the other string.
     */
    public static boolean ContainComparison(String t1, String t2){
        if(( t1 == null)||( t2 == null )){
            return true;
        }
        if( t1 == null){
            return false;
        }
        if( t2 == null){
            return false;
        }
        int pos = 0;

        if(t1.length() > t2.length()){
            pos = t1.indexOf(t2);
        }else{
            pos = t2.indexOf(t1);
        }
        if(pos >-1){
            return true;
        }
        return false;
    }
}
