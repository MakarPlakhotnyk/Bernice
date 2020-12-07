/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class ULabel extends JLabel implements
        MouseMotionListener,
        MouseListener, IsPlItem{
    
    private static final long serialVersionUID = 1;
    private FontMetrics fontMetrics;
    private int w, h, x;
    private final MPannel mPannel;
    private final String fontFName;
    
    private int prevX, prevY;
    private boolean isChanging;
    
    public ULabel(String[] params, MPannel mPannel, int k){
        super(params[k+2]);
        String fontFName =params[k];
        int fontSize =Integer.valueOf(params[k+1]);
        String curValor =params[k+2];        
        Font font =mPannel.getFont(fontFName,
            Font.TRUETYPE_FONT, Font.PLAIN, Integer.valueOf(fontSize));        
        this.fontFName =fontFName;
        this.setFont(font);
        //this.activate();
        this.mPannel =mPannel;
        this.isChanging =false;
        this.prevX =-1;
        this.prevY =-1;
        this.fontMetrics = this.getFontMetrics(this.getFont());
        this.w =fontMetrics.stringWidth(curValor);
        this.h =getFont().getSize();
        this.x=0;
        this.addMouseListener(this);   
        this.addMouseMotionListener(this);          
        }    
    
    public ULabel(String value, MPannel mPannel){
        super(value);
        this.fontFName ="data/fonts/times-new-roman/times-new-roman.ttf";
        //this.fontFName ="data/fonts/dialog/Dialog Bold.ttf";
        
        
        
        
        File file = new File(this.fontFName);
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            font = font.deriveFont(Font.PLAIN, 14);
            //System.out.println(font);
            this.setFont(font);
            //FontMetrics fontMetrics1 = this.getFontMetrics(font);
            //fontMetrics1.s
        } catch (IOException ex) {
            Logger.getLogger(CLine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {        
            Logger.getLogger(ULabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.setForeground(new Color(51, 51, 51));
        this.mPannel =mPannel;
        this.isChanging =false;
        this.prevX =-1;
        this.prevY =-1;
        fontMetrics = this.getFontMetrics(this.getFont());
        this.w =fontMetrics.stringWidth(value);
        this.h =getFont().getSize();
        this.x=0;
        this.addMouseListener(this);   
        this.addMouseMotionListener(this);          
        }
    
    public int getW(){
        return this.w; }

    @Override
    public int getX(){
        return this.x; }
    
    public void setX(int x){
        this.x =x; }

    void setBounds(int y) {
        this.setBounds(x, y, w, h);
    }
    
    void setPos(int x, int y){
        this.x =x;
        this.setBounds(x, y, w, h);
        }
    

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.isChanging==false){
            return; }
        int cx  = e.getX();
        this.x += cx-this.prevX;
        Point loc = this.getLocation();
        loc.x =this.x;
        this.setLocation(loc);
        this.mPannel.repaint(0, loc.y, this.mPannel.getW(), this.h*2);
        //this.mPannel.repaint();
        //this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.activate(); }

    @Override
    public void mousePressed(MouseEvent e) {
        this.activate();
        this.prevX  = e.getX();
        this.prevY = e.getY();
        this.isChanging =true;
        repaint(); }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void activate() {
        if (this.mPannel.activate(this)){
            this.setBorder(new LineBorder(Color.YELLOW, 1));
            }
    }

    @Override
    public void desActivate() {
        this.setBorder(null);
    }

    @Override
    public String toFile() {
        String ans =this.fontFName;
        ans += ";" +this.h +";" +this.getText();
        return ans;
    }
    
}
