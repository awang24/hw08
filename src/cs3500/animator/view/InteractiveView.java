package cs3500.animator.view;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * A class that represents a view for an interactive view.
 */
public class InteractiveView extends JFrame implements IView {

  private double tempo;
  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private JButton fileButton;
  private JButton exportButton;
  private JCheckBox loopCheckbox;
  private JTextField fileInput;
  private List<JCheckBox> checkBoxList;

  private AnimationPanel animatePanel;
  private List<Shapes> shapes;
  private List<Animations> animations;
  private boolean isLoop;
  private int lastTime;

  /**
   * Constructs a {@code InteractiveView} object.
   *
   * @param tempo  represents the speed at which the animation occurs
   * @param shapes list of shapes that view will use
   */
  public InteractiveView(double tempo, List<Shapes> shapes, List<Animations> animations,
                         int lastTime) {
    super();

    this.isLoop = false;
    this.lastTime = lastTime;

    this.tempo = tempo;
    this.checkBoxList = new ArrayList<JCheckBox>();

    this.shapes = shapes;
    this.animations = animations;

    this.setTitle("Simple Animation");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animatePanel = new AnimationPanel();
    animatePanel.setPreferredSize(new Dimension(1000, 1000));

    animatePanel.setShapes(shapes);

    JScrollPane animationScrollPane = new JScrollPane(animatePanel);
    animationScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    animationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    animationScrollPane.setBounds(50, 30, 300, 500);
    animationScrollPane.setPreferredSize(new Dimension(1000, 1000));


    this.add(animationScrollPane);

    JPanel buttonPanel;
    JLabel checkboxPanelLabel;
    JPanel checkboxPanel;

    // button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    // buttons
    playButton = new JButton("PLAY");
    buttonPanel.add(playButton);

    pauseButton = new JButton("PAUSE");
    buttonPanel.add(pauseButton);
    restartButton = new JButton("RESTART");
    buttonPanel.add(restartButton);

    loopCheckbox = new JCheckBox("LOOP");
    buttonPanel.add(loopCheckbox);

    increaseSpeedButton = new JButton("INCREASE SPEED");
    buttonPanel.add(increaseSpeedButton);

    decreaseSpeedButton = new JButton("DECREASE SPEED");
    buttonPanel.add(decreaseSpeedButton);

    // input textfield
    fileInput = new JTextField(10);
    buttonPanel.add(fileInput);
    fileButton = new JButton("SET FILE");
    buttonPanel.add(fileButton);

    exportButton = new JButton("EXPORT");
    buttonPanel.add(exportButton);

    //checkbox panel
    checkboxPanel = new JPanel();
    checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
    checkboxPanelLabel = new JLabel(" Select Shapes When Paused ");
    checkboxPanel.add(checkboxPanelLabel);

    // check boxes
    for (int i = 0; i < shapes.size(); i++) {
      Shapes current = shapes.get(i);
      JCheckBox currentBox = new JCheckBox(current.getName());

      currentBox.setSelected(true);

      this.checkBoxList.add(currentBox);
      checkboxPanel.add(currentBox);
    }

    JScrollPane checkboxScrollPane = new JScrollPane(checkboxPanel);
    checkboxScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    checkboxScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    checkboxScrollPane.setBounds(50, 30, 50, 500);
    checkboxScrollPane.setPreferredSize(new Dimension(200, 1000));

    this.add(checkboxScrollPane, BorderLayout.EAST);

    this.pack();

  }

  @Override
  public String getDescription() {

    String state = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n";

    double lastTime = (this.lastTime / tempo) * 1000;
    state += "<rect>\n"
            + "<animate id=\"base\" begin=\"0;base.end\" dur=\"" + lastTime + "ms\" "
            + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
            + "</rect>\n";

    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      if (currentShape.getRender()) {
        state += currentShape.toSVGTag();
        for (int j = 0; j < animations.size(); j++) {
          Animations currentA = animations.get(j);
          Shapes currentS = currentA.getShape();
          if (currentShape.getName().equals(currentS.getName()) && !isLoop) {
            state += currentA.toSVGTag(this.getTempo());
          } else if (currentShape.getName().equals(currentS.getName()) && isLoop) {
            state += currentA.toSVGTagWithLoop(this.getTempo());
          }
        }
        state += currentShape.svgEndTag() + "\n";
      }
    }
    state += "</svg>";

    return state;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setButtonListener(ActionListener actionEvent) {
    playButton.addActionListener(actionEvent);
    pauseButton.addActionListener(actionEvent);
    restartButton.addActionListener(actionEvent);
    increaseSpeedButton.addActionListener(actionEvent);
    decreaseSpeedButton.addActionListener(actionEvent);
    fileButton.addActionListener(actionEvent);
    loopCheckbox.addActionListener(actionEvent);
    exportButton.addActionListener(actionEvent);
  }

  @Override
  public String getFilenameCommand() {
    return fileInput.getText();
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setShapes(List<Shapes> shapes) {
    this.shapes = shapes;
    animatePanel.setShapes(shapes);
  }

  @Override
  public void writeOut(String fileName) {
    String description = this.getDescription();
    try {
      BufferedWriter output = null;
      if (fileName.equals("System.out")) {
        output = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File file = new File(fileName);
        output = new BufferedWriter(new FileWriter(file));
      }
      output.write(description);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public double getTempo() {
    return this.tempo;
  }

  @Override
  public List<JCheckBox> getCheckBoxList() {
    return this.checkBoxList;
  }

  @Override
  public List<Shapes> getShapes() {
    return this.shapes;
  }

  @Override
  public List<Animations> getAnimations() {
    return this.animations;
  }

  @Override
  public void setIsLoop(boolean loop) {
    this.isLoop = loop;
  }

  @Override
  public boolean getIsLoop() {
    return this.isLoop;
  }


}
