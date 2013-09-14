package org.bjwumpusandcompany.wumpusworld.common;

public class GoldEntity extends Entity {

	public GoldEntity(int x, int y) {
		super(x, y);
	}

	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {
		if (isNeighbor(squarePos)) {
			square.getPercepts().glitter = true;
		}
	}

}
