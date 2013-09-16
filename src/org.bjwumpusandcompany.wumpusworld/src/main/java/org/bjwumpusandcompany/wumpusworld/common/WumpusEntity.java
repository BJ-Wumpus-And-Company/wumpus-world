package org.bjwumpusandcompany.wumpusworld.common;

import org.bjwumpusandcompany.wumpusworld.common.Position.Orientation;

public class WumpusEntity extends Entity {

	private boolean screamFlag;
	
	public WumpusEntity(int x, int y) {
		super(x, y);
		screamFlag = false;
	}

	public void arrowWasShot(int x, int y, Orientation direction) {
		screamFlag = false;
		
		if (x == position.getX()) {
			if (y > position.getY() && direction == Orientation.South) {
				screamFlag = true;
			} else if (y < position.getY() && direction == Orientation.North) {
				screamFlag = true;
			}
		} else if (y == position.getY()) {
			if (x > position.getX() && direction == Orientation.West) {
				screamFlag = true;				
			} else if (x < position.getX() && direction == Orientation.East) {
				screamFlag = true;				
			}
		}
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
