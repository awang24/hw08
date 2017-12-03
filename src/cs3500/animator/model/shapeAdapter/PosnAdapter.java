package cs3500.animator.model.shapeAdapter;

import cs3500.animator.provider.model.IPosn;

/**
 * Represents a posn class. It has x and y coordinates.
 */
public class PosnAdapter implements IPosn {
  private double x;
  private double y;

  /**
   * Constructor of the PosnAdapter.
   *
   * @param x the x component of the position.
   * @param y the y component of the position.
   */
  public PosnAdapter(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

  @Override
  public float getX() {
    return (float)x;
  }

 @Override
  public float getY() {
    return (float)y;
  }

}
