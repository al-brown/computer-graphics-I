package kevin832924.b01.Material;

import java.util.Objects;

import kevin832924.b01.Bib.Normal3;
import kevin832924.b01.Bib.Point3;
import kevin832924.b01.Bib.Vec3;
import kevin832924.b01.Light.Light;
import kevin832924.b01.Raytracer.Color;
import kevin832924.b01.Raytracer.Group;
import kevin832924.b01.Raytracer.Hit;
import kevin832924.b01.Raytracer.Tracer;

/**
 * class represent a phong material with lighting point
 *
 * @author Mr. Kevin
 */
public class PhongMaterial extends Scattered {

    /**
     * color of diffuse reflection
     */
    public final Color diffuse;

    /**
     * color of specular reflection
     */
    public final Color specular;

    /**
     * exponent
     */
    public final int exponent;

    /**
     * phongmaterial object
     *
     * @param diffuse
     * @param specular
     * @param exponent
     */
    public PhongMaterial(final Color diffuse, final Color specular, final int exponent) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }

    /**
     * method that returns the color of a given hit in a given world
     *
     * @param hit
     * @param world
     * @param tracer
     * @return a color
     */
    @Override
    public Color colorFor(final Hit hit, final Group world, final Tracer tracer) {

        Color colAmbientLight = getAmbient(hit, world); //color ambient light
        Color col = diffuse.mul(colAmbientLight); //cd * ca = col

        final Vec3 e = (hit.ray.d.multi(-1)).normalized(); //direction vector pointing towards light source
        final Normal3 n = hit.normal; //normal vector
        final Point3 hitPoint = hit.ray.at(hit.t);

        Color lightColor = new Color(0, 0, 0); //cl

        for (Light light : world.lightlist) {

            if (light.illuminates(hitPoint, world)) { //checks if it gets illuminated

                final Vec3 l = light.directionFrom(hitPoint).normalized();
                final Vec3 r = l.reflectedOn(n);

                final double max0NdotL = Math.max(0.0, l.dot(n));
                final double max0EdotR = Math.pow(Math.max(0.0, r.dot(e)), exponent);

                //lightColor = diffuse.mul(light.color).mul(max0NdotL).add(light.color.mul(specular).mul(light.color).mul(max0EdotR));
                lightColor
                        = lightColor.add(light.color.mul(diffuse.mul(max0NdotL))).add(light.color.mul(specular.mul(max0EdotR)));
                col = col.add(lightColor);
            }
        }
        return col;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.diffuse);
        hash = 97 * hash + Objects.hashCode(this.specular);
        hash = 97 * hash + this.exponent;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhongMaterial other = (PhongMaterial) obj;
        if (this.exponent != other.exponent) {
            return false;
        }
        if (!Objects.equals(this.diffuse, other.diffuse)) {
            return false;
        }
        return Objects.equals(this.specular, other.specular);
    }

    @Override
    public String toString() {
        return "PhongMaterial{" + "diffuse=" + diffuse + ", specular=" + specular + ", exponent=" + exponent + '}';
    }

	@Override
	public Color getColor() {
		return null;
	}

}
