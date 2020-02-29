/*
 * Programmar: Wyatt Rose
 * 
 * Last Edited: 2/29/20
 * 
 * Created: 2/5/20
 * 
 * Descripton: GamePanel is a class that helps making games easier by handling
 * keyboad and mouse inputs as well as graphical updates. It is fairly easy to
 * use and is much more tuned to game creation than DrawingPanel.
 * 
 * KeyCodes: https://docs.oracle.com/en/java/javase/12/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_0
 * Graphics2D documentation: https://docs.oracle.com/en/java/javase/12/docs/api/java.desktop/java/awt/Graphics2D.html
 */

package Stuff;

import java.awt.*; // used for graphics
import java.awt.event.*; // used for all the listeners
import java.awt.geom.*; // used for better graphics (might not need it if all graphics are done seperately in their own classes)

import javax.swing.*; // used for jFrame
import java.util.*; // used for various operations

public class GamePanel extends JPanel implements MouseMotionListener { // class that helps making games easier
	
	// CLASS CONSTANTS
	private static final long serialVersionUID = 1L; // ignore this unless you're using serialization for some reason
	private static final String frameName = "GamePanel";
	private static final int panelSizeX = 800; // preferred size of panel and frame
	private static final int panelSizeY = 800; // ^
	
	// GAME ENTITY FIELDS
	
	
	// MOUSE AND KEY FIELDS
	private int mouseX;
	private int mouseY;
	private ArrayList<Integer> mouseButtonsDown = new ArrayList<Integer>();
	private ArrayList<Integer> keysDown = new ArrayList<Integer>();
	
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
				updateMousePressed(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) { // called on release
				updateMousePosition(e);
				updateMouseReleased(e);
			}
		});
		
		addKeyListener(new KeyListener() { // keyboard input methods
			@Override
			public void keyTyped(KeyEvent e) { // called when typed (see java documentation for more information)
				
			}

			@Override
			public void keyPressed(KeyEvent e) { // called on press
				updateKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) { // called on release
				updateKeyReleased(e);
			}
		});
		
		Thread paintThread = new Thread(() -> { // thread devoted to calling repaint (allows for animation)
			while(true) {
				repaint();
			}
		});
		paintThread.start();
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
	
	// INPUT UPDATE METHODS
	public void updateMousePosition(MouseEvent e) { // updates GamePanel's mouse fields
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void updateMousePressed(MouseEvent e) { // updates the mouseButtonsDown arrayList
		if(!mouseButtonsDown.contains(Integer.valueOf(e.getButton()))) {
			mouseButtonsDown.add(Integer.valueOf(e.getButton()));
		}
	}
	
	public void updateMouseReleased(MouseEvent e) { // updates the mouseButtonsDown arrayList
		if(mouseButtonsDown.contains(Integer.valueOf(e.getButton()))) {
			mouseButtonsDown.remove(Integer.valueOf(e.getButton()));
		}
	}
	
	public void updateKeyPressed(KeyEvent e) { // updates the keysDown arrayList
		if(!keysDown.contains(Integer.valueOf(e.getKeyCode()))) {
			keysDown.add(Integer.valueOf(e.getKeyCode()));
		}
	}
	
	public void updateKeyReleased(KeyEvent e) { // updates the keysDown arraylist
		if(keysDown.contains(Integer.valueOf(e.getKeyCode()))) {
			keysDown.remove(Integer.valueOf(e.getKeyCode()));
		}
	}
	
	// GRAPHICAL UPDATES
	@Override
	public void paintComponent(Graphics graphics) { // called when repaint()* is called, all graphical and passive updates are done here (*refer to paintThread)
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics; // allows for Graphics2D painting
	    g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)); // turns on antialiasing
	    
	    // put your passive updates here
	}
	
	// EASY METHODS
	public boolean isKeyPressed(int keyCode) {
		return keysDown.contains(Integer.valueOf(keyCode));
	}
	
	public boolean isButtonPressed(int button) {
		return mouseButtonsDown.contains(Integer.valueOf(button));
	}
	
	// ACCESSORS
	public int getMouseX() {return mouseX;}
	public int getMouseY() {return mouseY;}
	public ArrayList<Integer> getMouseButtonsDown() {return mouseButtonsDown;}
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
