/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

import com.arpablue.tools.Dupla;
import java.util.ArrayList;

/**
 * It contains a dupla list of T objects, each object has a String reference called key.
 * @author root
 */
public class DuplaList<T> extends ArrayList<Dupla<T>>{
    
    

    /**
     * Add a new dupla to the array.
     * 
     * @param key It is the the key reference of the value.
     * @param value It is the the value of the key,
     */
    public void add(String key, T value)
    {
        this.add(new Dupla(key,value));
        
    }
    /**
     * Search a dupla with a specific key, return the index of the first occurrence, 
     * if no elements are  found then return -1.
     * @param key It is the the key reference of the value.
     */
    protected int search(String key)
    {
        Dupla e = null;
        for(int i = 0; i<this.size(); i++)
        {
            e = this.get(i);
            if(e.equalKey(key))
            {
                return i;
            }
        }
        return -1;
    }
    /**
     * Set a value for the element that have the same key, if no elements have the key, then is added to the list.
     * If an element with the same key is found then return true, in other case return false.
     * @param key it is the key of the value.
     * @param value it is the value of the key.
     * @return 
     */
    public boolean setValue(String key, T value)
    {
        int index = search(key);
        if(index < 0)
        {
            this.add(key, value);
            return false;
        }
        Dupla e = this.get(index);
        e.setValue(value);
        return true;
    }
    /**
     * Set a value for the element that have the same key, if no elements have the key, then is added to the list.
     * @param dupla It is the dupla to be added.
     * @return If an element with the same key is found then return true, in other case return false.
     */
    public boolean setValue(Dupla<T> dupla)
    {
        return setValue(dupla.getKey(),dupla.getValue());
    }
    /**
     * It return the value of dupla with a specific  key.
     * @param key It is the reference of the key.
     * @return It is the value associated to the key.
     */
    public T getValue(String key)
    {
        int index = search(key);
        if(index < 0)
        {
            
            return null;
        }
        return this.get(index).getValue();
    }
    /**
     * Add the duplas with the keys specified in the vector, the values are null,
     * all previous elements are removed.
     * @param keys It is the list of keys  to add to the list.
    */    
    public void setDuplaKeys(ArrayList<String> keys)
    {
        if(keys == null)
        {
            return;
        }
        this.clear();
        for(int i=0; i<keys.size(); i++ )
        {
            this.add(keys.get(i),null);
        }
    }
    /**
     * Sed values to the lista, no matter the keys, only the order of the values.
      * @param values It is the list of the new values.
      */
    public void setDuplaValues(ArrayList<T> values)
    {
        if(values == null)
        {
            return;
        }
        for(int i=0; i<values.size(); i++ )
        {
            this.get(i).setValue(values.get(i));
        }
    }
    
}
