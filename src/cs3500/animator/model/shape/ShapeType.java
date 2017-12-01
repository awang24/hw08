package cs3500.animator.model.shape;

/**
 * Different types of shapes that are implemented.
 */
public enum ShapeType {
  RECTANGLE, OVAL;

  /**
   * Returns the string represetnation of the shape type.
   *
   * @return String representation of the shape type
   */
  public String toString() {
    switch (this) {
      case RECTANGLE:
        return "rectangle";
      case OVAL:
        return "oval";
      default:
        throw new AssertionError("Invalid shape Type");
    }
  }
}
