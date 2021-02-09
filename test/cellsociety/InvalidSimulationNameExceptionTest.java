package cellsociety;

import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class InvalidSimulationNameExceptionTest extends DukeApplicationTest {

  @Test
  public void invalidSimulationNameTest(){
    //call makeGrid here to throw exception
  }

  private void makeGrid(){
    Grid grid = new Grid("resources/InvalidGameType.properties");
  }
}
