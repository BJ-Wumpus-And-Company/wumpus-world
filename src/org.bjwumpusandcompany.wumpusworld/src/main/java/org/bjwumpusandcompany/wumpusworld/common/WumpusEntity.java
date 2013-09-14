package org.bjwumpusandcompany.wumpusworld.common;

import org.bjwumpusandcompany.wumpusworld.common.Position.Orientation;

public class WumpusEntity extends Entity {

	public WumpusEntity(int x, int y) {
		super(x, y);
	}

	public void arrowWasShot(int x, int y, Orientation direction) {
		//TODO (WPH) : Implement scream here
	}
	
	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {
		if (isNeighbor(squarePos)) {
			square.getPercepts().stench = true;
		}	
	}

}
