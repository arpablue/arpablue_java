/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;

import com.arpablue.tools.StringManager;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class IniFile extends IniFileBehaivor{
    
    /**
     * Default constructor.
     */
    public IniFile(){
        super();
    }

    /**
     * It constructor receive the ini file path.
     * @param file
     */
    public IniFile( String file ){
        this();
        load( file );
    }
    /**
     * It create a file with a INI file structure, if the file exists then is overwrite with the new content.
     * @param file It is the path of the file.
     * @return It is true if the file has been created without problems.
     */
    public boolean save(String file){
        try{
            if( StringManager.isEmpty(file)){
                return false;
            }
            file = file.trim();
            File f = new File( file );
            if(  f.exists()  && f.isFile() ){
                if( !f.delete() ){
                    return false;
                }
                
            }
            RandomAccessFile writer = new RandomAccessFile(f, "rw");
            save( writer );
            writer.close();
        }catch( Exception e){}
        return true;
    }
    /**
     * It write the Ini File structure in a RandomAccessFile object.
     * @param writer It is the writer of the file.
     * @return  It is true then the structure has been saved successfully.
     */
    protected boolean save(RandomAccessFile writer){
        try{
            for( IniFileSection section: this.mSections){
                writer.writeBytes( section.toString() +"\n");
            }
            
        }catch(Exception e){
            return false;
        }
        return true;
    }
    /**
     * It load the data of the ini file specified.
     * @param file It is the ini file to loaded.
     * @return If the all data has been loaded without problems then return true, if return false, means that no data or sonme data has been loaded, the errors can be get using getErrors() method.
     */
    public boolean load( String file ){
        this.setPathFile( file );
        return load();
    }
    /**
     * It load the data from the ini file.
     */
    protected boolean  load(){
        if( this.mFile == null ){
            return false;
        }
        this.mFile = this.mFile.trim();
        if( this.mFile.length() < 1){
            return false;
        }
        try{
            File file = new File( this.mFile );
            if( !file.exists() ){
                return false;
            }
            if( !file.isFile() ){
                return false;
            }
            RandomAccessFile reader;

            reader = new RandomAccessFile(file, "r");
            load( reader );
            reader.close();
        }catch( Exception e){
            return false;
        }
        return true;
    }
    /**
     * It read each line of the file to be processed.
     * @param reader It is the reader of the file.
     * @return  It is true if the file has been 
     */
    protected boolean  load(RandomAccessFile reader){
        try{
            String line = "";
            String section = "";
            String s = "";
           do{
               line = reader.readLine();
               
               if( line != null ){
                    line = line.trim();
                    if( !StringManager.isEmpty(line) ){
                        if( !isComment( line )){
                            s = IniFileSection.getSectionName(line);
                            if( s == null ){
                                IniFileAttribute e = IniFileAttribute.parse(line);
                                if( e != null ){
                                    this.set(section+"/"+e.getName(), e.getValue());
                                }
                            }else{
                                section = s;
                            }
                        }
                    }
               }
           }while( line!= null );
           return true;
        }catch(Exception e){ }
        return false;
    }
    /**
     * It return true of the line is a comment.
     * @param line It is the string to verify of is a string.
     * @return It is true if the line begins with a ;
     */
    protected boolean isComment( String line ){
        if( line == null ){
            return false;
        }
        return  line.charAt(0) == ';';
    }
}
