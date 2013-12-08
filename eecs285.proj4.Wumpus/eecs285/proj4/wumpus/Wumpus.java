package eecs285.proj4.wumpus;

/** Purpose: This is the Wumpus class
 * currently it only sits around
 * 
 * @author Jessica DeVriese
 */

public class Wumpus extends Trap{
   ImageList images = new ImageList();

  
  Wumpus(Room inRoom){
    //EFF: creates an instance of wumpus
    super(inRoom);
    image = images.WUMPUS;
  }

  
  String callHint() {
    //EFF: returns "A strong smell is smelt"
    
    return "You smell a smelly smell that smells";
  }

}
