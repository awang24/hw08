package cs3500.animator.provider.model;


public interface IColor {
  /**
   * get the blue value of color.
   *
   * @return the blue value of color.
   */
  public float getBlue();

  /**
   * get the green value of color.
   *
   * @return the green value of color.
   */
  public float getGreen();

  /**
   * get the red value of color.
   *
   * @return the red value of color.
   */
  public float getRed();

  /**
   * returns the string representation of the color.
   *
   * @return the string representation of the color
   */
  public String toString();
}
