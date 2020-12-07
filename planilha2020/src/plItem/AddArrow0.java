/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class AddArrow0 implements ActionListener{
    
    private final MPannel mPannel;
    private final IsDItem dItem;
    
    public AddArrow0(MPannel mPannel, IsDItem dItem){
        this.mPannel =mPannel;
        this.dItem =dItem;
        }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.mPannel.setDItem(this.dItem);
        }    
    
}
