package kevin832924.b01.Raytracer;

import kevin832924.b01.Bib.Normal3;
import kevin832924.b01.Shape.Shape;

/**
 *
 * @author Mr. Kevin
 */
public class Hit {

    /**
     * Parameter t deciding the position on the ray
     */
    public final double t;
    /**
     * instance of Ray which was used to check for intersections
     */
    public final Ray ray;
    /**
     * instance of Shape or subclass which was checked for intersections
     */
    public final Shape shape;

    public final Normal3 normal;


    /**
     * Constructor creates new instance of Hit
     *
     * @param t Parameter t deciding the position on the ray
     * @param ray instance of Ray which was used to check for intersections
     * @param geo instance of Shape or subclass which was checked for
     * intersections
     * @param normal
     */
    public Hit(final double t, final Ray ray, final Shape shape, final Normal3 normal) {
        if (shape == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        this.t = t;
        this.ray = ray;
        this.shape = shape;
        this.normal = normal;
    }

}
