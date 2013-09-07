package org.bjwumpusandcompany.wumpusworld;

public class Hunter {
	private Position position;
	
	public Hunter() {
		position = new Position(0, 0);
	}	
	
	public Position getPosition() {
		return position;
	}
	
	public void updatePosition(int x, int y) {
		position.update(x, y);
	}
}
