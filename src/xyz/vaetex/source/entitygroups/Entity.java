package xyz.vaetex.source.entitygroups;

import java.awt.Graphics;
import xyz.vaetex.source.*;

public abstract class Entity { // used for anything that takes up physical space in the game
	
	// fields
	protected GamePanel game;
	protected String identifier;
	protected int xPos;
	protected int yPos;
	
	// constructors
	public Entity() {
		game = null; // default constructor is to only be used if you aren't using a GamePanel for some reason
		identifier = "Default";
		xPos = 0;
		yPos = 0;
	}
	
	public Entity(GamePanel game, String identifier) {
		this.game = game;
		this.identifier = identifier;
	}
	
	public Entity(GamePanel game, String identifier, int xPos, int yPos) {
		this(game, identifier);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	// getters
	public GamePanel getGame() {return game;}
	public String getIdentifier() {return identifier;}
	public int getxPos() {return xPos;}
	public int getyPos() {return yPos;}
	
	// setters
	public void setIdentifier(String identifier) {this.identifier = identifier;}
	public void setxPos(int xPos) {this.xPos = xPos;}
	public void setyPos(int yPos) {this.yPos = yPos;}

	// abstract methods
	public abstract void graphicalUpdate(Graphics g); // updates the graphical representation of the instance
	
	public abstract void logicalUpdate(); // updates the logical representation of the instance
	
	public abstract void onClickUpdate(); // update that occurs on click
}