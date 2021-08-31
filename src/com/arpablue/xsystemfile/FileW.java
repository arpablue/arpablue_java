/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import com.arpablue.interfaces.IOpenClose;
import com.arpablue.tools.StringManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

/**
 *
 * @author user-pc
 */
public class FileW extends FileB implements IOpenClose{
    protected BufferedWriter mFile = null;
    public FileW(){}
    public FileW( String pathFile ){
        setFile( pathFile );
    }
    /**
     * It Open a file to write text, if the file exists then is deleted, if the 
     * folders not exists then it are created.
     * @return It is true if the field has been created without problems.
     */
    @Override
    public boolean open() {
        try{
            if( !this.close()){
                return false;
            }
            if( this.getFile() == null ){
                return false;
            }
            if( this.getFile().length() < 1 ){
                return false;
            }
            File f = new File( this.getFile() );
            if( f.exists() && f.isFile() ){
                f.delete();
            }else{

                SystemFile.mkdir( f.getParent() );
            }
            
            mFile = new BufferedWriter(new OutputStreamWriter( new FileOutputStream( f.getAbsoluteFile() ), "utf-8"));
            mbOpen = true;
            
        }catch( Exception e){
            e.printStackTrace();
        }
        
        return false;
    }
    /**
     * It close the file.
     * @return It is true if the file has been closed without problems.
     */
    @Override
    public boolean close() {
        try{
            if( !this.isOpen()){
                return true;
            }
            if( mFile == null ){
                return true;
            }
            mFile.close();
            mbOpen = false;
            return true;
        }catch( Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * It write a text in the file.
     * @param line It is true if the text has been written without problems.
     */
    public boolean write(String line){
        if( !this.isOpen() ){
            return false;
        }
        if( mFile == null ){
            return false;
        }
        try{
            mFile.write(String.format( line + "\n") );
            return true;
        }catch( Exception e ){
            e.printStackTrace();
        }
        return false;
    }
}
