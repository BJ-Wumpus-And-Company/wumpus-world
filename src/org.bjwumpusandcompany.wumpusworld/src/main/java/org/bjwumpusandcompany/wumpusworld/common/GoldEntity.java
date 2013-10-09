package org.bjwumpusandcompany.wumpusworld.common;

public class GoldEntity extends Entity {

	private boolean active;
	
	public GoldEntity(int x, int y) {
		super(x, y);
		active = true;
	}
	
	public void deactivate() {
		active = false;
	}

	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {
		if (squarePos.getX() == position.getX() && squarePos.getY() == position.getY())	{
			square.getPercepts().glitter = active;
		}
	}

}
