package cores;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import plItem.TextObject;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CorFrame extends JFrame{

    private static final long serialVersionUID = 1;
    
    public CorFrame(TextObject dItem, MPannel mPannel){
        CorPannel0 p = new CorPannel0(dItem, mPannel, this);
        JScrollPane s = new javax.swing.JScrollPane(p);
        this.add(s, BorderLayout.NORTH);
        
        setLocationRelativeTo(null);
        setTitle("Edit text object");
        
        pack();
        setVisible(true);
        }

    
}
