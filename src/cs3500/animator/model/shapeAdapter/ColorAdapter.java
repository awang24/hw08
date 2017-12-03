package cs3500.animator.model.shapeAdapter;

import cs3500.animator.provider.model.IColor;

/**
 * Represents color and has fields of red, green and blue.
 */
public class ColorAdapter implements IColor {
  private float red;
  private float green;
  private float blue;

  /**
   * Constructor of the ColorAdapter.
   *
   * @param c the java color that this class is representing
   */
  public ColorAdapter(java.awt.Color c) {
    this.red = (float)(c.getRed() / 255.0);
    this.green = (float)(c.getGreen() / 255.0);
    this.blue =  (float)(c.getBlue() / 255.0);
  }

  /**
   * Constructor of the ColorAdapter
   *
   * @param red   red value
   * @param green green value
   * @param blue  blue value
   */
  public ColorAdapter(float red, float green, float blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
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
