package com.stuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements MouseMotionListener {
	
	// CLASS CONSTANTS
	private static final long serialVersionUID = 1L; // we won't be using serialization, but eclipse keeps bothering me about it
	
	// FIELDS
	
	// MOUSE FIELDS
	private int mouseX;
	private int mouseY;
	private int mouseButton;
	
	// CONSTRUCTORS
	public GamePanel() {
		
		addMouseMotionListener(this); // GamePanel "is a" MouseMotionListener, so it can be passed as a parameter (setup for mouseMoved and mouseDragged)
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // this method is called when the mouse is pressed (onClickUpdate methods should be put here)
				updateMousePosition(e);
				updateMouseButton(e);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) { // this method is called when the mouse is released
				updateMousePosition(e);
			}
		});
		
		Thread paintThread = new Thread(new Runnable() { // anonymous instance of Runnable (this segment of code is just to call repaint continuosly)
			public void run() {
				while(true) {
					repaint();
				}
			}
		});
		paintThread.start();
		
		Thread logicalThread = new Thread(new Runnable() { // logical updates happen here
			public void run() {
				while(true) {

				}
			}
		});
		logicalThread.start();
	}
	// BEHAVIORS
	@Override
	public void mouseMoved(MouseEvent e) {
		updateMousePosition(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		updateMousePosition(e);
	}
	
	public void updateMousePosition(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void updateMouseButton(MouseEvent e) {
		mouseButton = e.getButton();
	}
	
	// GRAPHICAL UPDATES
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(mouseX-5, mouseY-5, 10, 10);
	}
	
	// ACCESSORS
	public int getMouseX() {return mouseX;}
	public int getMouseY() {return mouseY;}
	public int getMouseButton() {return mouseButton;}
	public Dimension getPreferredSize() {return new Dimension(800,800);}
	
	// MUTATORS
	
	// MAIN METHOD STUFF (TO MAKE LIFE EASIER FOR FILE TRANSPORTATION)
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI("Test Panel");
			}
		});
	}
	
	public static void createGUI(String name) {
		JFrame f = new JFrame(name);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.add(new GamePanel());
		f.pack();
		f.setVisible(true);
	}
}
