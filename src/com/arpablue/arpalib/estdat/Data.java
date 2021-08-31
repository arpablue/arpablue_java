/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

/**
 *
 * @author augusto
 */
public class Data {
    public static final String DATA_TYPE_ANY="any";
    public static final String DATA_TYPE_NONE="none";
    public static final String DATA_TYPE_STRING="string";
    public static final String DATA_TYPE_INT="int";
    public static final String DATA_TYPE_FLOAT="float";
    
    protected String mName=null;
    protected Object mValue="any";
    protected String mType=null;

    public Data(){}
    public Data(String name, Object value){
        mName=name;
        mType=DATA_TYPE_ANY;
        mValue=value;
    }
    public Data(String name, String type, Object value){
        mName=name;
        mType=type;
        mValue=value;
    }
    /**
     * @return the Name
     */
    public String getName() {
        return mName;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        if(mName==null){
            mName=null;
            return;
        }
        this.mName = Name.trim().toLowerCase();
    }

    /**
     * @return the Value
     */
    public Object getValue() {
        return mValue;
    }

    /**
     * @param Value the Value to set
     */
    public void setValue(Object Value) {
        this.mValue = Value;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return mType;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        if(Type!=null)
            this.mType = Type.trim().toLowerCase();
        mType=null;
    }
    public void setData(Data data){
        this.setName(data.getName());
        this.setType(data.getType());
        this.setValue(data.getValue());
    }
    @Override
    public String toString(){
        return this.getName()+"("+this.getType()+"):"+this.getValue();
    }
    
}
