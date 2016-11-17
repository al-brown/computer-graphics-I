package kevin832924.a04.Shape;

import java.awt.Color;
import kevin832924.a04.Raytracer.Group;
import kevin832924.a04.Raytracer.Ray;
import kevin832924.a04.bib.Normal3;
import kevin832924.a04.bib.Point3;

/**
 * class representing a background plane
 *
 *
 * @author Mr. Kevin
 */

public class Background implements Shape {

	public final Color color;
	/**
	 * center point of the background
	 */
	public final Point3 a;
	/**
	 * normal of the background
	 */
	public final Normal3 n;

	/**
	 * Constructor creates new instance of background
	 *
	 * @param material
	 */

	public Background(final Color color) {
		this.color = color;
		a = new Point3(1, 0, 0);
		n = new Normal3(0, 1, 0);
	}

	/**
	 *
	 * @param r
	 *            the Ray which is checked for an intersection with the background
	 * @return a new Instance of Hit if an intersection is found, else null
	 */
	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("ray can't be null!");
		}
		final double t = (a.sub(ray.o)).dot(n) / ray.d.dot(n);
		if (t > Group.EPSILON) {
			return new Hit(t, ray, this, n);
		} else {
			return null;
		}
	}



	@Override
	public String toString() {
		return "Background";
	}

	@Override
	public Color getCol() {

		return color;
	}

}
