package cs3500.animator.view;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.Dimension;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * Represents the view for a visual animation.
 */
public class VisualAnimationView extends JFrame implements IView {

  private AnimationPanel animatePanel;
  private List<Shapes> shapes;

  /**
   * Constructs a {@code VisualAnimationView} object.
   *
   * @param speed represents the speed at which the animation occurs
   */
  public VisualAnimationView(double speed, List<Shapes> shapes) {
    super();

    this.shapes = shapes;
    this.setTitle("Simple Animation");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animatePanel = new AnimationPanel();
    animatePanel.setPreferredSize(new Dimension(700, 700));

    animatePanel.setShapes(shapes);

    JScrollPane scrollPane = new JScrollPane(animatePanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(50, 30, 300, 50);

    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public String getDescription() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void writeOut(String fileName) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public double getTempo() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setShapes(List<Shapes> shapes) {
    this.shapes = shapes;
    animatePanel.setShapes(shapes);
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
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void setIsLoop(boolean loop) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("View does not support this method");
  }

}
