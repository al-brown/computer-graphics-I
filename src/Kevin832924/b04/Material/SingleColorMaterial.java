package kevin832924.b04.Material;


import kevin832924.b04.Raytracer.Color;
import kevin832924.b04.Raytracer.Group;
import kevin832924.b04.Raytracer.Tracer;
import kevin832924.b04.Shape.Hit;

/**
 * represents a single color material
 *
 * @author Mr. Kevin
 */
public class SingleColorMaterial extends Scattered {

    /**
     * color of the material
     */
    public final Color color;

    /**
     * instantiates a new single color material
     *
     * @param color
     */
    public SingleColorMaterial(final Color color) {
        this.color = color;
    }

    /**
     * method that returns the color of a given hit in a given group
     *
     * @param hit the Hit object that has to be checked
     * @param group the group that contains the shape objects of the Hit object
     * @param tracer
     * @return the color
     */
    @Override
    public Color colorFor(final Hit hit, final Group group,final Tracer t) {
        return color;
    }

    public String toString() {
        return "SingleColorMaterial{" + "color=" + color + '}';
    }

	@Override
	public Color getColor() {
		return color;
	}

}
