package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


import javax.swing.JCheckBox;

import cs3500.animator.model.Utils;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * A class that represents a view in textual form.
 */
public class TextualView implements IView {

  //private IAnimationModel model;
  private double tempo;
  private List<Shapes> shapes;
  private List<Animations> animations;

  /**
   * Constructs a {@code TextualView} object.
   *
   * @param tempo represents the speed at which the animation occurs
   * @param shapes represents the list of shapes of the model
   * @param animations represents the list of animations of the model
   */
  public TextualView(double tempo, List<Shapes> shapes, List<Animations> animations) {
    this.tempo = tempo;
    this.shapes = shapes;
    this.animations = animations;
  }

  @Override
  public double getTempo() {
    return tempo;
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void setShapes(List<Shapes> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void setButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public String getFilenameCommand() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public List<JCheckBox> getCheckBoxList() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public List<Shapes> getShapes() {
    return this.shapes;
  }

  @Override
  public List<Animations> getAnimations() {
    return this.animations;
  }

  @Override
  public void setIsLoop(boolean loop) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public String getDescription() {

    String state = "";

    if (shapes.size() != 0) {
      state += "Shapes:\n";
    }

    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      double newAppearTime = (double) currentShape.getAppear() / this.tempo;
      double newDisappearTime = (double) currentShape.getDisappear() / this.tempo;
      String current = "";
      current += "Name: " + currentShape.getName() + "\n" + "Type: "
              + currentShape.getShapeType().toString() + "\n"
              + currentShape.location() + ", "
              + currentShape.getDimensions() + ", ColorAdapter: "
              + Utils.getColorString(currentShape.getColor()) + "\n"
              + "Appears at t=" + newAppearTime + "s\n" + "Disappears at t="
              + newDisappearTime + "s\n";
      state += current + "\n";
    }


    for (int i = 0; i < animations.size(); i++) {
      Animations currentAnimation = animations.get(i);
      double newAppearTime = (double) currentAnimation.getStart() / this.tempo;
      double newDisappearTime = (double) currentAnimation.getEnd() / this.tempo;

      String current = "";
      current += "shape " + currentAnimation.getShape().getName() + " "
              + currentAnimation.getMovement() + " from "
              + currentAnimation.getBeginState() + " to " + currentAnimation.getEndState()
              + " from t=" + newAppearTime
              + "s to t=" + newDisappearTime + "s";
      state += current + "\n";
    }
    return state;
  }

  @Override
  public void writeOut(String fileName) {
    String description = this.getDescription();
    try {
      BufferedWriter output = null;
      if (fileName.equals("System.out")) {
        output = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File file = new File(fileName);
        output = new BufferedWriter(new FileWriter(file));
      }
      output.write(description);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
