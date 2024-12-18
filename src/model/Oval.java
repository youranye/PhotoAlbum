package model;

public class Oval extends AbstractShape {
  private int xRadius;
  private int yRadius;


  protected Oval(String name, int x, int y,
                 int xRadius, int yRadius,
                 float r, float g, float b) throws  IllegalArgumentException {
    super(name, x, y, r, g, b);
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Radius should be positive");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public IShape copy() {
    return new Oval(this.name, this.reference.getX(), this.reference.getY(),
            this.xRadius, this.yRadius, this.color.getR(), this.color.getG(), this.color.getB());
  }

  @Override
  public void resize(int x, int y) {
    super.resize(x, y);
    xRadius = x;
    yRadius = y;
  }

  @Override
  public String toString() {
    return "Name: " + super.name + "\n"
            + "Type: oval\n"
            + "Center: " + reference.toString() + ", X radius: " + xRadius + ", Y radius: " + yRadius + "\n"
            + color.toString();
  }

  @Override
  public String toSvg() {
    return String.format(
            "<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"rgb(%d,%d,%d)\" />",
            name, reference.getX(), reference.getY(), xRadius, yRadius,
            (int) (color.getR() * 255), (int) (color.getG() * 255), (int) (color.getB() * 255)
    );
  }

  @Override
  public int getWidth() {
    return xRadius;
  }

  @Override
  public int getHeight() {
    return yRadius;
  }
}
