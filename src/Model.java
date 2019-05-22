import java.awt.*;
import java.io.IOException;

public class Model implements IModel {
    /**
     * Count of color channels.
     */
    private static int CHANNEL_COUNT = 3;

    /**
     * Represents image's pixels by means of rows and columns of overlaid red, green, blue channels with various
     * magnitudes.
     */
    private int[][][] rgb;

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
        //Replace original image with blurred image.
        rgb = getFilteredRgb(getBlurKernel());
    }


    /**
     * Sharpen this image.
     */
    @Override
    public void sharpen() {
        //Replace original image with blurred image.
        rgb = getFilteredRgb(getSharpenKernel());
    }

    /**
     * Grayscale this image.
     */
    @Override
    public void grayscale() {

    }

    /**
     * Sepia this image.
     */
    @Override
    public void sepia() {

    }

    /**
     * Generate horizontal rainbow-striped image.
     *
     * @param height
     * @param width
     */
    @Override
    public void generateHorizontalRainbowStripeImage(int height, int width) {
        rgb = generateHorizontalStripes(height, width, getRainbowColors());
    }

    private int[][][] generateHorizontalStripes(int height, int width, Color[] rainbowColors) {
        int stripeCount = rainbowColors.length;
        int[][][] rainbow = new int[height][width][CHANNEL_COUNT];
        //Calculate the stripe thickness
        int stripeThickness = (int) ((double) height / (double) stripeCount);
            for (int h = 0; h < stripeCount; h++) {
                int[] stripeRgb = {rainbowColors[h].getRed(), rainbowColors[h].getGreen(), rainbowColors[h].getBlue()};
                for (int i = h*stripeThickness; i < (h+1)*stripeThickness; i++) {
                    for (int j = 0; j < width; j++) {
                        for (int k = 0; k < CHANNEL_COUNT; k++) {
                            rainbow[i][j][k] = stripeRgb[k];
                        }
                    }
                }
            }
        return rainbow;
    }




    /**
     * Generate vertical rainbow-striped image.
     *
     * @param height this image's width
     * @param width  this image's length
     */
    @Override
    public void generateVerticalRainbowStripeImage(int height, int width) {
        rgb = generateVerticalStripes(height, width, getRainbowColors());
    }

    private int[][][] generateVerticalStripes(int height, int width, Color[] rainbowColors) {
        int stripeCount = rainbowColors.length;
        int[][][] rainbow = new int[height][width][CHANNEL_COUNT];
        //Calculate the stripe thickness
        int stripeThickness = (int) ((double) width / (double) stripeCount);
        for (int h = 0; h < stripeCount; h++) {
            int[] stripeRgb = {rainbowColors[h].getRed(), rainbowColors[h].getGreen(), rainbowColors[h].getBlue()};
            for (int i = 0; i < height; i++) {
                for (int j = h*stripeThickness; j < (h+1)*stripeThickness; j++) {
                    for (int k = 0; k < CHANNEL_COUNT; k++) {
                        rainbow[i][j][k] = stripeRgb[k];
                    }
                }
            }
        }
        return rainbow;
    }

    /**
     * Generate checkerboard patterned image.
     *
     * @param width this image's checkerboard squares' length
     */
    @Override
    public void generateCheckerBoard(int width) {

    }

    /**
     * Generate image of France's flag.
     *
     * @param width this image of France's flag's width
     */
    @Override
    public void generateFranceFlag(int width) {

    }

    /**
     * Generate image of Greece's flag.
     *
     * @param width this image of Greece's flag's width
     */
    @Override
    public void generateGreeceFlag(int width) {

    }

    /**
     * Generate image of Switzerland's flag.
     *
     * @param width this image of Switzerland's flag's width
     */
    @Override
    public void generateSwitzerlandFlag(int width) {

    }

    public int[][][] getFilteredRgb(double[][] kernel) {
        //Get the count of the image's rows.
        int rgbRowCount = rgb.length;
        //Get the count of the image's columns.
        int rgbColCount = rgb[0].length;

        //Create blurred image holder to capture the modified pixels
        int[][][] filteredRgb = new int[rgbRowCount][rgbColCount][CHANNEL_COUNT];

        //Filter each pixel (row, column, channel)
        for (int rgbRow = 0; rgbRow < rgbRowCount; rgbRow++) {
            for (int rgbCol = 0; rgbCol < rgbColCount; rgbCol++) {
                for (int channel = 0; channel < CHANNEL_COUNT; channel++) {
                    filteredRgb[rgbRow][rgbCol][channel] = getFilteredPixel(rgbRow, rgbCol, channel, kernel);
                }
            }
        }
        return filteredRgb;
    }

    /**
     * Filters this provided pixel channel at its row and column with the provided kernel by means of matrix
     * multiplication between the kernel matrix and the pixels it overlaps.
     *
     * @param rgbRow  pixel row
     * @param rgbCol  pixel column
     * @param channel pixel channel
     * @param kernel  filtering kernel or matrix
     * @return the result of the matrix multiplication or getFilteredPixel of the pixel
     */
    public int getFilteredPixel(int rgbRow, int rgbCol, int channel, double[][] kernel) {

        //Get the count of the image's rows and columns.
        int rgbRowCount = rgb.length;
        int rgbColCount = rgb[0].length;

        //Get the count of the kernel's rows and columns.
        int kRowCount = kernel.length;
        int kColCount = kernel[0].length;

        //Calculate the edge overlap when overlaying this kernel's center over this image's edge pixels.
        int overlap = kernel.length / 2;

        double filteredPixel = 0.0;

        //Matrix multiply the kernel matrix times the matrix of pixels it overlays when the kernel center is placed
        // over the the provided image pixel (rgbRow, rgbCol, channel).
        for (int i = 0; i < kRowCount; i++) {
            int kernelRowOverlapIndex = rgbRow - overlap + i;
            for (int j = 0; j < kColCount; j++) {
                int kernelColOverlapIndex = rgbCol - overlap + j;
                if (((kernelRowOverlapIndex > -1) && (kernelRowOverlapIndex < rgbRowCount))
                        && ((kernelColOverlapIndex > -1) && (kernelColOverlapIndex < rgbColCount))) {
                    filteredPixel += kernel[i][j] * (double) rgb[kernelRowOverlapIndex][kernelColOverlapIndex][channel];
                } else {
                    filteredPixel += 0.0;
                }
            }
        }
        //Clamp (convert it to an integer and constrain it to between 0 and 255 both inclusive) and return the
        // filtered
        // pixel value.
        return clamp(filteredPixel);
    }


    /**
     * Convert pixel value to integer and constrain the value between 0 inclusive and 255 inclusive.
     *
     * @param pixel pixel
     * @return integer value between 0 inclusive and 255 inclusive
     */
    private int clamp(double pixel) {
        int value = (int) pixel;
        if (value < 0) {
            return 0;
        } else if (value > 255) {
            return 255;
        } else {
            return value;
        }
    }

    /**
     * Calculates and returns 3x3 blur kernel matrix.
     *
     * @return 3x3 blur kernel matrix
     */
    public double[][] getBlurKernel() {
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
    public double[][] getSharpenKernel() {
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
     * Creates and returns the seven rainbow colors.
     *
     * @return rainbow the seven rainbow colors
     */
    private Color[] getRainbowColors() {
        Color indigo = new Color(75, 0, 130);
        Color violet = new Color(143, 0, 255);
        Color[] rainbow = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, indigo, violet};
        return rainbow;
    }

    public int[][][] getRgb() {
        return this.rgb;
    }
}
