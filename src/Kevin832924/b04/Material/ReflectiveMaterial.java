package kevin832924.b04.Material;

import kevin832924.b04.Light.Light;
import kevin832924.b04.Raytracer.Color;
import kevin832924.b04.Raytracer.Group;
import kevin832924.b04.Raytracer.Ray;
import kevin832924.b04.Raytracer.Tracer;
import kevin832924.b04.Shape.Hit;
import kevin832924.b04.Bib.Normal3;
import kevin832924.b04.Bib.Point3;
import kevin832924.b04.Bib.Vec3;

/**
 * represents a reflective material
 *
 * @author Mr. Kevin
 *
 */
public class ReflectiveMaterial extends Scattered {

    /**
     * the diffuse color
     */
    public final Color diffuse;

    /**
     * the specular color
     */
    public final Color specular;

    /**
     * the exponent
     */
    public final int exponent;

    /**
     * the color of the reflection
     */
    public final Color reflection;

    /**
     * instantiates a new reflective material
     *
     * @param diffuse the diffuse color
     * @param specular the specular color
     * @param exponent the exponent
     * @param reflection the reflection color
     */
    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection) {
        if (diffuse == null) {
            throw new IllegalArgumentException("diffuse value can't be null!");
        }
        if (specular == null) {
            throw new IllegalArgumentException("specular value can't be null!");
        }
        if (reflection == null) {
            throw new IllegalArgumentException("reflection value can't be null!");
        }
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
        this.reflection = reflection;
    }

    /**
     * colorFor returns a color for a given hit
     *
     * @param hit hit
     * @param group the group
     * @param tracer object for the fr function
     * @return color of the hit
     */



    @Override
    public String toString() {
        return "ReflectiveMaterial{" + "diffuse=" + diffuse + ", specular=" + specular + ", exponent=" + exponent + ", reflection=" + reflection + '}';
    }

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color colorFor(Hit hit, Group group, Tracer tracer) {
		 final Color colAmbientLight = getAmbient(hit, group); //color ambient light
	        Color col = diffuse.mul(colAmbientLight); //cd * ca = col

	        final Vec3 e = (hit.ray.d.multi(-1)).normalized();
	        final Normal3 n = hit.normal; //normal vector
	        final Point3 hitPoint = hit.ray.at(hit.t);
	        final double cosValue = n.dot(e) * 2;

	        for (final Light light : group.lightlist) {

	            if (light.illuminates(hitPoint, group)) {
	                final Vec3 l = light.directionFrom(hitPoint).normalized();
	                final Vec3 r = l.reflectedOn(hit.normal);

	                final double max0NdotL = Math.max(0.0, l.dot(n));
	                final double max0EdotR = Math.pow(Math.max(0.0, r.dot(e)), exponent);
	                col = col.add(light.color.mul(diffuse).mul(max0NdotL)).add(light.color.mul(specular).mul(max0EdotR));
	            }
	        }
	        final Ray r = new Ray(hitPoint, hit.ray.d.add(hit.normal.mul(cosValue)));
	        final Color reflectedColor = tracer.fr(r);
	        return col.add(reflection.mul((reflectedColor)));
	    }
	
}
