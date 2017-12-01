package cs3500.animator.model.animationAdapter;

import cs3500.animator.model.animation.AnimationType;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shapeAdapter.Color;
import cs3500.animator.model.shapeAdapter.Posn;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.OperationType;

public abstract class AbstractAnimationAdapter implements IAnimationOperations {


  private Animations animations;
  private OperationType type;

  /**
   * Constructor for AbstractAnimationAdapter.
   *
   * @param animations Animation to convert to animation of provider's type
   */
  public AbstractAnimationAdapter(Animations animations) {
    this.animations = animations;

    if (animations.getType().equals(AnimationType.CHANGECOLOR)) {
      this.type = OperationType.changeColor;
    }
    else if (animations.getType().equals(AnimationType.CHANGEDIMENSION)) {
      this.type = OperationType.scale;
    }
    else if (animations.getType().equals(AnimationType.MOVE)) {
      this.type = OperationType.move;
    }
  }

  @Override
  public boolean validOperation(IAnimationOperations command) {
    return checkValidStartTimeAndEndTime() && checkOverlapCommand(command);
  }

  @Override
  public OperationType getType() {
    return this.type;
  }

  @Override
  public boolean checkOverlapCommand(IAnimationOperations command) {
    if (type.equals(command.getType())) {
      if (this.animations.getShape().getName().equals(command.getShape().getName())) {
        if (this.animations.getStart() <= command.getStartTime() &&
                this.animations.getEnd() >= command.getEndTime()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean checkValidStartTimeAndEndTime() {
    return (this.animations.getStart() >= this.animations.getShape().getAppear())
            && (this.animations.getEnd() <= this.animations.getShape().getDisappear());
  }

  @Override
  public abstract void updateShape(IShape s, int time);

  @Override
  public int compareTo(IAnimationOperations operation) {
    if (this.animations.getStart() < operation.getStartTime()) {
      return -1;
    } else if (this.animations.getStart() > operation.getStartTime()) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public abstract IShape getShape();

  @Override
  public abstract IShape getShapeForOperation();


  @Override
  public int getStartTime() {
    return this.animations.getStart();
  }

  @Override
  public int getEndTime() {
    return this.animations.getEnd();
  }

  @Override
  public abstract IAnimationOperations getOperation();

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
