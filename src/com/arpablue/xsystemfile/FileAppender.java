/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
/**
 *
 * @author root
 */
public class FileAppender extends FileBase{
    

    @Override
    public boolean open() {
        this.mIsOpen = true;
        return true;
    }

    @Override
    public boolean close() {
        this.mIsOpen = false;
        return true;
    }

    @Override
    public boolean writeln(String line) {
        try{
            Writer output= null;
            output = new BufferedWriter(new FileWriter(this.mFilePath, true));  
            output.append(line+"\r\n");
            output.close();        
        }catch( Exception e){
            setError("(FileAppender - writeln)"+e.getMessage());
            return false;
        }
        return true;
    }
    
}
