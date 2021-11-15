/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class LDL<T> {
    /**
     * It contains the list of emenests
     */
    protected ArrayList<ArrayList<T>> mLDL;
    /**
     * Default constructor.
     */
    public LDL()
    
    {
        mLDL = new ArrayList<ArrayList<T>>();
        
    }
    /**
     * It return the quantity of list.
     * @return It is the quantity of the lists.
     */
    public int getListSize()
    {
        if( this.mLDL == null )
        {
            return 0;
        }
        return this.mLDL.size();
    }
    /**
     * It return then list in the index spoecified, if the index is bigger than the quantity of lists this should be return the 
     * list from the last to the first.
     * @param index It is the position of the list.
     * @return  It is the list specified in the index.
     */
    public ArrayList<T> getList(int index)
    {
        while( index < 0){
            index = this.getListSize() - index;
        }
        if( index >= this.getListSize() )
        {
            return null;
        }
        return this.mLDL.get(index);
    }
    /**
     * It return the list with all elements from all list with the same position.
     * @param column It is the position of the element in the same position.
     * @return It is the list of elements.
     */
    public ArrayList<T> getColumn( int column )
    {
        ArrayList<T> res = new ArrayList<T>();
        if( this.mLDL == null )
        {
            return res;
        }
        for( ArrayList<T> list : this.mLDL )
        {
            if( list == null )
            {
                continue;
            }
            if( list.size() <= column )
            {
                continue;
            }
            res.add(list.get(column) );
        }
        return res;
    }
    /**
     * It add a list in the collection.
     * @param list It is the list of element to be added.
     */
    public void add( ArrayList<T> list )
    {
        if( list == null )
        {
            return;
        }
        mLDL.add(list);
    }
    /**
     * It return an element from a list, if the index is bigger than the size then return null.
     * @param list It is the list from element will be geting,
     * @param index It is the position of the element.
     * @return 
     */
    protected T getListElement( ArrayList<T> list,  int index )
    {
        if( index >= list.size() )
        {
            return null;
        }
        return list.get(index);
    }
     /**
     * It add alement in a list and a position.
     * @param element It is the element to be added 
     * @param listIndex It is the list where the element will be replaced.
     * @return It is true the element has been added without problems.
     */
    public boolean add( T element, int listIndex, int position )
    {
        ArrayList<T> list = this.getList( listIndex );
        if( list == null )
        {
            return false;
        }
        if( position >= list.size() )
        {
            return false;
        }
        list.set( position, element);
        return true;
    }
    /**
     * It return the string that represent to the current list of objects.
     * @param list It is the list of object.
     * @return It is the string that represent the list.
     */
    public  String  getListToStr( ArrayList<T> list )
    {
        if( list == null )
        {
            return(" NULL |");
            
        }
        if( list.isEmpty() )
        {
            return("  EMPTY | ");
        }
        String res = "";
        for( T e: list )
        {
            if( res == null )
            {
                 res = res + "NULL | ";
            }else{
                 res = res + e + " | ";
            }
        }
        return res;
    }
    /**
     * It print all the list in the screen.
     */
    public void println()
    {
        System.out.println("_____________________________________");
        if( mLDL == null)
        {
            System.out.println("| NULL |");
            return;
        }
        if( mLDL.isEmpty() )
        {
            System.out.println("| EMPTY |");
        }
        int pos = 0;
        for( ArrayList<T> list: mLDL )
        {
            System.out.println(" " + pos+") | "  + getListToStr( list) );
            pos++;
        }
        System.out.println("_____________________________________");
        
    }

}
