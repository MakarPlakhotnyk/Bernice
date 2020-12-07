package arrow;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Makar Plakhotnyk
 */
public class LablFrame extends JFrame{

    private static final long serialVersionUID = 1;
    
    public LablFrame(Arrow arrow){
        LablPannel p = new LablPannel(arrow, this);
        JScrollPane s = new javax.swing.JScrollPane(p);
        this.add(s, BorderLayout.NORTH);
        
        setLocationRelativeTo(null);
        setTitle("Edit label");
        
        pack();
        setVisible(true);
        }

}
