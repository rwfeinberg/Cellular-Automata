package cellsociety;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.CellView;
import view.SimulationView;

public class PercolationTest extends DukeApplicationTest {

  private BorderPane root = new BorderPane();
  private Grid[] model = new Grid[1];
  private SimulationView[] display = new SimulationView[1];

  private Main myGame = new Main();

  @Override
  public void start(Stage primaryStage) {
    Grid grid = new Grid("resources/testPercolation.properties");
    SimulationView s = new SimulationView(root, display, model, grid, "English");
    Scene scene = new Scene(root, Main.WIDTH, Main.HEIGHT);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @BeforeEach
  public void setup() {
    Main.setIsRunning(true);
  }

  @Test
  public void percolationTest() {
    CellView testCell = lookup("#1,3").query();
    assertTrue(testCell.getFill() == Color.WHITE);

    javafxRun(() -> myGame.step(model[0], display[0]));

    assertTrue(testCell.getFill() == Color.BLUE);
  }

  @Test
  public void clickPercolation() {
    Main.setIsRunning(false);
    CellView testCell1 = lookup("#0,0").query();

    assertTrue(testCell1.getFill() == Color.BLACK);

    clickOn(testCell1);

    assertTrue(testCell1.getFill() == Color.WHITE);
  }
}
