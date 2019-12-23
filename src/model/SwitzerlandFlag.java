/**
 * 
 */
package model;

import java.awt.Color;

/**
 * Draws the flag of Switzerland.
 * 
 * @author Peter
 *
 */
public class SwitzerlandFlag extends ImageImpl {

  /**
   * Draws the flag of Switzerland.
   * 
   * @param imageWidth
   */
  public SwitzerlandFlag(int imageWidth) {
    super(imageWidth);
    rgb = generateHorizontalStripes(imageWidth);
  }

  protected int[][][] generateHorizontalStripes(int imageWidth) {
    Color color = Color.red;
    int imageHeight = imageWidth;
    int[][][] newRGB = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];

    for (int i = 0; i < imageHeight; i++) {

      for (int j = 0; j < imageWidth; j++) {

        if ((0 <= i && i < 6 * imageWidth / 32) || 26 * imageWidth / 32 <= i) {
          color = Color.red;
        }
        if (6 * imageWidth / 32 <= i && i < 13 * imageWidth / 32 && j < 13 * imageWidth / 32) {
          color = Color.red;
        }

        if (6 * imageWidth / 32 <= i && i < 13 * imageWidth / 32 && 13 * imageWidth / 32 <= j
            && j < 19 * imageWidth / 32) {
          color = Color.white;
        }

        if (6 * imageWidth / 32 <= i && i < 13 * imageWidth / 32 && 19 * imageWidth / 32 <= j) {
          color = Color.red;
        }

        if ((13 * imageWidth / 32 <= i && i < 19 * imageWidth / 32)
            && (0 <= j && j < 6 * imageWidth / 32) || (26 * imageWidth / 32 <= j)) {
          color = Color.red;
        }

        if (13 * imageWidth / 32 <= i && i < 19 * imageWidth / 32 && 6 * imageWidth / 32 <= j
            && j < 26 * imageWidth / 32) {
          color = Color.white;
        }

        if (19 * imageWidth / 32 <= i && i < 26 * imageWidth / 32 && 0 <= j
            && j < 13 * imageWidth / 32) {
          color = Color.red;
        }

        if (19 * imageWidth / 32 <= i && i < 26 * imageWidth / 32 && 13 * imageWidth / 32 <= j
            && j < 19 * imageWidth / 32) {
          color = Color.white;
        }

        if (19 * imageWidth / 32 <= i && i < 26 * imageWidth / 32 && 19 * imageWidth / 32 <= j) {
          color = Color.red;
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
