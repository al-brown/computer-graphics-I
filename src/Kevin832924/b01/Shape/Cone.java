package kevin832924.b01.Shape;

import java.awt.Color;

import kevin832924.b01.Bib.Normal3;
import kevin832924.b01.Bib.Point3;
import kevin832924.b01.Bib.Vec3;
import kevin832924.b01.Material.Scattered;
import kevin832924.b01.Raytracer.Ray;

public class Cone extends Shape {
	
	

	/**
	 * Top of the cone.
	 */
	protected Vec3 top;

	/**
	 * Height of the cone.
	 */
	protected double height;

	/**
	 * Radius of the bottom cap of the cone.
	 */
	protected double bottomRadius;

	/**
	

	/**
	 * Constructor of a sphere with a given center and radius.
	 * 
	 * @param top
	 *            Top of the cone.
	 * @param height
	 *            Height of the cone.
	 * @param bottomRadius
	 *            Radius of the bottom cap of the cone.
	 */
	public Cone(Vec3 top, double height, double bottomRadius, Scattered material) {
		super(material);
		this.top = top;
		this.height = Math.abs(height);
		this.bottomRadius = Math.abs(bottomRadius);
		
	}

	/**
	 * Solves a quadratic equation a * x^2 + b * x + c
	 * 
	 * @param a
	 *            Scalar factor of the quadratic term.
	 * @param b
	 *            Scalar factor of the linear term.
	 * @param c
	 *            Constant of the equation.
	 * @return Double vector of size 0, 1 or 2 according to the number of
	 *         solutions. Element 0 of the return vector is always the smallest
	 *         possible solution!
	 */
	double[] SolveQuadrEq(double a, double b, double c) {
		double d = b * b - 4 * a * c;

		if (d < 0) {
			return new double[0];
		} else if (d == 0) {
			double ret[] = new double[1];
			ret[0] = -b / (2 * a);
			return ret;
		} else {
			double ret[] = new double[2];
			double sqrtD = Math.sqrt(d);
			// first index should be smaller value
			if (a < 0) {
				ret[0] = (-b + sqrtD) / (2 * a);
				ret[1] = (-b - sqrtD) / (2 * a);
			} else {
				ret[1] = (-b + sqrtD) / (2 * a);
				ret[0] = (-b - sqrtD) / (2 * a);
			}
			return ret;
		}
	}

	@Override
	public Hit hit (Ray ray) {
		
		// calculate r^2/h^2
		double rh = bottomRadius/height;
		rh *= rh;
		
		double a = ray.d.x * ray.d.x
				 + ray.d.z * ray.d.z
				 - ray.d.y * ray.d.y * rh;
		
		double b = 2 * ray.d.x * (ray.o.x - top.x)
				 + 2 * ray.d.z * (ray.o.z - top.z)
				 - 2 * ray.d.y * (ray.o.y - top.y) * rh;
		
		Point3 ot = ray.o.sub(top);
		double cyh = (height + top.y);
		cyh *= cyh;
		
		double c = ot.x*ot.x + ot.z*ot.z - (ot.y*ot.y) * rh;
		
		double solutions[] = SolveQuadrEq(a, b, c);

		for (double s : solutions) {
			if (s > 0) {
				Point3 intPos = ray.o.add(ray.d.multi(s));
				
				if (intPos.y > top.y || intPos.y < top.y - height)
					continue;

				float normalY = (float) (bottomRadius / Math.sqrt(bottomRadius*bottomRadius + height*height));

				Point3 normal = intPos.sub(top);
				normal.y = 0;
				Vec3 normale = new Vec3(normal.x,normal.y,normal.z);
				normale.normalized();
				normale = normale.multi(Math.sqrt(1-normalY*normalY));
				normale.y = normalY;

				// negate normal if ray hits the cone from inside to outside
				if (normale.dot(ray.d) > 0)
					normale = normale.multi(-1);
				return new Hit(s,ray,this, new Normal3 (normal.x,normal.y,normal.z));
			}
		}
		
		return null;
	}

	@Override
	Color getCol() {
		return null;
	}


}