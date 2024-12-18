package model;

import java.awt.*;

/**
 * The IShape interface defines the basic operations that can be performed on shapes.
 * Implementing classes must provide the logic for moving, changing colors, and modifying
 * specific properties of the shapes like radius or dimensions.
 */
public interface IShape {

  /**
   * Moves the shape to a new location based on the specified x and y coordinates.
   *
   * @param x the new x-coordinate for the shape's location
   * @param y the new y-coordinate for the shape's location
   */
  void move(int x, int y);

  /**
   * Changes the color of the shape to the specified color.
   *
   * @param r the red component (0.0 to 1.0)
   * @param g the green component (0.0 to 1.0)
   * @param b the blue component (0.0 to 1.0)
   */
  void changeColor(float r, float g, float b);

  /**
   * Resize one dimension of a shape.
   *
   * @param x  x-dimension
   * @param y  y-dimension
   */
  void resize(int x, int y);

  IShape copy();

  String toSvg();

  Color getColor();

  int getX();
  
  int getY();

  int getWidth();

  int getHeight();
}

