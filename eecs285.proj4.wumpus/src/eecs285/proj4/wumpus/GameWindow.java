package eecs285.proj3.teamWumpus;

import java.awt.*;

import javax.swing.*;

public class GameWindow extends JFrame {
    
    ImageIcon haveArrow = new ImageIcon();
    ImageIcon moveRight = new ImageIcon();
    ImageIcon moveLeft = new ImageIcon();
    ImageIcon moveUp = new ImageIcon();
    ImageIcon moveDown = new ImageIcon();

    
    BorderLayout mainLayout = new BorderLayout();
    GridLayout mapLayout;
    FlowLayout optionLayout = new FlowLayout();
    
    JPanel optionPan = new JPanel(optionLayout);
    JPanel gamePane;
    JPanel pane = new JPanel(mainLayout);
    
    GameWindow(){
        mapLayout = new GridLayout(8,8);
        
        initialize();
    }
    GameWindow(int x, int y){
        mapLayout = new GridLayout(8,8);
        
        initialize();
    }
    
    void initialize(){
        gamePane = new JPanel(mapLayout);
        
      //size the window
        setBounds(0,0,1000,800);
        
        //close on "x"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // inherit main frame
        Container con = this.getContentPane(); 
        
        // add the panel to frame
        con.add(pane); 
        
    }
    

}
