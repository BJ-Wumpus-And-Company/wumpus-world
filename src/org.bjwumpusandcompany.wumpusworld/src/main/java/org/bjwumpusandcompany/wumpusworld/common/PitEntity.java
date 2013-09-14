package org.bjwumpusandcompany.wumpusworld.common;

public class PitEntity extends Entity {

	public PitEntity(int x, int y) {
		super(x, y);
	}

	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {	
		if (isNeighbor(squarePos)) {
			square.getPercepts().breeze = true;
		}	
	}

}
