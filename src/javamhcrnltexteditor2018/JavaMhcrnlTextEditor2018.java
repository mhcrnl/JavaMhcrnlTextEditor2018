/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamhcrnltexteditor2018;

/**
 *
 * @author mhcrnl
 */
public class JavaMhcrnlTextEditor2018 {
    
    private static final long serialVersionUID = 1L;
    public final static String AUTHOR_EMAIL = "mhcrnl@gmail.com";
    public final static String NAME = "Mhcrnl Text Editor 2018";
    public final static double VERSION = 0.01;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CopyFile.test1();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                TextEditor te = new TextEditor();
                te.createAndShowGUI();
            }
            
        });
    }
    
}
