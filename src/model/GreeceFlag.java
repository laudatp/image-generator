/**
 * 
 */
package model;

import java.awt.Color;

/**
 * Draws the flag of Greece.
 * 
 * @author Peter
 *
 */
public class GreeceFlag extends ImageImpl {

  /**
   * Draws the flag of Greece.
   * 
   * @param imageWidth
   */
  public GreeceFlag(int imageWidth) {
    super(imageWidth);
    rgb = generateHorizontalStripes(imageWidth);
  }

  protected int[][][] generateHorizontalStripes(int imageWidth) {
    int stripeCount = 9;
    int imageHeight = (int) (((double) 2 / (double) 3) * imageWidth);
    int stripeHeight = (int) (((double) imageHeight) / ((double) stripeCount));
    int[][][] newRGB = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];
    Color color = Color.blue;

    for (int h = 0; h < stripeCount; h++) {
      for (int i = h * stripeHeight; i < (h + 1) * stripeHeight; i++) {
        int[] colorChannelMagnitudes = getColorChannelMagnitudes(color);
        for (int j = 0; j < imageWidth; j++) {
          for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
            newRGB[i][j][k] = colorChannelMagnitudes[k];
          }
        }
      }
      color = swapColors(color);
    }

    return newRGB;
  }

  private Color swapColors(Color color) {
    Color newColor = color;
    if (color.equals(Color.blue)) {
      newColor = Color.white;
    } else if (color.equals(Color.white)) {
      newColor = Color.blue;
    }
    return newColor;
  }

}
