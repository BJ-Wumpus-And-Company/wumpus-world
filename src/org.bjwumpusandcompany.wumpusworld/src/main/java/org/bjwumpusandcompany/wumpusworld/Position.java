package org.bjwumpusandcompany.wumpusworld;

public class Position {
	
	protected int x;
	protected int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
