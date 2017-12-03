package cs3500.animator.controller;

import javax.swing.Timer;

/**
 * This is the interface of the Animation controller. Classes that implement this interface only
 * need the method start to start the actual animation.
 */

public interface IAnimationController {

  /**
   * This method should start the animation using the provided model.
   */
  void start();

  /**
   * Retrieve the log from this controller.
   *
   * @return The log of this controller
   * @throws UnsupportedOperationException Throws exception if the controller does not support the
   *                                       functionality
   */
  Appendable getLog();

  /**
   * Retrieve the timer from this controller.
   * @return The timer of this controller
   * @throws UnsupportedOperationException Throws exception if the controller does not support the
   *                                       functionality
   */
  Timer getTimer();

  /**
   * Retrieves the tempo of the controller.
   * @return tempo used by controller
   * @throws UnsupportedOperationException Throws exception if the controller does not support the
   *                                       functionality
   */
  double getTempo();


}
