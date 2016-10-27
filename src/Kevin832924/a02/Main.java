package Kevin832924.a02;

import javax.imageio.ImageIO;
import Kevin832924.a02.Circle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	static String name = "doc/a02.png";
	static int width = 480;
	static int height = 270;
	static int readRate = 101;
	public static int quantity = 70;
	static List<Circle> circles = new ArrayList<>();
	private static double xs;
	private static double ys;

	public static void main(String[] args) {
		File outputfile = new File(name);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int counter = 0; counter != quantity; counter++) {

			Circle c = new Circle();
			circles.add(c);
			Collections.sort(circles);

		}

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				// *point sampling*
				// double xs = x + 0.5;
				// double ys = y + 0.5;
				image.setRGB(x, y, setBackgroundColor(y));
				for (Circle c : circles) {

					if (c.isHit(x, y)) {
						image.setRGB(x, y, setCircles(x, y, 2.2, c.getR(), c.getG(), c.getB()));
					}

				}
			}

		}

		try {
			ImageIO.write(image, "png", outputfile);

			System.out.println("Wrote image: " + name);
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e);
		}

	}

	static int setBackgroundColor(int y) {

		int r = 220;
		int g = 220;
		int b = 255;

		if ((r - y) > 0 && (g - y) > 0)
			return new Color(r - y, g - y, b).getRGB(); // set background color
														// with gradient

		else
			return new Color(0, 0, b).getRGB(); // r & b can't be negative

	}

	static int setCircles(int x, int y, double gamma, int r, int g, int b) {

		/* gamma corrector */

		double new_gamma = 1 / gamma;
		int new_r = (int) (255 * (Math.pow((double) r / (double) 255, new_gamma)));
		int new_g = (int) (255 * (Math.pow((double) g / (double) 255, new_gamma)));
		int new_b = (int) (255 * (Math.pow((double) b / (double) 255, new_gamma)));

		/* calculate coordinates of circle */

		return new Color(new_r, new_g, new_b).getRGB();

	}

	/* SuperSampling */

	static int stratifiedSampling(int x, int y, int r, int g, int b) {
		double s = 0;
		for (int xi = 0; xi < readRate; xi++) {
			for (int yi = 0; yi < readRate; y++) {
				double rx = Math.random();
				double ry = Math.random();
				xs = x + (xi + rx) / readRate;
				ys = y + (yi + ry) / readRate;
				s = setCircles((int) xs, (int) ys, 2.2, r, g, b) + s;

			}

		}

		return (int) (s / readRate); // calculate mean value
	}

}
