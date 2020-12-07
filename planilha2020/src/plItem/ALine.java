/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.CMYKColor;
//import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class ALine extends JLabel implements IsLine {
    
    private static final long serialVersionUID = 1;
    String valor;
    FontMetrics fontMetrics;
    int h;
    private final String fontFName;
    
    public ALine(String[] params, MPannel mPannel){
//      val           font                                          size x  y
//aLine;Nome completo:;data/fonts/times-new-roman/times-new-roman.ttf;17;45;90        
//0     1              2                                              3  4  5
        super(params[1]);
        this.fontFName =params[2];
        Font font =mPannel.getFont(this.fontFName,
            Font.TRUETYPE_FONT, Font.PLAIN, Integer.valueOf(params[3]));
        this.setFont(font);
        this.valor =params[1];
        fontMetrics = this.getFontMetrics(this.getFont());
        this.h =this.getFontMetrics(this.getFont()).getHeight();        
        int w = fontMetrics.stringWidth(this.valor);
        int x =Integer.valueOf(params[4]);
        int y =Integer.valueOf(params[5]);
        this.setBounds(x, y, w, this.h);
//        String ans ="aLine;";
//        ans += this.valor +";";
//        ans += this.fontFName +";";
//        ans += this.h +";";
//        Rectangle r = this.getBounds();
//        ans += r.x +";" +r.y +".\n";
//        return ans;


        }
    
    public ALine(String valor){
        super(valor);
        this.fontFName ="data/fonts/times-new-roman/times-new-roman.ttf";
        
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
            Logger.getLogger(ALine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {        
            Logger.getLogger(ALine.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        this.valor =valor;
        fontMetrics = this.getFontMetrics(this.getFont());
        this.h =this.getFontMetrics(this.getFont()).getHeight();        
        }

    @Override
    public void toPannel(MPannel mPannel, int y) {
        //int aw =mPannel.getAWidth();
        //int x =aw/2 -this.w/2;
        FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        int w = fontMetrics.stringWidth(this.valor);
        this.setBounds(mPannel.getBl(), y, w, h);
        mPannel.add(this);
    }

    @Override
    public int getH() {
        return this.h; }

//    @Override
//    public void toDoc(PdfContentByte cb, MPannel mPannel, int y)
//            throws IOException, DocumentException {
//        cb.beginText();
//        int aw =mPannel.getAWidth();
//        int x =mPannel.getBl();
//        //int textSize = this.getFont().getSize();
//        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
//	    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//        cb.setFontAndSize(bf, 12);
//        cb.showTextAligned(Element.ALIGN_LEFT, this.valor, 
//            x,
//            mPannel.getHeight() -y-12,
//            0);        
//        cb.endText();  
//        }

    @Override
    public void draw(Graphics g, MPannel mPannel, int y) {
        FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        int w = mPannel.getBl()+fontMetrics.stringWidth(this.valor);
        int yLine =y+this.fontMetrics.getAscent();
        g.drawLine(w, yLine,
                mPannel.getWidth()-mPannel.getbR(), yLine);
    }
    
    @Override
    public void draw(Graphics g, MPannel mPannel) {
        int y =this.getBounds().y;
        this.draw(g, mPannel, y);
    }    

//    @Override
//    public void drawDoc(PdfContentByte cb, MPannel mPannel, int y)
//            throws IOException, DocumentException {
//        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
//	    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//        CMYKColor magentaColor = new CMYKColor(1.f, 0.f, 0.f, 1.f);
//        cb.setColorStroke(magentaColor);
//        float w = bf.getWidthPoint(valor, 12);
//        int x =mPannel.getBl();
//        int textSize = this.getFont().getSize();
//        float lWidth =0.75f;
//        cb.setLineWidth(lWidth);
//        float yCoord =mPannel.getHeight()-y-this.fontMetrics.getAscent() -lWidth;
//        float xFrom =x+w;
//        float xTo =mPannel.getW()-mPannel.getbR();
//        cb.moveTo(xFrom, yCoord);
//        cb.lineTo(xTo, yCoord);
//        cb.closePathStroke(); 
//    }

    @Override
    public void activate() {
        
    }

    @Override
    public void desActivate() {
        
    }

    @Override
    public String toFile() {
        String ans ="aLine;";
        ans += this.valor +";";
        ans += this.fontFName +";";
        ans += this.fontMetrics.getFont().getSize() +";";
        Rectangle r = this.getBounds();
        ans += r.x +";" +r.y +".\n";
        return ans;
    }
    
}
