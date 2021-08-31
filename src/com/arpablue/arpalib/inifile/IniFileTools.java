/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;

import java.util.ArrayList;

public class IniFileTools {

    /**
     * It remove all white spaces from the strings.
     * @param target It is the String where the white spaces will be removed.
     * @return It is the String without white spaces.
     */
    static String withoutBlankSpaces(String target){
        String res = "";
        if( target == null){
            return "";
        }
        char [] v = target.toCharArray();
        for (int i = 0; i < v.length; i++) {
            if( !Character.isWhitespace( v[i] ) ){
                res = res + v[i];
            }
        }
        return res;
    }
    /**
     * It set in format of a key for an String.
     * @param target it is the string to generate the key.
     * @return It is the key generate from the target string.
     */
    static String formatKey( String target ){
        if( target == null){
            target = "";
        }
        target = target.trim();
        target = target.toLowerCase();
        target = withoutBlankSpaces( target );
        return target;
    }

    /**
     * Extract the comment from a line.
     * @param target It is the line where the commnet will be extracted.
     * @return It is the comment extrated, if return null then no comments found in the line.
     */
    static String extractComment( String target){
        if( target == null){
            return null;
        }
        target = target.trim();
        if( target.length() < 1){
            return null;
        }
        int pos = target.indexOf(';');
        if( pos < 0 ){
            return null;
        }
        if( pos == 0){
            return target;
        }
        char[] v = target.toCharArray();
        boolean element = false;
        boolean active = false;
        String res = "";

        for( char c : v){
            if( active ){
                res = res + c;
                continue;
            }
            if( c == '=' ){
                break;
            }
            if( c == '[' ){
                element = true;
                continue;
            }
            if( c == ']' ){
                element = false;
                continue;
            }
            if(element){
                continue;
            }
            if( c == ';'){
                active = true;
            }
        }

        if( res.length() < 1 ){
            return null;
        }
        return res;

    }

}
