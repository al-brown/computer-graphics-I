package kevin832924.b04.Material;


/**
 * Helper class with general mathematical functions 
 * 
 * @author Mr. Kevin
 *
 */
public class MathHelper {

    /**
     * Private (hidden) Constructor, since no instances are needed. 
     */
    private MathHelper() {
    }

    ;
	
	/**
	 * Solves a quadratic equation a * x^2 + b * x + c
	 * 
	 * @param a
	 *            Scalar factor of the quadratic term.
	 * @param b
	 *            Scalar factor of the linear term.
	 * @param c
	 *            Constant of the equation.
	 * @return Double vector of size 0, 1 or 2 according to the number of
	 *         solutions. Element 0 of the return vector is always the smallest
	 *         possible solution!
	 */
	public static double[] solveQuadrEq(double a, double b, double c) {
        double d = b * b - 4 * a * c;

        if (d < 0) {
            return new double[0]; // lustig, dass das geht :)
        } else if (d == 0) {
            double ret[] = new double[1];
            ret[0] = -b / (2 * a);
            return ret;
        } else {
            double ret[] = new double[2];
            double sqrtD = Math.sqrt(d);
            // first index should be smaller value
            if (a < 0) {
                ret[0] = (-b + sqrtD) / (2 * a);
                ret[1] = (-b - sqrtD) / (2 * a);
            } else {
                ret[1] = (-b + sqrtD) / (2 * a);
                ret[0] = (-b - sqrtD) / (2 * a);
            }
            return ret;
        }
    }
}