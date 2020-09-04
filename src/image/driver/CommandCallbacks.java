package image.driver;

import image.controller.ControllerImpl;
import image.model.Model;
import image.model.ModelImpl;
import image.view.View;
import image.view.ViewImpl;

public class CommandCallbacks {

    public static void main(String[] args) {
        Model model = new ModelImpl();
        ControllerImpl controller = new ControllerImpl(model);
        View view = new ViewImpl("Image Generator");
        controller.setView(view);
    }

}
