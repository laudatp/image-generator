package image.controller;

import java.util.HashMap;
import java.util.Map;

public enum ImageCommands {

/** Load image. */
LOAD("load"),
/** Save image. */
SAVE("save"),
/** Generate image. */
GENERATE("generate"),
/** Blur image. */
BLUR("blur"),
/** Sharpen image. */
SHARPEN("sharpen"),
/** Sharpen image. */
GRAYSCALE("grayscale"),
/** Sepia image. */
SEPIA("sepia");

/** Image commands by name. */
private static Map<String, ImageCommands> imageCommandsByName = new HashMap<>();
/** Name. */
private String name;

static {
    for (ImageCommands i : ImageCommands.values()) {
        imageCommandsByName.put(i.getName(), i);
    }
}

private ImageCommands(String name) {
    this.name = name;
}

public String getName() {
    return name;
}

public static ImageCommands getImageCommandsByName(String name) {
    return imageCommandsByName.get(name);
}

@Override
public String toString() {
    return this.name;
}

}
