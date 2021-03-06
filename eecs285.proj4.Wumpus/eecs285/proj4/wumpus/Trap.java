package eecs285.proj4.wumpus;

import javax.swing.ImageIcon;

/** Purpose: abstract class for trap.
 * Allows people to impliment many different types of traps like
 * wumpuses that move. Or pits. class must implement call hint
 * 
 * @author Jessica DeVirese
 */

abstract class Trap {
  //member variables
  ScoreWindow scoreBox;
  Room location;
  ImageIcon image;
  
  abstract String callHint();
  //EFF: returns a string
  
  Trap(Room inRoom){
    //EFF: traps must be assigned a room, and thus a room gets a trap
    //MOD: location
    location = inRoom;
  }

}
