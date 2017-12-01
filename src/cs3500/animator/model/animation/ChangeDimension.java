package cs3500.animator.model.animation;

import cs3500.animator.model.Utils;
import cs3500.animator.model.shape.Shapes;

/**
 * Represents the change dimension animation.
 */
public class ChangeDimension extends AAnimations {
  private double originalD1;
  private double originalD2;
  private double newD1;
  private double newD2;


  /**
   * Constructs a {@code ChangeDimension} object.
   *
   * @param shape The shape that the move animation will be implemented on
   * @param start The start time of the animation
   * @param end   The end time of the animation
   * @param d1    The data to change one dimension of the shape
   * @param d2    The data to change the second dimension of the shape
   */
  public ChangeDimension(Shapes shape, int start, int end, double beginD1, double beginD2,
                         double d1, double d2) {
    super(AnimationType.CHANGEDIMENSION, shape, start, end);
    if (Utils.isNegative(d1) || Utils.isNegative(d2)) {
      throw new IllegalArgumentException("New dimensions can not be negative");
    } else {
      this.originalD1 = beginD1;
      this.originalD2 = beginD2;
      this.newD1 = d1;
      this.newD2 = d2;
    }
  }

  @Override
  public void animate(double currentTime) {
    double changeD1 = this.newD1 - this.originalD1;
    double changeD2 = this.newD2 - this.originalD2;

    double changeInTime = (double) (currentTime - this.getStart())
            / (double) (this.getEnd() - this.getStart());

    if ((currentTime > this.getEnd()) || (currentTime < this.getStart())) {
      // do nothing
    } else {
      double d1 = this.originalD1 + (changeInTime * changeD1);
      double d2 = this.originalD2 + (changeInTime * changeD2);
      this.getShape().setD1(d1);
      this.getShape().setD2(d2);
    }
  }

  @Override
  public String getMovement() {
    return "scales";
  }

  @Override
  public String getBeginState() {
    return this.getShape().d1TagString() + this.originalD1 + ", " + this.getShape().d2TagString()
            + this.originalD2;
  }

  @Override
  public String getEndState() {
    return this.getShape().d1TagString() + this.newD1 + ", " + this.getShape().d2TagString()
            + this.newD2;
  }

  @Override
  public void changeField(Shapes s) {
    s.setD1(newD1);
    s.setD2(newD2);
  }

  @Override
  public String toSVGTag(double tempo) {
    double begin = (this.getStart() / tempo) * 1000;
    double end = (this.getEnd() / tempo) * 1000;
    double dur = end - begin;

    String svg = "";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.getShape().svgD1Tag() + "\" "
            + "from=\"" + this.originalD1
            + "\" to=\"" + this.newD1 + "\" fill=\"freeze\" /> \n";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.getShape().svgD2Tag() + "\" "
            + "from=\"" + this.originalD2
            + "\" to=\"" + this.newD2 + "\" fill=\"freeze\" />\n";

    return svg;
  }

  @Override
  public String toSVGTagWithLoop(double tempo) {
    double begin = (this.getStart() / tempo) * 1000;
    double end = (this.getEnd() / tempo) * 1000;
    double dur = end - begin;

    String svg = "";

    // actual move tags
    svg += "<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"base.begin+" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.getShape().svgD1Tag() + "\" "
            + "from=\"" + this.originalD1
            + "\" to=\"" + this.newD1 + "\" fill=\"freeze\" /> \n";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"base.begin+" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.getShape().svgD2Tag() + "\" "
            + "from=\"" + this.originalD2
            + "\" to=\"" + this.newD2 + "\" fill=\"freeze\" />\n";

    // tag for looping back
    svg += "<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"base.end\" dur=\"1ms\"" + " attributeName=\""
            + this.getShape().svgD1Tag() + "\" "
            + "from=\"" + this.newD1
            + "\" to=\"" + this.originalD1 + "\" fill=\"freeze\" /> \n";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"base.end\" dur=\"1ms\"" + " attributeName=\""
            + this.getShape().svgD2Tag() + "\" "
            + "from=\"" + this.newD2
            + "\" to=\"" + this.originalD2 + "\" fill=\"freeze\" />\n";

    return svg;
  }
}
