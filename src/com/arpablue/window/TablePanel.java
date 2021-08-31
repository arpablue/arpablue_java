/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.GridLayout;

/**
 *
 * @author Administrator
 */
public class TablePanel extends NullPanel {
    protected int mCols=2;
    protected int mRows=2;
    public TablePanel(){
        this(2,2);
    }
    public TablePanel(int cols, int rows){
        super();
        setCols(cols);
        setRows(rows);
       this.setLayout(new GridLayout(rows,cols));
    }

    /**
     * @return the mCols
     */
    public int getCols() {
        return mCols;
    }
    /**
     * @param mCols the mCols to set
     */
    public void setCols(int cols) {
        if(cols<0){
            cols*=-1;
        }
        this.mCols = cols;
    }
    /**
     * @return the mRows
     */
    public int getRows() {
        return mRows;
    }
    /**
     * @param mRows the mRows to set
     */
    public void setRows(int rows) {
        if(rows < 0){
            rows *=-1;
        }
        this.mRows = rows;
    }
}
