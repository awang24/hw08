package cs3500.animator.model.shapeAdapter;

import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.IShape;

/**
 * Represents the adapter class for converting our rectangle to the provider's rectangle.
 */
public class OvalAdapter extends AShapeAdapter {

  private Oval oval;

  /**
   * Constructor for OvalAdapter.
   *
   * @param oval Our shape to convert¬
   */
  public OvalAdapter(Shapes oval) {
    super(oval);
    this.oval = (Oval)oval;
  }

  @Override
  public String toString(int tempo) {
    return oval.toString();
  }

  @Override
  public IShape getShape() {
    return this;
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    return oval.toSVGTag();
  }

  @Override
  public String svgReset(boolean loop) {
    return "";
  }
}
