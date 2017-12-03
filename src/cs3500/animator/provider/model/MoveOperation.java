
package cs3500.animator.provider.model;

/**
 * HW6 We changed the toString() to toSring(int tempo) because the view takes a tempo to make the
 * speed change. <p></p> HW7 We changed getScrPosn and getDestPosn method to override since this is
 * an implemented class, it shouldn't have a independent public method.
 */



/**
 * This is a MoveOperation Class extends AbstractAnimationOperations. It enables user to move a
 * shape in the list.
 */

public class MoveOperation extends AbstractAnimationOperations {
  private IPosn srcPosn;
  private IPosn destPosn;



/**
   * Constructor for ChangeColorOperation.
   *
   * @param shape     the shape that the operation is going to operate on.
   * @param destPosn  the new posn that the shape is going to be at.
   * @param startTime the start time of operation.
   * @param endTime   the end time of operation.
   */

  public MoveOperation(OperationType type, IShape shape, IPosn srcPosn, IPosn destPosn,
                       int startTime, int endTime) {
    super(type, shape, startTime, endTime);
    this.srcPosn = srcPosn;
    this.destPosn = destPosn;
  }


/**
   * Constructor for ChangeColorOperation. This is for copy the data of this class.
   *
   * @param m The MoveOperation you want to copy from.
   */

  public MoveOperation(MoveOperation m) {
    super(m.getType(), m.getShape(), m.getStartTime(), m.getEndTime());
    this.destPosn = m.getDestPosn();
    this.srcPosn = m.getSrcPosn();
  }

  @Override
  public String toString(int tempo) {
    return "Shape " + getShape().getName()
            + " moves from " + getShape().getPosn().toString()
            + " to " + destPosn.toString()
            + " from t=" + getStartTime() / tempo
            + "s to t=" + getEndTime() / tempo + "s";
  }


  @Override
  public void updateShape(IShape s, int time) {
    if (s.getName().equals(getShape().getName())) {
      float x = getCurrentState(time, srcPosn.getX(),//this.getShape().getPosn().getX(),
              getDestPosn().getX(), getStartTime(), getEndTime());
      float y = getCurrentState(time, srcPosn.getY(),//getShape().getPosn().getY(),
              getDestPosn().getY(), getStartTime(), getEndTime());
      IPosn newPosn = new Posn(x, y);
      s.updatePosn(newPosn);
    }
  }

  @Override
  public IAnimationOperations getOperation() {
    return new MoveOperation(this);
  }

  @Override
  public String svgState(int tempo, boolean loop) {
    double temp = (double) tempo;
    String s = "";
    if (loop) {
      if (getShape().getShapeType() == ShapeType.rectangle) {
        s = s + "    <animate attributeType=\"xml\" begin=\"base.begin+" +
                (1000 * getStartTime()) / temp + "ms\" " +
                "dur=\"" + (1000 * (getEndTime() - getStartTime())) / temp + "ms\" "
                + "attributeName=\"x\" from=\"" +
                srcPosn.getX() + "\" to=\"" + destPosn.getX() + "\" " + "fill=\"freeze\"/>\n" +
                "    <animate attributeType=\"xml\" begin=\"base.begin+" +
                (getStartTime() / temp) + "s\" " +
                "dur=\"" + (1000 * (getEndTime() - getStartTime())) / temp + "ms\" "
                + "attributeName=\"y\" from=\"" +
                srcPosn.getY() + "\" to=\"" + destPosn.getY() + "\" " +
                "fill=\"freeze\"/>\n";
      } else {
        s = s + "    <animate attributeType=\"xml\" begin=\"base.begin+" +
                (1000 * getStartTime()) / temp + "ms\" " + "dur=\"" +
                (1000 * (getEndTime() - getStartTime())) / temp + "ms\" " +
                "attributeName=\"cx\" from=\""
                + srcPosn.getX() + "\" to=\"" + destPosn.getX() + "\" " +
                "fill=\"freeze\"/>\n"
                + "    <animate attributeType=\"xml\" begin=\"base.begin+" +
                (1000 * getStartTime()) / temp
                + "ms\" " +
                "dur=\"" +
                (1000 * (getEndTime() - getStartTime())) / temp + "ms\" " +
                "attributeName=\"cy\" from=\"" +
                srcPosn.getY() + "\" to=\"" + destPosn.getY() + "\" " +
                "fill=\"freeze\"/>\n";
      }
    } else {

      if (getShape().getShapeType() == ShapeType.rectangle) {
        s = s + "    <animate attributeType=\"xml\" begin=\"" + (1000 * getStartTime()) / temp +
                "ms\" " + "dur=\"" + (1000 * (getEndTime() - getStartTime())) / temp + "ms\" " +
                "attributeName=\"x\" from=\"" +
                srcPosn.getX() + "\" to=\"" + destPosn.getX() + "\" " +
                "fill=\"freeze\"/>\n"
                + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp) + "s\" " +
                "dur=\"" +
                ((getEndTime() - getStartTime()) / temp) + "s\" " + "attributeName=\"y\" from=\"" +
                srcPosn.getY() + "\" to=\"" + destPosn.getY() + "\" " +
                "fill=\"freeze\"/>\n";
      } else {
        s = s + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp) + "s\" " +
                "dur=\"" +
                ((getEndTime() - getStartTime()) / temp) + "s\" " + "attributeName=\"cx\" from=\"" +
                srcPosn.getX() + "\" to=\"" + destPosn.getX() + "\" " +
                "fill=\"freeze\"/>\n"
                + "    <animate attributeType=\"xml\" begin=\"" + (getStartTime() / temp) + "s\" " +
                "dur=\"" +
                ((getEndTime() - getStartTime()) / temp) + "s\" " + "attributeName=\"cy\" from=\"" +
                srcPosn.getY() + "\" to=\"" + destPosn.getY() + "\" " +
                "fill=\"freeze\"/>\n";

      }
    }
    return s;
  }

  @Override
  public IAnimationOperations copyOperation() {
    return new MoveOperation(this);
  }


  @Override
  public IPosn getDestPosn() {
    return new Posn(destPosn);
  }


  @Override
  public IPosn getSrcPosn() {
    return new Posn(srcPosn);
  }


}

