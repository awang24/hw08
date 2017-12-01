package cs3500.animator.provider.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This class represents a Button listener. It is configurable by the controller that instantiates
 * it.
 * <p></p>
 * This listener keeps one map. That is for every time click the button. It stores a
 * string as its key, and a runnable. The string is getting from the ActionEvent. The latter part of
 * that pair is actually a function object, i.e. an object of a class that implements the Runnable
 * interface. If the ActionEvent's command is one of the string, we execute the runnable. <p></p>
 * This class implements the ActionListener interface, so that its object can be used as a valid
 * button listener for Java Swing.
 */
public class ButtonListener implements ActionListener {
  Map<String, Runnable> buttonClickedActions;

  /**
   * Set the buttonClickedActions map to the given map.
   *
   * @param map the map that is going to assign in.
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
