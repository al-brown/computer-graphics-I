package kevin832924.b01.Raytracer;

/**
 * Class represents an RGB color
 *
 * @author Mr. KEvin
 */
public class Color {

	/**
	 * the red value of the color
	 */
	public double r;
	/**
	 * the green value of the color
	 */
	public double g;
	/**
	 * the blue value of the color
	 */
	public double b;

	/**
	 * Constructor creates new instance of this class
	 *
	 * @param r
	 *            the red value of the color to be created, must be between 0
	 *            and 1
	 * @param g
	 *            the green value of the color to be created, must be between 0
	 *            and 1
	 * @param b
	 *            the blue vaue of the color to be created, must be between 0
	 *            and 1
	 */
	public Color(double r, double g, double b) {
		// if values are above 250 or below 0...
		if (r > 255) {
			r = 255;
		}
		if (r < 0.0) {
			r = 0.0;
		}
		if (g > 255) {
			g = 255;
		}
		if (g < 0.0) {
			g = 0.0;
		}
		if (b > 255) {
			b = 255;
		}
		if (b < 0.0) {
			b = 0.0;
		}
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * method adds another instance of this class to this instance
	 *
	 * @param c
	 *            the Color instance which is to be added
	 * @return the newly created Color
	 */
	public Color add(final Color c) {
		final double r_new = this.r + c.r;
		final double g_new = this.g + c.g;
		final double b_new = this.b + c.b;
		return new Color(r_new, g_new, b_new);
	}

	/**
	 * method substracts another instance of this class to this istace
	 *
	 * @param c
	 *            the Color instance which is to be added
	 * @return the newly created Color
	 */
	public Color sub(final Color c) {
		final double r_new = this.r - c.r;
		final double g_new = this.g - c.g;
		final double b_new = this.b - c.b;
		return new Color(r_new, g_new, b_new);
	}

	/**
	 * method multiplies another instance of this class to this instance
	 *
	 * @param c
	 *            the Color instance which is to be added
	 * @return the newly created Color
	 */
	public Color mul(final Color c) {
		final double r_new = (this.r * c.r);
		final double g_new = (this.g * c.g);
		final double b_new = (this.b * c.b);
		return new Color(r_new, g_new, b_new);
	}

	/**
	 * method multiplies the current instance of Color with a double value
	 *
	 * @param v
	 *            the double value with which this instance is multiplied
	 * @return the newly created Color
	 */
	public Color mul(final double v) {
		final double r_new = this.r * v;
		final double g_new = this.g * v;
		final double b_new = this.b * v;
		return new Color(r_new, g_new, b_new);
	}

	public String toString() {
		return "Color{" + "r=" + r + ", g=" + g + ", b=" + b + '}';
	}

}
