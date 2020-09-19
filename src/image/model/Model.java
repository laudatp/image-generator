package image.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents the model for the Image Generator application.
 * 
 * @author  Peter Laudat
 * @version 5/19/2019
 */
public interface Model {

    /**
     * Loads this model from a file.
     * 
     * @param  imageInfile input image file
     * @throws IOException if issues encountered while reading input image file
     */
    void load(String imageInfile) throws IOException;

    /**
     * Saves this model to a file.
     * 
     * @param  imageOutfile output image file
     * @throws IOException  if issues encountered while writing output image file
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
    void horizontalRainbowStripes(int imageHeight, int imageWidth);

    /**
     * Draw vertical rainbow striped image.
     * 
     * @param imageHeight image height
     * @param imageWidth  image width
     */
    void verticalRainbowStripes(int imageHeight, int imageWidth);

    /**
     * Draw checkerboard.
     * 
     * @param cellWidth cell width
     */
    void checkerboard(int cellWidth);

    /**
     * Draw French flag.
     * 
     * @param flagWidth flag width
     */
    void franceFlag(int flagWidth);

    /**
     * Draw Swiss flag.
     * 
     * @param flagWidth flag width
     */
    void switzerlandFlag(int flagWidth);

    /**
     * Draw Greek flag.
     * 
     * @param flagWidth flag width
     */
    void greeceFlag(int flagWidth);

    /**
     * Get image.
     * 
     * @return BufferedImage image
     */
    BufferedImage getImage();

}
