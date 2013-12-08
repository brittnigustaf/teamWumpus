package eecs285.proj4.wumpus;

public class Pitfall extends Trap{
    ImageList images = new ImageList();

	
	Pitfall(Room inRoom){
		super(inRoom);
	    image = images.PIT;
	}

	
	String callHint() {
		return "You feel the wind brush against your cheek.";
	}
	

}
