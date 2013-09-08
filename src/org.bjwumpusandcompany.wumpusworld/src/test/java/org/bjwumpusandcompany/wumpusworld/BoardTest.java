package org.bjwumpusandcompany.wumpusworld;

import org.bjwumpusandcompany.wumpusworld.HunterPosition.Orientation;
import org.bjwumpusandcompany.wumpusworld.ModelInterface.Action;

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
     * testInitialBoard()
     */
    public void testInitialBoard()
    {
    	Board board = new Board(new Size(4, 4, 1));
    	assertTrue(board.getSize().getX() == 4);
    	assertTrue(board.getSize().getY() == 4);
    	
    	// check initial position is at 0, 0
    	assertTrue(board.getHunter().getPosition().getX() == 0);
    	assertTrue(board.getHunter().getPosition().getY() == 0);
    	
    	// check initial orientation is south
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    	
    	//TODO (WPH) : Add tests for Wumpus and Pit percept checks
    }

    /**
     * testHunterRotation()
     */
    public void testHunterRotation()
    {
    	Board board = new Board(new Size(4, 4, 1));
    	
    	// check initial orientation is south
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    	
    	board.setHunterAction(Action.TurnLeft);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.East);

    	board.setHunterAction(Action.TurnRight);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    	
    	board.setHunterAction(Action.TurnRight);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.West);
    	
    	board.setHunterAction(Action.TurnRight);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.North);

    	board.setHunterAction(Action.TurnRight);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.East);

    	board.setHunterAction(Action.TurnLeft);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.North);
    	
    	board.setHunterAction(Action.TurnLeft);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.West);

    	board.setHunterAction(Action.TurnLeft);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    }
    

    /**
     * testHunterForward()
     * This tests forward motion (and left/right turns) without checking for game state
     */
    public void testHunterForward()
    {
    	Board board = new Board(new Size(4, 4, 1));

    	assertTrue(board.getHunter().getPosition().getX() == 0);
    	assertTrue(board.getHunter().getPosition().getY() == 0);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    	
    	board.setHunterAction(Action.Forward);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    	assertTrue(board.getHunter().getPosition().getX() == 0);
    	assertTrue(board.getHunter().getPosition().getY() == 1);

    	board.setHunterAction(Action.TurnLeft);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.East);
    	assertTrue(board.getHunter().getPosition().getX() == 0);
    	assertTrue(board.getHunter().getPosition().getY() == 1);

    	board.setHunterAction(Action.Forward);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.East);
    	assertTrue(board.getHunter().getPosition().getX() == 1);
    	assertTrue(board.getHunter().getPosition().getY() == 1);

    	board.setHunterAction(Action.Forward);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.East);
    	assertTrue(board.getHunter().getPosition().getX() == 2);
    	assertTrue(board.getHunter().getPosition().getY() == 1);

    	board.setHunterAction(Action.TurnLeft);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.North);
    	assertTrue(board.getHunter().getPosition().getX() == 2);
    	assertTrue(board.getHunter().getPosition().getY() == 1);
    	
    	board.setHunterAction(Action.Forward);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.North);
    	assertTrue(board.getHunter().getPosition().getX() == 2);
    	assertTrue(board.getHunter().getPosition().getY() == 0);
    	
    	board.setHunterAction(Action.TurnLeft);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.West);
    	assertTrue(board.getHunter().getPosition().getX() == 2);
    	assertTrue(board.getHunter().getPosition().getY() == 0);

    	board.setHunterAction(Action.Forward);
    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.West);
    	assertTrue(board.getHunter().getPosition().getX() == 1);
    	assertTrue(board.getHunter().getPosition().getY() == 0);
    }
}
