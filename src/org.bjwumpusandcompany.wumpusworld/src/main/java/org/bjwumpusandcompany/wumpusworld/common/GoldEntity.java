package org.bjwumpusandcompany.wumpusworld.common;

public class GoldEntity extends Entity {

	public GoldEntity(int x, int y) {
		super(x, y);
	}

	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {
		if (squarePos.getX() == position.getX() && squarePos.getY() == position.getY())	{
			square.getPercepts().glitter = true;
		}
	}

}
