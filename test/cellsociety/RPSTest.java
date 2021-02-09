package cellsociety;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.CellView;
import view.SimulationView;

public class RPSTest extends DukeApplicationTest {

  private Scene myScene;
  private BorderPane root = new BorderPane();
  private Grid[] model = new Grid[1];
  private SimulationView[] display = new SimulationView[1];

  private Button myStartButton;
  private Button myStopButton;
  private Button myStepButton;
  private Button mySaveButton;
  private Label mySpeedLabel;
  private ComboBox myFileSelector;
  private ComboBox mySimulationSelector;
  private Label myInfo;
  private Main myGame = new Main();

  @Override
  public void start(Stage primaryStage) {
    Grid grid = new Grid("resources/RPSTest.properties");
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
  }

  @Test
  public void RPSUpdateTest() {
    CellView testCell = lookup("#2,4").query();

    //Cell is initially a Scissors Cell, surrounded by 6 Rock cells
    assertTrue(testCell.getFill() == Color.GREEN);

    clickOn(myStepButton);

    //Cell should become a Rock cell, because surrounded by more than 3 Rocks
    assertTrue(testCell.getFill() == Color.GREY);
  }

  @Test
  public void clickRPS() {
    Main.setIsRunning(false);
    CellView testCell1 = lookup("#0,0").query();

    assertTrue(testCell1.getFill() == Color.GREY);

    clickOn(testCell1);

    assertTrue(testCell1.getFill() == Color.YELLOW);
  }
}
