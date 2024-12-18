package model;

import java.awt.*;

public class AbstractShape implements IShape {
  protected Color color;
  protected Point2D reference;
  protected final String name;

  protected AbstractShape(String name, int x, int y, float r, float g, float b)
          throws IllegalArgumentException {
    this.color = new Color(r, g, b);
    this.reference = new Point2D(x, y);
    this.name = name;
  }

  @Override
  public void move(int x, int y) {
    this.reference = new Point2D(x, y);
  }

  @Override
  public void changeColor(float r, float g, float b) {
    this.color = new Color(r, g, b);
  }

  @Override
  public void resize(int x, int y) {
    if (x <= 0 || y <= 0) {
      throw new IllegalArgumentException("Each dimension should be greater than 0");
    }
  }

  @Override
  public IShape copy() {
    return null;
  }

  @Override
  public String toSvg() {
    return "";
  }

  @Override
  public Color getColor() {
    return new Color(color);
  }

  @Override
  public int getX() {
    return reference.getX();
  }

  @Override
  public int getY() {
    return reference.getY();
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }
}
