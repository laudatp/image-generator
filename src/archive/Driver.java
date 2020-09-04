package archive;

import java.io.FileReader;
import java.io.IOException;

import image.controller.Controller;
import image.controller.BatchControllerImpl;
import image.model.Model;
import image.model.ModelImpl;

public class Driver {

    /**
     * Drives the model application.
     * 
     * @param  args
     * @throws IOException if input file read problems
     */
    public static void main(String[] args) throws IOException {

        try {
            Readable in = new FileReader("C:/Users/Peter/mygitworkspace/git-repos/mvcPractice/imageCommands.txt");
            Controller controller = new BatchControllerImpl(in);
            Model model = new ModelImpl();
            controller.go(model);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
