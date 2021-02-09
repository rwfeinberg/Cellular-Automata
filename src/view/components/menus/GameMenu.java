package view.components.menus;

import cellsociety.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import model.Grid;
import view.SimulationView;
import view.components.SimulationMenu;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The GameMenu.java class is the class that represents the Game Dropdown Menu explained
 * in the README
 * <p>
 * Assumptions - This class extends SimulationMenu.java
 * <p>
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its
 * constructor and methods, as well as Main.java for constants.
 */
public class GameMenu extends SimulationMenu {

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
  public GameMenu(String prop, ResourceBundle resources, BorderPane root, String language,
      Grid[] model, SimulationView[] display, SimulationMenu gridlist) {
    super(prop, resources, root, language, model, display, gridlist);
    addFiles();
    this.valueProperty().addListener((o, old, nw) -> chooseSim(nw.toString(), gridlist));
  }

  private void chooseSim(String file, SimulationMenu gridlist) {
    gridlist.setDir(new File(file));
    File[] fileList = gridlist.getDir().listFiles();
    gridlist.getItems().clear();
    for (File f : fileList) {
      if (f.getName().contains(Main.CONFIG_FILE_EXTENSION)) {
        gridlist.getItems().add(f.getName());
      }
    }
  }

  /**
   * Overriden from StyleMenu
   */
  @Override
  public void addFiles() {
    File dir = new File(Main.DATA_DIRECTORY);
    List<File> files = new ArrayList<>();
    for (File f : dir.listFiles()) {
      if (f.isDirectory()) {
        files.add(f);
      }
    }
    Arrays.asList(new File(Main.DATA_DIRECTORY).listFiles());
    this.getItems().addAll(files);
  }
}
