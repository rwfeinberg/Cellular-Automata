package cellsociety;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Cell;
import model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.CellView;
import view.SimulationView;


public class SimulationTest extends DukeApplicationTest {

  private Scene myScene;
  private BorderPane root = new BorderPane();
  private Grid[] model = new Grid[1];
  private SimulationView[] display = new SimulationView[1];

  private Button myStartButton;
  private Button myStopButton;
  private Button myStepButton;
  private Button mySaveButton;
  private Button myGenerateButton;
  private Label mySpeedLabel;
  private ComboBox myFileSelector;
  private ComboBox mySimulationSelector;
  private ComboBox myStyles;
  private Label myInfo;
  private Main myGame = new Main();

  @Override
  public void start(Stage primaryStage) {
    Grid grid = new Grid("resources/GameOfLife.properties");
    SimulationView s = new SimulationView(root, display, model, grid, "English");
    myScene = new Scene(root, Main.WIDTH, Main.HEIGHT);

    primaryStage.setScene(myScene);
    primaryStage.show();

    myStartButton = lookup("#Start").query();
    myStopButton = lookup("#Stop").query();
    myStepButton = lookup("#Step").query();
    mySaveButton = lookup("#Save").query();
    mySpeedLabel = lookup("#SpeedLabel").query();
    myInfo = lookup("#Info").query();
    myFileSelector = lookup("#FileSelect").query();
    mySimulationSelector = lookup("#SimulationDropdown").query();
    myStyles = lookup("#StyleDropdown").query();
    myGenerateButton = lookup("#GenerateCSV").query();
  }

  @BeforeEach
  public void setup() {
    Main.setIsRunning(true);
  }


  @Test
  public void applicationCreatedTest() {
    assertTrue(!myStartButton.isDisabled());
    assertTrue(!myStopButton.isDisabled());
    assertTrue(!myStepButton.isDisabled());
    assertTrue(!mySaveButton.isDisabled());

    assertEquals(myInfo.getText(), "Game Of Life: John Conway's Game Of Life");
  }

  @Test
  public void oneStepTest() {
    CellView testCell = lookup("#0,0").query();
    javafxRun(() -> myGame.step(model[0], display[0]));
    assertTrue(testCell.getFill() == Color.BLACK);
  }

  @Test
  public void clickStepButtonTest() {
    Main.setIsRunning(false);
    CellView testCell = lookup("#0,0").query();
    clickOn(myStepButton);
    assertTrue(testCell.getFill() == Color.BLACK);
  }

  @Test
  public void simulationSwitchTest() {
    CellView testCell1 = lookup("#0,0").query();

    assertTrue(testCell1.getFill() == Color.YELLOW);

    clickOn(myFileSelector);
    select(myFileSelector, "beacon6_6.csv");

    javafxRun(() -> myGame.step(model[0], display[0]));
    javafxRun(() -> myGame.step(model[0], display[0]));

    CellView testCell2 = lookup("#0,0").query();

    //In beacon6_6.csv, first cell is black
    assertTrue(testCell2.getFill() == Color.BLACK);
  }

  @Test
  public void stepTest() {
    Main.setIsRunning(false);
    CellView testCell1 = lookup("#0,0").query();

    assertTrue(testCell1.getFill() == Color.YELLOW);

    clickOn(myStepButton);

    assertTrue(testCell1.getFill() == Color.BLACK);
  }

  @Test
  public void checkSpeedSliderExistsTest() {
    assertEquals("Speed: 1.0 FPS", mySpeedLabel.getText());
  }

  @Test
  public void simulationSelectorTest() {
    assertTrue(myFileSelector.getItems().contains("GameOfLife.csv"));

    clickOn(mySimulationSelector);
    select(mySimulationSelector, "data/Percolation");

    assertTrue(!myFileSelector.getItems().contains("GameOfLife.csv"));
  }

  @Test
  public void saveNewConfigTest() {
    int initialCSVListSize = new File("data/SavedConfigurations").listFiles().length;
    int initialPropertiesListSize = new File("src/resources").listFiles().length;

    clickOn(mySaveButton);

    //Save files called a.csv and a.properties
    writeToInputDialog("Test");
    writeToInputDialog("Test");
    writeToInputDialog("Test");

    int CSVListSize = new File("data/SavedConfigurations").listFiles().length;
    int propertiesListSize = new File("src/resources").listFiles().length;
    //1 Saved csv now
    assertEquals(CSVListSize, 1);
    //1 more properties file now
    assertTrue(initialPropertiesListSize >= 1);
  }

  @Test
  public void clickOnCellTest() {
    Main.setIsRunning(false);
    CellView testCell1 = lookup("#0,0").query();

    assertTrue(testCell1.getFill() == Color.YELLOW);

    clickOn(testCell1);

    assertTrue(testCell1.getFill() == Color.BLACK);
  }

  @Test
  public void changeStyleTest() {
    Main.setIsRunning(false);

    Paint initialBackground = root.getBackground().getFills().get(0).getFill();

    clickOn(myStyles);
    select(myStyles, "duke.css");

    //Background fill changed
    assertNotEquals(root.getBackground().getFills().get(0).getFill(), initialBackground);
  }

  @Test
  public void testGenerateButton() {
    Main.setIsRunning(false);

    int numberOfYellow = 0;
    for (List<Cell> row : model[0].getGrid()) {
      for (Cell c : row) {
        if (c.getMyState() == 1) {
          numberOfYellow++;
        }
      }
    }

    int initialCount = numberOfYellow;

    clickOn(myGenerateButton);

    int num = 0;
    for (List<Cell> row : model[0].getGrid()) {
      for (Cell c : row) {
        if (c.getMyState() == 1) {
          num++;
        }
      }
    }

    assertTrue(initialCount != num);
  }
}
