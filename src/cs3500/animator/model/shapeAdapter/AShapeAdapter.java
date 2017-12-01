package cs3500.animator.model.shapeAdapter;

import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.ShapeType;

public abstract class AShapeAdapter implements IShape{
  private Shapes aShape;

  public AShapeAdapter(Shapes aShape) {
    this.aShape = aShape;
  }

  @Override
  public Color getColor() {
    return new Color(this.aShape.getColor());
  }

  @Override
  public int getAppearTime() {
    return this.aShape.getAppear();
  }

  @Override
  public int getDisappearTime() {
    return this.aShape.getDisappear();
  }

  @Override
  public Posn getPosn() {
    return new Posn(this.aShape.getPosn().getX(), this.aShape.getPosn().getY());
  }

  @Override
  public String getName() {
    return this.aShape.getName();
  }

  @Override
  public ShapeType getShapeType() {
    if (this.aShape.getShapeType().equals(cs3500.animator.model.shape.ShapeType.RECTANGLE)) {
      return ShapeType.rectangle;
    } else {
      return ShapeType.oval;
    }
  }

  @Override
  public float getX() {
    return (float)this.aShape.getD1();
  }

  @Override
  public float getY() {
    return (float)this.aShape.getD2();
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
    java.awt.Color c = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
    this.aShape.setColor(c);
  }


  @Override
  public void setPosn(Posn posn) {
    cs3500.animator.model.shape.Posn p =
            new cs3500.animator.model.shape.Posn(posn.getX(), posn.getY());
    this.aShape.setPosn(p);
  }

  @Override
  public IShape getShapeForOperation() {
    return this;
  }


  @Override
  public void setX(float x) {
    this.aShape.setD1(x);
  }

  @Override
  public void setY(float y) {
    this.aShape.setD2(y);
  }

  @Override
  public abstract String svgState(int tempo, boolean loop);

  @Override
  public String svgStateForLoop(int tempo, boolean loop) {
    return this.aShape.toSVGTag();
  }

  @Override
  public String svgFront() {
    return "";//"    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"100ms\" ";
  }
}
