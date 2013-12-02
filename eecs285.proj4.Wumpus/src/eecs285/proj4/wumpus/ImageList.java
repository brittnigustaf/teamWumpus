package eecs285.proj4.wumpus;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

    /*
    ImageList(){
    	//EFF: imports all the images
    	
    	CROSS = new ImageIcon("images/cross_tile.png"); 
      UP_RIGHT = new ImageIcon("images/en_tile.png");
      UP_LEFT = new ImageIcon("images/wn_tile.png");
      UP_DOWN = new ImageIcon("images/ns_tile.png");
      DOWN_RIGHT = new ImageIcon("images/es_tile.png");
      DOWN_LEFT = new ImageIcon("images/ws_tile.png");
      LEFT_RIGHT = new ImageIcon("images/ew_tile.png");
      T_RIGHT = new ImageIcon("images/nes_tile.png");
      T_LEFT = new ImageIcon("images/nsw_tile.png");
      T_UP = new ImageIcon("images/new_tile.png");
      T_DOWN = new ImageIcon("images/esw_tile.png");
      EMPTY = new ImageIcon("images/empty_tile.png");
        
    }
    */

    ImageList(){
      //EFF: imports all the images
      
      BufferedImage CROSS_img = null;
      BufferedImage UP_RIGHT_img = null;
      BufferedImage UP_LEFT_img = null;
      BufferedImage UP_DOWN_img = null;
      BufferedImage DOWN_RIGHT_img = null;
      BufferedImage DOWN_LEFT_img = null;
      BufferedImage LEFT_RIGHT_img = null;
      BufferedImage T_RIGHT_img = null;
      BufferedImage T_LEFT_img = null;
      BufferedImage T_UP_img = null;
      BufferedImage T_DOWN_img = null;
      BufferedImage EMPTY_img = null;
      
      try {
        CROSS_img = ImageIO.read(new File("images/cross_tile.png"));
        UP_RIGHT_img = ImageIO.read(new File("images/en_tile.png"));
        UP_LEFT_img = ImageIO.read(new File("images/wn_tile.png"));
        UP_DOWN_img = ImageIO.read(new File("images/ns_tile.png"));
        DOWN_RIGHT_img = ImageIO.read(new File("images/es_tile.png"));
        DOWN_LEFT_img = ImageIO.read(new File("images/ws_tile.png"));
        LEFT_RIGHT_img = ImageIO.read(new File("images/ew_tile.png"));
        T_RIGHT_img = ImageIO.read(new File("images/nes_tile.png"));
        T_LEFT_img = ImageIO.read(new File("images/nsw_tile.png"));
        T_UP_img = ImageIO.read(new File("images/new_tile.png"));
        T_DOWN_img = ImageIO.read(new File("images/esw_tile.png"));
        EMPTY_img = ImageIO.read(new File("images/empty_tile.png"));
      } 
      catch (IOException e) {
        e.printStackTrace();
      }
      
      BufferedImage CROSS_resized = resize(CROSS_img, 100, 75);
      BufferedImage UP_RIGHT_resized = resize(UP_RIGHT_img, 100, 75);
      BufferedImage UP_LEFT_resized = resize(UP_LEFT_img, 100, 75);
      BufferedImage UP_DOWN_resized = resize(UP_DOWN_img, 100, 75);
      BufferedImage DOWN_RIGHT_resized = resize(DOWN_RIGHT_img, 100, 75);
      BufferedImage DOWN_LEFT_resized = resize(DOWN_LEFT_img, 100, 75);
      BufferedImage LEFT_RIGHT_resized = resize(LEFT_RIGHT_img, 100, 75);
      BufferedImage T_RIGHT_resized = resize(T_RIGHT_img, 100, 75);
      BufferedImage T_LEFT_resized = resize(T_LEFT_img, 100, 75);
      BufferedImage T_UP_resized = resize(T_UP_img, 100, 75);
      BufferedImage T_DOWN_resized = resize(T_DOWN_img, 100, 75);
      BufferedImage EMPTY_resized = resize(EMPTY_img, 100, 75);
      
      CROSS = new ImageIcon(CROSS_resized); 
      UP_RIGHT = new ImageIcon(UP_RIGHT_resized);
      UP_LEFT = new ImageIcon(UP_LEFT_resized);
      UP_DOWN = new ImageIcon(UP_DOWN_resized);
      DOWN_RIGHT = new ImageIcon(DOWN_RIGHT_resized);
      DOWN_LEFT = new ImageIcon(DOWN_LEFT_resized);
      LEFT_RIGHT = new ImageIcon(LEFT_RIGHT_resized);
      T_RIGHT = new ImageIcon(T_RIGHT_resized);
      T_LEFT = new ImageIcon(T_LEFT_resized);
      T_UP = new ImageIcon(T_UP_resized);
      T_DOWN = new ImageIcon(T_DOWN_resized);
      EMPTY = new ImageIcon(EMPTY_resized);
        
    }
    
    //Resizes image based on given width and height
    ImageList(int width, int height){
      //EFF: imports all the images
      
      BufferedImage CROSS_img = null;
      BufferedImage UP_RIGHT_img = null;
      BufferedImage UP_LEFT_img = null;
      BufferedImage UP_DOWN_img = null;
      BufferedImage DOWN_RIGHT_img = null;
      BufferedImage DOWN_LEFT_img = null;
      BufferedImage LEFT_RIGHT_img = null;
      BufferedImage T_RIGHT_img = null;
      BufferedImage T_LEFT_img = null;
      BufferedImage T_UP_img = null;
      BufferedImage T_DOWN_img = null;
      BufferedImage EMPTY_img = null;
      
      try {
        CROSS_img = ImageIO.read(new File("images/cross_tile.png"));
        UP_RIGHT_img = ImageIO.read(new File("images/en_tile.png"));
        UP_LEFT_img = ImageIO.read(new File("images/wn_tile.png"));
        UP_DOWN_img = ImageIO.read(new File("images/ns_tile.png"));
        DOWN_RIGHT_img = ImageIO.read(new File("images/es_tile.png"));
        DOWN_LEFT_img = ImageIO.read(new File("images/ws_tile.png"));
        LEFT_RIGHT_img = ImageIO.read(new File("images/ew_tile.png"));
        T_RIGHT_img = ImageIO.read(new File("images/nes_tile.png"));
        T_LEFT_img = ImageIO.read(new File("images/nsw_tile.png"));
        T_UP_img = ImageIO.read(new File("images/new_tile.png"));
        T_DOWN_img = ImageIO.read(new File("images/esw_tile.png"));
        EMPTY_img = ImageIO.read(new File("images/empty_tile.png"));
      } 
      catch (IOException e) {
        e.printStackTrace();
      }
      
      BufferedImage CROSS_resized = resize(CROSS_img, width, height);
      BufferedImage UP_RIGHT_resized = resize(UP_RIGHT_img, width, height);
      BufferedImage UP_LEFT_resized = resize(UP_LEFT_img, width, height);
      BufferedImage UP_DOWN_resized = resize(UP_DOWN_img, width, height);
      BufferedImage DOWN_RIGHT_resized = resize(DOWN_RIGHT_img, width, height);
      BufferedImage DOWN_LEFT_resized = resize(DOWN_LEFT_img, width, height);
      BufferedImage LEFT_RIGHT_resized = resize(LEFT_RIGHT_img, width, height);
      BufferedImage T_RIGHT_resized = resize(T_RIGHT_img, width, height);
      BufferedImage T_LEFT_resized = resize(T_LEFT_img, width, height);
      BufferedImage T_UP_resized = resize(T_UP_img, width, height);
      BufferedImage T_DOWN_resized = resize(T_DOWN_img, width, height);
      BufferedImage EMPTY_resized = resize(EMPTY_img, width, height);
      
      CROSS = new ImageIcon(CROSS_resized); 
      UP_RIGHT = new ImageIcon(UP_RIGHT_resized);
      UP_LEFT = new ImageIcon(UP_LEFT_resized);
      UP_DOWN = new ImageIcon(UP_DOWN_resized);
      DOWN_RIGHT = new ImageIcon(DOWN_RIGHT_resized);
      DOWN_LEFT = new ImageIcon(DOWN_LEFT_resized);
      LEFT_RIGHT = new ImageIcon(LEFT_RIGHT_resized);
      T_RIGHT = new ImageIcon(T_RIGHT_resized);
      T_LEFT = new ImageIcon(T_LEFT_resized);
      T_UP = new ImageIcon(T_UP_resized);
      T_DOWN = new ImageIcon(T_DOWN_resized);
      EMPTY = new ImageIcon(EMPTY_resized);
        
    }
    
    
    //Resizes an image
    public static BufferedImage resize(BufferedImage image, int width, int height) {
      BufferedImage buffedImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
      Graphics2D g2d = (Graphics2D) buffedImage.createGraphics();
      g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
      g2d.drawImage(image, 0, 0, width, height, null);
      g2d.dispose();
      return buffedImage;
  }

}