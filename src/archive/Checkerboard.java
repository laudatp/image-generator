/**
 * 
 */
package image.archive;

import java.awt.Color;

/**
 * Generate checkerboard patterned model.
 *
 * @param width this model's checkerboard squares' length
 */
public class Checkerboard {
  private static final Color[] COLORS = {Color.black,Color.white};

  /**
   * @param height
   * @param squareWidth
   */
  public Checkerboard(int squareWidth) {
    super(squareWidth);
    setRGB(generateCheckerboard(squareWidth));
  }

  /**
   * Generates 8x8 checkerboard with user-provided checkerboard square width. Assumes black/white
   * colors.
   *
   * @param  squareWidth user-provided checkerboard square width
   * @return             checkerboard model
   */
  private int[][][] generateCheckerboard(int squareWidth) {

    int[] channels = new int[3]; // Will hold pixel's RGB channels
    int imageWidth = 8 * squareWidth; // Model length since height and width
    int imageHeight = imageWidth;

    // Pixel colors represented by their integer rgb values
    int[][] colorChannels = new int[COLORS.length][NUMBER_OF_CHANNELS];

    int[][][] newRGB = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];

    // Get the pixel color channels
    for (int i = 0; i < COLORS.length; i++) {
      for (int j = 0; j < NUMBER_OF_CHANNELS; j++) {
        colorChannels[i] = getColorChannelMagnitudes(COLORS[i]);
      }
    }

    boolean isColor0Active = true;
    channels = colorChannels[0];

    // Populate the checkerboard model array with color channel values user-provided square width by
    // user-provided square width, row by row, assuming NUM_ROWS_AND_COLS number of rows and
    // columns.
    int numberOfSquaresPerRowOrColumn = 8;
    for (int i = 0; i < numberOfSquaresPerRowOrColumn; i++) {
      for (int ii = i * squareWidth; ii < squareWidth * (i + 1); ii++) { // Square height
        for (int j = 0; j < numberOfSquaresPerRowOrColumn; j++) {
          for (int jj = j * squareWidth; jj < squareWidth * (j + 1); jj++) {// Square width
            for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
              newRGB[ii][jj][k] = channels[k];
            }
          }
          // Swap color channels to create the alternating colors across the columns
          isColor0Active = !isColor0Active;
          channels = swapColorChannels(isColor0Active, colorChannels);
        }
      }
      // Swap color channels to create the alternating colors down the rows
      isColor0Active = !isColor0Active;
      channels = swapColorChannels(isColor0Active, colorChannels);
    }
    return newRGB;
  }

  /**
   * Swap color channels.
   *
   * @param  isColor0Active True if colors[0] is active, false otherwise.
   * @param  colorChannels  color's integer rgb channel values
   * @return                colorChannels swapped color channel
   */
  private int[] swapColorChannels(boolean isColor0Active, int[][] colorChannels) {
    return isColor0Active ? colorChannels[0] : colorChannels[1];
  }

}
