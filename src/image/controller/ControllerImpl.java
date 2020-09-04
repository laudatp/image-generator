package image.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import image.model.Model;
import image.model.ModelImpl;
import image.view.View;

/**
 * Represents the controller implementation for Image viewer application's features.
 * 
 * @author  Peter Laudat
 * @version 09/02/2020
 *
 */
public class ControllerImpl implements Features {
    /** The model to use. */
    private Model model;

    /** The view to use. */
    private View view;

    /**
     * Constructor.
     * 
     * @param model
     */
    public ControllerImpl(Model model) {
        this.model = new ModelImpl();
    }

    /**
     * Set the view.
     * 
     * @param view
     */
    public void setView(View view) {
        this.view = view;
        view.setFeatures(this);
    }

    @Override
    public void load(String imageInfile) throws IOException {
        model.load(imageInfile);
        view.updateDisplay(imageInfile);
    }

    @Override
    public void save(String imageOutfile) throws IOException {
        model.save(imageOutfile);
    }

    @Override
    public void blur() {
        model.blur();
        updateView();
    }

    @Override
    public void sharpen() {
        model.sharpen();
        updateView();
    }

    @Override
    public void grayscale() {
        model.grayscale();
        updateView();
    }

    @Override
    public void sepia() {
        model.sepia();
        updateView();
    }

    @Override
    public void drawHorizontalRainbowStripes(int imageHeight, int imageWidth) {
        model.drawHorizontalRainbowStripes(imageHeight, imageWidth);
        updateView();
    }

    @Override
    public void drawVerticalRainbowStripes(int imageHeight, int imageWidth) {
        model.drawVerticalRainbowStripes(imageHeight, imageWidth);
        updateView();
    }

    @Override
    public void drawCheckerboard(int cellWidth) {
        model.drawCheckerboard(cellWidth);
        updateView();
    }

    @Override
    public void drawFranceFlag(int flagWidth) {
        model.drawFranceFlag(flagWidth);
        updateView();
    }

    @Override
    public void drawSwitzerlandFlag(int flagWidth) {
        model.drawSwitzerlandFlag(flagWidth);
        updateView();
    }

    @Override
    public void drawGreeceFlag(int flagWidth) {
        model.drawGreeceFlag(flagWidth);
        updateView();
    }

    /**
     * Update the view.
     */
    private void updateView() {
        BufferedImage image = model.getImage();
        view.updateDisplay(image);
    }

    /**
     * Exit program.
     */
    @Override
    public void exitProgram() {
        System.out.println("Exiting program");
        System.exit(0);
    }

    @Override
    public void runBatchFile(String batchFile) throws IOException {
        try (BufferedReader inFile = new BufferedReader(new FileReader(batchFile))) {
            Model batchModel = new ModelImpl();
            BatchControllerImpl batchController = new BatchControllerImpl(batchModel, inFile);
            batchController.processBatchFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
