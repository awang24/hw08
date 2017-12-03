package cs3500.animator.provider.model;

/**
 * HW6 We changed the toString() to toSring(int tempo) because the view takes a tempo to make the
 * speed change. For the SVGView, we add SVGState to get the SVG string for this operation. We also
 * added updateShape method for update the shape based on the time passing from view. <p></p> HW7 We
 * changed getNewColor to override since this is an implemented class, it shouldn't have a
 * independent public method.
 */


/**
 * This is a ChangeColorOperation Class extends AbstractAnimationOperations. It enables user to
 * change the color of the shape in the list.
 */
public class ChangeColorOperation extends AbstractAnimationOperations {
  private IColor oldColor;
  private IColor newColor;

  /**
   * Constructor for ChangeColorOperation.
   *
   * @param shape     the shape that the operation is going to operate on.
   * @param newColor  the new color that the shape is going to change into.
   * @param startTime the start time of operation.
   * @param endTime   the end time of operation.
   */
  public ChangeColorOperation(OperationType type, IShape shape, IColor oldColor, IColor newColor,
                              int startTime, int endTime) {
    super(type, shape, startTime, endTime);
    this.oldColor = oldColor;
    this.newColor = newColor;
  }

  /**
   * Constructor for ChangeColorOperation. This is for copy the data of this class.
   *
   * @param o The ChangeColorOperation that is going to be copied
   */
  public ChangeColorOperation(ChangeColorOperation o) {
    super(o.getType(), o.getShape(), o.getStartTime(), o.getEndTime());
    this.oldColor = new Color(o.oldColor);
    this.newColor = o.getNewColor();
  }


  @Override
  public String toString(int tempo) {
    return "Shape " + getShape().getName()
            + " changes color from " + getShape().getColor().toString()
            + " to " + this.newColor.toString()
            + " from t=" + (1000 * getStartTime()) / tempo
            + "ms to t=" + (1000 * getEndTime()) / tempo + "ms";
  }


  @Override
  public void updateShape(IShape s, int time) {
    if (s.getName().equals(this.getShape().getName())) {
      float red = getCurrentState(time, oldColor.getRed(),
              getNewColor().getRed(), getStartTime(), getEndTime());
      float green = getCurrentState(time, oldColor.getGreen(),
              getNewColor().getGreen(), getStartTime(), getEndTime());
      float blue = getCurrentState(time, oldColor.getBlue(),
              getNewColor().getBlue(), getStartTime(), getEndTime());

      Color newColor = new Color(red, green, blue);

      s.updateColor(newColor);
    }
  }


  @Override
  public IColor getNewColor() {
    return new Color(newColor);
  }

  @Override
  public IColor getOldColor() {
    return new Color(oldColor);
  }

  @Override
  public IAnimationOperations getOperation() {
    return new ChangeColorOperation(this);
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    double temp = (double) tempo;
    String s = "";
    if (loop) {
      s = s + "    <animate attributeType=\"xml\" begin=\"base.begin+" + (getStartTime() / temp)
              + "s\" dur=\"" +
              ((getEndTime() - getStartTime()) / temp) + "s\" attributeName=\"fill\" from=\"rgb(" +
              (int) (oldColor.getRed() * 255) + "," + (int) (255 * oldColor.getGreen()) + "," +
              (int) (255 * oldColor.getBlue()) + ")\" to=\"rgb(" +
              (int) (getNewColor().getRed() * 255) + "," + (int) (255 * getNewColor().getGreen())
              + "," +
              (int) (255 * getNewColor().getBlue()) + ")\" fill=\"freeze\"/>\n";

    } else {
      s = s + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp) + "s\" " +
              "dur=\"" +
              ((getEndTime() - getStartTime()) / temp) + "s\" attributeName=\"fill\" from=\"" +
              "rgb(" +
              (int) (oldColor.getRed() * 255) + "," + (int) (255 * oldColor.getGreen()) + "," +
              (int) (255 * oldColor.getBlue()) + ")\" to=\"rgb(" +
              (int) (getNewColor().getRed() * 255) +
              "," + (int) (255 * getNewColor().getGreen()) + "," +
              (int) (255 * getNewColor().getBlue()) + ")\" fill=\"freeze\"/>\n";
    }
    return s;


  }

  @Override
  public IAnimationOperations copyOperation() {
    return new ChangeColorOperation(this);
  }
}
