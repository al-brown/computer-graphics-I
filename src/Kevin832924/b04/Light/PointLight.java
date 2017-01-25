package kevin832924.b04.Light;




import java.util.Objects;

import kevin832924.b04.Bib.Point3;
import kevin832924.b04.Bib.Vec3;
import kevin832924.b04.Raytracer.Color;
import kevin832924.b04.Raytracer.Group;
import kevin832924.b04.Raytracer.Ray;

/**
 * this class represents a point light
 *
 * @author Mr. Kevin
 */
public class PointLight extends Light {

    /**
     * point of the light source; its position
     */
    public final Point3 position;

    public PointLight(final Color color, final Point3 position, final boolean castsShadow) {
        super(color, castsShadow);
        if (position == null) {
            throw new IllegalArgumentException("Point cannot be null!");
        }
        this.position = position;
    }

    /**
     * checks if the given point gets illuminated by the light
     *
     * @param point
     * @param world
     * @return true or false
     */
    @Override
    public boolean illuminates(final Point3 point, final Group world) {
        if (world == null) {
            throw new IllegalArgumentException("Group can't be null!");
        }
        if (point == null) {
            throw new IllegalArgumentException("Point can't be null!");
        }
        if (castsShadow == true) {
            Ray r = new Ray(point, directionFrom(point));

            if (world.hit(r) == null) {
                return true;
            }
            double t = r.tOf(position);
            return world.hit(r).t >= t;
        }
        return true;
    }

    /**
     * calculates for a given point the direction as a vector which points to
     * the light source
     *
     * @return a vector
     */
    @Override
    public Vec3 directionFrom(final Point3 point) {
        return position.sub(point).normalized();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.position);
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
        final PointLight other = (PointLight) obj;
        return Objects.equals(this.position, other.position);
    }

    @Override
    public String toString() {
        return "PointLight{" + "position=" + position + '}';
    }

}
