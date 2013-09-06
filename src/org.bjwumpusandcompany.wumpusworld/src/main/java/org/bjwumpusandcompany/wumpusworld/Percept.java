package org.bjwumpusandcompany.wumpusworld;

public class Percept {

	public boolean stench  = false;
	public boolean breeze  = false;
	public boolean glitter = false;
	public boolean bump    = false;
	public boolean scream  = false;
	
	public Percept(boolean stench, boolean breeze, boolean glitter, boolean bump, boolean scream) {
		this.stench  = stench;
		this.breeze  = breeze;
		this.glitter = glitter;
		this.bump 	 = bump;
		this.scream  = scream;
	}
	
	public Percept() {
	}
}
