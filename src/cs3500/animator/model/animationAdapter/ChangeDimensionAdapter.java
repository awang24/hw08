package cs3500.animator.model.animationAdapter;

import java.awt.*;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.animation.ChangeDimension;
import cs3500.animator.model.shape.CreateShapeVisitor;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.OperationType;

public class ChangeDimensionAdapter extends AbstractAnimationAdapter {

  private Shapes s;
  private Animations animations;

  /**
   * Constructor for AbstractAnimationAdapter.
   *
   * @param animations Animation to convert to animation of provider's type
   */
  public ChangeDimensionAdapter(Animations animations) {
    super(animations);
    this.s = animations.getShape();
    this.animations = animations;
  }

  @Override
  public OperationType getType() {
    return OperationType.scale;
  }

  @Override
  public IAnimationOperations getOperation() {
    return copyOperation();
  }

  @Override
  public IAnimationOperations copyOperation() {
    Shapes newShape = this.s.accept(new CreateShapeVisitor());

    ChangeDimension c = new ChangeDimension(newShape, this.animations.getStart(),
            this.animations.getEnd(), this.animations.getOriginalD1(),
            this.animations.getOriginalD2(), this.animations.getNewD1(),
            this.animations.getNewD2());
    return new ChangeDimensionAdapter(c);
  }

}
