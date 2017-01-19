package kevin832924.a05.Material;

import kevin832924.a05.Bib.Normal3;
import kevin832924.a05.Bib.Point3;
import kevin832924.a05.Bib.Vec3;
import kevin832924.a05.Shape.Hit;
import kevin832924.a05.Light.Light;
import kevin832924.a05.Raytracer.Color;
import kevin832924.a05.Raytracer.Group;
import kevin832924.a05.Raytracer.Tracer;
import java.util.Objects;

/**
 * class represents a OrenNayar material
 *
 * @author Mr. Kevin
 */
public class OrenNayarMaterial extends Scattered {

    /**
     * the diffuse color of the material
     */
    public final Color diffuse;
    /**
     * the exponent of Oren-Nayar-Material
     */
    public final double exponent;

    /**
     * creates new instance of OrenNayarMaterial
     * @param diffuse the diffuse color of this material
     * @param exponent the exponent of this material
     */
    public OrenNayarMaterial(final Color diffuse, final double exponent) {
        this.diffuse = diffuse;
        this.exponent = exponent;
    }

    /**
     * method determines the color for given parameters
     * @param hit the instance of Hit determined at an intersection
     * @param world the instance of Group in which all shapes are displayed
     * @param tracer an instance of Tracer
     * @return an instance of Color for given parameters
     */
    @Override
    public Color colorFor(final Hit hit, final Group world, final Tracer tracer) {

        final Color colAmbientLight = world.ambientLight; //color ambient light
        Color col = diffuse.mul(colAmbientLight); //cd * ca = col

        final Vec3 e = hit.ray.d.multi(-1); //direction vector pointing towards light source
        final Normal3 n = hit.normal; //normal vector
        final Point3 hitPoint = hit.ray.at(hit.t);

        Color lightColor = new Color(0, 0, 0); //cl

        final double sigmaRauheit = exponent * exponent;
        final double A = 1.0 - 0.5 * (sigmaRauheit / (sigmaRauheit + 0.57));
        final double B = 0.45 * (sigmaRauheit / (sigmaRauheit + 0.09));

        for (Light light : world.lightlist) {

            if (light.illuminates(hitPoint, world)) { //checks if it gets illuminated

                final Vec3 l = light.directionFrom(hitPoint).normalized();

                //formula from wikipedia! ~ Oren-Nayar_reflectance_model
                final double thetaI = Math.acos(e.dot(n));
                final double thetaR = Math.acos(l.dot(n));

                final double alpha = Math.max(thetaI, thetaR);
                final double beta = Math.min(thetaI, thetaR);

                lightColor = diffuse.mul(l.dot(n)).mul(A + B * Math.max(0, l.dot(n)) * Math.sin(alpha) * Math.tan(beta));
 
                col = col.add(lightColor);
            }
        }
        return col;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.diffuse);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.exponent) ^ (Double.doubleToLongBits(this.exponent) >>> 32));
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
        final OrenNayarMaterial other = (OrenNayarMaterial) obj;
        if (Double.doubleToLongBits(this.exponent) != Double.doubleToLongBits(other.exponent)) {
            return false;
        }
        return Objects.equals(this.diffuse, other.diffuse);
    }

    @Override
    public String toString() {
        return "OrenNayarMaterial{" + "diffuse=" + diffuse + ", exponent=" + exponent + '}';
    }

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}