package arrow;

import cores.CComboBox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author Makar Plakhotnyk
 */
public class ArrSave implements ActionListener{


    
    private final JFrame jframe;
    private final CComboBox comboCores1;
    private final ConTypes cTypeFrom, cTypeTo;
    private final JCheckBox isTwoside;
    private final Arrow arrow;


    public ArrSave(JFrame jframe, CComboBox comboCores1,
            JCheckBox isTwoside,
            ConTypes cTypeFrom, ConTypes cTypeTo,
            Arrow arrow) {
        this.jframe =jframe;
        this.comboCores1 =comboCores1;
        this.isTwoside =isTwoside;
        this.cTypeFrom =cTypeFrom;
        this.cTypeTo =cTypeTo;
        this.arrow =arrow;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Color textCol = this.comboCores1.getSelested().getColor();
        this.arrow.setColor(textCol);
        int typeFrom = this.cTypeFrom.getSelectedIndex();
        int typeTo = this.cTypeTo.getSelectedIndex();
        this.arrow.setTypes(typeFrom, typeTo);
        this.arrow.setTwoSides(this.isTwoside.isSelected());
        this.jframe.dispatchEvent(
            new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
    }
    
}
