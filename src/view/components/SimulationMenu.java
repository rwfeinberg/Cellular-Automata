package view.components;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import model.Grid;
import view.SimulationView;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The SimulationMenu.java class is the superclass to be extended when creating a new
 * simulation dropdown menu.
 * <p>
 * Assumptions - This class should be extended when creating new dropdown menu subclasses to be
 * added to the simulation, and all of its abstract methods should be overwritten (if any).
 * <p>
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its
 * constructor.
 */
public abstract class SimulationMenu extends ComboBox {

  private File myDir;

  /**
   * Default constructor, sets id and label
   *
   * @param prop      - String that represents id and property key for label text
   * @param resources - .properties file for grid
   * @param model     - instance of the grid
   * @param display   - instance of the view
   * @param root      - instance of the view root
   * @param language  - configuration language obtained from splash screen
   * @param gridlist  - Instance of File Dropdown Menu
   */
  public SimulationMenu(String prop, ResourceBundle resources, BorderPane root, String language,
      Grid[] model, SimulationView[] display, SimulationMenu gridlist) {
    this.setId(prop);
    this.setPromptText(resources.getString(prop));
  }

  /**
   * This method is overriden in the subclasses, and is used to create the file list of each
   * dropdown menu from a directory
   */
  public abstract void addFiles();

  /**
   * This method is used to facilitate the interaction between GameMenu and FileMenu
   *
   * @return - instance of directory instance
   */
  public File getDir() {
    return myDir;
  }

  /**
   * This method is used to facilitate the interaction between GameMenu and FileMenu
   *
   * @param - directory
   */
  public void setDir(File file) {
    myDir = file;
  }
}
