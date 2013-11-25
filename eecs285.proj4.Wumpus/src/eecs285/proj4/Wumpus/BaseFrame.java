package eecs285.proj4.Wumpus;

import java.awt.Container;

import javax.swing.*;

import javax.swing.JFrame;


public class BaseFrame extends JFrame {
    
    JPanel pane = new JPanel();
    BaseFrame(){
        super();
        setBounds(0,0,0,0);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Container con = this.getContentPane(); // inherit main frame
        con.add(pane); // add the panel to frame
        MainMenuFrame StartGame =  new MainMenuFrame();
       
    }
}
