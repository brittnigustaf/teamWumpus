package eecs285.proj4.wumpus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/** Purpose: dwarf constructs the map given a bluePrint.
 * It requires a blueprint: filename
 * 
 * @author Jessica DeVriese
 */

public class dwarf {
  //Member variables
  Room dungeon[][];
  FileInputStream fis;
  InputStreamReader in;
  

  
  dwarf(String Filename, Room inDungeon[][]){
    //
    
    try {
      fis = new FileInputStream(Filename);
    } catch (FileNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
      in = new InputStreamReader(fis, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
