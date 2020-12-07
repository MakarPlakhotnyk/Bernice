/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fajly;

import java.nio.charset.Charset;
import plItem.ALine;
import plItem.ALines;
import arrow.Arrow;
import plItem.CLine;
import plItem.Elipse;
import plItem.Losango;
import plItem.MLine0;
import plItem.Malunok;
import plItem.PTable;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class FReader0 extends Fajl {
    
    MPannel mPannel;
    
public FReader0(String fName, //String dictName, 
        Charset charset, MPannel mPannel) {
    super(fName, //dictName,
            charset);
    this.mPannel =mPannel;
    }

@Override
public void readFile(){
    super.readFile();
    //return this.dict;
    }   

public FReader0(String fName, MPannel mPannel) {
    super(fName);
    this.mPannel =mPannel;
    }
    
   

    @Override
    public void doLine(String strLine) {
        String[] params = strLine.split(";");
        if (params[0].equals("CLine")){
            CLine cLine =new CLine(params, this.mPannel);
            this.mPannel.append(cLine);
            return;
            }
        if (params[0].equals("pTable")){
            PTable pTable =new PTable(params, this.mPannel);
            this.mPannel.append(pTable);
            return;
            }   
        if (params[0].equals("aLine")){
            ALine aLine =new ALine(params, this.mPannel);
            this.mPannel.append(aLine);
            return;
            }   
        if (params[0].equals("aLines")){
            ALines aLines =new ALines(this.mPannel, params);
            this.mPannel.append(aLines);
            return;
            }
        if (params[0].equals("mal")){
            Malunok malunok =new Malunok(this.mPannel, params);
            this.mPannel.append(malunok);
            return;
            } 
        if (params[0].equals("elips")){
            Elipse elips =new Elipse(this.mPannel, params);
            this.mPannel.append(elips);
            return;
            }    
        if (params[0].equals("losango")){
            Losango elips =new Losango(this.mPannel, params);
            this.mPannel.append(elips);
            return;
            }          
        if (params[0].equals("mLine")){
            MLine0 mLine =new MLine0(this.mPannel, params);
            this.mPannel.append(mLine);
            return;
            }
        if (params[0].equals("arrow")){
            try{
                Arrow arrow =new Arrow(this.mPannel, params);
                this.mPannel.append(arrow);
            }
            catch(ReadErr e){
                e.print(strLine);
                throw e;
                }
            return;
            }        
        System.out.println(strLine);
    }


    
}
