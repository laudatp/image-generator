package model;

public class ImageFactoryImpl implements ImageFactory {

  public ImageFactoryImpl() {
    // Intentionally blank.
  }

  @Override
  public Image makeImage(String imageName, int height, int width) throws Exception {
    ImageTypes imageType = ImageTypes.getImageTypesByName(imageName.trim().toLowerCase());
    switch (imageType) {
      case HORIZONTAL_RAINBOW_STRIPES:
        return new HorizontalRainbow(height, width);
      case VERTICAL_RAINBOW_STRIPES:
        return new VerticalRainbow(height, width);
      default:
        throw new Exception("Invalid image type");
    }
  }

  @Override
  public Image makeImage(String imageName, int width) throws Exception {
    ImageTypes imageType = ImageTypes.getImageTypesByName(imageName.trim().toLowerCase());
    switch (imageType) {
      case CHECKERBOARD:
        return new Checkerboard(width);
      case FRANCE_FLAG:
        return new FranceFlag(width);
      case GREECE_FLAG:
        return new GreeceFlag(width);
      case SWITZERLAND_FLAG:
        return new SwitzerlandFlag(width);
      default:
        throw new Exception("Invalid image type");
    }
  }

}
