/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plItem;

import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author Makar Plakhotnyk
 */
public class PTLine {
    
    int maxMinH, h;
    LinkedList<PTCell> cells;
    
    public PTLine(){
        this.maxMinH=0;
        this.cells =new LinkedList<>();
        }

    int size() {
        return this.cells.size();
    }

    void add(PTCell ptCell) {
        this.cells.add(ptCell);
        int curMinH = ptCell.getMinH();
        if (maxMinH<curMinH){
            maxMinH =curMinH;
            h =maxMinH;
            }
    }

    int getMaxMinH(){
        return this.maxMinH;
        }
    
    PTCell get(int i) {
        return this.cells.get(i);
    }

    void incCWidth(int coumna, int dW) {
        this.cells.get(coumna).incWidth(dW);
        Point p = this.cells.get(coumna+1).getLocation();
        this.cells.get(coumna+1).incWidth(-dW);
        this.cells.get(coumna+1).setBounds(p.x +dW, p.y);
    }

    void incLB(int dX) {
        this.cells.get(0).incLB(dX);
    }

    void incRB(int dX) {
        Point p = this.cells.getLast().getLocation();
        this.cells.getLast().incWidth(dX);
    }
}
