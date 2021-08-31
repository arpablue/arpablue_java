/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools.treepath;


import com.arpablue.tools.treepath.TreeBase;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Augusto Flores
 */
public class TreeLeaf extends TreeBase{
    protected Object mValue;
    public TreeLeaf(){
        mValue = null;
    }
    public TreeLeaf( String key, Object value){
        this.setKey(key);
        this.setValue(value);
    }
    /**
     * It specify the new object value.
     * @param value 
     */
    public void setValue( Object value){
        
        mValue = value;
    }
    /**
     * It return the current value object.
     * @return The current value.
     */
    @Override
    public Object getValue(){
        if(mValue instanceof String){
            return  "\"" + mValue + "\"";
        }
        if(mValue instanceof Character){
            return  "'" + mValue + "'";
        }
        return mValue;
    }
    /**
     * It return true if the current Th object .Is a Sheet.
     * @return It return true.
     */
    @Override
    protected boolean isNode(){
        return false;
    }
    @Override
    public String toString(){
        String res = "";
        res = "\""+this.getKey()+"\":";
        res = res + valueToString();
        return res;
    }
//    @Override
//    String getString() {
//        return toString();
//    }
    protected String valueToString(){
        if(getValue() == null){
            return null;
        }
        if (this.isArray(this.getValue())) {
            return toStringArray(this.getValue());
        }
        if (this.isCollection(this.getValue())) {
            return toStringCollection(this.getValue() );
        }
        if (this.isMap(this.getValue())) {
            return toStringMap(this.getValue());
        }
        if(this.getValue() instanceof String){
            return  this.getValue()+"";
        }
        if(this.getValue() instanceof Character){
            return  this.getValue()+"";
        }
        if(this.getValue() instanceof TreePath){
            return ((TreePath)this.getValue()).toString();
        }
        if (this.isPrimitive(this.getValue())) {
            if (this.isString(this.getValue())) {
                return  "\"" + this.getValue() + "\"";

            } else {
                return this.getValue().toString();

            }
        }
        return this.getValue().toString() ;
    }
    /**
     * It set in a string format the content of an array.
     * @param target
     * @return It is the string with the array structure.
     */
    protected static String toStringArray(Object target) {
        String res;
        if (target == null) {
            return "null";
        }
        int arrlength = Array.getLength(target);
        if (arrlength < 1) {
            return "[]";
        }
        
        
        res = "[";
        boolean first = true;
        Object e;
        for (int i = 0; i < arrlength; i++) {
            if (first) {
                first = false;
            } else {
                res = res + "," ;
            }
            e = Array.get(target, i);
            if( e == null){
                e = "null";
            }else{
                if( e instanceof String){
                    e = "\"" + e.toString() + "\"";
                } else if( e instanceof Character){
                    e = "\'" + e.toString() + "\'";
                }
            }
            res = res + e.toString();
        }
        res = res +"]";
        return res;
    }
    /**
     * It set in an string the content of a collection.
     * @param target
     * @return 
     */
    protected static String toStringCollection( Object target){
        if( target == null){
            return "null";
        }
        Collection<Object> vector =  (Collection<Object>) target;
        if( vector.size() < 1){
            return "[]";
        }
        String res = "[";
        boolean flag = true;
        String cad;
        for (Object o : vector) {
            if( o == null ){
                cad = "null";
            }else{
                cad = o.toString();
                if( o instanceof String){
                    cad = "\""+cad+"\"";
                }else if( o instanceof Character){
                    cad = "'"+cad+"'";
                }
            }
            if (flag) {
                res = res + cad.toString();
                flag = false;
            } else {
                res = res + "," + cad.toString();
            }
        }
        res = res + "]";
        return res;
    }
    /**
     * It return the string of any map is a string format.
     * @param map
     * @return It is the string result.
     */
    protected static String toStringMap(Object map) {
        if (map == null) {
            return "null";
        }
        String res = "{";
        boolean flag = false;
        Map target = (Map)map;
        if( target.size() < 1 ){
            return "{}";
        }
        Set set = target.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            
            Object key = mentry.getKey();
            Object value = mentry.getValue();
            
            if( flag ){
                res = res + ",";
            }

            res = res + "\"" + key + "\":";

            if( value == null){
                res = res + "null";
            }else if(value instanceof String){
                res = res +"\""+value+"\"";
            }else if(value instanceof Character){
                res = res +"\""+value+"\"";
            }else{
                res = res + value;
            }
            flag = true;
        }

        res = res + "}";
        return res;
    }
    

}
