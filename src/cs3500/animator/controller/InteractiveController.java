package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JCheckBox;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.CreateShapeVisitor;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.view.IView;


/**
 * Represents the controller for the interactive view that combines the functionality of the SVG
 * view and Visual view.
 */
public class InteractiveController implements IAnimationController, ActionListener {

  private IAnimationModel model;
  private IView view;
  private double tempo;
  private boolean isLoop;
  private String filename;
  private Timer timer;
  private List<Shapes> newListShapes;
  private int lastTime;
  private double unitsElapsed;
  private Appendable log;

  /**
   * Create an instance of an InteractiveController.
   *
   * @param model    The model that the controller uses
   * @param view     The view that the controller uses
   * @param tempo    The speed at which the animation is played
   * @param filename The filename that the controller writes out to
   */
  public InteractiveController(IAnimationModel model, IView view, double tempo, String filename) {
    this.model = model;
    this.view = view;
    this.tempo = tempo;
    this.isLoop = false;
    this.filename = filename;
    this.unitsElapsed = 0;
    this.lastTime = model.getLastTime();
    this.log = new StringBuffer();
  }

  @Override
  public void start() {

    this.view.setButtonListener(this);

    HandlerClass handler = new HandlerClass();

    for (int i = 0; i < view.getCheckBoxList().size(); i++) {
      JCheckBox current = view.getCheckBoxList().get(i);
      current.addItemListener(handler);
    }

    this.view.makeVisible();

    this.createNewListOfShapes();

    this.timer = new Timer(0, timerListener);


    List<Animations> animations = model.getAnimations();

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

    this.view.writeOut(filename);

  }

  /**
   * Sets the new list shapes to be the model's original list of shapes.
   */
  private void createNewListOfShapes() {
    List<Shapes> shapes = model.getShapes();

    newListShapes = new ArrayList<Shapes>();

    for (int i = 0; i < shapes.size(); i++) {
      Shapes newShape = shapes.get(i).accept(new CreateShapeVisitor());
      newListShapes.add(newShape);
    }
  }

  ActionListener timerListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

      unitsElapsed += tempo / 1000;

      if (isLoop && (lastTime - unitsElapsed < 0.000001)) {
        unitsElapsed = 0;

        timer.restart();

        List<Shapes> shapes = model.getShapes();

        newListShapes = new ArrayList<Shapes>();

        for (int i = 0; i < shapes.size(); i++) {
          Shapes newShape = shapes.get(i).accept(new CreateShapeVisitor());
          newListShapes.add(newShape);
        }

      } else if (lastTime - unitsElapsed < 0.000001) {
        timer.stop();
      }

      List<Animations> animations = model.getAnimations();

      for (int i = 0; i < animations.size(); i++) {
        Animations current = animations.get(i);
        int start = current.getStart();
        int end = current.getEnd();

        if (start <= unitsElapsed && end >= unitsElapsed) {
          current.animate(unitsElapsed);
          view.setShapes(newListShapes);
          view.refresh();
        }
      }
      view.makeVisible();
    }
  };

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "PLAY":
        this.appendLog("Play Button hit. \n");
        this.timer.start();
        this.enableBoxes(false);
        //Offer SVG functionality
        System.out.println(log.toString());
        break;
      case "PAUSE":
        this.appendLog("Pause Button hit. \n");
        this.timer.stop();
        this.enableBoxes(true);
        break;
      case "RESTART":
        this.appendLog("Restart Button hit. \n");
        this.timer.restart();
        this.enableBoxes(false);
        this.createNewListOfShapes();
        unitsElapsed = 0;
        break;
      case "LOOP":
        this.appendLog("Loop Button hit. \n");
        isLoop = !isLoop;
        view.setIsLoop(isLoop);
        this.enableBoxes(true);
        break;
      case "INCREASE SPEED":
        this.appendLog("Increase Speed Button hit. \n");
        this.enableBoxes(false);
        tempo += 5;
        unitsElapsed -= (tempo) / 1000;
        break;
      case "DECREASE SPEED":
        this.appendLog("Decrease Speed Button hit. \n");
        this.enableBoxes(false);
        if (tempo <= 0) {
          tempo = 0;
        } else {
          tempo -= 5;
        }
        unitsElapsed += (tempo) / 1000;
        break;
      case "SET FILE":
        this.appendLog("Set File Button hit. \n");
        this.enableBoxes(true);
        filename = view.getFilenameCommand();
        break;
      case "EXPORT":
        this.appendLog("Export Button hit. \n");
        processFilenameCommand(filename);
        break;
      default:
        // do nothing
    }
  }

  /**
   * Disables or enables all the check boxes.
   *
   * @param enable boolean for if you want to disable the check boxes
   */
  private void enableBoxes(boolean enable) {
    for (int i = 0; i < view.getCheckBoxList().size(); i++) {
      JCheckBox current = view.getCheckBoxList().get(i);
      current.setEnabled(enable);
    }
  }

  /**
   * A class to handle operations specific to a checkbox's item listener.
   */
  private class HandlerClass implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      for (int i = 0; i < view.getCheckBoxList().size(); i++) {
        if (!view.getCheckBoxList().get(i).isSelected()) {
          changeShapeRender(
                  view.getCheckBoxList().get(i).getAccessibleContext().getAccessibleName(),
                  false);
        } else {
          changeShapeRender(
                  view.getCheckBoxList().get(i).getAccessibleContext().getAccessibleName(),
                  true);
        }
      }
    }

    /**
     * Sets the appropriate shape's render field to false.
     *
     * @param shapeName the appropriate shape's render boolean you want to change
     */
    private void changeShapeRender(String shapeName, boolean render) {

      for (int i = 0; i < model.getShapes().size(); i++) {
        if (shapeName.equals(model.getShapes().get(i).getName())) {
          view.getShapes().get(i).setRender(render);
          model.getShapes().get(i).setRender(render);
          newListShapes.get(i).setRender(render);
        }
      }
    }
  }

  /**
   * Sets the file name to write out to.
   *
   * @param command The new filename that the user determines for the animation
   */
  private void processFilenameCommand(String command) {
    try {
      this.view.writeOut(command);
    } catch (Exception e) {
      view.showErrorMessage("Invalid filename.");
    }
  }

  /**
   * Put an entry into the log.
   *
   * @param entry The string that is being written to the log
   */
  private void appendLog(String entry) {
    try {
      this.log.append(entry);
    } catch (IOException e) {
      //do nothing
    }
  }

  @Override
  public Appendable getLog() {
    return this.log;
  }

  @Override
  public Timer getTimer() {
    return this.timer;
  }


}
