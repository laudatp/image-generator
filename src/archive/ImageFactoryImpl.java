package archive;

import archive.Checkerboard;
import archive.FranceFlag;
import archive.GreeceFlag;
import archive.HorizontalRainbow;
import archive.SwitzerlandFlag;
import archive.VerticalRainbow;
import model.ImageTypes;

public class ImageFactoryImpl implements ImageFactory {

  public ImageFactoryImpl() {
    // Intentionally blank.
  }

  @Override
  public Image generateImage(String imageName, int imageHeight, int imageWidth) {
    if (imageWidth <= 0) {
      throw new IllegalArgumentException("ImageWidth must be > 0");
    }
    if (imageHeight < 0) {
      throw new IllegalArgumentException("ImageHeight must be >= 0");
    }
    ImageTypes imageType = ImageTypes.getImageTypesByName(imageName.trim().toLowerCase());
    Image image;

    if (imageHeight > 0) {
      switch (imageType) {
        case HORIZONTAL_RAINBOW_STRIPES:
          image = new HorizontalRainbow(imageHeight, imageWidth);
          break;
        case VERTICAL_RAINBOW_STRIPES:
          image = new VerticalRainbow(imageHeight, imageWidth);
          break;
        default:
          throw new IllegalArgumentException("Illegal image type");
      }
    } else {
      switch (imageType) {
        case CHECKERBOARD:
          image = new Checkerboard(imageWidth);
          break;
        case FRANCE_FLAG:
          image = new FranceFlag(imageWidth);
          break;
        case GREECE_FLAG:
          image = new GreeceFlag(imageWidth);
          break;
        case SWITZERLAND_FLAG:
          image = new SwitzerlandFlag(imageWidth);
          break;
        default:
          throw new IllegalArgumentException("Illegal image type");
      }
    }
    return image;
  }
}
