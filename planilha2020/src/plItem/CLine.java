/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.ColumnText;
//import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CLine extends JLabel
        implements IsLine, MouseListener{
    
    private static final long serialVersionUID = 1;
    FontMetrics fontMetrics;
    boolean isActive;
    MPannel mPannel;
    Font font;
    String fontFName;
    String valor;
    int w;
    int h;
    
    public CLine(String[] params, MPannel mPannel){
//0     1                        2  3                             4  5   6
//CLine;data/fonts/WeissRunD.ttf;29;SECRETARIA MUNICIPAL DE SAÚDE;15;195;60        
        super(params[3]);
        this.fontFName =params[1];
        this.font =mPannel.getFont(this.fontFName,
            Font.TRUETYPE_FONT, Font.PLAIN, Integer.valueOf(params[4]));
        this.setFont(font);
//        File file = new File(this.fontFName);
//        try {
//            try {
//                font = Font.createFont(Font.TRUETYPE_FONT, file);
//            } catch (FontFormatException ex) {
//                Logger.getLogger(CLine.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            font = font.deriveFont(Font.PLAIN, 12);
//            this.setFont(font);
//        } catch (IOException ex) {
//            Logger.getLogger(CLine.class.getName()).log(Level.SEVERE, null, ex);
//        }
        this.mPannel =mPannel;
        this.valor =params[3];
        fontMetrics = this.getFontMetrics(font);
        w = fontMetrics.stringWidth(this.valor);
        h =fontMetrics.getHeight();
        //System.out.println("h = "+h);
        int x =Integer.valueOf(params[5]);
        int y =Integer.valueOf(params[6]);
        this.setBounds(x, y, w, h);
        //System.out.println(h);
        this.addMouseListener(this);         
        }
    
    public CLine(String valor, MPannel mPannel){
        super(valor);
        this.fontFName ="data/fonts/WeissRunD.ttf";
        File file = new File(this.fontFName);
        try {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, file);
            } catch (FontFormatException ex) {
                Logger.getLogger(CLine.class.getName()).log(Level.SEVERE, null, ex);
            }
            font = font.deriveFont(Font.PLAIN, 12);
            //System.out.println(font);
            this.setFont(font);
            //FontMetrics fontMetrics1 = this.getFontMetrics(font);
            //fontMetrics1.s
        } catch (IOException ex) {
            Logger.getLogger(CLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mPannel =mPannel;
        this.valor =valor;
        fontMetrics = this.getFontMetrics(this.getFont());
        w = fontMetrics.stringWidth(this.valor);
        h =fontMetrics.getHeight();
        //System.out.println(h);
        this.addMouseListener(this); 
        }
    
    @Override
    public void toPannel(MPannel mPannel, int y){
        int aw =mPannel.getAWidth();
        int x =aw/2 -this.w/2+mPannel.getBl();
        this.setBounds(x , y, w, h);
        mPannel.add(this);
        }
    
    @Override
    public int getH(){
        return this.h;
        }

//    @Override
//    public void toDoc(PdfContentByte cb, MPannel mPannel, int y)
//            throws IOException, DocumentException {
//        int aw =mPannel.getAWidth();
//        int x =aw/2 -this.w/2 +mPannel.getBl();
//        com.itextpdf.text.Font f = FontFactory.getFont("Weiss", BaseFont.IDENTITY_H, 
//            BaseFont.EMBEDDED, 12); //10 is the size        
//        int sWidth = f.getBaseFont().getWidth(valor);
//        Phrase p =new Phrase(this.valor, f);
//        ColumnText ct = new ColumnText(cb);
//        ct.setSimpleColumn(
//            x,
//            mPannel.getHeight()-y +this.fontMetrics.getDescent(),
//            x+sWidth,
//            mPannel.getHeight()-y-2*this.fontMetrics.getHeight()+this.fontMetrics.getDescent());
//        Paragraph pz = new Paragraph(p);
//        ct.addElement(pz);
//        ct.go();        
//        }

    @Override
    public void draw(Graphics g, MPannel mPannel, int y) { }
    
    @Override
    public void draw(Graphics g, MPannel mPannel) {    }        

//    @Override
//    public void drawDoc(PdfContentByte cb, MPannel mPannel, int y)
//        throws IOException, DocumentException { }

    @Override
    public void activate() {
        this.isActive =true;
        if (this.mPannel.activate(this)){
            this.setBorder(new LineBorder(Color.YELLOW, 4));
            }        
    }

    @Override
    public void desActivate() {
        this.isActive =false;
        this.setBorder(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //System.out.println("Edit0");
        if (me.getButton()==1){
            this.activate(); }
        if ((me.getButton()==3)&&(this.isActive)){
            //System.out.println("Edit");
            JPopupMenu ans =new JPopupMenu();
            JMenuItem editItem = new JMenuItem("Edit");
            editItem.addActionListener(new ClineEdt(this));
            ans.add(editItem);
            ans.show(me.getComponent(), me.getX(), me.getY());
            return;
        }        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

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
    public String toFile() {
        String ans ="CLine;";
        ans += this.fontFName +";";
        ans += this.valor.length() +";" +this.valor +";";
        ans += this.fontMetrics.getFont().getSize() +";";
        Rectangle border = this.getBounds();
        ans += border.x +";";
        ans += border.y +".\r\n";
        return ans;
    }
    
}
