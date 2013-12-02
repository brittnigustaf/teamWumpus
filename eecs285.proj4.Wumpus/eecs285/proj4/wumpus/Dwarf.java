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
      inLine = "E,F,E,K,1F,L,2L,L,B,J,A,A,I,E,K,F,E,F,D,B,C,H,C,D,B,A,C,L,E,C,L,D,E,J,K,F,B,G,K,I,H,K,J,A,G,OWK,J,C,D,D,L,B,F,H,K,F,B,J,G,G,J,C,B,C,";
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
        dungeon[cor[0]][cor[1]].add(ImageList.CROSS);
        connect(cor, "north");
        connect(cor, "east");
        connect(cor, "south");
        connect(cor, "west");
      }
      
      if(plans[i].equals(codex.UP_RIGHT)){
        dungeon[cor[0]][cor[1]].add(ImageList.UP_RIGHT);
        connect(cor, "north");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.UP_LEFT)){
        dungeon[cor[0]][cor[1]].add(ImageList.UP_LEFT);
        connect(cor, "north");
        connect(cor, "west");
      }
      
      if(plans[i].equals(codex.UP_DOWN)){
        dungeon[cor[0]][cor[1]].add(ImageList.UP_DOWN);
        connect(cor, "north");
        connect(cor, "south");
      }
      
      if(plans[i].equals(codex.DOWN_RIGHT)){
        dungeon[cor[0]][cor[1]].add(ImageList.DOWN_RIGHT);
        connect(cor, "south");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.DOWN_LEFT)){
        dungeon[cor[0]][cor[1]].add(ImageList.DOWN_RIGHT);
        connect(cor, "south");
        connect(cor, "west");
      }
      
      if(plans[i].equals(codex.LEFT_RIGHT)){
        dungeon[cor[0]][cor[1]].add(ImageList.LEFT_RIGHT);
        connect(cor, "west");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.T_RIGHT)){
        dungeon[cor[0]][cor[1]].add(ImageList.T_RIGHT);
        connect(cor, "north");
        connect(cor, "east");
        connect(cor, "south");
      }
      
      if(plans[i].equals(codex.T_LEFT)){
        dungeon[cor[0]][cor[1]].add(ImageList.T_LEFT);
        connect(cor, "north");
        connect(cor, "west");
        connect(cor, "south");
      }
      
      if(plans[i].equals(codex.T_UP)){
        dungeon[cor[0]][cor[1]].add(ImageList.T_UP);
        connect(cor, "north");
        connect(cor, "west");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.T_DOWN)){
        dungeon[cor[0]][cor[1]].add(ImageList.T_DOWN);
        connect(cor, "south");
        connect(cor, "west");
        connect(cor, "east");
      }
      
      if(plans[i].equals(codex.PITFALL)){
        dungeon[cor[0]][cor[1]].addTrap(new Pitfall(dungeon[cor[0]][cor[1]]));
      }
        
      if(plans[i].equals(codex.BATS)){
        dungeon[cor[0]][cor[1]].addTrap(new Bats(dungeon[cor[0]][cor[1]]));
      }
        
      if(plans[i].equals(codex.WUMPUS)){
        dungeon[cor[0]][cor[1]].addWumpus(new Wumpus(dungeon[cor[0]][cor[1]]));
      }
      
      if(plans[i].equals(codex.GOLD)){
        dungeon[cor[0]][cor[1]].addTrap(new Gold(dungeon[cor[0]][cor[1]]));
      }
      
      if(plans[i].equals(codex.PLAYER1)){
        Point cordinate = new Point(cor[0], cor[1]);
        playerList[0].setLocation(cordinate);
      }
      
      if(plans[i].equals(codex.PLAYER2)){
        if(numPlayers==2){
          Point cordinate = new Point(cor[0], cor[1]);
          playerList[1].setLocation(cordinate);
        }
      }
        
      }
    }
    
    void connect(int cor[], String direction){
      //EFF: Connects the two rooms
      //MOD: Dungeon
      
      if(direction.equals("north")) dungeon[cor[0]][cor[1]].add(dungeon[wrap(cor[0]-1)][cor[1]], "north");
      if(direction.equals("east")) dungeon[cor[0]][cor[1]].add(dungeon[cor[0]][wrap(cor[1]+1)], "east");
      if(direction.equals("south")) dungeon[cor[0]][cor[1]].add(dungeon[wrap(cor[0]+1)][cor[1]], "south");
      if(direction.equals("west")) dungeon[cor[0]][cor[1]].add(dungeon[cor[0]][wrap(cor[1]-1)], "west");
    }
    
    int wrap(int cor){
      //EFF: helps warp the board
      
      if(cor<0) return 3;
      if(cor>3) return 0;
      
      return cor;
    }
    
    private int[] getCordinate(int i){
      //EFF: based on the square number, return the cordinate
      
      int cor[] = new int[2];
      
      int newRow = i/col;
      int newCol = i%col -1;
      
      cor[0] = newRow;
      cor[1] = newCol;
      
      return cor;
    }

  }