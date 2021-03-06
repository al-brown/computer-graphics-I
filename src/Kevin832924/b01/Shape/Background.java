package kevin832924.b01.Shape;

import java.awt.Color;

import kevin832924.b01.Material.Scattered;
import kevin832924.b01.Raytracer.Group;
import kevin832924.b01.Raytracer.Ray;
import kevin832924.b01.Bib.Normal3;
import kevin832924.b01.Bib.Point3;

/**
 * class representing a background plane
 *
 *
 * @author Mr. Kevin
 */

public class Background extends Shape {


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

	public Background(final Scattered material) {
		super(material);
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

		return null;
	}

}
