/**
 * 
 */
package image.archive;

import java.awt.Color;

/**
 * Generate rectangular model of vertically striped rainbow.
 *
 */
public class VerticalRainbow {

  public VerticalRainbow(int imageHeight, int imageWidth) {
    super(imageHeight, imageWidth);
    setRGB(generateVerticalStripes(imageHeight, imageWidth, getRainbowColors()));
  }

  private int[][][] generateVerticalStripes(int imageHeight, int imageWidth, Color[] colors) {
    int colorCount = colors.length;
    int[][][] newRgb = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];
    // Calculate the stripe thickness
    int stripeThickness = (int) Math.round((double) imageWidth / colorCount);
    for (int h = 0; h < colorCount; h++) {
      int[] channelColors = {colors[h].getRed(),colors[h].getGreen(),colors[h].getBlue()};
      for (int i = 0; i < imageHeight; i++) {
        if (h < colorCount - 1) {
          for (int j = h * stripeThickness; j < (h + 1) * stripeThickness; j++) {
            for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
              newRgb[i][j][k] = channelColors[k];
            }
          }
        } else {
          for (int j = h * stripeThickness; j < imageWidth; j++) {
            for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
              newRgb[i][j][k] = channelColors[k];
            }
          }
        }
      }
    }
    return newRgb;
  }

}
