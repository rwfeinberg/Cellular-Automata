package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Kenneth Marenco
 * <p>
 * Purpose - The WaTorCell.java class represents the type of cell specific to the Wa-Tor World model
 * of predator-prey relationships Simulation. It holds the simulation's rules to update the cell's
 * state each step, based on its own state and its neighbors' states. It also holds the colors the
 * cell would show in the view side, but does not do any visualization tasks as required.
 * <p>
 * Assumptions - This class extends Cell.java, assumes initial state will be a valid value
 * <p>
 * Dependencies - This class depends on Cell.java as it is a subclass that extends the Cell class
 */
public class WaTorCell extends Cell {

  private final int REPRODUCTION_NUMBER = 3;
  private String myNeighborType;
  public final int SHARK_STATE = 2;
  public final int FISH_STATE = 1;
  public final int WATER_STATE = 0;
  public final int GAIN_ENERGY = 2;
  public int energy = 1;
  public int lifespan = 0;
  private int myState;
  private int myTempState;
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
  public WaTorCell(int state, int rowId, int colId, String neighborType, String edgePolicy) {
    super(state, rowId, colId, neighborType, edgePolicy);
    colors.put(0, "WHITE");
    colors.put(1, "GREEN");
    colors.put(2, "BLUE");
    myState = state;
    myTempState = state;
    myNeighborType = neighborType;
  }

  private int getNumberOfWater() {
    int numberOfWaterCells = 0;
    for (Cell neighbor : this.getMyNeighbors()) {
      if (neighbor.getMyState() == WATER_STATE) {
        numberOfWaterCells++;
      }
    }
    return numberOfWaterCells;
  }

  /**
   * Update cell's state based on simulation's rules
   */
  @Override
  public void updateState() {
    if (getMyState() == FISH_STATE) {
      updateFish();
    } else if (getMyState() == SHARK_STATE) {
      updateShark();
    }
  }

  //TODO: reduce duplication between update methods and shorten
  private void updateShark() {
    if (getNumberOfFish() != 0) {
      Random rand = new Random();
      int cellIndex = rand.nextInt(getNumberOfFish());
      List<Cell> myFishyNeighbors = fishFilledNeighbors();
      WaTorCell selectedCell = (WaTorCell) myFishyNeighbors.get(cellIndex);
      selectedCell.setMyTempState(SHARK_STATE);
      this.setEnergy(energy + GAIN_ENERGY);
      //selectedCell.setLifespan(this.lifespan);
      selectedCell.setEnergy(this.energy);
      this.setEnergy(0);
      if (lifespan == REPRODUCTION_NUMBER) {
        this.setMyTempState(SHARK_STATE);
        lifespan = 0;
      } else {
        this.setMyTempState(WATER_STATE);
      }
    } else if (getNumberOfWater() != 0 && getNumberOfFish() == 0) {
      Random rand = new Random();
      int cellIndex = rand.nextInt(getNumberOfWater());
      List<Cell> myValidNeighbors = fishFriendlyNeighbors();
      Cell selectedCell = myValidNeighbors.get(cellIndex);
      selectedCell.setMyTempState(SHARK_STATE);
    }
    energy--;
    if (energy == 0) {
      this.setMyTempState(WATER_STATE);
    }
    if (lifespan != REPRODUCTION_NUMBER) {
      lifespan++;
    }
  }

  private void setEnergy(int newEnergy) {
    this.energy = newEnergy;
  }

  private List<Cell> fishFilledNeighbors() {
    List<Cell> myValidNeighbors = new ArrayList<>();
    for (Cell neighbor : this.getMyNeighbors()) {
      if (neighbor.getMyState() == FISH_STATE) {
        myValidNeighbors.add(neighbor);
      }
    }
    return myValidNeighbors;
  }

    /*//TODO: double check this increases the sharks energy
    private void setEnergy(Cell myCell, Cell selectedCell) { energy = energy + GAIN_ENERGY; }*/

  private void updateFish() {
    if (getNumberOfWater() != 0) {
      Random rand = new Random();
      int cellIndex = rand.nextInt(getNumberOfWater());
      List<Cell> myValidNeighbors = fishFriendlyNeighbors();
      Cell selectedCell = myValidNeighbors.get(cellIndex);
      selectedCell.setMyTempState(FISH_STATE);
      if (lifespan == REPRODUCTION_NUMBER) {
        this.setMyTempState(FISH_STATE);
        lifespan = 0;
      } else {
        this.setMyTempState(WATER_STATE);
      }
    } else {
      this.setMyTempState(FISH_STATE);
    }
    if (lifespan != REPRODUCTION_NUMBER) {
      lifespan++;
    }
  }

  private List<Cell> fishFriendlyNeighbors() {
    List<Cell> myValidNeighbors = new ArrayList<>();
    for (Cell neighbor : this.getMyNeighbors()) {
      if (neighbor.getMyState() == WATER_STATE) {
        myValidNeighbors.add(neighbor);
      }
    }
    return myValidNeighbors;
  }

  private int getNumberOfFish() {
    int numberOfFishCells = 0;
    for (Cell neighbor : this.getMyNeighbors()) {
      if (neighbor.getMyState() == FISH_STATE) {
        numberOfFishCells++;
      }
    }
    return numberOfFishCells;
  }


  /**
   * change state to 'next' state when called
   */
  @Override
  public void rotateState() {
    List<Integer> states = List.of(SHARK_STATE, FISH_STATE, WATER_STATE);
    int max = Collections.max(states);
    if (this.getMyState() == max) {
      this.setMyTempState(0);
    } else {
      this.setMyTempState(this.getMyState() + 1);
    }
  }

  /**
   * @return cell's color based on the state
   */
  public String getColor() {
    return colors.get(this.getMyState());
  }

  /**
   * @return temporary state value
   */
  @Override
  public int getMyTempState() {
    return myTempState;
  }

  /**
   * update state with the temporary state
   */
  @Override
  public void setTempToOld() {
    myState = myTempState;
  }

  /**
   * set temporary state to value
   * @param state
   */
  @Override
  public void setMyTempState(int state) {
    myTempState = state;
  }

  /**
   * @return state's value
   */
  @Override
  public int getMyState() {
    return myState;
  }
}
