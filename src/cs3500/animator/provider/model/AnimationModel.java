/*
package cs3500.animator.provider.model;


import java.util.ArrayList;

import java.util.Collections;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.starter.TweenModelBuilder;


*/
/**
 * HW6 We changed the toString() to toSring(int tempo) because the view takes a tempo to make the
 * speed change. We added a builder in this class because since we read the file, we want to
 * generate a new model. We also added a method called passToView for passing the current shapes to
 * the view. For writing this method successfully, we wrote several helper methods such as
 * getCurrentLoShape, getCurrentLoOperations, to get the current shapes and current operations. We
 * also added a method calld SVGState for SVGView. <p></p> HW7 We removed getCurrentShapes,
 * getCurrentOperations methods because the views don't take a model any more, every views has two
 * fields: list of shapes and list of operations currently. These three methods are not necessary.
 *//*


*/
/**
 * This is a AnimationModel Class which implements AnimationModel Interface. This class plays the
 * Model Role in MVC-Structured program.
 *//*

public final class AnimationModel implements cs3500.animator.provider.model.IAnimationModel {
  private ArrayList<IShape> loShape;
  private ArrayList<IAnimationOperations> loOperations;


  */
/**
   * Constructor of AnimationModel. Initially, the fields are both an empty list.
   *//*

  public AnimationModel() {
    this.loShape = new ArrayList<IShape>();
    this.loOperations = new ArrayList<IAnimationOperations>();
  }


  */
/**
   * Constructor of AnimationModel. This is for copying the data from the parameters in case others
   * can access this field and change it.
   *
   * @param loShape      The list of shapes that is going to be assigned in.
   * @param loOperations The list of operations that is going to be assigned in.
   *//*

  public AnimationModel(ArrayList<IShape> loShape, ArrayList<IAnimationOperations> loOperations) {
    ArrayList<IShape> loshape = new ArrayList<>();
    ArrayList<IAnimationOperations> looperation = new ArrayList<>();
    for (IShape s : loShape) {
      loshape.add(s);
    }
    for (IAnimationOperations o : looperation) {
      looperation.add(o);
    }

    this.loShape = loShape;
    this.loOperations = loOperations;
  }


  @Override
  public void addShape(IShape newShape) {
    this.loShape.add(newShape);
  }

  @Override
  public void addCommand(IAnimationOperations command) {
    if (command.validOperation(command)) {
      if (!overlapCommand(command)) {
        this.loOperations.add(command);
        Collections.sort(loOperations);
      } else {
        throw new IllegalArgumentException("Invalid Operation.");
      }
    } else {
      throw new IllegalArgumentException("Invalid Operation.");
    }
  }

  */
/**
   * Checks if two operations are overlap.
   *
   * @param command The compared operation.
   * @return true if two operations are overlap.
   *//*

  protected boolean overlapCommand(IAnimationOperations command) {
    boolean b = false;
    for (IAnimationOperations operation : loOperations) {
      if (operation.checkOverlapCommand(command)) {
        return true;
      }
    }
    return b;
  }


  @Override
  public ArrayList<IShape> getLoShape() {
    ArrayList<IShape> temp = new ArrayList<IShape>();
    for (IShape s : loShape) {
      if (s.getShapeType() == ShapeType.rectangle) {
        temp.add(new Rectangle((Rectangle) s));
      } else {
        temp.add(new Oval((Oval) s));
      }
    }
    return loShape;
  }

  @Override
  public ArrayList<IAnimationOperations> getLoOperations() {
    ArrayList<IAnimationOperations> temp = new ArrayList<IAnimationOperations>();
    for (IAnimationOperations operation : loOperations) {
      if (operation.getType() == OperationType.move) {
        temp.add(new MoveOperation((MoveOperation) operation));
      } else if (operation.getType() == OperationType.changeColor) {
        temp.add(new ChangeColorOperation((ChangeColorOperation) operation));
      } else {
        temp.add(new ScaleOperation((ScaleOperation) operation));
      }
    }
    return loOperations;
  }


  */
/**
   * a final class called Builder. This class implements TweenModelBuilder for adding shapes and
   * operations from the file that is read to the model.
   *//*

  public static final class Builder implements TweenModelBuilder<cs3500.animator.model.IAnimationModel> {
    private ArrayList<IShape> loShape;
    private ArrayList<IAnimationOperations> loOperations;

    */
/**
     * Constructor of Builder. Initially, the fields are both an empty list.
     *//*

    public Builder() {
      this.loShape = new ArrayList<>();
      this.loOperations = new ArrayList<>();

    }

    @Override
    public TweenModelBuilder<cs3500.animator.model.IAnimationModel> addOval(String name, float cx, float cy,
                                                                            float xRadius, float yRadius, float red,
                                                                            float green, float blue, int startOfLife,
                                                                            int endOfLife) {
      Oval newOval = new Oval(name, ShapeType.oval, new Posn(cx, cy), new Color(red, green, blue),
              xRadius, yRadius, startOfLife, endOfLife);
      loShape.add(newOval);

      return this;
    }

    @Override
    public TweenModelBuilder<cs3500.animator.model.IAnimationModel> addRectangle(String name, float lx, float ly,
                                                                                 float width, float height, float red,
                                                                                 float green, float blue, int startOfLife,
                                                                                 int endOfLife) {
      Rectangle newRec = new Rectangle(name, ShapeType.rectangle, new Color(red, green, blue),
              new Posn(lx, ly), width, height, startOfLife, endOfLife);
      loShape.add(newRec);
      return this;
    }

    @Override
    public TweenModelBuilder<cs3500.animator.model.IAnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                                            float moveToX, float moveToY, int startTime,
                                                                            int endTime) {
      for (IShape s : loShape) {
        if (s.getName().equals(name)) {
          MoveOperation newOperation = new MoveOperation(OperationType.move, s,
                  new Posn(moveFromX, moveFromY),
                  new Posn(moveToX, moveToY), startTime, endTime);
          loOperations.add(newOperation);
        }
      }

      return this;
    }

    @Override
    public TweenModelBuilder<cs3500.animator.model.IAnimationModel> addColorChange(String name, float oldR, float oldG,
                                                                                   float oldB, float newR,
                                                                                   float newG, float newB, int startTime,
                                                                                   int endTime) {
      for (IShape s : loShape) {
        if (s.getName().equals(name)) {
          ChangeColorOperation newOperation = new ChangeColorOperation(OperationType.changeColor, s,
                  new Color(oldR, oldG, oldB), new Color(newR, newG, newB), startTime, endTime);
          loOperations.add(newOperation);
        }
      }

      return this;
    }

    @Override
    public TweenModelBuilder<cs3500.animator.model.IAnimationModel> addScaleToChange(String name, float fromSx,
                                                                                     float fromSy, float toSx, float toSy,
                                                                                     int startTime, int endTime) {

      for (IShape s : loShape) {
        if (s.getName().equals(name)) {
          ScaleOperation newOperation = new ScaleOperation(OperationType.scale, s,
                  fromSx, fromSy, toSx, toSy, startTime, endTime);
          loOperations.add(newOperation);
        }
      }


      return this;
    }

    @Override
    public IAnimationModel build() {
      return new AnimationModel(loShape, loOperations);
    }
  }

}
*/
