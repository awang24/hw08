package cs3500.animator.model.shape;

/**
 * Represents an interface for visiting shapes.
 */
public interface IShapeVisitor {
  /**
   * Returns shape that has the appropriate changes made to it.
   *
   * @param shape the oval shape to visit
   * @return shape of the same type of shape taken in
   */
  Shapes visit(Oval shape);

  /**
   * Returns shape that has the appropriate changes made to it.
   *
   * @param shape the rectangle shape to visit
   * @return shape of the same type of shape taken in
   */
  Shapes visit(RectangleShape shape);
}
