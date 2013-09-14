package org.bjwumpusandcompany.wumpusworld.common;

import org.bjwumpusandcompany.wumpusworld.common.Position.Orientation;

public class HunterEntity extends Entity {
	
	private Integer  numberOfArrows;
	private Percept  currentPercept;
	
	public HunterEntity() {
		super();
		position = new Position(0, 0, Orientation.South);
		numberOfArrows = 1;
		currentPercept = new Percept();
	}	
	
	HunterEntity(int x, int y) {
		super(x, y);
		position = new Position(0, 0, Orientation.South);
		numberOfArrows = 1;
	}
	
	public Integer getNumberOfArrows() {
		return numberOfArrows;
	}
	
	public void decrementNumberOfArrows() {
		if (numberOfArrows > 0) { 
			numberOfArrows--;
		}
	}
	
	public Percept getCurrentPercept() {
		return currentPercept;
	}
	
	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {
	}

}
