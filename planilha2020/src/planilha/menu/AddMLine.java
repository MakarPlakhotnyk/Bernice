/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha.menu;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import plItem.MLine0;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class AddMLine extends AbstractAction {

    private static final long serialVersionUID = 1;
    private final MPannel panel;
    
    public AddMLine(String name, MPannel panel) {
        super(name);
        this.panel =panel;
    }
    
@Override
 public void actionPerformed(ActionEvent event)
 {
    int newId =this.panel.getMaxId()+1;
    MLine0 elips= new MLine0(200, 200, 200, 200, panel, newId);
    this.panel.append(elips);
    this.panel.repaint();     
//    int newId =this.panel.getMaxId()+1;
//    MLine0 mLine =new MLine0(this.panel, newId);
//    mLine.setText("text");
//    Font font = mLine.getFont();
//    FontMetrics fontMetrics = mLine.getFontMetrics(font);
//    int w =fontMetrics.stringWidth("text");
//    int h =2 +(int) Math.round(font.getSize()*1.5);
//    Color c =new Color(124, 252, 0);
//    mLine.setBackground(c);
//    mLine.setForeground(Color.red);
//    Rectangle bounds = mLine.getBounds();
//    mLine.setBounds(panel.getBl(), panel.getBt(), w, h);
//    this.panel.append(mLine);
//    this.panel.repaint();
 }    
    
}
