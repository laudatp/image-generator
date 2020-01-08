package image.model;

import java.awt.Color;
import java.io.IOException;

public class ImageImpl implements Image {
    /**
     * Count of color channels.
     */
    protected static final int NUMBER_OF_CHANNELS = 3;

    /**
     * Represents image's pixels by means of rows and columns of overlaid red,
     * green, blue channels with various magnitudes.
     */
    private int[][][] rgb;

    public ImageImpl() {
    }

    public ImageImpl(int height, int width) {
	rgb = new int[height][width][NUMBER_OF_CHANNELS];
    }

    public ImageImpl(int width) {
	rgb = new int[width][width][NUMBER_OF_CHANNELS];
    }

    /**
     * Loads this image from a file.
     *
     * @param imageInfile full pathname of the .jpg, .png, file to be read
     */
    @Override
    public void load(String imageInfile) throws IOException {
	rgb = ImageUtil.readImage(imageInfile);
    }

    /**
     * Saves this image to a .jpg or .png image file.
     *
     * @param imageOutfile full path name for the file to be written
     */
    @Override
    public void save(String imageOutfile) throws IOException {
	ImageUtil.writeImage(rgb, rgb[0].length, rgb.length, imageOutfile);
    }

    /**
     * Blurs this image.
     */
    @Override
    public void blur() {
	// Replace original image with blurred image.
	rgb = blurRGB();
    }

    /**
     * Sharpen this image.
     */
    @Override
    public void sharpen() {
	rgb = sharpenRGB();
    }

    /**
     * Grayscale this image.
     */
    @Override
    public void grayscale() {
	rgb = grayscaleRGB();
    }

    /**
     * Sepia this image.
     */
    @Override
    public void sepia() {
	rgb = sepiaRGB();
    }

    private int[][][] blurRGB() {
	return filterRGB(getBlurKernel());
    }

    private int[][][] sharpenRGB() {
	return filterRGB(getSharpenKernel());
    }

    private int[][][] grayscaleRGB() {
	return transformRGB(getGrayscaleKernel());
    }

    private int[][][] transformRGB(double[][] kernel) {
	int nRows = rgb.length;
	int nCols = rgb[1].length;
	int kernelLength = kernel.length;
	int[][][] newRGB = new int[nRows][nCols][NUMBER_OF_CHANNELS];
	for (int i = 0; i < nRows; i++) {
	    for (int j = 0; j < nCols; j++) {
		for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
		    double temp = 0;
		    for (int l = 0; l < kernelLength; l++) {
			temp += kernel[k][l] * rgb[i][j][l];
			newRGB[i][j][k] = clamp(temp);
		    }
		}
	    }
	}
	return newRGB;
    }

    private double[][] getGrayscaleKernel() {
	double[] kernelRow = { 0.2126, 0.7152, 0.0722 };
	double[][] kernel = new double[3][3];
	for (int i = 0; i < 3; i++) {
	    kernel[i] = kernelRow;
	}
	return kernel;
    }

    protected int[][][] sepiaRGB() {
	return transformRGB(getSepiaKernel());
    }

    private double[][] getSepiaKernel() {
	double[][] kernel = new double[3][3];
	kernel[0][0] = 0.393;
	kernel[0][1] = 0.769;
	kernel[0][2] = 0.189;
	kernel[1][0] = 0.349;
	kernel[1][1] = 0.686;
	kernel[1][2] = 0.168;
	kernel[2][0] = 0.272;
	kernel[2][1] = 0.534;
	kernel[2][2] = 0.131;
	return kernel;
    }

    /**
     * Get red, green, and blue channel color magnitudes for a color.
     * 
     * @param color color
     * @return color channel magnitudes
     */
    private int[] getColorChannelMagnitudes(Color color) {
	return new int[] { color.getRed(), color.getGreen(), color.getBlue() };
    }

    /**
     * Filter image (blur or sharpen it).
     * 
     * @param kernel
     * @return filtered image
     */
    private int[][][] filterRGB(double[][] kernel) {
	// Get the count of the image's rows.
	int nRows = rgb.length;
	// Get the count of the image's columns.
	int nCols = rgb[0].length;

	// Create filtered image holder to capture the modified pixels
	int[][][] filteredRgb = new int[nRows][nCols][NUMBER_OF_CHANNELS];

	// Filter each pixel (row, column, channel)
	for (int row = 0; row < nRows; row++) {
	    for (int col = 0; col < nCols; col++) {
		for (int channel = 0; channel < NUMBER_OF_CHANNELS; channel++) {
		    filteredRgb[row][col][channel] = getFilteredPixel(row, col, channel, kernel);
		}
	    }
	}
	return filteredRgb;
    }

    /**
     * Filters this provided pixel channel at its row and column with the provided
     * kernel by means of matrix multiplication between the kernel matrix and the
     * pixels it overlaps.
     *
     * @param rgbRow  pixel row
     * @param rgbCol  pixel column
     * @param channel pixel channel
     * @param kernel  filtering kernel or matrix
     * @return the result of the matrix multiplication or getFilteredPixel of the
     *         pixel
     */
    private int getFilteredPixel(int rgbRow, int rgbCol, int channel, double[][] kernel) {

	// Get the count of the image's rows and columns.
	int rgbRowCount = rgb.length;
	int rgbColCount = rgb[0].length;

	// Get the count of the kernel's rows and columns.
	int kRowCount = kernel.length;
	int kColCount = kernel[0].length;

	// Calculate the edge overlap when overlaying this kernel's center over this
	// image's edge
	// pixels.
	int overlap = kernel.length / 2;

	double filteredPixel = 0.0;

	// Matrix multiply the kernel matrix times the matrix of pixels it overlays when
	// the kernel
	// center is placed
	// over the the provided image pixel (rgbRow, rgbCol, channel).
	for (int i = 0; i < kRowCount; i++) {
	    int kernelRowOverlapIndex = rgbRow - overlap + i;
	    for (int j = 0; j < kColCount; j++) {
		int kernelColOverlapIndex = rgbCol - overlap + j;
		if (((kernelRowOverlapIndex > -1) && (kernelRowOverlapIndex < rgbRowCount))
			&& ((kernelColOverlapIndex > -1) && (kernelColOverlapIndex < rgbColCount))) {
		    filteredPixel += kernel[i][j] * rgb[kernelRowOverlapIndex][kernelColOverlapIndex][channel];
		} else {
		    filteredPixel += 0.0;
		}
	    }
	}
	// Clamp (convert it to an integer and constrain it to between 0 and 255 both
	// inclusive) and
	// return the
	// filtered
	// pixel value.
	return clamp(filteredPixel);
    }

    /**
     * Convert channel value to integer and constrain the value between 0 inclusive
     * and 255 inclusive.
     *
     * @param channel channel
     * @return integer value between 0 inclusive and 255 inclusive
     */
    private int clamp(double channel) {
	int value = (int) channel;
	if (value < 0) {
	    value = 0;
	} else if (value > 255) {
	    value = 255;
	}
	return value;
    }

    /**
     * Calculates and returns 3x3 blur kernel matrix.
     *
     * @return 3x3 blur kernel matrix
     */
    private double[][] getBlurKernel() {
	double[][] kernel = new double[3][3];
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		if ((i == 0 || i == 2) && (j == 0 || j == 2)) {
		    kernel[i][j] = (double) 1 / (double) 16;
		} else if (i == 1 && j == 1) {
		    kernel[i][j] = (double) 1 / (double) 4;
		} else {
		    kernel[i][j] = (double) 1 / (double) 8;
		}
	    }
	}
	return kernel;
    }

    /**
     * Calculates and returns 5x5 blur kernel matrix.
     *
     * @return 5x5 blur kernel matrix
     */
    private double[][] getSharpenKernel() {
	double[][] kernel = new double[5][5];
	for (int i = 0; i < 5; i++) {
	    for (int j = 0; j < 5; j++) {
		if ((i == 0 || i == 4 || j == 0 || j == 4)) {
		    kernel[i][j] = (double) -1 / (double) 8;
		} else if (i == 2 && j == 2) {
		    kernel[i][j] = 1.0;
		} else {
		    kernel[i][j] = (double) 1 / (double) 4;
		}
	    }
	}
	return kernel;
    }

    /**
     * Creates and returns the seven rainbow colors, red, orange, yellow, green,
     * blue, indigo, violet (ROYGBIV).
     *
     * @return rainbow the seven rainbow colors
     */
    private Color[] getRainbowColors() {
	return new Color[] { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, new Color(75, 0, 130),
		new Color(143, 0, 255) };
    }

    public int[][][] getRgb() {
	return this.rgb;
    }

    /**
     * Set rgb.
     * 
     * @param rgb the rgb to set
     */
    public void setRGB(int[][][] rgb) {
	this.rgb = rgb;
    }

    @Override
    public void drawHorizontalRainbowStripes(int imageHeight, int imageWidth) {
	setRGB(generateHorizontalStripes(imageHeight, imageWidth));
    }

    /**
     * Generate horizontally striped image assuming one stripe per unique color.
     *
     * @param imageHeight stripe height
     * @param imageWidth  stripe width
     * @return newRGB image
     */
    private int[][][] generateHorizontalStripes(int imageHeight, int imageWidth) {
	Color[] colors = getRainbowColors();
	int colorCount = colors.length;
	int[][][] newRgb = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];
	// Calculate the stripe thickness
	int stripeThickness = (int) Math.round((double) imageHeight / colorCount);
	// Generate each stripe one pixel thickness at a time until full stripe
	// thickness is reached and assign each stripe to the image
	for (int h = 0; h < colorCount; h++) {
	    int[] channelColors = { colors[h].getRed(), colors[h].getGreen(), colors[h].getBlue() };
	    if (h < colorCount - 1) {
		paintTheRows(imageWidth, newRgb, stripeThickness, h, channelColors);
	    } else {
		for (int i = h * stripeThickness; i < imageHeight; i++) {
		    paintTheColumns(imageWidth, newRgb, channelColors, i);
		}
	    }
	}
	return newRgb;
    }

    /**
     * Iterate the paint brush down the image, pixel row by pixel row.
     * 
     * @param imageWidth
     * @param newRgb
     * @param stripeThickness
     * @param h
     * @param channelColors
     */
    private void paintTheRows(int imageWidth, int[][][] newRgb, int stripeThickness, int h, int[] channelColors) {
	for (int i = h * stripeThickness; i < (h + 1) * stripeThickness; i++) {
	    paintTheColumns(imageWidth, newRgb, channelColors, i);
	}
    }

    /**
     * Iterate the paint brush across the columns of each pixel row.
     * 
     * @param imageWidth
     * @param newRgb
     * @param channelColors
     * @param i
     */
    private void paintTheColumns(int imageWidth, int[][][] newRgb, int[] channelColors, int i) {
	for (int j = 0; j < imageWidth; j++) {
	    paintTheChannels(newRgb, channelColors, i, j);
	}
    }

    /**
     * Paint the red, green, and blue (rgb) channels of a pixel.
     * 
     * @param newRgb
     * @param channelColors
     * @param i
     * @param j
     */
    private void paintTheChannels(int[][][] newRgb, int[] channelColors, int i, int j) {
	for (int k = 0; k < NUMBER_OF_CHANNELS; k++) {
	    newRgb[i][j][k] = channelColors[k];
	}
    }

    @Override
    public void drawVerticalRainbowStripes(int imageHeight, int imageWidth) {
	setRGB(generateVerticalStripes(imageHeight, imageWidth));
    }

    private int[][][] generateVerticalStripes(int imageHeight, int imageWidth) {
	Color[] colors = getRainbowColors();
	int colorCount = colors.length;
	int[][][] newRgb = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];
	// Calculate the stripe thickness
	int stripeThickness = (int) Math.round((double) imageWidth / colorCount);
	for (int h = 0; h < colorCount; h++) {
	    int[] channelColors = { colors[h].getRed(), colors[h].getGreen(), colors[h].getBlue() };
	    for (int i = 0; i < imageHeight; i++) {
		if (h < colorCount - 1) {
		    for (int j = h * stripeThickness; j < (h + 1) * stripeThickness; j++) {
			paintTheChannels(newRgb, channelColors, i, j);
		    }
		} else {
		    for (int j = h * stripeThickness; j < imageWidth; j++) {
			paintTheChannels(newRgb, channelColors, i, j);
		    }
		}
	    }
	}
	return newRgb;
    }

    @Override
    public void drawCheckerBoard(int cellWidth) {
	setRGB(generateCheckerboard(cellWidth));
    }

    /**
     * Generates 8x8 checkerboard with user-provided checkerboard square width.
     * Assumes black/white colors.
     *
     * @param squareWidth user-provided checkerboard square width
     * @return checkerboard image
     */
    private int[][][] generateCheckerboard(int squareWidth) {
	Color[] colors = { Color.black, Color.white };
	int[] channels = new int[3]; // Will hold pixel's RGB channels
	int imageWidth = 8 * squareWidth; // Image length since height and width
	int imageHeight = imageWidth;

	// Pixel colors represented by their integer rgb values
	int[][] colorChannels = new int[colors.length][NUMBER_OF_CHANNELS];

	int[][][] newRGB = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];

	// Get the pixel color channels
	for (int i = 0; i < colors.length; i++) {
	    for (int j = 0; j < NUMBER_OF_CHANNELS; j++) {
		colorChannels[i] = getColorChannelMagnitudes(colors[i]);
	    }
	}

	boolean isColor0Active = true;
	channels = colorChannels[0];

	// Populate the checkerboard image array with color channel values user-provided
	// square width by
	// user-provided square width, row by row, assuming NUM_ROWS_AND_COLS number of
	// rows and
	// columns.
	int numberOfSquaresPerRowOrColumn = 8;
	for (int i = 0; i < numberOfSquaresPerRowOrColumn; i++) {
	    for (int ii = i * squareWidth; ii < squareWidth * (i + 1); ii++) { // Square height
		for (int j = 0; j < numberOfSquaresPerRowOrColumn; j++) {
		    for (int jj = j * squareWidth; jj < squareWidth * (j + 1); jj++) {// Square width
			paintTheChannels(newRGB, channels, ii, jj);
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
     * @param isColor0Active True if colors[0] is active, false otherwise.
     * @param colorChannels  color's integer rgb channel values
     * @return colorChannels swapped color channel
     */
    private int[] swapColorChannels(boolean isColor0Active, int[][] colorChannels) {
	return isColor0Active ? colorChannels[0] : colorChannels[1];
    }

    @Override
    public void drawFranceFlag(int flagWidth) {
	setRGB(generateFranceFlagStripes(flagWidth));
    }

    private int[][][] generateFranceFlagStripes(int flagWidth) {
	Color[] colors = { Color.blue, Color.white, Color.red };
	int colorCount = colors.length;

	int imageHeight = 2 * flagWidth / 3;
	int[][][] newRgb = new int[imageHeight][flagWidth][NUMBER_OF_CHANNELS];

	// Calculate the stripe thickness
	int stripeThickness = (int) Math.round((double) flagWidth / colorCount);
	for (int h = 0; h < colorCount; h++) {
	    int[] stripePixelColors = { colors[h].getRed(), colors[h].getGreen(), colors[h].getBlue() };
	    for (int i = 0; i < imageHeight; i++) {
		if (h < colorCount - 1) {
		    for (int j = h * stripeThickness; j < (h + 1) * stripeThickness; j++) {
			paintTheChannels(newRgb, stripePixelColors, i, j);
		    }
		} else {
		    for (int j = h * stripeThickness; j < flagWidth; j++) {
			paintTheChannels(newRgb, stripePixelColors, i, j);
		    }
		}
	    }
	}
	return newRgb;
    }

    @Override
    public void drawSwitzerlandFlag(int flagWidth) {
	setRGB(generateHorizontalStripes(flagWidth));
    }

    private int[][][] generateHorizontalStripes(int imageWidth) {
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

		if ((13 * imageWidth / 32 <= i && i < 19 * imageWidth / 32) && (0 <= j && j < 6 * imageWidth / 32)
			|| (26 * imageWidth / 32 <= j)) {
		    color = Color.red;
		}

		if (13 * imageWidth / 32 <= i && i < 19 * imageWidth / 32 && 6 * imageWidth / 32 <= j
			&& j < 26 * imageWidth / 32) {
		    color = Color.white;
		}

		if (19 * imageWidth / 32 <= i && i < 26 * imageWidth / 32 && 0 <= j && j < 13 * imageWidth / 32) {
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
		paintTheChannels(newRGB, colorChannelMagnitudes, i, j);
	    }
	}
	return newRGB;
    }

    @Override
    public void drawGreeceFlag(int flagWidth) {
	setRGB(generateGreeceFlagStripes(flagWidth));
    }

    private int[][][] generateGreeceFlagStripes(int imageWidth) {
	Color color = Color.blue;
	int imageHeight = (36 * imageWidth) / 54;
	int[][][] newRGB = new int[imageHeight][imageWidth][NUMBER_OF_CHANNELS];

	for (int i = 0; i < imageHeight; i++) {

	    for (int j = 0; j < imageWidth; j++) {

		if (0 <= i && i < 4 * imageWidth / 54 && 0 <= j && j < 8 * imageWidth / 54) {
		    color = Color.blue;
		}
		if (0 <= i && i < 4 * imageWidth / 54 && 8 * imageWidth / 54 <= j && j < 12 * imageWidth / 54) {
		    color = Color.white;
		}

		if (0 <= i && i < 4 * imageWidth / 54 && 12 * imageWidth / 54 <= j && j < imageWidth) {
		    color = Color.blue;
		}

		if (4 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 0 <= j && j < 8 * imageWidth / 54) {
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

		if (8 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 0 <= j && j < 20 * imageWidth / 54) {
		    color = Color.white;
		}

		if (8 * imageWidth / 54 <= i && i < 12 * imageWidth / 54 && 20 * imageWidth / 54 <= j
			&& j < imageWidth) {
		    color = Color.blue;
		}

		if (12 * imageWidth / 54 <= i && i < 16 * imageWidth / 54 && 0 <= j && j < 8 * imageWidth / 54) {
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

		if (16 * imageWidth / 54 <= i && i < 20 * imageWidth / 54 && 0 <= j && j < 8 * imageWidth / 54) {
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
		paintTheChannels(newRGB, colorChannelMagnitudes, i, j);
	    }
	}
	return newRGB;
    }

}
