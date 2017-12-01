package cs3500.animator.model.shape;

import java.awt.Color;

import cs3500.animator.model.Utils;

/**
 * Represents an abstract class for shapes since all shape types share common fields. The methods
 * include getter and setter methods for these fields.
 */

public abstract class AShape implements Shapes {
  private final String name;
  private ShapeType type;
  private int appear;
  private int disappear;
  private Posn p;
  private Color c;
  private double d1;
  private double d2;
  private boolean render;

  /**
   * Constructs a {@code AShape} object.
   *
   * @param name      Name of the shape
   * @param appear    Time when shape appears
   * @param disappear Time when shape disappears
   * @param p         Coordinate of shape
   * @param c         Color of shape
   * @throws IllegalArgumentException if either time is negative, if the dimensions are negative or
   *                                  if the disappear time comes before the appear time
   */
  public AShape(String name, ShapeType type, int appear, int disappear, Posn p,
                Color c, double d1, double d2) {
    if (Utils.isNegative(appear) || Utils.isNegative(disappear)
            || Utils.isNegative(d1) || Utils.isNegative(d2)) {
      throw new IllegalArgumentException("Can not be negative");
    }
    if (disappear < appear) {
      throw new IllegalArgumentException("Disappear time can not be before appear time");
    }
    this.name = name;
    this.type = type;
    this.appear = appear;
    this.disappear = disappear;
    this.d1 = d1;
    this.d2 = d2;
    this.p = p;
    this.c = c;
    this.render = true;
  }

  @Override
  public String getState() {
    return "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n"
            + this.location() + ", "
            + this.getDimensions() + ", Color: "
            + Utils.getColorString(this.c) + "\n"
            + "Appears at t=" + appear + "\n" + "Disappears at t=" + disappear + "\n";
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeType getShapeType() {
    return this.type;
  }

  @Override
  public int getAppear() {
    return this.appear;
  }

  @Override
  public int getDisappear() {
    return this.disappear;
  }

  @Override
  public Posn getPosn() {
    return this.p;
  }

  @Override
  public Color getColor() {
    return this.c;
  }


  @Override
  public double getD1() {
    return this.d1;
  }


  @Override
  public double getD2() {
    return this.d2;
  }

  @Override
  public boolean getRender() {
    return this.render;
  }

  @Override
  public void setType(ShapeType type) {
    this.type = type;
  }

  @Override
  public void setAppear(int newAppear) {

    if (newAppear > this.disappear) {
      throw new IllegalArgumentException("Appear time can not be after disappear time");
    }
    this.appear = newAppear;
  }

  @Override
  public void setDisappear(int newDisappear) {
    if (newDisappear < this.appear) {
      throw new IllegalArgumentException("Disappear time can not be before appear time");
    }
    this.appear = newDisappear;
  }

  @Override
  public void setPosn(Posn newP) {
    this.p = newP;
  }

  @Override
  public void setColor(Color newC) {
    this.c = newC;
  }

  @Override
  public void setD1(double d1) {
    if (Utils.isNegative(d1)) {
      throw new IllegalArgumentException("New dimensions can not be negative");
    } else {
      this.d1 = d1;
    }
  }

  @Override
  public void setD2(double d2) {
    if (Utils.isNegative(d2)) {
      throw new IllegalArgumentException("New dimensions can not be negative");
    } else {
      this.d2 = d2;
    }
  }

  @Override
  public void setRender(boolean newRender) {
    this.render = newRender;
  }

  @Override
  public String getDimensions() {
    return this.d1TagString() + this.d1 + ", " + this.d2TagString() + this.d2;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Shapes)) {
      return false;
    }
    Shapes s = (Shapes) o;
    return this.name.equals(s.getName());
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }
}
