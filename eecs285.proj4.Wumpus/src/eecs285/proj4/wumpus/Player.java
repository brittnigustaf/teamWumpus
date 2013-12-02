package eecs285.proj4.wumpus;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Player
{
  //Images are here for future use, not exactly implemented yet
	public ImageIcon curImage = new ImageIcon();
	
	private static ImageIcon moveUp = new ImageIcon();
	private static ImageIcon moveDown = new ImageIcon();
	private static ImageIcon moveLeft = new ImageIcon();
	private static ImageIcon moveRight = new ImageIcon();
	//private static ImageIcon fireUp = new ImageIcon();
	//private static ImageIcon fireDown = new ImageIcon();
	//private static ImageIcon fireLeft = new ImageIcon();
	//private static ImageIcon fireRight = new ImageIcon();
	
	//Directions enum, not a real player datapoint
	private Directions directions = new Directions();
	
	//Arrow
	private boolean arrow;
  //private boolean fireMode;
  
	private int facingDir;
	private Point location;
	private int playerNum;
	private Room curRoom;
	private int score;
	
	public Player ()
	{   
    setImages();
    
    arrow = true;
    //fireMode = false;
    
    curRoom = null;
    curImage = moveUp;
    facingDir = directions.orient("north");
    location = new Point(0, 0);
    
    score = 0;
    playerNum = 1;
    
	}
	
	public Player (int inPlayerNum, Room inRoom, Point inLocation)
	{
	  setImages();
	  
	  arrow = true;
	  //fireMode = false;
	  
	  curRoom = inRoom; 
	  curImage = moveUp;
		facingDir = directions.orient("north");
		location = inLocation;

    score = 0;
    playerNum = inPlayerNum;
		
	}
	
	private void setImages()
	{
	   //add player images
    /*
    moveUp = 
    moveDown = 
    moveLeft = 
    moveRight = 
    fireUp = 
    fireDown = 
    fireLeft = 
    fireRight = 
    */
	}
	
	/*
	public void setFireMode(boolean mode)
	{
	  //Returns if player is already in Fire Mode
	  if (fireMode && mode)
	    return;
	  
		fireMode = mode;
		if (fireMode)
  	{
  		switch(facingDir)
  		{
    		case 0:  curImage = fireUp;
    		         break;
        case 1:  curImage = fireRight;
                 break;
        case 2:  curImage = fireDown;
                 break;
        case 3:  curImage = fireLeft;
                 break;
        default: System.out.println("Invalid Player Fire Direction.");
                 break;
  		}
  	}
		
		else
  	{
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
  	}
	}
	*/
	
	public void shootArrow()
	{
		arrow = false;
	}
	
	public boolean hasArrow() 
	{
		return arrow;
	}
  
	//Determines if a player can move in the Specified Direction
	//Should probably go into room class
	/*
  public boolean canMoveDir(String inDirection, String curRoomInfo)
  {
    //Check map and see if a path allows movement in the specified direction
    
    //Temporary Return Value.
    return true;
  }
  */
  
  //Moves the player North to a room
  public void moveNorth (Room toRoom, Point inLocation)
  {
      curImage = moveUp;
      facingDir = directions.orient("north");
      
      //Change Room
      curRoom = toRoom;
      
      //Change Location
      location = inLocation;
  }
  
  //Moves the player South to a room
  public void moveSouth (Room toRoom, Point inLocation)
  {
      curImage = moveDown;
      facingDir = directions.orient("south");
      
      //Change Room
      curRoom = toRoom;
      
      //Change Location
      location = inLocation;
  }
 
  //Moves the player East to a room
  public void moveEast (Room toRoom, Point inLocation)
  {
      curImage = moveRight;
      facingDir = directions.orient("east");
      
      //Change Room
      curRoom = toRoom;
      
      //Change Location
      location = inLocation;
  }
 
  //Moves the player West to a room
  public void moveWest (Room toRoom, Point inLocation)
  {
      curImage = moveLeft;
      facingDir = directions.orient("west");
      
      //Change Room
      curRoom = toRoom;
      
      //Change Location
      location = inLocation;
  }
  
  //Moves the player in specified direction to a room
  public void move(int inDirection, Room toRoom, Point inLocation)
  {
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
    
    //Change Room
    curRoom = toRoom;
    
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
	
  public void setRoom (Room inRoom)
  {
    curRoom = inRoom;
  }
}