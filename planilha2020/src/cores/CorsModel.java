package cores;

import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CorsModel extends DefaultComboBoxModel<String>{
    
    LinkedList<CGroup> itens;
    private static final long serialVersionUID = 1;
    
    public CorsModel(LinkedList<CGroup> itens){
        if (itens==null){
            System.out.println("Error");
            }
        this.itens =itens;
        }

    @Override
    public String getElementAt(int index){
        return this.itens.get(index).getName();
        }
    
    @Override
    public int getSize(){
        return this.itens.size();
        }     
    
}
