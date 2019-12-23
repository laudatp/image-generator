package model;

public class ImageDriver {
  public static final String PATH = "C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\";
  public static final String FILENAME_EXTENSION = ".jpg";
  public static final String HORIZONTAL_RAINBOW = "horizontalRainbow";
  public static final String CHECKERBOARD = "checkerboard";
  public static final String FRANCE_FLAG = "franceFlag";
  public static final String GREECE_FLAG = "greeceFlag";

  public static Image image;

  public static void main(String[] args) throws Exception {
    ImageFactory imageFactory = new ImageFactoryImpl();

    image = imageFactory.makeImage(HORIZONTAL_RAINBOW, 600, 800);
    image.save(getPath(HORIZONTAL_RAINBOW));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.sharpen();
    image.save(getPath("sharpenedHorizontalRainbow"));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.blur();
    image.save(getPath("blurredHorizontalRainbow"));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.grayscale();
    image.save(getPath("grayscaledHorizontalRainbow"));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.sepia();
    image.save(getPath("sepiaedHorizontalRainbow"));

    image = imageFactory.makeImage(CHECKERBOARD, 100);
    image.save(getPath(CHECKERBOARD));

    image.load(getPath(CHECKERBOARD));
    image.sharpen();
    image.save(getPath("sharpenedCheckerboard"));

    image.load(getPath(CHECKERBOARD));
    image.blur();
    image.save(getPath("blurredCheckerboard"));

    image.load(getPath(CHECKERBOARD));
    image.grayscale();
    image.save(getPath("grayscaledCheckerboard"));

    image.load(getPath(CHECKERBOARD));
    image.sepia();
    image.save(getPath("sepiaedCheckerboard"));

    image = imageFactory.makeImage(FRANCE_FLAG, 800);
    image.save(getPath(FRANCE_FLAG));

    image.load(getPath(FRANCE_FLAG));
    image.sharpen();
    image.save(getPath("sharpenedFranceFlag"));

    image.load(getPath(FRANCE_FLAG));
    image.blur();
    image.save(getPath("blurredFranceFlag"));

    image.load(getPath(FRANCE_FLAG));
    image.grayscale();
    image.save(getPath("grayscaledFranceFlag"));

    image.load(getPath(FRANCE_FLAG));
    image.sepia();
    image.save(getPath("sepiaedFranceFlag"));

    image = imageFactory.makeImage(GREECE_FLAG, 900);
    image.save(getPath(GREECE_FLAG));

    image.load(getPath(GREECE_FLAG));
    image.sharpen();
    image.save(getPath("sharpenedGreeceFlag"));

    image.load(getPath(GREECE_FLAG));
    image.blur();
    image.save(getPath("blurredGreeceFlag"));

    image.load(getPath(GREECE_FLAG));
    image.grayscale();
    image.save(getPath("grayscaledGreeceFlag"));

    image.load(getPath(GREECE_FLAG));
    image.sepia();
    image.save(getPath("sepiaedGreeceFlag"));

  }

  public static String getPath(String name) {
    return PATH + name + FILENAME_EXTENSION;
  }
}
