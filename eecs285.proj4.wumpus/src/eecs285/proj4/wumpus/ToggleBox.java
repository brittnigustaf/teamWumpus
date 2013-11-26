package eecs285.proj4.wumpus;

/**Purpose: The ToggleBox type allows for the storage of two arrays together
 * one has important information while the other is a boolean
 * This allows the use to store information but only use portions of it
 * 
 * @author: Jessica DeVriese
*/

public class ToggleBox < DataType > {
  //protected class members
  protected DataType[] box;
  protected boolean[] toggles;
  int length;
  
  ToggleBox(DataType[] inBox){
    //EFF: initializes a box
    
    initialize(inBox);
  }
  
  ToggleBox(DataType[] inBox, boolean[] inMesh){
    //EFF: initilizes the entire datatype
    
    box = inBox;
    toggles = inMesh;

  }
  
  protected void initialize(DataType[] inBox){
    //EFF: creates an instance of ToggleWord
    //MOD: box, toggles, length
    
    box = inBox;
    toggles = new boolean[inBox.length];
    for(int i =0; i<toggles.length; i++){
      toggles[i] = false;
    }
    length = box.length;
  }
  
  void setAll(boolean condition){
    //EFF: sets all boolean values to condition
    //MOD: toggles
    
    for(int i=0; i<toggles.length; i++){
      toggles[i] = condition;
    }
  }

  protected boolean checkLet(String letter){
    //EFF: returns true if letter is non-alphanumeric except spaces
    
    return letter.matches("^.*[^a-zA-Z0-9 ].*$");
  }
  
  DataType getIndex(int index){
    //EFF: returns index value if toggle is true
    //REQ: index to be valid
    
    if(toggles[index]){
      return box[index];
    }
    else {
      return null;
    }
  }
  
  void toggle(int index){
    //EFF: reverses the boolean of integer index
    //MOD: toggles[index]
    //REQ: index to be valid
    
    toggles[index] = !toggles[index];
  }
  
  void toggle(DataType index){
    //EFF: reverses the boolean of string index
    //MOD: all toggles with string index
    
    for(int i=0; i<length; i++){
      if(index.equals(box[i])) toggle(i);
    }
  }
  
  int find(DataType Index){
    //EFF: returns number of instances of index
    
    int count = 0;
    for(int i=0; i<length; i++){
      if(Index.equals(box[i])){
        count++;
      }
    }
    return count;
  }
}
