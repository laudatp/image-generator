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

    /** Filename extension. */
    private static final String FILE_EXT = ".jpg";

    /** The input source. */
    private final Readable in;

    public ImageControllerImpl(Readable in) {
        this.in = in;
    }

    @Override
    public void go(Image image) throws IOException {
        String line = "";
        String imageCommand = "";
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
                                loadCommandFile(image, tokens);
                                break;
                            case "save":
                                writeImageFile(image, tokens);
                                break;
                            case "drawhorizontalrainbowstripes":
                                drawHorizontalRainbowStripes(image, tokens);
                                break;
                            case "drawverticalrainbowstripes":
                                drawVerticalRainbowStripes(image, tokens);
                                break;
                            case "drawcheckerboard":
                                drawCheckerBoard(image, tokens);
                                break;
                            case "drawfranceflag":
                                drawFranceFlag(image, tokens);
                                break;
                            case "drawswitzerlandflag":
                                drawSwitzerlandFlag(image, tokens);
                                break;
                            case "drawgreeceflag":
                                drawGreeceFlag(image, tokens);
                                break;
                            case "blur":
                                blur(image);
                                break;
                            case "sharpen":
                                sharpen(image);
                                break;
                            case "grayscale":
                                grayscale(image);
                                break;
                            case "sepia":
                                sepia(image);
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
     * Sepia filter the image.
     * 
     * @param image
     */
    private void sepia(Image image) {
        image.sepia();
    }

    /**
     * Grayscale filter the image.
     * 
     * @param image
     */
    private void grayscale(Image image) {
        image.grayscale();
    }

    /**
     * Sharpen the image.
     * 
     * @param image
     */
    private void sharpen(Image image) {
        image.sharpen();
    }

    /**
     * Blur the image.
     * 
     * @param image
     */
    private void blur(Image image) {
        image.blur();
    }

    /**
     * Draw Greece's flag.
     * 
     * @param image
     * @param tokens
     */
    private void drawGreeceFlag(Image image, Scanner tokens) {
        int imageWidth = tokens.nextInt();
        image.drawGreeceFlag(imageWidth);
    }

    /**
     * Draw Switzerland's flag.
     * 
     * @param image
     * @param tokens
     */
    private void drawSwitzerlandFlag(Image image, Scanner tokens) {
        int imageWidth = tokens.nextInt();
        image.drawSwitzerlandFlag(imageWidth);
    }

    /**
     * Draw France's flag.
     * 
     * @param image
     * @param tokens
     */
    private void drawFranceFlag(Image image, Scanner tokens) {
        int imageWidth = tokens.nextInt();
        image.drawFranceFlag(imageWidth);
    }

    /**
     * Draw a checkerboard.
     * 
     * @param image
     * @param tokens
     */
    private void drawCheckerBoard(Image image, Scanner tokens) {
        int cellWidth = tokens.nextInt();
        image.drawCheckerBoard(cellWidth);
    }

    /**
     * Draw vertical rainbow striped image with given image height and width.
     * 
     * @param image
     * @param tokens
     */
    private void drawVerticalRainbowStripes(Image image, Scanner tokens) {
        int imageHeight = tokens.nextInt();
        int imageWidth = tokens.nextInt();
        image.drawVerticalRainbowStripes(imageHeight, imageWidth);
    }

    /**
     * Draw horizontal rainbow striped image with given image height and width.
     * 
     * @param image
     * @param tokens
     */
    private void drawHorizontalRainbowStripes(Image image, Scanner tokens) {
        int imageHeight = tokens.nextInt();
        int imageWidth = tokens.nextInt();
        image.drawHorizontalRainbowStripes(imageHeight, imageWidth);
    }

    /**
     * Write image to a file.
     * 
     * @param  image
     * @param  tokens
     * @throws IOException
     */
    private void writeImageFile(Image image, Scanner tokens) throws IOException {
        String filename;
        filename = tokens.next() + FILE_EXT;
        image.save(filename);
    }

    /**
     * Load the file containing the image commands.
     * 
     * @param  image
     * @param  tokens
     * @throws IOException
     */
    private void loadCommandFile(Image image, Scanner tokens) throws IOException {
        String commandFile;
        commandFile = tokens.next() + FILE_EXT;
        image.load(commandFile);
    }

}
