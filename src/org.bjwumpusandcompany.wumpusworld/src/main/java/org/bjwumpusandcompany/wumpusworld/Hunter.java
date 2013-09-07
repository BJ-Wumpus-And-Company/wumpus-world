package org.bjwumpusandcompany.wumpusworld;

public class Hunter {
	private HunterPosition position;
	
	public Hunter() {
		position = new HunterPosition(0, 0, HunterPosition.Orientation.South);
	}	
	
	public HunterPosition getPosition() {
		return position;
	}
	
	public void updatePosition(int x, int y) {
		position.update(x, y);
	}
}
