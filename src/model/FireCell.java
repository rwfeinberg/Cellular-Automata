package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Kenneth Marenco
 * <p>
 * Purpose - The FireCell.java class represents the type of cell specific to the Spreading Of Fire
 * Simulation. It holds the simulation's rules to update the cell's state each step, based on its
 * own state and its neighbors' states. It also holds the colors the cell would show in the view
 * side, but does not do any visualization tasks as required.
 * <p>
 * Assumptions - This class extends Cell.java, assumes initial state will be a valid value
 * <p>
 * Dependencies - This class depends on Cell.java as it is a subclass that extends the Cell class
 */
public class FireCell extends Cell {

  public final int EMPTY_STATE = 0;
  public final int TREE_STATE = 1;
  public final int BURNING_STATE = 2;
  public final double PROB_CATCH_FIRE = .25;
  private int myState;
  private int myTempState;
  private String myNeighborType;
  private final Map<Integer, String> colors = new HashMap<>();

  /**
   * Constructor, sets up the cell object, saves the color-state preferences, sets the state,
   * tempState and NeighborType
   * @param state
   * @param rowId
   * @param colId
   * @param neighborType
   * @param edgePolicy
   */
  public FireCell(int state, int rowId, int colId, String neighborType, String edgePolicy) {
    super(state, rowId, colId, neighborType, edgePolicy);
    colors.put(EMPTY_STATE, "YELLOW");
    colors.put(TREE_STATE, "GREEN");
    colors.put(BURNING_STATE, "RED");
    myState = state;
    myTempState = state;
    myNeighborType = neighborType;
  }

  private int getBurningNeighbors() {
    int burningNeighbors = 0;
    for (Cell neighbor : this.getMyNeighbors()) {
      if (neighbor.getMyState() == BURNING_STATE) {
        burningNeighbors++;
      }
    }
    return burningNeighbors;
  }

  /**
   * @return
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
   * set Temporary State to certain value
   * @param state
   */
  @Override
  public void setMyTempState(int state) {
    myTempState = state;
  }

  /**
   * @return state
   */
  @Override
  public int getMyState() {
    return myState;
  }

  /**
   * Update cell's state based on simulation's rules
   */
  @Override
  public void updateState() {
    if (this.getMyState() == TREE_STATE && getBurningNeighbors() != 0) {
      Random rand = new Random();
      double chanceOfFire = 0.0 + (1.0 - 0.0) * rand.nextDouble();
      if (chanceOfFire <= PROB_CATCH_FIRE) {
        this.setMyTempState(BURNING_STATE);
      }
    }
    if (this.getMyState() == BURNING_STATE) {
      this.setMyTempState(EMPTY_STATE);
    }
  }

  /**
   * change state to 'next' state when called
   */
  @Override
  public void rotateState() {
    List<Integer> states = List.of(EMPTY_STATE, TREE_STATE, BURNING_STATE);
    int max = Collections.max(states);
    if (this.getMyState() == max) {
      this.setMyTempState(0);
    } else {
      this.setMyTempState(this.getMyState() + 1);
    }
  }

  /**
   * @return the cell's color
   */
  public String getColor() {
    return colors.get(this.getMyState());
  }
}
