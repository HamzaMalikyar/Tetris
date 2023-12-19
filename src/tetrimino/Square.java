package tetrimino;

import java.awt.Color;
import java.util.Random;

public class Square extends Tetrimino {
	public Square() {
		//Calls pickColor
		pickColor();
	}
	//Picks randomly between three colors using create from Tetrimino superclass
	private void pickColor() {
		create(Color.yellow);
	}
	public void setXY(int x, int y) {
		/* o o
		 * o o
		 */
		block[0].x = x;
		block[0].y = y;
		block[1].x = block[0].x;
		block[1].y = block[0].y + Block.size;
		block[2].x = block[0].x + Block.size;
		block[2].y = block[0].y;
		block[3].x = block[0].x + Block.size;
		block[3].y = block[0].y + Block.size;
	}
	public void getDirection1() {
		
	}
	public void getDirection2() {
		
	}
	public void getDirection3() {
		
	}
	public void getDirection4() {
		
	}
}
