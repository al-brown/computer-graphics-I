package kevin832924.b01.Bib;

/**
 * The Class Vec3 represents a mathematical vector with three values (double)
 * 
 *
 * @author Mr. Kevin
 *
 */

public class Vec3 {

	public double x;

	public double y;

	public double z;

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

	public Vec3() {
		x = y = z = 0.f;
		magnitude = 0;
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
	 * @param normal
	 *            to add
	 * @return the result vector3
	 */
	public Vec3 add(final Normal3 normal) {
		if (normal == null) {
			throw new IllegalArgumentException("The normal cannot be null!");
		}

		return new Vec3(x + normal.x, y + normal.y, z + normal.z);

	}
	
	
	 /**
	   * Subtract a Vector3 from this Vector3
	   * @param vector the Tuple3 to subtract
	   */
	  public Vec3 sub(Vec3 vector) {

	    this.x -= vector.x;
	    this.y -= vector.y;
	    this.z -= vector.z;
	    return vector;

	  }
	  
	  
	  /**
	   * Subtract one Point3 from another Point3 and set as this Vector
	   * @param p1 the first operand
	   * @param p2 the second operand
	   */
	  public void sub(Point3 p1, Point3 p2) {

	    this.x = p1.x - p2.x;
	    this.y = p1.y - p2.y;
	    this.z = p1.z - p2.z;

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
	 * @param normal
	 *            to multiply
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
     * Subtracts a normal and returns the result as a new vector.
     *
     * @param normal to subtract
     * @return the result vector
     */
    public Vec3 sub(final Normal3 normal) {
        if (normal == null) {
            throw new IllegalArgumentException("The normal cannot be null!");
        }

        return new Vec3(x - normal.x, y - normal.y, z - normal.z);

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

	public Point3 add(Point3 o) {
		if (o == null) {
			throw new IllegalArgumentException("The normal cannot be null!");
		}

		return new Point3(x + o.x, y + o.y, z + o.z);// TODO Auto-generated method stub

	}
	
	  public double distanceTo(Vec3 v) {
	        return this.sub(v).length();
	}

}
