/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import java.io.File;

/**
 *
 * @author root
 */
public abstract class FileBase {
    // It contains the file path to be open
    protected String mFilePath = null;
    // It contains the status of the file, if is open.
    protected boolean mIsOpen = false;
    // It contains any error that happen in the file process
    protected String mError = null;
    
    //////////////////////////// Abstract Methos //////////////////////
    /**
     * Author; safa
     * Date: 2016-03-13: Open the file to start add the lines
     */
    public abstract boolean open();
    /**
     * Author: safa
     * Date: 2016-03-13 Close the file.
     */
    public abstract boolean close();
    /**
     * Author: safa
     * Date: 2013-03-13: Write a line text in the file, return true if the line has been writer successfully.
     */
    public abstract boolean writeln(String line);
    
    //////////////////////////////////Methods//////////////////////////
    /**
     * Author: safa
     * Date: 2016-03-16: Specify the file to be write.
     */
    public void setFile( String path ){
        this.mFilePath = path;
    }
    /**
     * Author: safa
     * Date: 2016-03-13: return the path file of the file.ç
     */
    public String getFile(){ return this.mFilePath; }
    /**
     * Author: safa
     * Date: It return the last error message that happen during the process,
     * if no errors has been raised then return null.
     */
    public String getError(){ return mError; }
    /**
     * Author: safa
     * Date: Specify the error message during the execution.
     */
    public void setError( String error){ mError = error; }
    /**
     * Author: safe
     * Date: 2016-03-13: Delete the current file, if the file is open then is closed and after that is deleted
     *  return true if the file has been delete successfully.
     */
    public boolean delete(){
        if(!exists()){
            return false;
        }
        File f = new File(mFilePath);
        try{
            f.delete();
        }catch(Exception e){
            mError = "(FileBase - delete) - "+e.getMessage();
            return false;
        }
        return true;
    }
    /**
     * Author: safa
     * Date: 2016-043-13: It validate if the file is not null exists and is a file
     */
    public boolean exists(){
        if(mFilePath == null ){
            setError("(FileBase - delete) - The [pathFile] is null");
            return false;
        }
        File f = new File(mFilePath);
        if(!f.exists())
        {
            return false;
        }
        if(!f.isFile())
        {
            return false;
        }
        return true;
    }
            
}
