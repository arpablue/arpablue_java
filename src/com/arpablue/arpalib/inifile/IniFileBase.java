/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;


public abstract class IniFileBase {
    protected String mName;
    protected String mKeyName;

    /**
     * It specify the name of the current attribute.
     * @param name It is the name of the attribute.
     */
    public void setName( String name ){
        if(name == null){
            name = "<NotSpecified_Name>";
        }
        mName = name.trim();
        setKeyName(mName);
    }

    /**
     * It return the name of the attribute.
     * @return It is the name of the attribute.
     */
    public String getName(){
        return mName;
    }
    /**
     * It specify the key to be used to identify the current attribute.
     * @param name
     */
    protected void setKeyName(String name){
        if( name == null ){
            name = "";
        }
        mKeyName = IniFileTools.formatKey(name);
    }

    /**
     * It return the current key.
     * @return
     */
    String getKeyName(){
        return mKeyName;
    }
    /**
     * It validate if the current attribute is equal to the passed key.
     * @param name
     * @return
     */
    public boolean isThis(String name){
        if( ( name == null ) && ( this.getName() == null) ){
            return true;
        }
        if( name == null ){
            return false;
        }
        if( this.getName() == null ){
            return false;
        }
        name = IniFileTools.formatKey(name);
        if( name.equalsIgnoreCase(this.getKeyName())){
            return true;
        }
        return false;
    }
    /**
     * It verify if the current object and another object of the same type has the same name.
     * @param target It is the object with the name to compare.
     * @return It is true if both objects has the same name.
     */
    public boolean isThis( IniFileBase target){
        if( target == null ){
            return false;
        }
        return isThis( target.getName() );
    }

    /**
     * It get the data from an string.
     * @param target It is the line where the data will be collected for the current object.
     * @return It is true if the data has been extract successfully.
     */
    public abstract boolean setData( String target);

    /**
     * It verify if the line content of has data structure for the current object.
     * @param target it is the line to verify is of one kind of object.
     * @return return true of the structure is correct.
     */
    public abstract boolean isThisType( String target);

}
