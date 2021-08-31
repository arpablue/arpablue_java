/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

import java.util.Calendar;

/**
 *
 * @author Administrator
 */
public class Day {
    /**
     * Get the current Date
     *
     */
    public static int getCurrentDate(){
        return Calendar.getInstance().get(Calendar.DATE);

    }
    /**
     * Get the current Day
     *
     */
    public static int getCurrentDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    }
    /**
     * Get the current Month
     *
     */
    public static int getCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);

    }
    /**
     * Get the current Year
     */
    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);

    }
    /**
     * Get the current hour
     */
    public static int getCurrentHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    /**
     * Get the current minutes.
     */
    public static int getCurrentMinute(){
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    /**
     * Get the current seconds.
     */
    public static int getCurrentSecond(){
        return Calendar.getInstance().get(Calendar.SECOND);
    }
}
