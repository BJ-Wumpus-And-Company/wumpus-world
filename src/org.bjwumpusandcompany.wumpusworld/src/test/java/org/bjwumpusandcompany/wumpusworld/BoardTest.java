package org.bjwumpusandcompany.wumpusworld;

import org.bjwumpusandcompany.wumpusworld.HunterPosition.Orientation;
import org.bjwumpusandcompany.wumpusworld.ModelInterface.Action;
import org.bjwumpusandcompany.wumpusworld.ModelInterface.GameState;

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
		System.out.println("Running: testInitialBoard()");
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
		System.out.println("Running: testHunterRotation()");
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
		System.out.println("Running: testHunterForward()");
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
    
    /**
     * testGameStateEnded()
     * Iterate across board with hunter until we find a pit, verify game ends
     * Iterate across board with hunter until we find a wumpus, verify game ends
     */
    public void testGameStateEnded()
    {
		System.out.println("Running: testGameStateEnded()");
		
    	//Look For Pit
    	Board board = new Board(new Size(4, 4, 1));
    	
    	assertTrue(board.getGameState() != GameState.Ended);

    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    	assertTrue(board.getHunter().getPosition().getX() == 0);
    	assertTrue(board.getHunter().getPosition().getY() == 0);
    	
    	boolean foundPit = false;
    	for (int x = 0; x < board.getSize().getX() && !foundPit; ++x) {
    		for (int y = 0; y < board.getSize().getY(); ++y) {
            	board.setHunterAction(Action.Forward);
            	
            	int hunterX = board.getHunter().getPosition().getX();
            	int hunterY = board.getHunter().getPosition().getY();

            	//TODO (WPH) : It won't always make it into this conditional
            	//             because sometimes it hits a wumpus first.            	
            	if (board.getWorld()[hunterX][hunterY].isPit()) {
            		System.out.println(" -- Found Pit");
            		assertTrue(board.getGameState() == GameState.Ended);
            		foundPit = true; //use foundPit to avoid outer loop
            		break;
            	}
    		}
    		
    		if (x == 0 || x % 2 == 0) {
            	board.setHunterAction(Action.TurnLeft);
            	board.setHunterAction(Action.Forward);
            	board.setHunterAction(Action.TurnLeft);
    		} else {
            	board.setHunterAction(Action.TurnRight);
            	board.setHunterAction(Action.Forward);
            	board.setHunterAction(Action.TurnRight);
    		}    		
    	}
    	
    	//Look For Wumpus
    	board = new Board(new Size(4, 4, 1));
    	
    	assertTrue(board.getGameState() != GameState.Ended);

    	assertTrue(board.getHunter().getPosition().getOrientation() == Orientation.South);
    	assertTrue(board.getHunter().getPosition().getX() == 0);
    	assertTrue(board.getHunter().getPosition().getY() == 0);
    	
    	boolean foundWumpus = false;
    	for (int x = 0; x < board.getSize().getX() && !foundWumpus; ++x) {
    		for (int y = 0; y < board.getSize().getY(); ++y) {
            	board.setHunterAction(Action.Forward);
            	
            	int hunterX = board.getHunter().getPosition().getX();
            	int hunterY = board.getHunter().getPosition().getY();
            	
            	//TODO (WPH) : It won't always make it into this conditional
            	//             because sometimes it hits a pit first.
            	if (board.getWorld()[hunterX][hunterY].isWumpus()) {
            		System.out.println(" -- Found Wumpus");
            		assertTrue(board.getGameState() == GameState.Ended);
            		foundWumpus = true; //use foundPit to avoid outer loop
            		break;
            	}
    		}
    		
    		if (x == 0 || x % 2 == 0) {
            	board.setHunterAction(Action.TurnLeft);
            	board.setHunterAction(Action.Forward);
            	board.setHunterAction(Action.TurnLeft);
    		} else {
            	board.setHunterAction(Action.TurnRight);
            	board.setHunterAction(Action.Forward);
            	board.setHunterAction(Action.TurnRight);
    		}    		
    	}
    }
}
