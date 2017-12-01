package cs3500.animator.model.animation;

import cs3500.animator.model.Utils;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.Shapes;


/**
 * Represents the move animation.
 */
public class MoveAnimation extends AAnimations {

  private Posn origin;
  private Posn dest;

  /**
   * Constructs a {@code MoveAnimation} object.
   *
   * @param shape The shape that the move animation will be implemented on
   * @param start The start time of the animation
   * @param end   The end time of the animation
   * @param dest  The destination location of the shape
   */
  public MoveAnimation(Shapes shape, int start, int end, Posn origin, Posn dest) {
    super(AnimationType.MOVE, shape, start, end);
    this.origin = origin;
    this.dest = dest;
  }

  @Override
  public void animate(double currentTime) {
    double currentX = this.origin.getX();
    double currentY = this.origin.getY();

    double destX = this.dest.getX();
    double destY = this.dest.getY();

    double changeX = destX - currentX;
    double changeY = destY - currentY;

    double changeInTime = (currentTime - this.getStart())
            / (double) (this.getEnd() - this.getStart());

    if ((currentTime > this.getEnd()) || (currentTime < this.getStart())) {
      // doesn't do anything
    } else {
      double newX = currentX + (changeX * changeInTime);
      double newY = currentY + (changeY * changeInTime);

      Posn newPosn = new Posn(newX, newY);

      this.getShape().setPosn(newPosn);
    }
  }

  @Override
  public String getMovement() {
    return "moves";
  }

  @Override
  public String getBeginState() {
    return Utils.getPosnString(this.origin);
  }

  @Override
  public String getEndState() {
    return Utils.getPosnString(this.dest);
  }

  @Override
  public void changeField(Shapes s) {
    s.setPosn(this.dest);
  }

  @Override
  public String toSVGTag(double tempo) {
    String svg = "";
    double begin = (this.getStart() / tempo) * 1000;
    double end = (this.getEnd() / tempo) * 1000;
    double dur = end - begin;

    svg += "<animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
            + dur + "ms\" attributeName=\"" + this.getShape().svgAnimationTagX() + "\" "
            + "from=\"" + this.origin.getX() + "\" to=\""
            + this.dest.getX() + "\" fill=\"freeze\" />\n";

    svg += "<animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
            + dur + "ms\" attributeName=\"" + this.getShape().svgAnimationTagY() + "\" "
            + "from=\"" + this.origin.getY() + "\" to=\""
            + this.dest.getY() + "\" fill=\"freeze\" />\n";

    return svg;
  }

  @Override
  public String toSVGTagWithLoop(double tempo) {
    String svg = "";
    double begin = (this.getStart() / tempo) * 1000;
    double end = (this.getEnd() / tempo) * 1000;
    double dur = end - begin;

    // tag for move animation
    svg += "<animate attributeType=\"xml\" begin=\"base.begin+" + begin + "ms\" dur=\""
            + dur + "ms\" attributeName=\"" + this.getShape().svgAnimationTagX() + "\" "
            + "from=\"" + this.origin.getX() + "\" to=\""
            + this.dest.getX() + "\" fill=\"freeze\" />\n";

    svg += "<animate attributeType=\"xml\" begin=\"base.begin+" + begin + "ms\" dur=\""
            + dur + "ms\" attributeName=\"" + this.getShape().svgAnimationTagY() + "\" "
            + "from=\"" + this.origin.getY() + "\" to=\""
            + this.dest.getY() + "\" fill=\"freeze\" />\n";

    // tag for looping
    svg += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"" + this.getShape().svgAnimationTagX() + "\" "
            + "from=\"" + this.dest.getX() + "\" to=\""
            + this.origin.getX() + "\" fill=\"freeze\" />\n";

    svg += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"" + this.getShape().svgAnimationTagY() + "\" "
            + "from=\"" + this.dest.getY() + "\" to=\""
            + this.origin.getY() + "\" fill=\"freeze\" />\n";
    return svg;
  }
}
