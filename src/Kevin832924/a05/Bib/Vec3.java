package kevin832924.a04.bib;

/**
 * The Class Vec3 represents a mathematical vector with three values (double)
 * 
 *
 * @author Mr. Kevin
 *
 */

public class Vec3 {

	public final double x;

	public final double y;

	public final double z;

	public double length;

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
     * Adds a normal and returns the results as a new vector3.
     *
     * @param normal to add
     * @return the result vector3
     */
    public Vec3 add(final Normal3 normal) {
        if (normal == null) {
            throw new IllegalArgumentException("The normal cannot be null!");
        }

        return new Vec3(x + normal.x, y + normal.y, z + normal.z);

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
	 * @param d
	 * @return result (double)
	 */
	public double dot(final Vec3 d) {
		if (d == null) {
			throw new IllegalArgumentException("The vector cannot be null!");
		}

		final double result = x * d.x + y * d.y + z * d.z;
		return result;
	}
	
	 /**
     * Calculated the scalar product with a normal and returns the result as an
     * new double.
     *
     * @param normal to multiply
     * @return the result double
     */
    public double dot(final Normal3 normal) {
        if (normal == null) {
            throw new IllegalArgumentException("The normal cannot be null!");
        }

        final double result = x * normal.x + y * normal.y + z * normal.z;
        return result;
    }


	/**
	 * Returns the normalized vector of this object as a new vec3.
	 *
	 * @return the result vec3
	 */
	public Vec3 normalized() {

		return new Vec3(x / magnitude, y / magnitude, z / magnitude);

	}

	/**
	 * Calculated the Cartesian product with a vec3.
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

	/**
	 * Calculated the length of vec3.
	 *
	 * @return the result length
	 */
	public double length() {
		length = Math.sqrt(x * y * z);
		return length;
	}

	@Override
	public String toString() {
		return "Vector3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
	}
	

    /**
     * Returns this object as a new normal3.
     *
     * @return the normal
     */
    public Normal3 asNormal() {

        return new Normal3(x, y, z);

    }
    
    /**
     * Reflected this object at the given normal3 and returns the result as a
     * new vec3.
     *
     * @param normal
     * @return the result vec3
     */
    public Vec3 reflectedOn(final Normal3 normal) {
        if (normal == null) {
            throw new IllegalArgumentException("The normal cannot be null!");
        }

        final double dot = dot(normal);
        return new Vec3(2 * dot * normal.x - x, 2 * dot * normal.y - y, 2 * dot * normal.z - z);
    }


}
