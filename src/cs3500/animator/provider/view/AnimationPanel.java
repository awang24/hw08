package cs3500.animator.provider.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.ShapeType;

/**
 * HW7: we added setShapes and setOperations methods since now it is taking a list of shapes and
 * operations as its fields.
 */

/**
 * A class called AnimationPanel whose purpose is to drawing the shapes on the canvas over the
 * time. This class is created for AnimationView. Because the AnimationView needs to draw the shapes
 * it extends JPanel. It implements ActionListener for taking a timer.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  private ArrayList<IShape> currentShapes;
  private int currentTime;
  private Timer timer;
  private List<IShape> shapes;
  private List<IAnimationOperations> operations;
  private int tempo;


  /**
   * Constructor of AnimationPanel.
   * @param tempo The tempo of animation.
   */
  public AnimationPanel(int tempo) {
    super();
    this.setBackground(Color.white);
    this.currentShapes = new ArrayList<IShape>();
    this.currentTime = 0;
    this.shapes = new ArrayList<IShape>();
    this.operations = new ArrayList<IAnimationOperations>();
    this.timer = new Timer(1000 / tempo, this);
    this.tempo = tempo;
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);


    for (IShape s : currentShapes) {
      if (s.getShapeType() == ShapeType.rectangle) {

        float red = s.getColor().getRed();
        float green = s.getColor().getGreen();
        float blue = s.getColor().getBlue();

        g2d.setPaint(new Color(red, green, blue));

        g2d.fillRect((int) s.getPosn().getX(), (int) s.getPosn().getY(), (int) s.getX(),
                (int) s.getY());

      } else {

        float red = s.getColor().getRed();
        float green = s.getColor().getGreen();
        float blue = s.getColor().getBlue();
        g2d.setPaint(new Color(red, green, blue));
        g2d.fillOval((int) s.getPosn().getX(), (int) s.getPosn().getY(), (int) s.getX(),
                (int) s.getY());
      }
    }
  }

  /**
   * to start the timer.
   */
  public void start() {
    timer.start();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    currentShapes = getCurrentShapes(currentTime);
    this.repaint();
    currentTime = currentTime + 1;
  }

  /**
   * get the current shapes based on the current time passing in.
   * @param time current time.
   * @return a list of shapes which has already applied the operations on.
   */
  private ArrayList<IShape> getCurrentShapes(int time) {

    List<IShape> currentShapes = new ArrayList<IShape>();
    List<IAnimationOperations> currentOperations = new ArrayList<IAnimationOperations>();
    ArrayList<IShape> result = new ArrayList<IShape>();

    for (IShape s : shapes) {
      if (time >= s.getAppearTime() && time <= s.getDisappearTime()) {
        currentShapes.add(s);
      }
    }

    for (IAnimationOperations o : operations) {
      if (time >= o.getStartTime() && time <= o.getEndTime()) {
        currentOperations.add(o);
      }
    }

    for (IShape s : currentShapes) {
      for (IAnimationOperations o : currentOperations) {
        o.updateShape(s, time);
      }
      result.add(s);
    }

    return result;
  }


  /**
   * getter of the tempo.
   *
   * @return the rate of animation.
   */
  public int getTempo() {
    return tempo;
  }

  /**
   * set the given shapes in.
   * @param shapes the given shapes that are going to assign in.
   */
  public void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * set the given operations in.
   * @param operations the given operations that are going to assign in.
   */
  public void setOperations(List<IAnimationOperations> operations) {
    this.operations = operations;
  }
}
