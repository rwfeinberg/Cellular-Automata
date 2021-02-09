package cellsociety;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import model.Cell;
import model.Grid;
import org.junit.jupiter.api.Test;

public class GridTest {

  public static final String TEST_FILE = "GameOfLife.properties";

  @Test
  public void testGridUpdater() throws FileNotFoundException {
    Grid grid = new Grid(Main.RESOURCES + TEST_FILE);
    grid.updateGrid();

    List<List<Cell>> cells = grid.getGrid();

    assertEquals(0, cells.get(0).get(0).getMyState());
    assertEquals(0, cells.get(0).get(9).getMyState());
    assertEquals(1, cells.get(0).get(4).getMyState());
    assertEquals(1, cells.get(1).get(4).getMyState());
  }

}
