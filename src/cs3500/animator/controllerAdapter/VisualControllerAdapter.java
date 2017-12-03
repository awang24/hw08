package cs3500.animator.controllerAdapter;

import cs3500.animator.controller.VisualController;
import cs3500.animator.provider.controller.IController;

public class VisualControllerAdapter implements IController {
  private VisualController vc;

  /**
   * Constructor for VisualControllerAdapter.
   * @param vc VisualController to adapt
   */
  public VisualControllerAdapter(VisualController vc) {
    this.vc = vc;
  }

  @Override
  public void action() {
    this.vc.start();
  }

  @Override
  public int getTempo() {
    return (int)this.vc.getTempo();
  }
}
