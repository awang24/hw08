package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.provider.view.HybridView;

public class ProviderInteractiveViewToIViewAdapt implements IView {

  private cs3500.animator.provider.view.HybridView view;
  private boolean isLoop;
  private boolean isPlaying;
  private List<Shapes> shapes;
  private List<Animations> animations;
  private int lastTime;

  /**
   * The Constructor of HybridView.
   *
   * @param tempo The tempo of the animation.
   */
  public ProviderInteractiveViewToIViewAdapt(int tempo , List<Shapes> shapes,
                                             List<Animations> animations, int lastTime) {
    this.view = new HybridView(tempo);
    this.view.addShapesToCheckBox();
    //this.view.setOperations(animations);
    this.isLoop = false;
    this.isPlaying = true;
    this.shapes = shapes;
    this.animations = animations;
    this.lastTime = lastTime;
  }

  @Override
  public String getDescription() {
    return this.view.getString(isLoop);
  }

  @Override
  public void writeOut(String fileName) {
    this.view.outPut(true);
  }

  @Override
  public double getTempo() {
    return 0;
  }

  @Override
  public void makeVisible() {
    this.view.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this.view, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.view.repaint();
  }

  @Override
  public void setShapes(List<Shapes> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void setButtonListener(ActionListener actionEvent) {
    this.view.addActionListener(actionEvent);
  }

  @Override
  public String getFilenameCommand() {
    return this.view.getFileName();
  }

  @Override
  public List<JCheckBox> getCheckBoxList() {
    JCheckBox[] checkBoxes = this.view.getCheckBoxArray();
    ArrayList<JCheckBox> allBoxes = new ArrayList<JCheckBox>();

    for (int i = 0; i < checkBoxes.length; i++) {
      allBoxes.add(checkBoxes[i]);
    }
    return allBoxes;
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
    this.isLoop = loop;
  }

  @Override
  public boolean getIsLoop() {
    return this.isLoop;
  }
}
