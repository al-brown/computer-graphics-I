package kevin832924.b01.Shape;

import java.awt.Color;

import kevin832924.b01.Bib.MathHelper;
import kevin832924.b01.Bib.Normal3;
import kevin832924.b01.Bib.Point3;
import kevin832924.b01.Bib.Vec3;
import kevin832924.b01.Material.Scattered;
import kevin832924.b01.Raytracer.Ray;

/**
 * Intersectable Cylinder in y-d.
 * 
 * @author Mr.Kevin 
 */
public class Cylinder extends Shape {

	
	  /**
     * Top of the cylinder.
     */
    protected Vec3 top;
    /**
     * Height of the cylinder.
     */
    protected double height;
    /**
     * Radius of the bottom cap of the cylinder.
     */
    protected double bottomRadius;
    /**
     * Material of the cylinder.
     */
    protected Scattered material;
    
    double d;
    
    
    Vec3 normal_new;
    /**
     * Constructor of a sphere with a given center and radius.
     * 
     * @param top
     *            Top of the sphere.
     * @param height
     *            Height of the sphere.
     * @param bottomRadius
     *            Radius of the bottom cap of the cylinder.
     */
    public Cylinder(Vec3 top, double height, double bottomRadius, Scattered m) {
       
    	super(m);
    	this.top = top;
        this.height = Math.abs(height);
        this.bottomRadius = Math.abs(bottomRadius);

    }


    @Override
    public Hit hit(Ray ray) {

        // calculate r^2/h^2
        double a = ray.d.x * ray.d.x
                + ray.d.z * ray.d.z;

        double b = 2 * ray.d.x * (ray.o.x - top.x)
                + 2 * ray.d.z * (ray.o.z - top.z);

        Point3 ot = ray.o.sub(top);

        double c = ot.x * ot.x + ot.z * ot.z - bottomRadius * bottomRadius;

        double solutions[] = MathHelper.solveQuadrEq(a, b, c);


        Point3 normal = null;
        double distance = Double.MAX_VALUE;

        for (double s : solutions) {
            if (s > 0) {
                Point3 intPos = ray.o.add(ray.d.multi(s));

                if (intPos.y > top.y || intPos.y < top.y - height) {
                    continue;
                }

                distance = s;
                

                normal = intPos.sub(top);
                normal.y = 0;
                normal_new = new Vec3(normal.x,normal.y,normal.z);
                normal_new.normalized();
                d= s;
                // negate normal if ray hits the cylinder from inside to outside
                if (normal_new.dot(ray.d) > 0) {
                    normal_new = normal_new.multi(-1);
                }
                break;
            }
        }
        // a ray parallel to the xz-plane can not intersect top or bottom
        if (ray.d.y != 0) {
            // intersect with ground plane
            double t = (top.y - ray.o.y) / ray.d.y;
            // if farer away than shortest distance
            if (t > 0 && t < distance) {
                Point3 pos = ray.d.multi(t).add(ray.o);
                // test if Hit is within cylinder radius
                if (Math.pow(pos.x - top.x, 2) + Math.pow(pos.z - top.z, 2) < bottomRadius * bottomRadius) {
                    distance = t;
                    normal_new = new Vec3(0, 1, 0);
                }
            }
            // intersect with top plane
            t = (top.y - height - ray.o.y) / ray.d.y;
            // if farer away than shortest distance
            if (t > 0 && t < distance) {
                Point3 pos = ray.d.multi(t).add(ray.o);
                // test if Hit is within cylinder radius
                if (Math.pow(pos.x - top.x, 2) + Math.pow(pos.z - top.z, 2) < bottomRadius * bottomRadius) {
                    distance = t;
                    normal_new = new Vec3(0, -1, 0);
                }
            }
        }
        // if no Hit found, return null
        if (normal_new == null) {
            return null;
        } else {
            //Point3 intPos = ray.o.add(ray.d.multi(distance));
            return new Hit(distance,ray, this, new Normal3(normal_new.x,normal_new.y,normal_new.z));
        }
    }
 


	@Override
	Color getCol() {
		return null;
	}
  
}