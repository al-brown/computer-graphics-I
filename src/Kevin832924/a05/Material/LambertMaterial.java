package kevin832924.a04.Material;

import kevin832924.a04.Light.Light;
import kevin832924.a04.Raytracer.Color;
import kevin832924.a04.Raytracer.Group;
import kevin832924.a04.Raytracer.Tracer;
import kevin832924.a04.Shape.Hit;
import kevin832924.a04.bib.Normal3;
import kevin832924.a04.bib.Point3;
import kevin832924.a04.bib.Vec3;

/**
 * class represents a material that has a Lambert Reflection
 *
 * @author Mr. Kevin
 */
public class LambertMaterial extends Scattered {

	/**
	 * color of the lambert material
	 */
	public final Color color;

	/**
	 * instantiates a new lambert material
	 *
	 * @param color
	 */

	public LambertMaterial(final Color color) {
		this.color = color;
	}

	/**
	 * method that returns the color of a given hit in a given group
	 *
	 * @param hit
	 *            the Hit object that has to be checked
	 * @param world
	 *            the group that contains the shape objects of the Hit object
	 * @param tracer
	 * @return the color
	 */
	@Override
	public Color colorFor(final Hit hit, final Group group, final Tracer t) {
		final Point3 hitPoint = hit.ray.at(hit.t);
		final Color ca = getAmbient(hit, group);
		// System.out.println(ca.toString());
		Color cdca = ca.mul(color);
		final Normal3 n = hit.normal;

		Color cl;
		for (Light light : group.lightlist) {
			cl = light.color; // light color

			if (light.illuminates(hitPoint, group)) {

				final Vec3 l = light.directionFrom(hitPoint).normalized(); // l
																			// =
																			// light
																			// vector
				final double luminance = n.dot(l);
				final double max = Math.max(0, luminance);
				cdca = cdca.add(color.mul(cl).mul(max));
			}
		}
		// System.out.println(cdca);
		return cdca;
	}

	@Override
	public Color getColor() {
		return color;
	}

}
