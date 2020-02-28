/*
 * Programmar: Wyatt Rose
 * 
 * Last Edited: 2/28/20
 * 
 * Created: 2/5/20
 * 
 * Descripton: GamePanel is a class that helps making games easier by handling
 * keyboad and mouse inputs as well as handling graphical updates.
 * 
 * Features:
 * 	1. Easy Frame and Panel Creation
 * 	2. Easy use of graphical updates
 * 	3. Easy use of mouseListener methods
 * 	4. Easy use of mouseMotionListener methods
 * 	5. Easy use of keyListener methods
 * 	6. Easily add your own objects by putting them in the FIELDS section
 * 	7. A second main thread for people that don't like event based programming
 * 	8. GamePanel is 100% better than DrawingPanel for game creation
 * 
 * Helpful Links:
 * 	Key IDs: https://docs.oracle.com/en/java/javase/12/docs/api/java.desktop/java/awt/event/KeyEvent.html
 *  Graphics2D methods: https://docs.oracle.com/en/java/javase/12/docs/api/java.desktop/java/awt/Graphics2D.html
 */

package Stuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel implements MouseMotionListener { // class that helps making games easier
	
	// CLASS CONSTANTS
	private static final long serialVersionUID = 1L;
	private static final String frameName = "GamePanel";
	private static final int panelSizeX = 800; // size of panel and frame
	private static final int panelSizeY = 800; // ^
	
	// FIELDS
	Entity testObject = new Entity(this);
	
	// MOUSE AND KEY FIELDS
	private ArrayList<Integer> keysDown;
	private int mouseX;
	private int mouseY;
	private int mouseButton;
	
	// CONSTRUCTORS
	public GamePanel() {
		
		setFocusable(true); // allows for keyboard input
		
		addMouseMotionListener(this); // registers this class as a mouseMotionListener
		
		addMouseListener(new MouseAdapter() { // mouse button methods
			@Override
			public void mouseClicked(MouseEvent e) { // called on click (press and release in conjunction)
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) { // called on press
				updateMousePosition(e);
				updateMouseButton(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) { // called on release
				updateMousePosition(e);
			}
		});
		
		addKeyListener(new KeyListener() { // keyboard input methods
			@Override
			public void keyTyped(KeyEvent e) { // called when typed (see java documentation for more information)
				
			}

			@Override
			public void keyPressed(KeyEvent e) { // called on press
				
			}

			@Override
			public void keyReleased(KeyEvent e) { // called on release
				
			}
		});
		
		Thread updateThread = new Thread(() -> { // thread devoted to calling repaint (allows for animation)
			while(true) {
				repaint();
			}
		});
		updateThread.start();
	}
	
	// MOUSE MOVEMENT METHODS
	@Override
	public void mouseMoved(MouseEvent e) { // called when mouse is moved
		updateMousePosition(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) { // called when mouse is pressed and moving
		updateMousePosition(e);
	}
	
	public void updateMousePosition(MouseEvent e) { // updates GamePanel's mouse fields
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	// INPUT UPDATE METHODS
	public void updateMouseButton(MouseEvent e) { // updates GamePanel's mouseButton field
		mouseButton = e.getButton();
	}

	public void updateKeyPressed(KeyEvent e) {
		if(!keysDown.contains(e.getKeyCode())) {
			keysDown.add(e.getKeyCode());
		}
	}

	public void updateKeyReleased(KeyEvent e) {
		//keysDown.remove();
	}
	
	// GRAPHICAL UPDATES
	@Override
	public void paintComponent(Graphics graphics) { // called when repaint() is called, all graphical and passive updates are done here (refer to paintThread)
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics; // allows for Graphics2D painting
		testObject.logicalUpdate();
		testObject.graphicalUpdate(g);
	}
	
	// ACCESSORS
	public int getMouseX() {return mouseX;}
	public int getMouseY() {return mouseY;}
	public int getMouseButton() {return mouseButton;}
	public ArrayList<Integer> getKeysDown() {return keysDown;}
	public Dimension getPreferredSize() {return new Dimension(panelSizeX,panelSizeY);}
	
	// MUTATORS
	
	// MAIN METHOD AND FRAME CREATION
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { // running this instance of runnable when the time is "just right"
			@Override
			public void run() {
				createGUI(frameName);
			}
		});
	}
	
	public static void createGUI(String name) {
		JFrame f = new JFrame(name);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // stops the program when window is closed
		f.add(new GamePanel()); // appends the panel to the frame
		f.pack(); // sets the frame's dimensions to match the Dimension returned by getPrefferedSize()
		f.setVisible(true);
	}
}
