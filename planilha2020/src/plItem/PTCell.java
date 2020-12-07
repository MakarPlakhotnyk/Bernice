/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

//import com.itextpdf.text.Phrase;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

/**
 *
 * @author Makar Plakhotnyk
 */
public class PTCell extends JLabel implements
        MouseMotionListener,
        MouseListener{
    
    private static final long serialVersionUID = 1;
    private int linha, coumna;
    int y;
    int w;
    int h;
    PTable pTable;
    boolean isActive;
    private int prevX, prevY;
    private ChngType chngType;
    //private boolean isChanging;
    private String value;
    JLabel jLabel2;
    private final String fontFName;
    private int margLeft, margUp;
    
    
    public PTCell(String value, PTable pTable, int w, int linha, int coumna){
        super();
        //this.setLayout(null);
        //jLabel =new JLabel("value");
        //jLabel.setHorizontalAlignment(SwingConstants.LEFT);
        //jLabel.setBackground(Color.red);
        //jLabel.setHorizontalAlignment(0); // set the horizontal alignement on the x axis !
        //jLabel.setVerticalAlignment(0);
        //jLabel.getFont().
        //jLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        //jLabel.setVerticalTextPosition(SwingConstants.TOP);
        
        this.margLeft =5;
        this.margUp =0;
        
        this.fontFName ="data/fonts/times-new-roman/times-new-roman.ttf";
        
        Font lableFont =null;
        
        File file = new File(this.fontFName);
        try {
            lableFont = Font.createFont(Font.TRUETYPE_FONT, file);
            
            //System.out.println(font);
           
            //FontMetrics fontMetrics1 = this.getFontMetrics(font);
            //fontMetrics1.s
        } catch (IOException ex) {
            Logger.getLogger(ALine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {        
            Logger.getLogger(ALine.class.getName()).log(Level.SEVERE, null, ex);
        }           
        
        //Font thisFont = lableFont.deriveFont(Font.PLAIN, 12);
        //this.setFont(thisFont);
        
        jLabel2 = new JLabel(value);
        Font label2Font =lableFont.deriveFont(Font.PLAIN, 12);
        jLabel2.setFont(label2Font);
        
        this.value =value;
        
        
        
        
        
        this.prevX =0;
        this.prevY =0;
        this.linha =linha;
        this.coumna =coumna;
        this.pTable =pTable;
        this.isActive =false;
        //this.isChanging =false;
        //this.jLabel =new JLabel();
        FontMetrics fontMetrics
            =this.getFontMetrics(this.getFont()); 
        this.w = w;
        this.h =fontMetrics.getHeight();
        //jLabel.setLocation(5, 5);
        //Point p = jLabel.getLocation();
        //System.out.println(p.x +" " +p.y);
        this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        }

    String getValue() {
        //return this.getText();
        return this.value;
    }
    
    int getMinH(){
        FontMetrics fontMetrics
            =this.getFontMetrics(this.getFont()); 
        return fontMetrics.getHeight();
        }

    public void setBounds(int x, int y) {
        this.setBounds(x, y, this.w, h);
        this.jLabel2.setBounds(x+this.margLeft, y+this.margUp,
            this.w-this.margLeft, h-this.margUp);
    }
    
    public JLabel getLabel(){
        return this;
        }

    private void cursorForm(MouseEvent e){
        int xc = e.getX();
        int yc = e.getY();  
        if (xc==w-1){
            this.chngType =ChngType.right;
            this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            return;            
            }
        if (xc==0){
            this.chngType =ChngType.left;
            this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
            return;            
            }
        this.chngType =null;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==1){
            if ((e.getModifiers() & ActionEvent.CTRL_MASK) !=ActionEvent.CTRL_MASK) {
                this.pTable.reEscolha(this);
                }   
            else{
                this.pTable.addEscolha(this);
                }
            this.activate();      
            return;
            }
        if (e.getButton()==3){
            if (this.isActive){
                JPopupMenu ans =new JPopupMenu();
                JMenuItem editItem = new JMenuItem("Edit");
                editItem.addActionListener(new PTCelEdit(this));
                ans.add(editItem);
                ans.show(e.getComponent(), e.getX(), e.getY());
                return;            
                }
            //this.pTable.desActivate();
            this.pTable.mPannel.desActivate();
            
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.cursorForm(e);
        //System.out.println("pressed " +this.pTable.getPos().x);
    }
    
    public void activate() {
        this.setBorder(new LineBorder(Color.YELLOW, 4));
        this.isActive =true;
    }

    public void desActivate() {
        this.setBorder(null);
        this.isActive =false;
        //this.pTable.printWidthes();
    }    

    @Override
    public void mouseReleased(MouseEvent e) {
        this.chngType =null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //this.cursorForm(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.chngType==null){
            return; }    
        int cx  = e.getX();        
        int dX =cx-this.prevX;
        if (this.chngType ==ChngType.right){
            if (this.pTable.getNCols()>this.coumna+1){
                if (this.pTable.incCWidth(this.coumna, dX)){
                    this.prevX =cx;
                    }
                else{
                    this.chngType =null;
                    this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                return;
                }
            if (this.pTable.incRB(dX)){
                this.prevX =cx; }
            else{
                this.chngType =null;
                this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } 
            return;
        
            }
        if (this.chngType ==ChngType.left){
            if (this.coumna>0){
                if (this.pTable.incCWidth(this.coumna-1, dX)){
                    this.prevX =cx -dX;
                    }
                else{
                    this.chngType =null;
                    this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }   
                return;
                }
            if (this.pTable.incLB(dX)){
                this.prevX =cx-dX; }
            else{
                this.chngType =null;
                this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }              
            }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.isActive==false){
            return; }
        this.cursorForm(e);
        this.prevX  = e.getX();
        this.prevY = e.getY(); }

    void setWidths(int newWC) {
        this.w =newWC;
        Point l = this.getLocation();
        this.setBounds(l.x, l.y);
    }

    void incWidth(int dW) {
        this.w += dW;
        Point l = this.getLocation();
        this.setBounds(l.x, l.y); }

    void incLB(int dX) {
        this.w -= dX;
        Point l = this.getLocation();
        l.x =l.x +dX;
        this.setBounds(l.x, l.y); }

    JLabel getLabel2() {
        return this.jLabel2;
    }

    String toFile() {
        return this.value;
    }
    
}
