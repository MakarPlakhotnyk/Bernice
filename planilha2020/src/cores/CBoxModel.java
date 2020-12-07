/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cores;

import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Makar.Plakhotnyk
 * @param <T>
 */
public class CBoxModel<T> extends DefaultComboBoxModel<T>{
    
    private static final long serialVersionUID = 1;
    LinkedList<T> itens;
    
    public CBoxModel(LinkedList<T> itens){
        if (itens==null){
            System.out.println("Error");
            }
        this.itens =itens;
        }

    @Override
    public T getElementAt(int index){
        return this.itens.get(index);
        }
    
    public T get(int index){
        return this.itens.get(index);
        }
    
    @Override
    public int getSize(){
        return this.itens.size();
        }   
    
}
