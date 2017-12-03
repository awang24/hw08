package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.animation.AnimationType;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animationAdapter.ChangeColorAdapter;
import cs3500.animator.model.animationAdapter.ChangeDimensionAdapter;
import cs3500.animator.model.animationAdapter.MoveAnimationAdapter;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.model.shapeAdapter.OvalAdapter;
import cs3500.animator.model.shapeAdapter.RectangleAdapter;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.view.IView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualAnimationView;

/**
 * Class for static methods that are used for testing and used among different classes.
 */
public class Utils {

  /**
   * Checks to see if two doubles are the same.
   *
   * @param x1 First number to compare to
   * @param x2 Second number to compare to
   * @return true if doubles are the same, false if they are not
   */
  public static boolean checkDoubles(double x1, double x2) {
    return Math.abs(x1 - x2) <= 0.0000001;
  }

  /**
   * Checks to see if the number is negative.
   *
   * @param x Number to check if negative
   * @return true if number is negative, false if it isn't
   */
  public static boolean isNegative(double x) {
    return x < 0.0;
  }

  /**
   * Returns the float representation of a field of a color. Number ranges from 0.0 to 1.0.
   *
   * @return the float representation of a color field
   */

  public static float getColorFloat(int x) {
    return (float) x / (float) 255;
  }

  /**
   * Returns the string representation of a color in float representation.
   *
   * @param c the color
   * @return string representation of the color
   */
  public static String getColorString(Color c) {
    return "(" + Utils.getColorFloat(c.getRed()) + "," + Utils.getColorFloat(c.getGreen()) + ","
            + Utils.getColorFloat(c.getBlue()) + ")";
  }

  /**
   * Returns the string representation of a color.
   *
   * @param c the color
   * @return string representation of the color.
   */
  public static String getNotFloatColorString(Color c) {
    return "(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
  }

  /**
   * Returns the posn string representation.
   *
   * @param p the posn
   * @return the string representation of the posn
   */
  public static String getPosnString(Posn p) {
    return "(" + p.getX() + ", " + p.getY() + ")";
  }

  /**
   * Returns the appropriate view according to the view string taken in.
   *
   * @param view  string representation of what type of view to make
   * @param model model for view to work on
   * @param tempo tempo at which to set the view to
   * @return the approrpriate view
   * @throws IllegalArgumentException if the String view is invalid
   */
  public static IView createView(String view, IAnimationModel model, double tempo) {
    if (view.equals("text")) {
      return new TextualView(tempo, model.getShapes(), model.getAnimations());
    } else if (view.equals("visual")) {
      return new VisualAnimationView(tempo, model.getShapes());
    } else if (view.equals("svg")) {
      return new SVGView(tempo, model.getShapes(), model.getAnimations());
    } else if (view.equals("interactive") || view.equals("provider")) {
      return new InteractiveView(tempo, model.getShapes(), model.getAnimations(),
              model.getLastTime());
    } /*else if (view.equals("provider")) {
      return new ProviderInteractiveViewToIViewAdapt((int) tempo, model.getShapes(),
              model.getAnimations(), model.getLastTime());
    } */else {
      throw new IllegalArgumentException("Invalid view type");
    }
  }

  /**
   * Returns the adapter that converts a shape to an IShape.
   *
   * @param s shape to convert to an IShape
   * @return the appropriate IShape
   */
  public static IShape createAdapterShape(Shapes s) {
    if (s.getShapeType().equals(ShapeType.OVAL)) {
      return new OvalAdapter(s);
    } else if (s.getShapeType().equals(ShapeType.RECTANGLE)) {
      return new RectangleAdapter(s);
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  /**
   * Returns the adapter that converts an Animation to an IAnimationOperations.
   *
   * @param a animation to convert to an IAnimaitonOperations
   * @return the appropriate IAnimationOperations
   */
  public static IAnimationOperations createAdapterAnimation(Animations a) {
    if (a.getType().equals(AnimationType.CHANGECOLOR)) {
      return new ChangeColorAdapter(a);
    } else if (a.getType().equals(AnimationType.MOVE)) {
      return new MoveAnimationAdapter(a);
    } else if (a.getType().equals(AnimationType.CHANGEDIMENSION))  {
      return new ChangeDimensionAdapter(a);
    } else {
      throw new IllegalArgumentException("Invalid animation");
    }
  }

  /**
   * Converts the list of shapes to a list of IShape and sets the shapes in the view to this new
   * list.
   *
   * @param shapes list of shapes to convert
   * @return the converted list of IShapes
   */
  public static List<IShape> convertShapes(List<Shapes> shapes) {
    List<IShape> newShapes = new ArrayList<IShape>();
    for (int i = 0; i < shapes.size(); i++) {
      newShapes.add(Utils.createAdapterShape(shapes.get(i)));
    }
    return newShapes;
  }

  /**
   * Converts the list of animations to a list of IAnimationOperations and sets the operations in
   * the view to this new list.
   *
   * @param animations list of Animations to convert
   * @return the converted list of IAnimationOperations
   */
  public static List<IAnimationOperations> convertAnimations(List<Animations> animations) {
    List<IAnimationOperations> newAnimations = new ArrayList<IAnimationOperations>();
    for (int i = 0; i < animations.size(); i++) {
      newAnimations.add(Utils.createAdapterAnimation(animations.get(i)));
    }
    return newAnimations;
  }
}
