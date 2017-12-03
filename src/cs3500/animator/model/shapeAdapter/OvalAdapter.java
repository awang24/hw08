package cs3500.animator.model.shapeAdapter;

import java.awt.*;

import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.ShapeType;

/**
 * Represents the adapter class for converting our rectangle to the provider's rectangle.
 */
public class OvalAdapter extends AShapeAdapter {

  //private Oval oval;

  /**
   * Constructor for OvalAdapter.
   *
   * @param oval Our shape to convert¬
   */
  public OvalAdapter(Shapes oval) {
    super(oval);
    //this.oval = (Oval)oval;
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.oval;
  }

  @Override
  public IShape getShape() {
    return this;
  }

  @Override
  public IShape getShapeForOperation() {
    Posn p = new Posn(this.getPosn().getX(), this.getPosn().getY());
    Color c = new Color(this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue());
    Shapes oval = new Oval(this.getName(), this.getAppearTime(), this.getDisappearTime(), p, c, this.getX(), this.getY());
    return new OvalAdapter(oval);
  }
}
