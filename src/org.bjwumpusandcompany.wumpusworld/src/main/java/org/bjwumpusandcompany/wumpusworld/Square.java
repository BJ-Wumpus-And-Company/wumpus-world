package org.bjwumpusandcompany.wumpusworld;

public class Square {
	
	private boolean pit    = false;
	private boolean gold   = false;
	private boolean wumpus = false;
	
	private Percept percepts;
	
	public Square(boolean pit, boolean gold, boolean wumpus) {
		this.pit    = pit;
		this.gold   = gold;
		this.wumpus = wumpus;
	}
	
	public Square() {
	}
	
	public boolean isPit() {
		return pit;
	}
	
	public boolean isGold() {
		return gold;
	}
	
	public boolean isWumpus() {
		return wumpus;
	}
	
	public void setWumpus() {
		wumpus = true;
	}
	
	public void setGold() {
		gold = true;
	}
	
	public Percept getPercepts() {
		return percepts;
	}
	
	public void setPercept(Percept percepts) {
		this.percepts = percepts;
	}
	
	public String toString() {
		return "Square:TODO";
	}
}
