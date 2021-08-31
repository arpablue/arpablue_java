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
public class MatrixList<T> implements IPrintable{
    protected int mColumnsCount;
    protected ArrayList<ArrayList<T>> mLol;
    
    public MatrixList(){
        this( 0 );
    }
    public MatrixList(int cols ){
        this.mColumnsCount = cols;
        mLol = new ArrayList<ArrayList<T>>();
    }
    /**
     * It remove all elements fo the table.
     */
    public void clear(){
        this.mLol.clear();
        this.mColumnsCount = 0;
    }
    /**
     * It add a new row to the table, if the list is null or empty, then will not be added.
     * @param list It is the list to be added.
     */
    public void add( ArrayList<T> list ){
        if( list == null ){
            return;
        }
        if( list.size() < 1 ) {
            return; 
        }
        if( this.mColumnsCount <  list.size() ){
            this.mColumnsCount =  list.size();
        }
        mLol.add( list );
        if( this.mColumnsCount < mLol.size() ){
            this.mColumnsCount = mLol.size();
        }
        
    }
    /**
     * It return the quantity of columns of the table.
     * @return It is the number of columns.
     */
    public int getColumsCount(){
        return this.mColumnsCount;
    }
    /**
     * It return the quantity of rows.
     * @return It is the number of rows.
     */
    public int getRowsCount(){
        return mLol.size();
    }
    /**
     * It return a specific row, if the position is out of range then the method return null.
     * @param row it is the position of the row, if the number is negative then the position is counted from the last to the first.
     * @return It is the row of the position specified.
     */
    public ArrayList<T> getRow(int row){
        if( row < 0){
            row = this.mLol.size() - row;
        }
        if( row < 0 ){
            return null;
        }
        if( row >= this.mLol.size()){
            return null;
        }
        return this.mLol.get(row);
    }
    /**
     * It return a list of all element from a column, if the column not exists then the list of element is empty.
     * @param colum it is the column specified.
     * @return 
     */
    public ArrayList<T> getColum( int colum ){
        ArrayList<T> res = new ArrayList<T>();
        ArrayList<T> r;
        T e;
        for( int i=0; i < this.mLol.size(); i++){
            r = this.getRow(i);
            e = this.getElement(colum, r);
            res.add( e );
        }
        return res;
    }
    /**
     * It return the element corresponding to the specified column and row, it the column and row are 
     * out of the range then a null value is returned.
     * @param row it is the row position.
     * @param col it is the column of the position.
     * @return It is the element corresponding to the row a,d col position.
     */
    public T get( int row, int col){
        ArrayList<T> r = getRow( row );
        if ( r == null ){
            return null;
        }
        if( r.size() <= col ){
            return null;
        }
        return this.getElement(col, r);
            
    }
    /**
     * It return a list with the number of elements equals to the number of columns.
     * @return 
     */
    protected ArrayList<T> buildRow(){
        ArrayList<T> row = new ArrayList<T>();
        for( int i = 0; i < this.getColumsCount();  i++){
            row.add(null);
        }
        return row;
    }
    /**
     * Return a element of the list, if the position is out of the range then a null values is returned, for a negative
     * value, the element returned is from the last to the first.
     * @param pos it is the position of the element to be returned.
     * @param list this is list of elements.
     * @return  It is the element corresponding to the specified position.
     */
    public T getElement( int pos, ArrayList<T> list){
        if( list == null){
            return null;
        }
        if( list.size() < 1 ){
            return null;
        }
        if( pos < 0) {
            pos = this.mColumnsCount - pos;
                    
        }
        if( pos < 0){
            return null;
        }
        if( pos >= this.mColumnsCount ){
            return null;
        }
        if( pos >= list.size() ){
            return null;
        }
        return list.get( pos );
    }
    /**
     * It set an element in an specified row and column, when the position is out of the range of the matrix
     * then the function return false.
     * @param row It is the row of the data.
     * @param col It is the column of the data.
     * @param element It is the element to be inserted.
     * @return It is true if the element has been inserted without problem.
     */
    public boolean set(int row, int col, T element){
        buildRows(row + 1);
        ArrayList<T> r = this.getRow( row  );
        if( this.mColumnsCount <= col ){
            return false;
        }
        if( r == null ){
            return false;
        }
        putOnColumSize( r );
        r.set(col, element);
        return true;
    }
    /**
     * It increase the size of the ro until an specified quantity of rows.
     * @param totalRows It is the total of rows for the matrix.
     */
    protected void buildRows(int totalRows){
        if ( totalRows < 0 ){
            return;
        }
        if( totalRows == 0 ){
            return;
            
        }
        if( this.mLol.size() >= totalRows ){
            return;
        }
        ArrayList<T> row;
        for( int i=this.mLol.size(); i < totalRows; i++){
            row = this.buildRow();
            this.mLol.add(row);
        }
    }
    /**
     * It increment the size of a list to the number of columns of the matrix.
     * @param list This the list to increment the its length.
     */
    protected void putOnColumSize( ArrayList<T> list){
        if( list == null ){
            return;
        }
        if( list.size() >= this.mColumnsCount ){
            return;
        }
        for( int i = list.size(); i < this.mColumnsCount; i++ ){
            list.add( null );
        }
    }
    /**
     * It set the contain of the Matrix in an screen to print in the screen.
     * @return It is the string with the matrix structure
     */
    @Override
    public String toPrint() {
       String res = "Size: rows="+this.getRowsCount()+",cols="+this.getColumsCount()+"\n";
       T cell;
       for( int r = 0; r < this.getRowsCount(); r++){
           res = res + "| ";
           for( int c = 0; c < this.getColumsCount(); c++ ){
               cell = this.get(r, c);
               if( cell == null ){
                   res = res + "null | ";
               }else{
                    res = res + cell.toString()+" | ";
               }
           }
           res = res + "\n";
       }
       return res;
    }
    /**
     * It return another object with the same data of the current object.
     * @return It is the clone object.
     */
    public MatrixList<T> clone(){
        MatrixList<T> res = new MatrixList<T>();
        res.mColumnsCount = this.mColumnsCount;
        res.mLol = this.mLol;
        return res;
    }
    
}
