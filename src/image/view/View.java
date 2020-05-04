package image.view;

import java.awt.Image;

import image.controller.Features;

public interface View {

    /**
     * Accept the set of callbacks from the controller, and hook up as needed to various things in this
     * view.
     * 
     * @param f the set of feature callbacks as a Features object
     */
    void setFeatures(Features f);

    void updateDisplay(String inFile);

    void updateDisplay(Image image);

}
