package cellsociety;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import model.Cell;
import model.GridSetup;
import org.junit.jupiter.api.Test;

public class GridSetupTest {

  public static final String TEST_FILE = "data/GameOfLife/GameOfLife.csv";
  public static final String NEIGHBOR_TYPE = "All";
  public static final int SIMULATION_TYPE = 0;
  public static final String EDGE_POLICY = "finite";

  @Test
  public void testGridCreation() throws FileNotFoundException {
    Properties exceptionMessages = new Properties();
    try {
      exceptionMessages.load(Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("resources/ExceptionMessages.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    GridSetup s = new GridSetup();
    List<List<Cell>> cellGrid = s
        .makeGrid(TEST_FILE, "GameOfLife", NEIGHBOR_TYPE, EDGE_POLICY, exceptionMessages);

    assertTrue(cellGrid.get(0).get(0).getMyState() == 1);
    assertTrue(cellGrid.get(0).get(9).getMyState() == 1);
    assertTrue(cellGrid.get(9).get(0).getMyState() == 0);
    assertTrue(cellGrid.get(9).get(9).getMyState() == 0);


  }

}
