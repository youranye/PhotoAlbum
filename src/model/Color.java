package model;

/**
 * Represents a color in the RGB color model.
 * This class stores red, green, and blue components as float values ranging from 0.0 to 1.0.
 */
public class Color {
  private float red;
  private float green;
  private float blue;

  /**
   * Constructor to create a color with specified RGB values.
   * Each value should be in the range [0.0, 1.0].
   *
   * @param red the red component (0.0 to 1.0)
   * @param green the green component (0.0 to 1.0)
   * @param blue the blue component (0.0 to 1.0)
   */
  public Color(float red, float green, float blue) {
    if (red < 0.0f || red > 1.0f || green < 0.0f || green > 1.0f || blue < 0.0f || blue > 1.0f) {
      throw new IllegalArgumentException("RGB values must be between 0.0 and 1.0");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public Color(Color other) {
    this.red = other.red;
    this.green = other.green;
    this.blue = other.blue;
  }

  /**
   * Gets the red component of the color.
   *
   * @return the red component as a float between 0.0 and 1.0
   */
  public float getRed() {
    return red;
  }

  /**
   * Gets the green component of the color.
   *
   * @return the green component as a float between 0.0 and 1.0
   */
  public float getGreen() {
    return green;
  }

  /**
   * Gets the blue component of the color.
   *
   * @return the blue component as a float between 0.0 and 1.0
   */
  public float getBlue() {
    return blue;
  }

  /**
   * Sets the red component of the color.
   *
   * @param red the new red component value (0.0 to 1.0)
   */
  public void setRed(float red) {
    if (red < 0.0f || red > 1.0f) {
      throw new IllegalArgumentException("Red value must be between 0.0 and 1.0");
    }
    this.red = red;
  }

  /**
   * Sets the green component of the color.
   *
   * @param green the new green component value (0.0 to 1.0)
   */
  public void setGreen(float green) {
    if (green < 0.0f || green > 1.0f) {
      throw new IllegalArgumentException("Green value must be between 0.0 and 1.0");
    }
    this.green = green;
  }

  /**
   * Sets the blue component of the color.
   *
   * @param blue the new blue component value (0.0 to 1.0)
   */
  public void setBlue(float blue) {
    if (blue < 0.0f || blue > 1.0f) {
      throw new IllegalArgumentException("Blue value must be between 0.0 and 1.0");
    }
    this.blue = blue;
  }

  /**
   * Returns a string representation of the color in the format "Color: (red, green, blue)".
   *
   * @return a string representing the color
   */
  @Override
  public String toString() {
    return String.format("Color: (%.1f,%.1f,%.1f)", red, green, blue);
  }

  /**
   * Checks whether this color is equal to another color.
   * Colors are considered equal if all RGB components match.
   *
   * @param obj the object to compare this color with
   * @return true if the colors are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Color color = (Color) obj;
    return Float.compare(color.red, red) == 0 &&
            Float.compare(color.green, green) == 0 &&
            Float.compare(color.blue, blue) == 0;
  }

  /**
   * Returns a hash code for the color based on its RGB components.
   *
   * @return the hash code of the color
   */
  @Override
  public int hashCode() {
    return java.util.Objects.hash(red, green, blue);
  }

  public float getR() {
    return red;
  }

  public float getG() {
    return green;
  }

  public float getB() {
    return blue;
  }
}


