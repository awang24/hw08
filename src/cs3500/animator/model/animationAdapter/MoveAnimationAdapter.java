package cs3500.animator.model.animationAdapter;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeDimension;
import cs3500.animator.model.animation.MoveAnimation;
import cs3500.animator.model.shape.CreateShapeVisitor;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.OperationType;

public class MoveAnimationAdapter extends AbstractAnimationAdapter {

  private Shapes s;
  private Animations a;

  /**
   * Constructor for AbstractAnimationAdapter.
   *
   * @param animations Animation to convert to animation of provider's type
   */
  public MoveAnimationAdapter(Animations animations) {
    super(animations);
    this.s = animations.getShape();
    this.a = animations;
  }

  @Override
  public OperationType getType() {
    return OperationType.move;
  }

  @Override
  public IAnimationOperations getOperation() {
    return copyOperation();
  }

  @Override
  public IAnimationOperations copyOperation() {
    Shapes newShape = this.s.accept(new CreateShapeVisitor());

    Posn p1 = new Posn(this.getSrcPosn().getX(), this.getSrcPosn().getY());
    Posn p2 = new Posn(this.getDestPosn().getX(), this.getDestPosn().getY());

    MoveAnimation c = new MoveAnimation(newShape, this.a.getStart(),
            this.a.getEnd(), p1, p2);
    return new MoveAnimationAdapter(c);
  }
}
