package kevin832924.b01.Raytracer;

import kevin832924.b01.Raytracer.Ray;
import kevin832924.b01.Bib.Point3;
import kevin832924.b01.Bib.Vec3;

/**
 * class represents a simple camera with zoom
 *
 * @author Mr. Kevin
 */
public class Camera {

	/**
	 * The e the position.
	 */
	public final Point3 e;

	/**
	 * The g the gaze direction
	 */
	public final Vec3 g;

	/**
	 * The t the up-factor
	 */
	public final Vec3 t;

	/**
	 * The u new x axis
	 */
	public final Vec3 u;

	/**
	 * The v new y axis
	 */
	public final Vec3 v;

	/**
	 * The w new z axis.
	 */
	public final Vec3 w;

	/**
	 * the angle of the camera
	 */
	public final double angle;

	/**
	 * Constructor creates a new instance of class Camera
	 *
	 * @param e
	 *            eye position
	 * @param g
	 *            gaze direction
	 * @param t
	 *            Up-Vector
	 * @param angle
	 *            the angle of the camera
	 */
	public Camera(final Point3 e, final Vec3 g, final Vec3 t, final double angle) {
		if (e == null) {
			throw new IllegalArgumentException("The Point  e cannot be null!");
		}
		if (g == null) {
			throw new IllegalArgumentException("The Vector g cannot be null!");
		}
		if (t == null) {
			throw new IllegalArgumentException("The Vector t cannot be null!");
		}

		this.e = e;
		this.g = g;
		this.t = t;
		this.angle = angle;

		w = g.normalized().multi(-1);

		u = t.cart(w).normalized();

		v = w.cart(u);
	}

	/**
	 * method creates an instance of Ray for given pixel
	 *
	 * @param width
	 * @param height
	 * @param x
	 *            x coordinate of given pixel
	 * @param y
	 *            y coordinate of given pixel
	 * @return
	 */
	public Ray generateRay(final int width, final int height, final double x, final double y) {
		final Vec3 r = w.multi(-1).multi((height / 2) / Math.tan(angle / 2)).add(u.multi((x - ((width) / 2))))
				.add(v.multi(((height) / 2) - y));
		final Point3 o = e;

		final Ray result = new Ray(o, r.normalized());
		return result;

	}

	public Camera zoomIn() {
		double x = e.x - 1;
		double y = e.y - 1;
		double z = e.z - 1;
		if (x > 0 && y > 0 && z > 0) {
			return new Camera(new Point3(e.x - 1, e.y - 1, e.z - 1), g, t, angle);
		} else {
			return this;
		}

	}

	public Camera zoomOut() {
		double x = e.x + 1;
		double y = e.y + 1;
		double z = e.z + 1;
		return new Camera(new Point3(x, y, z), g, t, angle);

	}

}
