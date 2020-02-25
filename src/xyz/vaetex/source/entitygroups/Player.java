package xyz.vaetex.source.entitygroups;

import java.awt.*;

import xyz.vaetex.source.GamePanel;

public class Player extends Entity {

	// fields
	
	
	// constructors (just using abstract inherited constructors at the moment)
	public Player(GamePanel game, String identifier) {
		super(game, identifier);
	}
	
	public Player(GamePanel game, String identifier, int xPos, int yPos) {
		super(game, identifier, xPos, yPos);
	}
	
	// getters
	
	
	// setters
	
	
	// behaviors
	public void move(int destinationX, int destinationY) { // moves to the current posistion of the mouse
		
	}
	
	// inheritted abstract methods
	public void graphicalUpdate(Graphics g) {
		g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		g.drawRect(xPos-5, yPos-5, 10, 10);
	}
	
	public void logicalUpdate() {
		
	}
	
	public void onClickUpdate() {
		
	}
}
