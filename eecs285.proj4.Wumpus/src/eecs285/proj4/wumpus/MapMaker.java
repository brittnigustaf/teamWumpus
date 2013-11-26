package eecs285.proj4.wumpus;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

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
    
    private class littleRoom{
        roomTypes type;
    }
    
    public static int main(String [] args){
        Scanner scanner = new Scanner (System.in);
        System.out.println("Please input the dimensions of the map in the form: X Y");
        String input = scanner.next(); // Get what the user types.
        
        
        input = input.trim();
        String dimensions[] = input.split(" ");
        
        System.out.println("Please input the number of pit-traps you'd like:");
        input = scanner.next(); // Get what the user types.
        
        input = input.trim();
        
        int numPits = Integer.parseInt(input);
        
        System.out.println("Please input the number of bat-traps you'd like:");
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
        
        map[start1x][start1y] += "1"; // set that square to be the starting square for P1
        
        int start2;
        do{ //make sure start2 and start1 aren't the same square;
            start2 = rando.nextInt() % size;
        }
        while(start2 == start1);
        
        int start2x = start2 / x;
        int start2y = start2 % x;
        
        map[start2x][start2y] += "2"; // set that square to be the starting square for P2

        int wumpusSquare;
        do{ //make sure start2 and start1 aren't the same square;
            wumpusSquare = rando.nextInt() % size;
        }
        while(wumpusSquare == start2 || wumpusSquare == start1);
        
        int wumpusx = wumpusSquare / x;
        int wumpusy = wumpusSquare % x;
        
        map[start2x][start2y] += "W"; // set that square to be the starting square for the wumpus
        
        while(!isPath(map, rooms)){
            generateMap(map,rooms);
        }
        
        printMap(map,rooms);
        
        return 0;
        
    }

    private static void printMap(String[][] map, littleRoom[][] rooms) {
        // TODO Auto-generated method stub
        
    }

    private static void generateMap(String[][] map, littleRoom[][] rooms) {
        // TODO Auto-generated method stub
        
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
        toVisit.add(p2Loc);
        toVisit.add(wumpusLoc);
        
        while(toVisit.size() > 0){
            Integer curRoom = toVisit.remove();
            Integer curX = curRoom / x;
            Integer curY = curRoom % x;
            if(!haveVisited[curX][curY]){
                switch (rooms[curX][curY].type){ //add appropriate things to queue
                case CROSS:
                    break;
                case UP_RIGHT:
                    break;
                case UP_LEFT:
                    break;
                case UP_DOWN:
                    break;
                }
                    
                
            }
        }
            
        return false;
    }

}
