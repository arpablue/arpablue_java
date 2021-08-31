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
public class NList {
    public static void printMatriz(Vector matriz)
    {
        for (int i = 0; i < matriz.size(); i++) {
            Vector e = (Vector)matriz.get(i);
            System.out.print(i+") ");
            for (int j = 0; j < e.size(); j++) {
                Object object1 = e.get(j);
                System.out.print(object1+" - ");
            }
            System.out.println("");

        }
    }

}
