package kevin832924.a05.Material;

import kevin832924.a05.Bib.Normal3;
import kevin832924.a05.Bib.Vec3;
import kevin832924.a05.Shape.Hit;
import kevin832924.a05.Raytracer.Color;
import kevin832924.a05.Raytracer.Ray;
import kevin832924.a05.Raytracer.Tracer;
import kevin832924.a05.Raytracer.Group;

/**
 * represents a transparent material
 *
 * @author Mr. Kevin
 */
public class TransparentMaterial extends Scattered {

    /**
     * the index of refraction
     */
    public final double indexOfRefraction;

    /**
     * instantiates a new transparent material
     *
     * @param indexOfRefraction
     */
    public TransparentMaterial(final double indexOfRefraction) {
        this.indexOfRefraction = indexOfRefraction*1.25;
        
    }

    /**
     * determines the color for given parameters
     *
     * @param hit the hit
     * @param world the group
     * @param tracer object for the fr function
     * @return a new instance of Color for given parameter
     */
    @Override
    public Color colorFor(final Hit hit, final Group world, final Tracer tracer) {
        Normal3 n = hit.normal;
        // rd reflected vector
        final Vec3 rd = hit.ray.d.multi(-1).normalized().reflectedOn(n).normalized();
        // rate of refraction
        double nQuotient;
        if (rd.dot(n) < 0) {
            nQuotient = indexOfRefraction / world.refractionIndex;
            n = n.mul(-1);
        } else {
            nQuotient = world.refractionIndex / indexOfRefraction;
        }
        final double cosPhiEIns = n.dot(hit.ray.d.multi(-1));
        if ((1 - (Math.pow(nQuotient, 2.0) * (1 - Math.pow(cosPhiEIns, 2.0)))) < 0) {
            return tracer.fr(new Ray(hit.ray.at(hit.t - Group.EPSILON), rd));
        } else {
            final double cosPhiZwei = Math.sqrt(1 - (Math.pow(nQuotient, 2.0) * (1 - Math.pow(cosPhiEIns, 2.0))));
            final Vec3 t = hit.ray.d.multi(nQuotient).sub(n.mul(cosPhiZwei - nQuotient * cosPhiEIns));
            //Schlicksche Approximation
            final double R0 = Math.pow((world.refractionIndex - indexOfRefraction) / (world.refractionIndex + indexOfRefraction), 2);
            // R the rate of reflection
            final double R = R0 + (1 - R0) * (Math.pow(1 - cosPhiEIns, 5));
            // T the rate of transmission
            final double T = 1 - R;
            // R and T are multiplied with the colors of the raytraced rays
            final Ray prrd = new Ray(hit.ray.at(hit.t - Group.EPSILON), rd);
            final Ray prrt = new Ray(hit.ray.at(hit.t + Group.EPSILON), t);
            final Color c = tracer.fr(prrd).mul(R).add(tracer.fr(prrt)).mul(T);
            return c;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.indexOfRefraction) ^ (Double.doubleToLongBits(this.indexOfRefraction) >>> 32));
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
        final TransparentMaterial other = (TransparentMaterial) obj;
        return Double.doubleToLongBits(this.indexOfRefraction) == Double.doubleToLongBits(other.indexOfRefraction);
    }

    @Override
    public String toString() {
        return "TransparentMaterial{" + "indexOfRefraction=" + indexOfRefraction + '}';
    }

	@Override
	public Color getColor() {
		return null;
	}

}
