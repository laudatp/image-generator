package image.controller;

import java.io.IOException;

/**
 * Represents the model.model for the model processing application. This is post-course practice.
 * 
 * @author  Peter Laudat
 * @version 5/19/2019
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
    // void blur();
    /**
     * Sharpen this model.
     */
    // void sharpen();
    /**
     * Grayscale this model.
     */

    // void grayscale();
    /**
     * Sepia this model.
     */
    // void sepia();
    // void drawHorizontalRainbowStripes(int imageHeight, int imageWidth);
    // void drawVerticalRainbowStripes(int imageHeight, int imageWidth);
    // void drawCheckerBoard(int cellWidth);
    // void drawFranceFlag(int flagWidth);
    // void drawSwitzerlandFlag(int flagWidth);
    // void drawGreeceFlag(int flagWidth);
    void exitProgram();
    // void go(Model model) throws IOException;

}
