package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The RPS.java class represents the type of cell specific to the Rock, Paper, Scissor
 * Simulation. It holds the simulation's rules to update the cell's state each step, based on its
 * own state and its neighbors' states. It also holds the colors the cell would show in the view
 * side, but does not do any visualization tasks as required.
 * <p>
 * Assumptions - This class extends Cell.java, assumes initial state will be a valid value
 * <p>
 * Dependencies - This class depends on Cell.java as it is a subclass that extends the Cell class
 */
public class RPSCell extends Cell {

  //Number of enemy neighbors needed to overtake current cell
  public static final int THRESHOLD_ENEMY_NUMBER = 2;
  public final int ROCK_STATE = 0;
  public final int PAPER_STATE = 1;
  public final int SCISSORS_STATE = 2;
  public String myNeighborType;
  public String myEdgePolicy;

  private Map<Integer, String> colors = new HashMap<>();

  /**
   * Constructor, sets up the cell object, saves the color-state preferences, sets the NeighborType
   * @param state
   * @param rowId
   * @param colId
   * @param neighborType
   * @param edgePolicy
   */
  public RPSCell(int state, int rowId, int colId, String neighborType, String edgePolicy) {
    super(state, rowId, colId, neighborType, edgePolicy);
    colors.put(0, "GREY");
    colors.put(1, "YELLOW");
    colors.put(2, "GREEN");
    myNeighborType = neighborType;
  }

  /**
   * Update cell's state based on simulation's rules
   */
  @Override
  public void updateState() {
    int numberEnemyNeighbors = getNumberEnemyNeighbors();

    if (numberEnemyNeighbors > THRESHOLD_ENEMY_NUMBER) {
      this.setMyTempState(this.getEnemyState());
    } else {
      this.setMyTempState(this.getMyState());
    }
  }

  private int getNumberEnemyNeighbors() {
    int numberEnemyNeighbors = 0;
    int enemyState = getEnemyState();

    for (Cell Neighbor : this.getMyNeighbors()) {
      if (Neighbor.getMyState() == enemyState) {
        numberEnemyNeighbors++;
      }
    }

    return numberEnemyNeighbors;
  }

  private int getEnemyState() {
    int enemyState = -1;

    switch (this.getMyState()) {
      case ROCK_STATE -> {
        enemyState = PAPER_STATE;
      }
      case PAPER_STATE -> {
        enemyState = SCISSORS_STATE;
      }
      case SCISSORS_STATE -> {
        enemyState = ROCK_STATE;
      }
    }
    return enemyState;
  }

  /**
   * change state to 'next' state when called
   */
  @Override
  public void rotateState() {
    List<Integer> states = List.of(ROCK_STATE, PAPER_STATE, SCISSORS_STATE);
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
  @Override
  public String getColor() {
    return colors.get(this.getMyState());
  }
}
