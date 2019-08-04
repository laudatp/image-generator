import java.io.IOException;

public class Driver {
    private static IModel model;

    public static void main(String[] args) throws IOException {
        model = new Model();
        model.generateCheckerboardImage(100);
        model.save("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\checkerboard.jpg");
        //model.generateHorizontalRainbowStripeImage(2000,3000);
        //model.save("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\hRainbow.jpg");
        //
        //model.generateVerticalRainbowStripeImage(2000,3000);
        //model.save("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\vRainbow.jpg");

        //model.load("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\van.jpg");
        //model.sharpen();
        //model.sharpen();
        //model.save("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\sharpenedvan.jpg");
        //model.load("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\van.jpg");
        //model.blur();
        //model.blur();
        //model.save("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\blurredvan.jpg");


    }
}
