package cs3500.animator;

import java.io.StringReader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.controller.IAnimationController;
import cs3500.animator.controller.InteractiveController;
import cs3500.animator.controller.SVGController;
import cs3500.animator.controller.TextController;
import cs3500.animator.controller.VisualController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.Utils;
import cs3500.animator.starter.AnimationFileReader;
import cs3500.animator.starter.TweenModelBuilder;
import cs3500.animator.view.IView;

/**
 * Represents an animation runner. Compiles the MVC to represent an animation.
 */
public class EasyAnimator {
  /**
   * Main method to run easy animation compiling model, view and controller.
   *
   * @param args arguments for the main method
   */
  public static void main(String[] args) {

    Readable r = new StringReader(String.join(" ", args));

    IAnimationModel model = null;
    Scanner scan = new Scanner(r);
    String filename = "";
    String viewType = "";
    String output = "";
    double speed = -1;

    IView view = null;
    IAnimationController controller = null;

    while (scan.hasNext()) {
      String in = scan.next();

      switch (in) {
        case "-if":
          if (filename.equals("") && scan.hasNext()) {
            filename = scan.next();
          }
          break;
        case "-iv":
          if (viewType.equals("") && scan.hasNext()) {
            viewType = scan.next();
          }
          break;
        case "-o":
          if (output.equals("") && scan.hasNext()) {
            output = scan.next();
          }
          break;
        case "-speed":
          if (speed == -1 && scan.hasNext()) {
            speed = Double.parseDouble(scan.next());
          }
          break;
        default:
          JFrame frame = new JFrame();
          frame.setSize(100, 100);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          JOptionPane.showMessageDialog(frame, "Invalid input",
                  "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (speed == -1) {
      speed = 1;
    }
    if (output.equals("") || output.equals("out")) {
      output = "System.out";
    }

    AnimationFileReader fileReader = new AnimationFileReader();
    TweenModelBuilder<IAnimationModel> simpleBuilder =
            new SimpleAnimationModel.SimpleAnimationModelBuilder();

    try {
      model = fileReader.readFile(filename, simpleBuilder);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame,
              "Invalid file", "Error", JOptionPane.ERROR_MESSAGE);
    }

    try {
      view = Utils.createView(viewType, model, speed);
    } catch (Exception e) {
      System.out.println("first erroro " + e.getMessage());
      System.out.println("create view");
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame, "Invalid view type",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    switch (viewType) {
      case "text":
        controller = new TextController(model, view, output);
        break;
      case "visual":
        controller = new VisualController(model, view, speed);
        break;
      case "svg":
        controller = new SVGController(view, output);
        break;
      case "interactive":
        controller = new InteractiveController(model, view, speed, output);
        break;
      default:
        JFrame frame = new JFrame();
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Invalid view type",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    try {
      controller.start();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("tried to start");
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame, "ERROR",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
