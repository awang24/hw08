package cs3500.animator.model.animationAdapter;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.model.shapeAdapter.ColorAdapter;
import cs3500.animator.model.shapeAdapter.OvalAdapter;
import cs3500.animator.model.shapeAdapter.PosnAdapter;
import cs3500.animator.model.shapeAdapter.RectangleAdapter;
import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IColor;
import cs3500.animator.provider.model.IPosn;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.OperationType;
import cs3500.animator.provider.model.Posn;

public abstract class AbstractAnimationAdapter implements IAnimationOperations {


  private Animations animations;
  private IShape shape;

  /**
   * Constructor for AbstractAnimationAdapter.
   *
   * @param animations Animation to convert to animation of provider's type
   */
  public AbstractAnimationAdapter(Animations animations) {
    this.animations = animations;

    Shapes s = this.animations.getShape();
    if (s.getShapeType().equals(ShapeType.RECTANGLE)) {
      this.shape = new RectangleAdapter(s);
    } else if (s.getShapeType().equals(ShapeType.OVAL)) {
      this.shape = new OvalAdapter(s);
    }

  }

  @Override
  public String toString(int tempo) {
    return animations.toString();
  }

  @Override
  public boolean validOperation(IAnimationOperations command) {
    return checkValidStartTimeAndEndTime() && checkOverlapCommand(command);
  }

  @Override
  public abstract OperationType getType();

  @Override
  public boolean checkOverlapCommand(IAnimationOperations command) {
    if (this.getType().equals(command.getType())) {
      if (this.animations.getShape().getName().equals(command.getShape().getName())) {
        if (this.animations.getStart() <= command.getStartTime() &&
                this.animations.getEnd() >= command.getEndTime()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean checkValidStartTimeAndEndTime() {
    return (this.animations.getStart() >= this.animations.getShape().getAppear())
            && (this.animations.getEnd() <= this.animations.getShape().getDisappear());
  }

  @Override
  public void updateShape(IShape s, int time) {
    if (s.getName().equals(this.shape.getName())) {
      Shapes myShape = null;
      cs3500.animator.model.shape.Posn p = new cs3500.animator.model.shape.Posn(s.getPosn().getX(),
              s.getPosn().getY());
      java.awt.Color c = new java.awt.Color(s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue());//((int)(s.getColor().getRed() * 255.0), (int)(s.getColor().getGreen() * 255.0),
      //(int)(s.getColor().getBlue() * 255.0));
      if (s.getShapeType().equals(cs3500.animator.provider.model.ShapeType.rectangle)) {
        myShape = new Rectangle(s.getName(), s.getAppearTime(), s.getDisappearTime(), p, c,
                s.getX(), s.getY());
      } else {
        myShape = new Oval(s.getName(), s.getAppearTime(), s.getDisappearTime(), p, c,
                s.getX(), s.getY());
      }
      this.animations.setShape(myShape);
      this.animations.animate(time);

      IColor newColor = new ColorAdapter(myShape.getColor());
      IPosn newP = new PosnAdapter(myShape.getPosn().getX(), myShape.getPosn().getY());
      //System.out.println(s.getName() + " " +  newColor.toString());
      s.setColor(newColor);
      s.setPosn(newP);
      s.setX((float) myShape.getD1());
      s.setY((float) myShape.getD2());
    }
  }

  @Override
  public int compareTo(IAnimationOperations operation) {
    if (this.animations.getStart() < operation.getStartTime()) {
      return -1;
    } else if (this.animations.getStart() > operation.getStartTime()) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public IShape getShape() {
    return this.shape;
  }

  @Override
  public IShape getShapeForOperation() {
    return this.shape.getShapeForOperation();
  }


  @Override
  public int getStartTime() {
    return this.animations.getStart();
  }

  @Override
  public int getEndTime() {
    return this.animations.getEnd();
  }

  @Override
  public abstract IAnimationOperations getOperation();

  @Override
  public String svgState(int tempo, boolean loop) {
    if (loop) {
      return this.animations.toSVGTagWithLoop(tempo);
    } else {
      return this.animations.toSVGTag(tempo);
    }
  }

  @Override
  public float getChangedHeight() {
    return (float) this.animations.getNewD2();
  }

  @Override
  public float getChangedWidth() {
    return (float) this.animations.getNewD1();
  }

  @Override
  public float getOldHeight() {
    return (float) this.animations.getOriginalD2();
  }

  @Override
  public float getOldWidth() {
    return (float) this.animations.getOriginalD2();
  }

  @Override
  public IPosn getDestPosn() {
    return new Posn((float) this.animations.getNewP().getX(),
            (float) this.animations.getNewP().getY());
  }

  @Override
  public IPosn getSrcPosn() {
    return new Posn((float) this.animations.getOldP().getX(),
            (float) this.animations.getOldP().getY());
  }

  @Override
  public IColor getNewColor() {
    return new Color(this.animations.getNewColor().getRed() / (float) 255,
            this.animations.getNewColor().getGreen() / (float) 255,
            this.animations.getNewColor().getBlue() / (float) 255);
  }

  @Override
  public IColor getOldColor() {
    return new Color(this.animations.getOldColor().getRed() / (float) 255,
            this.animations.getOldColor().getGreen() / (float) 255,
            this.animations.getOldColor().getBlue() / (float) 255);
  }

}
