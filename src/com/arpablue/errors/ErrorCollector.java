/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.errors;

import com.arpablue.interfaces.IError;
import java.util.ArrayList;

/**
 * It collect the errors message genrated during the execution of the project.
 * @author Augusto Flores
 */
public class ErrorCollector implements IError{
    protected ArrayList<String> mList; // It i sthe list of error message collected.
    /**
     * Default constructor.
     */
    public ErrorCollector(){
        mList = new ArrayList<String>();
    }
    @Override
    public void setError(String msg) {
        if( msg == null ){
            return;
        }
        msg = msg.trim();
       if( msg.length() < 1 ){
           return;
       }
        mList.add(msg);
    }
    @Override
    public String getError() {
        if( sizeErrors() < 1 ){
            return null;
        }
        return getError(mList.size() - 1);
    }

    @Override
    public String getError(int index) {
        if( index < 0 ){ return null; }
        if( index > sizeErrors() ){ return null; }
        return mList.get(index);
        
    }

    @Override
    public String getErrors() {
        if( sizeErrors()< 1 ){
            return "";
        }
        String res = "";
        for( String e : mList ){
            res += e + "\n";
        }
        return res;
    }

    @Override
    public void clearErrors() {
        mList.clear();
    }

    @Override
    public boolean isError() {
        return (sizeErrors() > 0);
    }

    @Override
    public int sizeErrors() {
        return mList.size();
    }
    @Override
    public String toString(){
        return this.getErrors();
    }
    
}
