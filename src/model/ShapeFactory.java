package model;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
  private Map<String, ShapeCreator> creators;

  public ShapeFactory() {
    this.creators = new HashMap<>();
    // Register shape creators in the factory
    creators.put("OVAL", Oval::new);
    creators.put("RECTANGLE", Rectangle::new);
  }

  public IShape createShape(String shape, String name, int x, int y, int xRadius, int yRadius, float r, float g, float b) {
    ShapeCreator creator = creators.get(shape.toUpperCase());
    if (creator == null) {
      throw new IllegalArgumentException("Shape not supported: " + shape);
    }
    return creator.create(name, x, y, xRadius, yRadius, r, g, b);
  }

  // Functional interface to handle creation of shapes
  @FunctionalInterface
  interface ShapeCreator {
    IShape create(String name, int x, int y, int xRadius, int yRadius, float r, float g, float b);
  }
}

