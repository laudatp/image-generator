/**
 * 
 */
package image.controller;

import java.io.IOException;
import java.util.Scanner;

import image.model.Image;

/**
 * Implements image controller functions.
 * 
 * @author Peter
 *
 */
public class ImageControllerImpl implements ImageController {

    /** The input source. */
    private final Readable in;

    public ImageControllerImpl(Readable in) {
        this.in = in;
    }

    @Override
    public void go(Image image) throws IOException {
        String commandFile = "";
        String filenameExtension = ".jpg";
        String line = "";
        String imageCommand = "";
        int imageHeight = 0;
        int imageWidth = 0;
        int cellWidth = 0;
        try (Scanner sc = new Scanner(this.in)) {
            while (sc.hasNextLine()) {
                // read a line
                line = sc.nextLine().toLowerCase();
                // parse the tokens in the line
                try (Scanner tokens = new Scanner(line)) {
                    while (tokens.hasNext()) {
                        // parse the line into tokens
                        imageCommand = tokens.next();
                        switch (imageCommand) {
                            case "load":
                                loadCommandFile(image, filenameExtension, tokens);
                                break;
                            case "save":
                                saveImage(image, filenameExtension, tokens);
                                break;
                            case "drawhorizontalrainbowstripes":
                                if (tokens.hasNextInt()) {
                                    imageHeight = tokens.nextInt();
                                }
                                if (tokens.hasNextInt()) {
                                    imageWidth = tokens.nextInt();
                                }
                                image.drawHorizontalRainbowStripes(imageHeight, imageWidth);
                                break;
                            case "drawverticalrainbowstripes":
                                if (tokens.hasNextInt()) {
                                    imageHeight = tokens.nextInt();
                                }
                                if (tokens.hasNextInt()) {
                                    imageWidth = tokens.nextInt();
                                }
                                image.drawVerticalRainbowStripes(imageHeight, imageWidth);
                                break;
                            case "drawcheckerboard":
                                if (tokens.hasNextInt()) {
                                    cellWidth = tokens.nextInt();
                                }
                                image.drawCheckerBoard(cellWidth);
                                break;
                            case "drawfranceflag":
                                if (tokens.hasNextInt()) {
                                    imageWidth = tokens.nextInt();
                                }
                                image.drawFranceFlag(imageWidth);
                                break;
                            case "drawswitzerlandflag":
                                if (tokens.hasNextInt()) {
                                    imageWidth = tokens.nextInt();
                                }
                                image.drawSwitzerlandFlag(imageWidth);
                                break;
                            case "drawgreeceflag":
                                if (tokens.hasNextInt()) {
                                    imageWidth = tokens.nextInt();
                                }
                                image.drawGreeceFlag(imageWidth);
                                break;
                            case "blur":
                                image.blur();
                                break;
                            case "sharpen":
                                image.sharpen();
                                break;
                            case "grayscale":
                                image.grayscale();
                                break;
                            case "sepia":
                                image.sepia();
                                break;
                            case "quit":
                                System.out.println("Quitting program");
                                System.exit(0);
                                break;
                            default:
                                throw new IllegalArgumentException("An illegal command string was provided");
                        }
                    }
                }
            }
        }
    }

    /**
     * Write image to a file.
     * 
     * @param  image
     * @param  filenameExtension
     * @param  tokens
     * @throws IOException
     */
    private void saveImage(Image image, String filenameExtension, Scanner tokens) throws IOException {
        String filename;
        filename = tokens.next() + filenameExtension;
        image.save(filename);
    }

    /**
     * Load the file containing the image commands.
     * 
     * @param  image
     * @param  filenameExtension
     * @param  tokens
     * @throws IOException
     */
    private void loadCommandFile(Image image, String filenameExtension, Scanner tokens) throws IOException {
        String commandFile;
        commandFile = tokens.next() + filenameExtension;
        image.load(commandFile);
    }

}
