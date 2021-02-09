package view.components.menus;

import cellsociety.Main;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import model.Grid;
import view.SimulationView;
import view.components.SimulationMenu;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The GridMenu.java class is the class that represents the Grid Dropdown Menu explained
 * in the README
 * <p>
 * Assumptions - This class extends SimulationMenu.java
 * <p>
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its
 * constructor and methods, as well as Main.java for constants.
 */
public class GridMenu extends SimulationMenu {

  private File myDir = new File(Main.DATA_DIRECTORY + "GameOfLife");

  /**
   * Constructor, adds function
   *
   * @param prop      - String that represents id and property key for label text
   * @param resources - .properties file for grid
   * @param model     - instance of the grid
   * @param display   - instance of the view
   * @param root      - instance of the view root
   * @param language  - configuration language obtained from splash screen
   * @param gridlist  - Instance of File Dropdown Menu
   */
  public GridMenu(String prop, ResourceBundle resources, BorderPane root, String language,
      Grid[] model, SimulationView[] display, SimulationMenu gridlist) {
    super(prop, resources, root, language, model, display, gridlist);
    addFiles();
    this.getSelectionModel()
        .selectedItemProperty()
        .addListener((o, old, nw) -> loadSim(root, display, model, nw.toString(), language));
  }

  /**
   * This method loads in the selected simulation into the grid and display of the root.
   *
   * @param root     - instance of the view root
   * @param display  - instance of the view
   * @param model    - instance of the grid
   * @param sim      - Selected CSV
   * @param language - configuration language obtained from splash screen
   */
  public void loadSim(BorderPane root, SimulationView[] display, Grid[] model, String sim,
      String language) {
    String prop = "resources/" + sim.substring(0, sim.length() - 4) + ".properties";
    Grid grid = new Grid(prop);
    SimulationView s = new SimulationView(root, display, model, grid, language);
  }

  /**
   * Overriden from SimulationMenu
   */
  @Override
  public void addFiles() {
    File[] fileList = myDir.listFiles();
    for (File f : fileList) {
      if (f.getName().contains(Main.CONFIG_FILE_EXTENSION)) {
        this.getItems().add(f.getName());
      }
    }
  }
}
