package org.bjwumpusandcompany.wumpusworld;

public interface ModelInterface {
	enum GameState {Initialize, Started, Ended};
	enum Action {Forward, TurnLeft, TurnRight, Grab, Shoot, Climb};
	
	public void setGameState(GameState state); //TODO add GameState 
	public GameState getGameState();
	
	public Percept getCurrentPercept();
	public void setHunterAction(Action action);
	
	public Square[][] getWorld();
	public Hunter getHunter();
}
