package cs3500.animator.provider.view;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

import cs3500.animator.provider.controller.CheckBoxListener;
import cs3500.animator.provider.model.IAnimationOperations;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.view.IView;

/**
 * A class called HybirdView, which extends JFrame because it need to show several functionalities,
 * implements IView because it is one of the view. This view has more functionality. Comparing with
 * AnimationView, the user can control when it starts, when it pauses, when it restart, if it loops,
 * and also the user can speed up or slow down the animation, and export this animation by naming
 * the file whatever he/she wants.
 */
public class HybridView extends JFrame implements IView {
  private int tempo;
  private List<IShape> shapes;
  private HashMap<String, IShape> selectedShapes;

  private JPanel mainPanel;
  private HybridAnimationPanel hybridAnimationPanel;
  private JPanel checkBoxesPanel;
  private JTextField textField;
  private JScrollPane scrollCheckBox;

  private JButton startButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton speedUpButton;
  private JButton speedDownButton;
  private JButton exportButton;


  private JCheckBox[] checkBoxes;

  private JCheckBox selectAll;
  private JCheckBox unSelectAll;


  private List<IAnimationOperations> operations;


  /**
   * The Constructor of HybridView.
   * @param tempo The tempo of the animation.
   */
  public HybridView(int tempo) {
    super();
    this.setTitle("Hybrid View");
    this.setSize(2000, 1200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.tempo = tempo;
    this.shapes = new ArrayList<IShape>();
    this.selectedShapes = new HashMap<String, IShape>();
    this.operations = new ArrayList<IAnimationOperations>();

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());

    // main panel
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setPreferredSize(new Dimension(1200, 800));
    this.add(mainPanel);

    // animation panel
    hybridAnimationPanel = new HybridAnimationPanel(tempo);
    hybridAnimationPanel.setPreferredSize(new Dimension(1000, 1000));

    // scroll the animation panel
    JScrollPane scrollAnimationPanel = new JScrollPane(hybridAnimationPanel);
    mainPanel.add(scrollAnimationPanel, BorderLayout.CENTER);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    //start buttons
    startButton = new JButton("Start/Pause");
    startButton.setActionCommand("Start/Pause Button");
    buttonPanel.add(startButton);

    //restart buttons
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");
    buttonPanel.add(restartButton);


    //loop buttons
    loopButton = new JButton("Loop");
    loopButton.setActionCommand("Loop Button");
    buttonPanel.add(loopButton);

    //speedUp buttons
    speedUpButton = new JButton("Speed Up");
    speedUpButton.setActionCommand("SpeedUp Button");
    buttonPanel.add(speedUpButton);

    //speedDown buttons
    speedDownButton = new JButton("Speed Down");
    speedDownButton.setActionCommand("SpeedDown Button");
    buttonPanel.add(speedDownButton);

    // SVG Name Text Area
    textField = new JTextField(15);
    textField.setBorder(BorderFactory.createTitledBorder("File Name of Exported File:"));
    buttonPanel.add(textField);

    //Export Button
    exportButton = new JButton("Export");
    exportButton.setActionCommand("Export Button");
    buttonPanel.add(exportButton);

    //quit button
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);


    //checkboxes panel
    checkBoxesPanel = new JPanel();
    checkBoxesPanel.setPreferredSize(new Dimension(500, 5000));
    //checkBoxesPanel.setLayout(new GridLayout(30, 1));
    checkBoxesPanel.setBorder(BorderFactory.createTitledBorder("Checkboxes"));
    scrollCheckBox = new JScrollPane(checkBoxesPanel);

    this.checkBoxes = new JCheckBox[shapes.size()];
    selectAll = new JCheckBox("Select All");
    unSelectAll = new JCheckBox("Select None");
    selectAll.setSelected(false);
    unSelectAll.setSelected(false);
    selectAll.setActionCommand("Select All");
    unSelectAll.setActionCommand("Select None");

    ButtonGroup group = new ButtonGroup();
    group.add(selectAll);
    group.add(unSelectAll);
    checkBoxesPanel.add(selectAll);
    checkBoxesPanel.add(unSelectAll);

    this.setVisible(true);
  }

  @Override
  public String getFileName() {
    String fileName = this.textField.getText();
    this.textField.setText("");
    return fileName;
  }


  @Override
  public void addShapesToCheckBox() {
    checkBoxes = new JCheckBox[shapes.size()];

    for (int i = 0; i < checkBoxes.length; i++) {
      checkBoxes[i] = new JCheckBox(shapes.get(i).getName());
      checkBoxes[i].setSelected(false);
      checkBoxes[i].setActionCommand(shapes.get(i).getName());
      checkBoxesPanel.add(checkBoxes[i]);
    }

    mainPanel.add(scrollCheckBox, BorderLayout.EAST);
    pack();
  }

  @Override
  public void setBoxSelected(boolean b) {
    for (int i = 0; i < checkBoxes.length; i++) {
      checkBoxes[i].setSelected(b);
    }
  }


  @Override
  public void addActionListener(ActionListener actionListener) {
    startButton.addActionListener(actionListener);
    restartButton.addActionListener(actionListener);
    loopButton.addActionListener(actionListener);
    speedUpButton.addActionListener(actionListener);
    speedDownButton.addActionListener(actionListener);
    exportButton.addActionListener(actionListener);
  }

  @Override
  public void addItemListener(CheckBoxListener actionListener) {
    selectAll.addItemListener(actionListener);
    unSelectAll.addItemListener(actionListener);
    for (int i = 0; i < shapes.size(); i++) {
      checkBoxes[i].addItemListener(actionListener);
    }
  }


  @Override
  public void setOperations(List<IAnimationOperations> operations) {
    this.operations = operations;
    hybridAnimationPanel.setOperations(operations);
  }

  @Override
  public void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
    addShapesToCheckBox();
    hybridAnimationPanel.setAnimationMap(selectedShapes);
    hybridAnimationPanel.setShapes(this.shapes);
  }


  @Override
  public void restartAnimation() {
    hybridAnimationPanel.restartAnimation();
  }

  @Override
  public void loopAnimation() {
    hybridAnimationPanel.loopAnimation();
  }

  @Override
  public void unLoopAnimation() {
    hybridAnimationPanel.unLoopAnimation();
  }

  @Override
  public int getDuration() {
    return -1;
  }


  @Override
  public void outPut(boolean playing) {
    hybridAnimationPanel.startHybridView(playing);
  }

  @Override
  public String getString(boolean loop) {
    SVGView svg = new SVGView(tempo, shapes, operations);

    return svg.getString(loop);
  }

  @Override
  public void changeSpeed(int tempo) {
    hybridAnimationPanel.changeSpeed(tempo);
  }



  @Override
  public void selectShapes(IShape shape) {
    if (!this.selectedShapes.containsKey(shape.getName())) {
      selectedShapes.put(shape.getName(), shape);
    } else {
      selectedShapes.remove(shape.getName(), shape);
    }
    hybridAnimationPanel.setAnimationMap(selectedShapes);

  }

  @Override
  public boolean getLoop() {
    return hybridAnimationPanel.getShouldLoop();
  }

  @Override
  public JCheckBox[] getCheckBoxArray() {
    return this.checkBoxes;
  }
}