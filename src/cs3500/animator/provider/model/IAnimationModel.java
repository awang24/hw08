package cs3500.animator.provider.model;

import java.util.ArrayList;

/**
 * HW7 We deleted the passToView method since the views don't take a model any more. Instead, they
 * take a list of shapes and a list of operations. As a result, the model doesn't need to pass the
 * current shapes to the view.
 */

/**
 * This is the interface of the Animation Model. It includes all the basic functions needed for
 * Animation Model. This interface is the top level interface. It can execute the operations to
 * shapes based on the information passing from the view.
 */
public interface IAnimationModel {
  /**
   * Add a new shape to the existing model.
   *
   * @param newShape the new shape user want to add to the model.
   */
  void addShape(IShape newShape);


  /**
   * Add the command to the existing model.
   *
   * @param command the new command user want to add to the model.
   */
  void addCommand(IAnimationOperations command);

  /**
   * getter for the list of shapes.
   *
   * @return a copy of the list of shapes.
   */
  ArrayList<IShape> getLoShape();

  /**
   * getter for the list of operations.
   *
   * @return a copy of the list of operations.
   */
  ArrayList<IAnimationOperations> getLoOperations();


}
