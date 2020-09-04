/**
 * 
 */
package image.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import image.model.Model;
import image.model.ModelImpl;

/**
 * Implements batchModel controller functions.
 * 
 * @author Peter
 *
 */
public class BatchControllerImpl implements Features {

    /** The batchModel to use. */
    private Model batchModel;

    /** Input file or line. */
    private final BufferedReader in;

    public BatchControllerImpl(Model batchModel, BufferedReader in) {
        this.batchModel = new ModelImpl();
        this.in = in;
    }

    public void processBatchFile() {
        int imageHeight;
        int imageWidth;
        int cellWidth;
        try (Scanner sc = new Scanner(this.in)) {
            while (sc.hasNextLine()) {
                // read a line
                String line = readLine(sc);
                System.out.println(line);
                // parse the tokens in the line. The first token is the command, which signals the tokens that
                // follow.
                try (Scanner tokens = new Scanner(line)) {
                    while (tokens.hasNext()) {
                        // parse the line into tokens
                        String imageCommand = tokens.next();
                        switch (imageCommand) {
                            case "load":
                                try {
                                    String imageInFile = tokens.next();
                                    load(imageInFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "save":
                                try {
                                    String imageOutFile = tokens.next();
                                    save(imageOutFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "drawhorizontalrainbowstripes":
                                imageHeight = Integer.parseInt(tokens.next());
                                imageWidth = Integer.parseInt(tokens.next());
                                drawHorizontalRainbowStripes(imageHeight, imageWidth);
                                break;
                            case "drawverticalrainbowstripes":
                                imageHeight = Integer.parseInt(tokens.next());
                                imageWidth = Integer.parseInt(tokens.next());
                                drawVerticalRainbowStripes(imageHeight, imageWidth);
                                break;
                            case "drawcheckerboard":
                                cellWidth = Integer.parseInt(tokens.next());
                                drawCheckerboard(cellWidth);
                                break;
                            case "drawfranceflag":
                                imageWidth = Integer.parseInt(tokens.next());
                                drawFranceFlag(imageWidth);
                                break;
                            case "drawswitzerlandflag":
                                imageWidth = Integer.parseInt(tokens.next());
                                drawSwitzerlandFlag(imageWidth);
                                break;
                            case "drawgreeceflag":
                                imageWidth = Integer.parseInt(tokens.next());
                                drawGreeceFlag(imageWidth);
                                break;
                            case "blur":
                                blur();
                                break;
                            case "sharpen":
                                sharpen();
                                break;
                            case "grayscale":
                                grayscale();
                                break;
                            case "sepia":
                                sepia();
                                break;
                            case "exitProgram":
                                exitProgram();
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
     * Sepia filter the batchModel.
     * 
     */
    @Override
    public void sepia() {
        batchModel.sepia();
    }

    /**
     * Grayscale filter the batchModel.
     * 
     */
    @Override
    public void grayscale() {
        batchModel.grayscale();
    }

    /**
     * Sharpen the batchModel.
     * 
     */
    @Override
    public void sharpen() {
        batchModel.sharpen();
    }

    /**
     * Blur the batchModel.
     * 
     */
    @Override
    public void blur() {
        batchModel.blur();
    }

    /**
     * Draw Greece's flag.
     * 
     */
    @Override
    public void drawGreeceFlag(int flagWidth) {
        batchModel.drawGreeceFlag(flagWidth);
    }

    /**
     * Draw Switzerland's flag.
     * 
     */
    @Override
    public void drawSwitzerlandFlag(int flagWidth) {
        batchModel.drawSwitzerlandFlag(flagWidth);
    }

    /**
     * Draw France's flag.
     * 
     */
    @Override
    public void drawFranceFlag(int flagWidth) {
        batchModel.drawFranceFlag(flagWidth);
    }

    /**
     * Draw a checkerboard.
     * 
     */
    @Override
    public void drawCheckerboard(int cellWidth) {
        batchModel.drawCheckerboard(cellWidth);
    }

    /**
     * Draw vertical rainbow striped batchModel with given batchModel height and width.
     * 
     */
    @Override
    public void drawVerticalRainbowStripes(int imageHeight, int imageWidth) {
        batchModel.drawVerticalRainbowStripes(imageHeight, imageWidth);
    }

    /**
     * Draw horizontal rainbow striped batchModel with given batchModel height and width.
     * 
     */
    @Override
    public void drawHorizontalRainbowStripes(int imageHeight, int imageWidth) {
        batchModel.drawHorizontalRainbowStripes(imageHeight, imageWidth);
    }

    @Override
    public void load(String imageInfile) throws IOException {
        batchModel.load(imageInfile);
    }

    /**
     * Write batchModel to a file.
     * 
     * @throws IOException
     */
    @Override
    public void save(String imageOutFile) throws IOException {
        batchModel.save(imageOutFile);
    }

    @Override
    public void exitProgram() {
        System.out.println("Exiting program");
        System.exit(0);
    }

    @Override
    public void runBatchFile(String batchFile) throws IOException {
        // Left empty intentionally
    }

}
