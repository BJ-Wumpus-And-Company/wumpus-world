package org.bjwumpusandcompany.wumpusworld;

import org.bjwumpusandcompany.wumpusworld.common.Square;
import org.bjwumpusandcompany.wumpusworld.common.WumpusEntity;
import org.bjwumpusandcompany.wumpusworld.common.Position.Orientation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class WumpusEntityTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public WumpusEntityTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(WumpusEntityTest.class);
    }

    /**
     * testScream()
     */
    public void testScream()
    {
    	Square[][] squares = new Square[4][4];

		for (int x = 0; x < squares.length; ++x) {
			for (int y = 0; y < squares[x].length; ++y) {
				squares[x][y] = new Square();
			}
		}
		
		assertTrue(!squares[0][0].getPercepts().scream);
		assertTrue(!squares[1][1].getPercepts().scream);
		
    	WumpusEntity wumpus = new WumpusEntity(2, 0);    	
    	wumpus.arrowWasShot(0, 0, Orientation.East);

		wumpus.updateWorld(squares);
		
		assertTrue(squares[0][0].getPercepts().scream);
		assertTrue(squares[1][1].getPercepts().scream);

		wumpus.updateWorld(squares);
		
		// Make sure the scream only lasted one frame.
		assertTrue(!squares[0][0].getPercepts().scream);
		assertTrue(!squares[1][1].getPercepts().scream);
		
		wumpus.getPosition().update(2, 2); 	
    	wumpus.arrowWasShot(2, 0, Orientation.North);
    	
		wumpus.updateWorld(squares);
		assertTrue(squares[0][0].getPercepts().scream);
		assertTrue(squares[1][1].getPercepts().scream);
    }
}
