package cellsociety;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import model.Cell;
import model.GameOfLifeCell;
import org.junit.jupiter.api.Test;

public class NeighborCornerTest {

  private String myNeighborType = "Corners";
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
    Cell firstCell = sampleCells.get(1).get(1);

    //retrieve neighbor cells
    firstCell.findNeighbors(sampleCells);
    assertEquals(4, firstCell.getMyNeighbors().size());

  }

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
