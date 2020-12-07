/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import arrow.Arrow;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class MLine0 extends TextObject implements
        MouseMotionListener, IsDItem,
        MouseListener, IsPlItem,
        HasMitky0 {
    
    private static final long serialVersionUID = 1;
    
    Rectangle currentRect;
    String fName;
    ChngType chngType;
    private int bordWidth;
    
    public MLine0(MPannel mPannel, String[] params) {
        super(mPannel, params);
        }    
    
    public MLine0(int x, int y, int w, int h, MPannel mPannel, int id){
        super(mPannel, new TOData(
            mPannel, 3,
            x, y, y, h,
            id,
            new Color(124, 252, 0), Color.red,
            "New text"
            ));
        }     
    
    @Override
    public void setBounds(int x, int y, int w, int h){
        super.setBounds(x, y, w, h);
        this.jTextArea.setBounds(x, y, w, h);
        for(Arrow arrow:this.arrows){
            arrow.recalc();
            }
        }    
    
    public void toRight(int panelWidth){
        this.xl =this.mPannel.getW()-this.mPannel.getbR()-this.w;
        this.jTextArea.setLocation(this.xl, this.yt);
        }

    
    private void cursorForm(MouseEvent e){
        int xc = e.getX();
        int yc = e.getY();      

        Rectangle bounds = this.jTextArea.getBounds();
        this.w =bounds.width;
        this.h =bounds.height;
        if (xc<this.bordWidth){
            this.chngType =ChngType.left;
            this.jTextArea.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
            return;
            }
        if (xc>w-this.bordWidth-1){
            this.chngType =ChngType.right;
            this.jTextArea.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            return;            
            }
        if (yc<this.bordWidth+1){
            this.chngType =ChngType.up;
            this.jTextArea.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
            return;            
            }
        if (yc>h-this.bordWidth-1){
            this.chngType =ChngType.down;
            this.jTextArea.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
            return;            
            }        
        this.chngType =null;
        this.jTextArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    
    void updateSize(MouseEvent e) {
    }    

//    public void toDoc(Document document, MPannel mPannel) throws
//            IOException, DocumentException {
//        }

    @Override
    public String name(){
        return "mLine";
        }

    @Override
    public Point getToCenter(Point p) {
        Point curCener = this.getCener();
        if (curCener.x ==p.x){
            int y1 =this.yt;
            int y2 =this.yt +this.h;
            if (Math.abs(y1-p.y)>Math.abs(y2-p.y)){
                return new Point(p.x, y2); }
            return new Point(p.x, y1);
            }
        if (curCener.y ==p.y){
            int x1 =this.xl;
            int x2 =this.xl +this.w;
            if (Math.abs(x1-p.x)>Math.abs(x2-p.x)){
                return new Point(x2, p.y); }
            return new Point(x1, p.y);
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

//    @Override
//    public void add(Arrow arrow) {
//        this.arrows.add(arrow);
//    }
//
//    @Override
//    public void unchMitky() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
    public void draw(Graphics g){
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
//            Insets insets = this.jTextArea.getInsets();
//            int xText = this.xl +insets.left;
//            int yText = this.yt +insets.top;
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
//            ColorComp =this.jTextArea.getBackground().getRGBColorComponents(ColorComp);
//            cb.setRGBColorFillF(ColorComp[0], ColorComp[1], ColorComp[2]);
//            cb.moveTo(this.xl,
//                mPannel.getHeight() -this.yt);
//            cb.lineTo(this.xl+this.w,
//                mPannel.getHeight() -this.yt);
//            cb.lineTo(this.xl+this.w,
//                mPannel.getHeight()-this.yt-this.h);
//            cb.lineTo(this.xl,
//                mPannel.getHeight()-this.yt-this.h);
//            cb.fill();
//            }
//        for(Arrow arrow: this.arrows){
//                if (arrow.atartsAt(this)){
//                    arrow.drawDoc(cb, mPannel);
//                    }
//                }
//    }

    @Override
    public Rectangle getTxtPos(int x, int y, int w, int h) {
        return new Rectangle(x+3, y+3, w-2*3, h-2*3);
    }

    @Override
    public boolean contem(Point p) {
        if ((p.x<this.xl)||(p.x>this.xl+this.w)){
            return false;
            }
        return ((p.y>=this.yt)&&(p.y<=this.yt+this.h));
    }

    @Override
    public Point getVert(Point p) {
        if (p.x<this.xl){
            if (p.y<this.yt){
                return new Point(this.xl, this.yt); }
            if (p.y<this.yt+this.h){
                return new Point(this.xl, p.y); }
            return new Point(this.xl, this.yt +this.h);
            }
        if (p.x <this.xl +this.w){
            if (p.y<this.yt){
                return new Point(p.x, this.yt); }
            if (p.y<this.yt+this.h){
                return null; }
            return new Point(p.x, this.yt +this.h);    
            }
        if (p.y<this.yt){
            return new Point(this.xl +this.w, this.yt); }
        if (p.y<this.yt+this.h){
            return new Point(this.xl +this.w, p.y); }
        return new Point(this.xl +this.w, this.yt +this.h);        
    }

    @Override
    public Point getHoriz(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public Point getClosest(Point p) {
        return this.getToCenter(p);
    }
    
}
