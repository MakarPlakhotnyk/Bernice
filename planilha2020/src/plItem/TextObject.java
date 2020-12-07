package plItem;

import arrow.Arrow;
//import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import planilha.Glowny;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public abstract class TextObject extends JLabel
    implements IsPlItem, IsDItem,
        MouseMotionListener,
        HasMitky0,
        MouseListener{

    private static final long serialVersionUID = 1;
    final TextArea jTextArea;
    int r;// Radii of Border Label
    final Mitka[] mitkas;
    int xl, yt, w, h;
    int id;
    MPannel mPannel;
    LinkedList<Arrow> arrows;
    boolean isActive;
    private boolean isTransp;
    Color backColor;
    int prevX, prevY;
    String fontFName;
    
    public TextObject(MPannel mPannel, TOData tOData){
        //System.out.println("TextObject, line 39: tOData.id = " +tOData.id );
        this.xl =tOData.xl;
        this.yt =tOData.yt;
        this.w =tOData.w;
        this.h =tOData.h;
        this.id =tOData.id;
        this.backColor =tOData.backColor;
        this.r =tOData.r;
        this.arrows = new LinkedList<>();
        this.id =tOData.id;
        this.isTransp =tOData.isTransp;
        this.mPannel =mPannel;
        
        this.isActive =false;
        
        this.fontFName =tOData.fontFName;
        this.jTextArea = new TextArea(2, 4, 2, 4);
        if (tOData.isTransp){
            this.jTextArea.setOpaque(false); }
        this.jTextArea.setFont(tOData.font);
        Rectangle rect = this.getTxtPos(
            tOData.xl, tOData.yt,
            tOData.w, tOData.h);
        jTextArea.setBounds(rect);
        super.setBounds(tOData.xl, tOData.yt, tOData.w, tOData.h);
        //this.backColor =new Color(204, 204, 255);
        jTextArea.setBackground(tOData.backColor);
        jTextArea.setValue(tOData.txtValue);
        this.jTextArea.setForeground(tOData.textColor);
        
        mPannel.add(this);
        mPannel.add(jTextArea);
        this.addMouseListener(this);   
        this.addMouseMotionListener(this);           
        
        this.prevX =-1;
        this.prevY =-1;
        this.mitkas = new Mitka[8];
        Mitka mitka =new Mitka(this.xl, this.yt, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(dx, dy, -dx, -dy); }
            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl-r, yt-r, 2*r, 2*r);
            }
            };
        this.mitkas[0] =mitka;
        mitka =new Mitka(this.xl, this.yt +this.h/2, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(dx, 0, -dx, 0); }

            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl-r, yt+h/2-r, 2*r, 2*r);
            }
            };        
        this.mitkas[1] =mitka;
        mitka =new Mitka(this.xl, this.yt +this.h, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(dx, 0, -dx, dy); }

            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl-r, yt+h-r, 2*r, 2*r);
            }
            };        
        this.mitkas[2] =mitka;        
        mitka =new Mitka(this.xl +this.w/2, this.yt +this.h, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(0, 0, 0, dy); }

            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl+w/2-r, yt+h-r, 2*r, 2*r);
            }
            };        
        this.mitkas[3] =mitka;    
        mitka =new Mitka(this.xl +this.w, this.yt +this.h, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(0, 0, dx, dy); }

            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl+w-r, yt+h-r, 2*r, 2*r);
            }
            };        
        this.mitkas[4] =mitka;    
        mitka =new Mitka(this.xl +this.w, this.yt +this.h/2, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(0, 0, dx, 0); }

            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl+w-r, yt+h/2-r, 2*r, 2*r);
            }
            };        
        this.mitkas[5] =mitka;    
        mitka =new Mitka(this.xl +this.w, this.yt, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(0, dy, dx, -dy); }

            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl+w-r, yt-r, 2*r, 2*r);
            }
            };        
        this.mitkas[6] =mitka;  
                mitka =new Mitka(this.xl +this.w/2, this.yt, this.r, this) {
            private static final long serialVersionUID = 1;
            @Override
            void resElipse(int dx, int dy) {
                this.hasMitky.reSize(0, dy, 0, -dy); }

            @Override
            public void setCoord(int xl, int yt, int w, int h) {
                this.setBounds(xl+w/2-r, yt-r, 2*r, 2*r);
            }
            };        
        this.mitkas[7] =mitka;
        }
    
    public TextObject(MPannel mPannel, String[] params){
        this(mPannel, new TOData(mPannel, params));
    }        
    
    public TextObject(int x, int y, int w, int h,
            MPannel mPannel, int id){
        this(mPannel, new TOData(
            mPannel, 3,
            x, y, y, h,
            id,
            new Color(204, 204, 255), Color.black,
            "New text"
            ));
        }     
    
    public abstract Rectangle getTxtPos(int x, int y, int w, int h);
    
    public abstract String name();
    
    @Override
    public final int getId() {
        return this.id; }
    
    @Override
    public final boolean hasId(int id) {
        return this.id ==id; }     
    
    @Override
    public final String toFile() {
        String ans = this.name()+";";
        ans +=this.id +";";
        Color c = this.jTextArea.getBackground();
        ans +=c.getRed() +";";
        ans +=c.getGreen() +";";
        ans +=c.getBlue() +";";
        c =this.jTextArea.getForeground();
        ans +=c.getRed() +";";
        ans +=c.getGreen() +";";
        ans +=c.getBlue() +";";        
        String text = this.jTextArea.getValue();
        ans +=text.length() +";" +text +";";
        ans +=this.xl +";" +this.yt +";";
        ans +=this.w +";" +this.h +";";
        if (this.isTransp){ ans += "+.\r\n"; }
        else{ ans += "-.\r\n"; }        
        return ans;
    }

    @Override
    public abstract Point getToCenter(Point p);

    @Override
    public abstract void reSize(int dx, int i, int i0, int i1);

    @Override
    public abstract void draw(Graphics g);

//    @Override
//    public abstract void toDoc(PdfContentByte cb, MPannel mPannel);
//
//    @Override
//    public abstract void drawDoc(PdfContentByte cb, MPannel mPannel);

    @Override
    public final void mousePressed(MouseEvent e) {
        int cx =e.getX();
        int cy =e.getY();
        Point p =this.inText(cx, cy);
        if (p!=null){
            this.jTextArea.setCaretPosition(
            this.jTextArea.viewToModel(p));
            }
        if (this.isActive){
            this.prevX =cx;
            this.prevY =cy;
            return;
            }
        if (this.contem(new Point(this.xl +cx, this.yt +cy))){
            this.mPannel.activate(this);
            this.activate();
            this.mPannel.repaint(this.xl-this.r,
                this.yt-this.r, this.w+2*r, this.h+2*r);
            this.prevX =cx;
            this.prevY =cy;
            }
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
    }

    @Override
    public final void mouseExited(MouseEvent e) {
    }

    @Override
    public final String writeArrows() {
        String ans = "";
        for(Arrow arrow: this.arrows){
            if (arrow.atartsAt(this)){
                ans +=arrow.toFile(); }
            }  
        return ans;
        }    

    @Override
    public final Point getCener() {
        return new Point(
            this.xl +this.w/2,
            this.yt +this.h/2);
    }    

    @Override
    public final int getFSize() {
        return this.jTextArea.getFont().getSize();
    }

    @Override
    public final void setCor(Color textCol, Color backCor) {
        this.jTextArea.setBackground(backCor);
        this.backColor = backCor;
        this.jTextArea.setForeground(textCol);
        this.mPannel.repaint(this.xl, this.yt, this.w, this.h);
    }    

    @Override
    public final void add(Arrow arrow) {
        this.arrows.add(arrow);
    }  

    @Override
    public final void unchMitky() {
        for(Mitka mitka:this.mitkas){
            mitka.unChanged();
            }
    }

    @Override
    public final void mouseDragged(MouseEvent e) {
        if (this.isActive==false){
            return; }
        int cx =e.getX();
        int cy =e.getY();        
        int dx =cx -this.prevX;
        int dy =cy -this.prevY;
        this.reSize(dx, dy, 0, 0);
    }

    @Override
    public final void mouseMoved(MouseEvent e) {
    }

    public abstract boolean contem(Point p);
    
    public final Point inText(int cx, int cy){
        Rectangle bounds = this.jTextArea.getBounds();
        if ((cx>bounds.x-this.xl)&&(cx<bounds.x -this.xl +bounds.width)&&
            (cy>bounds.y -this.yt)&&(cy<bounds.y -this.yt +bounds.height)){
            return new Point(cx-bounds.x+this.xl, cy-bounds.y+this.yt);
            }
        return null;
        }
    
    @Override
    public final void mouseClicked(MouseEvent e) {
        if ((e.getButton()==3)&&(this.isActive)){
            JPopupMenu ans =new JPopupMenu();
            JMenuItem editItem = new JMenuItem("Add Arrow");
            editItem.addActionListener(new AddArrow0(this.mPannel, this));
            ans.add(editItem);
            JMenuItem menuFormat = new JMenuItem("Format");
            menuFormat.addActionListener(new TextFormat(this.mPannel, this));
            ans.add(menuFormat);
            ans.show(e.getComponent(), e.getX(), e.getY());
            return;
            }         
        int cx =e.getX();
        int cy =e.getY();
        if (this.contem(new Point(this.xl +cx, this.yt +cy))){
            this.mPannel.activate(this);
            this.activate();
            this.mPannel.repaint(this.xl-this.r,
                this.yt-this.r, this.w+2*r, this.h+2*r);
            this.prevX =cx;
            this.prevY =cy;
            Point p =this.inText(cx, cy);
            if (p!=null){
                this.jTextArea.setCaretPosition(
                    this.jTextArea.viewToModel(p));
                }
            return;
            }
        }


    @Override
    public final void activate() {
        if (this.mPannel.hasDItem()){
            this.mPannel.addArrow(this); }
        else{
            this.mPannel.delDItem(); }
        this.isActive =true;
        for(Mitka mitka:this.mitkas){
            this.mPannel.add(mitka);
            }
        for(Arrow arrow: this.arrows){
            arrow.acivate();
            }         
        this.jTextArea.requestFocus();
    }
    
    @Override
    public final void desActivate() {
        for(Arrow arrow: this.arrows){
            arrow.desAcivate();
            }           
        this.isActive =false;
        for(Mitka mitka:this.mitkas){
            this.mPannel.remove(mitka);
            }
        this.mPannel.repaint(this.xl-this.r, this.yt-this.r,
            this.w+2*this.r, this.h+2*this.r);
    }

    @Override
    public final boolean isActive(){
        return this.isActive;
        }

    @Override
    public final void remove(Arrow arrow){
        this.arrows.remove(arrow);
        }

    @Override
    public final Point getTop() {
        return new Point(
            this.xl + this.w/2,
            this.yt);
    }

    @Override
    public final Point getLeft() {
        return new Point(
            this.xl,
            this.yt +this.h/2);
    }

    @Override
    public final Point getRight() {
        return new Point(
            this.xl +this.w,
            this.yt +this.h/2);
    }

    @Override
    public final Point getBottom() {
        return new Point(
            this.xl +this.w/2,
            this.yt +this.h); }
    
    @Override
    public abstract Point getVert(Point fCenter);
//
    @Override
    public abstract Point getHoriz(Point fCenter);

    public final boolean isTransp(){
        return this.isTransp; }
    
    public final void setTransp(boolean isTransp){
        this.isTransp =isTransp;
        this.jTextArea.setOpaque(!isTransp); }
    
    public Color getBackColor(){
        return this.backColor; }

    public Color getTextCor() {
        return this.jTextArea.getForeground(); }
    
    @Override
    public abstract Point getClosest(Point p);    
    
}
