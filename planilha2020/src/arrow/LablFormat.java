/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Makar Plakhonyk
 */
public class LablFormat implements ActionListener{
    
    private final Arrow arrow;
    
    public LablFormat(Arrow arrow) {
        this.arrow =arrow;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        LablFrame corFrame = new LablFrame(arrow);
        corFrame.setVisible(true);        
        }    
    
}
