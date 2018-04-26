/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamhcrnltexteditor2018;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
/**
 *
 * @author mhcrnl
 */
public class TextEditor {
    
    //JFrame frame;
    
    public JMenuBar createMenuBar() {
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu, submenu;
        JMenuItem menuClose,menuItem;
        
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Meniul File");
        menuBar.add(menu);
        
        menuClose = new JMenuItem("Close", KeyEvent.VK_C);
        menuClose.addActionListener(new ActionListener(){ 

            @Override
            public void actionPerformed(ActionEvent ae) {
                 System.exit(0);
            }
            
        });
        menu.add(menuClose);
        
        return menuBar;
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI(){
        
        JPanel cp = new JPanel(new BorderLayout());
        
        RSyntaxTextArea textArea = new RSyntaxTextArea(20,60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        cp.add(sp);
        //Create and setup the frame
        JFrame frame = new JFrame(JavaMhcrnlTextEditor2018.NAME);
        // set this so your application can easily be stopped/killed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the jframe size. in a more complicated application you will
        // probably call frame.pack() before displaying it.
        frame.setPreferredSize(new Dimension(800, 600));
        TextEditor teMenu = new TextEditor();
        frame.setJMenuBar(teMenu.createMenuBar());
        
        //JLabel label = new JLabel("Salut editor!");
        frame.setContentPane(cp);//.add(label);
        
        frame.pack();
        // center the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
