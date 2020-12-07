/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fajly;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Makar Plakhotnyk
 */
public abstract class Fajl{

String fName;
//ChMowy chMovy;
Charset charset;    
//ArrayList<T> dict;
//private final String dictName;
    
public Fajl(String fName, //String dictName,
        Charset charset){
    this.fName = fName;
    //this.dictName = dictName;
    this.charset = charset;
    //this.dict = new ArrayList();
    }

public Fajl(String fName//, String dictName
        ){
    this.fName = fName;
    //this.dictName = dictName;
    //this.dict = new ArrayList();
    this.charset = Charset.defaultCharset();
    }

public void readFile(){
    try {
        FileInputStream fstream = new FileInputStream(fName);
        BufferedReader br;
        br = new BufferedReader(
            new InputStreamReader(fstream, charset));
        String strLine;
        while ((strLine = this.readNexLine(br)) != null){
            try{
            this.doLine(strLine);
            }catch(LDictErr lDictErr){
                lDictErr.println("in file " + this.fName);
                throw lDictErr;
                }
            } 
        fstream.close();
        br.close();}
        catch (FileNotFoundException ex) {
            String toThrow = "File " + this.fName +" not found";
            throw new LDictErr(toThrow);}
        catch (IOException ex) {
            String toThrow = "Failed o close file " + this.fName +".";
            throw new LDictErr(toThrow);
            }
    }

//public String readNexLine(BufferedReader br){
//    String ans = "";
//    try {
//        ans = br.readLine();
//    } catch (IOException ex) {
//        Logger.getLogger(Fajl.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return ans;
//    }

public String readNexLine(BufferedReader br){
    String prev = "";
    try {
    prev = br.readLine();
    if (prev==null){return null;}
    //prev = prev.replaceAll(" ", "");
    int i=prev.indexOf("%");
    if (i>=0){
        prev = prev.substring(0, i);}
        while (prev.endsWith(".")==false){
            String ans = br.readLine();
            i=ans.indexOf("%");
            if (i>=0){
                ans = ans.substring(0, i);}            
            //ans = ans.replaceAll(" ", "");
            prev = prev + ans;
            }
        } catch (IOException ex) {
            Logger.getLogger(Fajl.class.getName()).log(Level.SEVERE, null, ex);
        }
    prev = prev.substring(0, prev.length()-1);    
    return prev;
    }    

public abstract void doLine(String strLine);

}
