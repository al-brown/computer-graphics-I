package kevin832924.b01.Shape;

import kevin832924.b01.Material.Scattered;
import kevin832924.b01.Raytracer.Ray;

import java.awt.Color;

/**
 * interface from which all shape items inherit
 *
 * @author Mr. Kevin
 */
public abstract class Shape {
	

	public Scattered material;
	
	/**
     * creates new instance of this class
     *
     * @param material
     */
  
  public Shape(final Scattered material) {
        if (material == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        this.material = material;
    }

	/**
	 * Method checks if a given Ray intersects with the Shape instance
	 *
	 * @param r
	 *            the Ray which is checked for intersection
	 * @return an instance of Hit if intersection is found
	 */

	public abstract Hit hit(final Ray r) ;
	
	

	public String toString() {
		return null;
	}

	abstract Color getCol() ;

}