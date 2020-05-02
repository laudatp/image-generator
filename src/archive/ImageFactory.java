package image.model;
/**
 * 
 */

/**
 * @author Peter
 *
 */
public interface ImageFactory {
  public Model generateModel(String imageName, int imageHeight, int imageWidth);
}
