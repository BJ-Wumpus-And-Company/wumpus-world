package org.bjwumpusandcompany.wumpusworld;

import java.util.Random;

//import org.codehaus.jettison.json.JSONObject;

public class Board {

	private Size size;
	private Square[][] squares; //TODO - Add Third Dimension
	private Position playerPosition;
	
	public Board(Size size) {
		this.size = size;
		
		playerPosition = new Position(0, 0);
		
		squares = new Square[size.getY()][size.getX()];
		
		// Initialize board
		for (int x = 0; x < squares.length; ++x) {
			for (int y = 0; y < squares[x].length; ++y) {
				squares[x][y] = new Square(false, false, false);
				
				Random randPit = new Random(1);
				if (randPit.nextDouble() <= 0.02) { //with 2%
					squares[x][y] = new Square(true, false, false); //place pit
				}
			}
		}	
		
		Random randX = new Random();
		Random randY = new Random();

		//Place Wumpus
        Position position = new Position(randX.nextInt(size.getY()), randY.nextInt(size.getX()));
        squares[position.getX()][position.getY()].setWumpus();

        //Place Gold
		while (!squares[position.getX()][position.getY()].isWumpus()) {
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
			squares[position.getX() - 1][position.getY()].getPercepts().stench = square.isWumpus();
			squares[position.getX() - 1][position.getY()].getPercepts().breeze = square.isPit();
		}
		
		if (position.getX() < size.getX() - 1) {
			squares[position.getX() + 1][position.getY()].getPercepts().stench = square.isWumpus();
			squares[position.getX() + 1][position.getY()].getPercepts().breeze = square.isPit();
		}
		
		if (position.getY() > 0) {
			squares[position.getX()][position.getY() - 1].getPercepts().stench = square.isWumpus();
			squares[position.getX()][position.getY() - 1].getPercepts().breeze = square.isPit();
		}
		
		if (position.getY() < size.getY() - 1) {
			squares[position.getX()][position.getY() + 1].getPercepts().stench = square.isWumpus();
			squares[position.getX()][position.getY() + 1].getPercepts().breeze = square.isPit();
		}
	}
	
	// Returns: true if successful
	public boolean move(Position moveToPosition) {		
		return false;
	}
	
	public String toString() {
		String output = "";
		String delimiter = "------------------------------";
		
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
}
