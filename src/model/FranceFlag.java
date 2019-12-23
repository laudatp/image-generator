/**
 * 
 */
package model;

import java.awt.Color;

/**
 * @author Peter
 *
 */
public class FranceFlag extends ImageImpl {

  public FranceFlag(int width) {
    super(width);

    int height = (int) (((double) 2 / (double) 3) * width);

    Color[] colors = {Color.blue,Color.white,Color.red};

    rgb = generateUniqueVerticalStripesProportionalToImageWidth(height, width, colors);
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
