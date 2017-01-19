package kevin832924.a05.Bib;



/* Author: Henrik Tramberend tramberend@beuth-hochschule.de */


import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferUShort;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Represents 16-bit per component RGB raster images. Produces 16-bit RGB PNG
 * images. */
public class Image {

  /** Width of the image in pixels. */
  public final int width;
  /** Height of the image in pixels. */
  public final int height;

  private BufferedImage image;
  private WritableRaster raster;

  /** Constructs a new image with the specified size. Pixels are not initialized
   * to any particular color.
   *
   * @param width Image width in pixels.
   * @param height Image height in pixels.
   */
  public Image(int width, int height, BufferedImage image) {
    this.width = width;
    this.height = height;
    this.image=image;

    ComponentColorModel ccm = new ComponentColorModel(
        ColorSpace.getInstance(ColorSpace.CS_sRGB), false, false,
        ComponentColorModel.OPAQUE, DataBufferDouble.TYPE_USHORT);
    int[] bandOffsets = {0, 3 * width * height, 3 * width * height * 2};
    SampleModel sm = new ComponentSampleModel(
        DataBufferUShort.TYPE_USHORT, width, height, 3, 3 * width, bandOffsets);

    image = new BufferedImage(ccm, Raster.createWritableRaster(sm, null), false,
                              null);
    raster = image.getRaster();
  }

  /** Sets a pixel in the image to the specified RGB color. Color components are
   * from the interval [0,1]. No gamma correction is performed.
   *
   * @param x X coordinate of the pixel.
   * @param y Y coordinate of the pixel.
   * @param c RGB color of the pixel as an array of doubles. Component order is
   * RGB.
   */
  public void setPixel(int x, int y, double[] c) {
    int[] rgb = {(int)(clamp(c[0]) * 65535.999), (int)(clamp(c[1]) * 65535.999),
                 (int)(clamp(c[2]) * 65535.999)};
    raster.setPixel(x, y, rgb);
  }

  /** Sets a pixel in the image to the specified RGB color. Color components are
   * from the interval [0,1]. No gamma correction is performed.
   *
   * @param x X coordinate of the pixel.
   * @param y Y coordinate of the pixel.
   * @param c RGB color of the pixel as vector of three double values. Component
   * order is RGB.
   */
  public void setPixel(int x, int y, Vec3 c) {
    int[] rgb = {(int)(clamp(c.x) * 65535.999), (int)(clamp(c.y) * 65535.999),
                 (int)(clamp(c.z) * 65535.999)};
    raster.setPixel(x, y, rgb);
  }

  /** Writes the image to a PNG file.
   *
   * @param filename Name of the file.
   */
  public void write(String filename) throws IOException {
    File outputfile = new File(filename);
    ImageIO.write(image, "png", outputfile);
  }

  private double clamp(double v) { return Math.min(Math.max(0, v), 1); }
}
