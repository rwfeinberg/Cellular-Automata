package view;

import cellsociety.Main;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Grid;

/**
 * @author Kenneth Marenco, Muazzam Khan-Noorpuri
 * <p>
 * Purpose - The GridView.java class represents the dynamic grid visualization present in the center
 * of the simulation's window, below the population graph. This class shows the user each cell
 * colored in to match its state.
 * <p>
 * Assumptions - The WIDTH constant in Main should not be too big, as the positioning of the graph
 * may become disjointed.
 * <p>
 * Dependencies - This class depends on Main.java for constants, Cell.java in order to access the
 * cells in grid, CellView.java in order to associate linkedCells and place the visual cell
 * representations into a gridpane, and Grid.java as a parameter of the grid.
 */
public class GridView {

  GridPane myNode;
  private List<List<CellView>> physicalCells = new ArrayList<>();
  private final int GAP_SIZE = 1;
  private final int PADDING_SIZE = 20;

  /**
   * Constructor, initializes and fills gridpane of cells to be displayed. Also associates
   * cellview's with cells.
   *
   * @param Grid - grid instance
   */
  public GridView(Grid Grid) {
    myNode = new GridPane();
    myNode.setHgap(GAP_SIZE);
    myNode.setVgap(GAP_SIZE);
    myNode.setAlignment(Pos.CENTER);
    myNode.setPadding(new Insets(0, -PADDING_SIZE, PADDING_SIZE / 2, -PADDING_SIZE));

    int cellsPerRow = Grid.getGrid().get(0).size();
    int cellsPerCol = Grid.getGrid().size();

    int cellWidth =
        ((Main.WIDTH - ((cellsPerRow - 1) * (GAP_SIZE)) - (2 * PADDING_SIZE)) / cellsPerRow) / 2;
    int cellHeight =
        ((Main.HEIGHT - (cellsPerCol - 1) * (GAP_SIZE) - (2 * PADDING_SIZE)) / cellsPerCol) / 2;

    int cellSize = Math.min(cellWidth, cellHeight);

    for (int i = 0; i < Grid.getGrid().size(); i++) {
      List<CellView> row = new ArrayList<>();
      for (int j = 0; j < Grid.getGrid().get(0).size(); j++) {
        Cell currentCell = Grid.getGrid().get(i).get(j);
        CellView cell = new CellView(cellSize, cellSize, currentCell);

        GridPane.setRowIndex(cell, i);
        GridPane.setColumnIndex(cell, j);
        row.add(cell);
        cell.setId(i + "," + j);
        myNode.getChildren().add(cell);
      }
      physicalCells.add(row);
    }
  }

  /**
   * This is used to retrieve the actual gridpane created in the constructor.
   *
   * @return - gridpane
   */
  public Node getMyNode() {
    return myNode;
  }

  /**
   * This method is used to return the list of visual cells, used to initialize the instance
   * variable in SimulationView
   *
   * @return
   */
  public List<List<CellView>> getPhysicalCells() {
    return physicalCells;
  }
}
