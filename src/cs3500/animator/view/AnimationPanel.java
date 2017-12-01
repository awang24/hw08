package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.Shapes;

/**
 * A class to represent a panel on which to draw animations. Extends JPanel to allow for overriding
 * paintComponent.
 */
public class AnimationPanel extends JPanel {

  private List<Shapes> shapes;

  /**
   * Constructs a {@code AnimationPanel} object.
   */

  public AnimationPanel() {
    super();
    shapes = new ArrayList<Shapes>();
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);

    AffineTransform originalTransform = g2d.getTransform();

    for (Shapes s : shapes) {
      if (s.getRender()) {
        Posn p = s.getPosn();
        int x = (int) p.getX();
        int y = (int) p.getY();
        int d1 = (int) s.getD1();
        int d2 = (int) s.getD2();
        Color c = s.getColor();
        if (s.getShapeType().equals(ShapeType.OVAL)) {
          g2d.setColor(c);
          g2d.fillOval(x, y, d1, d2);
          g2d.drawOval(x, y, d1, d2);
        } else if (s.getShapeType().equals(ShapeType.RECTANGLE)) {
          g2d.setColor(c);
          g2d.fillRect(x, y, d1, d2);
          g2d.drawRect(x, y, d1, d2);
        }
      }
    }

    g2d.setTransform(originalTransform);
  }

  /**
   * Sets the list of shapes of the panel to the given the list of shapes.
   *
   * @param shapes list of shapes to change the field list of shapes to
   */
  public void setShapes(List<Shapes> shapes) {
    this.shapes = shapes;
  }
}
