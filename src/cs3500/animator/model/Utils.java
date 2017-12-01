package cs3500.animator.model;

import java.awt.Color;

import cs3500.animator.model.shape.Posn;
import cs3500.animator.view.IView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.ProviderInteractiveViewToIViewAdapt;
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
    } else if (view.equals("interactive")) {
      return new InteractiveView(tempo, model.getShapes(), model.getAnimations(),
              model.getLastTime());
    }  else if (view.equals("provider")) {
      return new ProviderInteractiveViewToIViewAdapt((int)tempo, model.getShapes(),
              model.getAnimations(), model.getLastTime());
    } else {
      throw new IllegalArgumentException("Invalid view type");
    }
  }
}
