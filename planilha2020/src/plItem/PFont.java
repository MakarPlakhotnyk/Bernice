/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import java.awt.Font;

/**
 *
 * @author Makar Plakhotnyk
 */
public class PFont {
    
    private final String fontFName;
    private final Font font;
    
    public PFont(String fontFName, Font font){
        this.fontFName =fontFName;
        this.font =font;
        }
    
    public boolean hasName(String fontFName){
        return this.fontFName.equals(fontFName);
        }
    
    public Font getFont(int fontStyle, int fontSize){
        return this.font.deriveFont(fontStyle, fontSize);
        }
    
}
