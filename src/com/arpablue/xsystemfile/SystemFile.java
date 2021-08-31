/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.xsystemfile;

import com.arpablue.tools.StringManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Administrator
 */
public class SystemFile {
    
    
    //Format and cleant the one path
    public static String formatPath(String path)
    {
        path=StringManager.replaceStr(path, "\\", "/");
        path=StringManager.replaceStr(path, "//", "/");
        return path;
        
    }
    //return the current work directory.
    public static String getWorkDirectory(){
        return System.getProperty("user.dir");
    }
    //Set in a valid format a name of any file
    public static String fomatFileName(String filename){
        String valid ="1234567890qwertyuiopasdfghjklñzxcvbnmQWERTYUIOPASDFGHJKLÑZXCVBNM.-_()[] ";
        String res ="";
        char [] target = filename.toCharArray();
        for (int i = 0; i < target.length; i++) {
            char c = target[i];
            if(valid.indexOf(c+"")>-1)
                res=res+c;
        }
        return res;
    }
    //move a folder to another folder
    public static boolean moveFolder(String source, String target){
        if(source == null)
            return false;
        if(target == null)
            return false;
        if(source.equalsIgnoreCase(target))
            return true;
        try{
            File s = new File(source);
            return moveFolder(s,target);
        }catch(Exception e){
            //LogFile.error("(SystemFile-moveFolder): "+e.getMessage());
        }
        return false;
    }
    public static boolean moveFolder(File source, String target){
        if(source == null)
            return false;
        if(target==null)
            return false;
        try{
            File s = source;
            if((!s.exists())||(!s.isDirectory()))
                return false;
            File t = new File(target);
            t.mkdirs();
            t = new File(target,s.getName());
            return s.renameTo(t);
        }catch(Exception e){
            //LogFile.error("(SystemFile-moveFolder): "+e.getMessage());
        }
        return false;
    }
   /**
    * It delf
    * @param file
    * @return 
    */ 
    public static boolean delete(String file){
        if ( file == null)
            return true;
        File f = new File(file);
        if(!f.exists())
            return true;
        return f.delete();
    }
    /**
     * It create the the folder that belong to the path.
     * @param path 
     */
    public static void mkdir(String path){
        File f = new File(path);
        f.mkdirs();

    }
    public static RandomAccessFile openWriter(String path){
        try {
            if (path == null) {
                return null;
            }
            File f = new File(path);
            RandomAccessFile res = null;
            if (f.exists() && f.isFile()) {
                f.delete();
            }
            res = new RandomAccessFile(f, "rw");
            return res;
        }
        catch (FileNotFoundException ex) {
            //Logger.getLogger(SystemFile.class.getName()).log(Level.SEVERE, null, ex);
            //LogFile.error("(SystemFile - openWriter): "+ex.getMessage());
        }
        return null;
    }
    
    public static boolean renameFiles(String path,String search, String replace){
        if(path==null)
            return false;
        if(search==null)
            return false;
        if(replace==null)
            replace="";
        return renameFiles(new File(path),search,replace);
    }
    public static boolean renameFile(File source, String destiny){
        if(source == null){
            return false;
        }
        if(destiny == null){
            return false;
        }
        boolean band=false;
        destiny=destiny.replace("\\", "/");
        band=destiny.indexOf("/")>-1;
        
        if(!band){
            destiny=source.getParent()+"/"+destiny;
            destiny=destiny.replace("\\", "/");
        }
        source.renameTo(new File(destiny));
        return true;
    }
    public static boolean renameFiles(File path,String search, String replace){
        if(search==null)
            return false;
        if(replace==null)
            replace="";
        try{
            if(!path.exists()){
                return false;
            }
            if(path.isFile()){
                return false;
            }
            File[] files = path.listFiles();
            String name=null;
            String r = replace.toLowerCase();
            String s = search.toLowerCase();
            boolean res=false;
            for (int i = 0; i < files.length; i++) {
                File file1 = files[i];
                if(file1.isDirectory()){
                    res=res||renameFiles(file1,search,replace);
                }else{
                    name=file1.getName().toLowerCase();
                    if(name.indexOf(s)>-1){
                        name=file1.getName().replace(search, replace);
                        renameFile(file1,name);
                    }
                }
            }
            return res;
        }catch(Exception e){
            //LogFile.error("SystemFile-renameFiles"+e.getMessage());
        }
        return false;
    }
    public static ArrayList<File> getFolderList(String path){
        if(path==null)
            return null;
        return getFolderList(new File(path));
    }
    public static ArrayList<File> getFolderList(File path){
        ArrayList<File> res=new ArrayList<File>();
        
        if(path==null){
                return null;
        }
        if(path.isFile()){
            return null;
        }
        File [] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if(f.isDirectory()){
                res.add(f);
            }
        }
        return res;
    }
    public static ArrayList<File> getFileList(String path){
        if(path==null)
            return null;
        return getFileList(new File(path));
    }
    public static ArrayList<File> getFileList(File path){
        ArrayList<File> res=new ArrayList<File>();
        
        if(path==null){
                return null;
        }
        if(path.isFile()){
            return null;
        }
        File [] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if(f.isFile()){
                res.add(f);
            }
        }
        return res;
    }
}
