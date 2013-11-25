package eecs285.proj4.Wumpus;

import javax.swing.ImageIcon;

public class Room {
  //Member variables
  private Trap trap;
  private Room[] doors;
  private Directions dir;
  ImageIcon image;
  
  Room(){
    //EFF: inits the room as a empty box
    //MOD: dir, doors, trap
    
    dir = new Directions();
    doors = new Room[4];
    for (int i = 0; i<4; i++){
      doors[i] = null;
    }
    trap = null;
    image = null;
  }
  
  void add(final Room inRoom, String direction){
    //EFF: add inRoom to rooms connected to this room
    //MOD: doors
    
    doors[dir.orient(direction)] = inRoom;
  }
  
  void add(ImageIcon inImage){
    //EFF: adds the image to the room
    
    image = inImage;
  }
  
  void add(final Trap inTrap){
    //EFF: adds trap to room
    //MOD: trap
    
    trap = inTrap;
  }
  
  Room move(String direction){
    //EFF: returns the room in the direction given
    
    return doors[dir.orient(direction)];
  }
  
  String revealTrap(){
    //EFF: reveals trap hint by returning a string
    
    return trap.callHint();
  }
  
  void hintAtPlayer(){
    //EFF: prints out hint to player
    //     The problem is I don't know how the player will recieve this yet.
    
    Room knockknock;
    for (int i=0; i<4; i++){
      knockknock = doors[i];
      if(knockknock != null) System.out.println(knockknock.revealTrap());
    }
  }
  
  

}
