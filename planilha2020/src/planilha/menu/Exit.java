/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha.menu;

/**
 *
 * @author Makar Plakhotnyk
 */
import java.awt.event.*;
import javax.swing.*;

public class Exit extends AbstractAction{
    private static final long serialVersionUID = 1;
    
public Exit(String name){
    super(name);
    }
 
    @Override
 public void actionPerformed(ActionEvent event)
 {
  System.exit(0);
 }
}
