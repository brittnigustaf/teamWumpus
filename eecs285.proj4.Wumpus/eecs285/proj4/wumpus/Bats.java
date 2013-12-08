package eecs285.proj4.wumpus;

public class Bats extends Trap{
    ImageList images = new ImageList();
	
	Bats(Room inRoom){
		super(inRoom);
	    image = images.BAT;

	}

	String callHint() {
		return "You hear soft fluttering";
	}

}
