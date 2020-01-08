package image.controller;

import java.util.HashMap;
import java.util.Map;

public enum ImageCommands {

LOAD("load"),
SAVE("save"),
GENERATE("generate"),
BLUR("blur"),
SHARPEN("sharpen"),
GRAYSCALE("grayscale"),
SEPIA("sepia");

private static Map<String, ImageCommands> imageCommandsByName = new HashMap<>();
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
