/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import arrow.Arrow;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
import geom.Segment;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import planilha.Glowny;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class Losango extends TextObject
        implements IsPlItem, IsDItem,
        MouseMotionListener,
        HasMitky0,
        MouseListener{
    
    private static final long serialVersionUID = 1;
    
    public Losango(MPannel mPannel, String[] params){
        super(mPannel, params);
    }    
    
    public Losango(int x, int y, int w, int h, MPannel mPannel, int id){
        super(x, y, w, h,
            mPannel, id);
        } 

    @Override
    public void setBounds(int x, int y, int w, int h){
        super.setBounds(x, y, w, h);
        for(Arrow arrow:this.arrows){
            arrow.recalc();
            }
        }
    
    @Override
    public void reSize(int dx, int dy, int dw, int dh){
        int repX =this.xl -Math.abs(dx)-2;
        int repy =this.yt -Math.abs(dy)-2;
        int repW =this.w+Math.abs(dw)+Math.abs(dx)+4;
        int repH =this.h+Math.abs(dh)+Math.abs(dy)+4;
        this.xl += dx;
        this.yt += dy;
        this.w += dw;
        this.h += dh;

        Rectangle txtPos = this.getTxtPos(this.xl, this.yt, this.w, this.h);
        this.jTextArea.setBounds(txtPos);
        
        this.setBounds(this.xl +this.r, this.yt +this.r,
            this.w -2*this.r, this.h -2*this.r);        
        this.mPannel.repaint(repX, repy, repW, repH);
        for(Mitka mitka:this.mitkas){
            mitka.setCoord(this.xl, this.yt, this.w, this.h);
            }
        
        }    
    
    @Override
    public boolean contem(Point p){
        int x =p.x -this.xl-this.w/2;
        int y =p.y -this.yt-this.h/2;        
        x =Math.abs(x);
        y =Math.abs(y);
        if (this.w <2*x){return false; }
        boolean ans =(( this.w -2*x)*this.h) >(2*this.w*y);
        return ans;
        }
    
    @Override
    public void draw(Graphics g){
        g.setColor(this.backColor);
        Polygon p = new Polygon();
        p.addPoint(this.xl+this.w/2, this.yt);
        p.addPoint(this.xl+this.w, this.yt+this.h/2);
        p.addPoint(this.xl+this.w/2, this.yt+this.h);
        p.addPoint(this.xl, this.yt+this.h/2);
        g.fillPolygon(p);
        if (this.isActive){
            g.setColor(Color.black);
            g.drawLine(this.xl, this.yt, this.xl, this.yt+this.h);
            g.drawLine(this.xl, this.yt, this.xl+this.w, this.yt);
            g.drawLine(this.xl, this.yt+this.h, this.xl+this.w, this.yt+this.h);
            g.drawLine(
                this.xl+this.w, this.yt,
                this.xl+this.w, this.yt+this.h);
            g.setColor(Color.red);
            for(Mitka mitka:this.mitkas){
                mitka.draw(g);
                }
            
            }
        }

    @Override
    public Rectangle getTxtPos(int x, int y, int w, int h){
        int xText = x+w/4;
        int yText = y +h/4;
        int wText = w/2;
        int hText = h/2;        
        return new Rectangle(xText, yText, wText, hText);
        }    

    @Override
    public String name(){
        return "losango";
        }

    @Override
    public Point getToCenter(Point p1) {
        if (this.contem(p1)){
            return null; }
        Point p2 =this.getCener();
        Segment segment = new Segment(p1, p2);
        Point[] ps = new Point[5];
        ps[0] = new Point(this.xl+this.w/2, this.yt);
        ps[1] = new Point(this.xl+this.w, this.yt+this.h/2);
        ps[2] = new Point(this.xl+this.w/2, this.yt+this.h);
        ps[3] = new Point(this.xl, this.yt+this.h/2);
        ps[4] = ps[0];
        Point ansP =null;
        int ansD =0;
        for(int i=0; i<4; i++){
            Point curAns = segment.inters(ps[i], ps[i+1]);
            if (curAns==null){ continue; }
            int curD =(p1.x -curAns.x)*(p1.x -curAns.x)
                +(p1.y -curAns.y)*(p1.y -curAns.y);
            if ((curD<ansD)||(ansP==null)){
                ansD =curD;
                ansP =curAns;
                }
            }
        return ansP;
    }

//    @Override
//    public void toDoc(PdfContentByte cb, MPannel mPannel) {
//        try {
//            cb.beginText();
//
//            Font font = this.jTextArea.getFont();
//            FontMetrics fontMetrics = this.jTextArea.getFontMetrics(font);
//            int ascent = fontMetrics.getAscent();
//            int size = font.getSize();
//        
//            BaseFont bf =mPannel.getBaseFont(this.fontFName);
//            cb.setFontAndSize(bf, size);
//            Color textCol =this.jTextArea.getForeground();
//            float[] ColorComp = new float[3];
//            ColorComp =textCol.getRGBColorComponents(ColorComp);
//            cb.setRGBColorFillF(ColorComp[0], ColorComp[1], ColorComp[2]);
//            
//            int xText = this.xl+this.w/4;
//            int yText = this.yt +this.h/4;        
//            Insets insets = this.jTextArea.getInsets();
//            xText += insets.left;
//            yText += insets.top;            
//        
//            String[] lines = this.jTextArea.getText().split("\n");
//            int y =mPannel.getHeight() -yText-ascent;
//            for(String line : lines){
//                cb.showTextAligned(
//                    Element.ALIGN_LEFT,
//                    line,
//                    xText,
//                    y,
//                    0);
//                y -= fontMetrics.getHeight();
//                }            
//            cb.endText();         
//        } catch (DocumentException ex) {
//            Logger.getLogger(Losango.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Losango.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//    }

//    @Override
//    public void drawDoc(PdfContentByte cb, MPannel mPannel) {
//        if (!this.isTransp()){
//            cb.setColorStroke(BaseColor.BLUE);
//            float[] ColorComp = new float[3];
//            ColorComp =this.backColor.getRGBColorComponents(ColorComp);
//            cb.setRGBColorFillF(ColorComp[0], ColorComp[1], ColorComp[2]);
//            
//            cb.moveTo(this.xl+this.w/2,
//                mPannel.getHeight() -this.yt);
//            cb.lineTo(this.xl+this.w,
//                mPannel.getHeight() -this.yt-this.h/2);
//            cb.lineTo(this.xl+this.w/2,
//                mPannel.getHeight()-this.yt-this.h);
//            cb.lineTo(this.xl,
//                mPannel.getHeight()-this.yt-this.h/2);
//            cb.fill();
//            }
//        for(Arrow arrow: this.arrows){
//                if (arrow.atartsAt(this)){
//                    arrow.drawDoc(cb, mPannel);
//                    }
//                }            
//    }

    @Override
    public Point getVert(Point p) {
        if (this.contem(p)){ return null; }        
        if (p.x<this.xl){return this.getLeft(); }
        if (p.x>this.xl +this.w ){return this.getRight(); }
        float yPlus = ((float) h)/w *
            (w -Math.abs(2*p.x -2*this.xl -w))
            +2*this.yt +h;
        if (p.y>=yPlus){ return
            new Point(p.x, Math.round(yPlus/2)); }
        float yMinus = -((float) h)/w *
            (w -Math.abs(2*p.x -2*this.xl -w))
            +2*this.yt +h;        
        return new Point(p.x, Math.round(yMinus/2));
        }

    @Override
    public Point getHoriz(Point point) {
        return this.getClosest(point);
    }

    @Override
    public Point getClosest(Point p) {
        if (this.contem(p)){
            return null; }
        if (p.x<=this.xl +this.w/2){
            if (p.y<=this.yt +this.h/2){
                Point p1 = new Point(this.xl, this.yt+this.h/2);
                Point p2 = new Point(this.xl+this.w/2, this.yt);                
                Segment segment = new Segment(p1, p2);
                return segment.getClosest(p);
                }
            Point p1 = new Point(this.xl, this.yt+this.h/2);
            Point p2 = new Point(this.xl+this.w/2, this.yt+this.h);                
            Segment segment = new Segment(p1, p2);
            return segment.getClosest(p);            
            }
        if (p.y<=this.yt +this.h/2){
                Point p1 = new Point(this.xl+this.w/2, this.yt);
                Point p2 = new Point(this.xl+this.w, this.yt+this.h/2);
                Segment segment = new Segment(p1, p2);
                return segment.getClosest(p);
                }
        Point p1 = new Point(this.xl+this.w, this.yt+this.h/2);
        Point p2 = new Point(this.xl+this.w/2, this.yt+this.h);                
        Segment segment = new Segment(p1, p2);
        return segment.getClosest(p); 
        }    
   
}