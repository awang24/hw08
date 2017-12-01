package cs3500.animator.model.animationAdapter;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.animationAdapter.AbstractAnimationAdapter;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.model.shapeAdapter.Color;
import cs3500.animator.model.shapeAdapter.OvalAdapter;
import cs3500.animator.model.shapeAdapter.RectangleAdapter;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.OperationType;
import cs3500.animator.model.shapeAdapter.Posn;

public class ChangeColorAdapter extends AbstractAnimationAdapter {

  private ChangeColor cc;
  private IShape shape;

  /**
   * Constructor for adapter for change color.
   *
   * @param cc Animation to change to an IAnimationOperations
   */
  public ChangeColorAdapter(Animations cc) {
    super(cc);
    this.cc = (ChangeColor) cc;

    Shapes s = this.cc.getShape();
    if (s.getShapeType().equals(ShapeType.RECTANGLE)) {
      this.shape = new RectangleAdapter(s);
    } else if (s.getShapeType().equals(ShapeType.OVAL)) {
      this.shape = new OvalAdapter(s);
    }
  }

  @Override
  public String toString(int tempo) {
    return cc.toString();
  }

  @Override
  public void updateShape(IShape s, int time) {

  }

  @Override
  public IShape getShape() {
    return this.shape;
  }

  @Override
  public IShape getShapeForOperation() {
    return this.shape.getShapeForOperation();
  }

  @Override
  public IAnimationOperations getOperation() {
    return this;
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    if (loop) {
      return this.cc.toSVGTagWithLoop(tempo);
    } else {
      return this.cc.toSVGTag(tempo);
    }
  }

}
