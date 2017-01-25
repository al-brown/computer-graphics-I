package kevin832924.b04.Light;


import kevin832924.b04.Raytracer.Color;
import kevin832924.b04.Raytracer.Group;
import kevin832924.b04.Bib.Point3;
import kevin832924.b04.Bib.Vec3;

/**
 *
 * class for lightening
 *
 * @author Mr. Kevin
 */
public abstract class Light{

    /**
     * color of the light
     */
    public final Color color;

    /**
     * boolean value if there's a shadow or not
     */
    public final boolean castsShadow;

    /**
     * constructor of light class
     *
     * @param color
     * @param castsShadow
     */
    public Light(final Color color, final boolean castsShadow) {
        if (color == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        this.color = color;
        this.castsShadow = castsShadow;
    }

    /**
     * checks whether the given point gets illuminated by the light
     *
     * @param point
     * @return true or false
     */
    public abstract boolean illuminates(final Point3 point, final Group group);

    /**
     * calculates for a given point the direction as a vector which points to
     * the light source
     *
     * @param point
     * @return vec3 object (shows towards lighting source)
     */
    public abstract Vec3 directionFrom(final Point3 point);

   
    public String toString() {
        return "Light{" + "color=" + color + '}';
    }

}
