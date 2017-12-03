package cs3500.animator.model.shapeAdapter;

import java.awt.*;

import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.ShapeType;

/**
 * Represents the adapter class for converting our rectangle to the provider's rectangle.
 */
public class RectangleAdapter extends AShapeAdapter {

  //private Rectangle rect;

  /**
   * Constructor for RectangleAdapter.
   *
   * @param rect Our shape to convert.
   */
  public RectangleAdapter(Shapes rect) {
    super(rect);
    //this.rect = (Rectangle)rect;
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.rectangle;
  }

  @Override
  public IShape getShape() {
    return this;
  }

  @Override
  public IShape getShapeForOperation() {
    Posn p = new Posn(this.getPosn().getX(), this.getPosn().getY());
    Color c = new Color(this.getColor().getRed(), this.getColor().getGreen(),
            this.getColor().getBlue());
    Shapes rect = new Rectangle(this.getName(), this.getAppearTime(), this.getDisappearTime(), p,
            c, this.getX(), this.getY());
    return new RectangleAdapter(rect);
  }

}
