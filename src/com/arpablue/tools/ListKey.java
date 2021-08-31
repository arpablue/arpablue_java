/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

import java.util.Vector;

/**
 * create a list of item asociate to the keyword.
 * @author Administrator
 */
public class ListKey {
    protected Vector mList = new Vector();
    public Object get(int i){ return ((ItemKey)mList.get(i)).getValue();}
    public int size(){ return mList.size();}
    public void removeAllElements(){ mList.removeAllElements();}
    
    public void add(String key, Object value){
        ItemKey e=null;
        for(int i = 0; i<mList.size(); i++){
            e = (ItemKey)mList.get(i);
            if(e.getKey().equals(key)){
                e.setValue(value);
                return;
            }
        }
        mList.add(new ItemKey(key,value));
    }
    public Object get(String key){
        ItemKey e=null;
        for(int i = 0; i<mList.size(); i++){
            e = (ItemKey)mList.get(i);
            if(e.getKey().equals(key)){
                return e.getValue();
            }
        }
        return null;
    }
}
