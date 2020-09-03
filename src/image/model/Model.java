package image.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents the model for the Image processing application.
 * 
 * @author  Peter Laudat
 * @version 5/19/2019
 */
public interface Model {

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
     * Draw checkerboard.
     * 
     * @param cellWidth
     */
    void drawCheckerboard(int cellWidth);

    /**
     * Draw French flage.
     * 
     * @param flagWidth
     */
    void drawFranceFlag(int flagWidth);

    /**
     * Draw Swiss flag.
     * 
     * @param flagWidth
     */
    void drawSwitzerlandFlag(int flagWidth);

    /**
     * Draw Greek flag.
     * 
     * @param flagWidth
     */
    void drawGreeceFlag(int flagWidth);

    /**
     * Get image.
     * 
     * @return
     */
    BufferedImage getImage();

}
