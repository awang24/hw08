package cs3500.animator.model.shapeAdapter;

import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.IShape;

/**
 * Represents the adapter class for converting our rectangle to the provider's rectangle.
 */
public class RectangleAdapter extends AShapeAdapter {

  private RectangleShape rect;

  /**
   * Constructor for RectangleAdapter.
   *
   * @param rect Our shape to convert.
   */
  public RectangleAdapter(Shapes rect) {
    super(rect);
    this.rect = (RectangleShape)rect;
  }

  @Override
  public String toString(int tempo) {
    return rect.toString();
  }

  @Override
  public IShape getShape() {
    return this;
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    return rect.toSVGTag();
  }

  @Override
  public String svgReset(boolean loop) {
    return "";
  }
}
