/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import com.arpablue.interfaces.IOpenClose;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * It open a file an read the content, the class has the capability to return the file content
 * in an ArrayLis<String> object, or return the lines that match with a patron. 
 * @author Augusto Flores
 */
public class FileR extends FileB{
    protected BufferedReader  mFile = null;
    protected boolean mbEOF = false;
    /**
     * It verify the file exists and open the file to be read.
     * @return It is true if the file has been opened without problems.
     */
    @Override
    public boolean open() {
        try{
            this.mbEOF = true;
            if( this.getFile() == null ){
                return false;
            }
            File f = new File( this.getFile() );
            if( !f.exists() ){
                return false;
            }
            if( !f.isFile() ){
                return false;
            }
            mFile = new BufferedReader(new FileReader( this.getFile() ));
            mbOpen = true;
            this.mbEOF = false;
            return true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * It close the opened file.
     * @return It is true if the file has been closed without problems.
     */
    @Override
    public boolean close() {
        try{
            if( !this.isOpen() ){
                return true;
            }
            if( this.mFile == null ){
                return true;
            }
            this.mFile.close();
            mbOpen = true;
            this.mbEOF = true;
            return true;
        }catch( Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * It read a line of the current file, if the file is closed or is end of 
     * file then return null.
     * @return It is the read line, if is closed or end of file then return null.
     */
    public String readLine(){
        try{
            if(!this.isOpen()){
                return null;
            }
            String line = this.mFile.readLine();
            if( line == null ){
                this.mbEOF = true;
            }
            return line;
        }catch( Exception e ){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * It get if the file read all lines of the file.
     * @return It is true if is end of file.
     */
    public boolean isEOF(){
        return this.mbEOF;
    }
}
