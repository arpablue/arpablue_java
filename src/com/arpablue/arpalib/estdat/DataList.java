/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

import com.arpablue.arpalib.estdat.Data;
import java.util.ArrayList;

/**
 * This is a collection of object, where each object can be saved with a value, and 
 * @author augusto
 */
public class DataList extends ArrayList<Data>{
    public Data getElementByName(String name){
        if(name==null)
            return null;
        Data e =null;
        name=name.trim().toLowerCase();
        for(int i=0;i<this.size();i++){
            e = this.get(i);
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }
    public boolean search(String name){
        Data res = getElementByName(name);
        if(res!=null){
            return true;
        }
        return false;
        
    }
    /**
     * Return the value of one data founded by the name
     * @param name
     * @return 
     */
    public Object getValue(String name){
        Data res = getElementByName(name);
        if(res!=null){
            return null;
        }
        return res.getValue();
        
    }

    public Object getValue(int index){
        return this.get(index).getValue();
    }
    public String getName(int index){
        return this.get(index).getName();
    }
    public String getType(int index){
        return this.get(index).getType();
    }
    /**
     * Set the value to all data of the list, return false is not found any.
     * @param name
     * @return 
     */
    public boolean setValue(String name, Object value){
        Data data = getElementByName(name);
        if(data==null){
            data=new Data(name,value);
            this.add(data);
            return false;
        }
        data.setName(name);
        data.setValue(value);
        return true;
        
    }
    /**
     * Add a elements if is unique, if another element exists with the same 
     * name, then this is replaced.
     * @param data 
     */
    public void append(Data data){
        if(data==null)
            return;
        Data f = getElementByName(data.getName());
        if(f==null){
            this.add(data);
            return;
        }
        f.setData(data);
    }
    /**
     * Print in the screen all elements
     * 
     */
    public void print(){
        System.out.println("================================");
        System.out.println("Elements of the [DataList], size: "+size());
        System.out.println("--------------------------------");
        for(int i=0; i<size(); i++){
            System.out.println(i+") "+get(i));
        }
        System.out.println("=================================");
    }
    
    public String toStringValues(){
        return toStringValues(" :: ");
    }
    public String toStringValues(String separator){
        String res = "";
        for(int i=0; i<size(); i++){
            res=res+separator+get(i).getValue();
        }
        return res+separator;
    }
}
