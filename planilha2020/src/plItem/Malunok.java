/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import arrow.Arrow;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class Malunok extends JLabel implements
        MouseMotionListener, IsDItem,
        MouseListener, IsPlItem {
    
    private static final long serialVersionUID = 1;
    MPannel mPannel;
    ImageIcon icon;
    int xl, yt, w, h;
    int prevX, prevY;
    final int formerW, formerH;
    Rectangle currentRect;
    double coef;
    String fName;
    boolean isActive;
    ChngType chngType;
    LinkedList<Arrow> arrows;
    int id;
    
    public Malunok(MPannel mPannel, String[] params){
        this.id =Integer.valueOf(params[1]);
        int curPar =1;
//mal;./data/jpg/f1.jpg;350;468;94.

//        String ans = "mal;";
//        ans +=this.fName +";";
//        ans += this.xl +";";
//        ans += this.yt +";";
//        ans += this.w +".\r\n";
//        return ans;


        this.arrows =new LinkedList<>();
        this.isActive =false;
        this.chngType =null;
        this.mPannel =mPannel;
        curPar++;
        String property =mPannel.getCurDir();
        String dirName =property +"\\..\\";
        this.fName =params[curPar];
        
        //System.out.println(dirName+this.fName);
        curPar++;
        xl =Integer.valueOf(params[curPar]);
        curPar++;
        yt =Integer.valueOf(params[curPar]);
        curPar++;
        w =Integer.valueOf(params[curPar]);
        //System.out.println(dirName +this.fName);
        this.icon =new ImageIcon(dirName +this.fName);
        //System.out.println(this.icon);
        
        this.setIcon(icon);
        //long round = Math.round(thisicon.getIconWidth()*0.5);
        this.formerW =this.icon.getIconWidth();
        this.formerH =this.icon.getIconHeight();
        this.coef =(double)this.w/(double)this.formerW;
        //w = (int) Math.round(this.formerW*this.coef);
        h = (int) Math.round(this.formerH*this.coef);
        Image img = icon.getImage().getScaledInstance(
            w,
            h, 
            java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(img);
        this.setIcon(newIcon);
        
        this.setBounds(xl, yt, w, h);
        //this.xl =this.mPannel.getBl();
        //this.yt =this.mPannel.getBt();
        this.prevX =-1;
        this.prevY =-1;
        this.currentRect =null;
        this.addMouseListener(this);   
        this.addMouseMotionListener(this);   

    }    
    
    public Malunok(String fName, MPannel mPannel, double coef, int id){
        this.id =id;
        this.arrows =new LinkedList<>();
        this.isActive =false;
        this.chngType =null;
        this.mPannel =mPannel;
        this.icon =new ImageIcon(fName);
        this.fName =fName;
        this.setIcon(icon);
        //long round = Math.round(thisicon.getIconWidth()*0.5);
        this.coef =coef;
        this.formerW =this.icon.getIconWidth();
        this.formerH =this.icon.getIconHeight();
        w = (int) Math.round(this.formerW*this.coef);
        h = (int) Math.round(this.formerH*this.coef);
        Image img = icon.getImage().getScaledInstance(
            w,
            h, 
            java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(img);
        this.setIcon(newIcon);
        
        this.setBounds(mPannel.getBl(), mPannel.getBt(), w, h);
        this.xl =this.mPannel.getBl();
        this.yt =this.mPannel.getBt();
        this.prevX =this.xl;
        this.prevY =this.yt;
        this.currentRect =null;
        this.addMouseListener(this);   
        this.addMouseMotionListener(this);   
        }


    
    public void toRight(int panelWidth){
        this.xl =this.mPannel.getW()-this.mPannel.getbR()-this.w;
        //System.out.println(this.xl);
        this.setLocation(this.xl, this.yt);
        //System.out.println(xl+" "+yt +" "+w +" "+h);
        }

    
    private void cursorForm(MouseEvent e){
        int xc = e.getX();
        int yc = e.getY();      
        //System.out.println(xc +" "+yc);
        if (xc<4){
            this.chngType =ChngType.left;
            this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
            return;
            }
        if (xc>w-4){
            this.chngType =ChngType.right;
            this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            return;            
            }
        if (yc<4){
            this.chngType =ChngType.up;
            this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
            return;            
            }
        if (yc>h-4){
            this.chngType =ChngType.down;
            this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
            return;            
            }        
        this.chngType =null;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        updateSize(e);
        if (this.isActive==false){
            return; }
        int cx  = e.getX();
        int cy = e.getY();
        if (this.chngType ==null){
            this.xl += cx-this.prevX;
            this.yt += cy-this.prevY;
            this.setLocation(this.xl, this.yt);
            this.repaint();
            return;
            }
        if (this.chngType==ChngType.right){
            int newW =this.w +cx-this.prevX;
            this.w =newW;
            this.coef =(float)this.w /this.formerW;
            this.h =(int) Math.round(this.formerH *this.coef);
            Image img = icon.getImage().getScaledInstance(
                w, h, 
                java.awt.Image.SCALE_SMOOTH);
            this.setBounds(this.xl, this.yt, w, h);
            ImageIcon newIcon = new ImageIcon(img);
            this.setIcon(newIcon);
            this.prevX =cx;
            this.repaint();
            return;
            }
        if (this.chngType==ChngType.left){
            int dx =cx-this.prevX;
            this.w =this.w -dx;
            this.xl =this.xl +dx;
            this.prevX =cx-dx;
            this.coef =(float)this.w /this.formerW;
            this.h =(int) Math.round(this.formerH *this.coef);
            Image img = icon.getImage().getScaledInstance(
                w, h, 
                java.awt.Image.SCALE_SMOOTH);
            this.setBounds(this.xl, this.yt, w, h);
            ImageIcon newIcon = new ImageIcon(img);
            this.setIcon(newIcon);
            this.repaint();
            return;
            }        
        if (this.chngType==ChngType.down){
            int dy =cy-this.prevY;
            this.h =this.h +dy;
            this.prevY =cy;
            int oldW =this.w;
            this.coef =(float)this.h /this.formerH;
            this.w =(int) Math.round(this.formerW *this.coef);
            this.xl =this.xl -(this.w -oldW)/2;
            Image img = icon.getImage().getScaledInstance(
                w, h, 
                java.awt.Image.SCALE_SMOOTH);
            this.setBounds(this.xl, this.yt, w, h);
            ImageIcon newIcon = new ImageIcon(img);
            this.setIcon(newIcon);
            this.repaint();
            }  
        if (this.chngType==ChngType.up){
            int dy =cy-this.prevY;
            this.yt =this.yt +dy;
            this.h =this.h -dy;
            this.prevY =cy-dy;
            int oldW =this.w;
            this.coef =(float)this.h /this.formerH;
            this.w =(int) Math.round(this.formerW *this.coef);
            this.xl =this.xl -(this.w -oldW)/2;
            Image img = icon.getImage().getScaledInstance(
                w, h, 
                java.awt.Image.SCALE_SMOOTH);
            this.setBounds(this.xl, this.yt, w, h);
            ImageIcon newIcon = new ImageIcon(img);
            this.setIcon(newIcon);
            this.repaint();
            }         
    }

    
    @Override
    public void setBounds(int x, int y, int w, int h){
        super.setBounds(x, y, w, h);
        for(Arrow arrow:this.arrows){
            arrow.recalc();
            }
        }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        this.cursorForm(e);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //this.setBorder(new LineBorder(Color.YELLOW, 4));
        this.activate();
        //System.out.println("Edit0");
        if (e.getButton()==1){
            this.cursorForm(e);
            //this.activate();
            }
        if ((e.getButton()==3)&&(this.isActive)){
            //System.out.println("Edit");
            JPopupMenu ans =new JPopupMenu();
            JMenuItem editItem = new JMenuItem("Add Arrow");
            editItem.addActionListener(new AddArrow0(this.mPannel, this));
            ans.add(editItem);
            ans.show(e.getComponent(), e.getX(), e.getY());
            return;
            }        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.activate();
        this.prevX  = e.getX();
        this.prevY = e.getY();
        this.isActive =true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updateSize(e);
        this.isActive =false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.cursorForm(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        //this.setBorder(null);
    }
    
    void updateSize(MouseEvent e) {
    }    

//    public void toDoc(Document document, MPannel mPannel) throws
//            IOException, DocumentException {
//        com.itextpdf.text.Image image =
//            com.itextpdf.text.Image.getInstance(this.fName);                
//        float width = image.getWidth();
//        float height = image.getHeight();
//        long fHeight = Math.round(height*this.coef);
//        image.scaleAbsolute(
//            Math.round(width*this.coef),
//            Math.round(height*this.coef));
//        image.setAbsolutePosition(this.xl,
//            mPannel.getHeight()-fHeight -this.yt);
//        document.add(image);         
//        }

    @Override
    public void activate() {
        if (this.mPannel.hasDItem()){
            this.mPannel.addArrow(this); }
        else{
            this.mPannel.delDItem(); }        
        this.isActive =true;
        if (this.mPannel.activate(this)){
            this.setBorder(new LineBorder(Color.YELLOW, 4));
            }
        for(Arrow arrow: this.arrows){
            arrow.acivate();
            }        
    }

    @Override
    public void desActivate() {
        this.setBorder(null);
        for(Arrow arrow: this.arrows){
            arrow.desAcivate();
            }        
    }

    @Override
    public String toFile() {
        String ans = "mal;";
        ans +=this.id +";";
        ans +=this.fName +";";
        ans += this.xl +";";
        ans += this.yt +";";
        ans += this.w +".\r\n";
        return ans;
    }
 
    @Override
    public Point getCener() {
        return new Point(this.xl +this.w/2, this.yt +this.h/2);
    }

    @Override
    public Point getToCenter(Point p) {
        Point curCener = this.getCener();
        if (curCener.x ==p.x){
            int y1 =this.yt;
            int y2 =this.yt +this.h;
            if (Math.abs(y1-curCener.y)>Math.abs(y2-curCener.y)){
                return new Point(p.x, y1); }
            return new Point(p.x, y2);
            }
        if (curCener.y ==p.y){
            int x1 =this.xl;
            int x2 =this.xl +this.w;
            if (Math.abs(x1-curCener.x)>Math.abs(x2-curCener.x)){
                return new Point(x1, p.y); }
            return new Point(x2, p.y);
            }
        int[][] ans =new int[4][2];
        int lenAns =0;
        int ansX =Math.round(((float)(curCener.x -p.x))/
            (curCener.y -p.y)*
            (this.yt -curCener.y) +curCener.x);
        if (ansX>=this.xl && ansX<=this.xl +this.w){
            ans[lenAns][0] =ansX;
            ans[lenAns][1] =this.yt;
            lenAns++; }
        ansX =Math.round(((float)(curCener.x -p.x))/
            (curCener.y -p.y)*
            (this.yt+this.h -curCener.y) +curCener.x);
        if (ansX>=this.xl && ansX<=this.xl +this.w){
            ans[lenAns][0] =ansX;
            ans[lenAns][1] =this.yt+this.h;
            lenAns++; }
        int ansY =Math.round((float)(curCener.y-p.y)/
            (curCener.x -p.x)*
            (this.xl-curCener.x) +curCener.y);
        if (ansY>=this.yt && ansY<=this.yt +this.h){
            ans[lenAns][0] =this.xl;
            ans[lenAns][1] =ansY;
            lenAns++; }    
        ansY =Math.round((float)(curCener.y-p.y)/
            (curCener.x -p.x)*
            (this.xl +this.w-curCener.x) +curCener.y);
        if (ansY>=this.yt && ansY<=this.yt +this.h){
            ans[lenAns][0] =this.xl+this.w;
            ans[lenAns][1] =ansY;
            lenAns++; }        
        int curAns =0;
        int curDict2 =(ans[0][0]-p.x)*(ans[0][0]-p.x) +(ans[0][1]-p.y)*(ans[0][1]-p.y);
        for(int i=1; i<lenAns; i++){
            int toCurDict2 =(ans[i][0]-p.x)*(ans[i][0]-p.x)
                +(ans[i][1]-p.y)*(ans[i][1]-p.y);
            if (toCurDict2<curDict2){
                curAns =i;
                curDict2 =toCurDict2;
                }
            }
        return new Point(ans[curAns][0], ans[curAns][1]);
    }

    @Override
    public void add(Arrow arrow) {
        this.arrows.add(arrow);
    }

    @Override
    public int getId() {
        return this.id; }

    @Override
    public boolean hasId(int fndId) {
        return this.id ==fndId; }

    @Override
    public boolean isActive() {
        return this.isActive;
    }
    
    @Override
    public final void remove(Arrow arrow){
        this.arrows.remove(arrow);
        }    

    @Override
    public Point getTop() {
        return new Point(
            this.xl + this.w/2,
            this.yt);
    }

    @Override
    public Point getLeft() {
        return new Point(
            this.xl,
            this.yt +this.h/2);
    }

    @Override
    public Point getRight() {
        return new Point(
            this.xl +this.w,
            this.yt +this.h/2);
    }

    @Override
    public Point getBottom() {
        return new Point(
            this.xl +this.w/2,
            this.yt +this.h);
    }

    @Override
    public Point getVert(Point point) {
        return this.getToCenter(point); }

    @Override
    public Point getHoriz(Point point) {
        return this.getToCenter(point); }
 
    @Override
    public Point getClosest(Point p) {
        return this.getToCenter(p); }
    
}
