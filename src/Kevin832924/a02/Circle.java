package kevin832924.a02;

import java.awt.Color;
import java.util.Random;

public class Circle implements Comparable<Object> {
    
    /**
    *
    * This class represents a circle with:
    * 
    * 1. random position
    * 2. random radius
    * 3. random color
    * 
    * 
    *@author Mr. Kevin
    * 
    **/
    
	int pos_x; 
	int pos_y;
	
	int rad;
	int r;
	int g;
	int b;
	
    /*
    *@param pos_x x-center of circle
    *@param pos_y y-center of circle
    *@param rad radius of circle
    *@param r,g,b rgb-values (color)
    
    */
	public Circle() {

		pos_x = new Random().nextInt(481);
		pos_y = new Random().nextInt(271);
		rad = new Random().nextInt(80) + 10;
		r = new Random().nextInt(256);
		g = new Random().nextInt(256);
		b = new Random().nextInt(256);
		color = new Color(r, g, b);

	}

	public boolean isHit(double x, double y) {
		if (Math.sqrt((getPos_x() - x) * (getPos_x() - x)+ (getPos_y() - y) * (getPos_y() - y)) < rad)
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
