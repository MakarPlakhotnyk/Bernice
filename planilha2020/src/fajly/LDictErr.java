/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fajly;

/**
 *
 * @author Makar Plakhotnyk
 */
public class LDictErr extends RuntimeException{
    
    private static final long serialVersionUID = 1;
    
    public LDictErr(String message) {
        super("");
        System.out.println("Local dictionary error: " +message);
    }    
    
public void println(String line){
    System.out.println(line);
    }    
    
}
