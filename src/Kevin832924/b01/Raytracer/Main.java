package kevin832924.b01.Raytracer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kevin832924.b01.Bib.Image;
import kevin832924.b01.Bib.Point3;
import kevin832924.b01.Bib.Vec3;
import kevin832924.b01.Light.DirectionalLight;
import kevin832924.b01.Light.Light;
import kevin832924.b01.Shape.Shape;

/*
import kevin832924.b01.Shape.Cone;
import kevin832924.b01.Shape.Cylinder;
import kevin832924.b01.Shape.Hit;
import kevin832924.b01.Light.DirectionalLight;
import kevin832924.b01.Light.Light;
import kevin832924.b01.Light.PointLight;
import kevin832924.b01.Material.LambertMaterial;
import kevin832924.b01.Material.OrenNayarMaterial;
import kevin832924.b01.Material.PhongMaterial;
import kevin832924.b01.Material.ReflectiveMaterial;
import kevin832924.b01.Material.SingleColorMaterial;
import kevin832924.b01.Material.TransparentMaterial;
import kevin832924.b01.Raytracer.Color;
import kevin832924.b01.Shape.Shape;
import kevin832924.b01.Shape.Sphere;
import kevin832924.b01.Shape.Torus;
import kevin832924.b01.Bib.Image;
import kevin832924.b01.Bib.Point3;
import kevin832924.b01.Bib.Vec3;

*/

public class Main {

	static String name = "doc/a06-6.png";
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
	
	//	Torus s_one = new Torus(new Vec3(0, 0, -4 ),new Vec3(0, 0, -4 ), 2.2,4.4,new Color(40,230,80), new PhongMaterial(new Color(200,100,200),new Color(200,40,60),2));
	//	Torus s_two = new Torus(new Vec3(0.2, 0.8, -4 ),new Vec3(0, 0, -4 ), 2.4,3.8,new Color(200,200,200), new PhongMaterial(new Color(200,100,200),new Color(200,40,60),2));
	//	Torus s_three = new Torus(new Vec3(0.1, 0.6, -4 ),new Vec3(1, 1, 0 ), 2,4,new Color(200,200,200), new OrenNayarMaterial(new Color(200,200,200),1));

		
	//	Cylinder s_one = new Cylinder(new Vec3(0, 0, -4 ), 2,2, new LambertMaterial(new Color(200,100,200)));
	//	Cylinder s_two = new Cylinder(new Vec3(0, 0, -4 ), 2,2, new PhongMaterial(new Color(200,100,200)));
	//	Cylinder s_three = new Cylinder(new Vec3(0, 0, -4 ), 2,2, new ReflectiveMaterial(new Color(200,100,200), null, blue, null));
	//	Cone s_one = new Cone(new Vec3(1, 1, -4 ), 2,0.8, new LambertMaterial(new Color(200,100,200)));
	//	Cone s_two = new Cone(new Vec3(-1, 7, 3 ), 0.7,0.2, new LambertMaterial(new Color(0,100,200)));

	//	Sphere s_two = new Sphere(new Point3(1, 1, -6), 2.2, new LambertMaterial(new Color(200,100,200)));
	//	Sphere s_three = new Sphere(new Point3(-1, -1, -2), 0.6, new LambertMaterial(new Color(200,100,120)));
	//	Sphere s_four = new Sphere(new Point3(-2, -6, -3), 2.6, new LambertMaterial(new Color(0,100,220)));
	//	Sphere s_five = new Sphere(new Point3(0, -2, 1), 1.8, new LambertMaterial(new Color(200,100,120)));
	//	Sphere s_six = new Sphere(new Point3(0, 1.1, -4), 1.3, new OrenNayarMaterial(new Color(250,200,40),2));
	//	Sphere s_seven = new Sphere(new Point3(0, 7, -3), 0.3, new LambertMaterial(new Color(0,150,180)));
	//	Sphere s_eight = new Sphere(new Point3(0.7, 1.7, -3.3), 0.6, new LambertMaterial(new Color(0,150,180)));

	//	System.out.println(s_one.toString());
		
	//	shapes.add(s_two);
	//	shapes.add(s_one);
	//	shapes.add(s_three);
	//	shapes.add(s_four);
	//	shapes.add(s_five);
	//	shapes.add(s_six);
	//	shapes.add(s_seven);
	//	shapes.add(s_eight);
		
		ArrayList<Light> lightlistDIR = new ArrayList<>(); 
		//lightlistDIR.add(new PointLight(new Color(150, 150, 150), new Point3(-1,0,0), true));
		lightlistDIR.add(new DirectionalLight(new Color(150, 150, 150), new Vec3(-1,0,0), true));
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
					
					//	Hit hit = group.hit(cam.generateRay(width, height, xs, ys));
			        //    if (hit != null && hit.t > Group.EPSILON)
					//			t.recursionDepth = t.recursionDepth-1;
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
