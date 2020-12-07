package cores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CListen1 implements ActionListener{
    
    private final CComboBox comboCores1;
    private final JTextField exemploField;
    
    public CListen1(CComboBox comboCores1, JTextField exemploField) {
        this.comboCores1 =comboCores1;
        this.exemploField =exemploField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.exemploField.setForeground(
            this.comboCores1.getSelested().getColor());        
    }
}
