package xyz.vaetex.source;

import javax.swing.*;

public class Main { // keep in mind that main does not get touched using event based proggraming

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
