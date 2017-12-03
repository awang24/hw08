package cs3500.animator.provider.model;

/**
 * HW7 We added a parameter called loop to the method svgState since the hybrid view needs to export
 * file based on the current loop status.
 */

import cs3500.animator.model.shapeAdapter.ColorAdapter;
import cs3500.animator.model.shapeAdapter.PosnAdapter;

/**
 * This is the interface of the AnimationOperations. It includes all the basic functions needed for
 * animation Operations. This interface is for changing the status for the shape such as moving the
 * shape, changing the shape's color, and scale the shape's width and height/x radius and y radius.
 */
public interface IAnimationOperations extends Comparable<IAnimationOperations> {
  /**
   * Return the Operation as a string.
   *
   * @return a string which explains the present operation.
   */
  String toString(int tempo);

  /**
   * Check if the operation is valid by checking the start time and end time and if two operations
   * are overlapping.
   *
   * @return true if the operation is valid.
   */
  boolean validOperation(IAnimationOperations command);

  /**
   * Update the properties of shape with command at corresponding time.
   *
   * @param s    The shape that is going to update
   * @param time The corresponding time.
   */
  void updateShape(IShape s, int time);

  /**
   * check if the intervals of commands are overlapped.
   *
   * @return true if the intervals are overlapped.
   */
  boolean checkOverlapCommand(IAnimationOperations command);

  /**
   * check if the start time and end time are valid.
   *
   * @return true if the start time and end time are valid.
   */
  boolean checkValidStartTimeAndEndTime();


  /**
   * getter for shape.
   *
   * @return a copy of shape.
   */
  IShape getShape();

  /**
   * getter for startTime.
   *
   * @return the startTime
   */
  int getStartTime();

  /**
   * getter for endTime.
   *
   * @return the endTime.
   */
  int getEndTime();

  /**
   * getter for operation type.
   *
   * @return the operation type.
   */
  OperationType getType();

  /**
   * getter for destination posn.
   * @return the destination posn.
   */
  IPosn getDestPosn();

  /**
   * Getter of the source position.
   *
   * @return the source position.
   */
  IPosn getSrcPosn();

  /**
   * getter of the changed Height.
   * @return the changed height
   */
  float getChangedHeight();

  /**
   * getter of the changed width.
   * @return the changed width.
   */
  float getChangedWidth();

  /**
   * getter of the old Height.
   * @return the old height
   */
  float getOldHeight();

  /**
   * getter of the old width.
   * @return the old width.
   */
  float getOldWidth();

  /**
   * getter for operation.
   *
   * @return a copy of the operation.
   */
  IAnimationOperations getOperation();


  /**
   * Get the new color.
   *
   * @return the newColor.
   */
  IColor getNewColor();

  /**
   * Get the old color.
   *
   * @return the oldColor.
   */
  IColor getOldColor();


  /**
   * Get the SVG version text based on the corresponding time and loop status.
   *
   * @param tempo the corresponding time.
   * @param loop if loop or not
   * @return get the svg version test.
   */
  String svgState(int tempo, boolean loop);

  /**
   * getter of the shape for the operation. In this method, it should just given the shape without
   * a copy.
   * @return the shape of the operation.
   */
  IShape getShapeForOperation();

  /**
   * copy the operation.
   * @return a copy of operation.
   */
  IAnimationOperations copyOperation();
}
