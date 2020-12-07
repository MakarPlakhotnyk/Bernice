package arrow;

//import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import fajly.ReadErr;
import plItem.IsDItem;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class Arrow {
    
    private static final Polygon ARROW_HEAD = new Polygon();
    
    static {
        ARROW_HEAD.addPoint(0, 0);
        ARROW_HEAD.addPoint(-5, -10);
        ARROW_HEAD.addPoint(5, -10);
    }    

        private Point pFrom, pTo;
        private Color color;
        private final int thickness;
        private final IsDItem itemFrom, itemTo;
        private MPannel mPannel;
        private LinkedList<ArLabel> arLabels;
        private boolean isTwoside;
        private int typeFrom, typeTo;

    public Arrow(MPannel mPannel, String[] params) {
        this.mPannel =mPannel;
        this.thickness =2;
        this.typeFrom =0;
        this.typeTo =0;
        int curPar =0;
        curPar++;
        this.itemFrom =mPannel.getDItem(params[curPar]);
        if (this.itemFrom==null){
            throw new ReadErr("Failed to find item with id '"
                +params[curPar] +"'");
            }
        curPar++;
        this.itemTo =mPannel.getDItem(params[curPar]);
        if (this.itemTo==null){
            throw new ReadErr("Failed to find item with id '"
                +params[curPar] +"'");
            }        

        this.arLabels =new LinkedList<>();
        curPar++;
        int nLables =Integer.valueOf(params[curPar]);
        for(int i=0; i<nLables; i++){
            curPar++;
            int cX =Integer.valueOf(params[curPar]);
            curPar++;
            int cY =Integer.valueOf(params[curPar]);        
            this.arLabels.add( new ArLabel(
                cX, cY, 3, this));
            }
        curPar++;
        int compRed =Integer.valueOf(params[curPar]);
        curPar++;
        int compGreen =Integer.valueOf(params[curPar]);
        curPar++;
        int compBlue =Integer.valueOf(params[curPar]);
        this.color = new Color(compRed, compGreen, compBlue);        
        
        curPar++;
        if (params[curPar].equals("+")){
            this.isTwoside =true; }
        else{ this.isTwoside =false; }
        
        curPar++;
        this.typeFrom =Integer.valueOf(params[curPar]);
        curPar++;
        this.typeTo =Integer.valueOf(params[curPar]);
        
        this.recalc();
        itemFrom.add(this);
        itemTo.add(this);
        this.mPannel.add(arLabels);        
        this.mPannel.repaint();
    }
        
    public Arrow(IsDItem itemFrom, IsDItem itemTo, MPannel mPannel){
        this.mPannel =mPannel;
        this.itemFrom =itemFrom;
        this.itemTo =itemTo;
 
        this.color =Color.blue;
        this.thickness =2;
        Point cFrom = itemFrom.getCener();
        Point cTo = itemTo.getCener();
        this.pFrom =itemFrom.getToCenter(cTo);
        this.pTo =itemTo.getToCenter(cFrom);
        this.arLabels =new LinkedList<>();
        this.arLabels.add(new ArLabel(
            (cFrom.x +cTo.x)/2,
            (cFrom.y +cTo.y)/2,
            3, this));
        itemFrom.add(this);
        itemTo.add(this);
        this.mPannel.add(arLabels);
        this.mPannel.repaint();
        }
    
    public void recalc(){
        switch(this.typeFrom){
            case 1:{
                Point fCenter = this.arLabels.getFirst().getCenter();
                this.pFrom =itemFrom.getVert(fCenter);
                break;
                }
            case 2:{
                Point fCenter = this.arLabels.getFirst().getCenter();
                this.pFrom =itemFrom.getHoriz(fCenter);
                break;
                }
            case 3:{// Top
                this.pFrom =itemFrom.getTop();
                break; }
            case 4:{// Left
                this.pFrom =itemFrom.getLeft();
                break; }
            case 5:{// Right
                this.pFrom =itemFrom.getRight();
                break; }
            case 6:{// Bottom
                this.pFrom =itemFrom.getBottom();
                break; }
            case 7:{// Bottom
                Point fCenter = this.arLabels.getFirst().getCenter();
                this.pFrom =itemFrom.getClosest(fCenter);
                break; }            
            default:{
                Point centerFrom = this.arLabels.getFirst().getCenter();
                this.pFrom =itemFrom.getToCenter(centerFrom);}
            }
        switch(this.typeTo){
            case 1:{
                Point lCenter = this.arLabels.getLast().getCenter();
                this.pTo =itemTo.getVert(lCenter);
                break;
                }
            case 2:{
                Point lCenter = this.arLabels.getLast().getCenter();
                this.pTo =itemTo.getHoriz(lCenter);                
                break;
                }
            case 3:{// Top
                this.pTo =itemTo.getTop();
                break; }
            case 4:{// Left
                this.pTo =itemTo.getLeft();
                break; }
            case 5:{// Right
                this.pTo =itemTo.getRight();
                break; }
            case 6:{// Bottom
                this.pTo =itemTo.getBottom();
                break; }
            case 7:{// Bottom
                Point lCenter = this.arLabels.getLast().getCenter();
                this.pTo =itemTo.getClosest(lCenter);
                break; }            
            default:{
                Point centerTo = this.arLabels.getLast().getCenter();
                this.pTo =itemTo.getToCenter(centerTo);
                break;}
            }
        this.mPannel.repaint();
        }
    
    public void draw(Graphics g) {
        if ((this.pFrom==null)||(this.pTo==null)){
            return; }
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.setStroke(new BasicStroke(thickness));
        Point a =pFrom;
        int i=0;
        if (this.isTwoside){
            ArLabel arLabel =this.arLabels.get(0);
            Point b =arLabel.getCenter();
            this.drawAB(g, b, a, this.color);
            i =1;
            a =b;
            }
        while(i< this.arLabels.size()){
            ArLabel arLabel =this.arLabels.get(i);
            Point b =arLabel.getCenter();
            g2.drawLine(a.x, a.y, b.x, b.y);
            a =b;
            i++;
            }
        this.drawAB(g, a, pTo, this.color);
        if (this.arLabels.getFirst().isAtive()){
            g2.setColor(Color.red);
            for(ArLabel arLabel:this.arLabels){
                Polygon p =new Polygon();
                Point c =arLabel.getCenter();
                p.addPoint(c.x-3, c.y-3);
                p.addPoint(c.x+3, c.y-3);
                p.addPoint(c.x+3, c.y+3);
                p.addPoint(c.x-3, c.y+3);
                g2.fill(p);
                }
            }
        }  
    
    private void drawAB(Graphics g, Point a, Point b, Color c) {
        if ((a==null)||(b==null)){
            return; }
        
        Graphics2D g2 = (Graphics2D) g;

            // Calcula o ângulo da seta.
            double angle = Math.atan2(b.y - a.y, b.x - a.x);

        g2.setColor(c);
        g2.setStroke(new BasicStroke(thickness));

            // Desenha a linha. Corta 10 pixels na ponta para a ponta não ficar grossa.
        g2.drawLine(a.x, a.y,
            (int) (b.x - 10 * Math.cos(angle)),
            (int) (b.y - 10 * Math.sin(angle)));

            // Obtém o AffineTransform original.
        AffineTransform tx1 = g2.getTransform();

            // Cria uma cópia do AffineTransform.
        AffineTransform tx2 = (AffineTransform) tx1.clone();

            // Translada e rotaciona o novo AffineTransform.
        tx2.translate(b.x, b.y);
        tx2.rotate(angle - Math.PI / 2);

            // Desenha a ponta com o AffineTransform transladado e rotacionado.
        g2.setTransform(tx2);
        g2.fill(ARROW_HEAD);

            // Restaura o AffineTransform original.
        g2.setTransform(tx1);        
        }

    public void acivate() {
        this.recalc();
        }

    public void desAcivate() {
        this.arLabels.getFirst().desAcivate(); }

//    public void drawDoc(PdfContentByte cb, MPannel mPannel) {
//        if ((this.pFrom!=null)&&(this.pTo!=null)){
//            int tov =1;
//            float[] ColorComp = new float[3];
//            ColorComp =this.color.getRGBColorComponents(ColorComp);
//            cb.setRGBColorFillF(ColorComp[0], ColorComp[1], ColorComp[2]);
//            Point a= pFrom;
//            int i=0;
//            if (this.isTwoside){
//                ArLabel arLabel = this.arLabels.get(i);
//                Point b = arLabel.getCenter();
//                float vACX =((float)(b.x -a.x))/
//                        (float) Math.sqrt(
//                        (b.y -a.y)*(b.y -a.y)
//                       +(b.x -a.x)*(b.x -a.x));
//                float vACY =((float)(b.y -a.y))/
//                    (float) Math.sqrt(
//                            (b.y -a.y)*(b.y -a.y)
//                       +(b.x -a.x)*(b.x -a.x));
//                cb.moveTo(a.x,
//                    mPannel.getHeight() -a.y);
//                cb.lineTo(a.x +10*vACX -5*vACY,
//                    mPannel.getHeight() -(a.y +10*vACY +5*vACX));
//                cb.lineTo(a.x +10*vACX +5*vACY,
//                    mPannel.getHeight() -(a.y +10*vACY -5*vACX));
//                cb.fill();
//                a.x += (10-2*tov)*vACX;
//                a.y += (10-2*tov)*vACY;
//                }
//            while(i<this.arLabels.size()){
//                ArLabel arLabel = this.arLabels.get(i);
//                Point b = arLabel.getCenter();
//                float vACX =((float)(b.x -a.x))/
//                        (float) Math.sqrt(
//                        (b.y -a.y)*(b.y -a.y)
//                       +(b.x -a.x)*(b.x -a.x));
//                float vACY =((float)(b.y -a.y))/
//                    (float) Math.sqrt(
//                            (b.y -a.y)*(b.y -a.y)
//                       +(b.x -a.x)*(b.x -a.x));
//                cb.moveTo(a.x-tov*vACY,
//                    mPannel.getHeight() -(a.y+tov*vACX));
//                cb.lineTo(b.x-tov*vACY,
//                    mPannel.getHeight() -(b.y+tov*vACX));
//                cb.lineTo(b.x+tov*vACY,
//                    mPannel.getHeight() -(b.y-tov*vACX));
//                cb.lineTo(a.x+tov*vACY,
//                    mPannel.getHeight() -(a.y-tov*vACX));
//                cb.fill();
//                cb.circle(b.x, mPannel.getHeight() -b.y, tov);
//                cb.fill();
//                a =b;
//                i++;
//                }
//            float vCbX =((float)(pTo.x -a.x))/
//                (float) Math.sqrt(
//                    (pTo.y -a.y)*(pTo.y -a.y)
//                   +(pTo.x -a.x)*(pTo.x -a.x));
//            float vCbY =((float)(pTo.y -a.y))/
//                (float) Math.sqrt(
//                    (pTo.y -a.y)*(pTo.y -a.y)
//                   +(pTo.x -a.x)*(pTo.x -a.x));
//            cb.moveTo(a.x-tov*vCbY,
//                mPannel.getHeight() -(a.y+tov*vCbX));
//            cb.lineTo(pTo.x -10*vCbX -tov*vCbY,
//                mPannel.getHeight() -(pTo.y -10*vCbY +tov*vCbX));
//            cb.lineTo(pTo.x -10*vCbX -5*vCbY,
//                mPannel.getHeight() -(pTo.y -10*vCbY +5*vCbX));
//            cb.lineTo(pTo.x,
//                mPannel.getHeight() -(pTo.y));            
//            cb.lineTo(pTo.x -10*vCbX +5*vCbY,
//                mPannel.getHeight() -(pTo.y -10*vCbY -5*vCbX));            
//            cb.lineTo(pTo.x -10*vCbX +tov*vCbY,
//                mPannel.getHeight() -(pTo.y -10*vCbY -tov*vCbX));
//            cb.lineTo(a.x+tov*vCbY,
//                mPannel.getHeight() -(a.y-tov*vCbX));
//            cb.fill();
//            }
//
//    }

    public boolean atartsAt(IsDItem hasMitky) {
        return this.itemFrom.equals(hasMitky);
    }

    public String toFile() {
        String ans ="arrow;";
        ans +=this.itemFrom.getId()+";";
        ans +=this.itemTo.getId()+";";
        ans += this.arLabels.size()+";";
        for(ArLabel arLabel:this.arLabels){
            Point c = arLabel.getCenter();
            ans += c.x +";" +c.y +";";
            }
        ans += this.color.getRed() +";";
        ans += this.color.getGreen() +";";
        ans += this.color.getBlue() +";";
        if (this.isTwoside){ ans += "+;"; }
        else{ ans += "-;"; }
        ans += this.typeFrom +";";
        ans += this.typeTo + "";
        ans += ".\r\n";
        return ans;
    }

    public void createAfter(ArLabel arLabel) {
        int i=0;
        while(i<this.arLabels.size()){
            if (this.arLabels.get(i)==arLabel){
                break;
                }
            i++;       
            }
        Point nextCener =null;
        if (i==this.arLabels.size()-1){
            nextCener =this.pTo;
            }
        else{
            nextCener =this.arLabels.get(i+1).getCenter();
            }
        Point c =arLabel.getCenter();
        ArLabel newArLabel = new ArLabel(
            (c.x+nextCener.x)/2,
            (c.y+nextCener.y)/2,
            3, this);
        this.mPannel.add(newArLabel);
        this.arLabels.add(i+1, newArLabel);
    }

    public boolean isChanging() {
        if (this.itemFrom.isActive()){
            return true;
            }
        return this.itemTo.isActive();
    }

    public void remove(ArLabel arLabel) {
        if (this.arLabels.size()>1){
            this.arLabels.remove(arLabel);
            this.mPannel.remove(arLabel);
            this.recalc();
            }
    }

    public MPannel getPannel() {
        return this.mPannel;
    }

    void setColor(Color textCol) {
        this.color =textCol;
    }
    
    public Color getColor(){
        return this.color;
        }

    void setTwoSides(boolean isTwoside) {
        this.isTwoside =isTwoside;
    }

    public boolean isTwoSides(){
        return this.isTwoside; }
    
    void remove() {
        for(ArLabel arLabel: this.arLabels){
            this.mPannel.remove(arLabel);
            }
        this.itemFrom.remove(this);
        this.itemTo.remove(this);
        this.mPannel.remove(this);
        this.mPannel.repaint();
    }

    void setTypes(int typeFrom, int typeTo) {
        this.typeFrom =typeFrom;
        this.typeTo =typeTo;
    }

    int getTypeFrom() {
        return this.typeFrom; }

    int getTypeTo() {
        return this.typeTo; }
    
}

