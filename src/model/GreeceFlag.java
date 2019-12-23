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

    int verticalStripeLeftEdge = (int) (((double) 8 / (double) 54) * imageWidth);
    int verticalStripeWidth = (int) (((double) 4 / (double) 54) * imageWidth);

    Color color = Color.blue;
    int[] colorChannelMagnitudes = getColorChannelMagnitudes(color);
    for (int h = 0; h < 5; h++) {
      for (int i = h * stripeHeight; i < (h + 1) * stripeHeight; i++) {
        colorChannelMagnitudes = getColorChannelMagnitudes(color);
        for (int g = 0; g < 5; g++) {
          for (int j = g * verticalStripeWidth; j < (g + 1) * verticalStripeWidth; j++) {

            if (2 * verticalStripeWidth < j && j < 3 * verticalStripeWidth) {
              color = Color.white;
            } else {
              color = Color.blue;
            }

            if (2 * stripeHeight < i && i < 3 * stripeHeight && 0 < j
                && j < 5 * verticalStripeWidth) {
              color = Color.white;
            }

            colorChannelMagnitudes = getColorChannelMagnitudes(color);

            for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
              newRGB[i][j][k] = colorChannelMagnitudes[k];
            }
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
