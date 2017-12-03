package cs3500.animator.provider.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.Timer;

import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.ShapeType;

/**
 * A class called HybridAnimationPanel whose purpose is to drawing the shapes on the canvas over the
 * time. It's better to avoid changing on the existed Animation, so we created a
 * HybridAnimationPanel for HybridAnimation. Since its purpose is the same as AnimationPanel, it
 * extends AnimationPanel. However, they are taking different fields, so we overrides some methods
 * and makes some new methods.
 */
public class HybridAnimationPanel extends AnimationPanel {

  private ArrayList<IShape> currentShapes;
  private int currentTime;
  private Timer timer;
  private List<IShape> shapes;
  private List<IShape> shapesOriginal;
  private List<IAnimationOperations> operations;
  private List<IAnimationOperations> operationsOriginal;
  private HashMap<String, IShape> animationMap;

  private boolean shouldLoop;


  /**
   * Constructor of HybridAnimationPanel.
   *
   * @param tempo The tempo of the animation.
   */
  public HybridAnimationPanel(int tempo) {
    super(tempo);
    this.shapes = new ArrayList<IShape>();
    this.operations = new ArrayList<IAnimationOperations>();

    this.shapesOriginal = new ArrayList<IShape>();
    this.operationsOriginal = new ArrayList<IAnimationOperations>();

    this.animationMap = new HashMap<>();

    this.setBackground(Color.white);

    this.currentTime = 0;

    this.currentShapes = getCurrentShapes(currentTime);
    this.timer = new Timer(1000 / tempo, this);
    this.shouldLoop = false;

  }

  /**
   * getter for the shouldLoop field.
   *
   * @return the shouldLoop status.
   */
  public boolean getShouldLoop() {
    return shouldLoop;
  }

  /**
   * copy the original shapes to the shapes.
   */
  public void copyLoShape() {
    this.shapes = new ArrayList<IShape>();

    for (IShape s : shapesOriginal) {
      shapes.add(s.copyShape());
    }

  }

  /**
   * copy the original operations to the operation.
   */
  public void copyLoOperations() {
    this.operations = new ArrayList<IAnimationOperations>();
    for (IAnimationOperations operation : operationsOriginal) {
      operations.add(operation.copyOperation());
    }
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);

    for (IShape s : currentShapes) {
      if (animationMap.containsKey(s.getName())) {
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
  }

  /**
   * to start the timer based on the boolean playing. If it is true, start it. If not, stop it.
   *
   * @param playing should play the timer or not.
   */
  public void startHybridView(boolean playing) {
    if (playing) {
      timer.restart();
    } else {
      timer.stop();
    }


  }

  @Override
  public void actionPerformed(ActionEvent e) {
    currentShapes = getCurrentShapes(currentTime);
    this.repaint();
    currentTime = currentTime + 1;

    if (shouldLoop) {
      if (currentTime == getLastTick()) {
        restartAnimation();
      }
    }


  }


  /**
   * get the current shapes based on the current time passing in.
   *
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

  @Override
  public void setShapes(List<IShape> shapes) {
    this.shapesOriginal = shapes;
    copyLoShape();
  }

  /**
   * set the animation map to the given on.
   *
   * @param animationMap the map that is going to assign in.
   */
  public void setAnimationMap(HashMap<String, IShape> animationMap) {
    this.animationMap = animationMap;
  }

  @Override
  public void setOperations(List<IAnimationOperations> operations) {
    this.operationsOriginal = operations;
    copyLoOperations();
  }


  /**
   * restart the animation by making the current time to 0 and assigning the shapes and operation to
   * the original ones.
   */
  public void restartAnimation() {
    currentTime = 0;
    copyLoShape();
    copyLoOperations();
  }

  /**
   * change shouldLoop field to true.
   */
  public void loopAnimation() {
    this.shouldLoop = true;

  }


  /**
   * change shouldLoop field to false.
   */
  public void unLoopAnimation() {
    this.shouldLoop = false;

  }

  /**
   * get the latest disappear time of the shapes.
   *
   * @return the latest disappear time.
   */
  private int getLastTick() {
    int temp = 0;
    for (IShape s : shapes) {
      if (s.getDisappearTime() > temp) {
        temp = s.getDisappearTime();
      }
    }
    return temp;
  }

  /**
   * change the speed to the given one.
   *
   * @param tempo the speed that is going to assign in.
   */
  public void changeSpeed(int tempo) {
    timer.setDelay(1000 / tempo);
  }


}
