package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * Represents a general interface for a view. Encapsulates all types of views. Methods throw
 * UnsupportedOperationException if the individual views does not need the functionality
 */
public interface IView {

  /**
   * Gets the string representation/text view of the animation.
   *
   * @return string representation of the animations and shapes
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  String getDescription();

  /**
   * Writes the string description out to a given text file name.
   *
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  void writeOut(String fileName);


/*  *//**
   * Returns the model that the view is using.
   *
   * @return the model that the view is using
   * @throws UnsupportedOperationException if the view does not need the functionality
   *//*
  IAnimationModel getModel();*/

  /**
   * Returns the tempo of the view.
   *
   * @return the tempo of the view
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  double getTempo();

  /**
   * Makes the view visible.
   *
   * @throws UnsupportedOperationException if the view does not need the functionality
   **/
  void makeVisible();

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error String error message
   * @throws UnsupportedOperationException if the view does not need the functionality
   **/
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself.
   *
   * @throws UnsupportedOperationException if the view does not need the functionality
   **/
  void refresh();

  /**
   * Sets the list of shapes to see.
   *
   * @param shapes List of shapes to add
   * @throws UnsupportedOperationException if the view does not need the functionality
   **/
  void setShapes(List<Shapes> shapes);

  /**
   * Provide the view with an actionListener for the buttons in the view.
   *
   * @param actionEvent The actionEvent for the button
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  void setButtonListener(ActionListener actionEvent);

  /**
   * Returns the file name command from the text box.
   *
   * @return file name from user
   */
  String getFilenameCommand();

  /**
   * Returns the checkbox list from this view.
   *
   * @return the view's checkbox list
   */
  List<JCheckBox> getCheckBoxList();

  /**
   * Returns the list of shapes from this view.
   *
   * @return the view's shapes list
   */
  List<Shapes> getShapes();

  /**
   * Returns the list of animations from this view.
   *
   * @return the view's animation list
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  List<Animations> getAnimations();

  /**
   * Sets the boolean isLoop in the view.
   *
   * @param loop boolean to set isLoop to
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  void setIsLoop(boolean loop);

  /**
   * Returns the is loop boolean in the view.
   *
   * @return boolean for the isLoop field
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  boolean getIsLoop();

}
