package eecs285.proj4.wumpus;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Player 
{
	public ImageIcon curImage = new ImageIcon();
	
	private static ImageIcon moveUp = new ImageIcon();
	private static ImageIcon moveDown = new ImageIcon();
	private static ImageIcon moveLeft = new ImageIcon();
	private static ImageIcon moveRight = new ImageIcon();
	private static ImageIcon fireUp = new ImageIcon();
	private static ImageIcon fireDown = new ImageIcon();
	private static ImageIcon fireLeft = new ImageIcon();
	private static ImageIcon fireRight = new ImageIcon();
	
	private Directions directions;
	
	private boolean arrow;
  private boolean fireMode;
  
	private int facingDir;
	private Point location;
	
	//for telling where the player is
	int coordinate[];
	
	
	public Player (int x, int y)
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
	 
		coordinate = new int[2];
		coordinate[0] = x;
		coordinate[1] = y;
	
	
	  curImage = moveUp;
		arrow = true;
		fireMode = false;
		directions = new Directions();
		facingDir = directions.orient("north");
	}
	
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
	
	public void shootArrow()
	{
		arrow = false;
	}
	
	public boolean hasArrow() 
	{
		return arrow;
	}
	
	public void turnNorth ()
	{
	  curImage = moveUp;
	  facingDir = directions.orient("north");
	}
	
	public void turnSouth ()
  {
    curImage = moveDown;
    facingDir = directions.orient("south");
  }
 
  public void turnEast ()
  {
    curImage = moveRight;
    facingDir = directions.orient("east");
  }
  
  public void turnWest ()
  {
    curImage = moveLeft;
    facingDir = directions.orient("west");
  }
	
	
}