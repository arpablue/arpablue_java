/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import com.arpablue.xsystemfile.LogFile;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author Administrator
 */
public class Navigator extends JEditorPane implements HyperlinkListener{
    public Navigator(){
        super();
        this.setEditable(false);
        this.addHyperlinkListener(this);
    }

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if ( e.getEventType() ==
                    HyperlinkEvent.EventType.ACTIVATED )
                  getHtmlPage( e.getURL().toString() );
    }
    @Override
   public void setPage(String url){
        try {
            super.setPage(url);
        } catch (IOException ex) {
            //Logger.getLogger(Navigator.class.getName()).log(Level.SEVERE, null, ex);
            //LogFile.error("(Navigator - setPage): "+ex.getMessage());
        }
   }
   protected void getHtmlPage( String url )
   {
      try {
         this.setPage( url );
      }
      catch ( Exception e ) {
         JOptionPane.showMessageDialog( this,
            "Error to load URL:" + url, "Wrong URL",
            JOptionPane.ERROR_MESSAGE );
      }

   }
}
