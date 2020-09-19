/**
 * 
 */
package image.controller;

import java.io.BufferedReader;
import java.util.Scanner;

import image.model.Model;

/**
 * Implements controller to read and process formatted batch file of commands.
 * 
 * @author  Peter Laudat
 * @version 09/20/2020
 * 
 * 
 *          <p>
 *          Format simple text batch file as follows below:
 *          </p>
 *          <ol>
 *          <li>One command per line</li>
 *          <li>In each command, separate each token with a space</li>
 *          <li>All dimensions are in pixels</li>
 *          </ol>
 * 
 *          Command options, formats, and examples are listed below: <br>
 * 
 * 
 *          1. Rainbow Stripes Image Generation Command Format: "Command Height Width"
 *          <ul>
 *          <li>drawHorizontalRainbowStripes 600 800</li>
 *          <li>drawVerticalRainbowStripes 600 800</li>
 *          </ul>
 * 
 *          2. Flag Image Generation Command Format: "Command Width"
 *          <ul>
 *          <li>drawSwitzerlandFlag 600</li>
 *          <li>drawFranceFlag 400</li>
 *          <li>drawGreekFlag 500</li>
 *          </ul>
 * 
 * 
 *          3. Checkerboard Image Generation Command Format: "Command Cell_Width"
 *          <ul>
 *          <li>drawSwitzerlandFlag 600</li>
 *          <li>drawFranceFlag 400</li>
 *          <li>drawGreekFlag 500</li>
 *          </ul>
 * 
 *          4. Image Filter and Transform Command Format: "Command"
 *          <ul>
 *          <li>"sharpen"</li>
 *          <li>"blur"</li>
 *          <li>"grayscale"</li>
 *          <li>"sepia"</li>
 *          </ul>
 * 
 *          5. Image File Management Command Format: "Command Fully_Qualified_File_Name"
 *          <ul>
 *          <li>"save C:/blah/blah/filename.jpg"</li>
 *          <li>"load C:/blah/blah/filename.jpg"</li>
 *          </ul>
 * 
 *
 */
public class BatchControllerImpl extends ControllerImpl {

    /** The model to use. */
    private Model model;

    /** Input file or line. */
    private final BufferedReader in;

    public BatchControllerImpl(Model model, BufferedReader in) {
        super(model);
        this.in = in;
    }

    /**
     * Process batch file containing command lines.
     */
    public void processBatchFile() {
        int imageHeight;
        int imageWidth;
        String fileName;
        try (Scanner sc = new Scanner(this.in)) {
            while (sc.hasNextLine()) {
                // read a line
                String line = sc.nextLine();
                // parse the tokens in the line. The first token is the command, which signals the tokens that
                // follow.
                try (Scanner tokens = new Scanner(line)) {
                    while (tokens.hasNext()) {
                        // parse the line into tokens and execute the line if the first token is a known command
                        String imageCommand = tokens.next();
                        if (this.getImageFilters().containsKey(imageCommand)) {
                            this.getImageFilters().get(imageCommand).operation();
                        } else if (this.getImageTransforms().containsKey(imageCommand)) {
                            this.getImageTransforms().get(imageCommand).operation();
                        } else if (this.getOneDimensionalImages().containsKey(imageCommand)) {
                            imageWidth = tokens.nextInt();
                            this.draw(imageWidth, this.getOneDimensionalImages().get(imageCommand));
                        } else if (this.getTwoDimensionalImages().containsKey(imageCommand)) {
                            imageHeight = tokens.nextInt();
                            imageWidth = tokens.nextInt();
                            this.draw(imageHeight, imageWidth, this.getTwoDimensionalImages().get(imageCommand));
                        } else if (this.getFileOperations().containsKey(imageCommand)) {
                            fileName = tokens.next();
                            this.operate(fileName, this.getFileOperations().get(imageCommand));
                        } else if (imageCommand.equals("exitProgram")) {
                            exitProgram();
                        } else {
                            throw new IllegalArgumentException(imageCommand + ILLEGAL_COMMAND);
                        }
                    }
                }
            }
        }
    }
}
