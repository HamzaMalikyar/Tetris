package tetrimino;

import java.awt.Color;
import java.util.Random;

public class J extends Tetrimino {
	public J() {
		
		pickColor();
	}
	//Picks randomly between three colors using create from Tetrimino superclass
	private void pickColor() {
		create(Color.blue);
	}
	public void setXY(int x, int y) {
		/*   o
		 *   o
		 * o o
		 */
		block[0].x = x;
		block[0].y = y;
		block[1].x = block[0].x;
		block[1].y = block[0].y - Block.size;
		block[2].x = block[0].x;
		block[2].y = block[0].y + Block.size;
		block[3].x = block[0].x - Block.size;
		block[3].y = block[0].y + Block.size;
	}
	public void getDirection1() {
		/*   o
		 *   o
		 * o o
		 */
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x;
		tempBlock[1].y = block[0].y - Block.size;
		tempBlock[2].x = block[0].x;
		tempBlock[2].y = block[0].y + Block.size;
		tempBlock[3].x = block[0].x - Block.size;
		tempBlock[3].y = block[0].y + Block.size;
		
		updateXY(1);
	}
	public void getDirection2() {
		/* o
		 * o o o
		 * 
		 */
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x + Block.size;
		tempBlock[1].y = block[0].y;
		tempBlock[2].x = block[0].x - Block.size;
		tempBlock[2].y = block[0].y;
		tempBlock[3].x = block[0].x - Block.size;
		tempBlock[3].y = block[0].y - Block.size;
		
		updateXY(2);
	}
	public void getDirection3() {
		/* o o
		 * o
		 * o
		 */
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x;
		tempBlock[1].y = block[0].y + Block.size;
		tempBlock[2].x = block[0].x;
		tempBlock[2].y = block[0].y - Block.size;
		tempBlock[3].x = block[0].x + Block.size;
		tempBlock[3].y = block[0].y - Block.size;
		
		updateXY(3);
	}
	public void getDirection4() {
		/* o o o
		 *     o
		 */
		tempBlock[0].x = block[0].x;
		tempBlock[0].y = block[0].y;
		tempBlock[1].x = block[0].x - Block.size;
		tempBlock[1].y = block[0].y;
		tempBlock[2].x = block[0].x + Block.size;
		tempBlock[2].y = block[0].y;
		tempBlock[3].x = block[0].x + Block.size;
		tempBlock[3].y = block[0].y + Block.size;
		
		updateXY(4);
	}
}
