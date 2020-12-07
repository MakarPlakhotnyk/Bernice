/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cores;

import java.util.LinkedList;
import javax.swing.JComboBox;

/**
 *
 * @author Makar Plakhotnyk
 * @param <T>
 */
public class ComboBox<T> extends JComboBox<T>{

    private static final long serialVersionUID = 1;
    private LinkedList<T> itens;
    
    public ComboBox(LinkedList<T> itens){
        super(new CBoxModel<>(itens));
        this.itens =itens;
        this.setSelectedIndex(0);
        }
    
    public T getItem(int nItem){
        return this.itens.get(nItem);
        }
    
    public T getSelested(){
        int i = this.getSelectedIndex();
        if (i==-1){ i=0; }
        return this.itens.get(i);
        }

    public void setValues(LinkedList<T> itens) {
        this.removeAllItems();
        for(T t: itens){
            this.addItem(t); }
        this.itens =itens;
        CBoxModel<T> newM =new CBoxModel<>(itens);
        this.setModel(newM);
        this.setSelectedIndex(0);
        }
    
}

