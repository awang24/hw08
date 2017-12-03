package cs3500.animator.provider.model;

/**
 * This is a class represents color and it has fields of red, green and blue, which are basic
 * components of color.
 */
public class Color implements IColor{
  protected float red;
  protected float green;
  protected float blue;

  /**
   * Constructor of the Color.
   *
   * @param red   the value of red in the color.
   * @param green the value of green in the color.
   * @param blue  the value of blue in the color.
   */
  public Color(float red, float green, float blue) {
    if (red > 1.0 || red < 0.0) {
      throw new IllegalArgumentException("Invalid red");
    } else {
      this.red = red;
    }

    if (green > 1.0 || green < 0.0) {
      throw new IllegalArgumentException("Invalid green");
    } else {
      this.green = green;
    }

    if (blue > 1.0 || blue < 0.0) {
      throw new IllegalArgumentException("Invalid blue");
    } else {
      this.blue = blue;
    }
  }

  /**
   * Constructor of Color. The purpose of it is to get a copy of the Color.
   *
   * @param c a color that we assign its values to the fields.
   */
  public Color(IColor c) {
    this.red = c.getRed();
    this.green = c.getGreen();
    this.blue = c.getBlue();
  }

  /**
   * get the blue value of color.
   *
   * @return the blue value of color.
   */
  public float getBlue() {
    return blue;
  }

  /**
   * get the green value of color.
   *
   * @return the green value of color.
   */
  public float getGreen() {
    return green;
  }

  /**
   * get the red value of color.
   *
   * @return the red value of color.
   */
  public float getRed() {
    return red;
  }

  @Override
  public String toString() {
    return "(" + red + ", " + green + ", " + blue + ")";
  }
}
