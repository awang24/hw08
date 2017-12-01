package cs3500.animator.model.shapeAdapter;

import cs3500.animator.provider.model.IColor;

/**
 * Represents color and has fields of red, green and blue.
 */
public class Color implements IColor{
  private float red;
  private float green;
  private float blue;

  /**
   * Constructor of the Color.
   *
   * @param c the java color that this class is representing
   */
  public Color(java.awt.Color c) {
    this.red = c.getRed() / 255;
    this.green = c.getGreen() / 255;
    this.blue = c.getBlue() / 255;
  }

  @Override
  public float getBlue() {
    return blue;
  }

  @Override
  public float getGreen() {
    return green;
  }

  @Override
  public float getRed() {
    return red;
  }

  @Override
  public String toString() {
    return "(" + red + ", " + green + ", " + blue + ")";
  }
}
