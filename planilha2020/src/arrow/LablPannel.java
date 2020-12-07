package arrow;

import cores.AL;
import cores.CBoxModel;
import cores.CComboBox;
import cores.CGroup;
import cores.CListen1;
import cores.Cor;
import cores.GComboBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class LablPannel extends JPanel{

    private static final long serialVersionUID = 1;
    GridBagLayout bag;
    
    public LablPannel(Arrow arrow, JFrame jframe){
    
        this.bag =new GridBagLayout();
        this.setLayout(this.bag);
        
        JTextField exemploField= new JTextField("Exemplo");
        
        int y=0;
        JLabel lName = new JLabel("Color of label:", SwingConstants.CENTER);
        GridBagConstraints gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.gridwidth =3;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lName, gLRoots);
        this.add(lName);            
        
        
        y++;
        JLabel lLebel = new JLabel("Group of colors:", SwingConstants.CENTER);
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lLebel, gLRoots);
        this.add(lLebel);            

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        Dimension d = separator.getPreferredSize();
        d.height =10;
        d.width =2;
        separator.setPreferredSize(d);
        GridBagConstraints gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =1;
        gValue.gridy =y;
        bag.setConstraints(separator, gValue);        
        this.add(separator);         
        
        JLabel rLebel = new JLabel("Color:", SwingConstants.CENTER);
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =2;
        gValue.weightx =0.5;
        gValue.gridy =y;
        bag.setConstraints(rLebel, gValue);
        this.add(rLebel);     
        
        
        MPannel mPannel = arrow.getPannel();
        
        
        Color arrowColor = arrow.getColor();
        int[] corInd = mPannel.getColor(arrowColor);
        if (corInd==null){
            //System.out.println("Have not found color");
            corInd =new int[]{0, 0}; }         
        
        LinkedList<CGroup> cGroups =  mPannel.getCGroups();
        //CBoxModel<CGroup> сBoxMode4 =new CBoxModel<>(cGroups);
        GComboBox comboGroups1 = new GComboBox(cGroups);
        comboGroups1.setSelectedIndex(corInd[0]);        
        JScrollPane scrollGroups1 = new JScrollPane(comboGroups1);
        LinkedList<Cor> cores1 = cGroups.get(corInd[0]).getCores();
        CBoxModel<Cor> cBoxModel3 =new CBoxModel<>(cores1);
        CComboBox comboCores1 = new CComboBox(cores1, cBoxModel3);
        comboCores1.setSelectedIndex(corInd[1]);        
        JScrollPane scrollCores1 = new JScrollPane(comboCores1);
        comboGroups1.addActionListener(new AL(comboGroups1, comboCores1));
        comboCores1.addActionListener(new CListen1(comboCores1, exemploField));
        
        
        y++;
        //JLabel lLebel = new JLabel("Group of colors");
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(scrollGroups1, gLRoots);
        this.add(scrollGroups1);            

        separator = new JSeparator(SwingConstants.VERTICAL);
        d = separator.getPreferredSize();
        d.height =10;
        d.width =2;
        separator.setPreferredSize(d);
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =1;
        gValue.gridy =y;
        bag.setConstraints(separator, gValue);        
        this.add(separator);          
        
        
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =2;
        gValue.weightx =0.5;
        gValue.gridy =y;
        bag.setConstraints(scrollCores1, gValue);
        this.add(scrollCores1);             
        
        // **************
        y++;
        JCheckBox isTwoside = new JCheckBox("Two side arrow");
        isTwoside.setSelected(arrow.isTwoSides());
        gLRoots =new GridBagConstraints();
        //gLRoots.anchor =GridBagConstraints.NORTHWEST;
        //gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(isTwoside, gLRoots);
        this.add(isTwoside); 
        
        y++;
        lLebel = new JLabel("Type from", SwingConstants.CENTER);
        gLRoots =new GridBagConstraints();
        //gLRoots.anchor =GridBagConstraints.NORTHWEST;
        //gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lLebel, gLRoots);
        this.add(lLebel);            

        separator = new JSeparator(SwingConstants.VERTICAL);
        d = separator.getPreferredSize();
        d.height =10;
        d.width =2;
        separator.setPreferredSize(d);
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =1;
        gValue.gridy =y;
        bag.setConstraints(separator, gValue);        
        this.add(separator);         
        
        ConTypes cTypeFrom =new ConTypes();
        cTypeFrom.setSelectedIndex(arrow.getTypeFrom());
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =2;
        gValue.weightx =0.5;
        gValue.gridy =y;
        bag.setConstraints(cTypeFrom, gValue);
        this.add(cTypeFrom);  
        
        
        // **************
    
        
        y++;
        lLebel = new JLabel("Type to", SwingConstants.CENTER);
        gLRoots =new GridBagConstraints();
        //gLRoots.anchor =GridBagConstraints.NORTHWEST;
        //gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lLebel, gLRoots);
        this.add(lLebel);            

        separator = new JSeparator(SwingConstants.VERTICAL);
        d = separator.getPreferredSize();
        d.height =10;
        d.width =2;
        separator.setPreferredSize(d);
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =1;
        gValue.gridy =y;
        bag.setConstraints(separator, gValue);        
        this.add(separator);         
        
        ConTypes cTypeTo =new ConTypes();
        cTypeTo.setSelectedIndex(arrow.getTypeTo());
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =2;
        gValue.weightx =0.5;
        gValue.gridy =y;
        bag.setConstraints(cTypeTo, gValue);
        this.add(cTypeTo);          
        
        y++;
        JButton saveButton = new JButton("Save:");
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.gridwidth =3;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(saveButton, gLRoots);
        saveButton.addActionListener(new ArrSave(jframe,
            comboCores1,
            isTwoside,
            cTypeFrom, cTypeTo, arrow));
        this.add(saveButton);        
        
        
        
    }
}
