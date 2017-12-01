package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * A class to represent a SVG view that extends the Textual view.
 */
public class SVGView extends TextualView {

  /**
   * Constructs a {@code TextualView} object.
   *
   * @param tempo the tempo that the view is converted to
   * @param shapes represents the list of shapes of the model
   * @param animations represents the list of animations of the model
   */
  public SVGView(double tempo, List<Shapes> shapes, List<Animations> animations) {
    super(tempo, shapes, animations);
  }

  @Override
  public String getDescription() {
    List<Shapes> shapes = this.getShapes();
    List<Animations> animations = this.getAnimations();
    double tempo = this.getTempo();

    String state = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n";

    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      state += currentShape.toSVGTag();
      for (int j = 0; j < animations.size(); j++) {
        Animations currentA = animations.get(j);
        Shapes currentS = currentA.getShape();
        if (currentShape.getName().equals(currentS.getName())) {
          state += currentA.toSVGTag(this.getTempo());
        }
      }
      state += currentShape.svgEndTag() + "\n";
    }
    state += "</svg>";

    return state;
  }
}
