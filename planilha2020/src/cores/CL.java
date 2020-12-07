package cores;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CL implements PopupMenuListener{
    
    private final CComboBox cComboBox;
    
    public CL(CComboBox сComboBox){
        this.cComboBox =сComboBox;
        }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                // This method is called before the popup menu becomes visible.
                //System.out.println("PopupMenuWillBecomeVisible");
                this.cComboBox.setIsClosed(false);
            }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // This method is called before the popup menu becomes invisible
                //System.out.println("PopupMenuWillBecomeInvisible");
                this.cComboBox.setCurCor(this.cComboBox.getSelested());
                this.cComboBox.setIsClosed(true);
            }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
                // This method is called when the popup menu is canceled
                //System.out.println("PopupMenuCanceled");
                this.cComboBox.setIsClosed(true);
            }
    
    
}
