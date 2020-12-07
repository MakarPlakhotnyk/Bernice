/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Makar Plakhotnyk
 */
public class ClineEdt implements ActionListener{
    
    private final CLine cLine;
    
    public ClineEdt(CLine cLine){
        this.cLine =cLine;
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        
//        Font font = this.cLine.getFont();
//        String fontName = font.getFamily();
//        String fontFilePath = FontManager.getFontPath( true )
//            + "/"
////            +FontManager.getFileNameForFontName( fontName );
        
        JFrame frame = new JFrame();
        EdtPannel pNorth = new EdtPannel();
        pNorth.addItem(new JLabel("Value:"),
            new TextField(this.cLine.getText()));
        pNorth.addItem(new JLabel("Font:"),
            new TextField(this.cLine.getFont().getName()));
        pNorth.addItem(new JLabel("Size:"), 
            new TextField(String.valueOf(this.cLine.getH())));
        pNorth.addItem(new JButton("Salvar"));
        frame.add(new JScrollPane(pNorth));
        frame.pack();
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.pack();
        frame.setLocation(x, y); 
        frame.setVisible(true);        
    }
    
}
