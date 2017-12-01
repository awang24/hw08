package cs3500.animator.provider.model;

import cs3500.animator.model.shapeAdapter.Color;
import cs3500.animator.model.shapeAdapter.Posn;

/**
 * This is an abstract Class which implements IAnimationOperations. This class includes basic fields
 * and methods for AnimationOperations.
 */
public abstract class AbstractAnimationOperations implements IAnimationOperations {
  private OperationType type;
  private IShape shape;
  private int startTime;
  private int endTime;


  /**
   * Constructor for AbstractAnimationOperations.
   *
   * @param shape     the shape that the operation is going to operate on.
   * @param startTime the start time of operation.
   * @param endTime   the end time of operation.
   */
  public AbstractAnimationOperations(OperationType type, IShape shape, int startTime,
                                     int endTime) {
    if (startTime > endTime) {
      throw new IllegalArgumentException("Illegal Start Time and End Time.");
    }
    if (shape == null || type == null) {
      throw new IllegalArgumentException("Find null value");
    }
    this.type = type;
    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public boolean validOperation(IAnimationOperations command) {
    return checkValidStartTimeAndEndTime() && checkOverlapCommand(command);
  }

  @Override
  public OperationType getType() {
    return type;
  }

  @Override
  public boolean checkOverlapCommand(IAnimationOperations command) {
    if (type.equals(command.getType())) {
      if (this.shape.getName().equals(command.getShape().getName())) {
        if (this.startTime <= command.getStartTime() &&
                this.endTime >= command.getEndTime()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean checkValidStartTimeAndEndTime() {
    return (startTime >= shape.getAppearTime())
            && (endTime <= shape.getDisappearTime());
  }

  @Override
  public abstract void updateShape(IShape s, int time);

  @Override
  public int compareTo(IAnimationOperations operation) {
    if (this.startTime < operation.getStartTime()) {
      return -1;
    } else if (this.startTime > operation.getStartTime()) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public IShape getShape() {
    return this.shape.getShape();
  }

  @Override
  public IShape getShapeForOperation() {
    return this.shape.getShapeForOperation();
  }


  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public abstract IAnimationOperations getOperation();


  /**
   * return the current status of a based on a math equation.
   *
   * @param time      The current time.
   * @param a         The original status.
   * @param b         The final status.
   * @param startTime The start time of the status.
   * @param endTime   The end time of the status.
   * @return the current status.
   */
  protected float getCurrentState(float time, float a, float b, float startTime, float endTime) {
    return a * ((endTime - time) / (endTime - startTime))
            + b * ((time - startTime) / (endTime - startTime));
  }

  @Override
  public abstract String svgState(int tempo, boolean loop);

  @Override
  public float getChangedHeight() {
    return -1;
  }

  @Override
  public float getChangedWidth() {
    return -1;
  }

  @Override
  public Posn getDestPosn() {
    return null;
  }

  @Override
  public Posn getSrcPosn() {
    return null;
  }

  @Override
  public Color getNewColor() {
    return null;
  }
}
