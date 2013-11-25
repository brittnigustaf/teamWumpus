package eecs285.proj4.wumpus;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelpWindow extends JFrame {
    
   
    JButton backBtn = new JButton("Back");
    BorderLayout mainLayout = new BorderLayout();
    
    JPanel btnPane = new JPanel();
    JPanel helpPane = new JPanel();
    JPanel pane = new JPanel(mainLayout);
    JLabel helpText = new JLabel();
    
    
    
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
        
        helpText.setText("THIS IS WHERE THE HELP WILL BE ISN'T THAT THE COOLIEST?");
        helpPane.add(helpText);
        
        backBtn.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                
            }
        });
        btnPane.add(backBtn);
        
        pane.add(helpPane, BorderLayout.NORTH);
        pane.add(btnPane, BorderLayout.SOUTH);
    }

}
