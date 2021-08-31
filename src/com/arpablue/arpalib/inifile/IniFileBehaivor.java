/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;

import java.util.ArrayList;

/**
 *
 * @author engau
 */
public class IniFileBehaivor extends IniFileData{
    /**
     * Default constructor.
     */
    public IniFileBehaivor(){
        super();
    }
    /**
     * It compare the current structure with another structure, the section fields and calues should be the same.
     * @param target It is the structure to compare.
     * @return It is true if the keys and the values are the same.
     */
    public boolean compare(IniFile target){
        if( target == null ){
            return false;
        }
        ArrayList<String> key1 = this.getKeys();
        ArrayList<String> key2 = target.getKeys();
        
        if( key1.size() != key2.size() ){
            return false;
        }
        Object val1 = null;
        Object val2 = null;
        for( String key : key1 ){
            val1 = this.get(key);
            val2 = target.get(key);
            if( !val1.toString().equals( val2.toString() ) ){
                return false;
            }
        }
        return true;
            
    }
    /**
     * It return another IniFile object with the same structure and the asame values.
     * @return It is the clone object of the current object.
     */
    public IniFile clone(){
        IniFile res = new IniFile();
        ArrayList<String> keys = this.getKeys();
        Object value = null;
        for( String key : keys ){
            value = this.get( key );
            res.set( key, value);
        }
        return res;
    }
    
    
}
