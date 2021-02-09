package model;

import cellsociety.InvalidSimulationNameException;
import cellsociety.UnexpectedGridDimensionsException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Muazzam Khan-Noorpuri, Ryan Feinberg
 * <p>
 * Purpose - The GridSetup.java class is responsible for setting up the Grid.java class based on
 * the information fed in from the properties file
 * <p>
 * Assumptions - This class deals with errors in the properties files with custom exceptions
 * <p>
 * Dependencies - This class depends on Cell.java and all the specific types of simulation cells in
 * order to make instances of each cell to fill the grid.
 */
public class GridSetup {

  private List<List<Cell>> myGrid;
  private Properties myExceptionMessages;

  /**
   * Setup the grid based on the information from the properties file passed in.
   * @param pathname
   * @param simulationType
   * @param neighborType
   * @param edgePolicy
   * @param exceptionMessages
   * @return the set of cells created in the setup
   */
  public List<List<Cell>> makeGrid(String pathname, String simulationType, String neighborType,
      String edgePolicy, Properties exceptionMessages) {
    File configFile = new File(pathname);
    myExceptionMessages = exceptionMessages;

    Scanner scanner = null;
    try {
      scanner = new Scanner(configFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String[] header = scanner.nextLine().split(",");

    int rowSize = Integer.parseInt(header[0]);
    int columnSize = Integer.parseInt(header[1]);

    myGrid = new ArrayList<>();

    fillGrid(simulationType, neighborType, edgePolicy, scanner, rowSize, columnSize, configFile);

    return myGrid;
  }

  //TODO: to complex, split it up to make simpler
  private void fillGrid(String simulationType, String neighborType, String edgePolicy,
      Scanner scanner, int rowSize, int columnSize, File file) {
    checkDimensions(rowSize, columnSize, file);

    for (int row = 0; row < rowSize; row++) {
      String[] currentRow = scanner.nextLine().split(",");
      myGrid.add(new ArrayList<>());

      for (int col = 0; col < columnSize; col++) {
        int state = Integer.parseInt(currentRow[col]);
        makeCell(simulationType, neighborType, edgePolicy, state, row, col);
      }
    }

    //must be done in separate for loop because all cells must be created before finding neighbors
    for (List<Cell> row : myGrid) {
      for (Cell cell : row) {
        cell.findNeighbors(myGrid);
      }
    }
  }


  private void checkDimensions(int rowSize, int columnSize, File file) {
    Scanner tempScanner = null;
    try {
      tempScanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    checkColumns(columnSize, tempScanner);
    checkRows(rowSize, tempScanner);
  }

  private void checkRows(int rowSize, Scanner tempScanner) {
    int ActualRows = 1;
    while (tempScanner.hasNextLine()) {
      tempScanner.nextLine();
      ActualRows++;
    }
    if (ActualRows != rowSize) {
      try {
        throw new UnexpectedGridDimensionsException("" + myExceptionMessages.get("UnexpectedRows"));
      } catch (UnexpectedGridDimensionsException e) {
        e.printStackTrace();
      }
    }
  }

  private void checkColumns(int columnSize, Scanner tempScanner) {
    tempScanner.nextLine();
    String[] currentRow = tempScanner.nextLine().split(",");
    if (currentRow.length != columnSize) {
      try {
        throw new UnexpectedGridDimensionsException(
            "" + myExceptionMessages.get("UnexpectedColumns"));
      } catch (UnexpectedGridDimensionsException e) {
        e.printStackTrace();
      }
    }
  }


  private void makeCell(String simulationType, String neighborType, String edgePolicy, int state,
      int row, int col) {
    Cell cell = null;
    try {
      switch (simulationType) {
        case "GameOfLife" -> cell = new GameOfLifeCell(state, row, col, neighborType, edgePolicy);
        case "Percolation" -> cell = new PercolationCell(state, row, col, neighborType, edgePolicy);
        case "RPS" -> cell = new RPSCell(state, row, col, neighborType, edgePolicy);
        case "ForestFire" -> cell = new FireCell(state, row, col, neighborType, edgePolicy);
        case "WaTor" -> cell = new WaTorCell(state, row, col, neighborType, edgePolicy);
        case "Schelling" -> cell = new SchellingCell(state, row, col, neighborType, edgePolicy);
        default -> {
          cell = new GameOfLifeCell(state, row, col, neighborType, edgePolicy);
          throw new InvalidSimulationNameException(
              myExceptionMessages.get("InvalidSimulationName") + simulationType);
        }
      }
    } catch (InvalidSimulationNameException e) {
    }
    myGrid.get(row).add(cell);
  }
}
