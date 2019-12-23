/**
 * 
 */
package model;

import java.awt.Color;

/**
 * Generate rectangular image of vertically striped rainbow.
 *
 * @param height this image's width
 * @param width  this image's length
 */
public class VerticalRainbow extends ImageImpl {

  public VerticalRainbow(int height, int width) {
    super(height, width);
    rgb = generateUniqueVerticalStripesProportionalToImageWidth(height, width, getRainbowColors());
  }

  protected int[][][] generateUniqueVerticalStripesProportionalToImageWidth(int height, int width,
      Color[] colors) {
    int colorCount = colors.length;
    int[][][] newRgb = new int[height][width][NUMBER_OF_CHANNELS];
    // Calculate the stripe thickness
    int stripeThickness = (int) ((double) width / (double) colorCount);
    for (int h = 0; h < colorCount; h++) {
      int[] stripePixelColors = {colors[h].getRed(),colors[h].getGreen(),colors[h].getBlue()};
      for (int i = 0; i < height; i++) {
        for (int j = h * stripeThickness; j < (h + 1) * stripeThickness; j++) {
          for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
            newRgb[i][j][k] = stripePixelColors[k];
          }
        }
      }
    }
    return newRgb;
  }

}
