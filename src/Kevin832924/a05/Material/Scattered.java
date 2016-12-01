package kevin832924.a04.Material;




import kevin832924.a04.Raytracer.Color;
import kevin832924.a04.Raytracer.Ray;
import kevin832924.a04.Raytracer.Tracer;
import kevin832924.a04.Raytracer.Group;
import kevin832924.a04.Shape.Hit;
import kevin832924.a04.bib.Point3;
import kevin832924.a04.bib.Vec3;

/**
 * interface of a material
 *
 * @author Mr. Kevin
 */
public abstract class Scattered implements Material {
	
	// abstract method only for SingleColorMaterial
	 public abstract Color getColor();

    public abstract Color colorFor(final Hit hit, final Group group, final Tracer tracer);

    public Color getAmbient(final Hit hit, final Group group)   {
        final Point3 hitPoint = hit.ray.at(hit.t);
        final int samples = 32;
        double ambientDiv = 0;
        Ray ray;
        Hit ambientHit;
        final Vec3 normalVector = hit.normal.asVector();
        final Vec3 reverseHitRay = hit.ray.d.multi(-1);
        for (int i = 1; i <= samples; i++) {
            ray = new Ray(hitPoint, normalVector.multi(samples / 2 - i).add(reverseHitRay).normalized());
            //System.out.println(ray.toString());
            ambientHit = group.hit(ray);
            if (ambientHit != null && ambientHit.t <= 1.0) {
                ambientDiv++;
            }
            //System.out.println(ambientDiv);
        }
        final Color ca;
        if (ambientDiv > 0) {
            ca = group.ambientLight.mul((1 - (ambientDiv / samples)));
        } else {
            ca = group.ambientLight;
        }

        return ca;
    }
}