package eecs285.proj4.wumpus;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/** Purpose: dwarf constructs the map given a bluePrint.
 * It requires a blueprint: filename
 * 
 * @author Jessica DeVriese
 */

public class Dwarf {
    //Member variables
    Room dungeon[][];
    Player playerList[];
    ScoreWindow scoreBox;
    
    protected String inLine;
    protected int numPlayers;
    protected int row;
    protected int col;
    private boolean bug = false;
    ImageList images = new ImageList();
    

    Dwarf(Path file, Player inList[]){
      //EFF: connects the inFile to the program and builds a dungeon
    //MOD: inLine
      
      readFile(file);
      init(inList);
    }
    
    void readFile(Path file){
      
      inLine = "";
      int rowCount = 0;
      col = 0;
      String colCount[];
        
        try (InputStream in = Files.newInputStream(file);
            BufferedReader reader =
              new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                inLine = inLine + line;
                if(col == 0){
                  colCount = inLine.split(",");
                  col = colCount.length;
                }
                rowCount++;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        
        row = rowCount;
        //System.out.println(inLine);
        //System.out.println(row);
        //System.out.println(col);
    }
    
    Dwarf(Player inList[]){
      //EFF: connects the file
      
      col = 8;
      row = 8;
      inLine =  "2E,F,E,K,1F,L,L,L,"
              + "B,J,A,A,I,E,K,F,"
              + "E,F,D,bB,C,H,C,D,"
              + "B,A,C,L,E,C,L,D,"
              + "E,J,K,F,B,G,K,I,"
              + "H,K,J,A,G,OWK,J,C,"
              + "D,D,L,B,F,H,K,F,"
              + "B,J,G,G,J,C,B,C,";

      init(inList);
    }
    
    Dwarf(Player inList[], String mapString){
        //EFF: connects the file
        
        col = 8;
        row = 8;
        inLine =  mapString;

        init(inList);
      }
    
    void init(Player inList[]){
      //EFF: builds the map
      
      playerList = inList;
      numPlayers = inList.length;
      
      dungeon = new Room[row][col];
      
      int cor[];
      String plans[];
      for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
          dungeon[i][j] = new Room();
          dungeon[i][j].addEmpty(images.EMPTY);
          dungeon[i][j].add(images.EMPTY);
        }
      }
      
      String hold[] = inLine.split(",");
      for(int i=0;i<hold.length;i++){
        cor = getCordinate(i);
        plans = hold[i].split("");
        build(plans, cor);
        
      }
    }
    
    void build(String plans[], int cor[]){
      //EFF: designs the room based on the plans
      for(int i=0; i<plans.length;i++){
      Codex codex = new Codex();

      if(plans[i].equals(codex.CROSS)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.CROSS);
        connect(cor, "north");
        connect(cor, "east");
        connect(cor, "south");
        connect(cor, "west");
      }
      
      if(plans[i].equals(codex.UP_RIGHT)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.UP_RIGHT);
        connect(cor, "north");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.UP_LEFT)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.UP_LEFT);
        connect(cor, "north");
        connect(cor, "west");
      }
      
      if(plans[i].equals(codex.UP_DOWN)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.UP_DOWN);
        connect(cor, "north");
        connect(cor, "south");
      }
      
      if(plans[i].equals(codex.DOWN_RIGHT)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.DOWN_RIGHT);
        connect(cor, "south");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.DOWN_LEFT)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.DOWN_LEFT);
        connect(cor, "south");
        connect(cor, "west");
      }
      
      if(plans[i].equals(codex.LEFT_RIGHT)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.LEFT_RIGHT);
        connect(cor, "west");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.T_RIGHT)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.T_RIGHT);
        connect(cor, "north");
        connect(cor, "east");
        connect(cor, "south");
      }
      
      if(plans[i].equals(codex.T_LEFT)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.T_LEFT);
        connect(cor, "north");
        connect(cor, "west");
        connect(cor, "south");
      }
      
      if(plans[i].equals(codex.T_UP)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.T_UP);
        connect(cor, "north");
        connect(cor, "west");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.T_DOWN)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].add(images.T_DOWN);
        connect(cor, "south");
        connect(cor, "west");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.PITFALL)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].addTrap(new Pitfall(dungeon[cor[0]][cor[1]]));
      }
        
      if(plans[i].equals(codex.BATS)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].addTrap(new Bats(dungeon[cor[0]][cor[1]]));
      }
        
      if(plans[i].equals(codex.WUMPUS)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].addWumpus(new Wumpus(dungeon[cor[0]][cor[1]]));
      }
      
      if(plans[i].equals(codex.GOLD)){
        debug(plans[i], cor);
        dungeon[cor[0]][cor[1]].addTrap(new Gold(dungeon[cor[0]][cor[1]]));
      }
      
      if(plans[i].equals(codex.PLAYER1)){
        debug(plans[i], cor);
        Point cordinate = new Point(cor[0], cor[1]);
        playerList[0].setLocation(cordinate);
        playerList[0].setRoom(dungeon[cor[0]][cor[1]]);
      }
      
      if(plans[i].equals(codex.PLAYER2)){
        if(numPlayers==2){
          debug(plans[i], cor);
          Point cordinate = new Point(cor[0], cor[1]);
          playerList[1].setLocation(cordinate);
          playerList[1].setRoom(dungeon[cor[0]][cor[1]]);
        }
      }
        
      }
    }
    
    void connect(int cor[], String direction){
      //EFF: Connects the two rooms
      //MOD: Dungeon
      
      if(direction.equals("north")) dungeon[cor[0]][cor[1]].add(dungeon[wrap(cor[0]-1,0)][cor[1]], "north");
      if(direction.equals("east")) dungeon[cor[0]][cor[1]].add(dungeon[cor[0]][wrap(cor[1]+1,1)], "east");
      if(direction.equals("south")) dungeon[cor[0]][cor[1]].add(dungeon[wrap(cor[0]+1,0)][cor[1]], "south");
      if(direction.equals("west")) dungeon[cor[0]][cor[1]].add(dungeon[cor[0]][wrap(cor[1]-1, 1)], "west");
    }
    
    int wrap(int cor, int dir){
      //EFF: helps warp the board
      
      if(dir == 0){
        if(cor<0) return (row - 1);
        if(cor>(row -1)) return 0;
      }
      
      if(dir == 1){
        if(cor<0) return (col - 1);
        if(cor>(col -1)) return 0;
      }
      
      return cor;
    }
    
    private void debug(String output, int cor[]){      
      //System.out.println("at " + cor[0] + ", " + cor[1] + " it is a: " + output);

    }
    
    private int[] getCordinate(int i){
      //EFF: based on the square number, return the cordinate
      
      int cor[] = new int[2];
      
      int newRow = i/col;
      int newCol = i%col;
      
      cor[0] = newRow;
      cor[1] = newCol;
      
      return cor;
    }

  }
