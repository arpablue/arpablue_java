/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.html;

/**
 *
 * @author Socrates
 */
public class HtmlTool {
    /**
     * Search a string and replace for a new string..
     */
    public static String htmlCode(String target, String search, String code)
    {
        if(target == null)
        {
            return null;
        }
        if(code == null)
        {
            return target;
        }
        if(code == null)
        {
            return target;
        }
        target = target.replaceAll(search, code);
        return target;
    }
    /**
     * replace All EOF characters by --r----n--
     */
    public static String EOFreplace(String text){
        
        text = htmlCode(text,"\n","--n--");
        text = htmlCode(text,"\r","--r--");        
        return text;
    }
    /**
     * Revert the replacement did by EOFReplace.
     */
    public static String EOFreverce(String text){
        
        text = htmlCode(text,"--n--","\n");
        text = htmlCode(text,"--r--","\r");
        return text;
    }
    /**
     * Replace all special character with html codes.
     * 
     */
    public static String htmlCode(String line)
    {
        line = htmlCode(line,"&","&amp;");
        line = htmlCode(line,"<","&lt;");
        line = htmlCode(line,">","&gt;");
        line = htmlCode(line,"'","&#39;");
        line = htmlCode(line,"\"","&quot;");
        return line;
    }
    /**
     * Revert all special characters replacement.
     */
    public static String htmlCodeRevert(String line)
    {
        line = htmlCode(line,"&amp;","&");
        line = htmlCode(line,"&lt;","<");
        line = htmlCode(line,"&gt;",">");
        line = htmlCode(line,"&#39;","'");
        line = htmlCode(line,"&quot;","\"");
        return line;
    }
    
}
