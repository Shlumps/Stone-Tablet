package Stuff;

import java.awt.*;
import java.awt.geom.*;

public class GameEntity {
	
	// FIELDS
	private GamePanel game;
	private String name;
	
	private boolean isSelected;
	private double xPos;
	private double yPos;
	
	// CONSTRUCTORS
	public GameEntity(GamePanel game, String name) {
		this.game = game;
		this.name = name;
	}
	
	public GameEntity(GamePanel game, String name, double xPos, double yPos) {
		this(game, name);
		this.xPos = xPos;
		this.yPos = yPos;
	}

	
	public void passiveUpdate(Graphics2D g) {
		// INPUT STUFF
		if(game.isKeyPressed(87)) { // W
			yPos -= .1;
		}
		if(game.isKeyPressed(65)) { // A
			xPos -= .1;
		}
		if(game.isKeyPressed(83)) { // S
			yPos += .1;
		}
		if(game.isKeyPressed(68)) { // D
			xPos += .1;
		}
		
		// GRAPHICAL
		g.setPaint(new Color(255,0,0));
		g.draw(new Rectangle2D.Double(xPos-5, yPos-5, 10, 10));
	}
}
