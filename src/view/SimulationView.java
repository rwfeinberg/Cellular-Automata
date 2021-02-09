package view;

import cellsociety.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Cell;

/**
 * @author Ryan Feinberg, Muazzam Khan-Noorpuri, Kenneth Marenco
 * <p>
 * Purpose - The SimulationView.java class represents the entire borderpane used to display all of
 * the simuation in the window.
 * <p>
 * Assumptions -
 * <p>
 * Dependencies - This class depends on Main.java for constants, Cell.java to iterate through the
 * cells in the population graph update, Grid.java to store and instance of the grid, CellView.java
 * to store an instance of the cell visualizations, ControlPanel.java in order to create the control
 * panel node, GraphView.java in order to create the graph linechart, and GridView.java in order to
 * create the gridpane node.
 */
public class SimulationView {

  private Label myInfo;
  private model.Grid myModel;
  private List<List<CellView>> physicalCells = new ArrayList<>();
  private LineChart<Number, Number> lineChart;
  private int myTime = 1;
  private Properties myProperties;
  public GraphView graph;

  /**
   * Constructor, clears the root and sets up scene of the stage with all Nodes.
   *
   * @param root     - instance of the view root
   * @param model    - array containing model instance
   * @param display  - instance of the view
   * @param m        - instance of the actual model
   * @param language - configuration language obtained from splash screen
   */
  public SimulationView(BorderPane root, SimulationView[] display, model.Grid[] model, model.Grid m,
      String language) {
    //Clear root from previous display
    root.getChildren().clear();
    //Set myModel instance variable to the grid created
    myModel = m;
    //Set model array in Main to current model, so that step can keep track of it
    model[0] = myModel;

    myProperties = myModel.getProperties();
    createScene(root, myModel, language, display, model);

    //Set display array in Main to this Simulation View, so that step can keep track of it
    display[0] = this;
  }


  /**
   * This method adds all required nodes to the scene along with their ids, and aligns the
   * borderpane slightly.
   *
   * @param root     - instance of the view root
   * @param grid     - instance of the grid
   * @param language - configuration language obtained from splash screen
   * @param model    - array containing model instance
   * @param display  - instance of the view
   */
  public void createScene(BorderPane root, model.Grid grid, String language,
      SimulationView[] display,
      model.Grid[] model) {
    Node NavigationPanel = new ControlPanel(root, language, display, model).getMyNode();

    String displayText =
        myProperties.getProperty(Main.TITLE_KEY) + ": " + myProperties
            .getProperty(Main.DESCRIPTION_KEY);
    Node infoPanel = makeInfoPanel(displayText);

    VBox center = new VBox();

    GridView simulationGrid = new GridView(grid);
    Node simulationPanel = simulationGrid.getMyNode();
    physicalCells = simulationGrid.getPhysicalCells();

    graph = new GraphView(grid);
    lineChart = graph.getLineChart();

    center.getChildren().addAll(simulationGrid.getMyNode(), lineChart);
    root.setBottom(NavigationPanel);
    root.setCenter(center);
    root.setTop(infoPanel);

    BorderPane.setAlignment(infoPanel, Pos.CENTER);
    BorderPane.setMargin(infoPanel, new Insets(20, 0, 0, 0));
    BorderPane.setAlignment(simulationPanel, Pos.CENTER);
  }

  private Node makeInfoPanel(String gameName) {
    myInfo = new Label(gameName);
    myInfo.setId("Info");
    return myInfo;
  }

  /**
   * This method updates the population graph with the present populations
   */
  public void updateGraph() {
    XYChart.Series<Number, Number> data1 = lineChart.getData().get(0);
    XYChart.Series<Number, Number> data2 = lineChart.getData().get(1);
    XYChart.Series<Number, Number> data3 = lineChart.getData().get(2);

    updateData(data1, 0);
    updateData(data2, 1);
    updateData(data3, 2);

    myTime++;
  }

  private void updateData(XYChart.Series<Number, Number> series, int state) {
    int emptyPopulation = 0;
    for (List<Cell> row : myModel.getGrid()) {
      for (Cell cell : row) {
        if (cell.getMyState() == state) {
          emptyPopulation++;
        }
      }
    }
    Data<Number, Number> dataPoint = new Data<>(myTime, emptyPopulation);
    series.getData().add(dataPoint);
  }

  /**
   * This method returns the cell visualizations, and is used in Main.java's step method to update
   * the color of each cell
   *
   * @return - list of cell visualizations
   */
  public List<List<CellView>> getPhysicalCells() {
    return physicalCells;
  }
}
