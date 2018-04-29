/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamhcrnltexteditor2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 *
 * @author Irina
 */
public class CopyFile {
    
    public void copyFile(String inputFile, String outputFile) throws IOException{
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(inputFile);
            out = new FileOutputStream(outputFile);
            int c;
            while((c = in.read()) != -1){
                out.write(c);
            }
        } finally  {
            if(in != null){ in.close(); }
            if(out != null){ out.close(); }
        
        }
    }
    
    public void openFile(JFrame frame, RSyntaxTextArea textArea) throws IOException{
        BufferedReader in = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String filename = selectedFile.getAbsolutePath();
            System.out.println(filename);
            try {
                in = new BufferedReader( new FileReader(filename));
                String c;
                while((c = in.readLine()) != null){
                    textArea.setText(c);
                    //textArea.write(Writer write);
                    //textArea.
                }
            }finally {
                if(in != null){ in.close(); }
            }
        }
    }
    
    public static void test1(){
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        CopyFile cp = new CopyFile();
        try {
            cp.copyFile(inputFile, outputFile);
        } catch (IOException ex) {
            Logger.getLogger(CopyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
