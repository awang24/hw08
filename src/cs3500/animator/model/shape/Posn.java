package cs3500.animator.model.shape;

/**
 * Represents a posn class of a coordinate.
 */
public class Posn {
  private double x;
  private double y;

  /**
   * Constructs a {@code Posn} object.
   *
   * @param x x coordinate of the position
   * @param y y coordinate of the position
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate of the posn.
   *
   * @return x coordinate of the posn
   */
  public double getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate of the posn.
   *
   * @return y coordinate of the posn
   */
  public double getY() {
    return this.y;
  }

}
