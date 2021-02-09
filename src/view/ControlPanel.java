package view;

import cellsociety.Main;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Grid;
import view.components.SimulationButton;
import view.components.SimulationMenu;
import view.components.buttons.GenerateButton;
import view.components.buttons.SaveButton;
import view.components.buttons.StartButton;
import view.components.buttons.StepButton;
import view.components.buttons.StopButton;
import view.components.menus.GameMenu;
import view.components.menus.GridMenu;
import view.components.menus.StyleMenu;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The ControlPanel.java class represents the bottom of the root gridpane, and contains
 * all of the buttons and dropdown menus of the simulation, as well as the speed slider.
 * <p>
 * Assumptions - The language properties files should be in a directory called /configs/
 * <p>
 * Dependencies - This class depends on Main.java for constants, Grid.java and SimulationView.java
 * for parameters of the model and view, as well as SimulationMenu.java and SimulationButton.java to
 * create the respective instances.
 */
public class ControlPanel {

  private final String DEFAULT_CONFIG_PACKAGE = Main.RESOURCES.replace("/", ".") + "configs.";

  private SimulationMenu myDropdown;
  private ResourceBundle myResources;
  private SimulationMenu myFiles;
  private SimulationMenu myStyles;
  private SimulationButton myStartButton;
  private SimulationButton myStopButton;
  private SimulationButton myStepButton;
  private SimulationButton mySaveButton;
  private SimulationButton myNewCSVButton;
  private VBox mySpeedSlider;
  private VBox myNode;

  private File myDir = new File(Main.DATA_DIRECTORY + "GameOfLife");

  /**
   * Constructor, creates the navigation panel at the bottom of the screen and sets ids.
   *
   * @param root     - instance of the view root
   * @param language - configuration language obtained from splash screen
   * @param model    - instance of the grid
   * @param display  - instance of the view
   */
  public ControlPanel(BorderPane root, String language, SimulationView[] display, Grid[] model) {
    myResources = ResourceBundle.getBundle(DEFAULT_CONFIG_PACKAGE + language);
    myNode = new VBox();
    myNode.setId("ControlPanel");

    myFiles = new GridMenu("FileSelect", myResources, root, language, model, display, myFiles);
    myDropdown = new GameMenu("SimulationDropdown", myResources, root, language, model, display,
        myFiles);
    myStyles = new StyleMenu("StyleDropdown", myResources, root, language, model, display, myFiles);
    myNewCSVButton = new GenerateButton("GenerateCSV", myResources, model, display);
    myStartButton = new StartButton("Start", myResources, model, display);
    myStopButton = new StopButton("Stop", myResources, model, display);
    myStepButton = new StepButton("Step", myResources, model, display);
    mySaveButton = new SaveButton("Save", myResources, model, display);
    mySpeedSlider = createSlider("Speed", model, display);

    HBox topBox = new HBox();
    HBox bottomBox = new HBox();

    bottomBox.setId("bottomBox");
    topBox.setId("topBox");

    topBox.getChildren().addAll(myDropdown, myFiles, myStyles);
    bottomBox.getChildren()
        .addAll(myNewCSVButton, myStartButton, myStopButton, myStepButton, mySaveButton,
            mySpeedSlider);

    myNode.getChildren().addAll(topBox, bottomBox);
  }


  private VBox createSlider(String prop, Grid[] model, SimulationView[] display) {
    VBox result = new VBox();
    Slider slider = new Slider();
    Label speedLabel = new Label();
    speedLabel.setText(myResources.getString(prop) + ": 1.0 FPS");

    slider.setMin(1);
    slider.setMax(25);
    slider.setValue(1);

    slider.valueProperty().addListener(
        (o, old, nw) -> {
          Main.setFPS(nw.intValue(), model, display);
          speedLabel.setText(prop + ": " + nw + " FPS");
        });

    slider.setId("SpeedSlider");
    speedLabel.setId("SpeedLabel");
    result.setId(prop);
    result.getChildren().addAll(slider, speedLabel);
    return result;
  }

  /**
   * This method is used to return the actual node initialized in the constructor
   *
   * @return - myNode node, which contains all buttons/menus
   */
  public Node getMyNode() {
    return myNode;
  }
}
