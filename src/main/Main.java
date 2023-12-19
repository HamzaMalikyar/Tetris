package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Color Tetris");
		//Makes sure window can close and isn't resizeable and is visible
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		
		//Makes panel created in TetrisPanel class as window panel
		TetrisPanel tp = new TetrisPanel();
		window.add(tp);
		//window.pack makes size of TetrisPanel = the size of window
		window.pack();
		
		//Makes window location the center of the screen, has to be after you add the panel to the window
		window.setLocationRelativeTo(null);
		
		tp.launch();
		tp.requestFocusInWindow();//Launches the game

	}

}
