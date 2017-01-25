package kevin832924.b04.Bib;

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
	public double y;

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

	public Point3() {
		x=0;
		y=0;
		z=0;
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
	 * Adds a vector and returns the result as a new point.
	 *
	 * @param vector
	 *            to add
	 * @return the result point
	 */
	public Vec3 add(final Point3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		return new Vec3(x + vector.x, y + vector.y, z + vector.z);
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
	
	  public double distanceTo(Point3 v) {
	        return this.sub(v).length();
	}

}
