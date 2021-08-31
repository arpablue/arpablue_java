/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.xsystemfile;

import com.arpablue.interfaces.IOpenClose;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * It Open a file to write
 * @author Administrator
 */
public class FileAdm implements IOpenClose{
    /**
     * It is the output to the file.
     */
    protected RandomAccessFile mWrite;
    /**
     * It contain the path of the file.
     */
    protected String mPath;
    /**
     * It contains the status of the file.
     */
    protected boolean mIsOpen;
    public FileAdm(){
        mIsOpen = false;
    }
    /**
     * It return the current path of the file.
     * @return 
     */
    public String getPaht(){ return mPath;}
    /**
     * It soecify the writer to be used to write the message.
     * @param writer It is the output used to write the messages.
     */
    public void setWriter(RandomAccessFile writer){
        ///close();
        mWrite = writer;
    }
    /**
     * It create the file to be written, if the file exists then this is deleted to create a new file.
     * @return It is true if the file has been open without problems.
     */
    public boolean open(){ return open(mPath); }
    /**
     * It open a file in the path specified, if the file exists then it is deleted to create a new file.
     * @param path It is the path of the file.
     * @return It is true if the file has been openned without problems.
     */
    public boolean open(String path){
        if( isOpen() ){
            return true;
        }
        try{
            mPath = path;
            if(mWrite !=null)
                mWrite.close();
            File f = new File(path);
            if( f.exists())
                f.delete();
            mWrite = new RandomAccessFile(path,"rw");
            mIsOpen = true;
        }catch(Exception e){
            mIsOpen = false;
            //LogFile.error("(FileAdm - open):"+e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * It close the file.
     * @return it is true if the file has been closed without problems.
     */
    public boolean close(){
        try{
            if(mWrite==null)
                mWrite.close();
            mIsOpen = false;
        }catch(Exception e){
            //LogFile.error("FileAdm - close):"+e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * It write a text in the file with a next line character at the end of the text.
     * @param line this is the text to write inthe file.
     */
    public void writeln(String line){
        try {
            if( mWrite == null ){
                return;
            }
            mWrite.writeBytes(line + "\r\n");
        } catch (IOException ex) {
            //LogFile.error("(FileAdm - writeln):"+ex.getMessage());
        }
    }
    /**
     * Write a text in the file, this is without a next line character at the end of the text.
     * @param line It is the text to be write in the text.
     */
    public void write(String line){
        try {
            if( mWrite == null ){
                return;
            }
            mWrite.writeBytes(line);
        } catch (IOException ex) {
            //LogFile.error("(FileAdm - writeln):"+ex.getMessage());
        }
    }
    /**
     * It verify if the file is open to be write.
     * @return true if the file is open and ready to write messages.
     */
    @Override
    public boolean isOpen() {
        return mIsOpen;
    }
}
