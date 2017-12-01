package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * This is an interface representation an animation model. Methods allow for adding shapes and
 * animations to the model, getting a string representation of the model and getting the list of
 * shapes and animations.
 */

public interface IAnimationModel {

  /**
   * Adds a shape to the list of shapes.
   *
   * @param s the shape to add into the list of shapes
   */
  void addShape(Shapes s);

  /**
   * Adds an animation to the list of animations.
   *
   * @param a the animation to add to the list of animations
   */
  void addAnimations(Animations a);

  /**
   * Return the present state of the animation as a string. The string is formatted as follows:
   * Shapes:[n] Name: String representing name of shape[n] Type: Type of shape[n] Location: ,
   * Dimensions: , Color: (double, double, double) [n] Appears at t=int [n] Disappears at t=int
   * [n][n] ... Lists out all shapes Animations from original state to new state from appear time to
   * disappear time [n] ... Lists out all animations [n] is a new line
   *
   * @return formatted string as above
   */
  String getDescription();

  /**
   * Returns the list of shapes.
   *
   * @return the list of shapes of the model.
   */
  List<Shapes> getShapes();

  /**
   * Returns the list of animations.
   *
   * @return the list of animations of the model.
   */
  List<Animations> getAnimations();

  /**
   * Returns the last end time from the list of animations.
   *
   * @return end time from the list of animations.
   */
  int getLastTime();

}
