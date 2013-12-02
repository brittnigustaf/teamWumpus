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

    }

}
