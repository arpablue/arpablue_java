/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

import java.util.Random;

/**
 *
 * @author root
 */
public class Tool {
    /**
     * Return a random number
     * 
     * @return 
     */
    public static int azar(int n)
    {
        Random rand = new Random();
        int res = rand.nextInt(n+1);
        return res;
    }
    /**
     * return a number between the limits.
     * @param min
     * @param max
     * @return 
     */
    public static int azar(int min, int max)
    {
        int n = min - max;
        int res = azar(n);
        return min + res;
        
    }
    /**
     * Return a number between the 1 and 100.
     */
    public static int azar()
    {
        return azar(100);
    }
}
