package eecs285.proj4.wumpus;

public class Pitfall extends Trap{
	
	Pitfall(Room inRoom){
		super(inRoom);
	}

	
	String callHint() {
		return "You feel the wind brush against your cheek.";
	}
	

}
