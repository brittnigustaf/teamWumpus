package eecs285.proj4.wumpus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
    
    Player curPlayer;
    Player players[];
    
  
	Room roomMap[][];
	Dwarf Urist;
	int rowNum;
	int colNum;
	
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
    JTextArea hints;

    
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
    
    Border blackBorder;
    
    private ButtonListener actionListener;
    private int moveDir;
    
    GameWindow(ScoreWindow inScore)
    {
      //EFF: generates a default 8x8, 1-player game
      
      super("Hunt The Wumpus!");
      
      players = new Player[1];
      players[0] = new Player();
      //players[1] = new Player();
      
      Urist = new Dwarf(players);
      rowNum = Urist.row;
      colNum = Urist.col;
      
      mapLayout = new GridLayout(rowNum, colNum);
      roomMap = Urist.dungeon;
         
      initialize();
    }

    GameWindow(int x, int y, int numPlayers, ScoreWindow inScore){
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
        setBounds(0,0,1200,800);
        
        //close on "x"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // inherit main frame
        Container con = this.getContentPane();
        
        JFrame window = new JFrame("Hunt the Wumpus!");
        
        //fill gamePane with roomIcons and find starting player position
        gamePane.setBounds(0, 0, 800, 600);
        gamePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //fill Info with status icons
        infoPane.setPreferredSize(new Dimension(1200,200));
        infoPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hints = new JTextArea();
        hints.setText("Welcome to the Dungeon!!");
        infoPane.add(hints);

       
        //fill options with desired options
        optionPane.setPreferredSize(new Dimension(350, 600));
        optionPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        blackBorder = BorderFactory.createLineBorder(Color.red);
        
        //Initialize Empty map!
        ImageList images = new ImageList();
        for (int i = 0; i < 8; i++)
        {
          for (int j = 0; j < 8; j++)
          {
            gamePane.add(Urist.dungeon[i][j].panel);
            //JLabel emptyMap = new JLabel(images.EMPTY);
            
          }
        }
        
        //Build Player Score Info Panel
        JPanel playerInfo = new JPanel(new GridLayout(2,1));
        
        //Initialize player (FOR DEBUGGING!)
        //players[0] = new Player(1, roomMap[5][3], roomMap[5][3].panel.getLocation());
        
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
        
        actionListener = new ButtonListener();
        northButton.addActionListener(actionListener);
        westButton.addActionListener(actionListener);
        eastButton.addActionListener(actionListener);
        southButton.addActionListener(actionListener);
        //e.addActionListener(actionListener);

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
        for(Player pl: players){
        	pl.curRoom.reveal();
        }
        validMoves();
       
    }
    
    void fire(Directions dir){
      //dirPanTitle.setTitle("Fire Directions");
        
    }
    
    void step(){
      curPlayer.curRoom.reveal();
      event();
      nextPlayer();
      hint();
      validMoves();
      
      
    }
    
    void hint(){
      //EFF: prints hints to user screen
      
      String allOfTheHints = "You enter a new Room \n";
      ToggleBox<String> hinters = curPlayer.curRoom.hintAtPlayer();
      for(int i=0; i<4;i++){
        if(hinters.getIndex(i) != null){
          allOfTheHints = hinters.getIndex(i) + "\n" + allOfTheHints;
          System.out.println(hinters.getIndex(i));
        }
      }
      
      hints.setText(allOfTheHints);
    }
    
    void nextPlayer(){
    	int cur = curPlayer.playerNum;
    	cur++;
    	if(cur> players.length){
    		cur =0;
    	}
    	curPlayer = players[cur];
    }
    
    void event(){
    	//EFF: plays event if there is a trap
    	
    	Trap trip = curPlayer.curRoom.trap;
    	Wumpus wump = curPlayer.curRoom.wumpus;
    	Random ran = new Random();
    	
    	if(wump != null){
    		if(players.length==1){
    			new GameOver(0, "You were eaten by the Wumpus!");
    		}
    		else {
    			String name = Integer.toString(curPlayer.playerNum);
    			nextPlayer();
    			int score = 5000 - curPlayer.numMoves*100;
    			new GameOver(score, "Player " + name + " was eaten by the Wumpus");
    		}
    	}
    	else if(trip !=null){
    		if(trip instanceof Gold){
    			int score = curPlayer.score;
    			int moves = curPlayer.numMoves;
    			String name = Integer.toString(curPlayer.playerNum);
    			score = score + ran.nextInt(5000) + 5000;
    			score = score - moves*100;
    			if(score<0) score = 0;
    			new GameOver(score, "Player "+ name +" found the gold!");                               
    		}
    		
    		if(trip instanceof Bats){
    			int newCol = ran.nextInt(colNum -1);
    			int newRow = ran.nextInt(rowNum -1);
    			
    			curPlayer.setRoom(roomMap[newRow][newCol]);
    			Point point = new Point(newRow, newCol);
    			curPlayer.setLocation(point);
    		}
    		
    		if(trip instanceof Pitfall){
    			if(players.length==1){
        			new GameOver(0, "You fell into a pit!");
        		}
        		else {
        			String name = Integer.toString(curPlayer.playerNum);
        			nextPlayer();
        			int score = 5000 - curPlayer.numMoves*100;
        			new GameOver(score, "Player " + name + " fell into a pit");
        		}
    		}
    	}
    }
    
    void validMoves(){
      //EFF: checks for valid moves for the player
      
      Boolean validDoors[] = curPlayer.curRoom.checkRoom();
      Directions dir = new Directions();
      
      northButton.setVisible(false);
      southButton.setVisible(false);
      westButton.setVisible(false);
      eastButton.setVisible(false);
      
      if(validDoors[dir.north]) northButton.setVisible(true);
      if(validDoors[dir.south]) southButton.setVisible(true);
      if(validDoors[dir.west]) westButton.setVisible(true);
      if(validDoors[dir.east]) eastButton.setVisible(true);
      
    }
    
    public class ButtonListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
        ImageList images = new ImageList();
        String desc = ((ImageIcon)((JButton)event.getSource()).getIcon()).getDescription();
        if (desc.equals(images.northArrow.getDescription()))
          curPlayer.move(0, curPlayer.curRoom.move("north"), curPlayer.curRoom.move("north").panel.getLocation());
        else if (desc.equals(images.eastArrow.getDescription()))
          curPlayer.move(1, curPlayer.curRoom.move("east"), curPlayer.curRoom.move("east").panel.getLocation());
        else if (desc.equals(images.southArrow.getDescription()))
          curPlayer.move(2, curPlayer.curRoom.move("south"), curPlayer.curRoom.move("south").panel.getLocation());
        else if (desc.equals(images.westArrow.getDescription()))
          curPlayer.move(3, curPlayer.curRoom.move("west"), curPlayer.curRoom.move("west").panel.getLocation());
        
        step();
      }
    }
  }
