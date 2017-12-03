package cs3500.animator.controllerAdapter;


import cs3500.animator.controller.TextController;
import cs3500.animator.provider.controller.IController;

public class TextControllerAdapter implements IController {

  private TextController tc;

  /**
   * Constructor for TextControllerAdapter.
   * @param tc TextController to adapt to
   */
  public TextControllerAdapter(TextController tc) {
    this.tc = tc;
  }

  @Override
  public void action() {
    this.tc.start();
  }

  @Override
  public int getTempo() {
    return 0;
  }
}
