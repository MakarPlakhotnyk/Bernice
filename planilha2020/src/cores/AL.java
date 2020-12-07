package cores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Makar Plakhotnyk
 */
public class AL implements ActionListener{

    GComboBox list;
    CComboBox cComboBox2;
    
    public AL(GComboBox list, CComboBox cComboBox2) {
        this.list =list;
        this.cComboBox2 =cComboBox2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CGroup selItem = this.list.getSelItem();
        this.cComboBox2.setNewList(selItem.getCores());
    }

    
}
