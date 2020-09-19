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
     * Render an image with the given image command.
     * 
     * @param imageCommand
     */
    void render(String imageCommand);

    /**
     * Render an image with the given image command and dimension.
     * 
     * @param imageCommand
     * @param width
     */
    void render(String imageCommand, int width);

    /**
     * Render an image with the given image command, and two dimensions.
     * 
     * @param imageCommand
     * @param height
     * @param width
     */
    void render(String imageCommand, int height, int width);

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
