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
