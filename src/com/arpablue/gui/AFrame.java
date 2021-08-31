/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.gui;

/**
 *
 * @author 5v
 */
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
public abstract class AFrame extends JFrame implements ActionListener{

    public AFrame(String title)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(300,300);
    }
    public Component append( Component comp){
        return this.getContentPane().add( comp );
    }
    public JTextArea addJTextArea(int x, int y, int width, int height)
    {
        JTextArea res=new JTextArea();
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res);
        return res;
    }
    public TextArea addTextArea(int x, int y, int width, int height)
    {
        TextArea res=new TextArea();
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res);
        return res;
    }
    public JTextField addJTextField(int x, int y, int width, int height)
    {
        JTextField res=new JTextField();
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res);
        return res;
    }
    public JButton addJButton(String title,int x, int y, int width, int height)
    {
        JButton res=new JButton(title);
        res.addActionListener(this);
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res);
        return res;
    }
    public JLabel addJLabel(String title, int x, int y, int width, int height)
    {
        JLabel res=new JLabel(title);
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res);
        return res;
    }

    public JTextArea addJTextArea(int x, int y, int width, int height,String Location)
    {
        JTextArea res=new JTextArea();
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res,Location);
        return res;
    }
    public TextArea addTextArea(int x, int y, int width, int height,String Location)
    {
        TextArea res=new TextArea();
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res,Location);
        return res;
    }
    public JTextField addJTextField(int x, int y, int width, int height,String Location)
    {
        JTextField res=new JTextField();
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res,Location);
        return res;
    }
    public JButton addJButton(String title,int x, int y, int width, int height,String Location)
    {
        JButton res=new JButton(title);
        res.addActionListener(this);
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res,Location);
        return res;
    }
    public JLabel addJLabel(String title, int x, int y, int width, int height,String Location)
    {
        JLabel res=new JLabel(title);
        res.setLocation(x,y);
        res.setSize(width,height);
        this.getContentPane().add(res,Location);
        return res;
    }

    public static JTextArea createJTextArea(int x, int y, int width, int height)
    {
        JTextArea res=new JTextArea();
        res.setLocation(x,y);
        res.setSize(width,height);
        return res;
    }
    public static TextArea createTextArea(int x, int y, int width, int height)
    {
        TextArea res=new TextArea();
        res.setLocation(x,y);
        res.setSize(width,height);
        return res;
    }
    public static JTextField createJTextField(int x, int y, int width, int height)
    {
        JTextField res=new JTextField();
        res.setLocation(x,y);
        res.setSize(width,height);
        return res;
    }
    public static JButton createJButton(String title,int x, int y, int width, int height)
    {
        JButton res=new JButton(title);
        res.setLocation(x,y);
        res.setSize(width,height);
        return res;
    }
    public static JLabel createJLabel(String title, int x, int y, int width, int height)
    {
        JLabel res=new JLabel(title);
        res.setLocation(x,y);
        res.setSize(width,height);
        return res;
    }    
    public AFrame()
    {
        this(null);
    }
    public abstract void actionPerformed(ActionEvent ae);
    

}
