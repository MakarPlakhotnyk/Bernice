package arrow;

import arrow.Arrow;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import plItem.AddArLabel;
import planilha.MPannel;

/** Label of Arrow
 *
 * @author Makar Plakhotnyk
 */
public class ArLabel extends JLabel implements
        MouseMotionListener,
        MouseListener{
    
    private static final long serialVersionUID = 1;
    private int r, prevX, prevY;
    //private boolean isChanging;    
    private final Arrow arrow;
    
    public ArLabel(int xCener, int yCenter, int r, Arrow arrow){
        super();
        this.arrow =arrow;
        this.setBounds(xCener-r, yCenter-r, 2*r, 2*r);
        this.r =r;
        //this.isChanging =true;
        this.prevX =-1;
        this.prevY =-1;        
        this.addMouseListener(this);  
        this.addMouseMotionListener(this);          
        }

//    public ArLabel(int xCener, int yCenter, int r, Arrow arrow,
//            boolean isChanging){
//        super();
//        this.arrow =arrow;
//        this.setBounds(xCener-r, yCenter-r, 2*r, 2*r);
//        this.r =r;
//        this.isChanging =isChanging;
//        this.prevX =-1;
//        this.prevY =-1;        
//        this.addMouseListener(this);  
//        this.addMouseMotionListener(this);          
//        }
    
    public Point getCenter(){
        Rectangle rect = this.getBounds();
        return new Point(rect.x+r, rect.y+r);
        }    
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.isChanging()==false){
            return; }
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        int cx =e.getX();
        int cy =e.getY();        
        int dx =cx -this.prevX;
        int dy =cy -this.prevY;        
        Rectangle bounds = this.getBounds();
        this.setBounds(bounds.x +dx, bounds.y +dy, 2*r, 2*r);
//        int minX =bounds.x -Math.abs(dx);
//        int minY =bounds.y -Math.abs(dy);
//        int wdth = 2*r +3*Math.abs(dx);
//        int heth = 2*r +3*Math.abs(dy);
//        this.mPannel.repaint(minX, minY, wdth, heth);
//        this.prevX =cx;
//        this.prevY =cy;
        this.arrow.recalc();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==3){
            //System.out.println("Edit");
            JPopupMenu ans =new JPopupMenu();
            JMenuItem editItem = new JMenuItem("Add label");
            editItem.addActionListener(new AddArLabel(this.arrow, this));
            ans.add(editItem);
            JMenuItem menuFormat = new JMenuItem("Format");
            menuFormat.addActionListener(new LablFormat(this.arrow));
            ans.add(menuFormat);
            JMenuItem menuRemove = new JMenuItem("Remove Label");
            menuRemove.addActionListener(new RemLabel(this.arrow, this));
            ans.add(menuRemove);
            JMenuItem menuRemArr = new JMenuItem("Remove Arrow");
            menuRemArr.addActionListener(new RemArrow(this.arrow));
            ans.add(menuRemArr);            
            ans.show(e.getComponent(), e.getX(), e.getY());
            return;
            }         
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (this.isChanging()){
            int cx =e.getX();
            int cy =e.getY();               
            this.prevX =cx;
            this.prevY =cy;
            }
    }

    public boolean isChanging(){
        return this.arrow.isChanging();
        }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        if (this.isChanging){
//            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

//    void acivate() {
////        if (!this.isChanging){
////            this.isChanging =true;
////            }
//        this.arrow.recalc();
//        }

    void desAcivate() {
//        if (this.isChanging){
//            this.isChanging =false;
//            }
        this.arrow.recalc();
        }

    boolean isAtive() {
        return this.isChanging();
    }
    
}
