package org.bjwumpusandcompany.wumpusworld.common;

public class WumpusEntity extends Entity {

	public WumpusEntity(int x, int y) {
		super(x, y);
	}

	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {
		if (isNeighbor(squarePos)) {
			square.getPercepts().stench = true;
		}	
	}

}
