/**
 * 
 */
package image.archive;

import java.awt.Color;

/**
 * @author Peter
 *
 */
public class FranceFlag {
  private static final Color[] COLORS = {Color.blue,Color.white,Color.red};

  public FranceFlag(int imageWidth) {
    super(imageWidth);
    int imageHeight = 2 * imageWidth / 3;
    setRGB(generateVerticalStripes(imageHeight, imageWidth));
  }

  protected int[][][] generateVerticalStripes(int imageHeight, int imageWidth) {
    int colorCount = COLORS.length;
    int[][][] newRgb = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];
    // Calculate the stripe thickness
    int stripeThickness = (int) Math.round((double) imageWidth / colorCount);
    for (int h = 0; h < colorCount; h++) {
      int[] stripePixelColors = {COLORS[h].getRed(),COLORS[h].getGreen(),COLORS[h].getBlue()};
      for (int i = 0; i < imageHeight; i++) {
        if (h < colorCount - 1) {
          for (int j = h * stripeThickness; j < (h + 1) * stripeThickness; j++) {
            for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
              newRgb[i][j][k] = stripePixelColors[k];
            }
          }
        } else {
          for (int j = h * stripeThickness; j < imageWidth; j++) {
            for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
              newRgb[i][j][k] = stripePixelColors[k];
            }
          }
        }
      }
    }
    return newRgb;
  }
}
