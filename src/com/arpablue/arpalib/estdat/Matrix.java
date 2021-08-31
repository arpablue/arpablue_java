/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

import com.arpablue.interfaces.IPrintable;
import java.util.ArrayList;

/**
 *
 * @author engau
 */
public class Matrix<T> implements IPrintable{
    protected ArrayList<T> mList;
    protected int mColums=4;
    protected int mRows=4;
    protected int mSize=1;
    public Matrix(){
        mList = new ArrayList<T>();
    }
    public Matrix(int cols, int rows){
        this.setSize(cols, rows);
    }
    /**
     * This evaluate a size, if the size is negative then the method return the positive equivalent value, if the size is
     * zero then return 1, in other cases return the same size
     * @param size It is the size to be evaluated.
     * @return It is the size evaluated.
    */
    protected static int evalueteSize( int size){
        if( size < 0 ){
            size = size * -1;
            return size;
        }
        if( size == 0 ){
            return 1;
        }
        return size;
    }
    public  int getPosition( int row, int col ){
        int pos = 0;
        pos = ( row * this.mColums ) + col;
        return pos;
    }
    /**
     * It specify the size of the matrix
     * @param cols
     * @param rows 
     */
    public void setSize(int rows, int cols){
        mColums = Matrix.evalueteSize( cols );
        mRows = Matrix.evalueteSize( rows );
        mSize = mColums * mRows;
        buildMatrix();
        
    }
    public ArrayList<T> getElments(){
        return mList;
    }
    /**
     * It fill the matrix with empty spaces.
     */
    protected void buildMatrix(){
        
        int size = size();
        if( size <= this.mList.size() ){
            return;
        }
        
        for( int i=0; i < size; i++){
            addToEnd( null );
        }
        
    }
    /**
     * It return the quantity of element in the current matrix.
     * @return It is the quantity of elements.
     */
    protected int size(){
        return this.mColums * this.mRows;
    }
    /**
     * it add a element in the last position of the next until the quantity of elements are filled.
     * @param element It is the element to be added.
     * @return It is true if the element has been added without problems.
     */
    public boolean addToEnd(T element){
        int size = size();
        if( size <= this.mList.size()  ){
            return false;
        }
        mList.add( element );
        return true;
    }
    /**
     *  It return an element of the matrix, if the reference is out of the matrix, then return null.    
     * @param row it is  the row pf the element.
     * @param col It is the column of the element.
     * @return It is the element of the position.
     */
    public T get( int row, int col ){
            int pos = this.getPosition(row, col);
            if( pos >= this.mList.size() ){
                return null;
            }
            return this.mList.get(pos);
    }
    /**
     * It speci
     * @param row
     * @param col
     * @param element 
     */
    public void set(int row, int col, T element ){
            int pos = this.getPosition(row, col);
            if( pos >= this.mList.size() ){
                return;
            }
            this.mList.set( pos, element );
    }
    /**
     * It print the stricture of the matrix in a string to be print in the screen or any other devices.
     * @return 
     */
    @Override
    public String toPrint(){
        String res = "Matrix( rows: "+this.mRows+", rows:"+this.mColums+")\n";
        T e = null;
        int pos = 0;
        for( int i=0; i<this.mRows; i++){
            res = res +"| ";
            for( int j=0; j < this.mColums; j++ ){
                e = this.get(i, j);
                if(e != null ){
                    res = res + e + " | ";
                }else{
                    res = res + "null  | ";
                }
                
            }
            res = res + "\n";
        }
        return res;
    }
    /**
     * It return the the quantity of columns of the matrix.
     * @return It is the number of columns.
     */
    public int getColumnsCount(){
        return this.mColums;
    }
    /**
     * It return the quantity of rows of the Matrix.
     * @return It is the number of row of the matrix,
     */
    public int getRowsCount(){
        return this.mRows;
    }
    
}
