package kevin832924.a02;

import javax.imageio.ImageIO;
import kevin832924.a02.Circle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main2 {

	static String name = "doc/a04.png";
	static int width = 480;
	static int height = 270;
	static int readRate = 10;
	public static int quantity = 50;
	static List<Circle> circles = new ArrayList<>();

	static double gamma = 2.2;

	public static void main(String[] args) {

		File outputfile = new File(name);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int counter = 0; counter != quantity; counter++) {

			Circle circle = new Circle();
			circles.add(circle);
			Comparator<Circle> comparator = Collections.reverseOrder();
			Collections.sort(circles, comparator);

		}

		    for (int x = 0; x != width; x++) {
			    for (int y = 0; y != height; y++) {

				double red = 0;
				double green = 0;
				double blue = 0;
				
				for (int xi = 0; xi < readRate; xi++) {
					for (int yi = 0; yi < readRate; yi++) {
						double xs = x + (xi + Math.random()) / readRate;
						double ys = y + (yi + Math.random()) / readRate;

						for (Circle c : circles) {
							if (c.isHit(xs, ys)) {

								red += c.getR();
								green += c.getG();
								blue += c.getB();
								System.out.println(blue);
								break;

							}
						}
					}
				}

				int new_red = gammaCorrector((red / Math.pow(readRate, 2)));
				int new_green = gammaCorrector((green / Math.pow(readRate, 2)));
				int new_blue = gammaCorrector((blue / Math.pow(readRate, 2)));
				// System.out.println(new_blue);
				image.setRGB(x, y, new Color(new_red, new_green, new_blue).getRGB());

			}
		}

		try

		{
			ImageIO.write(image, "png", outputfile);

			System.out.println("Wrote image: " + name);
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e);
		}

	}

	static int gammaCorrector(double d) {
		double new_gamma = 1.0 / gamma;
		int new_d = (int) (255 * (Math.pow((double) d / (double) 255, new_gamma)));
		return new_d;
	}

}
