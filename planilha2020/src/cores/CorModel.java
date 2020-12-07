package cores;

import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CorModel extends DefaultComboBoxModel<Cor>{
    
    private static final long serialVersionUID = 1;
    LinkedList<Cor> itens;
    
    public CorModel(LinkedList<Cor> itens){
        if (itens==null){
            System.out.println("Error");
            }
        this.itens =itens;
        }

    @Override
    public Cor getElementAt(int index){
        return this.itens.get(index);
        }
    
    @Override
    public int getSize(){
        return this.itens.size();
        }     
    
}
