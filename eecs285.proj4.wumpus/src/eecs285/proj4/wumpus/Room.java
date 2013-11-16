package eecs285.proj4.wumpus;

public class Room {
  //Member variables
  //Trap trap;
  Room[] doors;
  Directions dir;
  
  Room(){
    //EFF: inits the room as a empty box
    //MOD: dir, doors, trap
    
    dir = new Directions();
    doors = new Room[4];
    for (int i = 0; i<4; i++){
      doors[i] = null;
    }
  }
  
  void add(Room inRoom, String direction){
    doors[dir.orient(direction)] = inRoom;
  }

}
