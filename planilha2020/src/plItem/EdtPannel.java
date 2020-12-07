/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class EdtPannel extends JPanel{
    
    private static final long serialVersionUID = 1;
    GridBagLayout bag;
    int y;
    
    public EdtPannel(){
        this.bag =new GridBagLayout();
        this.setLayout(this.bag);
        this.y =0;
        
        }
    
    public final void addItem(Component lRoots, Component lValue){
        GridBagConstraints gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =this.y;
        bag.setConstraints(lRoots, gLRoots);
        this.add(lRoots);            
            
        GridBagConstraints gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =1;
        gValue.weightx =0.5;
        gValue.gridy =this.y;
        bag.setConstraints(lValue, gValue);
        this.add(lValue);
        this.y++;
        }
    
    public final void addItem(Component label){
        GridBagConstraints labelC =new GridBagConstraints();
        labelC.fill =GridBagConstraints.HORIZONTAL;
        labelC.anchor =GridBagConstraints.NORTH;
        labelC.weightx = 0.5;
        labelC.weighty = 0.5;
        labelC.gridwidth =2;
        labelC.gridx =0;
        labelC.gridy =this.y;
        bag.setConstraints(label, labelC);
        this.add(label);
        this.y++;
        }
    
}
