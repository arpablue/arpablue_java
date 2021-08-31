/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools.treepath;

import com.arpablue.interfaces.ILoadSave;
import com.arpablue.tools.StringManager;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author Augusto Flores
 */
public class TreePath extends TreePathMng implements ILoadSave {

    public TreePath() {
        super();
    }

    /**
     * It save the content of the file in a file, if the fiule exists then it is
     * removed to crewate a new file.
     *
     * @param filePath It is the path of the file.
     * @return It is true if the file has been created woithout problem.
     */
    @Override
    public boolean save(String filePath) {
        if (filePath == null) {
            setError("It is not possible delete a NULL file.");
            return false;
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                if (file.isFile()) {
                    if (!file.delete()) {
                        setError("It is not possible delete the ["+filePath+"] file.");
                        return false;
                    }
                }
            }
            RandomAccessFile r = new RandomAccessFile(file, "rw");
            r.writeBytes(this.toPrint());
            r.close();
        } catch (Exception e) {
            
            this.setError(e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * It load the the content of a JSON file in the current structure.
     * @param filePath It is the JSON file path.
     * @return It is true if the content of the file has been loaded and the 
     */
    @Override
    public boolean load(String filePath) {
        String str = this.loadFile(filePath);
        if( str == null ){
            return false;
        }
        if( str.length() < 1 ){
            return false;
        }
        System.out.println( str );
        return this.parse( str );
    }
    /**
     * It load the content of a file, if the file not exists then a null vallue is returned..
     * @param file It i sthe path of the file to be loaded-
     * @return It is the string of the content of the string.
     */
    protected String loadFile(String filePath){
        String res = "";
        if( filePath == null ){
            return null;
        }
        if( filePath.length() <  1){
            return null;
        }
        try{
            File file = new File( filePath );
            
            if( !file.exists() ){  return null;   }
            if( !file.isFile() ){   return null;   }
            RandomAccessFile f = new RandomAccessFile( file, "rw");
            String line = "";
            
            while( line != null ){
                line = f.readLine();
                if( line != null){
                    res = res + " " + line;
                }
            }
            f.close();
        }catch( Exception e){
            this.setError(e.getMessage());
            return null;
        }
        return res;
    }
            
}
