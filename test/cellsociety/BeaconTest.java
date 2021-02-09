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

public class BeaconTest extends DukeApplicationTest {

  private BorderPane root = new BorderPane();
  private Grid[] model = new Grid[1];
  private SimulationView[] display = new SimulationView[1];

  private Main myGame = new Main();

  @Override
  public void start(Stage primaryStage) {
    Grid grid = new Grid("resources/beacon6_6.properties");
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
  public void beaconTest() {
    CellView testCell = lookup("#2,2").query();
    assertTrue(testCell.getFill() == Color.BLACK);

    javafxRun(() -> myGame.step(model[0], display[0]));

    assertTrue(testCell.getFill() == Color.YELLOW);
  }
}
