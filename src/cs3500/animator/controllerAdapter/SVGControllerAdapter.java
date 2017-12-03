package cs3500.animator.controllerAdapter;

import cs3500.animator.controller.SVGController;
import cs3500.animator.provider.controller.IController;

public class SVGControllerAdapter implements IController {

  private SVGController svg;

  /**
   * Constructor for SVGControllerAdapter.
   * @param svg SVGController to convert
   */
  public SVGControllerAdapter(SVGController svg) {
    this.svg = svg;
  }

  @Override
  public void action() {
    this.svg.start();
  }

  @Override
  public int getTempo() {
    return (int)this.svg.getTempo();
  }
}
