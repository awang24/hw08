package cs3500.animator.provider.model;

/**
 * Represents a posn class. It has a x and y that are both double. It can represent a position.
 */
public class Posn implements IPosn{
  private float x;
  private float y;

  /**
   * Constructor of the Posn.
   *
   * @param x the x component of the position.
   * @param y the y component of the position.
   */
  public Posn(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Contructor of the Posn while taking a posn. This constructor is for making a copy of the Posn.
   *
   * @param p A posn that we assign its values to the fields.
   */
  public Posn(IPosn p) {
    this.x = p.getX();
    this.y = p.getY();
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

  /**
   * Gets the x-coordinate of the posn.
   *
   * @return the float that represents the position in x-coordinate.
   */
  public float getX() {
    return x;
  }

  /**
   * Gets the y-coordinate of the posn.
   *
   * @returnthe float that represents the position in y-coordinate.
   */
  public float getY() {
    return y;
  }

}
