/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.gui;

import java.awt.GridLayout;

/**
 *
 * @author 5v
 */
public class GridPanel extends APanel{
    
    public GridPanel()
    {
        this(3,3);
    }
    public GridPanel(int rows, int cols )
    {
        super();
        this.setLayout(new GridLayout(rows,cols));
        
    }
    
    public int getRows()
    {
        return ((GridLayout)this.getLayout()).getRows();
    }

    public void setRows(int rows)
    {
        ((GridLayout)this.getLayout()).setRows(rows);
    }

    public int getColumns()
    {
        return ((GridLayout)this.getLayout()).getColumns();
    }

    public void setColumns(int cols)
    {
        ((GridLayout)this.getLayout()).setColumns(cols);
    }
    public void setHgap(int hgap)
    {
        ((GridLayout)this.getLayout()).setHgap(hgap);
    }
    public void setVgap(int vgap)
    {
        ((GridLayout)this.getLayout()).setVgap(vgap);
    
    }
    public int getHgap()
    {
        return ((GridLayout)this.getLayout()).getHgap();
    }
    public int getVgap()
    {
        return ((GridLayout)this.getLayout()).getVgap();
    
    }

}