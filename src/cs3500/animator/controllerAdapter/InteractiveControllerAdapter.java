
package cs3500.animator.controllerAdapter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import cs3500.animator.controller.InteractiveController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.Utils;
import cs3500.animator.provider.controller.CheckBoxListener;
import cs3500.animator.provider.controller.IController;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.view.HybridView;
import cs3500.animator.provider.view.IView;
import cs3500.animator.view.InteractiveView;

public class InteractiveControllerAdapter implements IController, ActionListener {

  private IAnimationModel model;
  //private InteractiveController c;
  private HybridView view;
  private boolean isStarted;
  private boolean isLoop;
  private double tempo;
  private String filename;
  private InteractiveView myView;

  private List<IShape> shapes;
  private List<IAnimationOperations> animations;
  //private Map<String, Runnable> buttons;
  private Map<String, Runnable> checkBoxes;

  /**
   *
   * @param model
   * @param c
   * @param view
   **/
  public InteractiveControllerAdapter(IAnimationModel model, InteractiveController c,
                                      String filename, IView view, InteractiveView myView) {
    this.model = model;
    //this.c = c;
    this.view = (HybridView)view;
    this.isStarted = false;
    this.isLoop = false;
    this.tempo = c.getTempo();
    this.filename = filename;
    this.myView = myView;
    this.shapes = Utils.convertShapes(model.getShapes());
    this.animations = Utils.convertAnimations(model.getAnimations());

    //this.buttons = new HashMap<String, Runnable>();

    this.view.setShapes(this.shapes);
    this.view.setOperations(this.animations);
    this.initializeCheckBox();
  }

  // initiailizes the map for check boxes
  private void initializeCheckBox() {
    this.checkBoxes = new HashMap<String, Runnable>();

    checkBoxes.put("Select All", () -> this.view.setBoxSelected(true));
    checkBoxes.put("Select None", () -> this.view.setBoxSelected(false));

    for (int i = 0; i < shapes.size(); i++) {
      IShape cur = shapes.get(i);
      checkBoxes.put(cur.getName(), ()->this.view.selectShapes(cur));
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Start/Pause Button":
        isStarted = !isStarted;
        this.view.outPut(isStarted);
        break;
      case "Restart Button":
        this.view.restartAnimation();
        break;
      case "Loop Button":
        isLoop = !isLoop;
        if (isLoop) {
          this.view.loopAnimation();
        } else {
          this.view.unLoopAnimation();
        }
        this.myView.setIsLoop(isLoop);
        break;
      case "SpeedUp Button":
        tempo += 5;
        this.view.changeSpeed((int)tempo);
        break;
      case "SpeedDown Button":
        if (tempo <= 0) {
          tempo = 0;
        } else {
          tempo -= 5;
        }
        this.view.changeSpeed((int)tempo);
        break;
      case "SET FILE":
        filename = this.view.getFileName();
        break;
      case "Export Button":
        try {
          this.myView.writeOut(filename);
        } catch (Exception ex) {
          this.myView.showErrorMessage("Invalid filename.");
        }
        break;
      default:
        // do nothing
    }
  }

  @Override
  public void action() {
    this.view.addShapesToCheckBox();
    this.view.addActionListener(this);
    this.view.addItemListener(new CheckBoxListener(this.checkBoxes));

    this.view.setVisible(true);
  }

  @Override
  public int getTempo() {
    return (int)this.tempo;
  }
}

