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
public class CComboBox extends JComboBox<Cor>{

    private static final long serialVersionUID = 1;
    private LinkedList<Cor> itens;
    private Cor curCor;
    private boolean isClosed;
    
    public CComboBox(LinkedList<Cor> itens, CBoxModel<Cor> cBoxModel){
        super(cBoxModel);
        this.itens =itens;
        this.setSelectedIndex(0);
        this.curCor =itens.getFirst();
        B b =new B(this);
        this.setRenderer(b);
        addPopupMenuListener(new CL(this));
        this.isClosed =true;
        }
    
    void setNewList(LinkedList<Cor> itens) {
        CBoxModel<Cor> newBoxModel =new CBoxModel<>(itens);
        this.setModel(newBoxModel);
        this.itens =itens;
        this.setSelectedIndex(0);
        this.curCor =itens.getFirst();
    }    
    
    public void setIsClosed(boolean isClosed){
        this.isClosed = isClosed;
        }
    
    public boolean isClosed(){
        return this.isClosed;
        }
    
        @Override
    public void setSelectedIndex(int i){
        super.setSelectedIndex(i);
        this.curCor =this.itens.get(i);
        }
    
    public void setCurCor(Cor curCor){
        this.curCor =curCor;
        }
    
    public Cor getCurCor(){
        return this.curCor;
        }
    
    public Cor getItem(int nItem){
        return this.itens.get(nItem);
        }
    
    public Cor getSelested(){
        int i = this.getSelectedIndex();
        if (i==-1){ i=0; }
        return this.itens.get(i);
        }

//    public void setValues(LinkedList<T> itens) {
//        this.removeAllItems();
//        for(T t: itens){
//            this.addItem(t); }
//        this.itens =itens;
//        this.setModel(new CBoxModel(itens));
//        this.setSelectedIndex(0);
//        }

    private static class B extends JLabel implements ListCellRenderer<Cor> {

        private static final long serialVersionUID = 1;
    private final CComboBox cComboBox;
    
    public B(CComboBox cComboBox){
        this.cComboBox =cComboBox;
        }   
    
    @Override
    public Component getListCellRendererComponent(
            JList<? extends Cor> list, Cor value, int index,
            boolean isSelected, boolean cellHasFocus) {
        this.setOpaque(true);
        this.setText(value.getName());
        Color color = value.getColor();
        this.setBackground(color);
        this.setForeground(value.getComplem());
        return this;        
    }
    
    @Override
    public void paint(Graphics g) {
        if (this.cComboBox.isClosed()){
            setBackground(this.cComboBox.getCurCor().getColor());
            setForeground(this.cComboBox.getCurCor().getComplem());
            }
        super.paint(g);
    }       
    
    }


    
}

