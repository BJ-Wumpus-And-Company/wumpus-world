package org.bjwumpusandcompany.wumpusworld;

public class HunterPosition extends Position {
	
    public enum Orientation {
    	North, West, South, East;
    	
        public Orientation successor() {
        	return values()[(ordinal() + 1) % values().length];
        }
        
        public Orientation predeccessor() {
        	// need this ternary statement to make the enums circular from right to left 
            return values()[(this == North ? East.ordinal() : ordinal() - 1)];
        }
    };
	
	private Orientation orientation;
	
	public HunterPosition(int x, int y, Orientation orientation) {
		super(x, y);
		
		this.orientation = orientation;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void rotateOrientationClockwise() {
		orientation = orientation.predeccessor();
	}
	
	public void rotateOrientationCounterClockwise() {
		orientation = orientation.successor();
	}
}
