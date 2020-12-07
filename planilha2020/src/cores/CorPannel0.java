package cores;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import plItem.TextObject;
import planilha.MPannel;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CorPannel0 extends JPanel{

    private static final long serialVersionUID = 1;
    
    //GridBagLayout bag;
    
    public CorPannel0(TextObject textObject, MPannel mPannel, JFrame jframe){
        GridBagLayout bag =new GridBagLayout();
        this.setLayout(bag);
        
        JTextField exemploField= new JTextField("Exemplo");
        
        int y=0;
        JLabel lName = new JLabel("TextColor:", SwingConstants.CENTER);
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

        
        Color backColor = textObject.getTextCor();
        int[] corInd = mPannel.getColor(backColor);
        if (corInd==null){
            //System.out.println("Have not found color");
            corInd =new int[]{0, 0}; }        
        LinkedList<CGroup> cGroups = mPannel.getCGroups();
        //CBoxModel<CGroup> cBoxModel = new CBoxModel<>(cGroups);
        GComboBox comboGroups1 = new GComboBox(cGroups);
        comboGroups1.setSelectedIndex(corInd[0]);
        JScrollPane scrollGroups1 = new JScrollPane(comboGroups1);
        LinkedList<Cor> cores1 = cGroups.get(corInd[0]).getCores();
        CBoxModel<Cor> newcBoxModel =new CBoxModel<>(cores1);
        CComboBox comboCores1 = new CComboBox(cores1, newcBoxModel);
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
        JLabel lName2 = new JLabel("Background color:", SwingConstants.CENTER);
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.gridwidth =3;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lName2, gLRoots);
        this.add(lName2);            
        
        
        y++;
        JLabel lLebel2 = new JLabel("Group of colors:", SwingConstants.CENTER);
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lLebel2, gLRoots);
        this.add(lLebel2);            

        
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
        
        JLabel rLebel2 = new JLabel("Color:", SwingConstants.CENTER);
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =2;
        gValue.weightx =0.5;
        gValue.gridy =y;
        bag.setConstraints(rLebel2, gValue);
        this.add(rLebel2);     
        
        

    
        backColor = textObject.getBackColor();
        corInd = mPannel.getColor(backColor);
        if (corInd==null){
            //System.out.println("Have not found color");
            corInd =new int[]{0, 0}; }          
        LinkedList<CGroup> cGroups2 = mPannel.getCGroups();
        //CBoxModel<CGroup> сBoxMode4 =new CBoxModel<>(cGroups);
        GComboBox comboGroups2 = new GComboBox(cGroups);
        comboGroups2.setSelectedIndex(corInd[0]);        
        JScrollPane scrollGroups2 = new JScrollPane(comboGroups2);
        LinkedList<Cor> cores2 = cGroups2.get(corInd[0]).getCores();
        CBoxModel<Cor> newcBoxModel2 =new CBoxModel<>(cores2);
        CComboBox comboCores2 = new CComboBox(cores2, newcBoxModel2);
        comboCores2.setSelectedIndex(corInd[1]);        
        JScrollPane scrollCores2 = new JScrollPane(comboCores2);
        comboGroups2.addActionListener(new AL(comboGroups2, comboCores2));
        comboCores2.addActionListener(new CListen2(comboCores2, exemploField));
        
        y++;
        //JLabel lLebel = new JLabel("Group of colors");
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(scrollGroups2, gLRoots);
        this.add(scrollGroups2);            

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
        bag.setConstraints(scrollCores2, gValue);
        this.add(scrollCores2);   
        
        
        
        
// **************

        y++;
        lName2 = new JLabel("Format", SwingConstants.CENTER);
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.gridwidth =3;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lName2, gLRoots);
        this.add(lName2);            
        
        
        y++;
        JCheckBox isTransp = new JCheckBox("Is transparent");
        isTransp.setSelected(textObject.isTransp());
        gLRoots =new GridBagConstraints();
        //gLRoots.anchor =GridBagConstraints.NORTHWEST;
        //gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(isTransp, gLRoots);
        this.add(isTransp);        
        
        y++;
        lLebel2 = new JLabel("Size:", SwingConstants.CENTER);
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.HORIZONTAL;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(lLebel2, gLRoots);
        this.add(lLebel2);   
        
        separator = new JSeparator(SwingConstants.VERTICAL);
        d = separator.getPreferredSize();
        d.height =20;
        d.width =2;
        separator.setPreferredSize(d);
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridheight =2;
        gValue.gridx =1;
        gValue.gridy =y;
        bag.setConstraints(separator, gValue);        
        this.add(separator);    
        
                 

        rLebel2 = new JLabel("Style", SwingConstants.CENTER);
        gValue=new GridBagConstraints();
        gValue.fill =GridBagConstraints.HORIZONTAL;
        gValue.gridx =2;
        gValue.weightx =0.5;
        gValue.gridy =y;
        bag.setConstraints(rLebel2, gValue);
        this.add(rLebel2);     
        
        
        LinkedList<String> model3 = new LinkedList<>();
        model3.add("Left top");
        model3.add("Center");
        
        
//        LinkedList<String> model2 = new LinkedList<>();
//        model2.add("1");
//        model2.add("2");
//        
//        
//        ComboBox<String> sufsBox3 = new ComboBox<>(model2);
//        ComboBox<String> sufsBox4 = new ComboBox<>(model2);        
        ComboBox<String> sufsBox3 = new ComboBox<>(model3);
        ComboBox<String> sufsBox4 = new ComboBox<>(model3);
        

    
        y++;
        //JLabel lLebel = new JLabel("Group of colors");
        JTextField sizeField= new JTextField(textObject.getFSize() +"");
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.BOTH;
        gLRoots.gridx =0;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(sizeField, gLRoots);
        this.add(sizeField);  
        
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
        bag.setConstraints(sufsBox4, gValue);
        this.add(sufsBox4);         
    
        y++;
        //JLabel lLebel = new JLabel("Group of colors");
        
        exemploField.setHorizontalAlignment(SwingConstants.CENTER);
        exemploField.setBackground(comboCores2.getSelested().getColor());
        exemploField.setForeground(comboCores1.getSelested().getColor());
        gLRoots =new GridBagConstraints();
        gLRoots.anchor =GridBagConstraints.NORTHWEST;
        gLRoots.fill =GridBagConstraints.BOTH;
        gLRoots.gridx =0;
        gLRoots.gridwidth =3;
        gLRoots.weightx =0.5;
        gLRoots.gridy =y;
        bag.setConstraints(exemploField, gLRoots);
        this.add(exemploField);        
        
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
        saveButton.addActionListener(new SaveButton(jframe,
            comboCores1, comboCores2, isTransp, textObject));
        this.add(saveButton);        
        
        
        
    }

    private static class SaveButton implements ActionListener {

        private final JFrame jframe;
        private final CComboBox comboCores1;
        private final CComboBox comboCores2;
        private final TextObject dItem;
        private final JCheckBox isTransp;
    
        public SaveButton(JFrame jframe,
                CComboBox comboCores1,
                    CComboBox comboCores2,
                JCheckBox isTransp,
                TextObject dItem) {
            this.jframe =jframe;
            this.comboCores1 =comboCores1;
            this.comboCores2 =comboCores2;
            this.isTransp =isTransp;
            this.dItem =dItem;
        }    
    
        @Override
        public void actionPerformed(ActionEvent e) {
            Color textCol = this.comboCores1.getSelested().getColor();
            Color backCor = this.comboCores2.getSelested().getColor();
            this.dItem.setCor(textCol, backCor);
            this.dItem.setTransp(this.isTransp.isSelected());
            this.jframe.dispatchEvent(
                new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
        }
        
    }
}

//public class SaveButton implements ActionListener{
//
//    private final JFrame jframe;
//    private final CComboBox comboCores1;
//    private final CComboBox comboCores2;
//    private final TextObject dItem;
//    private final JCheckBox isTransp;
//    
//    public SaveButton(JFrame jframe,
//            CComboBox comboCores1,
//            CComboBox comboCores2,
//            JCheckBox isTransp,
//            TextObject dItem) {
//        this.jframe =jframe;
//        this.comboCores1 =comboCores1;
//        this.comboCores2 =comboCores2;
//        this.isTransp =isTransp;
//        this.dItem =dItem;
//    }    
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Color textCol = this.comboCores1.getSelested().getColor();
//        Color backCor = this.comboCores2.getSelested().getColor();
//        this.dItem.setCor(textCol, backCor);
//        this.dItem.setTransp(this.isTransp.isSelected());
//        this.jframe.dispatchEvent(
//            new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
//    }
//    
//}