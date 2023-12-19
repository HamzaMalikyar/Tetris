package tetrimino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.KeyHandler;
import main.Manager;

public class Tetrimino { //Super class for all tetriminos
	//Tetriminos have 4 blocks to them
	public Block block[] = new Block[4];
	public Block tempBlock[] = new Block[4];
	int autoDropCounter = 0;
	public int direction = 1; //Tetriminos have 4 directions: 1,2,3,4, so there will be 4 methods for this
	boolean leftCollision, rightCollision, downCollision;
	public boolean active = true;
	public boolean deactivating;
	int deactivateCounter = 0;
	int tempDropInterval = Manager.dropInterval;
	
	public void create(Color color) {
		block[0] = new Block(color);
		block[1] = new Block(color);
		block[2] = new Block(color);
		block[3] = new Block(color);
		
		tempBlock[0] = new Block(color);
		tempBlock[1] = new Block(color);
		tempBlock[2] = new Block(color);
		tempBlock[3] = new Block(color);
	}
	
	public void setXY(int x, int y) {}
	public void updateXY(int direction) {
		checkRotationCollision();
		//If no collision is happening, then tetrimino can be rotated
		if (leftCollision == false && rightCollision == false && downCollision == false) {
			this.direction = direction;
			//Using temporary array because if a tetrimino can't rotate due to collision, need to cancel rotation
			block[0].x = tempBlock[0].x;
			block[0].y = tempBlock[0].y;
			block[1].x = tempBlock[1].x;
			block[1].y = tempBlock[1].y;
			block[2].x = tempBlock[2].x;
			block[2].y = tempBlock[2].y;
			block[3].x = tempBlock[3].x;
			block[3].y = tempBlock[3].y;

		}
	}
	public void getDirection1() {}
	public void getDirection2() {}
	public void getDirection3() {}
	public void getDirection4() {}
	public void checkMovementCollision() {
		//Resets booleans
		leftCollision = false;
		rightCollision = false;
		downCollision = false;
		
		checkStaticBlockCollision(); //Checks static block collision
		//Left wall collision
		//Scans block array
		for (int i = 0; i < block.length; i++) {
			//If block's x is touching left wall, left collision is true
			if (block[i].x == Manager.leftx) {
				leftCollision = true;
			}
		}
		//Right wall collision
		for (int i = 0; i < block.length; i++) {
			//If block's x is touching right wall, right collision is true
			if ((block[i].x + Block.size) == Manager.rightx) {
				rightCollision = true;
			}
		}
		//Floor collision
		for (int i = 0; i < block.length; i++) {
			//If block's x is touching left wall, left collision is true
			if ((block[i].y + Block.size) == Manager.bottomy) {
				downCollision = true;
			}
		}
	}
	public void checkRotationCollision() {
		//Resets booleans
				leftCollision = false;
				rightCollision = false;
				downCollision = false;
				
				checkStaticBlockCollision(); //Checks static block collision
				//Left wall collision
				//Scans block array
				for (int i = 0; i < block.length; i++) {
					//If block's x is touching left wall, left collision is true
					if (tempBlock[i].x < Manager.leftx) { //If left x is greater the rotating block
						leftCollision = true;
					}
				}
				//Right wall collision
				for (int i = 0; i < block.length; i++) {
					//If block's x is touching right wall, right collision is true
					if ((tempBlock[i].x + Block.size) > Manager.rightx) { //If right x is smaller than rotating block
						rightCollision = true;
					}
				}
				//Floor collision
				for (int i = 0; i < block.length; i++) {
					//If block's x is touching left wall, left collision is true
					if ((tempBlock[i].y + Block.size) > Manager.bottomy) { //If bottom y is smaller than rotating block
						downCollision = true;
					}
				}
	}
	private void checkStaticBlockCollision() {
		for (int i = 0; i < Manager.staticBlocks.size(); i++) {
			int targetX = Manager.staticBlocks.get(i).x;
			int targetY = Manager.staticBlocks.get(i).y;
			//Check down collision
			for (int j = 0; j < block.length; j++) {
				if (block[j].y + Block.size == targetY && block[j].x == targetX) {
					downCollision = true;
				}
			}
			//Check left collision
			for (int j = 0; j < block.length; j++) {
				if (block[j].y == targetY && block[j].x - Block.size == targetX) {
					leftCollision = true;
				}
			}
			//Check right collision
			for (int j = 0; j < block.length; j++) {
				if (block[j].y == targetY && block[j].x + Block.size == targetX) {
					rightCollision = true;
				}
			}
		}
		
	}
	public void update() {
		//Checks if deactivating is true from downCollision to allow the tetrimino to slide
		if (deactivating) {
			deactivating();
		}
		
		//Moves tetrimino
		if (KeyHandler.rightRotatePressed) {
			switch(direction) {
				case 1: getDirection2(); //If current direction is 1, gets direction 2
				break;
				case 2: getDirection3();
				break;
				case 3: getDirection4();
				break;
				case 4: getDirection1();
				break;
			}
			KeyHandler.rightRotatePressed = false;
		}
		if (KeyHandler.leftRotatePressed) {
			switch(direction) {
			case 1: getDirection4(); //If current direction is 1, gets direction 4
			break;
			case 2: getDirection1();
			break;
			case 3: getDirection2();
			break;
			case 4: getDirection3();
			break;
		}
		KeyHandler.leftRotatePressed = false;
		}
		
		checkMovementCollision();
		
		if (KeyHandler.upPressed) {
			//Tetrimino goes to ground level or ontop of ground tetriminos
			while (downCollision == false) {
				block[0].y += Block.size;
				block[1].y += Block.size;
				block[2].y += Block.size;
				block[3].y += Block.size;
				autoDropCounter = 0; //When moved down, resets counter
				checkMovementCollision();
			}
			//Makes sure upPressed isn't activated after it gets let go by resetting to false
			KeyHandler.upPressed = false;
		}
		if (KeyHandler.downPressed) {
			if (downCollision == false) {
				//When downPressed is true, tetrimino goes down by one block
				block[0].y += Block.size;
				block[1].y += Block.size;
				block[2].y += Block.size;
				block[3].y += Block.size;
				autoDropCounter = 0; //When moved down, resets counter
			}
			//Makes sure downPressed isn't activated after it gets let go by resetting to false
			KeyHandler.downPressed = false;
		}
		if (KeyHandler.leftPressed) {
			//If tetrimino is not hitting left wall, it can go left
			if (leftCollision == false) {
				block[0].x -= Block.size;
				block[1].x -= Block.size;
				block[2].x -= Block.size;
				block[3].x -= Block.size;
			}
			KeyHandler.leftPressed = false;
		}
		if (KeyHandler.rightPressed) {
			//If tetrimino is not hitting right wall, it can go right
			if (rightCollision == false) {
				block[0].x += Block.size;
				block[1].x += Block.size;
				block[2].x += Block.size;
				block[3].x += Block.size;
			}
			KeyHandler.rightPressed = false;
		}
		//If bottom collision happens, then deactivate tetrimino
		if (downCollision) {
			deactivating = true;
		}
		else {
			autoDropCounter++; //Increases counter every frame
			if (autoDropCounter == Manager.dropInterval) {
				//Tetrimino goes downwards
				block[0].y += Block.size;
				block[1].y += Block.size;
				block[2].y += Block.size;
				block[3].y += Block.size;
				autoDropCounter = 0; //Reset counter after each block goes down 30 pixels (one block size)
			}
		
		}
	}
	private void deactivating() {
		//Lets the tetrimino slide for 50 frames
		deactivateCounter++;
		if (deactivateCounter == 50) {
			//Once the counter reaches 50 for 50 frames, deactivates counter
			deactivateCounter = 0;
			checkMovementCollision(); //Checks if bottom is still hitting
			//If the bottom is still hitting deactivate tetrimino
			if (downCollision) {
				active = false;
			}
		}
	}
	public void draw(Graphics2D graphic2d) {
		graphic2d.setColor(block[0].color);
		graphic2d.fillRect(block[0].x, block[0].y, Block.size, Block.size);
		graphic2d.fillRect(block[1].x, block[1].y, Block.size, Block.size);
		graphic2d.fillRect(block[2].x, block[2].y, Block.size, Block.size);
		graphic2d.fillRect(block[3].x, block[3].y, Block.size, Block.size);
	}
}
