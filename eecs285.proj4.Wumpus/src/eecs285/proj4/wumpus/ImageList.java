package eecs285.proj4.wumpus;

import javax.swing.ImageIcon;

public class ImageList {
	//member variables
	
	static ImageIcon CROSS; 
	static ImageIcon UP_RIGHT;
	static ImageIcon UP_LEFT; 
	static ImageIcon UP_DOWN; 
	static ImageIcon DOWN_RIGHT;
	static ImageIcon DOWN_LEFT;
    static ImageIcon LEFT_RIGHT;
    static ImageIcon T_RIGHT;
    static ImageIcon T_LEFT;
    static ImageIcon T_UP;
    static ImageIcon T_DOWN;
    static ImageIcon EMPTY;

    
    ImageList(){
    	//EFF: imports all the images
    	
    	CROSS = new ImageIcon(); 
        UP_RIGHT = new ImageIcon();
        UP_LEFT = new ImageIcon();
        UP_DOWN = new ImageIcon();
        DOWN_RIGHT = new ImageIcon();
        DOWN_LEFT = new ImageIcon();
        LEFT_RIGHT = new ImageIcon();
        T_RIGHT = new ImageIcon();
        T_LEFT = new ImageIcon();
        T_UP = new ImageIcon();
        T_DOWN = new ImageIcon();
        EMPTY = new ImageIcon();
        
    }

}