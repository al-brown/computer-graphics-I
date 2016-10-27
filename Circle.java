package Kevin832924.a02;

import java.awt.Color;
import java.util.Random;

public class Circle implements Comparable<Object> {

	int pos_x;
	int pos_y;
	Color color;
	int rad;
	int r;
	int g;
	int b;

	public Circle() {

		pos_x = new Random().nextInt(481);
		pos_y = new Random().nextInt(271);
		rad = new Random().nextInt(80) + 10;
		r = new Random().nextInt(256);
		g = new Random().nextInt(256);
		b = new Random().nextInt(256);
		color = new Color(r, g, b);

	}

	public boolean isHit(int x, int y) {
		if ((Math.sqrt((pos_x - x - rad) * (pos_x - x - rad) + ((pos_y - y - rad) * (pos_y - y - rad)))) < rad)
			return true;
		else
			return false;
	}

	public int getPos_x() {
		return pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public Color getColor() {
		return color;
	}

	public int getRad() {
		return rad;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	@Override
	public int compareTo(Object o) {
		int compareAge = ((Circle) o).getRad();

		return compareAge - this.rad;

	}

}
