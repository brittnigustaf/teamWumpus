package eecs285.proj4.wumpus;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Room {
  //Member variables
  protected Trap trap;
  protected Wumpus wumpus;
  protected Room[] doors;
  protected Directions dir;
  ImageIcon image;
  ImageIcon empty;
  
  JLabel roomImage;
  JPanel panel;
  Border blackBorder = BorderFactory.createLineBorder(Color.black);   
  
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
    
    panel = new JPanel(new GridLayout(1,1));
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
  
  void addEmpty(ImageIcon inImage){
	  
	empty = inImage;
	roomImage = new JLabel(image);
	panel.add(roomImage);
	panel.setBorder(blackBorder);
  }
  
  void reveal(){
	  roomImage.setIcon(image);
  }
  
  void addTrap(final Trap inTrap){
    //EFF: adds trap to room
    //MOD: trap
    
    trap = inTrap;
  }
  
  void addWumpus(final Wumpus inWump){
  //EFF: adds the wumpus to the room
  //MOD: wumpus
  
  wumpus = inWump;
  }
  
  Room move(String direction){
    //EFF: returns the room in the direction given
    
    return doors[dir.orient(direction)];
  }
  
  String revealTrap(){
    //EFF: reveals trap hint by returning a string
    
    return trap.callHint();
  }
  
  String checkStench(){
  //EFF: reveals the wumpus
    
  return wumpus.callHint();
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
    
    //deals with the wumpus
    for (int i=4; i<8; i++){
      knockknock = doors[i];
      if(knockknock != null) events[i] = doors[i].checkStench();
      else {
        events[i] = "";
        mesh[i] = false;
       }
     }
    
  //compare index 0 to all
    if(events[4].equals(events[5])){
      mesh[4] = false;
    }
    if(events[4].equals(events[6])){
      mesh[4] = false;
    }
    if(events[4].equals(events[7])){
      mesh[4] = false;
    }
    
    //compares index 1 to 2 and 3
    if(events[5].equals(events[6])){
      mesh[5] = false;
    }
    if(events[5].equals(events[7])){
      mesh[5] = false;
    }
    
    //compare index 2 to 3
    if(events[6].equals(events[7])){
      mesh[6] = false;
    }
    
    ToggleBox<String> eventCall = new ToggleBox<String> (events, mesh);
    
    return eventCall;
    
  }
  
  

}