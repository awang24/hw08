package cs3500.animator.model.shape;

import java.awt.Color;

import cs3500.animator.model.Utils;

/**
 * Represents an Oval.
 */
public class Oval extends AShape {

  /**
   * Constructs a {@code Oval} object.
   *
   * @param name      Name of the shape
   * @param appear    Time when shape appears
   * @param disappear Time when shape disappears
   * @param p         Coordinate of shape
   * @param c         Color of shape
   * @param x         X radius of oval
   * @param y         Y radius of oval
   * @throws IllegalArgumentException if x or y are negative
   */
  public Oval(String name, int appear, int disappear, Posn p, Color c, double x, double y) {
    super(name, ShapeType.OVAL, appear, disappear, p, c, x, y);
  }

  @Override
  public Shapes accept(IShapeVisitor visitor) {
    return visitor.visit(this);
  }

  @Override
  public String location() {
    return "Center: " + Utils.getPosnString(this.getPosn());
  }

  @Override
  public String d1TagString() {
    return "X radius: ";
  }

  @Override
  public String d2TagString() {
    return "Y radius: ";
  }

  @Override
  public String toSVGTag() {
    return "<ellipse id=\"" + this.getName() + "\" cx=\"" + this.getPosn().getX() + "\" cy=\""
            + this.getPosn().getY() + "\" rx=\"" + this.getD1() + "\" ry=\"" + this.getD2()
            + "\" fill=\"rgb" + Utils.getNotFloatColorString(this.getColor())
            + "\" visibility=\"visible\">\n";
  }

  @Override
  public String svgAnimationTagX() {
    return "cx";
  }

  @Override
  public String svgAnimationTagY() {
    return "cy";
  }

  @Override
  public String svgEndTag() {
    return "</ellipse>";
  }

  @Override
  public String svgD1Tag() {
    return "rx";
  }

  @Override
  public String svgD2Tag() {
    return "ry";
  }
}
