package image.model;

import java.io.IOException;

/**
 * Represents the image.model for the image processing application. This is post-class practice
 * 
 * @author  Peter Laudat
 * @version 5/19/2019
 */
public interface Image {

  /**
   * Loads this image from a file.
   * 
   * @param  imageInfile
   * @throws IOException
   */
  void load(String imageInfile) throws IOException;

  /**
   * Saves this image to a file.
   * 
   * @param  imageOutfile
   * @throws IOException
   */
  void save(String imageOutfile) throws IOException;

  /**
   * Blur this image.
   */
  void blur();

  /**
   * Sharpen this image.
   */
  void sharpen();

  /**
   * Grayscale this image.
   */
  void grayscale();

  /**
   * Sepia this image.
   */
  void sepia();

  void drawHorizontalRainbowStripes(int imageHeight, int imageWidth);

  void drawVerticalRainbowStripes(int imageHeight, int imageWidth);

  void drawCheckerBoard(int cellWidth);

  void drawFranceFlag(int flagWidth);

  void drawSwitzerlandFlag(int flagWidth);

  void drawGreeceFlag(int flagWidth);

}
