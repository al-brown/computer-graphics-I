package kevin832924.b04.Cameras;

import kevin832924.b04.Bib.Point3;
import kevin832924.b04.Bib.Vec3;
import kevin832924.b04.Raytracer.Ray;

/**
 * 
 * this class represents a 360° Panorama Photo Sphere Camera
 * 
 * 
 * @author Mr. Prince
 *
 */

public class PhotoSphereCam extends AbstractCam {

	public PhotoSphereCam(Point3 e, Vec3 g, Vec3 t) {
		super(e, g, t);
	}

	@Override
	public Ray generateRay(int width, int height, double x, double y) {
		 // Generate environment camera ray direction
        double theta = 2 * Math.PI * x / width + Math.PI / 2;
        double phi = Math.PI * (height - 1 - y) / height;
        return new Ray(new Point3(0, 0, 0),new Vec3( (float) (Math.cos(theta) * Math.sin(phi)), (float) (Math.cos(phi)), (float) (Math.sin(theta) * Math.sin(phi))));
}

	@Override
	public PerspectiveCam zoomIn() {
		return null;
	}

	@Override
	public PerspectiveCam zoomOut() {
		return null;
	}
	



}
