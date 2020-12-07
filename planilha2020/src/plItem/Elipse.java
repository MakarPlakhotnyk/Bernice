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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class Elipse extends TextObject
        //JTextPane
//        JLabel
        implements IsPlItem, IsDItem,
        MouseMotionListener, HasMitky0,
        MouseListener{
    
    private static final long serialVersionUID = 1;
    
//    private int xl, yt, w, h;
    private Point f1, f2;
    private double a,b,c;
//    private boolean isActive;
//    private MPannel mPannel;
//    private Mitka[] mitkas;
//    int r, prevX, prevY;
//    TextArea jTextArea;
//    LinkedList<Arrow> arrows;    
//    private Color backColor//, textColor
//            ;
//    private String fontFName;
//    private int id;
    
    
    public Elipse(MPannel mPannel, String[] params){
        super(mPannel, params);
//        this.id =Integer.valueOf(params[1]);
//        int curPar =1;
////elips;255;255;255;0;;323;264;150;300    
////        String ans = "elips;";
////        Color c = this.jTextArea.getBackground();
////        ans +=c.getRed() +";";
////        ans +=c.getGreen() +";";
////        ans +=c.getBlue() +";";
////        String text = this.jTextArea.getText();
////        ans +=text.length() +";" +text +";";
////        ans +=this.xl +";" +this.yt +";";
////        ans +=this.w +";" +this.h +".\n";
//        this.arrows =new LinkedList<>();
//        this.mPannel =mPannel;
//
//        curPar++;
//        int compRed =Integer.valueOf(params[curPar]);
//        curPar++;
//        int compGreen =Integer.valueOf(params[curPar]);
//        curPar++;
//        int compBlue =Integer.valueOf(params[curPar]);
//        this.backColor =new Color(compRed, compGreen, compBlue);
//        //this.backColor =new Color(204, 204, 255);
//
//        curPar++;
//        compRed =Integer.valueOf(params[curPar]);
//        curPar++;
//        compGreen =Integer.valueOf(params[curPar]);
//        curPar++;
//        compBlue =Integer.valueOf(params[curPar]);
//        Color textColor =new Color(compRed, compGreen, compBlue);
//        
//        curPar++;
//        int valueLen = Integer.valueOf(params[curPar]);
//        curPar++;
//        String toValue =params[curPar];
//        curPar++;
//        this.xl =Integer.valueOf(params[curPar]);
//        curPar++;
//        this.yt =Integer.valueOf(params[curPar]);
//        curPar++;
//        this.w =Integer.valueOf(params[curPar]);
//        curPar++;
//        this.h =Integer.valueOf(params[curPar]);
//
//        
//        this.r=3;
//        
        this.a =h/2;
        this.b=w/2;
        
        if (a<b){
            double tmp =a;
            a =b;
            b =tmp;
            }
        this.c = Math.sqrt(a*a -b*b);
        //double e = this.c/a;
        if (w>h){
            f1 =new Point(
                (int) Math.round(-c) +this.xl +this.w/2,
                this.yt +this.h/2);
            f2 =new Point((int) Math.round(c) +this.xl +this.w/2,
                this.yt +this.h/2);
            }
        else{
            f1 =new Point(this.xl +this.w/2,
                (int) Math.round(-c) +this.yt +this.h/2);
            f2 =new Point(this.xl +this.w/2, 
                (int) Math.round(c) +this.yt +this.h/2);            
            }
//        //System.out.println("(" +this.f1.x +"; " +this.f1.y +")");
//        //System.out.println("(" +this.f2.x +"; " +this.f2.y +")");
//        this.mitkas = new Mitka[8];
//        Mitka mitka =new Mitka(this.xl, this.yt, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(dx, dy, -dx, -dy); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl-r, yt-r, 2*r, 2*r);
//            }
//            };
//        this.mitkas[0] =mitka;
//        mitka =new Mitka(this.xl, this.yt +this.h/2, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(dx, 0, -dx, 0); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl-r, yt+h/2-r, 2*r, 2*r);
//            }
//            };        
//        this.mitkas[1] =mitka;
//        mitka =new Mitka(this.xl, this.yt +this.h, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(dx, 0, -dx, dy); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl-r, yt+h-r, 2*r, 2*r);
//            }
//            };        
//        this.mitkas[2] =mitka;        
//        mitka =new Mitka(this.xl +this.w/2, this.yt +this.h, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(0, 0, 0, dy); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl+w/2-r, yt+h-r, 2*r, 2*r);
//            }
//            };        
//        this.mitkas[3] =mitka;    
//        mitka =new Mitka(this.xl +this.w, this.yt +this.h, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(0, 0, dx, dy); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl+w-r, yt+h-r, 2*r, 2*r);
//            }
//            };        
//        this.mitkas[4] =mitka;    
//        mitka =new Mitka(this.xl +this.w, this.yt +this.h/2, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(0, 0, dx, 0); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl+w-r, yt+h/2-r, 2*r, 2*r);
//            }
//            };        
//        this.mitkas[5] =mitka;    
//        mitka =new Mitka(this.xl +this.w, this.yt, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(0, dy, dx, -dy); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl+w-r, yt-r, 2*r, 2*r);
//            }
//            };        
//        this.mitkas[6] =mitka;  
//                mitka =new Mitka(this.xl +this.w/2, this.yt, this.r, this) {
//            @Override
//            void resElipse(int dx, int dy) {
//                this.elips.reSize(0, dy, 0, -dy); }
//
//            @Override
//            public void setCoord(int xl, int yt, int w, int h) {
//                this.setBounds(xl+w/2-r, yt-r, 2*r, 2*r);
//            }
//            };        
//        this.mitkas[7] =mitka;  
//        this.isActive =false;
//        int xText = (int) Math.round(this.xl +this.w/2 -this.w*Math.sqrt(2)/4);
//        int yText = (int) Math.round(this.yt +this.h/2 -this.h*Math.sqrt(2)/4);
//        int wText = (int) Math.round(this.w/Math.sqrt(2));
//        int hText = (int) Math.round(this.h/Math.sqrt(2));
//        //ElLabel elLabel =new ElLabel(this);
//
//        int fontSize =14;
//        this.fontFName ="data/fonts/times-new-roman/times-new-roman.ttf";
//        Font font =mPannel.getFont(fontFName,
//            Font.TRUETYPE_FONT, Font.PLAIN, fontSize);        
//        
//        this.jTextArea = new TextArea();
//        this.jTextArea.setFont(font);
//        jTextArea.setBounds(xText, yText, wText, hText);
//        this.jTextArea.setBackground(this.backColor);
//        this.jTextArea.setForeground(textColor);
//        jTextArea.setValue(toValue);
//        this.setBounds(this.xl +this.r, this.yt +this.r,
//            this.w -2*this.r, this.h -2*this.r);
//        mPannel.add(this);
//        mPannel.add(jTextArea);
//        this.addMouseListener(this);   
//        this.addMouseMotionListener(this);          
//        this.prevX =-1;
//        this.prevY =-1;        
        
    }    
    
    public Elipse(int x, int y, int w, int h, MPannel mPannel, int id){
        super(x, y, w, h,
            mPannel, id);
        this.a =h/2;
        this.b=w/2;
        
        if (a<b){
            double tmp =a;
            a =b;
            b =tmp;
            }
        this.c = Math.sqrt(a*a -b*b);
        //double e = this.c/a;
        if (w>h){
            f1 =new Point(
                (int) Math.round(-c) +this.xl +this.w/2,
                this.yt +this.h/2);
            f2 =new Point((int) Math.round(c) +this.xl +this.w/2,
                this.yt +this.h/2);
            }
        else{
            f1 =new Point(this.xl +this.w/2,
                (int) Math.round(-c) +this.yt +this.h/2);
            f2 =new Point(this.xl +this.w/2, 
                (int) Math.round(c) +this.yt +this.h/2);            
            }
        } 

    @Override
    public void setBounds(int x, int y, int w, int h){
        super.setBounds(x, y, w, h);
        for(Arrow arrow:this.arrows){
            arrow.recalc();
            }
        }
    
//    @Override
//    public FontMetrics getFontMetrics(Font font) {
//        return new FontMetricsWrapper(super.getFontMetrics(font)) {
//            @Override
//            public int getHeight() {
//                return 10;  // Gives line height in pixels
//            }
//        };
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
        this.a =h/2;
        this.b=w/2;
        if (a<b){
            double tmp =a;
            a =b;
            b =tmp;
            }
        this.c = Math.sqrt(a*a -b*b);
        //double e = this.c/a;
        if (w>h){
            f1 =new Point(
                (int) Math.round(-c) +this.xl +this.w/2,
                this.yt +this.h/2);
            f2 =new Point((int) Math.round(c) +this.xl +this.w/2,
                this.yt +this.h/2);
            }
        else{
            f1 =new Point(this.xl +this.w/2,
                (int) Math.round(-c) +this.yt +this.h/2);
            f2 =new Point(this.xl +this.w/2, 
                (int) Math.round(c) +this.yt +this.h/2);            
            }
        int xText = (int) Math.round(this.xl +this.w/2 -this.w*Math.sqrt(2)/4);
        int yText = (int) Math.round(this.yt +this.h/2 -this.h*Math.sqrt(2)/4);
        int wText = (int) Math.round(this.w/Math.sqrt(2));
        int hText = (int) Math.round(this.h/Math.sqrt(2));
        this.jTextArea.setBounds(xText, yText, wText, hText);
        
        
        
        this.setBounds(this.xl +this.r, this.yt +this.r,
            this.w -2*this.r, this.h -2*this.r);        
        this.mPannel.repaint(repX, repy, repW, repH);
        for(Mitka mitka:this.mitkas){
            mitka.setCoord(this.xl, this.yt, this.w, this.h);
            }
        
        }    
    
//    public boolean isOnBorder(Point p){
//        double d =
//            Math.sqrt(
//                (p.x -this.f1.x)*(p.x -this.f1.x) 
//                +(p.y -this.f1.y)*(p.y -this.f1.y))
//            +Math.sqrt(
//                (p.x -this.f2.x)*(p.x -this.f2.x)
//                +(p.y -this.f2.y)*(p.y -this.f2.y));
//        return Math.abs(d-2*this.a)<2;
//        }
    
    @Override
    public boolean contem(Point p){
        double d =
            Math.sqrt(
                (p.x -this.f1.x)*(p.x -this.f1.x)+
                (p.y -this.f1.y)*(p.y -this.f1.y))+
            Math.sqrt(
                (p.x -this.f2.x)*(p.x -this.f2.x)+
                (p.y -this.f2.y)*(p.y -this.f2.y));
        //System.out.println(d);
        //System.out.println(2*a);
        return d<2*a;
        }
    
    @Override
    public void draw(Graphics g){
        g.setColor(this.backColor);
        g.fillOval(this.xl, this.yt, this.w, this.h);
        if (this.isActive){
            //System.out.println("Activated");
            g.setColor(Color.black);
            g.drawOval(this.xl, this.yt, this.w, this.h);
            //g.setColor(Color.red);
            //g.fillOval(this.f1.x, this.f1.y, 5, 5);
            //g.fillOval(this.f2.x, this.f2.y, 5, 5);
            g.setColor(Color.black);
            g.drawLine(this.xl, this.yt, this.xl, this.yt+this.h);
            g.drawLine(this.xl, this.yt, this.xl+this.w, this.yt);
            g.drawLine(this.xl, this.yt+this.h, this.xl+this.w, this.yt+this.h);
            g.drawLine(
                this.xl+this.w, this.yt,
                this.xl+this.w, this.yt+this.h);
//            int xText = (int) Math.round(this.xl +this.w/2 -this.w*Math.sqrt(2)/4);
//            int yText = (int) Math.round(this.yt +this.h/2 -this.h*Math.sqrt(2)/4);
//            int wText = (int) Math.round(this.w/Math.sqrt(2));
//            int hText = (int) Math.round(this.h/Math.sqrt(2));
//            g.drawLine(
//                xText, yText,
//                xText+wText, yText+hText);
            g.setColor(Color.red);
            for(Mitka mitka:this.mitkas){
                mitka.draw(g);
                }
            
            }
        }

//    @Override
//    public void activate() {
//        for(Arrow arrow: this.arrows){
//            arrow.acivate();
//            }         
//        if (this.mPannel.hasDItem()){
//            this.mPannel.addArrow(this); }
//        else{
//            this.mPannel.delDItem(); }
//        this.isActive =true;
//        for(Mitka mitka:this.mitkas){
//            this.mPannel.add(mitka);
//            }
//        this.jTextArea.requestFocus();
//    }
    
//    @Override
//    public void desActivate() {
//        for(Arrow arrow: this.arrows){
//            arrow.desAcivate();
//            }          
//        this.isActive =false;
//        for(Mitka mitka:this.mitkas){
//            this.mPannel.remove(mitka);
//            }
//        
////        JTextArea j = new JTextArea();
////        j.setBorder(this.jTextArea.getBorder());
////        j.setBounds(this.jTextArea.getBounds());
////        j.setBackground(this.jTextArea.getBackground());
////        j.setText(this.jTextArea.getText());
////        this.mPannel.remove(this.jTextArea);
////        this.jTextArea =j;
////        this.mPannel.add(this.jTextArea);
//        
//        this.mPannel.repaint(this.xl-this.r, this.yt-this.r,
//            this.w+2*this.r, this.h+2*this.r);
//        //this.mPannel.repaint(j.getBounds());
//    }

    public Rectangle getRectangle() {
        return new Rectangle(this.xl-4, this.yt-4, this.w+8, this.h+8);
    }

//    @Override
//    public void unchMitky() {
//        for(Mitka mitka:this.mitkas){
//            mitka.unChanged();
//            }
//    }

//    @Override
//    public void mouseDragged(MouseEvent e) {
//        if (this.isActive==false){
//            return; }
//        int cx =e.getX();
//        int cy =e.getY();        
//        int dx =cx -this.prevX;
//        int dy =cy -this.prevY;
//        this.reSize(dx, dy, 0, 0);
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        //System.out.println("mouseClicked");
//        if ((e.getButton()==3)&&(this.isActive)){
//            //System.out.println("Edit");
//            JPopupMenu ans =new JPopupMenu();
//            JMenuItem editItem = new JMenuItem("Add Arrow");
//            editItem.addActionListener(new AddArrow0(this.mPannel, this));
//            ans.add(editItem);
//            JMenuItem menuFormat = new JMenuItem("Format");
//            menuFormat.addActionListener(new MenuFormat(this.mPannel, this));
//            ans.add(menuFormat);            
//            ans.show(e.getComponent(), e.getX(), e.getY());
//            return;
//            }         
//        int cx =e.getX();
//        int cy =e.getY();
//        if (this.contem(new Point(this.xl +cx, this.yt +cy))){
//            this.mPannel.activate(this);
//            this.activate();
//            this.mPannel.repaint(this.xl-this.r,
//                this.yt-this.r, this.w+2*r, this.h+2*r);
//            //System.out.println("Elipse is activated");
//            this.prevX =cx;
//            this.prevY =cy;
//            Point p =this.inText(cx, cy);
//            if (p!=null){
//                this.jTextArea.setCaretPosition(
//                    this.jTextArea.viewToModel(p));
//                }
//            }
//        }
    
//    @Override
//    public Point inText(int cx, int cy){
//        Rectangle bounds = this.jTextArea.getBounds();
//        if ((cx>bounds.x-this.xl)&&(cx<bounds.x -this.xl +bounds.width)&&
//            (cy>bounds.y -this.yt)&&(cy<bounds.y -this.yt +bounds.height)){
//            return new Point(cx-bounds.x+this.xl, cy-bounds.y+this.yt);
//            }
//        return null;
//        }

//    @Override
//    public void mousePressed(MouseEvent e) {
//        int cx =e.getX();
//        int cy =e.getY();        
//        Point p =this.inText(cx, cy);
//        if (p!=null){
//            this.jTextArea.setCaretPosition(
//            this.jTextArea.viewToModel(p));
//            }        
//        if (this.isActive){
//            this.prevX =cx;
//            this.prevY =cy;
//            return;
//            }
//        if (this.contem(new Point(this.xl +cx, this.yt +cy))){
//            this.mPannel.activate(this);
//            this.activate();
//            this.mPannel.repaint(this.xl-this.r,
//                this.yt-this.r, this.w+2*r, this.h+2*r);
//            this.prevX =cx;
//            this.prevY =cy;            
//            }
//    }

//    @Override
//    public void mouseReleased(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//    }
//
//    @Override
//    public String toFile() {
//        String ans = "elips;";
//        ans +=this.id +";";
//        Color c = this.jTextArea.getBackground();
//        ans +=c.getRed() +";";
//        ans +=c.getGreen() +";";
//        ans +=c.getBlue() +";";
//        c =this.jTextArea.getForeground();
//        ans +=c.getRed() +";";
//        ans +=c.getGreen() +";";
//        ans +=c.getBlue() +";";
//        String text = this.jTextArea.getValue();
//        ans +=text.length() +";" +text +";";
//        ans +=this.xl +";" +this.yt +";";
//        ans +=this.w +";" +this.h +".\r\n";
//        return ans;
//    }

//    @Override
//    public Point getCener() {
//        return new Point(
//            (this.f1.x +this.f2.x)/2,
//            (this.f1.y +this.f2.y)/2);
//    }

    @Override
    public Point getToCenter(Point p) {
        if (this.contem(p)){
            return null; }
        //Point cCenter = this.getCener();
        Point cCenter =new Point(this.xl +this.w/2, this.yt+this.h/2);
        double angle=
                Math.atan(w*Math.tan(Math.atan2(cCenter.y - p.y, cCenter.x - p.x))/h);
        float dx =((float)(w*Math.cos(angle))/2);
        //double dy =((double)(h*Math.sin(angle))/2);
        float dy =((float)(h*Math.sin(angle))/2);
        Point a1 =new Point(
            Math.round(cCenter.x +dx),
            Math.round(cCenter.y +dy) );
        Point a2 =new Point(
            Math.round(cCenter.x -dx),
            Math.round(cCenter.y -dy) );
        float d1 =(p.x -a1.x)*(p.x -a1.x)+
            (p.y -a1.y)*(p.y -a1.y);
        float d2 =(p.x -a2.x)*(p.x -a2.x)+
            (p.y -a2.y)*(p.y -a2.y);        
        if (d1>d2){ return a2; }
        return a1;
    }
    
//    @Override
//    public void add(Arrow arrow) {
//        this.arrows.add(arrow);
//    }    

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
//            //cb.setColorFill(BaseColor.BLACK);
//            //int xText = this.xl+this.w/4;
//            //int yText = this.yt +this.h/4;        
//            
//            int xText = (int) Math.round(this.xl +this.w/2 -this.w*Math.sqrt(2)/4);
//            int yText = (int) Math.round(this.yt +this.h/2 -this.h*Math.sqrt(2)/4);            
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
//                    xText, y,
//                    0);
//                y -= fontMetrics.getHeight();
//                }            
//        
////            cb.showTextAligned(
////                Element.ALIGN_LEFT,
////                this.jTextArea.getText(),
////                    xText,
////                mPannel.getHeight() -yText-ascent,
////                0);        
//
//            cb.endText();         
//        } catch (DocumentException ex) {
//            Logger.getLogger(Losango.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Losango.class.getName()).log(Level.SEVERE, null, ex);
//        }              
//        
//    }

//    @Override
//    public void drawDoc(PdfContentByte cb, MPannel mPannel) {
//            if (!this.isTransp()){
//                float[] ColorComp = new float[3];
//                ColorComp =this.backColor.getRGBColorComponents(ColorComp);
//                cb.setRGBColorFillF(ColorComp[0], ColorComp[1], ColorComp[2]);
//                cb.ellipse(
//                    this.xl,
//                    mPannel.getHeight() -this.yt,
//                    this.xl +this.w,
//                    mPannel.getHeight() -this.yt -this.h);
//                cb.fill();
//                }
//            for(Arrow arrow: this.arrows){
//                if (arrow.atartsAt(this)){
//                    arrow.drawDoc(cb, mPannel);
//                    }
//                }            
//    }

//    @Override
//    public int getFSize() {
//        return this.jTextArea.getFont().getSize();
//    }
//
//    @Override
//    public void setCor(Color textCol, Color backCor) {
//        this.jTextArea.setBackground(backCor);
//        this.backColor = backCor;
//        this.jTextArea.setForeground(textCol);
//        this.mPannel.repaint(this.xl, this.yt, this.w, this.h);
//    }
//    
//    @Override
//    public String writeArrows() {
//        String ans = "";
//        for(Arrow arrow: this.arrows){
//            if (arrow.atartsAt(this)){
//                ans +=arrow.toFile(); }
//            }  
//        return ans;
//        }    
//
//    @Override
//    public int getId() {
//        return this.id; }
//
//    @Override
//    public boolean hasId(int fndId) {
//        return this.id ==fndId; }

    @Override
    public Rectangle getTxtPos(int x, int y, int w, int h) {
        int xText = (int) Math.round(this.xl +this.w/2 -this.w*Math.sqrt(2)/4);
        int yText = (int) Math.round(this.yt +this.h/2 -this.h*Math.sqrt(2)/4);
        int wText = (int) Math.round(this.w/Math.sqrt(2));
        int hText = (int) Math.round(this.h/Math.sqrt(2));
        return new Rectangle(xText, yText, wText, hText);
    }

    @Override
    public String name() {
        return "elips";
    }

    @Override
    public Point getVert(Point p) {
        if (p.x<this.xl){return this.getLeft(); }
        if (p.x>this.xl +this.w){return this.getRight(); }
        double toAns =
            this.yt +h/2 -((float)(h))/(2*w)*
                Math.sqrt(w*w -4*(p.x-xl -((float) w)/2)*
                                 (p.x-xl -((float) w)/2));
        if (toAns>p.y){
            return new Point(p.x, (int) Math.round(toAns));
            }
        toAns =
            this.yt +h/2 +((float)(h))/(2*w)*
                Math.sqrt(w*w -4*(p.x-xl -((float) w)/2)*
                                 (p.x-xl -((float) w)/2));
        if (toAns<p.y){
            return new Point(p.x, (int) Math.round(toAns));
            }
        return null;
    }

    @Override
    public Point getHoriz(Point p) {
        if (p.y<this.yt){return this.getTop(); }
        if (p.y>this.yt +this.h){return this.getBottom(); }
        double toAns =
            this.xl +w/2 -((float)(w))/(2*h)*
                Math.sqrt(h*h -4*(p.y-yt -((float) h)/2)*
                                 (p.y-yt -((float) h)/2));
        if (toAns>p.x){
            return new Point((int) Math.round(toAns), p.y);
            }
        toAns =
            this.xl +w/2 +((float)(w))/(2*h)*
                Math.sqrt(h*h -4*(p.y-yt -((float) h)/2)*
                                 (p.y-yt -((float) h)/2));
        if (toAns<p.x){
            return new Point((int) Math.round(toAns), p.y);
            }
        return null;
    }

    @Override
    public Point getClosest(Point p) {
        return this.getToCenter(p);
    }
    
}
