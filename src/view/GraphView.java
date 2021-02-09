package view;

import java.util.List;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import model.Cell;
import model.Grid;


/**
 * @author Muazzam Khan-Noorpuri
 * <p>
 * Purpose - The GraphView.java class represents the dynamic population graph present in the middle
 * of the simulation's window. This graph shows a real-time comparison of the population of each
 * type of cell in the current graph.
 * <p>
 * Assumptions - All cell states should be 0,1,2 as stated in README, since values were hardcoded.
 * <p>
 * Dependencies - This class depends on Cell.java in order to iterate through each cell in grid, and
 * Grid.java for parameter of the grid.
 */
public class GraphView {

  private final LineChart<Number, Number> lineChart;

  /**
   * Constructor, creates the line graph and 3 data series (one for each cell type). It also
   * initializes the first data point at time = 0.
   *
   * @param grid - instance of the grid
   */
  public GraphView(Grid grid) {
    NumberAxis xAxis = new NumberAxis();
    xAxis.setLabel("Time");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Population");
    lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setCreateSymbols(false);

    XYChart.Series<Number, Number> data1 = new XYChart.Series<>();
    data1.setName("Cell State 0");
    XYChart.Series<Number, Number> data2 = new XYChart.Series<>();
    data2.setName("Cell State 1");
    XYChart.Series<Number, Number> data3 = new XYChart.Series<>();
    data3.setName("Cell State 2");

    initializeData(grid, 0, data1);
    initializeData(grid, 1, data2);
    initializeData(grid, 2, data3);
  }

  private void initializeData(Grid grid, int state, XYChart.Series<Number, Number> series) {
    int emptyPopulation = 0;
    for (List<Cell> row : grid.getGrid()) {
      for (Cell cell : row) {
        if (cell.getMyState() == state) {
          emptyPopulation++;
        }
      }
    }
    series.getData().add(new Data<>(0, emptyPopulation));
    lineChart.getData().add(series);
  }

  /**
   * This method is used to return the actual line chart created in the parameter
   *
   * @return - linechart
   */
  public LineChart<Number, Number> getLineChart() {
    return lineChart;
  }
}
