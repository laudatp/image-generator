package model;
/**
 * 
 */

/**
 * @author Peter
 *
 */
public interface ImageFactory {

  public Image makeImage(String imageName, int height, int width) throws Exception;

  public Image makeImage(String imageName, int width) throws Exception;
}
