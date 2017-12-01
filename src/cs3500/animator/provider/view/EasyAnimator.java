/*
package cs3500.animator.provider.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


import javax.swing.JOptionPane;
import javax.swing.JFrame;

import cs3500.animator.provider.controller.NormalController;
import cs3500.animator.provider.controller.HybridController;
import cs3500.animator.provider.controller.IController;
import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.IAnimationModel;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;


*/
/**
 * A final class to take in main arguments and run the command in three forms: textual, svg or
 * visual animation.
 *//*

public final class EasyAnimator {

  */
/**
   * The main methods that execute.
   *
   * @param args the command-line.
   * @throws FileNotFoundException if the file cannot be found.
   *//*

  public static void main(String[] args) throws FileNotFoundException {

    AnimationFileReader fileReader = new AnimationFileReader();
    String fileName = "";
    int tempo = 1;
    IAnimationModel model = null;
    IView view = null;
    String outputType = "out";
    String viewName = "";
    IViewFactory factory = new IViewFactory();
    IController controller = null;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-o")) {
        outputType = args[i + 1];
      }
    }

    for (int j = 0; j < args.length; j++) {
      if (args[j].equals("-speed")) {
        try {
          tempo = Integer.parseInt(args[j + 1]);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Cannot convert to integer");
        }
      }
    }

    for (int a = 0; a < args.length; a++) {
      if (args[a].equals("-if")) {
        fileName = args[a + 1];
      }
    }

    for (int b = 0; b < args.length; b++) {
      if (args[b].equals("-iv")) {
        viewName = args[b + 1];
      }
    }

    if (fileName.equals("")) {
      JOptionPane.showMessageDialog(new JFrame(),
              "Need a input file here");
    } else {
      model = fileReader.readFile(fileName, new AnimationModel.Builder());
    }

    if (viewName.equals("")) {
      JOptionPane.showMessageDialog(new JFrame(),
              "Need a view here");
    } else {
      view = factory.create(viewName, tempo);

      if (view instanceof HybridView) {
        controller = new HybridController(model, view, tempo);
      }
      else {
        controller = new NormalController(model, view, tempo);
        controller.action();
      }
    }


    if (outputType.equals("out")) {
      System.out.print(view.getString(false));
    } else {
      try {
        File out = new File(outputType);
        FileWriter fileWriter = new FileWriter(out);
        fileWriter.write(view.getString(false));
        fileWriter.flush();
        fileWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}



*/
