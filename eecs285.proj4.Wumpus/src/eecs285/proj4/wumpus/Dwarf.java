package eecs285.proj4.wumpus;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
	  private String inLine;
	  Player playerList[];
	  private int numPlayers;
	  private int row;
	  private int col;
	  

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
		    System.out.println(inLine);
	  }
	  
	  Dwarf(Player inList[]){
		  //EFF: connects the file
		  inLine = "A,A,A,AG,A,A,A,A,A1,A,A,A,A,A,AW,A";
		  row = 4;
		  col = 4;
		  init(inList);
	  }
	  
	  void init(Player inList[]){
		  //EFF: builds the map
		  
		  playerList = inList;
		  numPlayers = inList.length;
		  
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
		  
		  switch(i){
		  case 0:
			  cor[0] = 0;
			  cor[1] = 0;
			  break;
		  case 1:
			  cor[0] = 0;
			  cor[1] = 1;
			  break;
		  case 2:
			  cor[0] = 0;
			  cor[1] = 2;
			  break;
		  case 3:
			  cor[0] = 0;
			  cor[1] = 3;
			  break;
		  case 4:
			  cor[0] = 1;
			  cor[1] = 0;
			  break;
		  case 5:
			  cor[0] = 1;
			  cor[1] = 1;
			  break;
		  case 6:
			  cor[0] = 1;
			  cor[1] = 2;
			  break;
		  case 7:
			  cor[0] = 1;
			  cor[1] = 3;
			  break;
		  case 8:
			  cor[0] = 2;
			  cor[1] = 0;
			  break;
		  case 9:
			  cor[0] = 2;
			  cor[1] = 1;
			  break;
		  case 10:
			  cor[0] = 2;
			  cor[1] = 2;
			  break;
		  case 11:
			  cor[0] = 2;
			  cor[1] = 3;
			  break;
		  case 12:
			  cor[0] = 3;
			  cor[1] = 0;
			  break;
		  case 13:
			  cor[0] = 3;
			  cor[1] = 1;
			  break;
		  case 14:
			  cor[0] = 3;
			  cor[1] = 2;
			  break;
		  default:
			  cor[0] = 3;
			  cor[1] = 3;
			  break;
		  }
		  
		  return cor;
	  }

	}