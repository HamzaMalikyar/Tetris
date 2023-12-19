package tetrimino;

import java.awt.Color;
import java.util.Random;

public class L extends Tetrimino{

	public L() {
		pickColor();
	}
	//Picks randomly between three colors using create from Tetrimino superclass
	private void pickColor() {
		create(Color.orange);
	}
	//Uses block array from superclass Tetrimino to set x and y
	public void setXY(int x, int y) {
		//o block[1]
		//o block[0]
		//o block[2] o block[3]
		block[0].x = x;
		block[0].y = y;
		block[1].x = block[0].x;
		block[1].y = block[0].y - Block.size; //block[1] is above block[0] so changes y
		block[2].x = block[0].x;
		block[2].y = block[0].y + Block.size; //block[2] is below block[0] so changes y
		block[3].x = block[0].x + Block.size; //block[3] is to the right of block[0] so changes x
		block[3].y = block[0].y + Block.size; //block[3] is below block[0] so changes y
	}
	public void getDirection1() {
		/*o
		  o
		  o o 
		*/
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x;
		tempBlock[1].y = block[0].y - Block.size;
		tempBlock[2].x = block[0].x;
		tempBlock[2].y = block[0].y + Block.size;
		tempBlock[3].x = block[0].x + Block.size;
		tempBlock[3].y = block[0].y + Block.size;
		
		updateXY(1);
	}
	public void getDirection2() {
		/* 
		 * o o o
		 * o
		 */
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x + Block.size;
		tempBlock[1].y = block[0].y;
		tempBlock[2].x = block[0].x - Block.size;
		tempBlock[2].y = block[0].y;
		tempBlock[3].x = block[0].x - Block.size;
		tempBlock[3].y = block[0].y + Block.size;
		
		updateXY(2);
	}
	public void getDirection3() {
		/* o o
		 *   o
		 *   o
		 */
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x;
		tempBlock[1].y = block[0].y + Block.size;
		tempBlock[2].x = block[0].x;
		tempBlock[2].y = block[0].y - Block.size;
		tempBlock[3].x = block[0].x - Block.size;
		tempBlock[3].y = block[0].y - Block.size;
		
		updateXY(3);
	}
	public void getDirection4() {
		/*     o
		 * o o o
		 */
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x - Block.size;
		tempBlock[1].y = block[0].y;
		tempBlock[2].x = block[0].x + Block.size;
		tempBlock[2].y = block[0].y;
		tempBlock[3].x = block[0].x + Block.size;
		tempBlock[3].y = block[0].y - Block.size;
		
		updateXY(4);
	}
}
