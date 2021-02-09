package cellsociety;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import model.Cell;
import model.GameOfLifeCell;
import org.junit.jupiter.api.Test;

public class GameOfLifeCellTest {

  private String myNeighborType = "All";
  private String myEdgePolicy = "finite";

  @Test
  public void testCellVariables() {
    Cell test = new GameOfLifeCell(0, 0, 0, myNeighborType, myEdgePolicy);
    test.setMyTempState(1);
    assertEquals(1, test.getMyTempState());
  }

  @Test
  public void testNeighborsAndStateUpdate() {
    //Make test grid
    List<List<Cell>> sampleCells = make3x3Grid();

    //get first and second cells
    Cell firstCell = sampleCells.get(0).get(0);
    Cell secondCell = sampleCells.get(0).get(1);

    //retrieve neighbor cells
    firstCell.findNeighbors(sampleCells);
    secondCell.findNeighbors(sampleCells);

    assertFalse(firstCell.getMyNeighbors().isEmpty());

    //Step forward 1 step, cells should update based on their neighbors
    firstCell.updateState();
    secondCell.updateState();

    for (List<Cell> row : sampleCells) {
      for (Cell c : row) {
        c.setTempToOld();
      }
    }

    assertEquals(0, firstCell.getMyTempState());
    assertEquals(1, secondCell.getMyTempState());

    //Step forward 1 step, cells should update based on their neighbors
    firstCell.updateState();
    secondCell.updateState();

    for (List<Cell> row : sampleCells) {
      for (Cell c : row) {
        c.setTempToOld();
      }
    }

    assertEquals(0, firstCell.getMyState());
    assertEquals(0, secondCell.getMyState());
  }

  /**
   * Creates the following grid of GameOfLifeCells: 1 1 1 0 0 0 0 0 0
   */
  private List<List<Cell>> make3x3Grid() {
    List<List<Cell>> sampleCells = new ArrayList<>();
    List<Cell> row1 = new ArrayList<>();
    List<Cell> row2 = new ArrayList<>();
    List<Cell> row3 = new ArrayList<>();

    row1.add(new GameOfLifeCell(1, 0, 0, myNeighborType, myEdgePolicy));
    row1.add(new GameOfLifeCell(1, 0, 1, myNeighborType, myEdgePolicy));
    row1.add(new GameOfLifeCell(1, 0, 2, myNeighborType, myEdgePolicy));

    row2.add(new GameOfLifeCell(0, 1, 0, myNeighborType, myEdgePolicy));
    row2.add(new GameOfLifeCell(0, 1, 1, myNeighborType, myEdgePolicy));
    row2.add(new GameOfLifeCell(0, 1, 2, myNeighborType, myEdgePolicy));

    row3.add(new GameOfLifeCell(0, 2, 0, myNeighborType, myEdgePolicy));
    row3.add(new GameOfLifeCell(0, 2, 1, myNeighborType, myEdgePolicy));
    row3.add(new GameOfLifeCell(0, 2, 2, myNeighborType, myEdgePolicy));

    sampleCells.add(row1);
    sampleCells.add(row2);
    sampleCells.add(row3);
    return sampleCells;
  }
}
