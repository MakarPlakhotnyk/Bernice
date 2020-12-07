/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha.menu;

/**
 *
 * @author Makar Plakhotnyk
 */
import java.awt.event.*;
import javax.swing.*;
import planilha.MPannel;

public class PlSave extends AbstractAction{
    
    MPannel panel;
    private static final long serialVersionUID = 1;
            
    public PlSave(String name, MPannel panel){
        super(name);
        this.panel =panel;
        }
 
    @Override
    public void actionPerformed(ActionEvent event){
        this.panel.toFile();

        }
}
