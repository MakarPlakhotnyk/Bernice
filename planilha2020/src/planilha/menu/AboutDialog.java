/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilha.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import planilha.MPannel;

/**
 *
 * @author Makar, 26/10/2020
 */
public class AboutDialog extends JFrame {
  
    private static final long serialVersionUID = 1;
    
    public AboutDialog(MPannel panel) {
        this.setUndecorated(true);
        JPanel cPannel =new JPanel();
        cPannel.setBackground(Color.white);
        GridBagLayout bag =new GridBagLayout();
        cPannel.setLayout(bag);
        cPannel.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
        
        this.setTitle("About");

        JLabel lTitle = new JLabel("About Planilhão", SwingConstants.CENTER);
        
        
        Font font = panel.getFont(panel.getCurDir()+"\\fonts\\WeissRunD.ttf",
                Font.TRUETYPE_FONT, Font.PLAIN, 14); 
        lTitle.setFont(font);
    
        int y=0;
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.NORTHWEST;
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.gridx =0;
        gbc.gridwidth =2;
        gbc.weightx =0.5;
        gbc.gridy =y;
        bag.setConstraints(lTitle, gbc);
        cPannel.add(lTitle);     
    
        JLabel name = new JLabel();
        ImageIcon icon = new ImageIcon(panel.getCurDir()
            +"\\jpg\\1.jpg");
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        Image img = icon.getImage().getScaledInstance(
            w/2, h/2, 
            java.awt.Image.SCALE_SMOOTH);    
        icon = new ImageIcon(img);
        name.setIcon(icon);
    
        y++;
        gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.NORTHWEST;
        //gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.gridx =0;
        gbc.gridwidth =2;
        gbc.weightx =0.5;
        gbc.gridy =y;
        bag.setConstraints(name, gbc);
        cPannel.add(name);     
    
        JLabel myName = new JLabel(" Author: ",
            SwingConstants.LEFT);
        y++;
        gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.NORTHWEST;
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.gridx =0;
        gbc.weightx =0;
        gbc.gridy =y;
        bag.setConstraints(myName, gbc);
        cPannel.add(myName);   
        
        JLabel myNameV = new JLabel("Makar Plakhotnyk",
            SwingConstants.LEFT);
        gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.NORTHWEST;
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.gridx =1;
        gbc.weightx =0;
        gbc.gridy =y;
        bag.setConstraints(myNameV, gbc);
        cPannel.add(myNameV);         
        
        JLabel myMail= new JLabel(" mail: ",
            SwingConstants.RIGHT);
        y++;
        gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.EAST;
        gbc.gridx =0;
        gbc.weightx =0;
        gbc.gridy =y;
        bag.setConstraints(myMail, gbc);
        cPannel.add(myMail); 
        
        JLabel myMailV= new JLabel("Makar.Plakhotnyk@gmail.com",
            SwingConstants.LEFT);
        gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.NORTHWEST;
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.weightx =0;
        gbc.gridy =y;
        gbc.gridx =1;
        bag.setConstraints(myMailV, gbc);
        cPannel.add(myMailV);        
        
        JLabel lDate= new JLabel("December, 2020 ",
            SwingConstants.RIGHT);
        y++;
        gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.NORTHWEST;
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.gridx =0;
        gbc.gridwidth =2;
        gbc.weightx =1;
        gbc.gridy =y;
        bag.setConstraints(lDate, gbc);
        cPannel.add(lDate);                     
        

        JButton close = new JButton("Ok");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            dispose();
            }
            });

        y++;
        gbc =new GridBagConstraints();
        gbc.anchor =GridBagConstraints.CENTER;
        gbc.gridx =0;
        gbc.gridwidth =2;
        gbc.weightx =0.5;
        gbc.gridy =y;
        bag.setConstraints(close, gbc);
        cPannel.add(close);    
    
        this.add(cPannel);
        this.pack();
    
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);     
        }
}
