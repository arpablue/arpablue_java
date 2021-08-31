/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools.treepath;


import com.arpablue.errors.CError;
import com.arpablue.tools.StringManager;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Augusto Flores
 */
class TreeBase extends CError{
    /**
     * This is the type of the children for the current node
     */
    protected String mType;
    /**
     * It contains the key to be used
     */
    protected String mReferenceKey;
    /**
     * It contains the real key used to identify the current value.
     *
     */
    protected String mKey;
    /**
     * It specify if the current structure belong to an array.
     */
    protected boolean mbIsArray = false;
    public TreeBase(){
        mKey = null;
        mReferenceKey = null;
    }
    /**
     * It verify is the current structure belong to an array or not.
     * @return It is true if the current structure belong to an array.
     */
    boolean isArray(){
        return mbIsArray;
    }
    /**
     * It specify if the current node is an array.
     * @param value It specify if the current node is an array.
     */
    void setIsArray( boolean value ){
       mbIsArray = value; 
    }
    /**
     * It return the current key used
     * @return 
     */
    public String getKey(){
        return mReferenceKey;
    }
    /**
     * It specify the new key to be used.
     * @param key 
     */
    public void setKey(String key){
        if(key == null){
            key = "null";
        }
        key = StringManager.replaceAll(key, "\"", "");
        key = key.trim();
        mReferenceKey = key;
        mKey = getKeyFormat(mReferenceKey);
    }
    /**
     * It compare the key of the current object to another key and return true 
     * if both keys are equals.
     * @param key
     * @return It return true if both keys are equals.
     */
    public boolean equalKey(String key){
        if( key == null){
            key = "null";
        }
        key = getKeyFormat(key);
        return key.equalsIgnoreCase(mKey);
    }
    /**
     * It set the key in format.
     * @param key The key to set in format
     * @return this is the new key under the format, i the key is null then return null.
     */
    protected static String getKeyFormat(String key){
        if(key == null){
            return null;
        }
        key = key.replace("\\", "/");
        key = key.replace(" ", "_");
        key = key.replace("/", " ");
        key = key.trim();
        key = key.toLowerCase();
        return key;
    }
    /**
     * It return the content of the object in an string.
     * @return It is the string content the data of the TreeBase
     */
    @Override
    public String toString(){
        
        return mReferenceKey;
    }    
    /**
     * It return true if the current Th object .is a Sheet.
     */
    protected boolean isNode(){
        return true;
    }
    /**
     * It clear the current object.
     * @return 
     */
    public boolean clear(){
        return true;
    }
    /**
     * It return a value,
     */
    public Object getValue(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        return null;
    }
    /**
     * It return true if the object is an array.
     * @param target
     * @return 
     */
    public static boolean isArray(Object target) {
        if (target == null) {
            return false;
        }
        String cl = target.getClass().getSimpleName();
        return (cl.indexOf("[]") > -1);
    }
    /**
    * It return true if the object is a number.
    * @target It is the object to verify if is a number.
    * @return It is true if the object is a number.
    */
    public static boolean isNumber(Object target) {
        if (target == null) {
            return false;
        }
        return Number.class.isAssignableFrom(target.getClass());
    }

    /** 
    * It return true if the object is a collection.
    * @target It is the object to verify if is a collection.
    * @return It is true if the object is a collection object.
    */
    public static boolean isCollection(Object target) {
        if (target == null) {
            return false;
        }
        boolean res = target instanceof Collection;
        res = res || Collection.class.isAssignableFrom(target.getClass());
        res = res || target.getClass().isArray();
        return res;
    }

    /**
     * It return true if the contain object is a map.
     * @target It is the object to verify if the object is a MAP object.
     * @return It return true if the current value is a MAP.
     */
    public static boolean isMap(Object target) {
        boolean res = Map.class.isAssignableFrom(target.getClass());
        res = res || target instanceof Map;
        return res;
    }


    /**
     * It return true if the object is a boolean.
     * @target It is the object to verify if is boolean object.
     * @return It is true if the object is a boolean object.
     */
    public static boolean isBoolean(Object target) {
        if (target == null) {
            return false;
        }

        return Boolean.class.isAssignableFrom(target.getClass());
    }
    /**
     * Return true if the object is null.
     * @target It is the object to verify if is a null pointer.
     * @return It is ture if the object is null.
     */
    public static boolean isNull(Object target) {
        return target == null;
    }
    /**
     * It verify if the current value is a TreePath.
     * @param target It it the object to verify if the is TreePath object.
     * @return It is true if the object is a TreePath.
     */
    public static boolean isThPath(Object target){
        return target instanceof TreePath;
    }
    /**
     * It return true if the content value is a string;
     * @return 
     */
    public static boolean isString(Object target){
        if( target == null){
            return false;
        }
        return target instanceof String;
    }
    /**
     * It return true if is a primitive object.
     * @return 
     */
    public static boolean isPrimitive(Object target){
         if(isNull(target) ){
             return false;
         }
        if( isCollection(target) ){
            return false;
        }
        if( isArray(target) ){
            return false;
        }
        if( isMap(target) ){
            return false;
        }
        if( isThPath(target) ){
            return false;
        }
        if( isString(target)){
            return true;
        }
        return target.getClass().isPrimitive();
    }
    /**
     * It return true if the target is any type collection .
     */
    public static boolean isCollector(Object target){
        if( target == null){
        return false;
        }
        if( target.getClass().getSimpleName().indexOf("[]") > -1){
            return true;
        }
        if( TreeBase.isArray(target)){
            return true;
        }
        if( TreeBase.isCollection(target)){
            return true;
        }
        if( TreeBase.isMap(target)){
            return true;
        }
        if( TreeBase.isThPath(true));
        return false;
    }
    
}
