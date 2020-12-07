package plItem;

import java.awt.Color;
import java.awt.Font;
import planilha.Glowny;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class TOData {
    
    final int r;// Radii of Border Label
    //final Mitka[] mitkas;
    final int xl, yt, w, h;
    final int id;
    
    //LinkedList<Arrow> arrows;
    //boolean isActive;
    final Color backColor, textColor;
    //final String fontFName;
    final String fontFName;
    final Font font;
    final String txtValue;
    final boolean isTransp;

    public TOData(MPannel mPannel, int r,
        int xl, int yt, int w, int h,
        int id,
            Color backColor, Color textColor,
            String txtValue){
        this.r =r;
        this.xl =xl;
        this.yt =yt;
        this.w =w;
        this.h =h;
        this.id =id;
        this.backColor =backColor;
        this.textColor =textColor;
        int fontSize =14;
        this.fontFName ="data/fonts/times-new-roman/times-new-roman.ttf";
        this.font =mPannel.getFont(fontFName,
            Font.TRUETYPE_FONT, Font.PLAIN, fontSize); 
        this.txtValue =txtValue;
        this.isTransp =false;
        }
    
    public TOData(MPannel mPannel, String[] params){
        this.id =Integer.valueOf(params[1]);
        int curPar =1;
        //this.arrows = new LinkedList<>();

        curPar++;
        int compRed =Integer.valueOf(params[curPar]);
        curPar++;
        int compGreen =Integer.valueOf(params[curPar]);
        curPar++;
        int compBlue =Integer.valueOf(params[curPar]);
        this.backColor = new Color(compRed, compGreen, compBlue);
        
        curPar++;
        compRed =Integer.valueOf(params[curPar]);
        curPar++;
        compGreen =Integer.valueOf(params[curPar]);
        curPar++;
        compBlue =Integer.valueOf(params[curPar]);
        this.textColor =new Color(compRed, compGreen, compBlue);         
        
        curPar++;
        int valueLen = Integer.valueOf(params[curPar]);        
        curPar++;
        this.txtValue =params[curPar];
        curPar++;
        this.xl =Integer.valueOf(params[curPar]);
        curPar++;
        this.yt =Integer.valueOf(params[curPar]);
        curPar++;
        this.w =Integer.valueOf(params[curPar]);
        curPar++;
        this.h =Integer.valueOf(params[curPar]);
        
        curPar++;
        if (params[curPar].equals("+")){
            this.isTransp =true; }
        else{ this.isTransp =false; }        
        
        int fontSize =14;
        //String property = System.getProperty("user.dir");
        //System.out.println(property);
        
        //String dirName =property +"/../data/";
        String dirName =mPannel.getCurDir();
        //this.fontFName ="data/fonts/times-new-roman/times-new-roman.ttf";
        this.fontFName =dirName +"/fonts/times-new-roman/times-new-roman.ttf";
        this.font =mPannel.getFont(fontFName,
            Font.TRUETYPE_FONT, Font.PLAIN, fontSize);           
        
        this.r=3;
        }
    
    
}
