package kevin832924.b01.Raytracer;

/**
 * class for the tracer fr function (recursive raytracing)
 *
 * @author Mr. Kevin
 */
public class Tracer {

    /**
     * max depth of the recursion
     */
    public int recursionDepth;

    /**
     * the group
     */
    public final Group group;

    /**
     * instantiates a new Tracer object
     *
     * @param group
     * @param recursionDepth
     */
    public Tracer(final Group group, final int recursionDepth) {
        this.group = group;
        /*set standard rekursionsTiefe in class Main  */
        this.recursionDepth = recursionDepth;
    }

    /**
     * get the color of the material of the hit object
     *
     * @param r the ray
     * @return the color
     */
    public Color fr(final Ray r) {
        if (r == null) {
            throw new IllegalArgumentException("ray can't be null!");
        }
        if (recursionDepth <= 0) {
			return group.backgroundColor;
        } else {
            final Hit hit = group.hit(r);
            if (hit != null && hit.t > Group.EPSILON) {
            	//System.out.println(hit.toString());
            	
                return hit.shape.material.colorFor(hit, group, new Tracer(group, recursionDepth - 1));
            } else {
                return group.backgroundColor;
            }
        }

    }

   
    @Override
    public String toString() {
        return "Tracer{" + "rekursionsTiefe=" + recursionDepth + ", group=" + group + '}';
    }

}
