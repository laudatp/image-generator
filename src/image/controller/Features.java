package image.controller;

import java.io.IOException;

/**
 * Represents this Image Generator's features.
 * 
 * @author  Peter Laudat
 * @version 09/2020
 * 
 */
public interface Features {

    /**
     * Loads this model from a file.
     * 
     * @param  imageInfile image input file
     * @throws IOException if errors encountered while reading image input file
     */
    void load(String imageInfile) throws IOException;

    /**
     * Saves this model to a file.
     * 
     * @param  imageOutfile image output file
     * @throws IOException  if errors encountered while writing image output file
     */
    void save(String imageOutfile) throws IOException;

    /**
     * Blur this model.
     */
    void blur();

    /**
     * Sharpen this model.
     */
    void sharpen();

    /**
     * Grayscale this model.
     */
    void grayscale();

    /**
     * Sepia this model.
     */
    void sepia();

    /**
     * Draw horizontal rainbow striped image.
     * 
     * @param imageHeight image height
     * @param imageWidth  image width
     */
    void drawHorizontalRainbowStripes(int imageHeight, int imageWidth);

    /**
     * Draw vertical rainbow striped image.
     * 
     * @param imageHeight image height
     * @param imageWidth  image width
     */
    void drawVerticalRainbowStripes(int imageHeight, int imageWidth);

    /**
     * Draw a checkerboard.
     * 
     * @param cellWidth cell width
     */
    void drawCheckerboard(int cellWidth);

    /**
     * Draw the French flag.
     * 
     * @param flagWidth flag width
     */
    void drawFranceFlag(int flagWidth);

    /**
     * Draw the Swiss flag.
     * 
     * @param flagWidth flag width
     */
    void drawSwitzerlandFlag(int flagWidth);

    /**
     * Draw the Greek flag.
     * 
     * @param flagWidth flag width
     */
    void drawGreeceFlag(int flagWidth);

    /**
     * Exit program.
     */
    void exitProgram();

    /**
     * Process batch file of image generation commands.
     * 
     * @param  batchFile   batch file of image generation commands
     * @throws IOException if errors encountered reading batch file
     */
    void runBatchFile(String batchFile) throws IOException;
}
