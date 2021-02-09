package model;

import cellsociety.Main;
import cellsociety.MissingKeyException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author Ryan Feinberg, Muazzam Khan-Noorpuri
 * <p>
 * Purpose - The Grid.java class represents the model version of the Grid. It is responsible for
 * getting the important information from the relevant properties file, calling the grid setup and
 * then updating the cells in the grid
 * <p>
 * Assumptions - This class assumes the gridSetup and cell-creation is completed correctly
 * <p>
 * Dependencies - This class depends on Cell.java in order to retrieve/store its cells in the grid,
 * as well as GridSetup.java to setup the original layout of the grid.
 */
public class Grid {


  private List<List<Cell>> myGrid;

  public Properties getProperties() {
    return this.myProperties;
  }

  private Properties myProperties;
  private Properties exceptionMessages;


  /**
   * Constructor, gets the relevant information for setup from the properties files. It then sets
   * up the grid base on this data.
   *
   * @param properties
   */
  public Grid(String properties) {
    createExceptionMessages();
    myProperties = initializeProperties(properties);

    String gameType = myProperties.getProperty("GameType");
    String fileName = myProperties.getProperty("FileName");
    String neighborType = myProperties.getProperty("NeighborType");
    String edgePolicy = myProperties.getProperty("EdgePolicy");

    GridSetup s = new GridSetup();
    myGrid = s.makeGrid(fileName, gameType, neighborType, edgePolicy, exceptionMessages);
  }

  /**
   * Update the cells in the grid based on their rules
   */
  public void updateGrid() {
    for (List<Cell> row : myGrid) {
      for (Cell c : row) {
        c.updateState();
      }
    }

    for (List<Cell> row : myGrid) {
      for (Cell c : row) {
        c.setTempToOld();
      }
    }
  }

  private Properties initializeProperties(String path) {
    Properties initialSettings = new Properties();
    try {
      initialSettings
          .load(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (isMissingKey(initialSettings)) {
      try {
        throw new MissingKeyException(
            exceptionMessages.get("MissingKey") + Main.GAME_TYPE_KEY + ", " + Main.TITLE_KEY + ", "
                + Main.DESCRIPTION_KEY + ", " + Main.FILE_NAME_KEY + ", " + Main.AUTHOR_KEY
                + " in file: " + path);
      } catch (MissingKeyException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
    return initialSettings;
  }

  private boolean isMissingKey(Properties initialSettings) {
    return !initialSettings.containsKey(Main.GAME_TYPE_KEY) || !initialSettings
        .containsKey(Main.TITLE_KEY) || !initialSettings.containsKey(Main.DESCRIPTION_KEY)
        || !initialSettings.containsKey(Main.FILE_NAME_KEY) || !initialSettings
        .containsKey(Main.AUTHOR_KEY);
  }

  private void createExceptionMessages() {
    exceptionMessages = new Properties();
    try {
      exceptionMessages.load(Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("resources/ExceptionMessages.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return this grid's set of cells
   */
  public List<List<Cell>> getGrid() {
    return this.myGrid;
  }

}
