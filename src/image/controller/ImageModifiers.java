package image.controller;

import java.util.HashMap;
import java.util.Map;

public enum ImageModifiers {

/** Load model. */
LOAD("load"),
/** Save model. */
SAVE("save"),
/** Generate model. */
GENERATE("generate"),
/** Blur model. */
BLUR("blur"),
/** Sharpen model. */
SHARPEN("sharpen"),
/** Sharpen model. */
GRAYSCALE("grayscale"),
/** Sepia model. */
SEPIA("sepia");

/** Model commands by name. */
private static Map<String, ImageModifiers> imageModifiersByName = new HashMap<>();
/** Name. */
private String name;

static {
    for (ImageModifiers i : ImageModifiers.values()) {
        imageModifiersByName.put(i.getName(), i);
    }
}

private ImageModifiers(String name) {
    this.name = name;
}

public String getName() {
    return name;
}

public static ImageModifiers getImageModifiersByName(String name) {
    return imageModifiersByName.get(name);
}

@Override
public String toString() {
    return this.name;
}

}
