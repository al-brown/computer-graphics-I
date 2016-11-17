package kevin832924.a04.Shape;

import kevin832924.a04.Raytracer.Ray;

import java.awt.Color;

/**
 * interface from which all shape items inherit
 *
 * @author Mr. Kevin
 */
public interface Shape {

	/**
	 * Method checks if a given Ray intersects with the Shape instance
	 *
	 * @param r
	 *            the Ray which is checked for intersection
	 * @return an instance of Hit if intersection is found
	 */

	Hit hit(final Ray r);
	
	

	int hashCode();

	boolean equals(Object obj);

	String toString();

	Color getCol();

}