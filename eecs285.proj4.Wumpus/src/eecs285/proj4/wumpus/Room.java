package eecs285.proj4.wumpus;

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
  
  ToggleBox<String> hintAtPlayer(){
    //EFF: returns a string ToggleBox with the hints from the room
    //     The problem is I don't know how the player will recieve this yet.
    
    Room knockknock;
    String events[] = new String[4];
    boolean mesh[] = new boolean[4];
    for(int i=0; i<4;i++) mesh[i] = true;
    
    for (int i=0; i<4; i++){
      knockknock = doors[i];
      if(knockknock != null) events[i] = doors[i].revealTrap();
      else {
        events[i] = "";
        mesh[i] = false;
      }
    }
    
    //compare index 0 to all
    if(events[0].equals(events[1])){
      mesh[0] = false;
    }
    if(events[0].equals(events[2])){
      mesh[0] = false;
    }
    if(events[0].equals(events[3])){
      mesh[0] = false;
    }
    
    //compares index 1 to 2 and 3
    if(events[1].equals(events[2])){
      mesh[1] = false;
    }
    if(events[1].equals(events[3])){
      mesh[1] = false;
    }
    
    //compare index 2 to 3
    if(events[2].equals(events[3])){
      mesh[2] = false;
    }
    
    ToggleBox<String> eventCall = new ToggleBox<String> (events, mesh);
    
    return eventCall;
    
  }
  
  

}