/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import cores.CorFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class TextFormat implements ActionListener{
    
    private final MPannel mPannel;
    private final TextObject dItem;
    
    public TextFormat(MPannel mPannel, TextObject dItem){
        this.mPannel =mPannel;
        this.dItem =dItem;
        }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //this.mPannel.setDItem(this.dItem);
        CorFrame corFrame = new CorFrame(dItem, mPannel);
        corFrame.setVisible(true);        
        }    
    
}
