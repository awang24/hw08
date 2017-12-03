package cs3500.animator.model.animation;

import java.awt.*;

import cs3500.animator.model.shape.Posn;
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

  /**
   * Returns original x dimension.
   * @return original x dimension
   */
  double getOriginalD1();

  /**
   * Returns original y dimension.
   * @return original y dimension
   */
  double getOriginalD2();

  /**
   * Returns new x dimension.
   * @return new x dimension
   */
  double getNewD1();

  /**
   * Returns new y dimension.
   * @return new y dimension
   */
  double getNewD2();

  /**
   * Returns old color.
   * @return old color
   */
  Color getOldColor();

  /**
   * Returns new color.
   * @return new color
   */
  Color getNewColor();

  /**
   * Returns old posn.
   * @return old posn
   */
  Posn getOldP();

  /**
   * Returns new posn.
   * @return new posn
   */
  Posn getNewP();


}
