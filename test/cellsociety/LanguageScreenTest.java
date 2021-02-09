package cellsociety;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.LanguageSplashScreen;
import view.SimulationView;

public class LanguageScreenTest extends DukeApplicationTest {

  private Scene myScene;
  private BorderPane root = new BorderPane();
  private Grid[] model = new Grid[1];
  private SimulationView[] display = new SimulationView[1];

  private Button myStartButton;

  @Override
  public void start(Stage primaryStage) {
    Grid grid = new Grid("resources/GameOfLife.properties");
    LanguageSplashScreen l = new LanguageSplashScreen();
    String language = l.makePopUp();
    SimulationView s = new SimulationView(root, display, model, grid, language);
    myScene = new Scene(root, Main.WIDTH, Main.HEIGHT);

    primaryStage.setScene(myScene);
    primaryStage.show();

    myStartButton = lookup("#Start").query();
  }

  @Test
  public void testLanguagePopUp() {
    String text = myStartButton.getText();
    assertTrue(text.equals("Start") || text.equals("Starten") || text.equals("Comienzo"));
  }
}
