package eecs285.proj4.wumpus;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;

@SuppressWarnings("serial")
public class HelpWindow extends JFrame {
    
   
    JButton backBtn = new JButton("Back");
    BorderLayout mainLayout = new BorderLayout();
    
    JPanel btnPane = new JPanel();
    JPanel helpPane = new JPanel();
    JPanel pane = new JPanel(mainLayout);
    JLabel helpText = new JLabel();
    
    JTextArea instructions = new JTextArea(6,20);
    
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
       
        instructions.setEditable(false);
        instructions.append("Gather gold, avoid the traps, and kill the dreaded Wumpus!\n"
        		+ "Click on the arrows to go in the direction you want to go!\n\n" 
        		+ "Be careful! The dungeon isn't safe!:\n"
        		+ "If you smell a stinky smell, that means the wumpus is within 1 square of you!\n"
        		+ "If you feel a breeze, then there's a deadly pitfall within 1 square of you!\n"
        		+ "If you hear the fluttering of wings there are Giant Bats nearby!\n"
        		+ "The bats like to pick careless travelers and drop them\ninto dark corners of the dungeon!");
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

    }

}
