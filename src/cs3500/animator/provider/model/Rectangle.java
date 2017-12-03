
package cs3500.animator.provider.model;


/**
 * We changed the toString() to toSring(int tempo) because the view takes a tempo to make the speed
 * change.
 */

import cs3500.animator.model.shapeAdapter.ColorAdapter;
import cs3500.animator.model.shapeAdapter.PosnAdapter;


/**
 * This is a Rectangle Class extends AbstractShape. It represents a rectangle that has width and
 * height.
 */

public class Rectangle extends AbstractShape {



/**
   * Constructor for a Rectangle.
   *
   * @param name          the name of the oval.
   * @param shapeType     the type of the oval.
   * @param color         the color of the oval.
   * @param posn          the posn of the oval.
   * @param x             the width of the oval.
   * @param y             the height of the oval.
   * @param appearTime    the appear time of the oval.
   * @param disappearTime the disappear time of the oval.
   */

  public Rectangle(String name, ShapeType shapeType, IColor color, IPosn posn, float x, float y,
                   int appearTime, int disappearTime) {
    super(name, shapeType, color, posn, x, y, appearTime, disappearTime);

  }


/**
   * constructor for rectangle. This is for copy the data of this class.
   *
   * @param r the rectangle that we want to copy.
   */

  public Rectangle(Rectangle r) {
    super(r.getName(), r.getShapeType(), r.getColor(), r.getPosn(), r.getX(), r.getY(),
            r.getAppearTime(), r.getDisappearTime());
  }


  @Override
  public String toString(int tempo) {
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getShapeType() + "\n"
            + "Lower-left corner: " + this.getPosn().toString() + ", "
            + "Width: " + this.getX() + ", "
            + "Height: " + this.getY() + ", "
            + "ColorAdapter: (" + this.getColor().getRed() + ","
            + this.getColor().getGreen() + "," + this.getColor().getBlue() + ")" + "\n"
            + "Appears at t=" + this.getAppearTime() / tempo + "s\n"
            + "Disappears at t=" + this.getDisappearTime() / tempo + "s";
  }

  @Override
  public IShape getShape() {
    return new Rectangle(this);
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    double temp = (double) tempo;
    String s = "<rect id=\"" + this.getName() + "\" x=\"" + getPosn().getX() + "\" y=\"" +
            getPosn().getY() + "\" width=\"" + getX() + "\" height=\"" + getY() + "\" fill=\"" +
            "rgb(" + (int) (getColor().getRed() * 255) + "," + (int) (255 * getColor().getGreen())
            + "," +
            (int) (255 * getColor().getBlue()) + ")\" ";
    if (getAppearTime() / temp != 0) {
      s = s + " visibility=\"hidden\">\n";
    } else {
      s = s + " visibility=\"visible\">\n";
    }

    return s + svgStateForLoop(tempo, loop);
  }

  @Override
  public String svgReset(boolean loop) {
    String s = "";
    s = s + svgFront() + "attributeName=\"fill\" to=\"rgb("
            + (int) (getColor().getRed() * 255) + "," + (int) (255 * getColor().getGreen())
            + "," + (int) (255 * getColor().getBlue()) + ")\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"x\" to=\""
            + getPosn().getX() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"y\" to=\""
            + getPosn().getY() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"width\" to=\""
            + getX() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"height\" to=\""
            + getY() + "\" fill=\"freeze\" />\n";
    if (loop) {
      return s;
    } else {
      return "";
    }
  }

  @Override
  public IShape copyShape() {
    return new Rectangle(this);
  }

}

