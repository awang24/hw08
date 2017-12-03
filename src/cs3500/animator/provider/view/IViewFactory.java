
package cs3500.animator.provider.view;


/**
 * HW7 We deleted the parameter model for the method create because the views don't take a model
 * any more. We also added a new branch for the switch case in this method because we have one more
 * view: HybridView.
 */


import cs3500.animator.provider.view.IView;
import cs3500.animator.provider.view.SVGView;
import cs3500.animator.provider.view.TextualView;


/**
 * This is a factory of IView. The purpose of this class is to create a view.
 */

public class IViewFactory {


/**
   * Based on the string s, generate a type of view.
   *
   * @param s     The string that contains a type of view.
   * @param tempo The animation speed.
   * @return A type of view.
   */

  public static IView create(String s, int tempo) {
    if (s == null) {
      throw new IllegalArgumentException("Found Null Value");
    }
    switch (s) {
      case "text":
        return new TextualView(tempo);
      case "svg":
        return new SVGView(tempo);
      case "visual":
        return new AnimationView(tempo);
      case "interactive":
        return new HybridView(tempo);
      default:
        throw new IllegalArgumentException("Can't Found This View");
    }
  }


}

