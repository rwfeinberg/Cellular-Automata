package model;

import java.util.ArrayList;
import java.util.List;

/**
 * * @author Muazzam Khan-Noorpuri, Kenneth Marenco
 * <p>
 * Purpose - The Cell.java class represents each cell in the simulation. It is the abstract class
 * from which each type of simulation cell will be created from. It holds the state of the cell as
 * well as finds and holds its neighbors
 * <p>
 * Assumptions - NeighborType entered will be correct
 * <p>
 * Dependencies - This class depends on the input information to set up the cell. No dependencies on
 * other classes
 */
public abstract class Cell {

  private int myState;
  private int myTempState;
  private int myRowId;
  private int myColId;
  private List<Cell> myNeighbors;
  private String myNeighborType;
  private String myEdgePolicy;

  /**
   * Constructor, sets state, temporary state, Row & Column ID, neighborhood type and edge policy
   * @param state
   * @param rowId
   * @param colId
   * @param neighborType
   * @param EdgePolicy
   */
  public Cell(int state, int rowId, int colId, String neighborType, String EdgePolicy) {
    myState = state;
    myTempState = -1;
    myRowId = rowId;
    myColId = colId;
    myNeighborType = neighborType;
    myEdgePolicy = EdgePolicy;
  }

  /**
   * General method to find neighbors, uses switch statement to determine exact set of neighbors
   * based on neighborhood established in the constructor
   * @param grid
   */
  public void findNeighbors(List<List<Cell>> grid) {
    String neighborType = myNeighborType;
    myNeighbors = new ArrayList<>();
    int myRowId = this.getMyRowId();
    int myColId = this.getMyColId();
    //edge policy
    for (int i = myRowId - 1; i <= myRowId + 1; i++) {
      for (int j = myColId - 1; j <= myColId + 1; j++) {
        switch (neighborType) {
          case "All" -> findAllNeighbors(grid, myRowId, myColId, i, j);
          case "Cardinal" -> addCardinalNeighbors(grid, myRowId, myColId, i, j);
          case "Corners" -> addCornerNeighbors(grid, myRowId, myColId, i, j);
        }
      }
    }
  }

  protected void addCardinalNeighbors(List<List<Cell>> grid, int myRowId, int myColId, int i,
      int j) {
    String edgePolicy = myEdgePolicy;
    boolean isLeftCell = (i == myRowId && j == myColId - 1);
    boolean isRightCell = (i == myRowId && j == myColId + 1);
    boolean isTopCell = (i == myRowId + 1 && j == myColId);
    boolean isBotCell = (i == myRowId - 1 && j == myColId);
    if (edgePolicy.equals("finite")) {
      if (cellIsInBounds(grid, i, j)) {
        if (isLeftCell || isRightCell || isTopCell || isBotCell) {
          myNeighbors.add(grid.get(i).get(j));
        }
      }
    }
        /*if (edgePolicy.equals("toroidal")) {
            if(cellIsToroidal(grid, i, j)) {
                if (isLeftCell || isRightCell || isTopCell || isBotCell) {
                    myNeighbors.add(grid.get(i).get(j));
                }
            }
        }
        if (edgePolicy.equals("klein bottle")) {
            if (cardinalKleinBottle(grid, i, j)) {
                if (isLeftCell || isRightCell || isTopCell || isBotCell) {
                    myNeighbors.add(grid.get(i).get(j));
                }
            }
        }*/
  }

  protected void addCornerNeighbors(List<List<Cell>> grid, int myRowId, int myColId, int i, int j) {
    boolean isTopLeftCell = (i == myRowId + 1 && j == myColId - 1);
    boolean isTopRightCell = (i == myRowId + 1 && j == myColId + 1);
    boolean isBotLeftCell = (i == myRowId - 1 && j == myColId - 1);
    boolean isBotRightCell = (i == myRowId - 1 && j == myColId + 1);
    if (cellIsInBounds(grid, i, j)) {
      if (isTopLeftCell || isTopRightCell || isBotLeftCell || isBotRightCell) {
        myNeighbors.add(grid.get(i).get(j));
      }
    }
  }

  protected void findAllNeighbors(List<List<Cell>> grid, int myRowId, int myColId, int i, int j) {
    if (cellIsInBounds(grid, i, j)) {
      myNeighbors.add(grid.get(i).get(j));
    }
  }

  protected boolean cellIsInBounds(List<List<Cell>> grid, int i, int j) {
    return !(i < 0 || j < 0 || i >= grid.size() || j >= grid.get(0).size()) && !(i == myRowId
        && j == myColId);
  }

  /**
   * Update cell's state based on simulation's rules
   */
  public abstract void updateState();

  /**
   * @return cell's current color
   */
  public abstract String getColor();

  /**
   * change state to 'next' state when called
   */
  public abstract void rotateState();

  /**
   * update the main state with the temporary state
   */
  public void setTempToOld() {
    myState = myTempState;
  }

  /**
   * @return the cell's list of neighbors
   */
  public List<Cell> getMyNeighbors() {
    return myNeighbors;
  }

  /**
   * @return cell's state
   */
  public int getMyState() {
    return myState;
  }

  /**
   * Set the temporary state
   * @param state
   */
  public void setMyTempState(int state) {
    myTempState = state;
  }

  /**
   * @return the temporary state
   */
  public int getMyTempState() {
    return myTempState;
  }

  /**
   * @return the cell's row ID
   */
  public int getMyRowId() {
    return myRowId;
  }

  /**
   * @return the cell's column ID
   */
  public int getMyColId() {
    return myColId;
  }
}
