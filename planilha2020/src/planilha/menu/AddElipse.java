/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha.menu;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import plItem.Elipse;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class AddElipse extends AbstractAction {

    private static final long serialVersionUID = 1;
    private final MPannel panel;
    
    public AddElipse(String name, MPannel panel) {
        super(name);
        this.panel =panel;
    }
    
@Override
 public void actionPerformed(ActionEvent event)
 {
    int newId =this.panel.getMaxId()+1;
    Elipse elips= new Elipse(100, 100, 100, 100, panel, newId);
    this.panel.append(elips);
    this.panel.repaint();
 }    
    
}
