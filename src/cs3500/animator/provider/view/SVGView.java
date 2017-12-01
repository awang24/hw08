package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.provider.controller.CheckBoxListener;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.ShapeType;

/**
 * Represents a view in svg way. After producing a .svg file, It can animate.
 */
public class SVGView implements IView {
  private List<IShape> shapes;
  private List<IAnimationOperations> operations;
  private int tempo;
  private Appendable ap;


  /**
   * Constructor of SVGView.
   *
   * @param tempo The tempo of the animation.
   */
  public SVGView(int tempo) {
    this.shapes = new ArrayList<>();
    this.operations = new ArrayList<>();
    this.tempo = tempo;
    this.ap = new StringBuffer();
  }

  /**
   * Constructor of SVGView. This is created for hybrid view because the hybrid view need to export
   * a file in svg version.
   *
   * @param tempo      The tempo of the animation.
   * @param shapes     The shapes in the animation.
   * @param operations The operation that is applying on the shapes.
   */
  public SVGView(int tempo, List<IShape> shapes, List<IAnimationOperations> operations) {
    this.tempo = tempo;
    this.shapes = shapes;
    this.operations = operations;
    this.ap = new StringBuffer();
  }


  /**
   * Constructor of SVGView. This constructor is for testing.
   *
   * @param shapes     The shapes that is going to represent.
   * @param operations The operations that is used to apply on the shapes.
   * @param tempo      The animation speed.
   * @param ap         The appendable that is used to show the animation.
   */
  public SVGView(List<IShape> shapes, List<IAnimationOperations> operations, int tempo,
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
  public void outPut(boolean playing) {
    try {
      ap.append(getString(false));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getString(boolean loop) {
    double duration = (1000 * this.getDuration()) / (double) tempo;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "    xmlns=\"http://www.w3.org/2000/svg\">\n\n");


    if (loop) {
      stringBuilder.append("<rect>\n <animate id=\"base\" begin=\"0;base.end\" dur=\"" + duration +
              "ms\"" + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/> \n</rect>\n\n");
    }


    for (int i = 0; i < shapes.size(); i++) {
      stringBuilder.append("\n" + shapes.get(i).svgState(tempo, loop) + "\n");
      for (int j = 0; j < operations.size(); j++) {
        if (shapes.get(i).getName().equals(operations.get(j).getShape().getName())) {
          stringBuilder.append(operations.get(j).svgState(tempo, loop));

        }
      }
      stringBuilder.append(shapes.get(i).svgReset(loop));

      if (shapes.get(i).getShapeType() == ShapeType.rectangle) {
        stringBuilder.append("\n</rect>\n");
      } else {
        stringBuilder.append("\n</ellipse>\n");
      }
    }
    stringBuilder.append("\n\n</svg>");
    return stringBuilder.toString();

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
  public int getDuration() {
    int duration = 0;
    for (IShape s : shapes) {
      if (s.getDisappearTime() > duration) {
        duration = s.getDisappearTime();
      }
    }
    return duration;

  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    throw new UnsupportedOperationException("svg view doesn't need add action listener");
  }

  @Override
  public void addItemListener(CheckBoxListener actionListener) {
    throw new UnsupportedOperationException("svg view doesn't need add item listener");
  }

  @Override
  public void restartAnimation() {
    throw new UnsupportedOperationException("svg view doesn't need restart");
  }

  @Override
  public void loopAnimation() {
    throw new UnsupportedOperationException("svg view doesn't need loop");
  }

  @Override
  public void unLoopAnimation() {
    throw new UnsupportedOperationException("svg view doesn't need un loop");
  }


  @Override
  public void changeSpeed(int tempo) {
    throw new UnsupportedOperationException("svg view doesn't need change the speed");
  }


  @Override
  public void selectShapes(IShape shape) {
    throw new UnsupportedOperationException("svg view doesn't need select shapes");
  }

  @Override
  public void addShapesToCheckBox() {
    throw new UnsupportedOperationException("svg view doesn't need add shapes to check box");
  }

  @Override
  public void setBoxSelected(boolean b) {
    throw new UnsupportedOperationException("svg view doesn't need get selected box");
  }

  @Override
  public String getFileName() {
    throw new UnsupportedOperationException("svg view doesn't need get a file name");
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
