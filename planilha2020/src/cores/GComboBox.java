/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Makar Plakhotnyk
 */
public class GComboBox extends JComboBox<CGroup>{

    private static final long serialVersionUID = 1;
    private final LinkedList<CGroup> itens;
    
    public GComboBox(//CBoxModel<CGroup> сBoxModel,
            LinkedList<CGroup> itens){
        //super(сBoxModel);
        //super(new CBoxModel(itens));
        CBoxModel<CGroup> nCBoxModel = new CBoxModel(itens);
        this.setModel(nCBoxModel);
        this.itens =itens;
        this.setSelectedIndex(0);
        A0 a =new A0();
        this.setRenderer(a);        
        }
    
    public String getItem(int nItem){
        return this.itens.get(nItem).getName();
        }
    
    public String getSelested(){
        int i = this.getSelectedIndex();
        if (i==-1){ i=0; }
        return this.itens.get(i).getName();
        }
    
    public CGroup getSelItem(){
        int i = this.getSelectedIndex();
        return this.itens.get(i);
        }

    private static class A0
        extends JLabel implements ListCellRenderer<CGroup>{

        private static final long serialVersionUID = 1;
        
        @Override
        public Component getListCellRendererComponent(
                JList<? extends CGroup> list, CGroup value, int index,
                boolean isSelected, boolean cellHasFocus) {
            this.setOpaque(true);
            this.setText(value.getName());
            if (isSelected) {
                this.setBackground(Color.blue);
                this.setForeground(Color.PINK);
            } else {
                this.setBackground(Color.white);
                this.setForeground(Color.green);
            }
            return this;        
        }
    
        @Override
        public void paint(Graphics g) {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            super.paint(g);
        } 
    }
    
}

