/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

/**
 *
 * @author root
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileSpeller extends FileBase{
    protected BufferedWriter mWriter;
    @Override
    public boolean open() {
        this.mIsOpen = false;
        try{
            mWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("filename.txt"), "utf-8")
            );
            this.mIsOpen = true;
        }catch(Exception e){
            setError("(FileWriter - open) - "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean close() {
        try{
            mWriter.close();
            this.mIsOpen = false;
        }catch(Exception e){
            setError("(FileSpeller - close) - "+e.getMessage());
            return  false;
        }
        return true;
    }

    @Override
    public boolean writeln(String line) {
        try{
            if(! this.mIsOpen ){
                setError("(FileSpeller - writeln) - The file has been not openned.");
                return false;
            }
            if(mWriter == null){
                setError("(FileSpeller - writeln) - The writer object cannot be null.");
                return false;
            }
            mWriter.write(line+"\r\n");
        }catch(Exception e){
            setError("(FileSpeller - close) - "+e.getMessage());
            return false;
        }
        return true;
    }
    
}
