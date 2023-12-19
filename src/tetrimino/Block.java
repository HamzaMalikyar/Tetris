package tetrimino;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Block extends Rectangle {
	public int x, y;
	public static final int size = 30; //30 by 30 block size
	public Color color;
	
	public Block(Color color) {
		this.color = color;
	}
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(x, y, size, size);
	}
}
