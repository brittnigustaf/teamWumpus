package eecs285.proj4.Wumpus;

import java.awt.*;

import javax.swing.*;

public class GameWindow extends JFrame {
    
	Room curRoom = new Room();
  
	Room roomMap[][];
	
	ImageIcon haveArrow = new ImageIcon();
    ImageIcon moveRight = new ImageIcon();
    ImageIcon moveLeft = new ImageIcon();
    ImageIcon moveUp = new ImageIcon();
    ImageIcon moveDown = new ImageIcon();
    ImageIcon smell = new ImageIcon();
    ImageIcon gold = new ImageIcon();
    ImageIcon wind = new ImageIcon();

    
    BorderLayout mainLayout = new BorderLayout();
    GridLayout mapLayout;
    FlowLayout informationLayout = new FlowLayout();
    FlowLayout optionLayout = new FlowLayout();
    FlowLayout infoLayout = new FlowLayout();

    
    JPanel optionPane = new JPanel(optionLayout);
    JPanel infoPane = new JPanel(infoLayout);
    JPanel gamePane;
    JPanel pane = new JPanel(mainLayout);
    
    GameWindow(){
    	super("Hunt The Wumpus!");
        mapLayout = new GridLayout(8,8);
        roomMap = new Room[8][8];
        
        initialize();
    }
    GameWindow(int x, int y){
    	super("Hunt The Wumpus!");
        mapLayout = new GridLayout(8,8);
        roomMap = new Room[8][8];

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
        
        //fill gamePane with roomIcons
        gamePane.setBounds(0, 0, 800, 600);
        gamePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //fill Info with status icons
        infoPane.setPreferredSize(new Dimension(1000,200));
        infoPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

       
        //fill options with desired options
        optionPane.setPreferredSize(new Dimension(250, 600));
        optionPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        
        //set panes
        pane.add(gamePane, BorderLayout.WEST);
        pane.add(infoPane, BorderLayout.SOUTH);
        pane.add(optionPane, BorderLayout.EAST);
        
        // add the panel to frame
        con.add(pane);
        setVisible(true);
        
    }
    

}
