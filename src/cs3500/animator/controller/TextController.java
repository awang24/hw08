package cs3500.animator.controller;

import java.util.List;

import javax.swing.Timer;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.view.IView;

/**
 * Represents the controller for the textual view and implements IAnimationController and its
 * associated operations.
 */
public class TextController implements IAnimationController {

  private IAnimationModel model;
  private IView view;
  private String filename;

  /**
   * Constructs a {@code TextController} object.
   *
   * @param model    the model that the controller will be using
   * @param view     the view that the controller will be using to display
   * @param filename the output file for the controller to write out to
   */
  public TextController(IAnimationModel model, IView view, String filename) {
    this.model = model;
    this.view = view;
    this.filename = filename;
  }

  @Override
  public void start() {
    List<Animations> animations = model.getAnimations();
    List<Shapes> shapes = model.getShapes();

    for (int i = 0; i < animations.size(); i++) {
      Animations a = animations.get(i);
      Shapes aShape = a.getShape();
      String aName = aShape.getName();

      for (int j = 0; j < shapes.size(); j++) {
        Shapes current = shapes.get(j);
        if (aName.equals(current.getName())) {
          a.changeField(current);
        }
      }
    }
    this.view.writeOut(filename);
  }

  @Override
  public Appendable getLog() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public double getTempo() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
