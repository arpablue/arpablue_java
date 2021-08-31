/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import com.arpablue.interfaces.ILogger;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author augusto
 */
public class LogFile implements ILogger {
    /**
     * It is the path of the log file where will be created.
     */
    protected String mFile;
    /**
     * It is the current log file generated.
     */
    protected String mCurrentFile;
    /**
     * It specify the current date in the log file when a log is written.
     */
    protected boolean mUseDateFileName = true;
    /**
     * This is the default file path where the log file will be created.
     */
    protected static String DEFAULT_LOG_PATH = "./activity";
    /**
     * This is the level to write the message
     */
    public static int DEEP = 7;
    /**
     * This is the full format of the date, it is the date + hour.
     */
    protected static DateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    /**
     * It is the format of the date.
     */
    protected static DateFormat mTime = new SimpleDateFormat("yyyy.MM.dd");
    /**
     * It is the simple format for the full date, it is used for the name of the log file.
     */
    protected static DateFormat mTimeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
    
    /**
     * It is the deffault constructor.
     */
    public LogFile() { 
        this( DEFAULT_LOG_PATH );
    }
    /**
     * It create a object with a reference to the log file.
     * @param file IT is the path of the 
     */
    public LogFile( String file){
        this( file, true );
    }
    /**
     * It constructor specify where the log file will be created and if is necesary add the date to the log file name.
     * @param file It is the file of the log file.
     * @param useDateFileName It is true then the date will be add to the file log name.
     */
    public LogFile(String file, boolean useDateFileName) {
        setFile( file );
        this.useDateFileName(useDateFileName);
    }
    /**
     * It constructor specify where the log file will be created and if is necesary add the date to the log file name.
     * @param file It is the file of the log file.
     * @param useDateFileName It is true then the date will be add to the file log name.
     * @param deleteFile It delete any previous log file with the same name.
     */
    public LogFile(String file, boolean useDateFileName, boolean deleteFile) {
        setFile( file );
        this.useDateFileName(useDateFileName);
        if( deleteFile ){
            this.delete();
        }
    }
    /**
     * It remove the current log file.
     * @return It is true if the file has been removed withtou problems.
     */
    public boolean delete(){
        String f = this.getFile();
        try{
            File file = new File( f );{
            if( !file.exists() ){
                return true;
            }
            if( !file.isFile()){
                return true;
            }
            return file.delete();
        }
        }catch(Exception e){}
        return false;
    }
    /**
     * It specify the log file use the current date in the name of the file to write the log, 
     * @param status It is true then the date willbe added to the end of the file.
     */
    public void useDateFileName(boolean status){
        this.mUseDateFileName = status;
    }
    public boolean getUseDateFileName(){
        return this.mUseDateFileName;
    }
    /**
     * It write a endline.
     */
    @Override
    public void writeln() {
        println("");
    }
    /**
     * It write a string in the output file with a next line character..
     * @param msg It is the string to wrtile.
     */
    protected synchronized void println(String msg) {
        if (mFile == null) {
            mFile = DEFAULT_LOG_PATH;
        }
        if( mUseDateFileName ){
            mCurrentFile = mFile + "_" + mTime.format(new Date()) + ".log";
        }else{
            mCurrentFile = mFile +".log";
        }        
        try {
            boolean append = true;
            FileWriter fw = new FileWriter(mCurrentFile, append);
            fw.write(mDateFormat.format(new Date()) + " - " + msg + "\r\n");//appends the string to the file 
            fw.close();
        } catch (Exception e) {
            System.out.println("Error (Logger - write): " + e.getMessage());
            System.out.println("\t\t" + e.getLocalizedMessage());
        }
    }
    /**
     * It return the current file used.
     * @return It is the current file.
     */
    protected String getFile(){
        if( mUseDateFileName ){
            mCurrentFile = mFile + "_" + mTime.format(new Date()) + ".log";
        }else{
            mCurrentFile = mFile +".log";
        }
        return mCurrentFile;
    }
    /**
     * It write a string in the output file wirthout next line character at the end of the line.
     * @param msg It is the string to write in the log file.
     */
    protected synchronized void print(String msg) {
        if (mFile == null) {
            mFile = DEFAULT_LOG_PATH;
        }
        getFile();
        try {
            boolean append = true;
            FileWriter fw = new FileWriter(mCurrentFile, append);
            fw.write(msg);//appends the string to the file 
            fw.close();
        } catch (Exception e) {
            System.out.println("Error (Logger - write): " + e.getMessage());
            System.out.println("\t\t" + e.getLocalizedMessage());
        }
    }
    /**
     * Write a message in the log file, with a number of tabs in front of the
     * message, this number is specified by level, if level is a negative number
     * then the level will be converted to positive number.
     *
     * @param level the number of tabs that will be put in front of the message
     * @param msg the message that will be written in the log file.
     */
    public void writeln(int level, String msg) {

        if (level < 0) {
            level = level * -1;
        }
        if (level > LogFile.DEEP) {
            return;
        }
        for(int i=0; i< level; i++)
        {
            msg = "\t"+msg;
        }
        println(msg);
    }
    /**
     * It write a message with a lkevel in the log.
     * @param level It is the level of the messafe.
     * @param msg It is the text to be writer in the log file.
     */
    public void write(int level, String msg) {

        if (level < 0) {
            level = level * -1;
        }
        if (level >= LogFile.DEEP) {
            return;
        }
        print(msg);
    }
    /**
     * This write a text in the log file without next line character to the end of the text.
     * @param text It is the message to write inthe log file.
     */
    @Override
    public void write( String text ){
        print( text );
    }
    /**
     * This write a text in the log file with next line character to the end of the text.
     * @param text It is the message to write inthe log file.
     */
    @Override
    public void writeln( String text ){
        println( text );
    }

    /**
     * Write an action in the log file.
     * @param msg
     */
    public void action(String msg) {
        writeln(3, "ACTION: " + msg);
    }

    /**
     * Write a step in the log file.
     * @param msg
     */
    public synchronized void step(String msg) {
        writeln(4, "STEP: " + msg);
    }

    /**
     * Write a title.
     * @param msg
     */
    public synchronized void title(String msg) {
        writeln(0, "---------------- " + msg + " ----------------");
    }

    /**
     * Write a fail message.
     * @param msg
     */
    public synchronized void fail(String msg) {
        writeln(2, "FAIL: " + msg);
    }

    /**
     * Write a pass message.
     * @param msg
     */
    public synchronized void pass(String msg) {
        writeln(2, "PASS: " + msg);
    }

    /**
     * Write a error message.
     * @param msg
     */
    public synchronized void error(String msg) {
        writeln(1, "ERROR: " + msg);
    }
    /**
     * It write a message as exception.
     * @param msg 
     */
    public synchronized void exception(String msg) {
        writeln(1, "EXCEPTION: " + msg);
    }

    /**
     * Write in the log file a QUERY.
     * @param msg  It is the string to be written.
     */
    public synchronized void query(String msg) {
        writeln(6, "QUERY: " + msg);
    }

    /**
     * Write a success message.
     * @param msg  It is the string to be written.
     */
    public synchronized void success(String msg) {
        writeln(1, "SUCCESS: " + msg);
    }

    /**
     * Write a success message.
     * @param msg It is the string to be written.
     */
    public synchronized void message(String msg) {
        writeln(1, "MESSAGE: " + msg);
    }

    /**
     * Write a warning message.
     * @param msg  It is the string to be written.
     */
    public synchronized void warning(String msg) {
        writeln(1, "WARNING: " + msg);
    }
    /**
     * It specify the path of the log file
     * @param file It is the new log file
     */
    public synchronized void setFile(String file) {
        mFile = file;
    }
}
