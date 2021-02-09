package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Muazzam Khan Noorpuri
 * <p>
 * Purpose - The Schelling.java class represents the type of cell specific to the Schelling
 * Simulation. It holds the simulation's rules to update the cell's state each step, based on its
 * own state and its neighbors' states. It also holds the colors the cell would show in the view
 * side, but does not do any visualization tasks as required.
 * <p>
 * Assumptions - This class extends Cell.java, assumes initial state will be a valid value
 * <p>
 * Dependencies - This class depends on Cell.java as it is a subclass that extends the Cell class
 */
public class SchellingCell extends Cell {

  public final double SATIFACTION_REQ = 0.4;
  public final int SECOND_POPULATION = 2;
  public final int FIRST_POPULATION = 1;
  public final int EMPTY_STATE = 0;
  private Map<Integer, String> colors = new HashMap<>();
  private String myNeighborType;

  /**
   * Constructor, sets up the cell object, saves the color-state preferences, sets the NeighborType
   * @param state
   * @param rowId
   * @param colId
   * @param neighborType
   * @param edgePolicy
   */
  public SchellingCell(int state, int rowId, int colId, String neighborType, String edgePolicy) {
    super(state, rowId, colId, neighborType, edgePolicy);
    colors.put(EMPTY_STATE, "WHITE");
    colors.put(FIRST_POPULATION, "BLUE");
    colors.put(SECOND_POPULATION, "RED");
    myNeighborType = neighborType;
  }

  /**
   * Update cell's state based on simulation's rules
   */
  @Override
  public void updateState() {
    double satisfaction = getPercentNeighborsOfSameType();
    if (satisfaction < SATIFACTION_REQ && this.getMyState() != EMPTY_STATE) {
      moveCell();
    } else if (this.getMyTempState() == -1) {
      this.setMyTempState(this.getMyState());
    }
  }

  private double getPercentNeighborsOfSameType() {
    double numberOfNeighbors = 0;
    double numberOfSameNeighbors = 0;
    for (Cell Neighbor : this.getMyNeighbors()) {
      if (Neighbor.getMyState() != EMPTY_STATE) {
        numberOfNeighbors++;
      }
      if (Neighbor.getMyState() == this.getMyState()) {
        numberOfSameNeighbors++;
      }
    }
    if (numberOfNeighbors != 0) {
      return numberOfSameNeighbors / numberOfNeighbors;
    } else {
      return 0;
    }
  }

  private void moveCell() {
    boolean moved = false;
    for (Cell Neighbor : this.getMyNeighbors()) {
      if (Neighbor.getMyTempState() == EMPTY_STATE ||
          (Neighbor.getMyState() == EMPTY_STATE && Neighbor.getMyTempState() == -1)) {
        Neighbor.setMyTempState(this.getMyState());
        this.setMyTempState(EMPTY_STATE);
        moved = true;
        break;
      }
    }
    if (!moved) {
      this.setMyTempState(this.getMyState());
    }
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
    List<Integer> states = List.of(SECOND_POPULATION, FIRST_POPULATION, EMPTY_STATE);
    int max = Collections.max(states);
    if (this.getMyState() == max) {
      this.setMyTempState(0);
    } else {
      this.setMyTempState(this.getMyState() + 1);
    }
  }
}
