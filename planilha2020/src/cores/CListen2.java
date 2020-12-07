package cores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CListen2 implements ActionListener{
    
    private final CComboBox comboCores1;
    private final JTextField exemploField;
    
    CListen2(CComboBox comboCores1, JTextField exemploField) {
        this.comboCores1 =comboCores1;
        this.exemploField =exemploField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.exemploField.setBackground(
            this.comboCores1.getSelested().getColor());

    }
}
