package image.archive;

import image.model.Image;
import image.model.ImageFactory;
import image.model.ImageFactoryImpl;

public class OldImageDriver {
  public static final String PATH = "C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\";
  public static final String FILENAME_EXTENSION = ".jpg";
  public static final String HORIZONTAL_RAINBOW = "horizontalRainbowStripes";
  public static final String VERTICAL_RAINBOW = "verticalRainbowStripes";
  public static final String CHECKERBOARD = "checkerboard";
  public static final String FRANCE_FLAG = "franceFlag";
  public static final String GREECE_FLAG = "greeceFlag";
  public static final String SWITZERLAND_FLAG = "switzerlandFlag";

  protected static Image image;

  public static void main(String[] args) throws Exception {
    ImageFactory imageFactory = new ImageFactoryImpl();

    image = imageFactory.generateImage(HORIZONTAL_RAINBOW, 600, 800);
    image.save(getPath(HORIZONTAL_RAINBOW));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.sharpen();
    image.save(getPath("sharpenedHorizontalRainbowStripes"));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.blur();
    image.save(getPath("blurredHorizontalRainbowStripes"));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.grayscale();
    image.save(getPath("grayscaledHorizontalRainbowStripes"));

    image.load(getPath(HORIZONTAL_RAINBOW));
    image.sepia();
    image.save(getPath("sepiaedHorizontalRainbowStripes"));

    image = imageFactory.generateImage(VERTICAL_RAINBOW, 600, 800);
    image.save(getPath(VERTICAL_RAINBOW));

    image.load(getPath(VERTICAL_RAINBOW));
    image.sharpen();
    image.save(getPath("sharpenedVerticalRainbowStripes"));

    image.load(getPath(VERTICAL_RAINBOW));
    image.blur();
    image.save(getPath("blurredVerticalRainbowStripes"));

    image.load(getPath(VERTICAL_RAINBOW));
    image.grayscale();
    image.save(getPath("grayscaledVerticalRainbowStripes"));

    image.load(getPath(VERTICAL_RAINBOW));
    image.sepia();
    image.save(getPath("sepiaedVerticalRainbowStripes"));

    image = imageFactory.generateImage(CHECKERBOARD, 100);
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

    image = imageFactory.generateImage(FRANCE_FLAG, 800);
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

    image = imageFactory.generateImage(GREECE_FLAG, 900);
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

    image = imageFactory.generateImage(SWITZERLAND_FLAG, 900);
    image.save(getPath(SWITZERLAND_FLAG));

    image.load(getPath(SWITZERLAND_FLAG));
    image.sharpen();
    image.save(getPath("sharpenedSwitzerlandFlag"));

    image.load(getPath(SWITZERLAND_FLAG));
    image.blur();
    image.save(getPath("blurredSwitzerlandFlag"));

    image.load(getPath(SWITZERLAND_FLAG));
    image.grayscale();
    image.save(getPath("grayscaledSwitzerlandFlag"));

    image.load(getPath(SWITZERLAND_FLAG));
    image.sepia();
    image.save(getPath("sepiaedSwitzerlandFlag"));

  }

  public static String getPath(String name) {
    return PATH + name + FILENAME_EXTENSION;
  }
}
