package cs3500.animator.provider.model;


/**
 * HW7 we make the change of svgState. Now it takes one more parameter: a boolean loop for the
 * hybrid view. we added a method called svgStateForLoop. This method is written in the abstraction
 * class for the loop part.
 */


import cs3500.animator.model.shapeAdapter.ColorAdapter;
import cs3500.animator.model.shapeAdapter.PosnAdapter;

/**
 * This is the interface of the Shape. It may include all the basic functions needed for Shape.
 * Inside this interface, there are several methods that make some changes to the shape and getter
 * methods.
 */
public interface IShape {


  /**
   * Get the name of the shape.
   *
   * @return the name  of the shape.
   */
  String getName();

  /**
   * Get the Shape Type of the shape.
   *
   * @return the Shape Type of the shape.
   */
  ShapeType getShapeType();

  /**
   * Get the color of the shape.
   *
   * @return the color of the shape.
   */
  IColor getColor();


  /**
   * Get the position of the shape.
   *
   * @return the position of the shape.
   */
  IPosn getPosn();


  /**
   * Get the appear time of the shape.
   *
   * @return the appear time  of the shape.
   */
  int getAppearTime();

  /**
   * Get the disappear time of the shape.
   *
   * @return the disappear time of the shape.
   */
  int getDisappearTime();

  /**
   * Getter for x coordinate.
   *
   * @return x coordinate of shape.
   */
  float getX();

  /**
   * Getter for y coordinate.
   *
   * @return y coordinate of shape.
   */
  float getY();

  /**
   * Getter for IShape.
   *
   * @return the copy of IShape.
   */
  IShape getShape();

  /**
   * return the state of the shape.
   *
   * @return the state of the shape as string.
   */
  String toString(int tempo);


  /**
   * Update the shape's color to the given c, and then return a copy of the shape.
   *
   * @param c the color that is going to assign in
   */
  void updateColor(IColor c);

  /**
   * Update the shape's posn to the given p, and then return a copy of the shape.
   *
   * @param p the posn that is going to assign in
   */
  void updatePosn(IPosn p);

  /**
   * Update the shape's x and y to the given x and y, and then return a copy of the shape.
   *
   * @param x the xRadius/width that is going to assign in
   * @param y the yRadius/height that is going to assign in
   */
  void updateScale(float x, float y);

  /**
   * Get the SVG version text based on the corresponding tempo and loop status.
   *
   * @param tempo the corresponding time.
   * @param loop  if loop or not.
   * @return get the svg version test.
   */
  String svgState(int tempo, boolean loop);

  /**
   * The helper method for svgState. Get the svg version text with loop based on the corresponding
   * tempo.
   *
   * @param tempo the corresponding time.
   * @param loop  if loop or not.
   * @return A string contains the svg state with looped.
   */
  String svgStateForLoop(int tempo, boolean loop);

  /**
   * getter of the shape for the operation.
   *
   * @return the shape.
   */
  IShape getShapeForOperation();

  /**
   * get the svg for loop.
   *
   * @return the svg for loop
   */
  String svgReset(boolean loop);

  /**
   * get the head of the reset of the svg.
   *
   * @return the head of the svg
   */
  String svgFront();

  /**
   * set the color to the shape.
   *
   * @param color the value that is going to be assigned in.
   */
  void setColor(IColor color);

  /**
   * set the PosnAdapter to the shape.
   *
   * @param posn the value that is going to be assigned in.
   */
  void setPosn(IPosn posn);

  /**
   * set the x to the shape.
   *
   * @param x the value that is going to be assigned in.
   */
  void setX(float x);

  /**
   * set the y to the shape.
   *
   * @param y the value that is going to be assigned in.
   */
  void setY(float y);

  /**
   * copy the shape.
   * @return a copy of shape.
   */
  IShape copyShape();
}
