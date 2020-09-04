package image.view;

import java.awt.Image;

import image.controller.Features;

/**
 * Represents the View for the Image Generator application.
 * 
 * @author  Peter Laudat
 * @version 09/20/2019
 */
public interface View {

    /**
     * Accept the set of callbacks from the controller, and hook up as needed to various things in this
     * view.
     * 
     * @param f the set of feature callbacks as a Features object
     */
    void setFeatures(Features f);

    /**
     * Update this view's display given the input file name.
     * 
     * @param inFile input file
     */
    void updateDisplay(String inFile);

    /**
     * Update this view's display with the given image.
     * 
     * @param image image
     */
    void updateDisplay(Image image);

}
