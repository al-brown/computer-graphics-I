package kevin832924.a04.Raytracer;

import kevin832924.a04.Shape.Shape;
import kevin832924.a04.Shape.Hit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Mr. Kevin
 */

public class Group implements Shape {

	/**
	 * Groups Epsilon constant to ignore hits with low t factors
	 */
	public static final double EPSILON = 0.0000001;
	/**
	 * List of all Shape instances
	 */
	public final ArrayList<Shape> population;

	/**
	 * The background color which the graphic representation will take
	 */
	public final Color backgroundColor;

	public Group(final ArrayList<Shape> population, Color backgroundColor) {

		this.population = population;
		this.backgroundColor = backgroundColor;
	}

	/**
	 * Method iterates through all Shape instances in population and checks for
	 * hits.
	 *
	 * @param r
	 *            the Ray with which the instances are checked for intersections
	 * @return the instance of Hit with the smallest parameter t
	 */
	public Hit hit(final Ray r) {
		TreeMap<Double, Hit> hitMap = new TreeMap<>();
		Hit hit;
		for (Shape item : population) {
			hit = item.hit(r);
			if (hit != null && hit.t > Group.EPSILON) {
				hitMap.put(hit.t, hit);
			}
		}
		if (hitMap.isEmpty()) {
			return null;
		} else {
			return hitMap.firstEntry().getValue();
		}
	}

	@Override
	public Color getCol() {
		// TODO Auto-generated method stub
		return null;
	}

}
