package eecs285.proj4.wumpus;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class HelpWindow extends JFrame {
    
   
    JButton backBtn = new JButton("Back");
    BorderLayout mainLayout = new BorderLayout();
    
    JPanel btnPane = new JPanel();
    JPanel helpPane = new JPanel();
    JPanel pane = new JPanel(mainLayout);
    JLabel helpText = new JLabel();
    /*JLabel instructiontext1 = new JLabel();
    JLabel instructiontext2 = new JLabel();
    JLabel instructiontext3 = new JLabel();
    JPanel instructionPane1 = new JPanel();
    JPanel instructionPane2 = new JPanel();
    JPanel instructionPane3 = new JPanel();*/
    JTextArea instructions = new JTextArea(6,30);
    
    HelpWindow(){
        super("Instructions");
        initialize();
    }
    void initialize(){
 setBounds(0,0,800,600);
        
        //make it so I don't actually close on "x"
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // inherit main frame
        Container con = this.getContentPane(); 
        
        // add the panel to frame
        con.add(pane); 
        
        helpText.setText("How to Play!");
        helpPane.add(helpText);
        /*instructiontext1.setText("Gather gold, avoid the traps, and kill the dreaded Wumpus!");
        instructiontext2.setText("Click on the arrows to go in the direction you want to go!");
        instructiontext3.setText("Read the Clues!");
        instructionPane1.add(instructiontext1);
        instructionPane2.add(instructiontext2);
        instructionPane3.add(instructiontext3);*/
        instructions.setEditable(false);
        instructions.append("Gather gold, avoid the traps, and kill the dreaded Wumpus!\n"
        		+ "Click on the arrows to go in the direction you want to go!\n\n" 
        		+ "Read the Clues:\n"
        		+ "Smell = Wumpus is nearby!\nWind = Trap is close!\nFluttering = Bats ahhh!");
        instructions.setFont(new Font("Serif",Font.PLAIN, 24));
        backBtn.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                
            }
        });
        btnPane.add(backBtn);
        
        pane.add(helpPane, BorderLayout.NORTH);
        pane.add(btnPane, BorderLayout.SOUTH);
        pane.add(instructions, BorderLayout.CENTER);
        /*pane.add(instructionPane1);
        pane.add(instructionPane2, BorderLayout.CENTER);
        pane.add(instructionPane3);//, BorderLayout.CENTER);*/
    }

}
