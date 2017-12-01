package cs3500.animator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.CreateShapeVisitor;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.view.IView;

/**
 * Represents the controller for the visual view and implements IAnimationController and its
 * associated operations.
 */
public class VisualController implements IAnimationController {

  private IAnimationModel model;
  private IView view;
  private double tempo;
  private boolean isAnimationStarted;

  /**
   * Constructs a {@code TextController} object.
   *
   * @param tempo the tempo that the animation will be animated
   * @param model the model that the controller will be using
   * @param view  the view that the controller will be using to display
   */
  public VisualController(IAnimationModel model, IView view, double tempo) {
    this.model = model;
    this.view = view;
    this.tempo = tempo;
    this.isAnimationStarted = false;
  }

  @Override
  public void start() {
    this.isAnimationStarted = true;
    long startTime = System.currentTimeMillis();
    long timeElapsed = 0;

    double secondsElapsed = 0;
    double unitsElapsed = 0;
    List<Animations> animations = model.getAnimations();
    List<Shapes> shapes = model.getShapes();

    List<Shapes> newListShapes = new ArrayList<Shapes>();

    for (int i = 0; i < shapes.size(); i++) {
      Shapes newShape = shapes.get(i).accept(new CreateShapeVisitor());
      newListShapes.add(newShape);
    }

    while (this.isAnimationStarted) {
      timeElapsed = System.currentTimeMillis() - startTime;
      secondsElapsed = timeElapsed / 1000.0;
      unitsElapsed = secondsElapsed * tempo;

      for (int i = 0; i < animations.size(); i++) {
        Animations currentAnimation = animations.get(i);
        Shapes animationShape = currentAnimation.getShape();
        for (int j = 0; j < newListShapes.size(); j++) {
          Shapes currentShape = newListShapes.get(j);
          if (currentShape.getName().equals(animationShape.getName())) {
            currentAnimation.setShape(currentShape);
          }
        }
      }

      for (int i = 0; i < animations.size(); i++) {
        Animations current = animations.get(i);
        int start = current.getStart();
        int end = current.getEnd();

        if (start <= unitsElapsed && end >= unitsElapsed) {
          current.animate(unitsElapsed);
          this.view.setShapes(newListShapes);
          this.view.refresh();
        }
      }
      this.view.makeVisible();
    }
  }

  @Override
  public Appendable getLog() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
