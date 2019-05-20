import java.io.IOException;

public class Driver {
    private static IModel model;

    public static void main(String[] args) throws IOException {
        model = new Model();
        model.load("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\van.jpg");
        model.blur();
        model.blur();
        model.blur();
        model.save("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\blurredvan.jpg");

    }
}
