package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import tetrimino.Block;
import tetrimino.I;
import tetrimino.J;
import tetrimino.L;
import tetrimino.S;
import tetrimino.Square;
import tetrimino.T;
import tetrimino.Tetrimino;
import tetrimino.Z;

public class Manager {
	final int width = 360;
	final int height = 600;
	public static int leftx;
	public static int rightx;
	public static int topy;
	public static int bottomy;
	
	//Tetrimino
	Tetrimino currentTetrimino;
	final int tetriminostartx;
	final int tetriminostarty;
	Tetrimino nextTetrimino;
	final int nextTetriminoX;
	final int nextTetriminoY;
	public static ArrayList<Block> staticBlocks = new ArrayList<>(); //Inactive tetriminos will go to staticBlocks
	public static int dropInterval = 60; //Tetrimino drops at 60fps
	boolean gameOver;
	
	//Score
	int level = 1;
	int lines;
	int score;
	
	public Manager() {
		//Play area frame
		leftx = (TetrisPanel.width / 2) - (width / 2); //1280/2 - 360/2 = 460
		rightx = leftx+width;
		topy = 50;
		bottomy = topy + height;
		//Starting position of where tetrimino falls (middle of play area)
		tetriminostartx = leftx + (width / 2) - Block.size;
		tetriminostarty = topy + Block.size;
		
		nextTetriminoX = rightx + 182;
		nextTetriminoY = topy + Block.size*7;
		
		//Sets the tetrimino that falls
		currentTetrimino = pickTetrimino();
		currentTetrimino.setXY(tetriminostartx, tetriminostarty);
		nextTetrimino = pickTetrimino();
		nextTetrimino.setXY(nextTetriminoX, nextTetriminoY);
	}
	private Tetrimino pickTetrimino() {
		Tetrimino tetrimino = null;
		int i = new Random().nextInt(7);
		
		switch(i) {
			case 0: tetrimino = new L();
			break;
			case 1: tetrimino = new J();
			break;
			case 2: tetrimino = new T();
			break;
			case 3: tetrimino = new I();
			break;
			case 4: tetrimino = new Square();
			break;
			case 5: tetrimino = new Z();
			break;
			case 6: tetrimino = new S();
			break;
		}
		return tetrimino;
	}
	
	public void update() {
		//Check if current tetrimino is active
		if (currentTetrimino.active == false) {
			//If tetrimino is not active, put it into staticBlocks
			staticBlocks.add(currentTetrimino.block[0]);
			staticBlocks.add(currentTetrimino.block[1]);
			staticBlocks.add(currentTetrimino.block[2]);
			staticBlocks.add(currentTetrimino.block[3]);
			//Check if game is over
			//If the current x and y are the same as the nextTetrimino's, then game over
			if(currentTetrimino.block[0].x == tetriminostartx && currentTetrimino.block[0].y == tetriminostarty) { 
				gameOver = true;
			}
			currentTetrimino.deactivating = false;
			
			//Replace current tetrimino with next
			currentTetrimino = nextTetrimino;
			currentTetrimino.setXY(tetriminostartx, tetriminostarty);
			nextTetrimino = pickTetrimino();
			nextTetrimino.setXY(nextTetriminoX, nextTetriminoY);
			
			//When current tetrimino is inactive, check if it can be deleted
			checkDelete();
		}
		else {
			currentTetrimino.update();
		}
		currentTetrimino.update();
	}
	private void checkDelete() {
		int x = leftx;
		int y = topy;
		int blockCount = 0;
		int lineCount = 0;
		
		while (x < rightx && y < bottomy) {
			for ( int i = 0; i < staticBlocks.size(); i++) {
				//Increases blockCount if there is a static block
				if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
					blockCount++;
				}
			}
			x += Block.size;
			if (x == rightx) {
				//If the blockCount reaches 12, then that means the current y line is filled with blocks, so they can get deleted
				if (blockCount == 12) {
					//Removes all the blocks from the current y line
					for (int i = staticBlocks.size() - 1; i > -1; i--) {
						if (staticBlocks.get(i).y == y) {
							staticBlocks.remove(i);
						}
					}
					
					lineCount++;
					lines++;
					
					//Drop speed
					if (lines % 10 == 0 && dropInterval > 1) { //Every 10 lines, drop interval decreases, speed increases
						level++; //Every 10 lines gets to the next level
						if (dropInterval > 10) { //If drop interval is greater than 10, decreases drop interval by 10 every level
							dropInterval -= 10;
						}
						else {
							dropInterval -= 1; //Once drop interval reaches ten, decreases by 1 every level
						}
					}
					
					//A line has been deleted so need to slide down blocks that are above it
					for (int i = 0; i < staticBlocks.size(); i++) {
						//If a block is above the current y, move it down by block size
						if (staticBlocks.get(i).y < y) {
							staticBlocks.get(i).y += Block.size;
						}
					}
				}
				blockCount = 0; //Resets blockCount when x = rightx because it goes to the next row
				x = leftx;
				y += Block.size;
			}
		}
		//Adds score
		if (lineCount > 0) {
			int singleLineScore = 10 * level;
			score += singleLineScore + lineCount;
		}
	}
	
	public void draw(Graphics2D graphic2d) {
		//Draws play area frame
		graphic2d.setColor(Color.white);
		graphic2d.setStroke(new BasicStroke(4f)); //Width of frame is 4 pixels
		graphic2d.drawRect(leftx - 4, topy - 4, width + 8, height + 8); //Subtract 4 and plus 8 to start drawing after the frame rather than in it
		
		//Draws tetrimino frame
		int x = rightx + 100;
		int y = bottomy - 500;
		graphic2d.drawRect(x, y, 200, 200);
		graphic2d.setFont(new Font("Arial", Font.PLAIN, 30));
		graphic2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphic2d.drawString("NEXT", x + 60, y + 30);
		
		//Draws score frame
		int a = rightx + 100;
		int b = bottomy - 250;
		graphic2d.drawRect(a, b, 200, 250);
		a += 40;
		b += 70;
		graphic2d.drawString("Level: "+level, a, b); b += 70;
		graphic2d.drawString("Lines: "+lines, a, b); b += 70;
		graphic2d.drawString("Score: "+score, a, b); b += 70;
		
		//Draws the currentTetrimino
		if (currentTetrimino != null) {
			currentTetrimino.draw(graphic2d);
		}
		
		//Draws next tetrimino
		nextTetrimino.draw(graphic2d);
		
		for (int i = 0; i < staticBlocks.size(); i++) {
			staticBlocks.get(i).draw(graphic2d);
		}
		
		graphic2d.setColor(Color.white);
		if (KeyHandler.pausePressed) {
			x = leftx + 120;
			y = topy + 320;
			graphic2d.drawString("PAUSED", x, y);
		}
		graphic2d.setColor(Color.red);
		if (gameOver) {
			x = leftx + 95;
			y = topy + 320;
			graphic2d.drawString("GAME OVER", x, y);
		}
	}
	
}
