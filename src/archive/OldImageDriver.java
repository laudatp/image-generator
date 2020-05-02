package image.archive;

import image.model.Model;
import image.model.Model;
import image.model.Model;

public class OldImageDriver {
  public static final String PATH = "C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\";
  public static final String FILENAME_EXTENSION = ".jpg";
  public static final String HORIZONTAL_RAINBOW = "horizontalRainbowStripes";
  public static final String VERTICAL_RAINBOW = "verticalRainbowStripes";
  public static final String CHECKERBOARD = "checkerboard";
  public static final String FRANCE_FLAG = "franceFlag";
  public static final String GREECE_FLAG = "greeceFlag";
  public static final String SWITZERLAND_FLAG = "switzerlandFlag";

  protected static Model model;

  public static void main(String[] args) throws Exception {
    ImageFactory imageFactory = new ImageFactoryImpl();

    model = imageFactory.generateModel(HORIZONTAL_RAINBOW, 600, 800);
    model.save(getPath(HORIZONTAL_RAINBOW));

    model.load(getPath(HORIZONTAL_RAINBOW));
    model.sharpen();
    model.save(getPath("sharpenedHorizontalRainbowStripes"));

    model.load(getPath(HORIZONTAL_RAINBOW));
    model.blur();
    model.save(getPath("blurredHorizontalRainbowStripes"));

    model.load(getPath(HORIZONTAL_RAINBOW));
    model.grayscale();
    model.save(getPath("grayscaledHorizontalRainbowStripes"));

    model.load(getPath(HORIZONTAL_RAINBOW));
    model.sepia();
    model.save(getPath("sepiaedHorizontalRainbowStripes"));

    model = imageFactory.generateModel(VERTICAL_RAINBOW, 600, 800);
    model.save(getPath(VERTICAL_RAINBOW));

    model.load(getPath(VERTICAL_RAINBOW));
    model.sharpen();
    model.save(getPath("sharpenedVerticalRainbowStripes"));

    model.load(getPath(VERTICAL_RAINBOW));
    model.blur();
    model.save(getPath("blurredVerticalRainbowStripes"));

    model.load(getPath(VERTICAL_RAINBOW));
    model.grayscale();
    model.save(getPath("grayscaledVerticalRainbowStripes"));

    model.load(getPath(VERTICAL_RAINBOW));
    model.sepia();
    model.save(getPath("sepiaedVerticalRainbowStripes"));

    model = imageFactory.generateImage(CHECKERBOARD, 100);
    model.save(getPath(CHECKERBOARD));

    model.load(getPath(CHECKERBOARD));
    model.sharpen();
    model.save(getPath("sharpenedCheckerboard"));

    model.load(getPath(CHECKERBOARD));
    model.blur();
    model.save(getPath("blurredCheckerboard"));

    model.load(getPath(CHECKERBOARD));
    model.grayscale();
    model.save(getPath("grayscaledCheckerboard"));

    model.load(getPath(CHECKERBOARD));
    model.sepia();
    model.save(getPath("sepiaedCheckerboard"));

    model = imageFactory.generateImage(FRANCE_FLAG, 800);
    model.save(getPath(FRANCE_FLAG));

    model.load(getPath(FRANCE_FLAG));
    model.sharpen();
    model.save(getPath("sharpenedFranceFlag"));

    model.load(getPath(FRANCE_FLAG));
    model.blur();
    model.save(getPath("blurredFranceFlag"));

    model.load(getPath(FRANCE_FLAG));
    model.grayscale();
    model.save(getPath("grayscaledFranceFlag"));

    model.load(getPath(FRANCE_FLAG));
    model.sepia();
    model.save(getPath("sepiaedFranceFlag"));

    model = imageFactory.generateImage(GREECE_FLAG, 900);
    model.save(getPath(GREECE_FLAG));

    model.load(getPath(GREECE_FLAG));
    model.sharpen();
    model.save(getPath("sharpenedGreeceFlag"));

    model.load(getPath(GREECE_FLAG));
    model.blur();
    model.save(getPath("blurredGreeceFlag"));

    model.load(getPath(GREECE_FLAG));
    model.grayscale();
    model.save(getPath("grayscaledGreeceFlag"));

    model.load(getPath(GREECE_FLAG));
    model.sepia();
    model.save(getPath("sepiaedGreeceFlag"));

    model = imageFactory.generateImage(SWITZERLAND_FLAG, 900);
    model.save(getPath(SWITZERLAND_FLAG));

    model.load(getPath(SWITZERLAND_FLAG));
    model.sharpen();
    model.save(getPath("sharpenedSwitzerlandFlag"));

    model.load(getPath(SWITZERLAND_FLAG));
    model.blur();
    model.save(getPath("blurredSwitzerlandFlag"));

    model.load(getPath(SWITZERLAND_FLAG));
    model.grayscale();
    model.save(getPath("grayscaledSwitzerlandFlag"));

    model.load(getPath(SWITZERLAND_FLAG));
    model.sepia();
    model.save(getPath("sepiaedSwitzerlandFlag"));

  }

  public static String getPath(String name) {
    return PATH + name + FILENAME_EXTENSION;
  }
}
