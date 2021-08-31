/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

/**
 *
 * @author socrates flores
 */
import java.util.*;
import java.io.*;
public class Section {

    private ArrayList mFields = null;
    private String mName=null;
    public Section(){
        this(null);
    }
    public Section(String name) {
        setName(name);
        mFields = new ArrayList();
    }
    public void setName(String name)
    {
        mName = name;
        if( mName == null){
            return;
        }
        mName = mName.trim();
    }
    public String getName(){
        return mName;
    }
    public void add(String field) {
        Field dupla = new Field(field);
        add(dupla);
    }

    public void add(Field field) {
        mFields.add(field);
    }

    public String getValue(String field) {
        Field var;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            if (var.getField().equalsIgnoreCase(field)) {
                return var.getValue();
            }

        }
        return null;

    }

    public String [] getFieldList() {
        String [] res = new String [mFields.size()];
        for (int i = 0; i < mFields.size(); i++) {
           res [i] = ((Field) mFields.get(i)).getField();
        }
        return res;
    }
    public String [] getValueList(String field){
        String [] res = null;
        ArrayList values =new ArrayList();
        Field var;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            if (var.getField().equalsIgnoreCase(field)) {
                values.add(var.getValue());
            }
        }
        res = new String [values.size()];
        
        for (int i = 0; i < res.length; i++) {
            res[i]=(String)values.get(i);
        }
       
        return res;
    }
    public void setValue(String field, String value)
    {
        Field var;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            if (var.getField().equalsIgnoreCase(field)) {
                var.setValue(value);
                return;
            }
        }        
        mFields.add(new Field(field,value));
    }
    public void setValue(String field, String [] value)
    {
        int pos=0;
        Field var;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            if (var.getField().equalsIgnoreCase(field)) {
                var.setValue(value[pos]);
                pos++;
            }
        }
        for (int i = pos; i < value.length; i++) {
            mFields.add(new Field(field,value[i]));
        }
    }
    public void addValue(String field, String value)
    {
        mFields.add(new Field(field,value));
    }
    public void addValue(String field)
    {
        mFields.add(new Field(field));
    }
    public void addValue(String field, String [] value)
    {
        for (int i = 0; i < value.length; i++) {
            mFields.add(new Field(field,value[i]));
        }
    }
    @Override
    public String toString()
    {
        String res = "";
        res=res+mName+": ";
        for (int i = 0; i < mFields.size(); i++) {
            res=res+((Field)mFields.get(i)).toString();
            if(i<mFields.size()-1)
            {
                res=res+",";
            }
        }

        return res;
    }
    public void toFile(RandomAccessFile file)
    {
        try {
            file.writeBytes("[" + mName + "]\r\n");
        } catch (IOException ex) {
            //LogFile.error("(Section-toFile): "+ex.getMessage());
        }
        Field elem = null;
        for (int i = 0; i < mFields.size(); i++) {
            elem=(Field)mFields.get(i);
            elem.toFile(file);
        }

    }
    public int size(){
        return mFields.size();
    }
}
