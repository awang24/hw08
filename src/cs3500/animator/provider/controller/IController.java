package cs3500.animator.provider.controller;

/**
 * An interface called IController, which is the bridge to connect with model and view. It can
 * interactive with model and view. If the view require to show a different image, it will pass a
 * signal to the controller, and the controller will pass this signal to model, and then the model
 * will do the corresponding change. Then, let the controller pass the changed information to the
 * view. With the controller, the model and the view are independent from each other so that we can
 * prevent the probability of the model change the view and the view change the model.
 */
public interface IController {

  /**
   * start the animation.
   */
  void action();

  /**
   * get the tempo of the controller.
   * @return the tempo of the animation
   */
  int getTempo();
}
