package image.controller;

import java.io.IOException;

import image.model.Model;

/**
 * Represents this Image application's features.
 * 
 * @author  Peter Laudat
 * @version 09/2020
 * 
 */
public interface Features {

    /**
     * Loads this model from a file.
     * 
     * @param  imageInfile
     * @throws IOException
     */
    void load(String imageInfile) throws IOException;

    /**
     * Saves this model to a file.
     * 
     * @param  imageOutfile
     * @throws IOException
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
     * @param imageHeight
     * @param imageWidth
     */
    void drawHorizontalRainbowStripes(int imageHeight, int imageWidth);

    /**
     * Draw vertical rainbow striped image.
     * 
     * @param imageHeight
     * @param imageWidth
     */
    void drawVerticalRainbowStripes(int imageHeight, int imageWidth);

    /**
     * Draw a checkerboard.
     * 
     * @param cellWidth
     */
    void drawCheckerboard(int cellWidth);

    /**
     * Draw the French flag.
     * 
     * @param flagWidth
     */
    void drawFranceFlag(int flagWidth);

    /**
     * Draw the Swiss flag.
     * 
     * @param flagWidth
     */
    void drawSwitzerlandFlag(int flagWidth);

    /**
     * Draw the Greek flag.
     * 
     * @param flagWidth
     */
    void drawGreeceFlag(int flagWidth);

    /**
     * Exit program.
     */
    void exitProgram();

    void go(Model model) throws IOException;

    void runBatchFile(String batchFile) throws IOException;
}
