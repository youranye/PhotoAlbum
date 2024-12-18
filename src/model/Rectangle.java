package model;

public class Rectangle extends AbstractShape{
  private int width;
  private int height;


  protected Rectangle(String name, int x, int y,
                 int xRadius, int yRadius,
                 float r, float g, float b) throws  IllegalArgumentException {
    super(name, x, y, r, g, b);
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Width or height should be positive");
    }
    this.width = xRadius;
    this.height = yRadius;
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.reference.getX(), this.reference.getY(),
            this.width, this.height, this.color.getR(), this.color.getG(), this.color.getB());
  }

  @Override
  public void resize(int x, int y) {
    super.resize(x, y);
    width = x;
    height = y;
  }

  @Override
  public String toString() {
    return "Name: " + super.name + "\n"
            + "Type: rectangle\n"
            + "Min corner: " + reference.toString() + ", Width: " + width + ", Height: " + height + "\n"
            + color.toString();
  }

  @Override
  public String toSvg() {
    return String.format(
            "<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"rgb(%d,%d,%d)\" />",
            name, reference.getX(), reference.getY(), width, height,
            (int) (color.getR() * 255), (int) (color.getG() * 255), (int) (color.getB() * 255)
    );
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }
}
