package plItem;

import java.awt.Insets;
import javax.swing.JTextArea;

/**
 *
 * @author Makar Plakhotnyk
 */
public class TextArea extends JTextArea {
    
    private static final long serialVersionUID = 1;
    private int insTop, insLeft, insBottom, insRight;
    
    public TextArea(int insTop, int insLeft,
            int insBottom, int insRight){
        this.insTop =insTop;
        this.insLeft =insLeft;
        this.insBottom =insBottom;
        this.insRight =insRight;
        }
    
    public void setMargens(int insTop, int insLeft,
            int insBottom, int insRight){
        this.insTop =insTop;
        this.insLeft =insLeft;
        this.insBottom =insBottom;
        this.insRight =insRight;
        }

    void setValue(String toValue) {
        char[] arr = toValue.toCharArray();
        toValue ="";
        int i=0;
        while(i<arr.length-1){
            if ((arr[i]=='\\')&&(arr[i+1]=='n')){
                toValue += '\n';
                i++; }
            else{ toValue += arr[i]; }
            i++;
            }
        if (i<arr.length){
            toValue += arr[i]; }        
        this.setText(toValue);
    }
    
    String getValue(){
        String text = this.getText();
        char[] arr = text.toCharArray();
        text ="";
        for(char cc: arr){
            if (cc=='\n'){
                text +='\\' +"n"; }
            else{ text += cc; }
            }
        return text;
        }
    
    @Override
    public Insets getInsets(){
        // Insets() args are top, left, bottom, right
        //return new Insets(2,4,2,4);
        return new Insets(this.insTop, this.insLeft,
            this.insBottom, this.insRight);
    }        
    
}
