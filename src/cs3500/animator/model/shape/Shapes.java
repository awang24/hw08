package cs3500.animator.model.shape;

import java.awt.Color;

/**
 * This is an interface representation of a shape.
 */

public interface Shapes {

  /**
   * Returns the appropriate shape with the appropriate changes made to it.
   *
   * @param visitor visitor that the shape will be used on
   * @return the shape that has the correct changes made to it
   */
  Shapes accept(IShapeVisitor visitor);

  /**
   * Returns the string representation of the shape.
   *
   * @return String representation of the shape.
   */
  String getState();

  /**
   * Returns the name of the shape.
   *
   * @return string representation of the name
   */
  String getName();

  /**
   * Returns the shape type of the shape.
   *
   * @return the ShapeType of the shape
   */
  ShapeType getShapeType();

  /**
   * Returns the appear time of the shape.
   *
   * @return the appear time of the shape
   */
  int getAppear();

  /**
   * Returns the disappear time of the shape.
   *
   * @return the disappear time of the shape
   */
  int getDisappear();

  /**
   * Returns the posn of the shape.
   *
   * @return the posn of the shape
   */
  Posn getPosn();

  /**
   * Returns the color of the shape.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Returns the first dimension of the shape.
   *
   * @return the first dimension of the shape
   */
  double getD1();

  /**
   * Returns the second dimension of the shape.
   *
   * @return the second dimension of the shape
   */
  double getD2();

  /**
   * Returns boolean of whether or not the shape should be rendered.
   *
   * @return boolean if shape should be rendered
   */
  boolean getRender();

  /**
   * Sets the type of this shape to another type.
   *
   * @param type the ShapeType to change this shape to
   */
  void setType(ShapeType type);

  /**
   * Sets the appear time of this shape to a new time.
   *
   * @param newAppear the appear time to change this shape to
   * @throws IllegalArgumentException if newAppear is negative or if the newAppear is greater than
   *                                  the disappear time
   */
  void setAppear(int newAppear);

  /**
   * Sets the disappear time of this shape to a new time.
   *
   * @param newDisapper the disappear time to change this shape to
   * @throws IllegalArgumentException if newDisappear is negative or if the newDisppear is smaller
   *                                  than the appear time
   */
  void setDisappear(int newDisapper);

  /**
   * Sets the coordinate of this shape to a new coordinate.
   *
   * @param newP the coordinate to change this shape to
   */
  void setPosn(Posn newP);

  /**
   * Sets the color of this shape to a new color.
   *
   * @param c the color to change this shape to
   */
  void setColor(Color c);

  /**
   * Sets the dimensions of this shape.
   *
   * @param d1 the first dimension to change this shape to
   * @throws IllegalArgumentException if the double is negative
   */
  void setD1(double d1);

  /**
   * Sets the dimensions of this shape.
   *
   * @param d2 the second dimension to change this shape to
   * @throws IllegalArgumentException if the double is negative
   */
  void setD2(double d2);

  /**
   * Sets the render boolean of this shape to be the opposite of the field.
   *
   * @param newRender render boolean to set this shape to
   */
  void setRender(boolean newRender);

  /**
   * Returns the string representation of the location.
   *
   * @return the string representation of the location
   */
  String location();

  /**
   * Returns the dimension tag of the first dimension.
   *
   * @return dimension tag of the first dimension
   */
  String d1TagString();

  /**
   * Returns the dimension tag of the second dimension.
   *
   * @return dimension tag of the second dimension
   */
  String d2TagString();

  /**
   * Returns the dimension of the shape.
   *
   * @return the string represenation of the dimension of the shape
   */
  String getDimensions();

  /**
   * Returns the svg tag of the shape.
   *
   * @return String svg representation of the shape
   */
  String toSVGTag();

  /**
   * Returns the svg tag x of the shape when animating it.
   *
   * @return String svg x representation of the shape when animating
   */
  String svgAnimationTagX();

  /**
   * Returns the svg tag y of the shape when animating it.
   *
   * @return String svg y representation of the shape when animating
   */
  String svgAnimationTagY();

  /**
   * Returns the svg end tag.
   *
   * @return String svg end tag
   */
  String svgEndTag();

  /**
   * Returns the svg dimension 1 tag.
   *
   * @return String svg dimension 1 tag
   */
  String svgD1Tag();

  /**
   * Returns the svg dimension 2 tag.
   *
   * @return String svg dimension 2 tag
   */
  String svgD2Tag();
}
