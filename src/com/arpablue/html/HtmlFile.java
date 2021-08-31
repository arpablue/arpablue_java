/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.html;

import com.arpablue.tools.Day;
import com.arpablue.xsystemfile.LogFile;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contain functions to set a field from database in a specific format in html,
 *
 * @author Administrator
 */
public class HtmlFile {
    public static final String ENDLN="\r\n";
    public static final String [] MONTHS={
                                            "January",
                                            "February",
                                            "March",
                                            "April",
                                            "May",
                                            "June",
                                            "July",
                                            "August",
                                            "September",
                                            "October",
                                            "November",
                                            "December"
                                        };
    public static final String [] DAYS={"Monday",
                                           "Tuesday",
                                           "Wednesday",
                                           "Thursday",
                                           "Friday",
                                           "Saturday",
                                           "Sunday"
                                        };
    /**
     * Set the name and the id using the name
     */

    public static String setNameId(String name){
        if(name==null){
            return "";
        }
        return "name='"+name+"' id='"+name+"'";
    }
    /**
     * identify the type of the field and set the correct tag.
     *
     */
    public static void inputType(RandomAccessFile target, String type, String name,String value)  {
        HtmlFile.inputType(target, type, name, value, null);
    }
    /**
     * identify the type of the field and set the correct tag.
     *
     */
    public static void inputType(RandomAccessFile target, String type, String name,String value,String attb)  {
        if(type.equalsIgnoreCase("text")){
            HtmlFile.inputTextArea(target, name, value,attb);
            return;
        }
        if(type.equalsIgnoreCase("password")){
            HtmlFile.inputPassword(target, name, value,attb);
            return;
        }
        if(type.equalsIgnoreCase("date")){
            HtmlFile.inputDate(target, name, value,attb);
            return;
        }
        if(type.equalsIgnoreCase("time")){
            HtmlFile.inputTime(target, name, value,attb);
            return;
        }
        if(type.equalsIgnoreCase("datetime")){
            HtmlFile.inputDateTime(target, name, value,attb);
            return;
        }
        if(type.equalsIgnoreCase("file")){
            HtmlFile.inputFile(target, name, value,attb);
            return;
        }
        inputText(target,name,value,attb);
    }
    /**
     * Create a input text field for a form.
     */
    public static void inputTag(RandomAccessFile target,String name,String type,String value,String attb){
        try{
            if(target==null){
                return;
            }
            String res="<input type='"+type+"' "+setNameId(name);
            if(attb!=null){
                res+=" "+attb;
            }
            if(value!=null){
                res+=" value='"+value+"'";
            }
            res+=">";
        target.writeBytes(res );
        }catch(IOException ex){
            System.err.println("ERROR (HtmlFile-inputType): "+ex.getMessage());
        }
    }
    /**
     * Create a input text field for a form.
     */
    public static void inputText(RandomAccessFile target,String name,String value){
        inputTag(target,name,"text",value,null);
    }
    /**
     * Create a input text field for a form.
     */
    public static void inputText(RandomAccessFile target,String name,String value,String attb){
        inputTag(target,name,"text",value,attb);
    }
    /**
     * Create a input text field for a form.
     */
    public static void inputFile(RandomAccessFile target,String name,String value){
        inputTag(target,name,"file",value,null);
    }
    /**
     * Create a input text field for a form.
     */
    public static void inputFile(RandomAccessFile target,String name,String value,String attb){
        inputTag(target,name,"file",value,attb);
    }
    /**
     * Create a input text field for a form.
     */
    public static void inputPassword(RandomAccessFile target,String name,String value){
        inputTag(target,name,"password",value,null);
    }
    /**
     * Create a input text field for a form.
     */
    public static void inputPassword(RandomAccessFile target,String name,String value,String attb){
        inputTag(target,name,"password",value,attb);
    }
    /**
     * Create a input hidden field for a form.
     */
    public static void inputHidden(RandomAccessFile target,String name,String value){
        inputTag(target,name,"hidden",value,null);
    }
    /**
     * Create a input hidden field for a form.
     */
    public static void inputButton(RandomAccessFile target,String name,String value){
        inputTag(target,name,"button",value,null);
    }
    /**
     * Create a input hidden field for a form.
     */
    public static void inputButton(RandomAccessFile target,String name,String value,String attb){
        inputTag(target,name,"button",value,attb);
    }
    /**
     * Create a input hidden field for a form.
     */
    public static void inputSubmit(RandomAccessFile target,String name,String value){
        inputTag(target,name,"submit",value,null);
    }
    /**
     * Create a input hidden field for a form.
     */
    public static void inputSubmit(RandomAccessFile target,String name,String value,String attb){
        inputTag(target,name,"submit",value,attb);
    }
    /**
     * Create a input hidden field for a form.
     */
    public static void inputReset(RandomAccessFile target,String name,String value){
        inputTag(target,name,"reset",value,null);
    }
    /**
     * Create a input hidden field for a form.
     */
    public static void inputReset(RandomAccessFile target,String name,String value,String attb){
        inputTag(target,name,"reset",value,attb);
    }
    /**
     * Create a field with TextArea tag.
     */
    public static void inputTextArea(RandomAccessFile target, String name, String value){
        inputTextArea(target,name,value,null);
    }
    /**
     * Create a field with TextArea tag.
     */
    public static void inputTextArea(RandomAccessFile target, String name, String value,String attb){
        try {
            target.writeBytes("<textarea " + setNameId(name));
            if(attb!=null){
                target.writeBytes(" "+attb);
            }
            target.writeBytes(" >" +ENDLN);
            if(value!=null){
                target.writeBytes(value+ENDLN);
            }
            target.writeBytes("</textarea>"+ENDLN);
        } catch (IOException ex) {
            //LogFile.error("(HtmlFile-inputTextArea): "+ex.getMessage());
        }
    }
    /**
     * create a field to select a option
     */
    public static void inputSelect(RandomAccessFile target, String name, int start, int end, int select){
        HtmlFile.inputSelect(target, name, start, end, select, null);
    }
    /**
     * create a field to select a option
     */
    public static void inputSelect(RandomAccessFile target, String name, int start, int end, int select,String attb){
        try {
            target.writeBytes("<select " + setNameId(name));
            if(attb!=null){
                target.writeBytes(" "+attb);
            }
            target.writeBytes(" >"+ENDLN );
            for(int i=start; i<end; i++){

                target.writeBytes("\t<option value='"+i+"' ");
                if(select == i){
                    target.writeBytes("selected='selected'");
                }
                target.writeBytes(">"+i+"</option>"+ENDLN );
            }
            target.writeBytes("</select>");
        } catch (IOException ex) {
            //LogFile.error("(HtmlFile-inputTextArea): "+ex.getMessage());
        }
    }
    /**
     * create a field to select a option
     */
    public static void inputSelect(RandomAccessFile target, String name,Object[] data,Object[] values, int select){
        inputSelect(target,name,data,values,select,null);
    }
    /**
     * create a field to select a option
     */
    public static void inputSelect(RandomAccessFile target, String name,Object[] data,Object[] values, int select,String attb){
        try {
            target.writeBytes("<select " + setNameId(name)+">"+ENDLN );
            if (data==null){
                return;
            }
            if(values == null){
                values=new Object[data.length];
                for (int i = 0; i < values.length; i++) {
                     values[i]=""+i;
                }
            }
            int size = data.length;

            if(size>=values.length){
                size=values.length;
            }
            for(int i=0; i<size; i++){

                target.writeBytes("\t<option value='"+values[i]+"' ");
                if(select == i){
                    target.writeBytes("selected='selected'");
                }
                target.writeBytes(">"+data[i]+"</option>"+ENDLN );
            }
            target.writeBytes("</select>");
        } catch (IOException ex) {
            //LogFile.error("(HtmlFile-inputTextArea): "+ex.getMessage());
        }
    }
    /**
     * Create a select with all days week.
     */
    public static void inputDayWeek(RandomAccessFile target, String name,int select){
        HtmlFile.inputSelect(target, name,HtmlFile.DAYS, null, select,null);
    }
    /**
     * Create a select with all days week.
     */
    public static void inputDayWeek(RandomAccessFile target, String name,int select,String attb){
        HtmlFile.inputSelect(target, name,HtmlFile.DAYS, null, select,attb);
    }
    /**
     * Create a select with all days week.
     */
    public static void inputMonths(RandomAccessFile target, String name,int select){
        if((select<0)||(select>11)){
            select = Day.getCurrentMonth();
        }
        HtmlFile.inputSelect(target, name,HtmlFile.MONTHS, null, select);
    }
    /**
     * Create a select with all days of the month.
     */
    public static void inputDaysMonth(RandomAccessFile target, String name,int select){
        if((select<1)||(select>31)){
            select = Day.getCurrentDate();
        }
        HtmlFile.inputSelect(target, name, 1, 32, select);
    }
    /**
     * Set the dates field.
     */
    public static void inputDate(RandomAccessFile target,String name,String value){
        try {
            int currentYear = Day.getCurrentYear();
            inputMonths(target, name + "_month", -1);
            target.writeBytes("-");
            inputDaysMonth(target, name + "_date", -1);
            target.writeBytes("-");
            inputSelect(target, name + "_year", currentYear - 90, currentYear + 90, currentYear);
        } catch (IOException ex) {
            
            //LogFile.error("(HtmlFile-inputDate): "+ex.getMessage());
        }

    }
    /**
     * Set the dates field.
     */
    public static void inputTime(RandomAccessFile target,String name,String value){
        try {
            inputSelect(target, name + "_hour", 0,24,Day.getCurrentHour());
            target.writeBytes(":");
            inputSelect(target, name + "_min", 0,60,Day.getCurrentMinute());
            target.writeBytes(":");
            inputSelect(target, name + "_sec", 0, 60, Day.getCurrentSecond());
        } catch (IOException ex) {
            //Logger.getLogger(HtmlFile.class.getName()).log(Level.SEVERE, null, ex);
            //LogFile.error("(HtmlFile-inputDate): "+ex.getMessage());
        }

    }
    /**
     * Set date and time.
     */
    public static void inputDateTime(RandomAccessFile target, String name, String value){
        try {
            HtmlFile.inputDate(target, name, value);
            target.writeBytes(" ");
            HtmlFile.inputTime(target, name, value);
        } catch (IOException ex) {
            //Logger.getLogger(HtmlFile.class.getName()).log(Level.SEVERE, null, ex);
            //LogFile.error("(HtmlFile-inputDateTime): "+ex.getMessage());
        }

    }
    /**
     * Create a select with all days week.
     */
    public static void inputMonths(RandomAccessFile target, String name,int select,String attb){
        if((select<0)||(select>11)){
            select = Day.getCurrentMonth();
        }
        HtmlFile.inputSelect(target, name,HtmlFile.MONTHS, null, select,attb);
    }
    /**
     * Create a select with all days of the month.
     */
    public static void inputDaysMonth(RandomAccessFile target, String name,int select,String attb){
        if((select<1)||(select>31)){
            select = Day.getCurrentDate();
        }
        HtmlFile.inputSelect(target, name, 1, 32, select,attb);
    }
    /**
     * Set the dates field.
     */
    public static void inputDate(RandomAccessFile target,String name,String value,String attb){
        try {
            int currentYear = Day.getCurrentYear();
            inputMonths(target, name + "_month", -1,attb);
            target.writeBytes("-");
            inputDaysMonth(target, name + "_date", -1,attb);
            target.writeBytes("-");
            inputSelect(target, name + "_year", currentYear - 90, currentYear + 90, currentYear,attb);
        } catch (IOException ex) {
            //Logger.getLogger(HtmlFile.class.getName()).log(Level.SEVERE, null, ex);
            //LogFile.error("(HtmlFile-inputDate): "+ex.getMessage());
        }

    }
    /**
     * Set the dates field.
     */
    public static void inputTime(RandomAccessFile target,String name,String value,String attb){
        try {
            inputSelect(target, name + "_hour", 0,24,Day.getCurrentHour(),attb);
            target.writeBytes(":");
            inputSelect(target, name + "_min", 0,60,Day.getCurrentMinute(),attb);
            target.writeBytes(":");
            inputSelect(target, name + "_sec", 0, 60, Day.getCurrentSecond(),attb);
        } catch (IOException ex) {
            //Logger.getLogger(HtmlFile.class.getName()).log(Level.SEVERE, null, ex);
            //LogFile.error("(HtmlFile-inputDate): "+ex.getMessage());
        }

    }
    /**
     * Set date and time.
     */
    public static void inputDateTime(RandomAccessFile target, String name, String value,String attb){
        try {
            HtmlFile.inputDate(target, name, value,attb);
            target.writeBytes(" ");
            HtmlFile.inputTime(target, name, value,attb);
        } catch (IOException ex) {
            //Logger.getLogger(HtmlFile.class.getName()).log(Level.SEVERE, null, ex);
            //LogFile.error("(HtmlFile-inputDateTime): "+ex.getMessage());
        }

    }

}
