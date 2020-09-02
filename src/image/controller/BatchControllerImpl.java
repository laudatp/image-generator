/**
 * 
 */
package image.controller;

import java.io.IOException;
import java.util.Scanner;

import image.model.Model;
import image.view.Features;

/**
 * Implements model controller functions.
 * 
 * @author Peter
 *
 */
public class BatchControllerImpl implements Controller, Features {

    /** Filename extension. */
    private static final String FILE_EXT = ".jpg";

    /** The input source. */

    /** Input file or line. */
    private final Readable in;

    public BatchControllerImpl(Readable in) {
        this.in = in;
    }

    @Override
    public void go(Model model) throws IOException {
        try (Scanner sc = new Scanner(this.in)) {
            while (sc.hasNextLine()) {
                // read a line
                String line = readLine(sc);
                // parse the tokens in the line. The first token is the command, which signals the tokens that
                // follow.
                try (Scanner tokens = new Scanner(line)) {
                    while (tokens.hasNext()) {
                        // parse the line into tokens
                        String imageCommand = tokens.next();
                        switch (imageCommand) {
                            case "load":
                                loadCommandFile(model, tokens);
                                break;
                            case "save":
                                writeModelFile(model, tokens);
                                break;
                            case "drawhorizontalrainbowstripes":
                                drawHorizontalRainbowStripes(model, tokens);
                                break;
                            case "drawverticalrainbowstripes":
                                drawVerticalRainbowStripes(model, tokens);
                                break;
                            case "drawcheckerboard":
                                drawCheckerBoard(model, tokens);
                                break;
                            case "drawfranceflag":
                                drawFranceFlag(model, tokens);
                                break;
                            case "drawswitzerlandflag":
                                drawSwitzerlandFlag(model, tokens);
                                break;
                            case "drawgreeceflag":
                                drawGreeceFlag(model, tokens);
                                break;
                            case "blur":
                                blur(model);
                                break;
                            case "sharpen":
                                sharpen(model);
                                break;
                            case "grayscale":
                                grayscale(model);
                                break;
                            case "sepia":
                                sepia(model);
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
     * Read a line from a file.
     * 
     * @param  sc
     * @return    the line
     */
    private String readLine(Scanner sc) {
        return sc.nextLine().toLowerCase();
    }

    /**
     * Sepia filter the model.
     * 
     * @param model
     */
    private void sepia(Model model) {
        model.sepia();
    }

    /**
     * Grayscale filter the model.
     * 
     * @param model
     */
    private void grayscale(Model model) {
        model.grayscale();
    }

    /**
     * Sharpen the model.
     * 
     * @param model
     */
    private void sharpen(Model model) {
        model.sharpen();
    }

    /**
     * Blur the model.
     * 
     * @param model
     */
    private void blur(Model model) {
        model.blur();
    }

    /**
     * Draw Greece's flag.
     * 
     * @param model
     * @param tokens
     */
    private void drawGreeceFlag(Model model, Scanner tokens) {
        int imageWidth = tokens.nextInt();
        model.drawGreeceFlag(imageWidth);
    }

    /**
     * Draw Switzerland's flag.
     * 
     * @param model
     * @param tokens
     */
    private void drawSwitzerlandFlag(Model model, Scanner tokens) {
        int imageWidth = tokens.nextInt();
        model.drawSwitzerlandFlag(imageWidth);
    }

    /**
     * Draw France's flag.
     * 
     * @param model
     * @param tokens
     */
    private void drawFranceFlag(Model model, Scanner tokens) {
        int imageWidth = tokens.nextInt();
        model.drawFranceFlag(imageWidth);
    }

    /**
     * Draw a checkerboard.
     * 
     * @param model
     * @param tokens
     */
    private void drawCheckerBoard(Model model, Scanner tokens) {
        int cellWidth = tokens.nextInt();
        model.drawCheckerboard(cellWidth);
    }

    /**
     * Draw vertical rainbow striped model with given model height and width.
     * 
     * @param model
     * @param tokens
     */
    private void drawVerticalRainbowStripes(Model model, Scanner tokens) {
        int imageHeight = tokens.nextInt();
        int imageWidth = tokens.nextInt();
        model.drawVerticalRainbowStripes(imageHeight, imageWidth);
    }

    /**
     * Draw horizontal rainbow striped model with given model height and width.
     * 
     * @param model
     * @param tokens
     */
    private void drawHorizontalRainbowStripes(Model model, Scanner tokens) {
        int imageHeight = tokens.nextInt();
        int imageWidth = tokens.nextInt();
        model.drawHorizontalRainbowStripes(imageHeight, imageWidth);
    }

    /**
     * Write model to a file.
     * 
     * @param  model
     * @param  tokens
     * @throws IOException
     */
    private void writeModelFile(Model model, Scanner tokens) throws IOException {
        String filename;
        filename = tokens.next() + FILE_EXT;
        model.save(filename);
    }

    /**
     * Load the file containing the model commands.
     * 
     * @param  model
     * @param  tokens
     * @throws IOException
     */
    private void loadCommandFile(Model model, Scanner tokens) throws IOException {
        String commandFile;
        commandFile = tokens.next() + FILE_EXT;
        model.load(commandFile);
    }

}
