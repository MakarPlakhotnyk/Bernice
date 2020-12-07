/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha;

import fajly.ReadErr;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Makar Plakhotnyk
 */
public class Planilha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
            {
            @Override
            public void run(){
                try{
                    Glowny trFrame = new Glowny();
                    trFrame.init();
                    trFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    trFrame.setVisible(true);
                    }        
                catch(ReadErr readErr){
                    System.exit(0);
                    }
                }
            }
        );
    }
    
}
