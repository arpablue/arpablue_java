/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

import com.arpablue.interfaces.IJson;
import java.util.ArrayList;

/**
 * It contian the reference a couple of data, one is the key and the other is the value.
 * @author ASUS
 */
class LinkNode<T> implements IJson{
    /**
     * It is the reference of the key of the object.
     */
    protected String mKey;
    /**
     * It is the value assigned to the key.
     */
    protected T mValue;
    /**
     * Default contructor.
     */
    public LinkNode(){}
    /**
     * tjhis contructor receive the key and its related value.
     * @param key It isthe key.
     * @param value  It is the value.
     */
    public LinkNode( String key, T value ){
        set( key, value );
    }
    /**
     * It return a different object with the dame data.
     * @return It is the LinkNode object.
     */
    public LinkNode<T> clone(){
        LinkNode<T> res = new LinkNode<T>();
        res.setKey(mKey);
        res.setValue(mValue);
        return res;
    }
    /**
     * It specify the kkey and the value associated to the value.
     * @param key It is the key.
     * @param value It is the value associated.
     */
    public void set(String key, T value){
        this.setKey(key);
        this.setValue(value);
    }
    /**
     * It specify the key of the value.
     * @param name It is the key of the value
     */
    public void setKey( String name ){
        if( name != null ){
            name = name.trim();

        }
        mKey = name;
    }
    /**
     * It return the current key.
     * @return It is the key of the couple
     */
    public String getKey(){
        return this.mKey;
    }
    /**
     * It specify the value related to the key.
     * @param value It is the value assigned to the key.
     */
    public void setValue( T value ){
        mValue = value;
    }
    /**
     * It return the value of the object.
     * @return It is the vallue of the object.
     */
    public T getValue(){
        return mValue;
    }
    /**
     * IT verify tif the current key is equals to ta refrence key, this method is uncase-sensitive.
     * @param key This ifçs the key to compare
     * @return It is ture the key is the same.
     */
    public boolean compareTo(String key){
        if( (key == null) && ( this.mKey == null ) ){
            return true;
        }
        if( key == null ){
            return false;
        }
        if( this.mKey == null ){
            return false;
        }
        key = key.trim();
        return this.mKey.equalsIgnoreCase(key);
    }
    /**
     * It set the content of the object in an string with JSON format.
     * @return Ti is the String that contains the JSON format the data of the object.
     */
    @Override
    public String toJSON() {
        String res = "\""+this.getKey()+"\"";
        if( this.mValue == null ){
            res = res + "null";
        }else if( res.getClass().getSimpleName().equalsIgnoreCase("String") ){
            res = res + "\""+this.mValue+"\"";
        }else if( res.getClass().getSimpleName().equalsIgnoreCase("Char") ){
            res = res + "\""+this.mValue+"\"";
        }else if( res.getClass().getSimpleName().contains("String") ){
            res = res + "\""+this.mValue+"\"";
        }else{
            res = res + this.mValue.toString();
        }
        return res;
    }
    /**
     * It set the content of the object in an string with JSON format.
     * @return Ti is the String that contains the JSON format the data of the object.
     */
    @Override
    public String toJSONnicelly() {
        return this.toJSON();
    }
}
/**
 * It create a link of a value, where the value could be reference to the value using a string, or a key.
 * 
 * @author ASUS
 */
public class LinkList <T>{
    /**
     * It is the list of element of the current objecet.
     */
    ArrayList<LinkNode<T>> mList;
    public LinkList(){
        mList = new ArrayList<LinkNode<T>>();
    }
    /**
     * It return the number of elements in the list.
     * @return It is the number of element.
     */
    public int size(){
        if( mList == null ){
            return -1;
        }
        return mList.size();
    }
    /**
     * It set a new value to existing key, if the key not exist then the key and the value are add to the list.
     * @param key It is the reerence key.
     * @param value It is the value assigned.
     */
    public void set(String key, T value){
        if( mList == null ){
            return;
        }
        for( int i = 0; i < this.mList.size(); i++ ){
            if( this.mList.get(i).compareTo(key)){
                this.mList.get(i).setValue(value);
                return;
            }
        }
        LinkNode<T> e = new LinkNode<T>( key, value );
        this.mList.add( e );
    }
    /**
     * It verify f a key exists in the list.
     * @param key It is the key to be search.
     * @return It is true then the key exists.
     */
    public boolean exist( String key ){
        if( mList == null ){
            return false;
        }
        for( int i = 0; i < this.mList.size(); i++ ){
            if( this.mList.get(i).compareTo(key)){
                return true;
            }
        }
       return false;
     }
    /**
     * It return a value from a key. It the key not exists then the method return null, it is necesary use the exists()
     * method to verify the key not exists,
     * @param key It i sthe key of the value.
     * @return it is the value assigened to the key.
     */
    public T get( String key ){
        if( mList == null ){
            return null;
        }
        for( int i = 0; i < this.mList.size(); i++ ){
            if( this.mList.get(i).compareTo(key)){
                return this.mList.get(i).getValue();
            }
        }
        return null;
    }
    /**
     * It return the value of the element in the position specified.
     * @param index It is the position of the tlement in the list.
     * @return It is the value of the position specified.
     */
    public T get( int index ){
        if( mList == null ){
            return null;
        }
        if( index < 0 ){
            index = this.size() - (index * -1);
            if( index < 0){
                return null;
            }
            index = this.size() - index;
        }
        if( index >= this.size() ){
            return null;
        }
        return this.mList.get(index).getValue();
    }
    /**
     * It return another object with a copy of the same elements fo the current object.
     * @return It is a LinkList object copy of the current list.
     */
    public LinkList<T> clone(){
        LinkList<T> res = new LinkList<T>();
        for( LinkNode<T> e : mList){
            res.set(e.getKey(), e.getValue());
        }
        return res;
    }
}
