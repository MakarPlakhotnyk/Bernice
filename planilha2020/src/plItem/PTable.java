/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JLabel;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class PTable implements IsLine{
    
    MPannel mPannel;
    int width;
    int[] widths;
    LinkedList<PTLine> itens;
    LinkedList<PTCell> cells;
    LinkedList<PTCell> escolhido;
    int ncols;
    int h;
    private int xl;
    
//    public PTable(int ncols, int[] widths, MPannel mPannel){
//        }
    
    
    public PTable(String[] params, MPannel mPannel) {
        this.mPannel =mPannel;
//       xl  y   nc w1 w2 w3 w4   
//pTable;80;124;4; 65;90;90;144;0;1;2;3;4;5;6;7.
//0      1  2   3  4  5  6  7   8

        int toX =Integer.valueOf(params[1]);
        
        int y =Integer.valueOf(params[2]);
        this.ncols =Integer.valueOf(params[3]);
        int[] toWidh =new int[ncols];
        int k=4;
        this.width =0;
        for(int i=0; i<toWidh.length; i++){
            toWidh[i] =Integer.valueOf(params[k]);
            this.width += toWidh[i];
            k++;
            }
        PTable pTable =new PTable(ncols, toWidh, mPannel, toX);
        while(k<params.length){
            pTable.addCell(params[k]);
            k++;
            }
        this.widths =toWidh;
        this.itens =pTable.itens;
        this.cells =pTable.cells;
        this.escolhido =pTable.escolhido;
        pTable.cells.getFirst().setBounds(xl, y);
        this.h =pTable.h;
        this.xl =toX;
        int x0 =this.xl;
        //System.out.println(x0);
//        ans += pos.xl +";" +pos.y +";";
//        ans += this.widths.length +"";
//        for(int wdh: this.widths){
//            ans += ";" +wdh;
//            }
//        for(PTCell pTCell:this.cells){
//            ans += ";" +pTCell.toFile();
//            }
//        return ans +=".\n";

    }
    
    public PTable(int ncols, int[] widths, MPannel mPannel, int x){
        this.mPannel =mPannel;
        this.ncols =ncols;
        this.widths =widths;
        this.width =0;
        for(int i: widths){
            this.width += i;
            }
        this.h =0;
        this.itens =new LinkedList<>();
        this.cells =new LinkedList<>();
        this.escolhido =new LinkedList<>();
        this.xl =x;
        }


    public int getNCols(){
        return this.ncols;
        }
    
    public void reEscolha(PTCell pTCell){
        this.desActivate();
        this.activate();
        this.escolhido.add(pTCell);
        }
    
    public void addCell(String value) {
        if ((this.cells.size()==0) ||
            (this.itens.getLast().size()==this.ncols)){
            PTLine pTLine =new PTLine();
            int linha =0;
            int coumna =0;
            if (this.cells.size()>0){
                linha =this.itens.size();
                //coumna =this.itens.getLast().size();
                }
            PTCell ptCell = new PTCell(value, this, this.widths[0],
                linha,
                coumna);
            this.cells.add(ptCell);
            pTLine.add(ptCell);
            //l.add(ptCell);
            this.itens.add(pTLine);
            this.h += pTLine.getMaxMinH();
            //this.itens.add(l);
            return;
            }
        PTLine lastLine = this.itens.getLast();
        PTCell ptCell = new PTCell(value, this,
            this.widths[lastLine.size()],
            this.itens.size(),
            this.itens.getLast().size());
        int oldH =lastLine.getMaxMinH();
        this.cells.add(ptCell);
        if (lastLine.getMaxMinH()!=oldH){
            this.h = this.h -oldH +lastLine.getMaxMinH();
            }
        this.itens.getLast().add(ptCell);
    }    

    @Override
    public void toPannel(MPannel mPannel, int y) {
        for(PTLine line: this.itens){
            //int xPos=this.xl +mPannel.getBl();
            int xPos=this.xl;
            int curH = line.getMaxMinH();
            for(int i=0; i<line.size(); i++){
                PTCell pTCell =line.get(i);
                pTCell.setBounds(xPos, y);
                JLabel label = pTCell.getLabel();
                JLabel label2 =pTCell.getLabel2();
                mPannel.add(label);
                mPannel.add(label2);
                xPos +=this.widths[i];
                }
            y += line.getMaxMinH();
            }
        //System.out.println(this.xl);
        }

    public Point getPos(){
        PTCell fCell = this.cells.getFirst();
        Rectangle bounds = fCell.getBounds();
        //System.out.println("getPos. x = " +this.xl);
        return new Point(bounds.x, bounds.y);
        }
    
    @Override
    public void draw(Graphics g, MPannel mPannel, int y) {
        this.xl =this.cells.getFirst().getBounds().x -mPannel.getBl();
        //System.out.println("draw: x = " +xl);
        int xPos=this.xl +mPannel.getBl();
        g.drawLine(xPos, y,
                xPos, y+this.h);        
        this.width =0;
        for(int w: this.widths){
            xPos += w;
            this.width += w;
            g.drawLine(xPos, y,
                xPos, y+this.h);  
            }
        g.drawLine(this.xl +mPannel.getBl(),
            y,
            this.xl +mPannel.getBl() +this.width,
            y);                
        for(PTLine line: this.itens){
            y += line.h;
            g.drawLine(this.xl +mPannel.getBl(),
                y,
                this.xl +mPannel.getBl() +this.width,
                y);
            }
    }    
    
    @Override
    public void draw(Graphics g, MPannel mPannel) {
        int y =this.cells.getFirst().getBounds().y;
        this.draw(g, mPannel, y);
    }         
    
    @Override
    public int getH() {
        return this.h;
    }

//    @Override
//    public void toDoc(PdfContentByte cb, MPannel mPannel, int y)
//            throws IOException, DocumentException {
//        Point pos = this.getPos();
//        int y0 = pos.y;
//        PdfPTable table = new PdfPTable(this.ncols);
//        table.setTotalWidth(this.width);
//        table.setWidths(this.widths);
//        //PdfPCell pdfPCell=new PdfPCell(new Phrase("Cell 1"));
//        //pdfPCell.setRowspan(2);
//        //table.addCell(pdfPCell);
//        for(PTLine line: this.itens){
//            //System.out.println(line.size());
//            for(int i=0; i<line.size(); i++){
//                PTCell pTCell =line.get(i);
//                PdfPCell pdfPCell=new PdfPCell(
//                    new Phrase(pTCell.getValue()));
//                table.addCell(pdfPCell);
//                }
//            table.completeRow();
//            }
//        //System.out.println(this.xl);
//        table.writeSelectedRows(0, 2, this.xl +mPannel.getBl(),
//            //mPannel.getHeight()//-y//-mPannel.getBt()
//            mPannel.getHeight()-y0//-mPannel.getBt()
//                ,
//            cb);        
//    }

//    @Override
//    public void drawDoc(PdfContentByte cb, MPannel mPannel, int y) throws IOException, DocumentException {
//    }

    @Override
    public void activate() {
        this.mPannel.activate(this);
    }

    @Override
    public void desActivate() {
        for(PTCell p: this.escolhido){
            p.desActivate(); }
        this.escolhido.clear();
        //this.mPannel.desActivate();
        }

    void addEscolha(PTCell pTCell) {
        this.escolhido.add(pTCell);
    }

    boolean incCWidth(int coumna, int dW) {
        // current width
        int newWC =this.widths[coumna] +dW;
        
        // width next
        int newWN =this.widths[coumna+1] -dW;
        
        if ((newWC<5)||(newWN<5)){
            return false; }
        
        for(PTLine line: this.itens){
            line.incCWidth(coumna, dW); }
        this.widths[coumna] =newWC;
        this.widths[coumna+1] =newWN;
        return true;
    }

//    void printWidthes() {
//        for(int i: this.widths){
//            System.out.print(i +" ");
//            }
//        System.out.println("");
//    }

    /**
     * Increase left border
     * @param dX
     * @return
     */

    public boolean incLB(int dX) {
        //System.out.println("incLB x = " + this.xl);
        this.xl =this.cells.getFirst().getBounds().x;
                
        int newX =this.xl +dX;
        boolean ans =true;
        //System.out.println(newX);
        if (newX<this.mPannel.getBl()){
            dX =this.mPannel.getBl()-this.xl;
            newX =this.mPannel.getBl();
            ans =false; }
        int renX =this.xl -Math.abs(dX);
        int rewW =this.widths[0] +2*Math.abs(dX)+1;
        int newW =this.widths[0] -dX;
        if ((newW<5) || (newX<this.mPannel.getBl())){
            return false; }                
        for(PTLine line: this.itens){
            line.incLB(dX); }        
        this.widths[0] =newW;
        //this.width -= dX;
        this.width =0;
        for(int w: this.widths){
            this.width += w;
            }        
        this.xl =newX;
        //System.out.println("incLB now x = " + this.xl);
        int renY = this.itens.get(0).get(0).getLocation().y;
        this.mPannel.repaint(renX, renY-1, rewW, this.h+2);
        return ans;
        
    }

    /**
     * Increase right border
     * @param dX
     * @return
     */
    public boolean incRB(int dX) {
        int newRB =this.xl +this.width +dX;
//        int maxRB =this.mPannel.getW()
//            -this.mPannel.getbR()-this.mPannel.getBl();
        int maxRB =this.mPannel.getW()
            -this.mPannel.getbR();
        boolean ans =true;
        if (newRB>maxRB){
            dX =dX +maxRB -newRB;
            ans =false; }
        int renX =this.xl +this.width
            -this.widths[this.widths.length-1];
        int rewW =this.widths[this.widths.length-1] +2*Math.abs(dX);
        int renY = this.itens.get(0).get(0).getLocation().y;
        for(PTLine line: this.itens){
            line.incRB(dX); } 
        this.widths[this.widths.length-1] += dX;
        this.width += dX;
        this.mPannel.repaint(renX, renY-1, 2*rewW, this.h+2);
        return ans;
    }

    @Override
    public String toFile() {
        String ans ="pTable;";
        Point pos = this.getPos();
        ans += pos.x +";" +pos.y +";";
        ans += this.widths.length +"";
        for(int wdh: this.widths){
            ans += ";" +wdh;
            }
        for(PTCell pTCell:this.cells){
            ans += ";" +pTCell.toFile();
            }
        return ans +=".\r\n";
    }

}
