/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.html;

import com.arpablue.tools.StringManager;
import com.arpablue.xsystemfile.FileAdm;
import com.arpablue.xsystemfile.LogFile;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class HtmlTable {
    public static void setInTableVectorArray(Vector e,RandomAccessFile w){
        setInTableVectorArray(e,null,w);
    }
    public static void setInTableVectorArray(Vector e,String head,RandomAccessFile w){
        try {
            String[] elem;
            w.writeBytes("<table border='1'>\r\n");
            if(head!=null){
                w.writeBytes("\t<tr>\r\n");
                w.writeBytes("\t/t"+head+"\r\n");
                w.writeBytes("\t</tr>\r\n");
            }
            for (int i = 0; i < e.size(); i++) {
                w.writeBytes("\t<tr>\r\n");
                elem = (String[]) e.get(i);

                w.writeBytes("\t\t<td>"+StringManager.toString(elem, "</td><td>")+"</td>\r\n");
                w.writeBytes("\t</tr>\r\n");
            }
            w.writeBytes("</table>\r\n");
        } catch (IOException ex) {
            //LogFile.error("(HtmlTable-setInTableVectorArray):"+ex.getMessage());
        }
    }
    public static void setInTableVectorArray(Vector e,FileAdm w){
        setInTableVectorArray(e,null,w);
    }
    public static void setInTableVectorArray(Vector e,String head,FileAdm w){
        String[] elem;
        w.writeln("<table border='1'>");
        if(head!=null){
            w.writeln("\t<tr>");
            w.writeln("\t\t"+head);
            w.writeln("\t</tr>");
        }
        for (int i = 0; i < e.size(); i++) {
            w.writeln("\t<tr>");
            elem = (String[]) e.get(i);

            w.writeln("\t\t<td>"+StringManager.toString(elem, "</td><td>")+"</td>");
            w.writeln("\t</tr>");
        }
        w.writeln("</table>");
    }
    public static void setVectorString(String head,Vector target, FileAdm w){
        String[] elem=null;
        String [] columns=null;
        w.writeln("<table border='1'>");
        if(head!=null){
            columns = head.split(",");
            w.writeln("\t<tr>");
            for (int i = 0; i < columns.length; i++) {
                String string = columns[i];
                w.writeln("\t\t<td>"+head+"</td>");
            }
            w.writeln("\t</tr>");
        }
        for (int i = 0; i < target.size(); i++) {
            w.writeln("\t<tr>");
            elem = (String[]) target.get(i);

            w.writeln("\t\t<td>"+StringManager.toString(elem, "</td>\r\n\t\t<td>")+"</td>");
            w.writeln("\t</tr>");
        }
        w.writeln("</table>");
    }
        
    
}
