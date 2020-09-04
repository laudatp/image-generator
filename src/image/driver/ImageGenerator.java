package image.driver;

import image.controller.ControllerImpl;
import image.model.Model;
import image.model.ModelImpl;
import image.view.View;
import image.view.ViewImpl;

/**
 * Main entry point for Image Generator application, which generates, filters, transforms, opens,
 * and saves images.
 * 
 * @author  Peter Laudat
 * @version 09/2020
 *
 */
public class ImageGenerator {

    public static void main(String[] args) {
        Model model = new ModelImpl();
        ControllerImpl controller = new ControllerImpl(model);
        View view = new ViewImpl("Image Generator");
        controller.setView(view);
    }

}
