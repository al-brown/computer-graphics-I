package Kevin832924.a01;

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
			
				image.setRGB(x, y, colorForRedCircleOnBlue(x, y));
				
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

	static int colorForRedCircleOnBlue(int x, int y) {
		
		int rad = 80; //radius
		int r = 220;  
		int g = 220;
		int b = 255;
	
		int x1 = width/2;	
		int y1 = height/2;
		
		/* calculate coordinates of centered circle */
		if ((Math.sqrt((x1-x)*(x1-x) + (y1-y)*(y1-y))) <= rad  ) 
			
				return new Color(255, 0, 0).getRGB(); 	// set color of circle
		
		else {
				/* set background color with gradient */
				if ((r-y)>0 && (g-y)>0)
					
						return new Color(r-y, g-y, b).getRGB();
				else // r & b can't be negative
			
						return new Color(0, 0, b).getRGB();
			
			
		
	}
	}
}
		
		
	
	



