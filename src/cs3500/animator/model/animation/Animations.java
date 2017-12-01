package cs3500.animator.model.animation;

import cs3500.animator.model.shape.Shapes;

/**
 * The interface for all types of Animations. The methods for animations include animate,
 * getDescription and getter-methods for the different types of animations. All animations that
 * implement this interface will have these methods defined.
 */
public interface Animations {

  /**
   * Changes the appropriate fields of the shape that needs to be animated.
   *
   * @param currentTime the current time of the animation
   */
  void animate(double currentTime);

  /**
   * Returns the string representation of the animation.
   *
   * @return string representation of the animation
   */
  String getDescription();

  /**
   * Returns the string representation of the animation movement.
   *
   * @return string representation of the movement
   */
  String getMovement();

  /**
   * Returns the string representation of the beginning state of the animation.
   *
   * @return string representation of the beginning state
   */
  String getBeginState();

  /**
   * Returns the string representation of the ending state of the animation.
   *
   * @return string representation of the ending state
   */
  String getEndState();

  /**
   * Returns the shape that the animation will be implemented on.
   *
   * @return the shape that the animation will be animating on
   */
  Shapes getShape();

  /**
   * Returns the start time of the animation.
   *
   * @return the start time of the animation
   */
  int getStart();

  /**
   * Returns the end time of the animation.
   *
   * @return the end time of the animation
   */
  int getEnd();

  /**
   * Returns the type of animation.
   *
   * @return the type of animation
   */
  AnimationType getType();

  /**
   * Changes the fields of the shape to match the destination of the animation.
   */
  void changeField(Shapes s);

  /**
   * Sets the shape of the animation to a new shape.
   *
   * @param s shape to change the field of the animation to
   */
  void setShape(Shapes s);

  /**
   * Returns the svg tag representation of the animation.
   *
   * @return svg tag string representation of the animation
   */
  String toSVGTag(double tempo);

  /**
   * Returns the svg tag representation of the animation when there's a loop.
   *
   * @return svg tag string representation of the animation when there's a loop
   */
  String toSVGTagWithLoop(double tempo);

}
