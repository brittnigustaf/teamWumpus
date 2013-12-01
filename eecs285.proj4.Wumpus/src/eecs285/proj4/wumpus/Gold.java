package eecs285.proj4.wumpus;

public class Gold extends Trap{
	
	Gold(Room inRoom){
		super(inRoom);
	}

	String callHint() {
		return "You smell a soft metallic sent.";
	}

}
