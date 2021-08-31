/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools.treepath;

import static com.arpablue.tools.treepath.TreePathPrimitive.splitFirstOcurrence;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class TreePathMng extends TreePathPrinter  {
    protected int mStrPos = 0;
    
    public TreePathMng(){
        super();
    }

    /**
     * It get the data from an object to fill the TreePath.
     * @param target This is the object where the data will be extract to be added to the 
     * current TreePath object.
     * @return It is true if the data has been added without problems.
     */
    public boolean parse(Object target) {
        if(target == null){
            return false;
        }
        if(target.getClass().getSimpleName().equalsIgnoreCase("String")){
            return convertFromString( target.toString() );
        }
        return false;
    }
    /**
     * It set in format a string that will be the key of a value.
     * @param key It is the string that contain the string.
     * @return It is the string with key under the correct format.
     */
    protected static String formatKey(String key){
        if(key == null){
            return null;
        }
        key = key.replaceAll(" ", "_0oo0_");
        key = key.replaceAll("\"", " ");
        key = key.trim();
        key = key.replaceAll("_0oo0_", " ");
        return key;
    }
    /**
     * It set on format an String to be parsed.
     * @param target It is the String that contains the format.
     * @return It is the string under format.
     */
    protected static String formatString(String target){
        if(target == null){
            return null;
        }
        target = target.trim();
        target = target.replaceAll(" ", "_0oo0_");
        target = target.replaceAll("\"", " ");
        target = target.trim();
        target = target.replaceAll(" ", "\"");
        target = target.replaceAll("_0oo0_", " ");
        return target;
    }
    /**
     * It set on format a value set in a string.
     * @param target It is the string has the the new value.
     * @return It return the value detected in the target string.
     */
    protected Object formatValue(String target){
        if(target == null){
            return null;
        }
        target= target.trim();
        if(target.length() < 1 ){
            return null;
        }
        if( target.charAt(0) == '"') {
            return formatString(target);
        }
        try{
            return Integer.parseInt(target);
        }catch( Exception e){}
        try{
            return Float.parseFloat(target);
        }catch( Exception e){}
        try{
            return Boolean.parseBoolean(target);
        }catch( Exception e){}
        return target;
    }
    /**
     * It add new field using the key and the target.
     * @param key It is key of the respective key.
     * @param target It is the value assigned to the key.
     */
    protected void addFieldFromString(String key,String target){
        if(target == null)
        {
            return;
        }
        int size = this.mChildren.size();
        String[] v = splitFirstOcurrence( target, ":" );
        if( v == null){
            return;
        }
        if( v.length < 1){
            return;
        }
        if (v.length == 1){
            this.add(key+"/"+size, formatValue(v[0]));
            return;
        }
        this.add(key+"/"+formatKey(v[0]), formatValue(v[1]));
    }
    /**
     * It extract the data from an string.
     * @param target It is the string that contain the data in a JSON format.
     * @return It return true if the converting process has been finished without problems.
     */
    protected boolean convertFromString(String target){
        if( target == null){
            return false;
        }
        this.clear();
        target = target.trim();
        
        char[] v = target.toCharArray();
        if(v.length < 1){
            return true;
        }
        if( target.equalsIgnoreCase("{}")){
            return true;
        }
        if( target.equalsIgnoreCase("[]")){
            return true;
        }
        if(( v[0] != '{' )&&( v[0] != '[' )){
            target = "{"+target;
        }
        if( ( v[ v.length - 1 ] != '}' )&&( v[ v.length - 1 ] != ']' )){
            target = target + "}";
        }
        target = target.trim();
        mStrPos = getNextStartObject(target, 0);
        if( mStrPos < 0){
            return false;
        }
        mStrPos++;
        if( target.charAt( mStrPos - 1 ) == '[' ){
           convertFromArrayString( target, this);
        }
        convertFromString(target,this);
        return true;
    }
    /**
     * It parse a string that contain an array.
     * @param target It is the string that contain the array.
     * @param tree It is the Tree path object where the data will be inserted.
     */
    protected void convertFromArrayString(String target, TreePathMng tree){
        if( target == null ){
            return;
        }
        if( mStrPos >= target.length() - 1 ){
            return ;
        }
        if( tree == null ){
            return;
        }
        if( mStrPos < 0 ){
            return;
        }
        char c = target.charAt(mStrPos);
        int index = -1;
        tree.setIsArray( true );
        do{
            index++;
            String key = index + "";
            Object value = extractValue( target );
            tree.set(key, value);
            c = target.charAt(mStrPos);
            mStrPos++;
        }while( !isEOFC( c ) );
        return;
    }
    /**
     * It get the data to fill the TreePath from a string in JSON  format.
     * @param target It is the string that contain the JSON format.
     * @param pos It is the current position in the string.
     */
    protected void convertFromString(String target, TreePathMng tree){
        if( target == null ){
            return;
        }
        if( mStrPos >= target.length() - 1 ){
            return ;
        }
        if( tree == null ){
            return;
        }
        if( mStrPos < 0 ){
            return;
        }
        char c = target.charAt(mStrPos);
        do{
            if( mStrPos >= target.length() - 1 ){
                return;
            }
            String key = extractKey( target );
            Object value = extractValue( target );
            tree.set(key, value);
            c = target.charAt(mStrPos);
            mStrPos++;
        }while( !isEOFC( c ) );
        return;
    }
    /**
     * It extract the current key of the parameter.
     * @param target It is the string that contain the 
     * @return It is the key of the current field.
     */
    protected String extractKey( String target  ){
        String key = null;
        if(target == null){
            return key;
        }
        key = "";
        char car = ' ';
        boolean verify = true;
        while( this.mStrPos < target.length() - 1 ){
           
            car = target.charAt(mStrPos);
            mStrPos++;
            if( car == '\"'){
                verify = !verify;
                continue;
            }
            if( verify ){
                if( isEOFC(car) || (car == ',') || (car == ':') ){
                    return key;
                }
            }
            key = key + car;
        }
        return key;
    }
    /**
     * It get the value from a section and until a end of field is identified.
     * @param target It is the 
     * @return It is the string with the respective value.
     */
    protected Object extractValue( String target ){
        String res = "";
        if( target == null ){
            return "";
        }
        if( mStrPos < 0){
            mStrPos = 0;
        }
        if( mStrPos >= target.length()-1 ){
            return "";
        }
        char car = target.charAt(mStrPos);
        if( car == ':' ){
            mStrPos++;
            if( mStrPos >= target.length()- 1 ){
                return "";
            }
            car = target.charAt( mStrPos );
            if( isEOFC( car ) )
            {
                return res;
            }
        }
        
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        if( target.charAt(mStrPos) == '{'){
            mStrPos++;
            Object obj = extractObject(target);
            return obj;
        }
        if( target.charAt(mStrPos) == '['){
            mStrPos++;
            
            TreePath tree = new TreePath();
            
            convertFromArrayString(target, tree);
            return tree;
        }
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        boolean verify  = true;
        while( mStrPos < target.length()){
            car = target.charAt(mStrPos);
            mStrPos++;
            if( car == '\"'){
                verify = !verify;
                continue;
            }
            if( verify ){
                if( isEOFC( car)  || (car == ',') ){
                    mStrPos--;
                    return res;
                }
            }
            if(( car != '\n' ) && ( car != '\r' )){
                res = res + car;
            }
        }
        
        
        mStrPos--;
        return res;
    }
    boolean mdShow = false;
    /**
     * It extract an object from the string, the object identified is initialized 
     * with the '{' character.
     * @param target It is the string that contain the object.
     * @return It is the object identified, if no object has been identified then 
     * return a null value.
     */
    protected Object extractObject(String target){
        TreePath res = new TreePath();
        convertFromString(target, res);
        
        return res;
    }
    /**
     * It return true when a find a character that is the end of the field.
     * @param car It is the character to verify is is a end of field character.
     * @return it is true if the character is a end of filed character.
     */
    public static boolean isEOFC(char car){
            if( car == '{' ){
                return true;
            }
            if( car == '[' ){
                return true;
            }
            if( car == '}' ){
                return true;
            }
            if( car == ']' ){
                return true;
            }
        return false;
    }
    /**
     * It return the position of the initial container character is present( [ and { ).
     * @param target It the String where the JSON data is present.
     * @param pos It is the positions where the search will be started.
     * @return It is the position where the start container character is present.
     */
    protected int getNextStartObject(String target, int pos){
        if( pos < 0){
            pos = 0;
        }
        if( target == null ){
            return -1;
        }
        if( target.length() < 1 ){
            return -1;
        }
        for( int i = pos; i < target.length(); i++){
            if( target.charAt(i) == '{'){
                return i;
            }
            if( target.charAt(i) == '['){
                return i;
            }
        }
        return -1;
    }
    /**
     * It verify if the current character is a end search character.
     * @return 
     */
    protected static boolean isEndChar(char target){
        if( target == ',' ){
            return true;
        }
        if( target == ':' ){
            return true;
        }
        if( target == '}' ){
            return true;
        }
        if( target == ']' ){
            return true;
        }
        return false;
    }
    /**
     * It show the structure of a TreePath in the console.
     * @param target It is The treePath object to display its structure.
     */
    protected static void print(ArrayList<String> target){
        if( target == null ){
            return;
        }
        System.out.println("------------------");
        String e;
        for( int i = 0; i < target.size(); i++ ){
            e = target.get(i);
            System.out.println("\t"+e);
        }
        System.out.println("------------------");
    }
    
}
