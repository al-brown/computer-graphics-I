package tramberend7215.a01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

	static String	name	= "doc/a01.png";
	static int		width	= 480;
	static int		height	= 270;

	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				image.setRGB(x, y, colorForRedSquareOnBlue(x, y));
			}
		}

		try {
			File outputfile = new File(name);
			ImageIO.write(image, "png", outputfile);
			System.out.println("Wrote image: " + name);
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e);
		}
	}

	static int colorForRedSquareOnBlue(int x, int y) {		
		int size = 100;
		if (Math.abs(width / 2 - x) < size / 2 && Math.abs(height / 2 - y) < size / 2)
			return new Color(255, 0, 0).getRGB();
		else
			return new Color(200, 200, 255).getRGB();
	}
}
