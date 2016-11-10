package kevin832924.a03;

import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Ray other = (Ray) obj;
		if (!Objects.equals(this.o, other.o)) {
			return false;
		}
		return Objects.equals(this.d, other.d);
	}

	@Override
	public String toString() {
		return "Ray [origin=" + o + ", direction=" + d + "]";
	}

}
