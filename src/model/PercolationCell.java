package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Muazzam Khan Noorpuri
 * <p>
 * Purpose - The Percolation.java class represents the type of cell specific to the Percolation
 * Simulation. It holds the simulation's rules to update the cell's state each step, based on its
 * own state and its neighbors' states. It also holds the colors the cell would show in the view
 * side, but does not do any visualization tasks as required.
 * <p>
 * Assumptions - This class extends Cell.java, assumes initial state will be a valid value
 * <p>
 * Dependencies - This class depends on Cell.java as it is a subclass that extends the Cell class
 */
//TODO: change grid to a local variable
public class PercolationCell extends Cell {

  private List<List<Cell>> myGrid;
  private int myState;
  private int myTempState;
  public final int BLOCKED_STATE = 0;
  public final int EMPTY_STATE = 1;
  public final int FULL_STATE = 2;

  private boolean isConnected = false;
  private List<Cell> connectedNeighbors;
  private String myNeighborType;
  private String myEdgePolicy;

  private Map<Integer, String> colors = new HashMap<>();

  /**
   * Constructor, sets up the cell object, saves the color-state preferences, sets the state,
   * tempState and NeighborType
   * @param state
   * @param rowId
   * @param colId
   * @param neighborType
   * @param edgePolicy
   */
  public PercolationCell(int state, int rowId, int colId, String neighborType, String edgePolicy) {
    super(state, rowId, colId, neighborType, edgePolicy);
    colors.put(0, "BLACK");
    colors.put(1, "WHITE");
    colors.put(2, "BLUE");
    myTempState = state;
    myState = state;
    myNeighborType = neighborType;
  }

  /**
   * Update cell's state based on simulation's rules
   */
  @Override
  public void updateState() {
    if (cellIsFull()) {
      for (Cell neighbor : this.getMyNeighbors()) {
        dfs(neighbor);
      }
    }
  }

  private void dfs(Cell cell) {
    if (cell.getMyTempState() == BLOCKED_STATE) {
      return;
    }
    if (cell.getMyTempState() == FULL_STATE) {
      return;
    }

    cell.setMyTempState(FULL_STATE);

    for (Cell neighbor : cell.getMyNeighbors()) {
      dfs(neighbor);
    }
  }


  private boolean cellIsBlocked() {
    return this.getMyState() == BLOCKED_STATE;
  }
  private boolean cellIsEmpty() {
    return this.getMyState() == EMPTY_STATE;
  }
  private boolean cellIsFull() {
    return this.getMyState() == FULL_STATE;
  }


  /**
   * change state to 'next' state when called
   */
  @Override
  public void rotateState() {
    List<Integer> states = List.of(BLOCKED_STATE, EMPTY_STATE, FULL_STATE);
    int max = Collections.max(states);
    if (this.getMyState() == max) {
      this.setMyTempState(0);
    } else {
      this.setMyTempState(this.getMyState() + 1);
    }
  }

  /**
   * @return the cell's color based on the state
   */
  public String getColor() {
    return colors.get(this.getMyState());
  }

  /**
   * @return the temporary state
   */
  @Override
  public int getMyTempState() {
    return myTempState;
  }

  /**
   * update the state with the temporary state
   */
  @Override
  public void setTempToOld() {
    myState = myTempState;
  }

  /**
   * set the temporary state to a value
   * @param state
   */
  @Override
  public void setMyTempState(int state) {
    myTempState = state;
  }

  /**
   * @return myState
   */
  @Override
  public int getMyState() {
    return myState;
  }
}
