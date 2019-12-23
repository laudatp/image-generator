package model;

import java.awt.Color;
import java.io.IOException;

public abstract class ImageImpl implements Image {
  /**
   * Count of color channels.
   */
  protected static final int NUMBER_OF_CHANNELS = 3;

  /**
   * Represents image's pixels by means of rows and columns of overlaid red, green, blue channels
   * with various magnitudes.
   */
  protected int[][][] rgb;

  public ImageImpl() {
    rgb = new int[100][100][NUMBER_OF_CHANNELS];
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

  @Override
  public Image makeImage(String imageName, int height, int width) throws Exception {
    ImageFactory imageFactory = new ImageFactoryImpl();
    return imageFactory.makeImage(imageName, height, width);
  }

  @Override
  public Image makeImage(String imageName, int width) throws Exception {
    ImageFactory imageFactory = new ImageFactoryImpl();
    return imageFactory.makeImage(imageName, width);
  }

  /**
   * Blurs this image.
   */
  @Override
  public void blur() {
    // Replace original image with blurred image.
    rgb = blurRGB();
  }

  protected int[][][] blurRGB() {
    return filterRGB(getBlurKernel());
  }

  /**
   * Sharpen this image.
   */
  @Override
  public void sharpen() {
    rgb = sharpenRGB();
  }

  protected int[][][] sharpenRGB() {
    return filterRGB(getSharpenKernel());
  }

  /**
   * Grayscale this image.
   */
  @Override
  public void grayscale() {
    rgb = grayscaleRGB();
  }

  protected int[][][] grayscaleRGB() {
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
    double[] kernelRow = {0.2126,0.7152,0.0722};
    double[][] kernel = new double[3][3];
    for (int i = 0; i < 3; i++) {
      kernel[i] = kernelRow;
    }
    return kernel;
  }

  /**
   * Sepia this image.
   */
  @Override
  public void sepia() {
    rgb = sepiaRGB();
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
   * @param  color color
   * @return       color channel magnitudes
   */
  protected int[] getColorChannelMagnitudes(Color color) {
    return new int[]{color.getRed(),color.getGreen(),color.getBlue()};
  }

  /**
   * Generates pixel rgb channel colors.
   * 
   * @param  color color
   * @return       pixel rgb channel colors.
   */
  protected int[] generatePixel(Color color) {
    int[] pixel = new int[NUMBER_OF_CHANNELS];
    pixel[0] = color.getRed();
    pixel[1] = color.getGreen();
    pixel[2] = color.getBlue();
    return pixel;
  }

  /**
   * Filter image (blur or sharpen it).
   * 
   * @param  kernel
   * @return        filtered image
   */
  public int[][][] filterRGB(double[][] kernel) {
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
   * Filters this provided pixel channel at its row and column with the provided kernel by means of
   * matrix multiplication between the kernel matrix and the pixels it overlaps.
   *
   * @param  rgbRow  pixel row
   * @param  rgbCol  pixel column
   * @param  channel pixel channel
   * @param  kernel  filtering kernel or matrix
   * @return         the result of the matrix multiplication or getFilteredPixel of the pixel
   */
  public int getFilteredPixel(int rgbRow, int rgbCol, int channel, double[][] kernel) {

    // Get the count of the image's rows and columns.
    int rgbRowCount = rgb.length;
    int rgbColCount = rgb[0].length;

    // Get the count of the kernel's rows and columns.
    int kRowCount = kernel.length;
    int kColCount = kernel[0].length;

    // Calculate the edge overlap when overlaying this kernel's center over this image's edge
    // pixels.
    int overlap = kernel.length / 2;

    double filteredPixel = 0.0;

    // Matrix multiply the kernel matrix times the matrix of pixels it overlays when the kernel
    // center is placed
    // over the the provided image pixel (rgbRow, rgbCol, channel).
    for (int i = 0; i < kRowCount; i++) {
      int kernelRowOverlapIndex = rgbRow - overlap + i;
      for (int j = 0; j < kColCount; j++) {
        int kernelColOverlapIndex = rgbCol - overlap + j;
        if (((kernelRowOverlapIndex > -1) && (kernelRowOverlapIndex < rgbRowCount))
            && ((kernelColOverlapIndex > -1) && (kernelColOverlapIndex < rgbColCount))) {
          filteredPixel +=
              kernel[i][j] * rgb[kernelRowOverlapIndex][kernelColOverlapIndex][channel];
        } else {
          filteredPixel += 0.0;
        }
      }
    }
    // Clamp (convert it to an integer and constrain it to between 0 and 255 both inclusive) and
    // return the
    // filtered
    // pixel value.
    return clamp(filteredPixel);
  }

  /**
   * Convert channel value to integer and constrain the value between 0 inclusive and 255 inclusive.
   *
   * @param  channel channel
   * @return         integer value between 0 inclusive and 255 inclusive
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
  protected Color[] getRainbowColors() {
    return new Color[]{Color.red,Color.orange,Color.yellow,Color.green,Color.blue,
        new Color(75, 0, 130),new Color(143, 0, 255)};
  }

  public int[][][] getRgb() {
    return this.rgb;
  }

  /**
   * Set rgb.
   * 
   * @param rgb the rgb to set
   */
  public void setRgb(int[][][] rgb) {
    this.rgb = rgb;
  }
}
