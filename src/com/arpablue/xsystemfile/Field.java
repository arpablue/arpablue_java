/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.xsystemfile;

/**
 *
 * @author socrates flores
 */
import java.io.*;
/**
 * This class group a field with a specific value
 * @author Socrates augusto Flores Ayala
 */
public class Field {
    protected String mField=null;
    protected String mValue=null;
    /**
     * Create a field with a specific name and value;
     * @param field the field name
     * @param value the value of the field
     */
    public Field(String field, String value)
    {
        this.setField(field);
        this.setValue(value);
    }
    /**
     * Convert the object in a string with specific format: <b>fieldName</b>=<b>fieldValue<b>
     * @return
     */
    @Override
    public String toString()
    {
        return this.getField()+"="+this.getValue();
    
    }
    public Field(String field)
    {
        parseIn(field);
    }
    public Field()
    {
        this(null);
    }
    public String getField() {
        return mField;
    }
    public void setField(String field) {
        this.mField = field;
        if( this.mField == null ){
            return;
        }
        this.mField = this.mField.trim();
    }
    public String getValue() {
        return mValue;
    }
    public void setValue(String value) {
        this.mValue = value;
        if( this.mValue == null ) {
            return;
        }
        this.mValue = this.mValue.trim();
    }
    public boolean parseIn(String cad)
    {
        if (cad == null)
            return false;
        if(cad.length()<1)
            return false;
        int pos=cad.indexOf("=");
        if(pos < 0)
        {
            setField(cad);
            return true;
        }
        setField(cad.substring(0, pos));
        setValue(cad.substring(pos+1,cad.length()));
        return true;
    }
    public void toFile(RandomAccessFile file){
        try {
            file.writeBytes("\t"+this.toString() + "\r\n");
        } catch (IOException ex) {
            //LogFile.error("(Field - toFile): "+ex.getMessage());
        }
    }
}
