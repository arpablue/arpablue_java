/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Dupla<T> {
    public Dupla(){}
    protected String mKey;
    protected String mRealKey;
    protected T mValue;
    public Dupla(String key,T value)
    {
        setKey(key);
        setValue(value);
    }
    /**
     * return the key of the current dupla.
     */
    public String getKey(){ return mKey; }
    /**
     * return the value of the current dupla.
     */
    public T getValue(){ return mValue;}
    /***
     * Specify the value of the current key.
     * @param value 
     */
    public void setValue(T value)
    {
        mValue = value;
    }
    /***
     * Change or specify the current key.
     */
    public void setKey(String key)
    {
        mKey = key;
        mRealKey = transformation(mKey);
        
    }
    /***
     * return the real codification.
     */
    protected static String transformation(String key)
    {
        if(key == null)
        {
            return null;
            
        }
        key = key.trim();
        key = key.toLowerCase();
        return key;
    }
    /***
     * verify if both key are equals
     */
    public boolean equalKey(String key)
    {
        if((key == null)&&(mKey == null))
        {
            return true;
        }
        key = transformation(key);
        return (mKey.equalsIgnoreCase(key));
    }
    /**
     * Show in a String.
     */
    @Override
    public String toString()
    {
        return mKey+"="+mValue.toString();
    }
}
