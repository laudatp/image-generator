import java.io.IOException;

/**
 * Represents the model for the image processing application.
 * This is post-class practice
 * @author Peter Laudat
 * @version 5/19/2019
 */
public interface IModel {

    /**
     * Loads this image from a file.
     * @param imageInfile
     */
    void load(String imageInfile) throws IOException;

    /**
     * Saves this image to a file.
     * @param imageOutfile
     */
    void save(String imageOutfile) throws IOException;


    /**
     * Blur this image.
     */
    void blur();

    /**
     * Sharpen this image.
     */
    void sharpen();

    /**
     * Grayscale this image.
     */
    void grayscale();

    /**
     * Sepia this image.
     */
    void sepia();

    /**
     * Generate horizontal rainbow-striped image.

     */
    void generateHorizontalRainbowStripeImage(int height, int width);

    /**
     * Generate vertical rainbow-striped image.
     * @param height this image's width
     * @param width this image's length
     */
    void generateVerticalRainbowStripeImage(int height, int width);

    /**
     * Generate checkerboard patterned image.
     * @param width this image's checkerboard squares' length
     */
    void generateCheckerBoard(int width);


    /**
     * Generate image of France's flag.
     * @param width this image of France's flag's width
     */
    void generateFranceFlag(int width);


    /**
     * Generate image of Greece's flag.
     * @param width this image of Greece's flag's width
     */
    void generateGreeceFlag(int width);


    /**
     * Generate image of Switzerland's flag.
     * @param width this image of Switzerland's flag's width
     */
    void generateSwitzerlandFlag(int width);


}
