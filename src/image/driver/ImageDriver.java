package image.driver;

import java.io.FileReader;
import java.io.IOException;

import image.controller.ImageController;
import image.controller.ImageControllerImpl;
import image.model.Image;
import image.model.ImageImpl;

public class ImageDriver {

    /**
     * Drives the image application.
     * 
     * @param  args
     * @throws IOException if input file read problems
     */
    public static void main(String[] args) throws IOException {

        try {
            Readable in = new FileReader("C:/Users/Peter/mygitworkspace/git-repos/mvcPractice/imageCommands.txt");
            ImageController controller = new ImageControllerImpl(in);
            Image image = new ImageImpl();
            controller.go(image);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}
