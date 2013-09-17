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

package org.bjwumpusandcompany.wumpusworld.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.bjwumpusandcompany.wumpusworld.EntityInterface;
import org.bjwumpusandcompany.wumpusworld.ModelInterface;
import org.bjwumpusandcompany.wumpusworld.common.GoldEntity;
import org.bjwumpusandcompany.wumpusworld.common.HunterEntity;
import org.bjwumpusandcompany.wumpusworld.common.Percept;
import org.bjwumpusandcompany.wumpusworld.common.PitEntity;
import org.bjwumpusandcompany.wumpusworld.common.Position;
import org.bjwumpusandcompany.wumpusworld.common.Size;
import org.bjwumpusandcompany.wumpusworld.common.Square;
import org.bjwumpusandcompany.wumpusworld.common.WumpusEntity;
import org.bjwumpusandcompany.wumpusworld.common.observer.AbstractSubject;

//import org.codehaus.jettison.json.JSONObject;

/**
*
* 
* @author William Harding
*
*/
public class Model extends AbstractSubject<ModelInterface> implements ModelInterface {

	private Size size;
	private Square[][] squares; //TODO - Add Third Dimension
	private HunterEntity hunter;
	private WumpusEntity wumpus;
	private GameState gameState;
	private ArrayList<EntityInterface> staticEntities;
	
	public Model(Size size) {
		this.size = size;
		
		staticEntities  = new ArrayList<EntityInterface>();
		hunter = new HunterEntity();
		
		gameState = GameState.Initialize;
		
		squares = new Square[size.getY()][size.getX()];
		
		// Initialize board
		for (int x = 0; x < squares.length; ++x) {
			for (int y = 0; y < squares[x].length; ++y) {
				squares[x][y] = new Square();
				
				Random randPit = new Random();
				if (randPit.nextDouble() <= 0.2 &&
					!(x == hunter.getPosition().getX() &&
					  y == hunter.getPosition().getY())) { //with 20%
					staticEntities.add(new PitEntity(x, y));
				}
			}
		}	
		
		Random randX = new Random();
		Random randY = new Random();

		//Place Wumpus
        Position position = new Position(randX.nextInt(size.getY()), randY.nextInt(size.getX()));
        while (position.getX() == hunter.getPosition().getX() &&
			   position.getY() == hunter.getPosition().getY()) {
        	position = new Position(randX.nextInt(size.getY()), randY.nextInt(size.getX()));
        }
        
        wumpus = new WumpusEntity(position.getX(), position.getY());

        //Place Gold - If square does not have a Wumpus, Pit or Hunter already
		while ((wumpus.getPosition().getX() == position.getX() &&
			    wumpus.getPosition().getY() == position.getY()) ||
			   isPitLocation(position) ||
			   (position.getX() == hunter.getPosition().getX() &&
			    position.getY() == hunter.getPosition().getY())) {
	        position = new Position(randX.nextInt(size.getY()), randY.nextInt(size.getX()));
		}
		
		staticEntities.add(new GoldEntity(position.getX(), position.getY()));
		
		generateStaticPercepts();
	}
	
	private void generateStaticPercepts() {
		Iterator<EntityInterface> it = staticEntities.iterator();
		while (it.hasNext()) {
			it.next().updateWorld(squares);
		}
		
		checkForEndOfGame();
	}
	
	public Size getSize() {
		return size;
	}
	
	public boolean isPitLocation(Position position) {
		boolean sameLocation = false;
		for (EntityInterface entity : staticEntities) {
			if (entity instanceof PitEntity) {
				sameLocation = entity.getPosition().getX() == position.getX() &&
							   entity.getPosition().getY() == position.getY();
				
				if (sameLocation) {
					break;
				}
			}
		}
		
		return sameLocation;
	}
	
	public boolean isGoldLocation(Position position) {
		boolean sameLocation = false;
		for (EntityInterface entity : staticEntities) {
			if (entity instanceof GoldEntity) {
				sameLocation = entity.getPosition().getX() == position.getX() &&
							   entity.getPosition().getY() == position.getY();
				
				if (sameLocation) {
					break;
				}
			}
		}
		
		return sameLocation;
	}
	
	public void checkForEndOfGame() {
		int hunterX = hunter.getPosition().getX();
		int hunterY = hunter.getPosition().getY();
		
//		boolean endOfGame = squares[hunterX][hunterY].isPit() || 
//							squares[hunterX][hunterY].isWumpus();
//		if (endOfGame) {
//			gameState = GameState.Ended;
//		}
	}
	
	public String toString() {
		String output = "";
		String unitDel = "-------";
		String delimiter = "";
		
		while (!(delimiter.length()/6 > size.getX())) {
			delimiter += unitDel;
		}
		
		for (int x = squares.length - 1; x >= 0; --x) {
			output += "\n";
			output += delimiter;
			output += "\n|";
			for (int y = 0; y < squares[x].length; ++y) {
				String cell = "";
				Position pos = new Position(x, y);
				if (isGoldLocation(pos)) {
					cell += "G";
				} else if (isPitLocation(pos)) {
					cell += "P";
				} else if (pos.getX() == wumpus.getPosition().getX() &&
						   pos.getY() == wumpus.getPosition().getY()) {
					cell += "W";
				}
				
				if (squares[x][y].getPercepts().breeze) {
					cell += "b";
				}
				
				if (squares[x][y].getPercepts().stench) {
					cell += "s";
				}
				
				if (squares[x][y].getPercepts().glitter) {
					cell += "g";
				}
				
				if (x == hunter.getPosition().getX() &&
					y == hunter.getPosition().getY()) {
					cell += "H";
				}
				
				// Pad to 6 chars
				while (cell.length() < 6) {
					cell += " ";
				}
				
				output += cell + "|";
			}
		}
		output += "\n";
		output += delimiter;
		output += "\n";
		output += "\n";
		
		return output;
	}

	@Override
	public void setGameState(GameState state) {
		if (state == GameState.Started) {
			if (gameState != GameState.Initialize ||
				state == GameState.Ended) {
				System.out.println("setGameState(): Illegal State Transition Request, From: " + 
								   gameState.name() + " To: " + state.name());
			} else {
				gameState = state;
			}
		}
		
		notifyObservers(this);
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	@Override
	public Percept getCurrentPercept() {
		int hunterX = hunter.getPosition().getX();
		int hunterY = hunter.getPosition().getY();
		
		Percept currentPercept = squares[hunterX][hunterY].getPercepts();
		
        return currentPercept;
	}

	@Override
	public void setHunterAction(Action action) {
		int hunterX = hunter.getPosition().getX();
		int hunterY = hunter.getPosition().getY();
		
		switch (action)
		{
		case Forward:
			switch (hunter.getPosition().getOrientation())
			{
			case North:
				if (hunterY  < size.getY() - 1) {
					hunter.getPosition().update(hunterX, hunterY + 1);
				} else {
					hunter.hitTheWall();
				}
				break;
			case West:
				if (hunterX > 0) {
					hunter.getPosition().update(hunterX - 1, hunterY);
				} else {
					hunter.hitTheWall();
				}
				break;
			case South:
				if (hunterY > 0) {
					hunter.getPosition().update(hunterX, hunterY - 1);
				} else {
					hunter.hitTheWall();
				}
				break;
			case East:
				if (hunterX < size.getX() - 1) {
					hunter.getPosition().update(hunterX + 1, hunterY);
				} else {
					hunter.hitTheWall();
				}
				break;
			default:
				throw new IllegalArgumentException("setHuntAction: Unknown Orientation");
			}
			break;
		case TurnLeft:
			hunter.getPosition().rotateOrientationCounterClockwise();
			break;
		case TurnRight:
			hunter.getPosition().rotateOrientationClockwise();
			break;
		case Grab:
			//TODO (WPH)
			break;
		case Shoot:
			//Right now, Wumpus will determine whether the arrow will hit him
			wumpus.arrowWasShot(hunter.getPosition().getX(), hunter.getPosition().getY(), hunter.getPosition().getOrientation());
			break;
		case Climb:
			//TODO (WPH)
			break;
		default:
			throw new IllegalArgumentException("setHunterAction: Unknown Action");
		}
		
		checkForEndOfGame();

		wumpus.updateWorld(squares);
		hunter.updateWorld(squares);
		
		notifyObservers(this);
	}

	@Override
	public Square[][] getWorld() {
		return squares.clone();
	}

	@Override
	public HunterEntity getHunter() {
		return hunter;
	}
}
