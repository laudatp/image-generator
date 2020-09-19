/**
 * 
 */
package image.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import image.model.Model;
import image.model.ModelImpl;

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
public class BatchControllerImpl implements Features {

    /** The model to use. */
    private Model model;

    /** Input file or line. */
    private final BufferedReader in;

    private ImageOperation blur, sharpen, grayscale, sepia, exitProgram;
    private RainbowOperation horizontalRainbow, verticalRainbow;
    private FlagOperation franceFlag, greeceFlag, switzerlandFlag, checkerboard;
    private FileOperation save, load;

    private Map<String, ImageOperation> imageOperations = new HashMap<>();
    private Map<String, RainbowOperation> rainbowOperations = new HashMap<>();
    private Map<String, FlagOperation> flagOperations = new HashMap<>();
    private Map<String, FileOperation> fileOperations = new HashMap<>();

    public BatchControllerImpl(Model model, BufferedReader in) {
        this.model = new ModelImpl();
        this.in = in;
        blur = model::blur;
        sharpen = model::sharpen;
        grayscale = model::grayscale;
        sepia = model::sepia;
        checkerboard = cellWidth -> model.checkerboard(cellWidth);
        franceFlag = (width) -> model.franceFlag(width);
        greeceFlag = (width) -> model.greeceFlag(width);
        switzerlandFlag = (width) -> model.switzerlandFlag(width);
        horizontalRainbow = (height, width) -> model.horizontalRainbowStripes(height, width);
        verticalRainbow = (height, width) -> model.verticalRainbowStripes(height, width);
        exitProgram = this::exitProgram;
        save = filename -> {
            try {
                model.save(filename);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };
        load = filename -> {
            try {
                model.load(filename);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };
        imageOperations.put("blur", blur);
        imageOperations.put("sharpen", sharpen);
        imageOperations.put("grayscale", grayscale);
        imageOperations.put("sepia", sepia);
        imageOperations.put("exitProgram", exitProgram);
        rainbowOperations.put("drawHorizontalRainbow", horizontalRainbow);
        rainbowOperations.put("drawVerticalRainbow", verticalRainbow);
        flagOperations.put("drawCheckerboard", checkerboard);
        flagOperations.put("drawFranceFlag", franceFlag);
        flagOperations.put("drawGreeceFlag", greeceFlag);
        flagOperations.put("drawSwitzerlandFlag", switzerlandFlag);
        fileOperations.put("save", save);
        fileOperations.put("load", load);
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
                        if (imageOperations.containsKey(imageCommand)) {
                            imageOperations.get(imageCommand).operation();
                        } else if (flagOperations.containsKey(imageCommand)) {
                            flagOperations.get(imageCommand).operation(tokens.nextInt());
                        } else if (rainbowOperations.containsKey(imageCommand)) {
                            rainbowOperations.get(imageCommand).operation(tokens.nextInt(), tokens.nextInt());
                        } else if (fileOperations.containsKey(imageCommand)) {
                            fileOperations.get(imageCommand).operation(tokens.next());
                        } else {
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
     */
    @Override
    public void sepia() {
        model.sepia();
    }

    /**
     * Grayscale filter the model.
     * 
     */
    @Override
    public void grayscale() {
        model.grayscale();
    }

    /**
     * Sharpen the model.
     * 
     */
    @Override
    public void sharpen() {
        model.sharpen();
    }

    /**
     * Blur the model.
     * 
     */
    @Override
    public void blur() {
        model.blur();
    }

    /**
     * Draw Greece's flags.
     * 
     */
    @Override
    public void drawGreeceFlag(int flagWidth) {
        model.greeceFlag(flagWidth);
    }

    /**
     * Draw Switzerland's flags.
     * 
     */
    @Override
    public void drawSwitzerlandFlag(int flagWidth) {
        model.switzerlandFlag(flagWidth);
    }

    /**
     * Draw France's flags.
     * 
     */
    @Override
    public void drawFranceFlag(int flagWidth) {
        model.franceFlag(flagWidth);
    }

    /**
     * Draw a checkerboard.
     * 
     */
    @Override
    public void drawCheckerboard(int cellWidth) {
        model.checkerboard(cellWidth);
    }

    /**
     * Draw vertical rainbows striped model with given model height and width.
     * 
     */
    @Override
    public void drawVerticalRainbowStripes(int imageHeight, int imageWidth) {
        model.verticalRainbowStripes(imageHeight, imageWidth);
    }

    /**
     * Draw horizontal rainbows striped model with given model height and width.
     * 
     */
    @Override
    public void drawHorizontalRainbowStripes(int imageHeight, int imageWidth) {
        model.horizontalRainbowStripes(imageHeight, imageWidth);
    }

    @Override
    public void load(String imageInfile) throws IOException {
        model.load(imageInfile);
    }

    /**
     * Write model to a file.
     * 
     * @param  imageOutFile image output file name
     * @throws IOException  if errors encountered writing image to file
     */
    @Override
    public void save(String imageOutFile) throws IOException {
        model.save(imageOutFile);
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

    @FunctionalInterface
    interface ImageOperation {
        void operation();
    }

    @FunctionalInterface
    interface FlagOperation {
        void operation(int width);
    }

    @FunctionalInterface
    interface RainbowOperation {
        void operation(int height, int width);
    }

    @FunctionalInterface
    interface FileOperation {
        void operation(String fileName);
    }

    private void operate(ImageOperation imageOperation) {
        imageOperation.operation();
    }

    private void operate(int width, FlagOperation flagOperation) {
        flagOperation.operation(width);
    }

    private void operate(int height, int width, RainbowOperation rainbowOperation) {
        rainbowOperation.operation(height, width);
    }

    private void operate(String fileName, FileOperation fileOperation) {
        fileOperation.operation(fileName);
    }

}
