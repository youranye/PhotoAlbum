package model;


public class Point2D {
  private int x;
  private int y;

  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point2D(Point2D other) {
    this.x = other.x;
    this.y = other.y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return String.format("(%d,%d)", x, y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o.getClass() == this.getClass())) return false;
    Point2D point = (Point2D) o;
    return this.x == point.x && this.y == point.y;
  }

}
