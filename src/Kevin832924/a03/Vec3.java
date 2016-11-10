package kevin832924.a03;

/**
 * The Class Vec3 represents a mathematical vector with three values (double)
 * 
 *
 * @author Mr. Kevin
 *
 */

public class Vec3  {

	public final double x;

	public final double y;

	public final double z;

	public final double magnitude;

	/**
	 * Instantiates a new vec3 and calculated the magnitude.
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vec3(final double x, final double y, final double z) {

		this.x = x;
		this.y = y;
		this.z = z;

		magnitude = Math.sqrt(x * x + y * y + z * z);

	}

	/**
	 * Adds a vector and returns the results as a new vec3.
	 *
	 * @param vector
	 *            to add
	 * @return the results vector
	 */
	public Vec3 add(final Vec3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		return new Vec3(x + vector.x, y + vector.y, z + vector.z);

	}

	/**
	 * Multiply the vector with a double and returns the result as a new
	 * vector3. (scalar multiplication)
	 *
	 * @param value
	 * @return the result vector
	 */
	public Vec3 multi(final double value) {

		return new Vec3(x * value, y * value, z * value);

	}

	/**
	 * Calculated the scalar product with a vector3 and returns the result as a
	 * new double.
	 *
	 * @param vector
	 * @return result (double)
	 */
	public double dot(final Vec3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		final double result = x * vector.x + y * vector.y + z * vector.z;
		return result;
	}

	/**
	 * Returns the normalized vector of this object as a new vector3.
	 *
	 * @return the result vector3
	 */
	public Vec3 normalized() {

		return new Vec3(x / magnitude, y / magnitude, z / magnitude);

	}

	/**
	 * Calculated the Cartesian product with a vector3.
	 *
	 * @param vector
	 * @return the result vector3
	 */
	public Vec3 cart(final Vec3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		return new Vec3(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);

	}


	public String toString() {
		return "Vector3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
	}



}
