/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools;

import java.util.Random;

/**
 *
 * @author augusto
 */
public class Caos {

    public static int azar(int n)
    {
        Random rand = new Random();
        int res = 0;
        res = (int)(rand.nextFloat()*n);
        res++;
        return res;
    }
    public static int azar(int start, int end)
    {
        int res = 0;
        int n=end-start;
        res = start+azar(n);
        return res;
    }
    //return the potency of one number y with a specifyc exponent, if the exponent is negative then is changed to positive
    public static long potency(int base, int exp)
    {
        long res = 1;
        
        if(exp<0)
            exp*=-1;
        for(int i=0; i<exp;i++)
            res=base*res;
        return res;
        
    }
}
