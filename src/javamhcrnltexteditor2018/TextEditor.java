/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamhcrnltexteditor2018;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.scene.shape.Shape;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
/**
 *
 * @author mhcrnl
 */
public class TextEditor {
    
    JFrame frame =new JFrame(JavaMhcrnlTextEditor2018.NAME); ;
    RSyntaxTextArea textArea = new RSyntaxTextArea();
    
    public JMenuBar createMenuBar() {
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu, helpMenu, submenu;
        JMenuItem menuNew, menuOpen, menuSave, menuClose, menuAbout, menuItem;
        
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Meniul File");
        menuBar.add(menu);
        
        menuNew = new JMenuItem("New", KeyEvent.VK_N);
        menuNew.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                 TextEditor te = new TextEditor();
                 te.createAndShowGUI();
            }
            
        });
        menu.add(menuNew);
        
        menuOpen = new JMenuItem("Open", KeyEvent.VK_O);
        menuOpen.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                //JTextComponent tc = getTextComponent(ae);
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filename = selectedFile.getAbsolutePath();
                    System.out.println(filename);
                    loadFile(selectedFile);
                    String line = null;
                    try(BufferedReader br = new BufferedReader(new FileReader(filename))){
                        textArea.read(br, null);
                        while((line = br.readLine()) != null){
                            System.out.println(line);
                            //textArea.setText(line);
                            
                        }
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    //String text = "Salut";
                    
//                    try(Stream<String> stream = Files.lines(Paths.get(filename))) {
//                        stream.forEach(System.out::println);
//                        textArea.setText(filename);
//                    } catch(IOException e){
//                        e.printStackTrace();
//                    }
//                    try
//                    {
//                        FileReader reader = new FileReader(filename);
//                        BufferedReader br = new BufferedReader(reader);
//                        textArea.read(br, null);
//                        br.close();
//                        System.out.println(textArea.getText());
//
//                    } catch (Exception e)
//                    {
//                        System.out.println("Error");
//                    }
//
               }       
            }
        });
        menu.add(menuOpen);
        
        menuSave = new JMenuItem("Save", KeyEvent.VK_S);
        menuSave.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChoose = new JFileChooser();
                int option = fileChoose.showSaveDialog(frame);
                if(option == JFileChooser.APPROVE_OPTION){
                    try{
                        File openFile = fileChoose.getSelectedFile();
                        //setTitle(openFile.getName()+" | "+JavaMhcrnlTextEditor2018.NAME);
                        BufferedWriter out = new BufferedWriter(new FileWriter(openFile.getPath()));
                        out.write(textArea.getText());
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
            
        });
        menu.add(menuSave);
        
        menuClose = new JMenuItem("Close", KeyEvent.VK_C);
        menuClose.addActionListener(new ActionListener(){ 

            @Override
            public void actionPerformed(ActionEvent ae) {
                 System.exit(0);
            }
            
        });
        menu.add(menuClose);
        
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.getAccessibleContext().setAccessibleDescription("Meniul Help");
        menuBar.add(helpMenu);
        
        menuAbout = new JMenuItem("About", KeyEvent.VK_A);
        menuAbout.addActionListener(new ActionListener(){ 

            @Override
            public void actionPerformed(ActionEvent ae) {
                 About ab = new About(frame);
                 ab.me();
            }
            
        });
        helpMenu.add(menuAbout);
        
        return menuBar;
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI(){
        
        
        JPanel cp = new JPanel(new BorderLayout());
        
        //textArea = new RSyntaxTextArea(20,60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        //textArea.setBackground(Color.BLACK);
        textArea.setEditable(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        cp.add(sp);
        //Create and setup the frame
        //frame = new JFrame(JavaMhcrnlTextEditor2018.NAME);
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
    
    public void loadFile(File file){
        System.out.println("DEBUG: "+file.getAbsolutePath());
        if(file.isDirectory()){
            JOptionPane.showMessageDialog(frame, file.getAbsolutePath() +
                    "is a directory", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(!file.isFile()){
            JOptionPane.showMessageDialog(frame, "No such file: " +
                    file.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            BufferedReader r = new BufferedReader(new FileReader(file));
            textArea.read(r, null);
            r.close();
        } catch(IOException ioe){
            ioe.printStackTrace();
            UIManager.getLookAndFeel().provideErrorFeedback(textArea);
        }
    }
}
