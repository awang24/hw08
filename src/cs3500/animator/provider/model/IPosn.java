package cs3500.animator.provider.model;

/**
 * Represents a posn.
 */

public interface IPosn {
  /**
   * Returns the string representation of the posn.
   *
   * @return string representation of the posn.
   */
  public String toString();

  /**
   * Gets the x-coordinate of the posn.
   *
   * @return the float that represents the position in x-coordinate.
   */
  public float getX();

  /**
   * Gets the y-coordinate of the posn.
   *
   * @returnthe float that represents the position in y-coordinate.
   */
  public float getY();
}
