/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import planilha.menu.AddMLine;
import planilha.menu.AboutDialog;
import planilha.menu.AddElipse;
import planilha.menu.AddLosango0;
//import planilha.menu.Cores;
import planilha.menu.Exit;
import planilha.menu.PlSave;
//import planilha.menu.SavePDF;

/**
 *
 * @author Makar Plakhotnyk
 */
public class Glowny extends JFrame{
    
    private static final long serialVersionUID = 1;
    private String dirName;
    
    public Glowny(){
        String property = System.getProperty("user.dir");
        this.dirName =property +"/../data/";
        }
    
    public void init(){
        
        String fName ="plan1";
        
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(620, 900));
        
        MPannel panel = new MPannel(dirName, fName, this);
        panel.read();
        p.add(new JScrollPane(panel) , BorderLayout.CENTER);
        
        JScrollPane s = new javax.swing.JScrollPane(p);
        this.add(s);
        
        //Dimension a4 = new Dimension(2480, 3508);
        
        //setPreferredSize(a4);
        //setMaximumSize(a4);
        //setMinimumSize(a4);
        JMenu ProgMenu = new JMenu("Planilha");
        ProgMenu.add(new Exit("Exit"));
        ProgMenu.add(new PlSave("Save", panel));
        
        //JMenu menuData = new JMenu("Data");
        //menuData.add(new SavePDF("Save PDF", panel));
        
        JMenu menuAdd = new JMenu("Add");
        menuAdd.add(new AddMLine("Multi Line Text", panel));
        menuAdd.add(new AddElipse("New Elipse", panel));
        menuAdd.add(new AddLosango0("New Losango", panel));
        
        
        
        JMenu menuTest = new JMenu("About");
        //TestVectors testVectors = new TestVectors("Vectors");
        //menuTest.add(testVectors);
        //AboutDialog aboutDialog = new AboutDialog("About");
        
        JMenuItem about = new JMenuItem("About");
        menuTest.add(about);
        //menuData.add(new Cores("Cores", panel));

        about.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            AboutDialog ad = new AboutDialog(panel);
            ad.setVisible(true);
          }
        });        
        
        
        //menuTest.add(aboutDialog);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(ProgMenu);
        //menuBar.add(menuData);
        menuBar.add(menuAdd);
        menuBar.add(menuTest);
        setJMenuBar(menuBar);
        
        
        setLocationRelativeTo(null);
        setTitle("Planilhão");
        
        
        pack();
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int cx = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int cy = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(cx, cy);             
        setVisible(true);
        
    }

    public void setCurDir(String dir) {
        this.dirName =dir;
        }
    
    public String getCurDir(){
        return this.dirName;
        }

}
