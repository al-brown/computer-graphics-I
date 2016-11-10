package kevin832924.a03;

/**
 * This class represents a point object.
 *
 * @author Mr. Kevin
 */
public class Point3 {

	/**
	 * x - component.
	 */
	public final double x;

	/**
	 * y - component.
	 */
	public final double y;

	/**
	 * z - component
	 */
	public final double z;

	/**
	 * Instantiates a new point with three double values.
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	/**
	 * Subtracts a vector and returns the result as a new point.
	 *
	 * @param vector
	 *            to subtract
	 * @return the result point
	 */
	public Point3 sub(final Vec3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		return new Point3(x - vector.x, y - vector.y, z - vector.z);
	}

	/**
	 * Adds a vector and returns the result as a new point.
	 *
	 * @param vector
	 *            to add
	 * @return the result point
	 */
	public Point3 add(final Vec3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		return new Point3(x + vector.x, y + vector.y, z + vector.z);
	}

	/**
	 * Subtracts a point and returns the result as a new vector.
	 *
	 * @param c
	 *            to subtract
	 * @return the result vector
	 */

	public Vec3 sub(Point3 c) {
		return new Vec3(x - c.x, y - c.y, z - c.z);
	}

	public String toString() {
		return "Point3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
	}

}
