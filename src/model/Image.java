package model;

import java.io.IOException;

/**
 * Represents the model for the image processing application. This is post-class practice
 * 
 * @author  Peter Laudat
 * @version 5/19/2019
 */
public interface Image {

  /**
   * Loads this image from a file.
   * 
   * @param imageInfile
   */
  void load(String imageInfile) throws IOException;

  /**
   * Saves this image to a file.
   * 
   * @param imageOutfile
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

  Image makeImage(String string, int height, int width) throws Exception;

  Image makeImage(String string, int height) throws Exception;

  /**
   * Generate checkerboard patterned image.
   * 
   * @param width this image's checkerboard squares' length
   */
  // void generateCheckerboardImage(int width);

  /**
   * Generate image of France's flag.
   * 
   * @param width this image of France's flag's width
   */
  // void generateFranceFlag(int width);

  /**
   * Generate image of Greece's flag.
   * 
   * @param width this image of Greece's flag's width
   */
  // void generateGreeceFlag(int width);

  /**
   * Generate image of Switzerland's flag.
   * 
   * @param width this image of Switzerland's flag's width
   */
  // void generateSwitzerlandFlag(int width);

}
