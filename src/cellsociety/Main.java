package cellsociety;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Grid;
import view.CellView;
import view.GraphView;
import view.LanguageSplashScreen;
import view.SimulationView;

/**
 * @author Muazzam Khan-Noorpuri, Kenneth Marenco, Ryan Feinberg
 * <p>
 * Purpose - The Main.java class is responsible for running the program, connecting the model and
 * view portions of the program
 * <p>
 * Assumptions - This class assumes the model and vizualiztion components function correctly
 * <p>
 * Dependencies - This class depends on Grid.java, SimulationView.java (the model and view portions)
 */
public class Main extends Application {

  public static final String TITLE = "Cellular Automata";

  public static final int WIDTH = 700;
  public static final int HEIGHT = WIDTH + 150;

  public static final String CONFIG_FILE_EXTENSION = ".csv";
  public static final String SOURCE_DIRECTORY = "src/";
  public static final String DATA_DIRECTORY = "data/";
  public static final String RESOURCES = "resources/";
  public static final String STYLESHEET_DIRECTORY = "/" + RESOURCES + "styles/";

  //Update these to match properties files key names
  public static final String GAME_TYPE_KEY = "GameType";
  public static final String TITLE_KEY = "Title";
  public static final String AUTHOR_KEY = "Author";
  public static final String DESCRIPTION_KEY = "Description";
  public static final String FILE_NAME_KEY = "FileName";
  public static final String NEIGHBOR_KEY = "NeighborType";

  //These are arrays so that they can be modified in other classes without getters/setters
  private Grid[] model = new Grid[1];
  private SimulationView[] display = new SimulationView[1];

  private static boolean isRunning = false;
  public final int framesPerSecond = 1;
  public final double secondDelay = 1.0 / framesPerSecond;

  private BorderPane root = new BorderPane();

  private static Timeline animation;
  private static KeyFrame frame;

  GraphView plot;

  /**
   * Start method setups the default simulation which is currently GameOfLife.
   * @param primaryStage
   */
  @Override
  public void start(Stage primaryStage) {
    Stage secondaryStage = new Stage();
    //Initialize grid with GameOfLife
    model[0] = new Grid(RESOURCES + "GameOfLife.properties");

    String language = new LanguageSplashScreen().makePopUp();

    //Initialize View (the view sets up the root in its constructor, and the display and model arrays get set inside as well)
    SimulationView s = new SimulationView(root, display, model, model[0], language);

    Scene scene = new Scene(root, WIDTH, HEIGHT);

    scene.getStylesheets()
        .add(getClass().getResource(STYLESHEET_DIRECTORY + "default.css").toExternalForm());

    primaryStage.setTitle(TITLE);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();

    frame = new KeyFrame(Duration.seconds(secondDelay), e -> step(model[0], display[0]));
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  /**
   * A step method updates the simulation by one 'step'. Updating involves both the grid and the graph
   * @param m
   * @param d
   */
  static void step(Grid m, SimulationView d) {
    if (isRunning) {
      m.updateGrid();
      updateColor(d.getPhysicalCells());
      d.updateGraph();
    }
  }

  /**
   * change the FPS of the visualization (ie. how quickly the program steps through the simulation)
   * @param value
   * @param model
   * @param display
   */
  public static void setFPS(int value, Grid[] model, SimulationView[] display) {
    animation.stop();
    frame = new KeyFrame(Duration.seconds(1.0 / value), e -> step(model[0], display[0]));
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private static void updateColor(List<List<CellView>> grid) {
    for (List<CellView> row : grid) {
      for (CellView c : row) {
        c.updateColor();
      }
    }
  }

  /**
   * change the status of whether the program should be running
   * @param status
   */
  public static void setIsRunning(boolean status) {
    isRunning = status;
  }

  /**
   * @return if the program is running
   */
  public static boolean getIsRunning() {
    return isRunning;
  }

  /**
   * Launch the program!
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
