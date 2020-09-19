/**
 * 
 */
package image.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import image.model.Model;
import image.model.ModelImpl;
import image.view.View;

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
public class ControllerImpl implements Features {

    /** The model to use. */
    private Model model;
    /** The view. */
    private View view;

    /** Blur or sharpen image filters. */
    private ImageFilter blur, sharpen, exitProgram;
    /** Grayscale and sepia image transforms. */
    private ImageTransform grayscale, sepia;
    /** Rainbow image generation commands. */
    private TwoDimensionalImage horizontalRainbow, verticalRainbow;
    /** Flag image generation commands. */
    private OneDimensionalImage franceFlag, greeceFlag, switzerlandFlag, checkerboard;
    /** File operation commands. */
    private FileOperation save, load;

    /** Image filters. */
    private Map<String, ImageFilter> imageFilters = new HashMap<>();
    /** Image transforms. */
    private Map<String, ImageTransform> imageTransforms = new HashMap<>();
    /** Rainbow image operations. */
    private Map<String, TwoDimensionalImage> twoDimensionalImages = new HashMap<>();
    /** Flag operations. */
    private Map<String, OneDimensionalImage> oneDimensionalImages = new HashMap<>();
    /** File operations. */
    private Map<String, FileOperation> fileOperations = new HashMap<>();

    /**
     * Create a new controller instance with the given model and set various image operations.
     * 
     * @param model
     */
    public ControllerImpl(Model model) {
        this.model = new ModelImpl();
        this.blur = this.model::blur;
        this.sharpen = this.model::sharpen;
        this.grayscale = this.model::grayscale;
        this.sepia = this.model::sepia;
        this.checkerboard = cellWidth -> this.model.checkerboard(cellWidth);
        this.franceFlag = width -> this.model.franceFlag(width);
        this.greeceFlag = width -> this.model.greeceFlag(width);
        this.switzerlandFlag = width -> this.model.switzerlandFlag(width);
        this.horizontalRainbow = (height, width) -> this.model.horizontalRainbowStripes(height, width);
        this.verticalRainbow = (height, width) -> this.model.verticalRainbowStripes(height, width);
        this.exitProgram = this::exitProgram;
        this.imageFilters.put("blur", blur);
        this.imageFilters.put("sharpen", sharpen);
        this.imageFilters.put("exitProgram", exitProgram);
        this.imageTransforms.put("grayscale", grayscale);
        this.imageTransforms.put("sepia", sepia);
        this.twoDimensionalImages.put("horizontalRainbowStripes", horizontalRainbow);
        this.twoDimensionalImages.put("verticalRainbowStripes", verticalRainbow);
        this.oneDimensionalImages.put("checkerboard", checkerboard);
        this.oneDimensionalImages.put("franceFlag", franceFlag);
        this.oneDimensionalImages.put("greeceFlag", greeceFlag);
        this.oneDimensionalImages.put("switzerlandFlag", switzerlandFlag);
        this.fileOperations.put("save", save);
        this.fileOperations.put("load", load);
    }

    /**
     * Set the view.
     * 
     * @param view view
     */
    public void setView(View view) {
        this.view = view;
        this.view.setFeatures(this);
    }

    @Override
    public void render(String imageCommand) {
        if (imageFilters.containsKey(imageCommand)) {
            this.draw(imageFilters.get(imageCommand));
            this.updateView();
        } else if (imageTransforms.containsKey(imageCommand)) {
            this.draw(imageTransforms.get(imageCommand));
            this.updateView();
        } else {
            throw new IllegalArgumentException("Illegal command string " + imageCommand + " was provided");
        }

    }

    @Override
    public void render(String imageCommand, int width) {
        if (oneDimensionalImages.containsKey(imageCommand)) {
            this.draw(width, oneDimensionalImages.get(imageCommand));
            this.updateView();
        } else {
            throw new IllegalArgumentException("Illegal command string " + imageCommand + " was provided");
        }
    }

    @Override
    public void render(String imageCommand, int height, int width) {
        System.out.println("Inside draw for rainbow images");
        System.out.println(imageCommand);
        if (twoDimensionalImages.containsKey(imageCommand)) {
            System.out.println("Inside twoDimensionalImage.containsKey conditional");
            this.draw(height, width, twoDimensionalImages.get(imageCommand));
            this.updateView();
        } else {
            throw new IllegalArgumentException("Illegal command string " + imageCommand + " was provided");
        }
    }

    @Override
    public void load(String imageInfile) throws IOException {
        this.model.load(imageInfile);
        this.updateView();
    }

    /**
     * Write model to a file.
     * 
     * @param  imageOutFile image output file name
     * @throws IOException  if errors encountered writing image to file
     */
    @Override
    public void save(String imageOutFile) throws IOException {
        this.model.save(imageOutFile);
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

    /**
     * Update the view.
     */
    private void updateView() {
        System.out.println("Inside updateView");
        System.out.println("Before getImage");
        BufferedImage image = this.model.getImage();
        System.out.println("After getImage and before view.updateDisplay");
        view.updateDisplay(image);
        System.out.println("After view.updateDisplay");
    }

    /**
     * Represents image filtering operations.
     * 
     * @author Peter
     *
     */
    @FunctionalInterface
    public interface ImageFilter {
        void operation();
    }

    /**
     * Represents image transform operations.
     * 
     * @author Peter
     *
     */
    @FunctionalInterface
    public interface ImageTransform {
        void operation();
    }

    /**
     * Represents images requiring submission of one dimension.
     * 
     * @author Peter
     *
     */
    @FunctionalInterface
    public interface OneDimensionalImage {
        void operation(int width);
    }

    /**
     * Represents images requiring submission of two dimensions.
     * 
     * @author Peter
     *
     */
    @FunctionalInterface
    public interface TwoDimensionalImage {
        void operation(int height, int width);
    }

    /**
     * Represents file operations, like save and load.
     * 
     * @author Peter
     *
     */
    @FunctionalInterface
    public interface FileOperation {
        void operation(String fileName);
    }

    /**
     * Filter an image, e.g., blur or sharpen an image.
     * 
     * @param imageFilter image filter
     */
    private void draw(ImageFilter imageFilter) {
        imageFilter.operation();
    }

    /**
     * Transform an image, e.g., grayscale or sepia an image.
     * 
     * @param imageTransform image transform
     */
    private void draw(ImageTransform imageTransform) {
        imageTransform.operation();
    }

    /**
     * Draw a one dimensional image with the given dimension.
     * 
     * @param width               width
     * @param oneDimensionalImage one dimensional image
     */
    private void draw(int width, OneDimensionalImage oneDimensionalImage) {
        oneDimensionalImage.operation(width);
    }

    /**
     * Draw a two dimensional image with the two given dimensions.
     * 
     * @param height              height
     * @param width               width
     * @param twoDimensionalImage two dimensional image
     */
    private void draw(int height, int width, TwoDimensionalImage twoDimensionalImage) {
        System.out.println("Inside draw for rainbow operations");
        twoDimensionalImage.operation(height, width);
    }

}
