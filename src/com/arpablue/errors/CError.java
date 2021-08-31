/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.errors;

import com.arpablue.errors.ErrorCollector;
import com.arpablue.interfaces.IError;

/**
 *
 * @author ASUS
 */
public class CError implements IError{
    protected ErrorCollector mErrors;
    public CError(){
        super();
        mErrors = new ErrorCollector();
    }
            

    @Override
    public void setError(String msg) {
        mErrors.setError(msg);
        
    }

    @Override
    public String getError() {
        return mErrors.getError( mErrors.sizeErrors()-1 );
    }

    @Override
    public String getError(int index) {
        return mErrors.getError(index);
    }

    @Override
    public String getErrors() {
        return mErrors.getErrors();
    }

    @Override
    public void clearErrors() {
        this.mErrors.clearErrors();
    }

    @Override
    public boolean isError() {
        return this.mErrors.sizeErrors() == 0;
    }

    @Override
    public int sizeErrors() {
        return this.mErrors.sizeErrors();
    }
    
}
