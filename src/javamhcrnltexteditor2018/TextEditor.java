/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamhcrnltexteditor2018;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author mhcrnl
 */
public class TextEditor {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI(){
        //Create and setup the frame
        JFrame frame = new JFrame(JavaMhcrnlTextEditor2018.NAME);
        // set this so your application can easily be stopped/killed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the jframe size. in a more complicated application you will
        // probably call frame.pack() before displaying it.
        frame.setPreferredSize(new Dimension(800, 600));
        
        
        JLabel label = new JLabel("Salut editor!");
        frame.getContentPane().add(label);
        
        frame.pack();
        // center the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
