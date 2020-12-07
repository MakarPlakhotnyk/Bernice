package arrow;

import javax.swing.JComboBox;

/**
 *
 * @author Makar Plakhotnyk
 */
public class ConTypes extends JComboBox<String>{
    
    private static final long serialVersionUID = 1;
    
    public ConTypes(){
        super(new String[]{
            "Center", // 0
            "Vertical", // 1
            "Horizontal",// 2
            "Top",// 3
            "Left",// 4
            "Right",// 5
            "Bottom",// 6
            "Closest"// 7
            } );
        }
    
}
