/**
 * 
 */
package image.archive;

import java.awt.Color;

/**
 * Draws the flag of Greece.
 * 
 * @author Peter
 *
 */
public class GreeceFlag {

  /**
   * Draws the flag of Greece.
   * 
   * @param imageWidth
   */
  public GreeceFlag(int imageWidth) {
    super(imageWidth);
    setRGB(generateHorizontalStripes(imageWidth));
  }

  protected int[][][] generateHorizontalStripes(int imageWidth) {
    Color color = Color.blue;
    int imageHeight = (36 * imageWidth) / 54;
    int[][][] newRGB = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];

    for (int i = 0; i < imageHeight; i++) {

      for (int j = 0; j < imageWidth; j++) {

        if (0 <= i && i < 4 * imageWidth / 54 && 0 <= j && j < 8 * imageWidth / 54) {
          color = Color.blue;
        }
        if (0 <= i && i < 4 * imageWidth / 54 && 8 * imageWidth / 54 <= j
            && j < 12 * imageWidth / 54) {
          color = Color.white;
        }

        if (0 <= i && i < 4 * imageWidth / 54 && 12 * imageWidth / 54 <= j && j < imageWidth) {
          color = Color.blue;
        }

        if (4 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 0 <= j
            && j < 8 * imageWidth / 54) {
          color = Color.blue;
        }

        if (4 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 8 * imageWidth / 54 <= j
            && j < 20 * imageWidth / 54) {
          color = Color.white;
        }

        if (4 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 12 * imageWidth / 54 <= j
            && j < 20 * imageWidth / 54) {
          color = Color.blue;
        }

        if (4 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 20 * imageWidth / 54 <= j
            && j < imageWidth) {
          color = Color.white;
        }

        if (8 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 0 <= j
            && j < 20 * imageWidth / 54) {
          color = Color.white;
        }

        if (8 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 20 * imageWidth / 54 <= j
            && j < imageWidth) {
          color = Color.blue;
        }

        if (12 * imageWidth / 54 <= i && i < 16 * imageWidth / 54 && 0 <= j
            && j < 8 * imageWidth / 54) {
          color = Color.blue;
        }

        if (12 * imageWidth / 54 <= i && i < 16 * imageWidth / 54 && 8 * imageWidth / 54 <= j
            && j < 12 * imageWidth / 54) {
          color = Color.white;
        }

        if (12 * imageWidth / 54 <= i && i < 16 * imageWidth / 54 && 12 * imageWidth / 54 <= j
            && j < 20 * imageWidth / 54) {
          color = Color.blue;
        }

        if (12 * imageWidth / 54 <= i && i < 16 * imageWidth / 54 && 20 * imageWidth / 54 <= j
            && j < imageWidth) {
          color = Color.white;
        }

        if (16 * imageWidth / 54 <= i && i < 20 * imageWidth / 54 && 0 <= j
            && j < 8 * imageWidth / 54) {
          color = Color.blue;
        }

        if (16 * imageWidth / 54 <= i && i < 20 * imageWidth / 54 && 8 * imageWidth / 54 <= j
            && j < 12 * imageWidth / 54) {
          color = Color.white;
        }

        if (16 * imageWidth / 54 <= i && i < 20 * imageWidth / 54 && 12 * imageWidth / 54 <= j
            && j < imageWidth) {
          color = Color.blue;
        }

        if (20 * imageWidth / 54 <= i && i < 24 * imageWidth / 54 && 0 <= j && j < imageWidth) {
          color = Color.white;
        }

        if (24 * imageWidth / 54 <= i && i < 28 * imageWidth / 54 && 0 <= j && j < imageWidth) {
          color = Color.blue;
        }

        if (28 * imageWidth / 54 <= i && i < 32 * imageWidth / 54 && 0 <= j && j < imageWidth) {
          color = Color.white;
        }

        if (32 * imageWidth / 54 <= i && i < 36 * imageWidth / 54 && 0 <= j && j < imageWidth) {
          color = Color.blue;
        }
        int[] colorChannelMagnitudes = getColorChannelMagnitudes(color);
        for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
          newRGB[i][j][k] = colorChannelMagnitudes[k];
        }
      }
    }
    return newRGB;
  }
}
