package eecs285.proj4.wumpus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class MapMaker {
    
    public enum roomTypes{
        CROSS, 
        UP_RIGHT, 
        UP_LEFT, 
        UP_DOWN, 
        DOWN_RIGHT, 
        DOWN_LEFT, 
        LEFT_RIGHT,
        T_RIGHT,
        T_LEFT,
        T_UP,
        T_DOWN,
        EMPTY;
    }
    public enum Direction{
    	UP,
    	RIGHT,
    	DOWN,
    	LEFT;
    }
    
    private class littleRoom{
        roomTypes type = roomTypes.EMPTY;
        Set <Direction> canGo = new HashSet<Direction>();
        boolean isPit = false;
        boolean isBat = false;
        boolean P1Start = false;
        boolean P2Start = false;
        boolean wumpusStart = false;
    }
    
    public String makeMap(int x, int y, int numPits, int numBats){

        int size = x * y;
        if(size <= 0){
            return "error :(";
        }
        
        String[][] map = new String[x][y];
        littleRoom[][] rooms = new littleRoom[x][y];
        
        for(int i = 0; i < x; i++){
        	for(int j = 0; j < y; j++){
        		map[i][j] = "";
        		rooms[i][j] = new littleRoom();
        	}
        }

        
        String[][] mapAttempt = new String[map.length][];
        littleRoom[][] roomAttempt = new littleRoom[map.length][];
        do{
            for(int i = 0; i < map.length; i++){
            	mapAttempt[i] = map[i].clone();
                roomAttempt[i] = rooms[i].clone();

            }
            generateMap(mapAttempt,roomAttempt, numBats, numPits);
            //printMap(mapAttempt, roomAttempt);
        }
        while(!isPath(mapAttempt, roomAttempt));
        
        Codex codex = new Codex();
        /*for(int i = 0; i < mapAttempt.length; i++){
            for(int j = 0; j < mapAttempt[0].length; j++){
                mapAttempt[i][j].replaceAll("L", "LP");
                if(roomAttempt[i][j].type.equals(roomTypes.EMPTY)){
                    roomAttempt[i][j].isPit = true;
                }
            }
        }*/
        
        map = mapAttempt;
        rooms = roomAttempt;
        
        printMap(map,rooms);
        String out= "";
        for(String[] row: map){
        	for(String room: row){
        		out += room + ",";
        	}
        	out+='\n';
        }
        int numMaps;
        /*File mapDir = null;
		try {
			mapDir = new File(getClass().getResource("maps/").toURI());
			numMaps = mapDir.listFiles().length;
		} catch (Exception e) {
			numMaps = 0;
		}
        
        
        PrintWriter writer;
		try {
			writer = new PrintWriter("map" + numMaps, "UTF-8");
			writer.println("The first line");
		    writer.println("The second line");
		    writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Bad file write");
			e.printStackTrace();
		}*/
       System.out.print(out);
       return out; 
    }

    private static void printMap(String[][] map, littleRoom[][] rooms) {
    	//prints out a map of wumpus rooms.
    	System.out.println("----------------------------------------------------");
    	for(String[] row: map){
    		String line = "|";
    		for(String room: row){
    			int bufLen = 5;
    			bufLen-=room.length();
    			line += room;
    			for(int i = 0; i < bufLen; i++){
    				line += " ";
    			}
    			line += "|";
    			
    		}
    		System.out.println(line);
    		System.out.println("----------------------------------------------------");
    		
    	}
    
    	return;
    }

    private static void generateMap(String[][] map, littleRoom[][] rooms, int numBats, int numPits) {
    	//generates a random map for hunt the wumpus 
    	//given the dimensions of the map.
    	int x = map.length;
    	int y = map[0].length;
    	int size = x * y;
    	Set<roomTypes> options = new HashSet<roomTypes>();
    	for (roomTypes tmp: roomTypes.values()){
    		options.add(tmp);
    	}
    	Set<roomTypes> up = new HashSet<roomTypes>();
    	up.add(roomTypes.CROSS);
    	up.add(roomTypes.UP_RIGHT);
    	up.add(roomTypes.UP_LEFT);
    	up.add(roomTypes.UP_DOWN);
    	up.add(roomTypes.T_UP);
    	up.add(roomTypes.T_LEFT);
    	up.add(roomTypes.T_RIGHT);
    	
    	Set<roomTypes> down = new HashSet<roomTypes>();
    	down.add(roomTypes.CROSS);
    	down.add(roomTypes.DOWN_RIGHT);
    	down.add(roomTypes.DOWN_LEFT);
    	down.add(roomTypes.UP_DOWN);
    	down.add(roomTypes.T_DOWN);
    	down.add(roomTypes.T_LEFT);
    	down.add(roomTypes.T_RIGHT);
    	
    	Set<roomTypes> left = new HashSet<roomTypes>();
    	left.add(roomTypes.CROSS);
    	left.add(roomTypes.LEFT_RIGHT);
    	left.add(roomTypes.UP_LEFT);
    	left.add(roomTypes.DOWN_LEFT);
    	left.add(roomTypes.T_UP);
    	left.add(roomTypes.T_LEFT);
    	left.add(roomTypes.T_DOWN);
    	
    	Set<roomTypes> right = new HashSet<roomTypes>();
    	right.add(roomTypes.CROSS);
    	right.add(roomTypes.UP_RIGHT);
    	right.add(roomTypes.DOWN_RIGHT);
    	right.add(roomTypes.LEFT_RIGHT);
    	right.add(roomTypes.T_UP);
    	right.add(roomTypes.T_DOWN);
    	right.add(roomTypes.T_RIGHT);
    	
    	Random rando = new Random();
        
        int start1 = rando.nextInt(Integer.MAX_VALUE) % size;
        
        int start1x = start1 / x;
        int start1y = start1 % x;
        
        map[start1x][start1y] += "1"; // set that square to be the 
                                      //starting square for P1
        //System.out.println(start1x + "," + start1y);
        
        rooms[start1x][start1y].P1Start = true;

        
        int start2;
        
        do{ //make sure start2 and start1 aren't the same square;
            start2 = rando.nextInt(Integer.MAX_VALUE) % size;
        }
        while(start2 == start1);
        
        int start2x = start2 / x;
        int start2y = start2 % x;
        
        map[start2x][start2y] += "2"; // set that square to be the 
                                      //starting square for P2
        rooms[start2x][start2y].P2Start = true;
     
        int wumpusSquare;
        do{ //make sure start2 and start1 aren't the same square;
            wumpusSquare = rando.nextInt(Integer.MAX_VALUE) % size;
        }
        while(wumpusSquare == start2 || wumpusSquare == start1);
        
        int wumpusx = wumpusSquare / x;
        int wumpusy = wumpusSquare % x;
        
        map[wumpusx][wumpusy] += "O";
        map[wumpusx][wumpusy] += "W"; // set that square to be the 
                                      //starting square for the wumpus
        rooms[wumpusx][wumpusy].wumpusStart = true;
        
        Integer batSquares[] = new Integer[numBats];
        int numFound = 0;
        for(int i = 0; i < numBats; i++){
            do{ //make sure start2 and start1 aren't the same square;
                batSquares[numFound] = rando.nextInt(Integer.MAX_VALUE) % size;
            }
            while(batSquares[numFound] == start1 || 
                    batSquares[numFound] == start2);
        
            int batx = batSquares[numFound] / x;
            int baty = batSquares[numFound] % x;
            
            numFound++;
            
            map[batx][baty] += "b"; // set that square to have bats
            rooms[batx][baty].isBat = true;
        }
        
        Integer pitSquares[] = new Integer[numPits];
        numFound = 0;
        for(int i = 0; i < numPits; i++){
            do{ //make sure start2 and start1 aren't the same square;
                pitSquares[numFound] = rando.nextInt(Integer.MAX_VALUE) % size;
            }
            while(pitSquares[numFound] == start1 ||
                    pitSquares[numFound] == start2);
        
            int pitx = pitSquares[numFound] / x;
            int pity = pitSquares[numFound] % x;
            
            numFound++;
            
            map[pitx][pity] += "P"; // set that square to have a pit
            rooms[pitx][pity].isPit = true;
        }
    	
    	for(int i = 0; i < x; i ++){
    		for(int j = 0; j < y; j++){
    			Set<roomTypes> roomOptions = new HashSet<roomTypes>();
    			roomOptions.addAll(options);
    			
    			//first, if it's an edge, don't let them go over the edge
    			if(i == 0){ //can't go up
    				roomOptions.removeAll(up);	
    			}
    			if(j == 0){//can't go left
    				roomOptions.removeAll(left);
    			}
    			if(i == x - 1){//can't go down
    				roomOptions.removeAll(down);
    			}
    			if(j == y - 1){//can't go right
    				roomOptions.removeAll(right);
    			}
    			//now check neighbors for things I have to follow
    			Set<Direction> requires = new HashSet<Direction>();
    			
    			//get the intersection of options and requirements
    			if(j+1 < y && rooms[i][j+1].type != roomTypes.EMPTY){
    				if(rooms[i][j+1].canGo.contains(Direction.LEFT) ){
    					requires.add(Direction.RIGHT);
    					roomOptions.retainAll(right);
    				}
    				else{
    					roomOptions.removeAll(right);
    				}
    			}
    			if(i+1 < x && rooms[i+1][j].type != roomTypes.EMPTY){
    				if(rooms[i+1][j].canGo.contains(Direction.UP) ){
    					requires.add(Direction.DOWN);
    					roomOptions.retainAll(down);
    				}
    				else{
    					roomOptions.removeAll(down);
    				}
    			}
    			if(j-1 >= 0 && rooms[i][j-1].type != roomTypes.EMPTY){
    				if(rooms[i][j-1].canGo.contains(Direction.RIGHT) ){
    					requires.add(Direction.LEFT);
    					roomOptions.retainAll(left);
    				}
    				else{
    					roomOptions.removeAll(left);
    				}
    			}
    			else if(j-1 >= 0 && rooms[i][j-1].type == roomTypes.EMPTY){
    				roomOptions.removeAll(left);
    			}
    			if(i -1 >= 0 && rooms[i-1][j].type != roomTypes.EMPTY){
    				if(rooms[i-1][j].canGo.contains(Direction.DOWN) ){
    					requires.add(Direction.UP);
    					roomOptions.retainAll(up);
    				}
    				else{
    					roomOptions.removeAll(up);
    				}
    			}
    			else if(i-1 >=0 && rooms[i-1][j].type == roomTypes.EMPTY){
    				roomOptions.removeAll(up);
    			}
    			
    			//finally, pick one of the remaining options
    			//at random and assign it
    			int pick;
    			if(roomOptions.size() == 0){
    				return;
    			}
    			else{
	    			pick = rando.nextInt(Integer.MAX_VALUE) % roomOptions.size();
    			}
    			
    			roomTypes room_type = (roomTypes) roomOptions.toArray()[pick];
    			rooms[i][j].type = room_type;
    			switch(room_type){
    			case CROSS:
    				map[i][j] += "A";
        			rooms[i][j].canGo.add(Direction.UP);
        			rooms[i][j].canGo.add(Direction.DOWN);
        			rooms[i][j].canGo.add(Direction.LEFT);
        			rooms[i][j].canGo.add(Direction.RIGHT);
    				break;
    			case UP_RIGHT:
    				map[i][j] += "B";
        			rooms[i][j].canGo.add(Direction.UP);
        			rooms[i][j].canGo.add(Direction.RIGHT);
    				break;
    			case UP_LEFT:
    				map[i][j] += "C";
        			rooms[i][j].canGo.add(Direction.UP);
        			rooms[i][j].canGo.add(Direction.LEFT);
    				break;
    			case UP_DOWN:
    				map[i][j] += "D";
        			rooms[i][j].canGo.add(Direction.UP);
        			rooms[i][j].canGo.add(Direction.DOWN);
    				break;
    			case DOWN_RIGHT:
    				map[i][j] += "E";
        			rooms[i][j].canGo.add(Direction.DOWN);
        			rooms[i][j].canGo.add(Direction.RIGHT);
    				break;
    			case DOWN_LEFT:
    				map[i][j] += "F";
        			rooms[i][j].canGo.add(Direction.DOWN);
        			rooms[i][j].canGo.add(Direction.LEFT);
    				break;
    			case LEFT_RIGHT:
    				map[i][j] += "G";
        			rooms[i][j].canGo.add(Direction.LEFT);
        			rooms[i][j].canGo.add(Direction.RIGHT);
    				break;
    			case T_RIGHT:
    				map[i][j] += "H";
        			rooms[i][j].canGo.add(Direction.UP);
        			rooms[i][j].canGo.add(Direction.DOWN);
        			rooms[i][j].canGo.add(Direction.RIGHT);
    				break;
    			case T_LEFT:
    				map[i][j] += "I";
        			rooms[i][j].canGo.add(Direction.UP);
        			rooms[i][j].canGo.add(Direction.DOWN);
        			rooms[i][j].canGo.add(Direction.LEFT);
    				break;
    			case T_UP:
    				map[i][j] += "J";
        			rooms[i][j].canGo.add(Direction.UP);
        			rooms[i][j].canGo.add(Direction.LEFT);
        			rooms[i][j].canGo.add(Direction.RIGHT);
    				break;
    			case T_DOWN:
    				map[i][j] += "K";
        			rooms[i][j].canGo.add(Direction.DOWN);
        			rooms[i][j].canGo.add(Direction.LEFT);
        			rooms[i][j].canGo.add(Direction.RIGHT);
				break;
    			case EMPTY:
    				map[i][j] += "PL";
    				break;
    			default:
    			    map[i][j] += "b";
    				break;
    			
    					
    			}
    		}
    	}
    }

	private static boolean isPath(String[][] map, littleRoom[][] rooms) {
        int x = map.length;
        int y = map[0].length;
        int p1Loc = -1;
        int p2Loc = -1;
        int wumpusLoc = -1;
        int p1x = -1;
    	int p1y = -1;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if (map[i][j].contains("1"))
                    p1Loc = i*y + j;
                	p1x = i;
                	p1y = j;
                if(map[i][j].contains("2"))
                    p2Loc = i*y + j;
                if(map[i][j].contains("W"))
                    wumpusLoc = i*y + j;
            }
        }
        
        Queue <Integer> toVisit = new ArrayDeque<Integer>();
        Boolean[][] haveVisited = new Boolean[x][y];
        for(int i = 0; i < x; i++){
        	for(int j = 0; j < y; j++){
        		haveVisited[i][j] = false;
        	}
        }
        
        haveVisited[p1x][p1y] = true;
        toVisit.add(p1Loc);
        
        while(toVisit.size() > 0){
            Integer curRoom = toVisit.remove();
            Integer curX = curRoom / x;
            Integer curY = curRoom % x;
            //System.out.println("At:" + curX + "," + curY);
            littleRoom currentRoom = rooms[curX][curY];
            if(	!currentRoom.isPit && !currentRoom.isBat){
            	switch (currentRoom.type){ //add appropriate things to queue
	                case CROSS:
	                	if(curX + 1 < x && !haveVisited[curX+1][curY]){ //add down
	                		toVisit.add((curX + 1) * y + (curY));
	                		haveVisited[curX+1][curY] = true;
	                	}
	                	if(curX - 1 >= 0 && !haveVisited[curX-1][curY]){ //add up
	                		toVisit.add((curX - 1) * y + (curY));
                			haveVisited[curX-1][curY] = true;
	                	}
	                	if(curY + 1 < y && !haveVisited[curX][curY+1]){ //add right
	                		toVisit.add((curX) * y + (curY + 1));
                			haveVisited[curX][curY + 1] = true;
	                	}
	                	if(curY - 1 >= 0 && !haveVisited[curX][curY-1]){ //add left
	                		toVisit.add((curX) * y + (curY - 1));
            				haveVisited[curX][curY - 1] = true;
	                	}
	                    break;
	                case UP_RIGHT:
	                	if(curX - 1 >= 0 && !haveVisited[curX-1][curY]){ //add up
	                		toVisit.add((curX - 1) * y + (curY));
	                		haveVisited[curX-1][curY] = true;
	                	}
	                	if(curY + 1 < y && !haveVisited[curX][curY+1]){ //add right
	                		toVisit.add((curX) * y + (curY + 1));
	                		haveVisited[curX][curY + 1] = true;
            			}
            			break;
	                case UP_LEFT:
	                	if(curX - 1 >= 0 && !haveVisited[curX-1][curY]){ //add up
	                		toVisit.add((curX - 1) * y + (curY));
	                		haveVisited[curX-1][curY] = true;
	                	}
	                	if(curY - 1 >= 0 && !haveVisited[curX][curY-1]){ //add left
	                		toVisit.add((curX) * y + (curY - 1));
	                		haveVisited[curX][curY - 1] = true;
	                	}
	                    break;
	                case UP_DOWN:
	                	if(curX + 1 < x && !haveVisited[curX+1][curY]){ //add down
	                		toVisit.add((curX + 1) * y + (curY));
	                		haveVisited[curX+1][curY] = true;
	                	}
	                	if(curX - 1 >= 0 && !haveVisited[curX-1][curY]){ //add up
	                		toVisit.add((curX - 1) * y + (curY));
	                		haveVisited[curX-1][curY] = true;
	                	}
	                    break;
	                case DOWN_RIGHT:
	                	if(curX + 1 < x && !haveVisited[curX+1][curY]){ //add down
	                		toVisit.add((curX + 1) * y + (curY));
	                		haveVisited[curX+1][curY] = true;
	                	}
	                	if(curY + 1 < y && !haveVisited[curX][curY+1]){ //add right
	                		toVisit.add((curX) * y + (curY + 1)); 
	                		haveVisited[curX][curY + 1] = true;
	                	}
	                	break;
	                case DOWN_LEFT:
	                	if(curX + 1 < x && !haveVisited[curX+1][curY]){ //add down
	                		toVisit.add((curX + 1) * y + (curY)); 
	                		haveVisited[curX+1][curY] = true;
	                	}
	                	if(curY - 1 >= 0 && !haveVisited[curX][curY-1]){ //add left
	                		toVisit.add((curX) * y + (curY - 1));
	                		haveVisited[curX][curY - 1] = true;
	                	}
	                	break;                
	                case LEFT_RIGHT:
	                	if(curY + 1 < y && !haveVisited[curX][curY+1]){ //add right
	                		toVisit.add((curX) * y + (curY + 1));
	                		haveVisited[curX][curY + 1] = true;
	                	}
	                	if(curY - 1 >= 0 && !haveVisited[curX][curY-1]){ //add left
	                		toVisit.add((curX) * y + (curY - 1)); 
	                		haveVisited[curX][curY - 1] = true;
	                		}
	                	break;
	                case T_RIGHT:
	                	if(curX + 1 < x && !haveVisited[curX+1][curY]){ //add down
	                		toVisit.add((curX + 1) * y + (curY)); 
	                		haveVisited[curX+1][curY] = true;
	                	}
	                	if(curX - 1 >= 0 && !haveVisited[curX-1][curY]){ //add up
	                		toVisit.add((curX - 1) * y + (curY)); 
	                		haveVisited[curX-1][curY] = true;
	                	}
	                	if(curY + 1 < y && !haveVisited[curX][curY+1]){ //add right
	                		toVisit.add((curX) * y + (curY + 1));
	                		haveVisited[curX][curY + 1] = true;
	                	}
	                    break;
	                case T_LEFT:
	                	if(curX + 1 < x && !haveVisited[curX+1][curY]){ //add down
	                		toVisit.add((curX + 1) * y + (curY));
	                		haveVisited[curX+1][curY] = true;
	                	}
	                	if(curX - 1 >= 0 && !haveVisited[curX-1][curY]){ //add up
	                		toVisit.add((curX - 1) * y + (curY)); 
	                		haveVisited[curX-1][curY] = true;
	                	}
	                	if(curY - 1 >= 0 && !haveVisited[curX][curY-1]){ //add left
	                		toVisit.add((curX) * y + (curY - 1)); 
	                		haveVisited[curX][curY - 1] = true;
	                	}
	                    break;
	                case T_UP:
	                	if(curX - 1 >= 0 && !haveVisited[curX-1][curY]){ //add up
	                		toVisit.add((curX - 1) * y + (curY)); 
	                		haveVisited[curX-1][curY] = true;
	                		}
	                	if(curY + 1 < y && !haveVisited[curX][curY+1]){ //add right
	                		toVisit.add((curX) * y + (curY + 1)); 
	                		haveVisited[curX][curY + 1] = true;
	                	}
	                	if(curY - 1 >= 0 && !haveVisited[curX][curY-1]){ //add left
	                		toVisit.add((curX) * y + (curY - 1)); 
	                		haveVisited[curX][curY - 1] = true;
	                	}
	                    break;
	                case T_DOWN:
	                	if(curX + 1 < x && !haveVisited[curX+1][curY]){ //add down
	                		toVisit.add((curX + 1) * y + (curY)); 
	                		haveVisited[curX+1][curY] = true;
	                	}
	                	if(curY + 1 < y && !haveVisited[curX][curY+1]){ //add right
	                		toVisit.add((curX) * y + (curY + 1));
	                		haveVisited[curX][curY + 1] = true;
	                	}
	                	if(curY - 1 >= 0 && !haveVisited[curX][curY-1]){ //add left
	                		toVisit.add((curX) * y + (curY - 1)); 
	                		haveVisited[curX][curY - 1] = true;
	                	}
	                    break;
	                case EMPTY:
	                	System.out.println(
	                			"Something went wrong, went to empty square");
	                    return false;
	                default:
	                	System.out.println(
	                			"Something went wrong, unaccoutned for state");
	                	return false;
                }    
            }
        }
        boolean isGood = true;
        for(int i = 0; i < x; i++){
        	for( int j = 0; j < y; j++){
        		littleRoom currentRoom = rooms[i][j];
        		if(currentRoom.type != roomTypes.EMPTY 
        				&& !currentRoom.isBat && !currentRoom.isPit){
        			if(!haveVisited[i][j]){
        				isGood = false;
        				//System.out.println(
        				//		"Did not visit room [" + i + "][" + j +"]");
        			}
        		}
        				
        	}
        }
        return isGood;
    }

}
