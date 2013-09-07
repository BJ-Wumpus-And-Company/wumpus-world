package org.bjwumpusandcompany.wumpusworld;

import java.util.Random;

//import org.codehaus.jettison.json.JSONObject;

public class Board implements ModelInterface {

	private Size size;
	private Square[][] squares; //TODO - Add Third Dimension
	private Hunter hunter;
	
	public Board(Size size) {
		this.size = size;
		
		hunter = new Hunter();
		
		squares = new Square[size.getY()][size.getX()];
		
		// Initialize board
		for (int x = 0; x < squares.length; ++x) {
			for (int y = 0; y < squares[x].length; ++y) {
				squares[x][y] = new Square(false, false, false);
				
				Random randPit = new Random();
				if (randPit.nextDouble() <= 0.2 &&
					!(x == hunter.getPosition().getX() &&
					  y == hunter.getPosition().getY())) { //with 20%
					squares[x][y] = new Square(true, false, false); //place pit
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
        
        squares[position.getX()][position.getY()].setWumpus();

        //Place Gold - If square does not have a Wumpus, Pit or Hunter already
		while (squares[position.getX()][position.getY()].isWumpus() ||
			   squares[position.getX()][position.getY()].isPit() ||
			   (position.getX() == hunter.getPosition().getX() &&
			    position.getY() == hunter.getPosition().getY())) {
	        position = new Position(randX.nextInt(size.getY()), randY.nextInt(size.getX()));
		}
		squares[position.getX()][position.getY()].setGold();
		
		generateStaticPercepts();
	}
	
	private void generateStaticPercepts() {
		for (int x = 0; x < squares.length; ++x) {
			for (int y = 0; y < squares[x].length; ++y) {
				generateStaticPercepts(squares[x][y], new Position(x, y));
			}
		}
	}
	
	private void generateStaticPercepts(Square square, Position position) {
		square.getPercepts().glitter = square.isGold();
		
		if (position.getX() > 0) {
			if (!squares[position.getX() - 1][position.getY()].getPercepts().stench) {
				squares[position.getX() - 1][position.getY()].getPercepts().stench = square.isWumpus();
			}
			
			if (!squares[position.getX() - 1][position.getY()].getPercepts().breeze) {
				squares[position.getX() - 1][position.getY()].getPercepts().breeze = square.isPit();
				
			}
		}
		
		if (position.getX() < size.getX() - 1) {
			if (!squares[position.getX() + 1][position.getY()].getPercepts().stench) {
				squares[position.getX() + 1][position.getY()].getPercepts().stench = square.isWumpus();
			}
			
			if (!squares[position.getX() + 1][position.getY()].getPercepts().breeze) {
				squares[position.getX() + 1][position.getY()].getPercepts().breeze = square.isPit();				
			}
		}
		
		if (position.getY() > 0) {
			if (!squares[position.getX()][position.getY() - 1].getPercepts().stench) {
				squares[position.getX()][position.getY() - 1].getPercepts().stench = square.isWumpus();				
			}
			
			if (!squares[position.getX()][position.getY() - 1].getPercepts().breeze) {
				squares[position.getX()][position.getY() - 1].getPercepts().breeze = square.isPit();
				
			}
		}
		
		if (position.getY() < size.getY() - 1) {
			if (!squares[position.getX()][position.getY() + 1].getPercepts().stench) {
				squares[position.getX()][position.getY() + 1].getPercepts().stench = square.isWumpus();				
			}
			
			if (!squares[position.getX()][position.getY() + 1].getPercepts().breeze) {
				squares[position.getX()][position.getY() + 1].getPercepts().breeze = square.isPit();				
			}
		}
	}
	
	public Size getSize() {
		return size;
	}
	
	public String toString() {
		String output = "";
		String delimiter = "-----------------------------";
		
		for (int x = 0; x < squares.length; ++x) {
			output += "\n";
			output += delimiter;
			output += "\n|";
			for (int y = 0; y < squares[x].length; ++y) {
				String cell = "";
				if (squares[x][y].isGold()) {
					cell += "G";
				} else if (squares[x][y].isPit()) {
					cell += "P";
				} else if (squares[x][y].isWumpus()) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameState getGameState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Percept getCurrentPercept() {
		return squares[hunter.getPosition().getX()][hunter.getPosition().getY()].getPercepts();
	}

	@Override
	public void setHunterAction(Action action) {
		// TODO Auto-generated method stub
	}

	@Override
	public Square[][] getWorld() {
		return squares.clone();
	}

	@Override
	public Hunter getHunter() {
		// TODO Auto-generated method stub
		return null;
	}
}
