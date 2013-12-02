package eecs285.proj4.wumpus;

import hold.Dwarf;
import hold.Player;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
    
    Player curPlayer;
    Player players[];
    
	Room curRoom = new Room();
  
	Room roomMap[][];
	
	boolean fireMode;
	
	ImageIcon haveArrow = new ImageIcon();
    ImageIcon moveRight = new ImageIcon();
    ImageIcon moveLeft = new ImageIcon();
    ImageIcon moveUp = new ImageIcon();
    ImageIcon moveDown = new ImageIcon();
    ImageIcon smell = new ImageIcon();
    ImageIcon gold = new ImageIcon();
    ImageIcon wind = new ImageIcon();
    ImageIcon bats = new ImageIcon();

    
    BorderLayout mainLayout = new BorderLayout();
    GridLayout mapLayout;
    FlowLayout informationLayout = new FlowLayout();
    GridLayout optionLayout = new GridLayout(3,1);
    FlowLayout infoLayout = new FlowLayout();

    
    JPanel optionPane = new JPanel(optionLayout);
    JPanel infoPane = new JPanel(infoLayout);
    JPanel gamePane;
    JPanel pane = new JPanel(mainLayout);
    
    //Player Variables
    ArrayList<TitledBorder> playerBorders;
    ArrayList<JLabel> playerScore;
    
    //Direction Panel members
    JButton northButton;
    JButton southButton;
    JButton westButton;
    JButton eastButton;
    
    TitledBorder dirPanTitle;
    
    //Game Mode Panel members
    JButton changeMode;
    
    GameWindow(){
      //EFF: generates a default 8x8, 1-player game
      
      super("Hunt The Wumpus!");
      
      players = new Player[1];
      
      Dwarf Urist = new Dwarf(players);
      rowNum = Urist.row;
      colNum = Urist.col;
      
      mapLayout = new GridLayout(rowNum, colNum);
      roomMap = Urist.dungeon;
         
      initialize();
     }
    
    GameWindow(int x, int y, int numPlayers){
        //lets you make variable size rooms
    	super("Hunt The Wumpus!");
        mapLayout = new GridLayout(x,y);
        roomMap = new Room[x][y];
        players = new Player[numPlayers];
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
        
        JFrame window = new JFrame("Hunt the Wumpus!");
        
        //fill gamePane with roomIcons and find starting player position
        gamePane.setBounds(0, 0, 800, 600);
        gamePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //fill Info with status icons
        infoPane.setPreferredSize(new Dimension(1000,200));
        infoPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

       
        //fill options with desired options
        optionPane.setPreferredSize(new Dimension(250, 600));
        optionPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Initialize Empty map!
        ImageList images = new ImageList();
        for (int i = 0; i < 8; i++)
        {
          for (int j = 0; j < 8; j++)
          {
            JLabel emptyMap = new JLabel(images.EMPTY);
            gamePane.add(emptyMap);
          } 
        }
        
        //Build Player Score Info Panel
        JPanel playerInfo = new JPanel(new GridLayout(2,1));
        
        //Initialize player (FOR DEBUGGING!)
        players[0] = new Player();
        
        int i = 0;
        playerScore = new ArrayList<JLabel>();
        playerBorders = new ArrayList<TitledBorder>();
        
        //setup player Info
        for(Player player : players){
        
          //Player Score Info Panel
          String scoreMessage = "   " + player.name() + ":   " + Integer.toString(player.getScore());
          JLabel scoreLabel = new JLabel(scoreMessage);
          scoreLabel.setFont(new Font(scoreLabel.getName(), Font.PLAIN, 18));
          playerScore.add(scoreLabel);
          
          playerInfo.add(playerScore.get(i));
          
          i++;
        }
        
        //Player Score Border
        TitledBorder scoreBorder = BorderFactory.createTitledBorder("Player Scores");
        scoreBorder.setTitleJustification(TitledBorder.LEFT);
        playerInfo.setBorder(scoreBorder);
        
        curPlayer = players[0];
        
        //Create info on top of info pane
        JPanel topInfoPanel = new JPanel(new FlowLayout());
        
        //Player's turn Pane
        JPanel turnMessage = new JPanel(new FlowLayout());
        
        JLabel playersTurnMessage = new JLabel("Current Players Turn: ");
        
        JLabel curPlayerName = new JLabel(curPlayer.name());
        curPlayerName.setFont(new Font(curPlayerName.getName(), Font.PLAIN, 18));
        
        turnMessage.add(playersTurnMessage);
        turnMessage.add(curPlayerName);
        
        //Sets the initial fire mode to false
        fireMode = false;
        
        JLabel curGameMode = new JLabel("Current Mode: Move");
        changeMode = new JButton("Change to Fire Mode");
        
        topInfoPanel.add(turnMessage);
        topInfoPanel.add(new JLabel("                                                          "));
        topInfoPanel.add(curGameMode);
        topInfoPanel.add(changeMode);
        
        //Create Direction pane
        JPanel dirPanel = new JPanel(new GridLayout(3,3));

        northButton = new JButton(images.northArrow);
        southButton = new JButton(images.southArrow);
        westButton = new JButton(images.westArrow);
        eastButton = new JButton(images.eastArrow);
       
        dirPanel.add(new JLabel());
        dirPanel.add(northButton);
        dirPanel.add(new JLabel());
        dirPanel.add(westButton);
        dirPanel.add(new JLabel());
        dirPanel.add(eastButton);
        dirPanel.add(new JLabel());
        dirPanel.add(southButton);
        dirPanel.add(new JLabel());

        //Add direction panel titled border
        dirPanTitle = BorderFactory.createTitledBorder("Move Directions");
        dirPanTitle.setTitleJustification(TitledBorder.LEFT);
        dirPanel.setBorder(dirPanTitle);
        
        //Add all of the option Panes
        optionPane.add(topInfoPanel);
        optionPane.add(dirPanel);
        optionPane.add(playerInfo);
        
        //set panes
        pane.add(gamePane, BorderLayout.WEST);
        pane.add(infoPane, BorderLayout.SOUTH);
        pane.add(optionPane, BorderLayout.EAST);
        
        
        // add the panel to frame      
        window.add(pane);
        window.pack();
        window.setVisible(true);
        
        //con.add(pane);
        //setVisible(true);
        
    }
    
    void move(Directions dir){
        
    }
    
    void fire(Directions dir){
      //dirPanTitle.setTitle("Fire Directions");
        
    }
    
    void step(){
        //is called after every action to update the game state
        
    }

}
