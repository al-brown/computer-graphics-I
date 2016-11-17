package kevin832924.a04.bib;

/**
 * This class represents a normal3 vector object.
 *
 * @author Mr. Kevin
 */
public class Normal3 {

	/**
	 * The x component as a double.
	 */
	public final double x;

	/**
	 * The y component as a double.
	 */
	public final double y;

	/**
	 * The z component as a double.
	 */
	public final double z;

	/**
	 * Instantiates a new normal from too vectors
	 *
	 * @param vectorA
	 *            the vector a
	 * @param vectorB
	 *            the vector b
	 */
	public Normal3(final Vec3 vectorA, final Vec3 vectorB) {
		if ((vectorA == null) || (vectorB == null)) {
			throw new IllegalArgumentException("The vectors cannot be null!");
		}

		this.x = vectorA.y * vectorB.z - vectorA.z * vectorB.y;
		this.y = vectorA.z * vectorB.x - vectorA.x * vectorB.z;
		this.z = vectorA.x * vectorB.y - vectorA.y * vectorB.x;
	}

	/**
	 * Instantiates a new normal with three double
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	public Normal3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	/**
	 * Multiply the normal with a double and returns the result as an new
	 * normal. (scalar multiplication)
	 *
	 * @param value
	 *            to multiply
	 * @return the result normal
	 */
	public Normal3 mul(final double value) {
		return new Normal3(x * value, y * value, z * value);
	}

	/**
	 * Adds a normal and returns the result as an new normal.
	 *
	 * @param normal
	 *            to add
	 * @return the result normal
	 */
	public Normal3 add(final Normal3 normal) {
		if (normal == null) {
			throw new IllegalArgumentException("The normal cannot be null!");
		}

		return new Normal3(x + normal.x, y + normal.y, z + normal.z);
	}

	/**
	 * Calculated the scalar product from a normal and a vector.
	 *
	 * @param vector
	 *            the vector for the scalar product
	 * @return the result double
	 */
	public double dot(final Vec3 vector) {
		if (vector == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}
		final double result = x * vector.x + y * vector.y + z * vector.z;
		return result;
	}

	/**
	 * This method converts the normal to a vec3 object.
	 *
	 * @return
	 */
	public Vec3 asVector() {
		return new Vec3(x, y, z);
	}

	@Override
	public String toString() {
		return "Normal3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
	}

}
