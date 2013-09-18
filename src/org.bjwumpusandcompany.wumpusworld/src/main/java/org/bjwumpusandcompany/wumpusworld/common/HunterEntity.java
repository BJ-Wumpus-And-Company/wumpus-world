package org.bjwumpusandcompany.wumpusworld.common;

import org.bjwumpusandcompany.wumpusworld.common.Position.Orientation;

public class HunterEntity extends Entity {
	
	private Integer  numberOfArrows;
	private boolean  bumpFlag;
	
	public HunterEntity() {
		super();
		initialize();
	}	
	
	HunterEntity(int x, int y) {
		super(x, y);
		initialize();
	}
	
	private void initialize() {
		position       = new Position(0, 0, Orientation.North);
		numberOfArrows = 1;
		bumpFlag       = false;
	}
	
	public Integer getNumberOfArrows() {
		return numberOfArrows;
	}
	
	public void decrementNumberOfArrows() {
		if (numberOfArrows > 0) { 
			numberOfArrows--;
		}
	}
	
	public void hitTheWall() {
		bumpFlag = true;
	}
	
	@Override
	protected void handleSquarePercepts(Square square, Position squarePos, Size size) {

		// If bumpFlag is set and we weren't bumping last turn
		if (bumpFlag && !square.getPercepts().bump) {
			square.getPercepts().bump = true;			
		} else {
			square.getPercepts().bump = false;
			bumpFlag = false;
		}
	}

}
