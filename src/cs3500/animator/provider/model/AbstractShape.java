/*
package cs3500.animator.provider.model;


import cs3500.animator.model.shapeAdapter.Color;
import cs3500.animator.model.shapeAdapter.Posn;

*/
/**
 * This is a abstract Class which implements IShape. This class includes basic fields and methods
 * for Shape.
 *//*

public abstract class AbstractShape implements IShape {
  private String name;
  private ShapeType shapeType;
  private Color color;
  private Posn posn;
  private int appearTime;
  private int disappearTime;
  private float x;
  private float y;

  */
/**
   * Constructor for AbstractShape.
   *
   * @param name          the name of the shape.
   * @param shapeType     the type of the shape.
   * @param color         the color of the shape.
   * @param posn          the posn of the shape.
   * @param appearTime    the appear time of the shape.
   * @param disappearTime the disappear time of the shape.
   *//*

  public AbstractShape(String name, ShapeType shapeType, Color color, Posn posn, float x, float y,
                       int appearTime, int disappearTime) {

    if (disappearTime < appearTime) {
      throw new IllegalArgumentException("Invalid appear time and disappear time.");
    }
    if (shapeType == null || name == null || color == null || posn == null) {
      throw new IllegalArgumentException("Found Null Value");
    }
    this.name = name;
    this.shapeType = shapeType;
    this.color = color;
    this.posn = posn;
    this.x = x;
    this.y = y;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }

  */
/**
   * copy constructor for Abstract shape.
   *
   * @param s the shape you want to copy.
   *//*

  public AbstractShape(AbstractShape s) {
    this.name = s.name;
    this.shapeType = s.shapeType;
    this.color = new Color(s.color);
    this.posn = new Posn(s.posn);
    this.x = s.x;
    this.y = s.y;
    this.appearTime = s.appearTime;
    this.disappearTime = s.disappearTime;
  }

  @Override
  public Color getColor() {
    return new Color(color);
  }

  @Override
  public int getAppearTime() {
    return appearTime;
  }

  @Override
  public int getDisappearTime() {
    return disappearTime;
  }

  @Override
  public Posn getPosn() {
    return new Posn(posn);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ShapeType getShapeType() {
    return shapeType;
  }

  @Override
  public float getX() {
    return x;
  }

  @Override
  public float getY() {
    return y;
  }

  @Override
  public abstract String toString(int tempo);

  @Override
  public abstract IShape getShape();

  @Override
  public void updateColor(Color c) {
    this.setColor(c);
  }

  @Override
  public void updatePosn(Posn p) {
    this.setPosn(p);
  }

  @Override
  public void updateScale(float x, float y) {
    this.setX(x);
    this.setY(y);
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }


  @Override
  public void setPosn(Posn posn) {
    this.posn = posn;
  }

  @Override
  public IShape getShapeForOperation() {
    return this;
  }


  @Override
  public void setX(float x) {
    this.x = x;
  }

  @Override
  public void setY(float y) {
    this.y = y;
  }

  @Override
  public abstract String svgState(int tempo, boolean loop);

  @Override
  public String svgStateForLoop(int tempo, boolean loop) {
    double temp = (double) tempo;
    String s = "";
    if (loop) {
      s = s + "\n    <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\" " +
              "begin=\"base.begin+" + (this.appearTime * 1000) / temp + "ms\" " +
              "fill=\"freeze\"/>";
      s = s + "\n    <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" " +
              "begin=\"base.begin+" + (1000 * this.disappearTime) / temp + "ms\" " +
              "fill=\"freeze\"/>\n";
    } else {
      if (getAppearTime() != 0) {
        s = s + "\n    <set attributeName=\"visibility\" " +
                "from=\"hidden\" " + "to=\"visible\" begin=\"" + (getAppearTime() * 1000) / temp
                + "ms\" "
                + "end=\"" + (1000 * getDisappearTime()) / tempo + "ms\"/>";
      } else {
        s = s + "\n    <set attributeName=\"visibility\" " +
                "from=\"visible\" " + "to=\"hidden\" begin=\"" +
                (1000 * getDisappearTime()) / temp + "ms\"/>";
      }
    }
    return s;
  }

  @Override
  public String svgFront() {
    return "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"100ms\" ";
  }


}
*/
