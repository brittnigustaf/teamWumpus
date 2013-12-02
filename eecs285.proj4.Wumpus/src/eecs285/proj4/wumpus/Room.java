package eecs285.proj4.wumpus;

import javax.swing.ImageIcon;

public class Room {
  //Member variables
  private Trap[] trap;
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
  
  int[] getMoves(){
	  //EFF: returns a list of available moves
	  
	  int count = 0;
	  for(int i=0; i<4;i++){
		  if(doors[i]==null);
		  else{
			  count++;
		  }
	  }
	  int list[] = new int[count];
	  count = 0;
	  for(int i=0; i<4;i++){
		  if(doors[i]==null);
		  else{
			  list[count] = i;
			  count++;
		  }
	  }
	  
	  return list;
	  
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
  
  void addTrap(final Trap inTrap){
    //EFF: adds trap to room
    //MOD: trap
    
    trap[0] = inTrap;
  }
  
  void addWumpus(final Wumpus inWump){
	//EFF: only for wumpuses
	//MOD: trap[1]
	  
	trap[1] = inWump;
  }
  
  Room move(String direction){
    //EFF: returns the room in the direction given
    
    return doors[dir.orient(direction)];
  }
  
  Room move(int direction){
	  //EFF: returns the room in the given direction
	  
	  return doors[direction];
  }
  
  String revealTrap(int i){
    //EFF: reveals trap hint by returning a string
    
    return trap[i].callHint();
  }
  
  ToggleBox<String> hintAtPlayer(){
    //EFF: returns a string ToggleBox with the hints from the room
    //     The problem is I don't know how the player will recieve this yet.
    
    Room knockknock;
    String events[] = new String[8];
    boolean mesh[] = new boolean[8];
    for(int i=0; i<8;i++) mesh[i] = true;
    
    for (int i=0; i<4; i++){
      knockknock = doors[i];
      for(int j=0; i<2; i++){
    	  if(knockknock != null) events[i] = doors[i].revealTrap(j);
    	  else {
    		  events[i] = "";
    		  mesh[i] = false;
    	  }
      }
    }
    
    //compare index 0 to all
    if(events[0].equals(events[2])){
      mesh[0] = false;
    }
    if(events[0].equals(events[4])){
      mesh[0] = false;
    }
    if(events[0].equals(events[6])){
      mesh[0] = false;
    }
    
    //compares index 1 to 2 and 3
    if(events[2].equals(events[4])){
      mesh[2] = false;
    }
    if(events[2].equals(events[6])){
      mesh[2] = false;
    }
    
    //compare index 2 to 3
    if(events[4].equals(events[6])){
      mesh[4] = false;
    }
    
    //compare wumpus
    if(events[1].equals(events[3])){
        mesh[1] = false;
      }
    if(events[1].equals(events[5])){
       mesh[1] = false;
    }
    if(events[1].equals(events[7])){
       mesh[1] = false;
    }
    
    if(events[3].equals(events[5])){
      mesh[3] = false;
    }
    if(events[3].equals(events[7])){
      mesh[3] = false;
    }
    
    if(events[5].equals(events[7])){
      mesh[5] = false;
    }
    
     
    
    ToggleBox<String> eventCall = new ToggleBox<String> (events, mesh);
    
    return eventCall;
    
  }
  
  

}