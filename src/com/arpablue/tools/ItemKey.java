/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

/**
 *
 * @author Administrator
 */
public class ItemKey {
    private String mKey=null;
    private Object mValue = null;
    public ItemKey(){ this(null,null);}
    public ItemKey(String key, Object value){
        mKey=key;
        mValue = value;
    }
    /**
     * @return the mKey
     */
    public String getKey() {
        return mKey;
    }

    /**
     * @param mKey the mKey to set
     */
    public void setKey(String mKey) {
        this.mKey = mKey;
    }

    /**
     * @return the mValue
     */
    public Object getValue() {
        return mValue;
    }

    /**
     * @param mValue the mValue to set
     */
    public void setValue(Object value) {
        this.mValue = value;
    }


}
