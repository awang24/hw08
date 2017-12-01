package cs3500.animator.model.animation;

import cs3500.animator.model.shape.Shapes;
import cs3500.animator.model.Utils;

/**
 * Represents an abstract class for animations that have similar fields of AnimationType, shape,
 * start time and end time. The methods getDescription and getter-methods for the fields in the
 * abstract class are the same between all Animations, which is why they are in this abstract class.
 */
public abstract class AAnimations implements Animations {
  private AnimationType type;
  private Shapes shape;
  private int start;
  private int end;

  /**
   * Constructs a {@code AAnimation} object.
   *
   * @param type  The type of animation
   * @param shape The shape that the move animation will be implemented on
   * @param start The start time of the animation
   * @param end   The end time of the animation
   * @throws IllegalArgumentException if start or end is negative, or if end comes before start
   */
  public AAnimations(AnimationType type, Shapes shape, int start, int end) {
    if (Utils.isNegative(start) || Utils.isNegative(end)) {
      throw new IllegalArgumentException("Can not be negative");
    }
    if (end < start) {
      throw new IllegalArgumentException("Disappear time can not be before appear time");
    }
    this.type = type;
    this.shape = shape;
    this.start = start;
    this.end = end;
  }

  @Override
  public String getDescription() {
    return "shape " + this.shape.getName() + " " + this.getMovement() + " from "
            + this.getBeginState() + " to " + this.getEndState() + " from t=" + this.start
            + " to t=" + this.end;
  }

  @Override
  public Shapes getShape() {
    return this.shape;
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public AnimationType getType() {
    return this.type;
  }

  @Override
  public void setShape(Shapes s) {
    this.shape = s;
  }

}
