package image.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import image.model.Model;
import image.model.ModelImpl;
import image.view.View;

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

    public void setView(View view) {
        this.view = view;
        view.setFeatures(this);
    }

    // @Override
    // public void go(Model model) throws IOException {
    // // TODO Auto-generated method stub
    //
    // }

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
        BufferedImage image = model.getImage();
        view.updateDisplay(image);
    }

    @Override
    public void sharpen() {
        model.sharpen();
        BufferedImage image = model.getImage();
        view.updateDisplay(image);
    }

    @Override
    public void grayscale() {
        model.grayscale();
        BufferedImage image = model.getImage();
        view.updateDisplay(image);
    }

    @Override
    public void sepia() {
        model.sepia();
        BufferedImage image = model.getImage();
        view.updateDisplay(image);
    }

    @Override
    public void drawHorizontalRainbowStripes(int imageHeight, int imageWidth) {
        model.drawHorizontalRainbowStripes(imageHeight, imageWidth);
        BufferedImage image = model.getImage();
        view.updateDisplay(image);
    }

    // @Override
    // public void drawVerticalRainbowStripes(int imageHeight, int imageWidth) {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // public void drawCheckerBoard(int cellWidth) {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // public void drawFranceFlag(int flagWidth) {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // public void drawSwitzerlandFlag(int flagWidth) {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // public void drawGreeceFlag(int flagWidth) {
    // // TODO Auto-generated method stub
    //
    // }

    /**
     * Exit program.
     */
    @Override
    public void exitProgram() {
        System.exit(0);
    }

}
