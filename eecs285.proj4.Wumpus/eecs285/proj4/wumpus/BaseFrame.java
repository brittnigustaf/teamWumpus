package eecs285.proj4.wumpus;

import java.awt.Container;

import javax.swing.*;


@SuppressWarnings("serial")
public class BaseFrame extends JFrame {
    
    JPanel pane = new JPanel();
    BaseFrame(){
        super();
        setBounds(0,0,0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con = this.getContentPane(); // inherit main frame
        con.add(pane); // add the panel to frame
        MainMenuFrame StartGame =  new MainMenuFrame();
       
    }
}
