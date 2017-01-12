package kevin832924.a05.Art;

import kevin832924.a05.Bib.Vec3;
import kevin832924.a05.Raytracer.Color;
import kevin832924.a05.Bib.Mat4;
import kevin832924.a05.Bib.Random;

import static kevin832924.a05.Art.RayTracer.aspectRatio;
import static kevin832924.a05.Art.RayTracer.bytes;

/**
 * 
 * 
 * @author Mr. Kevin
 */

final class Tracer implements Runnable {

	// Default pixel color is almost black
	private final Color DEFAULT_COLOR = new Color(13, 13, 13);

	private static final Vec3 EMPTY_VEC_3 = new Vec3();
	private static final Color SKY_COLOR = new Color(1, 1, 1);

	// Ray Origin
	private static final Vec3 CAM_FOCUS_VEC = new Vec3(-5, 16, 8);
	private static final Color FLOOR_COL_1 = new Color(1, 1, 3);
	private static final Color FLOOR_COL_2 = new Color(3, 3, 3);

	private static Vec3 STD_VEC = new Vec3(0, 0, 1);
	private static Vec3 g = new Vec3(-3.1, -16., 1.9).normalized(); // 
																  //See https://news.ycombinator.com/item?id=6425965

	private static Vec3 a = (STD_VEC.cart(g).normalized().multi(.002));
	private static Vec3 b = (g.cart(a)).normalized().multi(.002);
	private static Vec3 c = (a.add(b)).multi(-256).add(g);
	private static Mat4 mat = new Mat4(a,b,c);


	private final int offset;
	private final int jump;

	private final Vec3[] objects;

	private double t;
	private Vec3 n;

	public Tracer(final Vec3[] _objects, final int _offset, final int _jump) {
		objects = _objects;
		offset = _offset;
		jump = _jump;
		//g = mat.transformDirection(new Vec3(6,-1,-5));
		a = (STD_VEC.cart(g).normalized().multi(.002));
		b = (g.cart(a)).normalized().multi(.002);
		c = (a.add(b)).multi(-256).add(g);
	
		
	}

	// the intersection test for line [orig, v].
	// if a hit was found, return 2  (and also return distance t and bouncing ray
	// n).
	//if hit not found but ray goes upward, return 0 
	//if hit not found but ray goes downward, return 1
	private final int tracer(Vec3 orig, final Vec3 dir) {
		t = 1e9f;
		int m = 0;
		final double p = (-orig.z / dir.z);

		n = EMPTY_VEC_3;
		if (.01 < p) {
			t = p;
			n = STD_VEC;
			m = 1;
		}

		for (int i = 0; i < objects.length; i++) {
			final Vec3 p1 = orig.add(objects[i]);
			final double b = p1.dot(dir);
			final double c = (p1.dot(p1) - 1);
			final double b2 = b * b;

			// check if ray hit the sphere
			if (b2 > c) {
				// compute distance camera-sphere
				final double q = b2 - c;
				final double s = (-b - Math.sqrt(q));

				if (s < t && s > .01f) {
					t = s;
					n = (p1.add(dir.multi(t))).normalized();
					m = 2;
				}
			}
		}

		return m;
	}

	// sample world and return the pixel color for
	// a ray passing by point origin and dir (Direction)
	private final Color sample(final Vec3 origin, final Vec3 dir) {
		// search for an intersection ray
		final int m = tracer(origin, dir);
		final Vec3 on = n;

		if (m == 0) { // m==0
			// no sphere found and the ray goes upward: Generate a sky color
			final double p = (1 - dir.z);
			return SKY_COLOR.mul(p);
		}

		// sphere was maybe hit.
		Vec3 h = origin.add(dir.multi(t)); // h = intersection coordinate

		// 'l' = direction to light (with random delta for soft-shadows).
		final Vec3 l = new Vec3(9 + Random.random(), 9 + Random.random(), 16).add(h.multi(-1)).normalized();

		// calculate the lambert factor
		double b = l.dot(n);

		// calculate illumination factor (check lambert coefficient > 0 or in shadow)
		if (b < 0 || tracer(h, l) != 0) {
			b = 0;
		}

		if (m == 1) { // m == 1
			h = h.multi(.2); // no sphere was hit and the ray go
								// downward --> Generate a floor color
			final boolean cond = ((int) (Math.ceil(h.x) + Math.ceil(h.y)) & 1) == 1;
			return (cond ? FLOOR_COL_1 : FLOOR_COL_2).mul(b * .2 + .1);
		}

		final Vec3 r = dir.add(on.multi(on.dot(dir.multi(-2)))); // r = The
																	// half-vector

		// calculate the color 'p' with diffuse and specular component
		double p = Math.pow(l.dot(r.multi(b > 0 ? 1 : 0)), 99.0);

		// m == 2  sphere was hit. generate a ray bounce from the sphere
		// surface area
		// make color softer by 50% since it is bouncing (*.5)
		return new Color(p, p, p).add(sample(h, r).mul(.5));
	}

	@Override
	public void run() {
		for (int y = offset; y < RayTracer.size; y += jump) { // For each row
			int k = (RayTracer.size - y - 1) * RayTracer.size * 3;

			for (int x = RayTracer.size; x-- > 0;) { // for each pixel in a line
				final Color p = innerLoop(y, x, DEFAULT_COLOR);
				bytes[k++] = wedge(p.r);
				bytes[k++] = wedge(p.g);
				bytes[k++] = wedge(p.b);
			}
		}
	}

	
	private Color innerLoop(final int y, final int x, Color p) {
		// generate 64 rays per pixel for super sampling)
		// and soft-shadows.
		

		for (int r = 0; r < 64; r++) {
			// the delta to apply to the origin of the view for depth of view blur).
			final double factor1 = (Random.random() - .5) * 99;
			final double factor2 = (Random.random() - .5) * 99;
			final Vec3 t = a.multi(factor1).add(b.multi(factor2));
			
			// a little bit of delta up/down and left/right
			// set the camera focus point vector(17,16,8) and generate the ray
			//  collect the color returned in the p variable

			// ray direction with random deltas
			final Vec3 tmpA = a.multi(Random.random() + x * aspectRatio);
			final Vec3 tmpB = b.multi(Random.random() + y * aspectRatio);
			final Vec3 tmpC = tmpA.add(tmpB).add(c); 
			final Vec3 rayDirection = t.multi(-1).add(tmpC.multi(16)).normalized();

			p = sample(CAM_FOCUS_VEC.add(t), rayDirection).mul(3.5).add(p); // +p
																			// to
																			// collect
																			// color
		}
		return p;
	}

	private final byte wedge(final double v) {
		if (v > 255) {
			return (byte) 255;
		}
		return (byte) v;
	}
}