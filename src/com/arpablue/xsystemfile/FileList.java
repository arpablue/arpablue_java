/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import java.util.ArrayList;
import com.arpablue.tools.StringManager;
import java.io.File;
import java.io.RandomAccessFile;

/**
 * It mabage the contain of a file like a list, each line of the file is a string element of the list
 * @author ASUS
 */
public class FileList extends ArrayList<String>{
    
    public FileList(){
        super();
    }
    /**
     * It valid if the path of the file is valid for a path 
     * @param filePath
     * @return 
     */
    protected boolean isValidFile( String filePath ){
        try{
        if( filePath == null ){
            return false;
        }
        if( StringManager.isEmpty(filePath) ){
            return false;
        }
            
        }catch( Exception e){
            return false;
        }
        return true;
    }
    /**
     * It load the content of a file, where each line is an element, all previous elements are removed.
     * @param filePath it is the path of the file.
     * @return it is true if the file has been loaded without problems.
     */
    public boolean load( String filePath ){
        this.clear();
        try{
            if( !isValidFile( filePath )){
                return false;
            }
            File file = new File( filePath );
            if( !file.exists() ){
                return false;
            }
            if( !file.isFile() ){
                return false;
            }
            RandomAccessFile reader = new RandomAccessFile( file, "r" );
            String line = "";
            while( line != null){
                line = reader.readLine();
                if( line != null){
                    this.add(line);
                }
            }
            reader.close();
        }catch( Exception e){
            return false;
        }
        return true;
    }
    /**
     * It save the content of the list in a file, each element of the list is a line in a file.
     * @param filePath it is the path of the file where the content of list will be write.
     * @return It is true the file has been created without problems.
     */
    public boolean save( String filePath ){
        try{
            if( !isValidFile( filePath )){
                return false;
            }
            File file = new File( filePath );
            if( !file.exists() && file.isFile() ){
                if( !file.delete() ){
                    return false;
                }
            }
            boolean flag = false;
            RandomAccessFile writer = new RandomAccessFile( file, "rw");
            for( String e : this ){
               
                if( flag ){
                    writer.writeBytes("\n");
                }else{
                    flag = true;
                }
                writer.writeBytes(e);
                
            }
            writer.close();
        }catch(Exception e){
            return false;
        }
        return true;
    }
    /**
     * It compare the current elements with another FileList structure.
     * @param target It is another FileList to compare the current structure.
     * @return It is true if the current ekements are equaks to another FileList.
     */
    public boolean compare( FileList target ){
        if( target == null ){
            return false;
        }
        if( target.size() != this.size() ){
            return false;
        }
        for( int i = 0; i < this.size(); i++ ){
            if( !this.get(i).equals( target.get(i) )){
                return false;
            }
        }
        return true;
    }
    /**
     * It compare the current elements with another FileList structure.
     * @param target It is another FileList to compare the current structure.
     * @return It is true if the current ekements are equaks to another FileList.
     */
    public boolean compareIgnoreCase( FileList target ){
        if( target == null ){
            return false;
        }
        if( target.size() != this.size() ){
            return false;
        }
        for( int i = 0; i < this.size(); i++ ){
            if( !this.get(i).equalsIgnoreCase( target.get(i) )){
                return false;
            }
        }
        return true;
    }
    /**
     * It verifye if an string in the target object contains in the string of the same position.
     * @param target It is the sub-string object to verify if exist in th ecurrent strings.
     * @return It is true, all elements of the target object are presents in the string of the current object.
     */
    public boolean contains( FileList target ){
        if( target == null ){
            return false;
        }
        if( target.size() != this.size() ){
            return false;
        }
        for( int i = 0; i < this.size(); i++ ){
            if( !this.get(i).contains(target.get(i) )){
                return false;
            }
        }
        return true;
    }
    /**
     * It return a FileList object with the same objects
     * @return It is an object with the same element of the current object.
     */
    public FileList clone(){
        FileList res = new FileList();
        for( String e : this){
            res.add(e);
        }
        return res;
    }
    /**
     * It set the elements of the File list into a string.
     * @return 
     */
    @Override
    public String toString(){
        String res = "[";
        boolean flag = false;
        for( String e: this){
            if( flag ){
                    res += ",";
            }else{
                flag = true;
            }
            res += e;
        }
        res += "]";
        return res;
    }
    /**
     * It print the contain of the list in the screen.
     */
    public void println(){
        System.out.println("--- FileList ---");
        int pos = 0;
        for( String e: this){
            System.out.println(pos + " - "+e);
            pos++;
        }
        System.out.println("--------------");
    }
}
