package org.bjwumpusandcompany.wumpusworld.common;

import org.bjwumpusandcompany.wumpusworld.EntityInterface;

public abstract class Entity implements EntityInterface {
	protected Position position;
	
	Entity(int x, int y) {
		position = new Position(x, y);
	}
	
	Entity() {
		position = new Position(0, 0);
	}
		
	protected boolean isNeighbor(Position squarePos) {
		return Math.abs(position.getX() - squarePos.getX()) == 1 &&
		       Math.abs(position.getY() - squarePos.getY()) == 1;
	}
	 
//	protected boolean checkBounds(Position position, Size size) {
//		return ((position.getX() > 0) && (position.getX() < size.getX() - 1) &&
//				(position.getY() > 0)	&& (position.getY() < size.getY() - 1));
//	}

	@Override
	public void updateWorld(Square[][] squares) {
		for (int x = 0; x < squares.length; ++x) {
			for (int y = 0; y < squares[x].length; ++y) {
				handleSquarePercepts(squares[x][y], 
									 new Position(x, y), 
									 new Size(squares.length, squares[0].length, 0));
			}
		}
	}

	@Override
	public Position getPosition() {
		return position;
	}
	
	protected abstract void handleSquarePercepts(Square square, Position squarePos, Size size);
}
