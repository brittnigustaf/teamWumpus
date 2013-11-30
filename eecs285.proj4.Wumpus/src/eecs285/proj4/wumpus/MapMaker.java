package eecs285.proj4.wumpus;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
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
    
    public static int main(String [] args){
        Scanner scanner = new Scanner (System.in);
        System.out.println(
        		"Please input the dimensions of the map in the form: X Y");
        String input = scanner.next(); // Get what the user types.
        
        
        input = input.trim();
        String dimensions[] = input.split(" ");
        
        System.out.println(
        		"Please input the number of pit-traps you'd like:");
        input = scanner.next(); // Get what the user types.
        
        input = input.trim();
        
        int numPits = Integer.parseInt(input);
        
        System.out.println(
        		"Please input the number of bat-traps you'd like:");
        input = scanner.next(); // Get what the user types.
        
        input = input.trim();
        
        int numBats = Integer.parseInt(input);
        
        scanner.close();

        if(dimensions.length != 2){
            return -1;
        }
        int x;
        int y;
        try{
            x = Integer.parseInt(dimensions[0]);
            y = Integer.parseInt(dimensions[1]);
        }catch(Exception e){
            return -1;
        }
        int size = x * y;
        if(size <= 0){
            return -1;
        }
        String[][] map = new String[x][y];
        littleRoom[][] rooms = new littleRoom[x][y];

        
        Random rando = new Random();
        
        int start1 = rando.nextInt() % size;
        
        int start1x = start1 / x;
        int start1y = start1 % x;
        
        map[start1x][start1y] += "1"; // set that square to be the 
        							  //starting square for P1
        rooms[start1x][start1y].P1Start = true;

        
        int start2;
        do{ //make sure start2 and start1 aren't the same square;
            start2 = rando.nextInt() % size;
        }
        while(start2 == start1);
        
        int start2x = start2 / x;
        int start2y = start2 % x;
        
        map[start2x][start2y] += "2"; // set that square to be the 
         							  //starting square for P2
        rooms[start2x][start2y].P2Start = true;
     
        int wumpusSquare;
        do{ //make sure start2 and start1 aren't the same square;
            wumpusSquare = rando.nextInt() % size;
        }
        while(wumpusSquare == start2 || wumpusSquare == start1);
        
        int wumpusx = wumpusSquare / x;
        int wumpusy = wumpusSquare % x;
        
        map[wumpusx][wumpusy] += "W"; // set that square to be the 
        							  //starting square for the wumpus
        rooms[wumpusx][wumpusy].wumpusStart = true;
        
        Integer batSquares[] = new Integer[numBats];
        int numFound = 0;
        for(int i = 0; i < numBats; i++){
	        do{ //make sure start2 and start1 aren't the same square;
	            batSquares[numFound] = rando.nextInt() % size;
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
	            pitSquares[numFound] = rando.nextInt() % size;
	        }
	        while(pitSquares[numFound] == start1 ||
	        		pitSquares[numFound] == start2);
        
	        int pitx = pitSquares[numFound] / x;
	        int pity = pitSquares[numFound] % x;
	        
	        numFound++;
	        
	        map[pitx][pity] += "b"; // set that square to have a pit
	        rooms[pitx][pity].isPit = true;
        }
        
        
        while(!isPath(map, rooms)){
            generateMap(map,rooms);
        }
        
        printMap(map,rooms);
        
        return 0;
        
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

    private static void generateMap(String[][] map, littleRoom[][] rooms) {
    	//generates a random map for hunt the wumpus 
    	//given the dimensions of the map.
    	int x = map.length;
    	int y = map[0].length;
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
    			}
    			if(i+1 < x && rooms[i+1][j].type != roomTypes.EMPTY){
    				if(rooms[i+1][j].canGo.contains(Direction.UP) ){
    					requires.add(Direction.DOWN);
    					roomOptions.retainAll(down);
    				}
    			}
    			if(j-1 >= 0 && rooms[i][j-1].type != roomTypes.EMPTY){
    				if(rooms[i][j-1].canGo.contains(Direction.RIGHT) ){
    					requires.add(Direction.LEFT);
    					roomOptions.retainAll(left);
    				}
    			}
    			if(i -1 >= 0 && rooms[i-1][j].type != roomTypes.EMPTY){
    				if(rooms[i-1][j].canGo.contains(Direction.DOWN) ){
    					requires.add(Direction.UP);
    					roomOptions.retainAll(up);
    				}
    			}
    			
    			
    			//finally, pick one of the remaining options
    			//at random and assign it
    		}
    	}
    }

	private static boolean isPath(String[][] map, littleRoom[][] rooms) {
        int x = map.length;
        int y = map[0].length;
        int p1Loc = -1;
        int p2Loc = -1;
        int wumpusLoc = -1;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if (map[i][j].contains("1"))
                    p1Loc = i*y + j;
                if(map[i][j].contains("2"))
                    p2Loc = i*y + j;
                if(map[i][j].contains("W"))
                    wumpusLoc = i*y + j;
            }
        }
        
        Queue <Integer> toVisit = new ArrayDeque<Integer>();
        Boolean[][] haveVisited = new Boolean[x][y];
        
        toVisit.add(p1Loc);
        
        while(toVisit.size() > 0){
            Integer curRoom = toVisit.remove();
            Integer curX = curRoom / x;
            Integer curY = curRoom % x;
            littleRoom currentRoom = rooms[curX][curY];
            if(!haveVisited[curX][curY] && 
            		!currentRoom.isPit && !currentRoom.isBat){
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
        				System.out.println(
        						"Did not visit room [" + x + "][" + y +"]");
        			}
        		}
        				
        	}
        }
        if(isGood)
        	return true;
        else
        	return false;
    }

}
