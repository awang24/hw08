/*
package cs3500.animator.provider.model;

*/
/**
 * We changed the toString() to toSring(int tempo) because the view takes a tempo to make the speed
 * change.
 *//*


import cs3500.animator.model.shapeAdapter.Color;
import cs3500.animator.model.shapeAdapter.Posn;

*/
/**
 * This is a Oval Class extends AbstractShape. It represents a oval that has a xRadius and yRadius.
 *//*

public class Oval extends AbstractShape {

  */
/**
   * Constructor for a Oval.
   *
   * @param name          the name of the oval.
   * @param shapeType     the type of the oval.
   * @param color         the color of the oval.
   * @param posn          the posn of the oval.
   * @param x             the xRadius of the oval.
   * @param y             the yRadius of the oval.
   * @param appearTime    the appear time of the oval.
   * @param disappearTime the disappear time of the oval.
   *//*

  public Oval(String name, ShapeType shapeType, Posn posn,
              Color color, float x, float y,
              int appearTime, int disappearTime) {
    super(name, shapeType, color, posn, x, y, appearTime, disappearTime);
  }


  */
/**
   * constructor for oval. This is to copy the data of this class.
   *
   * @param o the oval you want to copy.
   *//*

  public Oval(Oval o) {
    super(o.getName(), o.getShapeType(), o.getColor(), o.getPosn(),
            o.getX(), o.getY(), o.getAppearTime(), o.getDisappearTime());
  }


  @Override
  public String toString(int tempo) {
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getShapeType() + "\n"
            + "Center: " + this.getPosn().toString() + ", "
            + "X radius: " + this.getX() + ", "
            + "Y radius: " + this.getY() + ", "
            + "Color: (" + this.getColor().getRed() + ","
            + this.getColor().getGreen() + "," + this.getColor().getBlue() + ")" + "\n"
            + "Appears at t=" + this.getAppearTime() / tempo + "s\n"
            + "Disappears at t=" + this.getDisappearTime() / tempo + "s";
  }

  @Override
  public IShape getShape() {
    return new Oval(this);
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    String s = "<ellipse id=\"" + this.getName() + "\" cx=\"" + getPosn().getX() + "\" cy=\"" +
            getPosn().getY() + "\" rx=\"" + getX() + "\" ry=\"" + getY() + "\" fill=\"rgb(" +
            (int) (getColor().getRed() * 255) + "," + (int) (getColor().getGreen() * 255) + "," +
            (int) (getColor().getBlue() * 255) + ")\"";
    if (getAppearTime() != 0) {
      s = s + " visibility=\"hidden\">\n";
    } else {
      s = s + " visibility=\"visible\">\n";
    }

    return s + svgStateForLoop(tempo, loop);
  }

  @Override
  public String svgReset(boolean loop) {
    if (loop) {
      String s = "";
      s = s + svgFront() + "attributeName=\"fill\" to=\"rgb("
              + (int) (getColor().getRed() * 255) + "," + (int) (255 * getColor().getGreen())
              + "," + (int) (255 * getColor().getBlue()) + ")\" fill=\"freeze\" />\n";
      s = s + svgFront() + "attributeName=\"cx\" to=\""
              + getPosn().getX() + "\" fill=\"freeze\" />\n";
      s = s + svgFront() + "attributeName=\"cy\" to=\""
              + getPosn().getY() + "\" fill=\"freeze\" />\n";
      s = s + svgFront() + "attributeName=\"rx\" to=\""
              + getX() + "\" fill=\"freeze\" />\n";
      s = s + svgFront() + "attributeName=\"ry\" to=\""
              + getY() + "\" fill=\"freeze\" />\n";

      return s;
    } else {
      return "";
    }
  }

}
*/
