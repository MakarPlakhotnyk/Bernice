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
public class ReadErr extends RuntimeException{
    
    private static final long serialVersionUID = 1;
    
    public ReadErr(String message) {
        super("");
        System.out.println("RunErr: " +message);
        }    

    public void print(String strLine) {
        System.out.println(strLine); }

}
