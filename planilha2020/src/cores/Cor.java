package cores;

import java.awt.Color;

/**
 *
 * @author Makar Plakhotnyk
 */
public class Cor {
    
    private final Color color, complem;
    private final CGroup parent;
    int red, green, blue;
    
    public Cor(int red, int green, int blue, CGroup parent){
        this.color =new Color(red, green, blue);
        this.complem =new Color(255-red, 255-green, 255-blue);
        this.red =red;
        this.green =green;
        this.blue =blue;
        this.parent =parent;
        }
    
    public Color getColor(){
        return this.color; }
    
    public Color getComplem(){  
        return this.complem;
        }

    String getName() {
        return this.red +"-" +this.green +"-" +this.blue;
    }

    boolean hasRGB(int[] rgb) {
        return (rgb[0]==this.red)&&
               (rgb[1]==this.green)&&
               (rgb[2]==this.blue);
    }
    
    
}
