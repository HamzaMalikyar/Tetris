package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class TetrisPanel extends JPanel implements Runnable {
	public static final int width = 1280; //1280
	public static final int height = 720; //720
	public static final int fps = 60;
	Thread gameThread;
	Manager manager;
	
	public TetrisPanel() {
		//Sets dimension and background color
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.black);
		this.setLayout(null);
		//Implements KeyListener
		this.addKeyListener(new KeyHandler()); //Get key input with KeyHandler class
		this.setFocusable(true); //While the window is focused
		if(this.hasFocus()) {
			System.out.print("focus");
		}
		manager = new Manager();
	}

	//Launches the game
	public void launch() {
		//Activates thread
		gameThread = new Thread(this);
		//Calls run method
		gameThread.start();
	}
	
	@Override
	public void run() {
		//Game loop
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			//Calls update and paint at the fps (in this case 60 fps)
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	private void update() {
		//When game is paused or over, game is not updated
		if (KeyHandler.pausePressed == false && manager.gameOver == false) {
			manager.update();
		}
	}
	
	public void paint(Graphics graphic) {
		super.paintComponent(graphic);
		
		Graphics2D graphic2d = (Graphics2D)graphic;
		manager.draw(graphic2d);
	}
}
