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
 *
 * Purpose - The StyleMenu.java class is the class that represents the Style Dropdown Menu explained in the README
 *
 * Assumptions - This class extends SimulationMenu.java
 *
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its constructor and methods, as well as Main.java for constants.
 *
 */
public class StyleMenu extends SimulationMenu {

  private final String STYLES_DIRECTORY = Main.SOURCE_DIRECTORY+Main.STYLESHEET_DIRECTORY.substring(1, Main.STYLESHEET_DIRECTORY.length()-1);

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
  public StyleMenu(String prop, ResourceBundle resources, BorderPane root, String language, Grid[] model, SimulationView[] display, SimulationMenu gridlist) {
    super(prop, resources, root, language, model, display, gridlist);
    addFiles();
    this.getSelectionModel()
        .selectedItemProperty()
        .addListener((o, old, nw) -> changeStyle(nw.toString(), root));
  }

  private void changeStyle(String nw, BorderPane root) {
    root.getStylesheets().clear();
    root.getStylesheets().add(getClass().getResource(Main.STYLESHEET_DIRECTORY + nw).toExternalForm());
  }

  /**
   * Overriden from SimulationMenu
   */
  @Override
  public void addFiles() {
    File[] fileList = new File(STYLES_DIRECTORY).listFiles();
    for (File f : fileList) {
      if (f.getName().contains(".css")) {
        this.getItems().add(f.getName());
      }
    }
    this.getSelectionModel().selectFirst();
  }
}
