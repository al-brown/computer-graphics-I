package kevin832924.a04.Raytracer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kevin832924.a04.Shape.Hit;
import kevin832924.a04.Shape.Background;
import kevin832924.a04.Shape.Shape;
import kevin832924.a04.Shape.Sphere;
import kevin832924.a04.bib.Image;
import kevin832924.a04.bib.Point3;
import kevin832924.a04.bib.Vec3;

public class Main {

	static String name = "doc/a02.png";
	private final static int width = 400;
	private final static int height = 250;
	static List<Shape> shapes = new ArrayList<>();
	static Group w;
	static int blue = 255;
	static int p = 20;
	static double gamma = 2.2;

	public static void main(String[] args) {

		Camera cam = new Camera(new Point3(0, 0, 0), new Vec3(0, 0, -1), new Vec3(0, 1, 0), Math.PI / 2);

		Sphere s_one = new Sphere(new Color(255, 0, 1), new Point3(0, 0, 4), 2.0);
		Sphere s_two = new Sphere(new Color(100, 100, 220), new Point3(0, 6, -4), 6.0);
		Background b_ground = new Background(new Color(250, 250, 255));
		shapes.add(s_two);
		shapes.add(s_one);
		shapes.add(b_ground);

		w = new Group((ArrayList<Shape>) shapes, b_ground.color);

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
	 * gamma - corrector
	 */

	static int gammaCorrector(double d) {
		double gamma_e = 1.00 / gamma;
		int d_e = (int) (255 * (Math.pow((double) d / (double) 255, gamma_e)));
		return d_e;
	}

	/**
	 * raytracing
	 *
	 */

	private static BufferedImage raytrace(Group group, Camera cam, int rate) {
		BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				double r = 0;
				double g = 0;
				double b = 0;
				for (int xi = 0; xi < rate; xi++) {
					for (int yi = 0; yi < rate; yi++) {
						double xs = x + (xi + kevin832924.a04.bib.Random.random()) / rate;
						double ys = y + (yi + kevin832924.a04.bib.Random.random()) / rate;

						Hit akt = null;
						for (Shape shape : group.population) {
							Hit i = shape.hit(cam.generateRay(width, height, xs, ys));
							if (i != null)
								if (akt == null || i.t < akt.t)
									akt = i;
						}

						if (akt == null) {

							r += w.backgroundColor.getRed() - (int) ys;
							g += w.backgroundColor.getGreen() - (int) ys;
							b += w.backgroundColor.getBlue();
						} else {

							r += akt.shape.getCol().getRed();
							g += akt.shape.getCol().getGreen();
							b += akt.shape.getCol().getBlue();

						}
					}

				}

				int r_e = gammaCorrector(r / (rate * rate));
				int g_e = gammaCorrector(g / (rate * rate));
				int b_e = gammaCorrector(b / (rate * rate));

				im.setRGB(x, y, new Color(r_e, g_e, b_e).getRGB());
			}
		}

		return im;
	}

}
