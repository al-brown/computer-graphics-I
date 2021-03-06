package kevin832924.a05.Raytracer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kevin832924.a05.Shape.Hit;
import kevin832924.a05.Light.DirectionalLight;
import kevin832924.a05.Light.Light;
import kevin832924.a05.Light.PointLight;
import kevin832924.a05.Material.LambertMaterial;
import kevin832924.a05.Material.OrenNayarMaterial;
import kevin832924.a05.Material.ReflectiveMaterial;
import kevin832924.a05.Material.SingleColorMaterial;
import kevin832924.a05.Material.TransparentMaterial;
import kevin832924.a05.Raytracer.Color;
import kevin832924.a05.Shape.Shape;
import kevin832924.a05.Shape.Sphere;
import kevin832924.a05.Bib.Image;
import kevin832924.a05.Bib.Point3;
import kevin832924.a05.Bib.Vec3;

public class Main {

	static String name = "doc/a06.png";
	private final static int width = 400;
	private final static int height = 280;
	
	static Group w;
	static int blue = 255;
	static int p = 20;
	static int recursionDepth = 20;
	static double gamma = 2.2;
	static List<Shape> shapes = new ArrayList<>();

	public static void main(String[] args) {

		Camera cam = new Camera(new Point3(0, 0, 0), new Vec3(0, 0, -1), new Vec3(0, 1, 0), Math.PI / 2);

		Sphere s_one = new Sphere(new Point3(0, 0, -4 ), 0.8, new LambertMaterial(new Color(200,100,200)));
		Sphere s_two = new Sphere(new Point3(1, 1, -6), 2.2, new LambertMaterial(new Color(200,100,200)));
		Sphere s_three = new Sphere(new Point3(-1, -1, -2), 0.6, new LambertMaterial(new Color(200,100,120)));
		Sphere s_four = new Sphere(new Point3(-2, -6, -3), 2.6, new LambertMaterial(new Color(0,100,220)));
		Sphere s_five = new Sphere(new Point3(0, -2, 1), 1.8, new LambertMaterial(new Color(200,100,120)));
		Sphere s_six = new Sphere(new Point3(0, 1.1, -4), 1.3, new OrenNayarMaterial(new Color(250,200,40),2));
		Sphere s_seven = new Sphere(new Point3(0, 7, -3), 0.3, new LambertMaterial(new Color(0,150,180)));
		Sphere s_eight = new Sphere(new Point3(0.7, 1.7, -3.3), 0.6, new LambertMaterial(new Color(0,150,180)));
		
		System.out.println(s_one.toString());
		 shapes.add(s_two);
		shapes.add(s_one);
		shapes.add(s_three);
		shapes.add(s_four);
		shapes.add(s_five);
		shapes.add(s_six);
		shapes.add(s_seven);
		shapes.add(s_eight);
		
		ArrayList<Light> lightlistDIR = new ArrayList<>();
		lightlistDIR.add(new PointLight(new Color(200, 200, 200), new Point3(6,6,1), true));
		w = new Group((ArrayList<Shape>) shapes, lightlistDIR,new Color(100,0,0));

		BufferedImage image = raytrace(w, cam, 10);
		Image ima = new Image(width, height, image);

		try {

			ima.write(name);
			System.out.println("Wrote image: " + name);
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e);
		}
	}

	/**
	 * raytracing
	 *
	 */

	private static BufferedImage raytrace(Group group, Camera cam, int rate) {
		BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Tracer t = new Tracer(w,50);
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				double r = 0;
				double g = 0;
				double b = 0;
				for (int xi = 0; xi < rate; xi++) {
					for (int yi = 0; yi < rate; yi++) {
						double xs = x + (xi + kevin832924.a04.bib.Random.random()) / rate;
						double ys = y + (yi + kevin832924.a04.bib.Random.random()) / rate;
						//System.out.println(w.hit(cam.generateRay(width, height, xs, ys)));
					r+= t.fr(cam.generateRay(width, height, xs, ys)).r;
					g+= t.fr(cam.generateRay(width, height, xs, ys)).g;
					b+= t.fr(cam.generateRay(width, height, xs, ys)).b;
					 Hit hit = group.hit(cam.generateRay(width, height, xs, ys));
			        //    if (hit != null && hit.t > Group.EPSILON)
					//t.recursionDepth = t.recursionDepth-1;
					}
				}
				int r_e = gammaCorrector(r / (rate * rate));
				int g_e = gammaCorrector(g / (rate * rate));
				int b_e = gammaCorrector(b / (rate * rate));
				im.setRGB(x, y, new java.awt.Color(r_e, g_e, b_e).getRGB());
			}
		}
		return im;
	}
	
	
	
	
	
	

	/**
	 * gamma - corrector
	 */

	static int gammaCorrector(double d) {
		double gamma_e = 1.00 / gamma;
		int d_e = (int) (255 * (Math.pow((double) d / (double) 255, gamma_e)));
		return d_e;
	}

}
