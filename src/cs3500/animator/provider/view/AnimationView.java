package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.animator.provider.controller.CheckBoxListener;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;


/**
 * A class called AnimationView representing one of the ways to showing the animation. This
 * AnimationView class is to showing the animation as animating.
 */
public class AnimationView extends JFrame implements IView {

  private AnimationPanel animationPanel;

  /**
   * Constructor of AnimationView.
   *
   * @param tempo the tempo of the animation
   */
  public AnimationView(int tempo) {
    super();

    this.setTitle("Animation");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel(tempo);
    animationPanel.setPreferredSize(new Dimension(1000, 1000));

    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
    this.makeVisible();
  }

  /**
   * To make the animation visible.
   */
  private void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void outPut(boolean playing) {
    animationPanel.start();
  }

  @Override
  public String getString(boolean loop) {
    throw new UnsupportedOperationException("Cannot convert visual view to string");
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    throw new UnsupportedOperationException("Animation view doesn't need add action listener");
  }

  @Override
  public void addItemListener(CheckBoxListener actionListener) {
    throw new UnsupportedOperationException("Animation view doesn't need add item listener");
  }

  @Override
  public void setOperations(List<IAnimationOperations> operations) {
    animationPanel.setOperations(operations);
  }

  @Override
  public void setShapes(List<IShape> shapes) {
    animationPanel.setShapes(shapes);

  }

  @Override
  public void restartAnimation() {
    throw new UnsupportedOperationException("Animation view doesn't need loop");
  }

  @Override
  public void loopAnimation() {
    throw new UnsupportedOperationException("Animation view doesn't need loop");
  }

  @Override
  public void unLoopAnimation() {
    throw new UnsupportedOperationException("Animation view doesn't need unLoop");
  }

  @Override
  public int getDuration() {
    return -1;
  }

  @Override
  public void changeSpeed(int tempo) {
    throw new UnsupportedOperationException("Animation view doesn't need change speed");
  }


  @Override
  public void selectShapes(IShape shape) {
    throw new UnsupportedOperationException("Animation view doesn't need select shapes");
  }

  @Override
  public void addShapesToCheckBox() {
    throw new UnsupportedOperationException("Animation view doesn't need add shapes to checkBox");
  }

  @Override
  public void setBoxSelected(boolean b) {
    throw new UnsupportedOperationException("Animation view doesn't need set box selected");
  }

  @Override
  public String getFileName() {
    throw new UnsupportedOperationException("Animation view doesn't need get the file name");
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
