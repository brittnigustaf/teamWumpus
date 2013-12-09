package eecs285.proj4.wumpus;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;

public class Player
{
 
	//Arrow
	protected boolean arrow;
  //private boolean fireMode;
  
	//private int facingDir;
	protected Point location;
	protected int playerNum;
	protected Room curRoom;
	protected int score;
	protected GameWindow game;

	public int numMoves;
	
	public Player (int num)
	{   
    setImages();
    
    numMoves = 0;
    arrow = true;
    //fireMode = false;
    
    curRoom = null;
    //curImage = moveUp;
    //facingDir = directions.orient("north");
    location = new Point(0, 0);
    
    score = 0;
    playerNum = num;
    
	}
	
	public Player (int inPlayerNum, Room inRoom, Point inLocation)
	{
	  setImages();
	  
	  numMoves = 0;
	  arrow = true;
	  //fireMode = false;
	  
    score = 0;
    playerNum = inPlayerNum;
	  
	  curRoom = inRoom; 
    if (playerNum == 1)
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    else
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	  //curImage = moveUp;
		//facingDir = directions.orient("north");
		location = inLocation;

		
	}
	
	private void setImages()
	{
	}
	
	public void shootArrow()
	{
		arrow = false;
	}
	
	public boolean hasArrow() 
	{
		return arrow;
	}
  
  //Moves the player North to a room
  public void moveNorth (Room toRoom, Point inLocation)
  {
    //curImage = moveUp;
    //facingDir = directions.orient("north");
    
    //Update Previous Room Border
    curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    //Change Room
    curRoom = toRoom;
    
    //Update New Room Border
    if (playerNum == 1)
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    else
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    
    //Change Location
    location = inLocation;
  }
  
  //Moves the player South to a room
  public void moveSouth (Room toRoom, Point inLocation)
  {
    //curImage = moveDown;
    //facingDir = directions.orient("south");
    
    //Update Previous Room Border
    curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    //Change Room
    curRoom = toRoom;
    
    //Update New Room Border
    if (playerNum == 1)
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    else
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    
    //Change Location
    location = inLocation;
  }
 
  //Moves the player East to a room
  public void moveEast (Room toRoom, Point inLocation)
  {
    //curImage = moveRight;
    //facingDir = directions.orient("east");
      
    //Update Previous Room Border
    curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    //Change Room
    curRoom = toRoom;
    
    //Update New Room Border
    if (playerNum == 1)
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    else
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
      
    //Change Location
    location = inLocation;
  }
 
  //Moves the player West to a room
  public void moveWest (Room toRoom, Point inLocation)
  {
    //curImage = moveLeft;
    //facingDir = directions.orient("west");
      
    //Update Previous Room Border
    curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    //Change Room
    curRoom = toRoom;
    
    //Update New Room Border
    if (playerNum == 1)
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    else
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
      
    //Change Location
    location = inLocation;
  }
  
  //Moves the player in specified direction to a room
  public void move(int inDirection, Room toRoom, Point inLocation)
  {
    /*
    facingDir = inDirection;
    
    switch(facingDir)
    {
      case 0:  curImage = moveUp;
               break;
      case 1:  curImage = moveRight;
               break;
      case 2:  curImage = moveDown;
               break;
      case 3:  curImage = moveLeft;
               break;
      default: System.out.println("Invalid Player Direction.");
               break;
    }
    */
    
    //Update Previous Room Border
    curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    //Change Room
    curRoom = toRoom;
    
    //Update New Room Border
    if (playerNum == 1)
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    else
      curRoom.panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    
    //Change Location
    location = inLocation;
  }
  
  //Returns the players name
  public String name()
  {
    return "Player " + playerNum;
  }
	
  public int getScore()
  {
    return score;
  }
  
  public void addToScore(int inScore)
  {
    score = score + inScore;
  }
  
  public Point getLocation()
  {
    return location;
  }
  
  public void setLocation (Point inLocation)
  {
    location = inLocation;
  }
  
  public Room getRoom()
  {
    return curRoom;
  }
  
  public void connectGame(GameWindow inGame){
	  game = inGame;
  }
	
  public void setRoom (Room inRoom)
  {
    curRoom = inRoom;
  }
}