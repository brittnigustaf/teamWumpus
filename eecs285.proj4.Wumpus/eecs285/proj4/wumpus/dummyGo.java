package eecs285.proj4.wumpus;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class dummyGo {
  
  Dwarf Urist;
  JPanel board;
  JFrame window;
  Player player;
  Player playerList[];
  
  dummyGo(){
    window = new JFrame();
    board = new JPanel(new GridLayout(8,8));
    player = new Player();
    playerList = new Player[1];
    playerList[0] = player;
    Urist = new Dwarf(playerList);
    
    for(int i=0;i<Urist.row;i++){
      for(int j=0; j<Urist.col;j++){
        Urist.dungeon[i][j].reveal();
        board.add(Urist.dungeon[i][j].panel);
      }
    }
    
    window.add(board);
    window.pack();
    window.setVisible(true);
  }

}
