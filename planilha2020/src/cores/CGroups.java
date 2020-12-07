package cores;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import fajly.Fajl;
import fajly.LDictErr;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import planilha.Glowny;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CGroups {
    
    private LinkedList<CGroup> cGroups;
    String fName;
    private boolean fileFound;
    
    public CGroups(String fName, Glowny glowny){
        this.cGroups= new LinkedList<>();
        this.fName =fName;
        this.fileFound =true;
        File f = new File(fName);
        if (!f.exists() || f.isDirectory()){
            this.fileFound =false;
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "File\n" + fName +"\ndoes not exists.\nFind Manually?",
                    "Warning", dialogButton);
                if(dialogResult == JOptionPane.NO_OPTION){
                    return; }
                if(dialogResult == JOptionPane.YES_OPTION){
                    String path;
                    path = System.getProperty("user.dir");
                    JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(new File(path));        
                    int returnVal = fc.showOpenDialog(glowny);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        this.fName =file.getAbsolutePath();
                        int i =this.fName.lastIndexOf("\\");
                        if (i>=0){
                            String dir =this.fName.substring(0, i);
                            glowny.setCurDir(dir);
                            }
                        this.fileFound =true;
                    }
                }
            }
        }
    
    public boolean fileFound(){
        return this.fileFound;
        }
    
    public void init(){
        
    //String fName ="./data/cores.html";
    Charset charset = Charset.defaultCharset();
    try {
        FileInputStream fstream = new FileInputStream(fName);
        BufferedReader br;
        br = new BufferedReader(
            new InputStreamReader(fstream, charset));
        String strLine =this.readNexLine(br);
        while (strLine != null){
            while ((strLine!=null)&&
                   (strLine.indexOf("<td>")==-1)&&
                   (strLine.indexOf("<h3>")==-1)){
                strLine =this.readNexLine(br);
                }
            if (strLine==null){
                break; }
            if (strLine.indexOf("<td>")>=0){
                int i=strLine.indexOf("<td>")+"<td>".length();
                int j=strLine.indexOf("</td>");
                String nome =strLine.substring(i, j);
                strLine =this.readNexLine(br);
                while((strLine!=null)&&
                        (strLine.indexOf("<td>")==-1)){
                    strLine =this.readNexLine(br); }
                if (strLine==null){ break; }
                i=strLine.indexOf("<td>") +"<td>".length();
                j=strLine.indexOf("</td>");                
                String valor =strLine.substring(i, j);
                //strLine =this.readNexLine(br);
                i=0;
                while ((strLine!=null)&&(i<2)){
                    while((strLine!=null)&&
                            (strLine.indexOf("<td>")==-1)){
                        strLine =this.readNexLine(br);
                        }
                    strLine =this.readNexLine(br);
                    i++;
                    }
                String[] valores = valor.split("-");
                int red =Integer.valueOf(valores[0]);
                int green =Integer.valueOf(valores[1]);
                int blue =Integer.valueOf(valores[2]);
                CGroup lastGroup = this.cGroups.getLast();
                lastGroup.add(new Cor(red, green, blue, lastGroup));
                strLine =this.readNexLine(br);
                continue;
                }
            if (strLine.indexOf("<h3>")>=0){
                int j=strLine.indexOf("<h3>");
                int k =strLine.indexOf("</h3>");
                String name = strLine.substring(j+"<h3>".length(), k);
                    this.cGroups.add(new CGroup(name));
                int i=0;
                while ((strLine!=null)&&(i<4)){
                    while((strLine!=null)&&
                            (strLine.indexOf("<td>")==-1)){
                        strLine =this.readNexLine(br);
                        }
                    strLine =this.readNexLine(br);
                    i++;
                    }
                strLine =this.readNexLine(br);
                continue;
                }
            strLine =this.readNexLine(br);
            }
        br.close(); } 
        catch (FileNotFoundException ex) {
            String toThrow = "File " + fName +" not found";
            throw new LDictErr(toThrow);}
        catch (IOException ex) {
            String toThrow = "Failed o close file " + fName +".";
            throw new LDictErr(toThrow);
            }    
    }        
        

public String readNexLine(BufferedReader br){
        String prev = "";
        try {
        prev = br.readLine();
        if (prev==null){
            return null; }
        while ((prev.isEmpty()==false)&&(prev.startsWith(" "))){
            prev = prev.substring(1); }
        if ((prev.isEmpty()==false)&&(prev.endsWith(">")==false)){
            prev += br.readLine(); }
        } catch (IOException ex) {
            Logger.getLogger(Fajl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prev;
    }  

    public LinkedList<CGroup> getGroups() {
        return this.cGroups;
    }

    public int[] getColor(int rgb[]) {
        for(int i=0; i<this.cGroups.size(); i++){
            CGroup cGroup =this.cGroups.get(i);
            int j=cGroup.getInd(rgb);
            if (j>=0){
                return new int[]{i, j};
                }
            }
        return null;
    }
    
}
