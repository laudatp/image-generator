/**
 * 
 */
package model;

import java.awt.Color;

/**
 * Rectangular horizontally striped rainbow image.
 */
public class HorizontalRainbow extends ImageImpl {

  public HorizontalRainbow(int imageHeight, int imageWidth) {
    super(imageHeight, imageWidth);
    rgb = generateHorizontalStripes(imageHeight, imageWidth);
  }

  /**
   * Generate horizontally striped image assuming one stripe per unique color.
   *
   * @param  imageHeight stripe height
   * @param  imageWidth  stripe width
   * @return             newRGB image
   */
  protected int[][][] generateHorizontalStripes(int imageHeight, int imageWidth) {
    Color[] colors = getRainbowColors();
    int colorCount = colors.length;
    int[][][] newRgb = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];
    // Calculate the stripe thickness
    int stripeThickness = (int) ((double) imageHeight / (double) colorCount);
    // Generate each stripe one pixel thickness at a time until full stripe thickness is reached and
    // assign each stripe to the image
    for (int h = 0; h < colorCount; h++) {
      int[] stripePixelColors = {colors[h].getRed(),colors[h].getGreen(),colors[h].getBlue()};
      for (int i = h * stripeThickness; i < (h + 1) * stripeThickness; i++) {
        for (int j = 0; j < imageWidth; j++) {
          for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
            newRgb[i][j][k] = stripePixelColors[k];
          }
        }
      }
    }
    return newRgb;
  }

}
