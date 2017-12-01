package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.animation.AnimationType;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.animation.ChangeDimension;
import cs3500.animator.model.animation.MoveAnimation;
import cs3500.animator.model.shape.CreateShapeVisitor;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.starter.TweenModelBuilder;

/**
 * This is a class for a simple animation model which represents a model that processes an
 * animation.  It implements the IAnimationModel and its operations.
 */

public class SimpleAnimationModel implements IAnimationModel {

  private List<Shapes> shapes;
  private List<Animations> animations;

  /**
   * Constructs a {@code SimpleAnimationModel} object.
   */
  public SimpleAnimationModel() {
    this.shapes = new ArrayList<Shapes>();
    this.animations = new ArrayList<Animations>();
  }

  private SimpleAnimationModel(SimpleAnimationModelBuilder builder) {
    this.shapes = builder.listShapes;
    this.animations = builder.listAnimations;
  }

  @Override
  public void addShape(Shapes s) {
    this.shapes.add(s);
  }

  @Override
  public void addAnimations(Animations a) {
    AnimationType addType = a.getType();
    Shapes addShape = a.getShape();
    int addStart = a.getStart();
    int size = animations.size();

    for (int i = 0; i < animations.size(); i++) {
      Animations current = animations.get(i);
      AnimationType type = current.getType();
      Shapes shape = current.getShape();
      int start = current.getStart();
      int end = current.getEnd();

      if (addType == type) {
        if (addShape.getName().equals(shape.getName())) {
          if ((addStart >= start) && (addStart <= end)) {
            throw new IllegalArgumentException("Incompatible move for the same shape "
                    + "during overlapping time intervals");
          }
        }
      }
    }

    // Add animation in order of start time
    for (int i = 0; i < size; i++) {
      Animations current = animations.get(i);
      int start = current.getStart();

      if (addStart < start) {
        animations.add(i, a);
      }
    }
    if (size == animations.size()) {
      animations.add(a);
    }
  }

  @Override
  public String getDescription() {
    String state = "Shapes:\n";
    for (int i = 0; i < shapes.size(); i++) {
      state += shapes.get(i).getState() + "\n";
    }

    for (int i = 0; i < animations.size(); i++) {
      state += animations.get(i).getDescription() + "\n";
    }
    return state;
  }

  @Override
  public List<Shapes> getShapes() {
    return shapes;
  }

  @Override
  public List<Animations> getAnimations() {
    return animations;
  }

  @Override
  public int getLastTime() {
    int last = -1;
    for (int i = 0; i < animations.size(); i++) {
      Animations current = animations.get(i);
      int currentEnd = current.getEnd();

      if (currentEnd > last) {
        last = currentEnd;
      }
    }
    return last;
  }

  /**
   * Represents a simple animation builder that will add shapes and animations to the model.
   */
  public static final class SimpleAnimationModelBuilder
          implements TweenModelBuilder<IAnimationModel> {

    private List<Animations> listAnimations;
    private List<Shapes> listShapes;


    /**
     * Constructs a {@code SimpleAnimationBuilder} object.
     **/
    public SimpleAnimationModelBuilder() {
      this.listAnimations = new ArrayList<Animations>();
      this.listShapes = new ArrayList<Shapes>();
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addOval(
            String name,
            float cx, float cy,
            float xRadius, float yRadius,
            float red, float green, float blue,
            int startOfLife, int endOfLife) {
      Posn p = new Posn(cx, cy);
      Color c = new Color(red, green, blue);
      Shapes shape = new Oval(name, startOfLife, endOfLife, p, c, xRadius, yRadius);
      listShapes.add(shape);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addRectangle(
            String name,
            float lx, float ly,
            float width, float height,
            float red, float green, float blue,
            int startOfLife, int endOfLife) {
      Posn p = new Posn(lx, ly);
      Color c = new Color(red, green, blue);
      Shapes shape = new RectangleShape(name, startOfLife, endOfLife, p, c, width, height);
      listShapes.add(shape);
      return this;
    }

    /**
     * Add given animation in order in the list of animations if animation is valid.
     *
     * @param a animation to add to list of animations
     */
    private void addAnimations(Animations a) {
      AnimationType addType = a.getType();
      Shapes addShape = a.getShape();
      int addStart = a.getStart();

      for (int i = 0; i < listAnimations.size(); i++) {
        Animations current = listAnimations.get(i);
        AnimationType type = current.getType();
        Shapes shape = current.getShape();
        int start = current.getStart();
        int end = current.getEnd();

        if (addType == type) {
          if (addShape.getName().equals(shape.getName())) {
            if ((addStart >= start) && (addStart <= end)) {
              throw new IllegalArgumentException("Incompatible move for the same shape "
                      + "during overlapping time intervals");
            }
          }
        }
      }
      listAnimations.add(a);
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addMove(
            String name,
            float moveFromX, float moveFromY, float moveToX, float moveToY,
            int startTime, int endTime) {
      Posn origin = new Posn(moveFromX, moveFromY);
      Posn dest = new Posn(moveToX, moveToY);
      Shapes s = null;
      //List<Shapes> loshapes = model.getShapes();
      for (int i = 0; i < listShapes.size(); i++) {
        Shapes current = listShapes.get(i);
        if (current.getName().equals(name)) {
          s = current.accept(new CreateShapeVisitor());
          s.setPosn(origin);
        }
      }

      try {
        Animations animation = new MoveAnimation(s, startTime, endTime, origin, dest);
        this.addAnimations(animation);
      } catch (Exception e) {
        // do nothing
      }
      return this;
    }


    @Override
    public TweenModelBuilder<IAnimationModel> addColorChange(
            String name,
            float oldR, float oldG, float oldB, float newR, float newG, float newB,
            int startTime, int endTime) {
      Color oldColor = new Color(oldR, oldG, oldB);
      Color newColor = new Color(newR, newG, newB);

      Shapes s = null;
      for (int i = 0; i < listShapes.size(); i++) {
        Shapes current = listShapes.get(i);
        if (current.getName().equals(name)) {
          s = current.accept(new CreateShapeVisitor());
          s.setColor(oldColor);
        }
      }
      try {
        Animations animation = new ChangeColor(s, startTime, endTime, oldColor, newColor);
        this.addAnimations(animation);
      } catch (Exception e) {
        // do nothing
      }
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(String name, float fromSx, float
            fromSy, float toSx, float toSy, int startTime, int endTime) {

      Shapes s = null;
      for (int i = 0; i < listShapes.size(); i++) {
        Shapes current = listShapes.get(i);
        if (current.getName().equals(name)) {
          s = current.accept(new CreateShapeVisitor());
          s.setD1(fromSx);
          s.setD2(fromSy);
        }
      }
      try {
        Animations animation = new ChangeDimension(s, startTime, endTime, fromSx,
                fromSy, toSx, toSy);
        this.addAnimations(animation);
      } catch (Exception e) {
        // do nothing
      }
      return this;
    }

    @Override
    public IAnimationModel build() {
      return new SimpleAnimationModel(this);
    }
  }

}
