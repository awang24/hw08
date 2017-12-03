package cs3500.animator.model.animationAdapter;

import java.awt.*;

import cs3500.animator.model.Utils;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.shape.CreateShapeVisitor;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.ChangeColorOperation;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.OperationType;

public class ChangeColorAdapter extends AbstractAnimationAdapter {

  private Shapes s;
  private Animations c;
  /**
   * Constructor for adapter for change color.
   *
   * @param cc Animation to change to an IAnimationOperations
   */
  public ChangeColorAdapter(Animations cc) {
    super(cc);
    this.c = cc;
    this.s = cc.getShape();
  }

  @Override
  public OperationType getType() {
    return OperationType.changeColor;
  }

  @Override
  public IAnimationOperations getOperation() {
    return copyOperation();
  }

  @Override
  public IAnimationOperations copyOperation() {
    Shapes newShape = this.s.accept(new CreateShapeVisitor());
    Color c1 = new Color(this.c.getOldColor().getRed(), this.c.getOldColor().getGreen(),
            this.c.getOldColor().getBlue());
    Color c2 = new Color(this.c.getNewColor().getRed(), this.c.getNewColor().getGreen(),
            this.c.getNewColor().getBlue());
    ChangeColor c = new ChangeColor(newShape, this.c.getStart(), this.c.getEnd(), c1, c2);
    return new ChangeColorAdapter(c);
  }

}
