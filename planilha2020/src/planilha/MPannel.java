/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfWriter;
import cores.CGroup;
import cores.CGroups;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import fajly.FReader0;
import plItem.ALine;
import plItem.ALines;
import arrow.ArLabel;
import arrow.Arrow;
import fajly.ReadErr;
import javax.swing.JOptionPane;
import plItem.CLine;
import plItem.IsDItem;
import plItem.IsLine;
import plItem.IsPlItem;
import plItem.Malunok;
import plItem.PFont;
import plItem.PTable;
import plItem.TextObject;
import plItem.HasMitky0;

/**
 *
 * @author Makar Plakhotnyk
 */
public class MPannel extends JPanel implements MouseListener
    {
    private static final long serialVersionUID = 1;
    private final LinkedList<IsLine> lines;
    private final LinkedList<Malunok> malunky;
    private final LinkedList<TextObject> elipses;
    private final LinkedList<Arrow> arrows;
    private final CGroups cGroups;
    
    
    public int w,h;
    public int bt, bb, bl, br;
    private IsPlItem isPlItem;
    public final String fName;
    private final LinkedList<PFont> fonts;
    private IsDItem dItem;
    private int maxId;
    private final Glowny glowny;

    public MPannel(String dirName, String fName, Glowny glowny){
        //this.dirName =dirName;
        this.fName =fName;
        this.glowny =glowny;
        String property = System.getProperty("user.dir");
        //System.out.println(property);
        property =property +"\\..\\data\\cores.html";
        this.cGroups = new CGroups(property, glowny);
        if (!this.cGroups.fileFound()){
            JOptionPane.showMessageDialog(null, "Running is cancelled", "Tchau :(",
                JOptionPane.ERROR_MESSAGE);
            throw new ReadErr("");
            }
        this.cGroups.init();
        this.maxId =0;
        w =595;
        h =842;
        
        this.bt =45;
        this.bb =45;
        this.bl=45;
        this.br =45;
        
        this.isPlItem =null;
        this.fonts =new LinkedList<>();
        this.lines =new LinkedList<>();
        this.malunky =new LinkedList<>();
        //this.mLines =new LinkedList<>();
        this.elipses =new LinkedList<>();
        this.arrows =new LinkedList<>();
        
        this.dItem =null;
        
        Dimension a4 = new Dimension(w, h);
        //2480, 3508
        setBackground(java.awt.Color.white);
        this.setLayout(null);

        int y =this.bt;
        for(IsLine c:this.lines){
            c.toPannel(this, y);
            y +=c.getH();
            }
        this.setPreferredSize(a4);
        this.addMouseListener(this);
        }
    
    public void init(){
        }
    
    public void setDItem(IsDItem dItem){
        this.dItem =dItem;
        }
    
    public void append(CLine сLine){
        this.lines.add(сLine);
        this.add(сLine);
        }
    
    public void append(PTable pTable){
        this.lines.add(pTable);
        int y =pTable.getPos().y;
        pTable.toPannel(this, y);
        } 
    
    public void append(ALine aLine) {
        this.lines.add(aLine);
        this.add(aLine);  
    }    

    public void append(ALines aLines) {
        this.lines.add(aLines);
        int y =aLines.getPos().y;
        aLines.toPannel(this, y);
        
    }    
    
    public void append(Malunok malunok) {
        int curId = malunok.getId();
        if (curId>this.maxId){ this.maxId =curId; }
        this.malunky.add(malunok);
        this.add(malunok);
    }    
    
    public void append(TextObject hasMitky) {
        int curId = hasMitky.getId();
        if (curId>this.maxId){ this.maxId =curId; }        
        this.elipses.add(hasMitky);
    }    
    
    public void read(){
//        System.out.println(this.glowny.getCurDir());
//        System.out.println(this.glowny.getCurDir() +"\\"
//            +this.fName +".txt");
        FReader0 rderLocOrth = new FReader0(this.glowny.getCurDir() +"\\"
            +this.fName +".txt", this);
        rderLocOrth.readFile();  
        }

    
    /** get Area Width
     *
     * @return
     */
    public int getAWidth(){
        return this.w -this.bl -this.br;
        }
    
    public int getBt(){
        return this.bt;
        }
    
    public int getW(){return this.w;
        }
    
//    public void savePdf() {
//        //PdfCreator.create("./data/arvore.pdf", this);
//        FontFactory.register("data/fonts/WeissRunD.ttf", "Weiss");
//
//        //String fName ="./data/plan2.pdf";
//        
//        Rectangle rect =
//                new Rectangle(
//                    this.getWidth(), this.getHeight());
//        Document document = new Document(rect, 0, 0, 0, 0);
//        try{
//	PdfWriter writer =
//            PdfWriter.getInstance(
//                document,
//                new FileOutputStream(this.dirName +this.fName +".pdf"));
//	document.open();
//        PdfContentByte cb = writer.getDirectContent();
//        int y =this.bt;
//        for(IsLine c:this.lines){
//            c.toDoc(cb, this, y);
//            y +=c.getH();
//            }
//        for(HasMitky hasMitky: this.elipses){
//            hasMitky.drawDoc(cb, this);
//            }
//        y =this.bt;
//        for(IsLine c:this.lines){
//            c.drawDoc(cb, this, y);
//            y +=c.getH();
//            }        
//        for(Malunok malunok: this.malunky){
//            malunok.toDoc(document, this); }        
//        for(HasMitky hasMitky: this.elipses){
//            hasMitky.toDoc(cb, this);
//            }        
//	document.close();                    
//        } catch (DocumentException | IOException e)
//	        {
//	            System.out.println(e);
//	        }        
//        
//        
//    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(this.bl-1, this.bt-1, this.w -this.br+1, this.bt-1);
        g.drawLine(this.bl-1, this.bt-1, this.bl-1, this.h -this.bb);
        g.drawLine(
            this.bl-1, this.h -this.bb,
            this.w -this.br+1, this.h -this.bb);
        g.drawLine(
            this.w -this.br+1, this.h -this.bb,
            this.w -this.br+1, this.bt-1);
        int y=this.bt;
        for(IsLine c:this.lines){
            //c.draw(g, this, y);
            c.draw(g, this);
            y +=c.getH();
            }
        g.setColor(new Color(100, 149, 237));
        for(HasMitky0 elips:this.elipses){
            elips.draw(g); }
        for(Arrow lineArrow:this.arrows){
            lineArrow.draw(g); }
        g.setColor(Color.black);
        }    

    public int getBl() {
        return this.bl;
    }

    public int getbR() {
        return this.br;
    }

    /**
     *
     * @param aThis
     * @return is activated a new object
     */
    public boolean activate(IsPlItem aThis) {
        if (this.isPlItem!=aThis){
            if (this.isPlItem!=null){
                this.isPlItem.desActivate(); }
            this.isPlItem =aThis;
            return true;
            }
        return false;
    }
    
    public void desActivate(){
        if (this.isPlItem!=null){
            this.isPlItem.desActivate();
            this.isPlItem =null;
            }            
        }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.desActivate();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.desActivate();
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

    public void toFile() {
        
        try {
            FileWriter writer = new FileWriter(this.glowny.getCurDir() +"\\"
            +this.fName +".txt", false);
            for(IsLine isLine: this.lines){
                String txt =isLine.toFile();
                writer.write(txt); }
            for(Malunok malunok: this.malunky){
                String txt =malunok.toFile();
                writer.write(txt); }
            for(HasMitky0 elips: this.elipses){
                String txt =elips.toFile();
                writer.write(txt); }
            for(HasMitky0 elips: this.elipses){
                String txt =elips.writeArrows();
                writer.write(txt); }            

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();        
        }            
    }

    public Font getFont(String fontFName, int fontType, int fontStyle,
            int fontSize) {
        for(PFont pFont:this.fonts){
            if (pFont.hasName(fontFName)){
                return 
                    pFont.getFont(fontStyle, fontSize);
                }
            }
        //String property = System.getProperty("user.dir");
        //fontFName =property +"/../" +fontFName;
        File file = new File(fontFName);
        Font ans = null;
        try {
            try {
                ans = Font.createFont(fontType, file);
                }
            catch (FontFormatException ex) {
                Logger.getLogger(CLine.class.getName()).log(Level.SEVERE, null, ex);
                }
            ans = ans.deriveFont(fontStyle, fontSize);
            }
        catch (IOException ex) {
            Logger.getLogger(MPannel.class.getName()).log(Level.SEVERE, null, ex);
            }
        this.fonts.add(new PFont(fontFName, ans));
        int size = this.fonts.size();
        //FontFactory.register(fontFName, fontFName);
        return ans;
    }
    
//    public BaseFont getBaseFont(String fontFName) throws
//            DocumentException, IOException{
//        int i=0;
//        while (i<this.fonts.size()){
//            PFont pFont = this.fonts.get(i);
//            if (pFont.hasName(fontFName)){
//                BaseFont ans = BaseFont.createFont(
//                        fontFName,
//                        BaseFont.IDENTITY_H, 
//                        BaseFont.EMBEDDED);
//                return ans;
//                }
//            i++;
//            }
//        return null;
//        }

    public void delDItem() {
        this.dItem =null; }

    public boolean hasDItem() {
        return (this.dItem !=null); }

    public void addArrow(IsDItem itemTo) {
        Arrow arrow = new Arrow(this.dItem, itemTo, this);
        this.dItem =null;
        this.arrows.add(arrow);
    }

    public LinkedList<CGroup> getCGroups() {
        return this.cGroups.getGroups();
    }

    public int getMaxId(){
        return this.maxId;
        }

    public IsDItem getDItem(String id) {
        int fndId =Integer.valueOf(id);
        for(Malunok malunok: this.malunky){
            if (malunok.hasId(fndId)){
                return malunok;
                }
            }
        for(HasMitky0 hasMitky: this.elipses){
            if (hasMitky.hasId(fndId)){
                return hasMitky;
                }
            }        
        return null;
        }

    public void append(Arrow arrow) {
        this.arrows.add(arrow);
    }

    public void add(LinkedList<ArLabel> arLabels) {
        for(ArLabel arLabel: arLabels){
            this.add(arLabel); }
    }

    public void remove(Arrow arrow) {
        this.arrows.remove(arrow);
    }

    public int[] getColor(Color backColor) {
        int rgb[] =new int[3];
        rgb[0] =backColor.getRed();
        rgb[1] =backColor.getGreen();
        rgb[2] =backColor.getBlue();
        return this.cGroups.getColor(rgb);
    }

    public String getCurDir() {
        return this.glowny.getCurDir();
    }
}
