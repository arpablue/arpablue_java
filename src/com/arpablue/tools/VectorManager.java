/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class VectorManager {
    public static Vector concat(Vector target, Vector source){
        if(target == null)
            target = new Vector();
        if(source == null)
            return target;
        Object elem = null;
        for(int i=0; i<source.size(); i++){
            elem = source.get(i);
               target.add(elem);
        }
        return target;

    }
    public static void print(Vector target){
        Object elem = null;
        System.out.println("++++++++++++++++++Printing Vector++++++++++++++++++");
        if(target==null)
        {
            System.out.println("The vector is null.");
            return;
        }
        for(int i = 0; i < target.size(); i++){
            elem = target.get(i);
            System.out.println(i+") "+elem);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    public static void print(int[] target)
    {
        System.out.println("++++++++++++++++++Printing Vector++++++++++++++++++");
        if(target==null)
        {
            System.out.println("The vector is null.");
            return;
        }
        for(int i = 0; i < target.length; i++){
            System.out.println(i+") "+target[i]);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        
    }
    //Order an array of int in descended way.
    public static int[] orderDesc(int[] target)
    {
        if(target==null)
            return null;
                    
        for (int i = 0; i < target.length-1; i++) 
        {
            int aux=0;
            if(target[i]<target[i+1])
            {
                aux=target[i];
                target[i]=target[i+1];
                target[i+1]=aux;
                i--;
                if(i>-1)
                    i--;
            }
        }
        return target;
    }
    //
    
}
