/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;

/**
 *
 * @author Makar Plakhotnyk
 */
public abstract class Mitka extends JLabel implements
        MouseMotionListener,
        MouseListener{
    
    private static final long serialVersionUID = 1;
    int r;
    HasMitky0 hasMitky;
    int prevX, prevY;
    boolean isChanging;

//public Mitka(int xL, int yT, int w, int h, float coefH, float coefW, float coefR, int r, HasMitky hasMitky) {
//    this.hasMitky =hasMitky;
//    int xBound =Math.round( xL + w*coefW + r*coefR);
//    }
    
public Mitka(int xCener, int yCenter, int r, HasMitky0 hasMitky) {
    super();
    this.r = r;
    //System.out.println(xCener +" "+ yCenter+" "+r);
    setBounds(xCener-r, yCenter-r, 2*r, 2*r);
    //this.setText("    ***");
    this.hasMitky =hasMitky;

    isChanging =false;
    this.prevX =-1;
    this.prevY =-1;
    this.addMouseListener(this);  
    this.addMouseMotionListener(this);    
    }

public Point getCenter(){
    Rectangle rect = this.getBounds();
    return new Point(rect.x+r, rect.y+r);
    }

public void draw(Graphics g){
    Rectangle bounds = this.getBounds();
    g.fillOval(bounds.x, bounds.y, this.r*2, this.r*2);
    //System.out.println("Mitka: draw");
    }

public void setCenter(Point p) {
    this.setBounds(p.x-r, p.y-r, 2*r, 2*r);
    }    

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.isChanging==false){
            return; }        
        int cx  = e.getX();
        int cy = e.getY();
        int dx =cx-this.prevX;
        int dy =cy-this.prevY;
        this.resElipse(dx, dy);
        
    }

//    void resElipse(int dx, int dy){
//        this.hasMitky.reSize(dx, dy, -dx, -dy);
//        }
    abstract void resElipse(int dx, int dy);
    
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.hasMitky.unchMitky();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mitka: mousePressed");
        this.prevX  = e.getX();
        this.prevY = e.getY();
        this.hasMitky.unchMitky();
        this.isChanging =true;        
    }
    
    public void unChanged(){
        this.isChanging =false;
        }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.isChanging =false;        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public abstract void setCoord(int xl, int yt, int w, int h);
    
}
