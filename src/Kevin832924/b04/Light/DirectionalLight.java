package kevin832924.b04.Light;

import kevin832924.b04.Raytracer.Color;
import kevin832924.b04.Raytracer.Group;
import kevin832924.b04.Raytracer.Ray;
import kevin832924.b04.Bib.Point3;
import kevin832924.b04.Bib.Vec3;

/**
 * this class represents a directional light
 *
 * @author Mr. Kevin
 */
public class DirectionalLight extends Light {

    /**
     * the direction of the light
     */
    public final Vec3 direction;

    /**
     * this constructor instantiates a directional light
     *
     * @param color light color
     * @param direction direction as vector
     * @param castsShadow
     */
    public DirectionalLight(final Color color, final Vec3 direction, final boolean castsShadow) {
        super(color, castsShadow);
        this.direction = direction;
    }

    /**
     * checks if the given point gets illuminated by the light
     *
     * @param point
     * @param group
     * @return true or false
     */
    @Override
    public boolean illuminates(final Point3 point, final Group group) {
        if (point == null) {
            throw new IllegalArgumentException("Point can't be null!");
        }
        if (group == null) {
            throw new IllegalArgumentException("World can't be null!");
        }
        if (castsShadow) {
            Ray r = new Ray(point, directionFrom(point));
            if (group.hit(r) == null) {
                return true;
            } else {
                return false;
            }
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
        return direction.multi(-1).normalized();
    }




    @Override
    public String toString() {
        return "DirectionalLight{" + "direction=" + direction + '}';
    }

}
