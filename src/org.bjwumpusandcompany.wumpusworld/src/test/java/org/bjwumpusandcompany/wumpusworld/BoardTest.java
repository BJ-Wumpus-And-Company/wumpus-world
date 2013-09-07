package org.bjwumpusandcompany.wumpusworld;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BoardTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BoardTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(BoardTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testBoard()
    {
    	Board board = new Board(new Size(4, 4, 1));
    	assertTrue(board.getSize().getX() == 4);
    	assertTrue(board.getSize().getY() == 4);
    	
    	//TODO (WPH) : Add tests for Wumpus and Pit percept checks
    }
}
