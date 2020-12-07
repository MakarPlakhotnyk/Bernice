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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class ALines //extends JLabel
        implements IsLine{
    
    ULabel[] valor;
    int h;
    FontMetrics fontMetrics;
    
    public ALines(MPannel mPannel, String[] params){
//
//ALines;45;2;
//data/fonts/times-new-roman/times-new-roman.ttf; 14; Cartão SUS (CNS) n:;
//data/fonts/times-new-roman/times-new-roman.ttf; 14; C.P.F.n:
//

        int x =Integer.valueOf(params[1]);
        int y =Integer.valueOf(params[2]);
        int valLeng =Integer.valueOf(params[3]);
        this.valor =new ULabel[valLeng];
        int k=4;
        for(int i=0; i<valLeng; i++){
            this.valor[i] =
                new ULabel(params, mPannel, k);
            k =k+3;
            }
        int w=0;
        for(ULabel u:this.valor){
            w += u.getW(); }        
        int s =(mPannel.getW() -mPannel.getBl()
            -mPannel.getbR() -w)/this.valor.length;        
        for(ULabel u:this.valor){
            u.setPos(x, y);
            x = x+u.getW() +s;
            }
        this.fontMetrics = this.valor[0].getFontMetrics(this.valor[0].getFont());
        }
    
    public ALines(String[] valor, MPannel mPannel){
        //super(valor);
        this.valor =new ULabel[valor.length];
        for(int i=0; i<valor.length; i++){
            this.valor[i] =new ULabel(valor[i], mPannel);
            }
        int w=0;
        for(ULabel u:this.valor){
            w += u.getW(); }
        int s =(mPannel.getW() -mPannel.getBl()
            -mPannel.getbR() -w)/this.valor.length;
        int x=mPannel.getBl();
        for(ULabel u:this.valor){
            u.setX(x);
            x = x+u.getW() +s;
            }
        fontMetrics = this.valor[0].getFontMetrics(this.valor[0].getFont());
        this.h =fontMetrics.getHeight();        
        }
    
    public Point getPos() {
        
        Rectangle bounds = this.valor[0].getBounds();
        //System.out.println("Alines: getPOs: "+bounds.y);
        //System.out.println("getPos. x = " +this.xl);
        return new Point(bounds.x, bounds.y);        
    }    

    @Override
    public void toPannel(MPannel mPannel, int y) {
        //int aw =mPannel.getAWidth();
        //int x =aw/2 -this.w/2;
        int posW =mPannel.getBl();
        for(ULabel u:this.valor){
            u.setBounds(y);
            mPannel.add(u);
            }
        
    }

    @Override
    public int getH() {
        return this.h; }

//    @Override
//    public void toDoc(PdfContentByte cb, MPannel mPannel, int y)
//            throws IOException, DocumentException {
//        cb.beginText();
//        
//        int aw =mPannel.getAWidth();
//        //int x =mPannel.getBl();
//        //int textSize = this.getFont().getSize();
//        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
//	    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//        cb.setFontAndSize(bf, 12);
//        for(ULabel u:this.valor){
//            //u.setBounds(y);
//            float w = bf.getWidthPoint(u.getText(), 12);
//            //float xFrom =x+w;
//            cb.showTextAligned(Element.ALIGN_LEFT, u.getText(), 
//                u.getX(),
//                mPannel.getHeight() -y-12,
//                0);              
//            //mPannel.add(u);
//            }        
//        cb.endText();  
//        }

    @Override
    public void draw(Graphics g, MPannel mPannel, int y) {
        int yLine =y+this.fontMetrics.getAscent();
        for(int i=0; i<this.valor.length-1; i++){
            ULabel u=this.valor[i];
            int w =this.valor[i].getW();
            int xFrom =u.getX() +u.getW();
            int xTo =this.valor[i+1].getX();
            g.drawLine(
                xFrom, yLine,
                xTo, yLine);        
            }
        int i=this.valor.length-1;
        ULabel u=this.valor[i];
        int w =this.valor[i].getW();
        g.drawLine(
            u.getX() +u.getW(), yLine,
            mPannel.getW()-mPannel.getbR(), yLine);

    }
    
    @Override
    public void draw(Graphics g, MPannel mPannel) {
        int y =this.valor[0].getBounds().y;
        this.draw(g, mPannel, y);
    }     

//    @Override
//    public void drawDoc(PdfContentByte cb, MPannel mPannel, int y) throws IOException, DocumentException {
//        
//        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
//	    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//        CMYKColor magentaColor = new CMYKColor(1.f, 0.f, 0.f, 1.f);
//        cb.setColorStroke(magentaColor);  
//        float x =this.valor[0].getX();
//        int textSize = this.fontMetrics.getHeight();
//        for(int i=0; i<this.valor.length-1; i++){
//            ULabel u=this.valor[i];
//            float w = bf.getWidthPoint(u.getText(), 12);
//            float lWidth =0.75f;
//            cb.setLineWidth(lWidth);
//            float yCoord =mPannel.getHeight()-y-this.fontMetrics.getAscent() -lWidth;            
//            float xFrom =x+w;
//            float xTo =this.valor[i+1].getX();
//            x =xTo;
//            cb.moveTo(xFrom, yCoord);
//            cb.lineTo(xTo, yCoord);
//            cb.closePathStroke();                 
//            }
//        int i=this.valor.length-1;
//        ULabel u=this.valor[i];
//        float w = bf.getWidthPoint(u.getText(), 12);
//        float lWidth =0.75f;
//        cb.setLineWidth(lWidth);
//        float yCoord =mPannel.getHeight()-y-this.fontMetrics.getAscent() -lWidth;            
//        float xFrom =x+w;
//        float xTo =mPannel.getW()-mPannel.getbR();
//        cb.moveTo(xFrom, yCoord);
//        cb.lineTo(xTo, yCoord);
//        cb.closePathStroke();         
//        }

    @Override
    public void activate() {
        
    }

    @Override
    public void desActivate() {
        
    }

    @Override
    public String toFile() {
        String ans ="aLines;";
        Rectangle bounds = this.valor[0].getBounds();
        ans +=  bounds.x +";" +bounds.y +";";
        ans += this.valor.length +"";
        for(ULabel uLabel: this.valor){
            ans += ";" +uLabel.toFile();
            }
        return ans +".\n";
    }


}
