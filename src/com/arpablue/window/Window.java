/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import com.arpablue.xsystemfile.LogFile;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.RandomAccessFile;
import javax.swing.JFrame;
/**
 *
 * @author Administrator
 */
public class Window extends JFrame implements ActionListener,WindowListener{
    protected String mSettingsFileFrame = "settings.dat";
    // It contains information about if the settings of the windows sgould be loaded, set in false when the user use the setSize() method.
    protected boolean mLoadSettings = true;
    public Window(){ this(null);}
    public Window(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(this);
        this.setLayout(null);
        this.loadFrameProperties();
        
    }
    /**
     * Author: safa
     * Date: 2016-05-06: It specify the size of the windows, if this method is used the auto load settings is disabled.
     */
    @Override
    public void setSize(Dimension d){
        this.mLoadSettings = false;
        super.setSize(d);
    }
    /**
     * Author: safa
     * Date: 2016-05-06: It specify the size of the windows, if this method is used the auto load settings is disabled.
     */
    @Override
    public void setSize(int with, int height){
        this.mLoadSettings = false;
        super.setSize(with,height);
    }
    /**
     * Specify the settings file to save the frame properties, if the value is null then 
     * not save anything.
     */
    public void setSettingFrameFile(String file){
        mSettingsFileFrame=file;
    }
    /**
     * Add a new component to the frame.
     * @param comp
     */
    public void addComponent(Component comp)
    {
        this.getContentPane().add(comp);
    }
 
    protected void loadFrameProperties(){
        if(this.mSettingsFileFrame==null){
            return;
        }
        try{
            File f= new File(this.mSettingsFileFrame);
            if(!f.exists()){
                this.setLocation(0, 0);
                this.setSize(600, 400);
                return;
            }

            RandomAccessFile ran = new RandomAccessFile(f,"rw");
            int x= ran.readInt();
            int y= ran.readInt();
            int w= ran.readInt();
            int h= ran.readInt();
            this.setLocation(x,y);
            this.setSize(w, h);
            ran.close();
        }catch ( Exception e){
            //LogFile.error("(MainWin - loadProperties): "+e.getMessage());
        }
        
    }
    protected void saveFrameProperties(){
        if(this.mSettingsFileFrame==null){
            return;
        }
        try{
            File f= new File(this.mSettingsFileFrame);
            if(f.exists()){
                f.delete();
            }
            RandomAccessFile ran = new RandomAccessFile(f,"rw");
            ran.writeInt(this.getLocation().x);
            ran.writeInt(this.getLocation().y);
            ran.writeInt(this.getSize().width);
            ran.writeInt(this.getSize().height);
            
            ran.close();
        }catch ( Exception e){
            //LogFile.error("(MainWin - saveProperties): "+e.getMessage());
        }
    }
    /**
     * It specify if the windows should be showed or hidden.
     * @param visible : It is a boolean 
     */
    @Override
    public void setVisible(boolean visible){
        if(this.mLoadSettings && visible){
            this.loadFrameProperties();
        }
        if(!visible){
            this.saveFrameProperties();
        }
            
        super.setVisible(visible);
    }
    ////////////////////////////////// Implementations////////////////////////
    @Override
    public void windowClosing(WindowEvent e) {
        this.saveFrameProperties();
    }
    @Override
    public void actionPerformed(ActionEvent e) {}
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}

}
