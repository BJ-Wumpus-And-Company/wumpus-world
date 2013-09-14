package org.bjwumpusandcompany.wumpusworld.common;

import org.bjwumpusandcompany.wumpusworld.common.Position.Orientation;

public class WumpusEntity extends Entity {

	private boolean screamFlag;
	
	public WumpusEntity(int x, int y) {
		super(x, y);
		screamFlag = false;
	}

	public void arrowWasShot(int x, int y, Orientation direction) {
		//TODO (WPH) : Implement scream logic here:
		//             if in same row/col and direction is toward me
		screamFlag = true;
	}
	
	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {
		if (isNeighbor(squarePos)) {
			square.getPercepts().stench = true;
		}
		
		// If screamFlag is set and we weren't screaming last turn
		if (screamFlag && !square.getPercepts().scream) {
			square.getPercepts().scream = true;			
		} else {
			square.getPercepts().scream = false;
			screamFlag = false;
		}
	}

}
