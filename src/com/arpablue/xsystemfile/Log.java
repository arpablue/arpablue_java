/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import com.arpablue.xsystemfile.LogFile;
import java.io.File;
/**
 *
 * @author augusto
 */
public class Log {
    protected static LogFile mError = new LogFile("c:/error");
    protected static LogFile mActivity = new LogFile("c:/activity");
    
    public static boolean setLogActivityFile(String path){
        if(path==null){
            mActivity=null;
            return true;
        }
        if(!pathExists(path))
            return false;
        mActivity=  new LogFile(path);
        return true;
    }
    public static boolean setLogErrorFile(String path){
        if(path==null){
            mError=null;
            return true;
        }
        if(!pathExists(path))
            return false;
        mError=  new LogFile(path);
        return true;
    }
    public static void level(int level, String msg){
        mActivity.writeln(level, msg);
    }
    public static void setLimitLevel(int level){
        LogFile.DEEP = level;
    }
    public static void title(String title){
        String add = "----------------------";
        level(0,add+title+add);
    }
    public static void step(String msg){
        level(4,msg);
    }
    public static void action(String msg){
        level(3,msg);
    }
    public static void error(String text){
        if(mError!=null)
            mError.writeln("ERROR: "+text);
        level(0,"ERROR: "+text);
    }
    public static void fail(String text){
        if(mError!=null)
            mError.writeln("FAIL: "+text);
        level(1,"FAIL: "+text);
    }
    public static void pass(String text){
        level(0,"PASS: "+text);
    }
    public static void msg(String text){
        if(mActivity!=null)
            level(2,"MESSAGE "+text);
    }
    public static void warning(String text){
        if(mActivity!=null)
            level(1,"WARNING: "+text);
    }
    public static boolean pathExists(String path){
        try{
            File f = new File(path);
            if(!f.exists())
                return false;
            return true;
        }catch(Exception e){
            error("(Log-pathExists): "+e.getMessage());
        }
        return false;
    }
}
