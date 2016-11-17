package kevin832924.a04.Shape;

import java.awt.Color;
import kevin832924.a04.Raytracer.Group;
import kevin832924.a04.Raytracer.Ray;
import kevin832924.a04.bib.Normal3;
import kevin832924.a04.bib.Point3;

/**
 * Class represents a sphere
 *
 *
 * @author Mr. Kevin
 */

public class Sphere implements Shape {

	public final Color color;
	/**
	 * Point3 representing the center coordinates
	 */
	public final Point3 c;

	/**
	 * Radius
	 */
	public final double r;

	/**
	 * creates new instance of this class
	 *
	 * @param material
	 */
	public Sphere(final Color color, final Point3 c, final double r) {
		this.color = color;
		this.c = c;
		this.r = r;
	}

	@Override
	public Hit hit(final Ray ray) {
		final double a = ray.d.dot(ray.d);
		final double b = ray.d.dot((ray.o.sub(c)).multi(2.0));
		final double c_double = (ray.o.sub(c)).dot(ray.o.sub(c)) - (r * r);
		final double divident = (b * b) - (4 * a * c_double);

		final double t;
		final double t1;
		final double t2;

		if (divident < 0) {
			return null;
		} else if (divident == 0) {
			t = ((-b) + Math.sqrt(divident)) / (2 * a);
			final Normal3 normal = ray.at(t).sub(this.c).normalized().asNormal();

			return new Hit(t, ray, this, normal);
		} else {
			t1 = ((-b) + Math.sqrt(divident)) / (2 * a);
			t2 = ((-b) - Math.sqrt(divident)) / (2 * a);
			if (t1 > Group.EPSILON && t1 < t2) {
				final Normal3 normal1 = ray.o.add(ray.d.multi(t1)).sub(c).normalized().asNormal();

				return new Hit(t1, ray, this, normal1);
			} else {
				final Normal3 normal2 = ray.o.add(ray.d.multi(t2)).sub(c).normalized().asNormal();

				return new Hit(t2, ray, this, normal2);
			}
		}
	}

	@Override
	public Color getCol() {
		// TODO Auto-generated method stub
		return color;
	}

}
