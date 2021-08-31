/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class NTable extends JScrollPane implements MouseListener
{
    protected JTable mTable;
    protected DefaultTableModel mModel;
    protected int mColumnSelected = -1, mRowSelected = -1;
    protected Vector mMouseListenerAZ=new Vector(0);
    public NTable(){
        super();
        mModel = new DefaultTableModel();
        mTable = new JTable(mModel);
        this.setViewportView(mTable);
        mTable.addMouseListener(this);
        
    }
    public ListSelectionModel getListSelectionModel(){
        return mTable.getSelectionModel();
    }
    public void selectRow(int index){
        getListSelectionModel().setSelectionInterval(index,index);
    }
    public void addColumn(String name){
        mModel.addColumn(name);
    }
    public void addRow(Object [] row){
        mModel.addRow(row);
    }
    public void addData(Object [] row){
        addRow(row);
    }
    public void addRow(Vector row){
        addRow(row.toArray());
    }
    public void addData(Vector row){
        addRow(row);
    }
    public void addTableData(Vector ldl){
        Vector row;
        for (int i = 0; i < ldl.size(); i++) {
            row = (Vector)ldl.get(i);
            addRow(row);
        }
    }
    public void removeRow(int index){
        mModel.removeRow(index);
    }
    @Override
    public void addMouseListener(MouseListener l){
        mMouseListenerAZ.add(l);
    }
    public void mouseClicked(MouseEvent e) {
        
        this.mColumnSelected = this.mTable.columnAtPoint(e.getPoint());
        this.mRowSelected = this.mTable.rowAtPoint(e.getPoint());

        for (int i = 0; i < mMouseListenerAZ.size(); i++)
            ((MouseListener)mMouseListenerAZ.get(i)).mouseClicked(e);
    }
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < mMouseListenerAZ.size(); i++)
            ((MouseListener)mMouseListenerAZ.get(i)).mousePressed(e);
    }
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < mMouseListenerAZ.size(); i++)
            ((MouseListener)mMouseListenerAZ.get(i)).mouseReleased(e);
    }
    public void mouseEntered(MouseEvent e) {
        for (int i = 0; i < mMouseListenerAZ.size(); i++)
            ((MouseListener)mMouseListenerAZ.get(i)).mouseEntered(e);

    }
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < mMouseListenerAZ.size(); i++)
            ((MouseListener)mMouseListenerAZ.get(i)).mouseExited(e);

    }
    public int getColumnSelected() {
        return mColumnSelected;
    }
    public int getRowSelected() {
        return mRowSelected;
    }
    public Vector getTableData(){
        return mModel.getDataVector();
    }
    public int getColumnCount(){ return mModel.getColumnCount();}
    public int getRowCount(){ return mModel.getRowCount();}
    public String [] GetColumnString(int column){
        String []res = new String[getRowCount()];
        Vector t = mModel.getDataVector();
        Vector r;
        Object e;
        for (int i = 0; i < t.size(); i++) {
            r=(Vector)t.get(i);
            for (int j = 0; j < r.size(); j++) {
                e = r.get(j);
                if(j == column)
                    res[i] = e.toString();
            }
        }
        return res;
    }
    public String [] GetRowString(int row){
        String []res = new String[getColumnCount()];
        Vector t = mModel.getDataVector();
        Vector r;
        Object e;
        r=(Vector)t.get(row);
        for (int j = 0; j < r.size(); j++) {
            e = r.get(j);
            res[j] = e.toString();
        }
        return res;
    }

}
