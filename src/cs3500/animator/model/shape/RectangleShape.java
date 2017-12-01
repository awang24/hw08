package cs3500.animator.model.shape;

import java.awt.Color;

import cs3500.animator.model.Utils;

/**
 * Represents a rectangle.
 */
public class RectangleShape extends AShape {

  /**
   * Constructs a {@code RectangleShape} object.
   *
   * @param name      Name of the shape
   * @param appear    Time when shape appears
   * @param disappear Time when shape disappears
   * @param p         Coordinate of shape
   * @param c         Color of shape
   * @param w         Width of rectangle
   * @param h         Height of rectangle
   * @throws IllegalArgumentException if width or height are negative
   */
  public RectangleShape(String name, int appear, int disappear, Posn p, Color c,
                        double w, double h) {
    super(name, ShapeType.RECTANGLE, appear, disappear, p, c, w, h);
  }

  @Override
  public Shapes accept(IShapeVisitor visitor) {
    return visitor.visit(this);
  }

  @Override
  public String location() {
    return "Lower-left corner: " + Utils.getPosnString(this.getPosn());
  }

  @Override
  public String d1TagString() {
    return "Width: ";
  }

  @Override
  public String d2TagString() {
    return "Height: ";
  }

  @Override
  public String toSVGTag() {
    return "<rect id=\"" + this.getName() + "\" x=\"" + this.getPosn().getX() + "\" y=\""
            + this.getPosn().getY() + "\" width=\"" + this.getD1() + "\" height=\"" + this.getD2()
            + "\" fill=\"rgb(" + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")\" visibility=\"visible\">\n";
  }

  @Override
  public String svgAnimationTagX() {
    return "x";
  }

  @Override
  public String svgAnimationTagY() {
    return "y";
  }

  @Override
  public String svgEndTag() {
    return "</rect>\n";
  }

  @Override
  public String svgD1Tag() {
    return "width";
  }

  @Override
  public String svgD2Tag() {
    return "height";
  }

}
