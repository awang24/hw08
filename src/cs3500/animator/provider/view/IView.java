package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.animator.provider.controller.CheckBoxListener;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;

/**
 * HW7: we added several methods such as addActionListener, addItemListener, setBoxSelected,
 * addShapesToCheckBox, getFileName, changeSpeed, getDuration, setShapes, restartAnimation,
 * loopAnimation, unLoopAnimation. They are for the hybridView's functionalities. Also, we added a
 * parameter, a boolean, to getString method because the hybridView need to export a file based on
 * the boolean. The file looks very different for true and false.
 */


/**
 * The IVew interface is designed to draw the shapes in the canvas, to show the client what they can
 * see over the time. Because there are four ways to present the animation, so under this interface
 * there are four classes for each way. Also, it has some foundational methods to execute the
 * animation.
 */
public interface IView {


  /**
   * by checking if it is playing or not, to output the animation. The textual, svg, and animation
   * aren't necessarily determined by the boolean, but the hybrid view cares about it.
   *
   * @param playing if the animation is playing or not.
   */
  void outPut(boolean playing);

  /**
   * get the animation in string based on loop or not. The textual, svg, and animation don't care
   * about this loop, but hybrid view does.
   *
   * @param loop if the animation is looping or not.
   * @return A string of the animation.
   */
  String getString(boolean loop);


  /**
   * add the given action listener to the view.
   *
   * @param actionListener the action listener that is going to add in.
   */
  void addActionListener(ActionListener actionListener);

  /**
   * add the given check box listener to the view.
   *
   * @param actionListener the check box listener that is going to add in.
   */
  void addItemListener(CheckBoxListener actionListener);


  /**
   * set the given operations to the view.
   *
   * @param operations the list of operations that are going to add in.
   */
  void setOperations(List<IAnimationOperations> operations);

  /**
   * set the given shapes to the view.
   *
   * @param shapes the list of shapes that are going to add in.
   */
  void setShapes(List<IShape> shapes);

  /**
   * to restart the animation.
   */
  void restartAnimation();

  /**
   * to loop the animation, letting the animation plays again and again.
   */
  void loopAnimation();

  /**
   * to cancel looping the animation.
   */
  void unLoopAnimation();

  /**
   * get the duration of the animation by finding difference between earliest appear time and latest
   * disappear time.
   * @return the difference between earliest appear time and latest disappear time.
   */
  int getDuration();

  /**
   * change the tempo of the animation.
   * @param tempo the tempo that is going to assign in to the view.
   */
  void changeSpeed(int tempo);


  /**
   * depending on weather the given shape is in the hash map, which is for check boxes, or not, if
   * not, put the shape into the hash map. If has, remove the shape from the map.
   * @param shape the shape that is going to put in the map or remove from the map.
   */
  void selectShapes(IShape shape);

  /**
   * add shapes to the check boxes.
   */
  void addShapesToCheckBox();

  /**
   * depending on the boolean b, make the checkBoxes selected or unselected.
   * @param b the selected or unselected status passing to the check boxes.
   */
  void setBoxSelected(boolean b);

  /**
   * get the file name from user's input.
   * @return the file name.
   */
  String getFileName();

  /**
   * get the loop status.
   * @return get the loop status.
   */
  boolean getLoop();

  /**
   * get the check box array
   *
   * @return array for the check box array field
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  JCheckBox[] getCheckBoxArray();


}
