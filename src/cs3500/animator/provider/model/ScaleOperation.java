
package cs3500.animator.provider.model;

/**
 * HW6 We changed the toString() to toSring(int tempo) because the view takes a tempo to make the
 * speed change. For the SVGView, we add SVGState to get the SVG string for this operation. We also
 * added updateShape method for update the shape based on the time passing from view. HW7 We changed
 * getChangedWidth and getChangedHeight method to override since this is an implemented class, it
 * shouldn't have a independent public method.
 */



/**
 * This is a ScaleOperation Class extends AbstractAnimationOperations. It enables user to change the
 * size of the shape in the list.
 */

public class ScaleOperation extends AbstractAnimationOperations {
  private float oldWidth;
  private float oldHeight;
  private float changedWidth;
  private float changedHeight;


/**
   * Constructor for ScaleOperation.
   *
   * @param shape         the shape that the operation is going to operate on.
   * @param changedWidth  the new width that the shape is going to be.
   * @param changedHeight the new height that the shape is going to be.
   * @param startTime     the start time of operation.
   * @param endTime       the end time of operation.
   */

  public ScaleOperation(OperationType type, IShape shape, float oldWidth, float oldHeight,
                        float changedWidth, float changedHeight,
                        int startTime, int endTime) {
    super(type, shape, startTime, endTime);
    this.oldWidth = oldWidth;
    this.oldHeight = oldHeight;
    this.changedWidth = changedWidth;
    this.changedHeight = changedHeight;
  }


/**
   * Constructor for ScaleOperation. This is for copy the data of this class.
   *
   * @param s The ScaleOperation that is going to be copied
   */

  public ScaleOperation(ScaleOperation s) {
    super(s.getType(), s.getShape(), s.getStartTime(), s.getEndTime());
    this.oldWidth = s.oldWidth;
    this.oldHeight = s.oldHeight;
    this.changedWidth = s.getChangedWidth();
    this.changedHeight = s.getChangedHeight();
  }

  @Override
  public String toString(int tempo) {
    switch (getShape().getShapeType()) {
      case oval:
        return "Shape " + getShape().getName()
                + " scales from XRadius:" + getShape().getX()
                + ", yRadius:" + getShape().getY()
                + " to XRadius:" + changedWidth
                + ", yRadius:" + changedHeight
                + "from t=" + getStartTime() / tempo
                + "s to t=" + getEndTime() / tempo + "s";
      case rectangle:
        return "Shape " + getShape().getName()
                + " scales from Width: " + getShape().getX()
                + ", Height: " + getShape().getY()
                + " to Width: " + changedWidth
                + ", Height: " + changedHeight
                + " from t=" + getStartTime() / tempo
                + "s to t=" + getEndTime() + "s";
      default:
        return "";
    }
  }


  @Override
  public void updateShape(IShape s, int time) {
    if (s.getName().equals(getShape().getName())) {
      float changedX = getCurrentState(time, oldWidth,
              getChangedWidth(), getStartTime(), getEndTime());
      float changedY = getCurrentState(time, oldHeight,
              getChangedHeight(), getStartTime(), getEndTime());

      s.updateScale(changedX, changedY);
    }
  }

  @Override
  public IAnimationOperations getOperation() {
    return new ScaleOperation(this);
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    double temp = (double) tempo;
    String s = "";
    if (loop) {
      if (getShape().getShapeType() == ShapeType.rectangle) {
        s = s + "    <animate attributeType=\"xml\" begin=\"base.begin+" + (getStartTime() / temp)
                + "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"width\" from=\"" + getShape().getX() + "\" to=\"" +
                getChangedWidth() + "\" fill=\"freeze\"/>\n"
                + "    <animate attributeType=\"xml\" begin=\"base.begin+" + (getStartTime() / temp)
                + "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"height\" from=\"" +
                getShape().getY() + "\" to=\"" + getChangedHeight() + "\" fill=\"freeze\"/>\n";
      } else {
        s = s + "    <animate attributeType=\"xml\" begin=\"base.begin+" + (getStartTime() / temp) +
                "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"rx\" from=\"" + getShape().getX() + "\" to=\"" + getChangedWidth()
                + "\" fill=\"freeze\"/>\n"
                + "    <animate attributeType=\"xml\" begin=\"base.begin+" + (getStartTime() / temp)
                + "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"ry\" from=\"" + getShape().getY() + "\" to=\"" +
                getChangedHeight() + "\" fill=\"freeze\"/>\n";
      }
    } else {
      if (getShape().getShapeType() == ShapeType.rectangle) {
        s = s + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp)
                + "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"width\" from=\"" + getShape().getX() + "\" to=\"" +
                getChangedWidth() + "\" fill=\"freeze\"/>\n"
                + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp) +
                "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"height\" from=\"" +
                getShape().getY() + "\" to=\"" + getChangedHeight() + "\" fill=\"freeze\"/>\n";
      } else {
        s = s + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp) +
                "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"rx\" from=\"" + getShape().getX() + "\" to=\"" + getChangedWidth()
                + "\" fill=\"freeze\"/>\n"
                + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp) +
                "s\" dur=\"" + ((getEndTime() - getStartTime()) / temp) + "s\" " +
                "attributeName=\"ry\" from=\"" + getShape().getY() + "\" to=\"" +
                getChangedHeight() + "\" fill=\"freeze\"/>\n";
      }
    }
    return s;
  }

  @Override
  public IAnimationOperations copyOperation() {
    return new ScaleOperation(this);
  }


  @Override
  public float getChangedHeight() {
    return changedHeight;
  }


  @Override
  public float getChangedWidth() {
    return changedWidth;
  }
}

