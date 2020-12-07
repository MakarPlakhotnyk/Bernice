/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Graphics;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public interface IsLine extends IsPlItem{

    public void toPannel(MPannel aThis, int y);
    public int getH();
//    public void toDoc(PdfContentByte cb, MPannel mPannel, int y)
//            throws IOException, DocumentException;
//    public void drawDoc(PdfContentByte cb, MPannel mPannel, int y)
//            throws IOException, DocumentException;    

    public void draw(Graphics g, MPannel mPannel, int y);
    public void draw(Graphics g, MPannel mPannel);
    @Override
    public void activate();
    @Override
    public void desActivate();

    @Override
    public String toFile();
    
}
