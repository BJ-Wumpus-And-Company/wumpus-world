/**
 * Copyright (c) 2013 Jonathan McCluskey and William Harding.
 *
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE. 
 */

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
		
		this.percepts = new Percept(false, false, false, false, false);
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
