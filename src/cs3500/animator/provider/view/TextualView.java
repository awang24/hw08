package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.provider.controller.CheckBoxListener;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;

/**
 * Represents a view in textual way.
 */
public class TextualView implements IView {
  private List<IShape> shapes;
  private List<IAnimationOperations> operations;
  private int tempo;
  private Appendable ap;


  /**
   * Constructor of TextualView.
   * @param tempo  The tempo of the animation.
   */
  public TextualView(int tempo) {
    this.tempo = tempo;
    this.shapes = new ArrayList<>();
    this.operations = new ArrayList<>();
    this.ap = new StringBuffer();
  }


  /**
   * Constructor of TextualView. This constructor is for testing.
   * @param shapes     The shapes that is used to represent.
   * @param operations The operations that is used to apply on the shapes.
   * @param tempo      The animation speed.
   * @param ap         The appendable that is used to show the animation.
   */
  public TextualView(List<IShape> shapes, List<IAnimationOperations> operations, int tempo,
                     Appendable ap) {
    this.tempo = tempo;
    if (ap == null) {
      throw new IllegalArgumentException("Null Value.");
    } else {
      this.shapes = shapes;
      this.operations = operations;
      this.ap = ap;
    }
  }


  @Override
  public String getString(boolean loop) {
    return getAnimationState(tempo, shapes, operations);
  }


  @Override
  public void addActionListener(ActionListener actionListener) {
    throw new UnsupportedOperationException("Textual view doesn't need add action listener");
  }

  @Override
  public void addItemListener(CheckBoxListener actionListener) {
    throw new UnsupportedOperationException("Textual view doesn't need add check box listener");
  }


  @Override
  public void outPut(boolean playing) {
    try {
      ap.append(getString(true));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * get the textual state of the shapes and operations.
   * @param tempo         The tempo of the animation.
   * @param loShape       The list of shapes that is going to show.
   * @param loOperations  The list of operations that is going to show.
   * @return a string containing the view in textual way.
   */
  private String getAnimationState(int tempo, List<IShape> loShape,
                                   List<IAnimationOperations> loOperations) {

    if (loShape.size() == 0) {
      return "";
    }

    String state = "Shapes:\n";
    for (int i = 0; i < loShape.size(); i++) {
      if (i == loShape.size() - 1) {
        state = state + loShape.get(i).toString(tempo) + "\n";
      } else {
        state = state + loShape.get(i).toString(tempo) + "\n\n";
      }
    }

    if (loOperations.size() != 0) {
      state = state + "\n";
      for (IAnimationOperations operation : loOperations) {
        state = state + operation.toString(tempo) + "\n";
      }
    }
    return state;
  }

  @Override
  public void setOperations(List<IAnimationOperations> operations) {
    this.operations = operations;
  }

  @Override
  public void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void restartAnimation() {
    throw new UnsupportedOperationException("Textual view doesn't need restart");
  }

  @Override
  public void loopAnimation() {
    throw new UnsupportedOperationException("Textual view doesn't need loop");
  }

  @Override
  public void unLoopAnimation() {
    throw new UnsupportedOperationException("Textual view doesn't need unLoop");
  }

  @Override
  public int getDuration() {
    return -1;
  }

  @Override
  public void changeSpeed(int tempo) {
    throw new UnsupportedOperationException("Textual view doesn't need change speed");
  }


  @Override
  public void selectShapes(IShape shape) {
    throw new UnsupportedOperationException("Textual view doesn't need select shapes");
  }

  @Override
  public void addShapesToCheckBox() {
    throw new UnsupportedOperationException("Textual view doesn't add shapes to check box");
  }

  @Override
  public void setBoxSelected(boolean b) {
    throw new UnsupportedOperationException("Textual view doesn't need select box");
  }

  @Override
  public String getFileName() {
    throw new UnsupportedOperationException("Textual view doesn't need get a file name");
  }

  @Override
  public boolean getLoop() {
    return false;
  }

  @Override
  public JCheckBox[] getCheckBoxArray() {
    throw new UnsupportedOperationException("Animation view doesn't need get the check box array");
  }

}
