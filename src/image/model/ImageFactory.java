package image.model;
/**
 * 
 */

/**
 * @author Peter
 *
 */
public interface ImageFactory {
  public Image generateImage(String imageName, int imageHeight, int imageWidth);
}
