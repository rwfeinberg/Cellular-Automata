package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Muazzam Khan Noorpuri
 * <p>
 * Purpose - The GameOfLifeCell.java class represents the type of cell specific to the Game of Life
 * Simulation. It holds the simulation's rules to update the cell's state each step, based on its
 * own state and its neighbors' states. It also holds the colors the cell would show in the view
 * side, but does not do any visualization tasks as required.
 * <p>
 * Assumptions - This class extends Cell.java, assumes initial state will be a valid value
 * <p>
 * Dependencies - This class depends on Cell.java as it is a subclass that extends the Cell class
 */
public class GameOfLifeCell extends Cell {

  public final int ALIVE_STATE = 1;
  public final int DEAD_STATE = 0;
  private Map<Integer, String> colors = new HashMap<>();
  private String myNeighborType;
  private String myEdgePolicy;

  /**
   * Constructor, sets up the cell object, saves the color-state preferences, sets the state and
   * NeighborType
   * @param state
   * @param rowId
   * @param colId
   * @param neighborType
   * @param edgePolicy
   */
  public GameOfLifeCell(int state, int rowId, int colId, String neighborType, String edgePolicy) {
    super(state, rowId, colId, neighborType, edgePolicy);
    colors.put(DEAD_STATE, "BLACK");
    colors.put(ALIVE_STATE, "YELLOW");
    myNeighborType = neighborType;
  }

  /**
   * Update cell's state based on simulation's rules
   */
  @Override
  public void updateState() {
    int numberAliveNeighbors = getNumberAliveNeighbors();

    if (cellIsAlive() && (numberAliveNeighbors != 2 && numberAliveNeighbors != 3)) {
      this.setMyTempState(DEAD_STATE);
    } else if (cellIsDead() && numberAliveNeighbors == 3) {
      this.setMyTempState(ALIVE_STATE);
    } else {
      this.setMyTempState(this.getMyState());
    }
  }


  private boolean cellIsAlive() {
    return this.getMyState() == ALIVE_STATE;
  }

  private boolean cellIsDead() {
    return this.getMyState() == DEAD_STATE;
  }

  private int getNumberAliveNeighbors() {
    int numberAliveNeighbors = 0;
    for (Cell Neighbor : this.getMyNeighbors()) {
      if (Neighbor.getMyState() == ALIVE_STATE) {
        numberAliveNeighbors++;
      }
    }
    return numberAliveNeighbors;
  }

  /**
   * @return the cell's color based on the state
   */
  public String getColor() {
    return colors.get(this.getMyState());
  }

  /**
   * change state to 'next' state when called
   */
  @Override
  public void rotateState() {
    List<Integer> states = List.of(ALIVE_STATE, DEAD_STATE);
    int max = Collections.max(states);
    if (this.getMyState() == max) {
      this.setMyTempState(0);
    } else {
      this.setMyTempState(this.getMyState() + 1);
    }
  }
}
