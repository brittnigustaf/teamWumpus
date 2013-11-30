package eecs285.proj4.wumpus;

public class Codex {
    //internal class for de-coding the file
    //member variables
    String CROSS; 
    String UP_RIGHT;
    String UP_LEFT; 
    String UP_DOWN; 
    String DOWN_RIGHT;
    String DOWN_LEFT;
    String LEFT_RIGHT;
    String T_RIGHT;
    String T_LEFT;
    String T_UP;
    String T_DOWN;
    String EMPTY;
    String PITFALL;
    String BATS;
    String WUMPUS;
    String GOLD;
    String PLAYER1;
    String PLAYER2;
    
    Codex(){
      CROSS = "A"; 
      UP_RIGHT = "B";
      UP_LEFT = "C"; 
      UP_DOWN = "D"; 
      DOWN_RIGHT = "E";
      DOWN_LEFT = "F";
      LEFT_RIGHT = "G";
      T_RIGHT = "H";
      T_LEFT = "I";
      T_UP = "J";
      T_DOWN = "K";
      EMPTY = "L";
      PITFALL = "P";
      BATS = "b";
      WUMPUS = "W";
      GOLD = "O";
      PLAYER1 = "1";
      PLAYER2="2";
    }
}
