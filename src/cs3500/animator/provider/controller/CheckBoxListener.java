package cs3500.animator.provider.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JCheckBox;


/**
 * This class represents a check box listener. It is configurable by the controller that
 * instantiates it.
 * <p></p>
 * This listener keeps one map called checkBoxListener. That is for every
 * time click the check box. It stores a string as its key, and a runnable. The string is getting
 * from the ActionEvent. The latter part of that pair is actually a function object, i.e. an object
 * of a class that implements the Runnable interface. If the ActionEvent's command is one of the
 * string, we execute the runnable.
 * <p></p>
 * This class implements the ActionListener interface, so
 * that its object can be used as a valid check box listener for Java Swing.
 */
public class CheckBoxListener implements ItemListener {
  Map<String, Runnable> checkBoxListener;

  /**
   * Constructor of CheckBoxListener.
   *
   * @param map The map that is going to assign in.
   */
  public CheckBoxListener(Map<String, Runnable> map) {
    super();
    this.checkBoxListener = map;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    String who = ((JCheckBox) e.getItemSelectable()).getActionCommand();

    if (checkBoxListener.containsKey(who)) {
      checkBoxListener.get(who).run();
    }

  }
}
