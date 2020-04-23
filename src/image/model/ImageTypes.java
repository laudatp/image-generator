package image.model;

/**
 * 
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author Peter
 *
 */
public enum ImageTypes {
HORIZONTAL_RAINBOW_STRIPES("horizontalrainbowstripes"),
VERTICAL_RAINBOW_STRIPES("verticalrainbowstripes"),
CHECKERBOARD("checkerboard"),
FRANCE_FLAG("franceflag"),
GREECE_FLAG("greeceflag"),
SWITZERLAND_FLAG("switzerlandflag");

private static Map<String, ImageTypes> imageTypesByName = new HashMap<>();
private String name;

static {
    for (ImageTypes i : ImageTypes.values()) {
        imageTypesByName.put(i.getName(), i);
    }
}

private ImageTypes(String name) {
    this.name = name;
}

public String getName() {
    return name;
}

public static ImageTypes getImageTypesByName(String name) {
    return imageTypesByName.get(name);
}

@Override
public String toString() {
    return this.name;
}

}
