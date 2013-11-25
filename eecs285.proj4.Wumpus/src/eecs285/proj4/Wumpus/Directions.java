package eecs285.proj4.Wumpus;

public class Directions {
  //member variables
  int north;
  int east;
  int south;
  int west;
  
  Directions(){
    //EFF: inits the member variables to integers
    
    north = 0;
    east = 1;
    south = 2;
    west = 3;
  }
  
  int orient(String dir){
    //EFF: returns the interger associated with the cardianl direction
    //REQ: to be a cardinal direction
    
    dir = dir.toUpperCase();
    
    if(dir.equals("NORTH")) return north;
    if(dir.equals("EAST")) return east;
    if(dir.equals("SOUTH")) return south;
    if(dir.equals("WEST")) return west;
    
    
    return 5;
  }

}
