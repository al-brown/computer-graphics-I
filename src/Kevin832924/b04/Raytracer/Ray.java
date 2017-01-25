package kevin832924.b04.Raytracer;

import kevin832924.b04.Bib.Point3;
import kevin832924.b04.Bib.Vec3;

/**
 *
 * @author Mr. Kevin
 */
public class Ray {

	/**
	 * origin of the ray
	 */
	public final Point3 o;
	/**
	 * direction of the ray
	 */
	public final Vec3 d;

	/**
	 * Constructor creates a new instance of Ray
	 *
	 * @param o
	 *            origin of the ray
	 * @param d
	 *            direction of the ray
	 */
	public Ray(final Point3 o, final Vec3 d) {
		if (o == null) {
			throw new IllegalArgumentException("The Origin of the Ray cannot be null!");
		}
		if (d == null) {
			throw new IllegalArgumentException("The Direction of the Ray cannot be null!");
		}
		this.o = o;
		this.d = d;

	}

	/**
	 * Method searches the position of the point on the ray for given t
	 *
	 * @param value
	 * @return Point3 representing the coordinates of point on ray
	 */
	public Point3 at(final double value) {

		final Point3 result = o.add(d.multi(value));
		return result;
	}
	
	 /**
     * Method is the 'opposite' of at(final double t) The method looks for the t
     * for a given point on the ray
     *
     * @param point
     * @return double value for the 't'
     */
    public double tOf(final Point3 point) {
        if (point == null) {
            throw new IllegalArgumentException("The Point cannot be null!");
        }

        final double result = (point.sub(o).dot(d))
                / d.dot(d);
        return result;

    }

	@Override
	public String toString() {
		return "Ray [origin=" + o + ", direction=" + d + "]";
	}
	
	

}
