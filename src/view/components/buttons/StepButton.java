package view.components.buttons;

import cellsociety.Main;
import java.util.List;
import java.util.ResourceBundle;
import model.Grid;
import view.CellView;
import view.SimulationView;
import view.components.SimulationButton;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The Step Button.java class is the class that represents the Step button explained in
 * the README
 * <p>
 * Assumptions - This class extends SimulationButton.java
 * <p>
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its
 * constructor and methods.
 */
public class StepButton extends SimulationButton {

  /**
   * Constructor, adds function
   *
   * @param prop      - String that represents id and property key for label text
   * @param resources - .properties file for grid
   * @param model     - instance of the grid
   * @param display   - instance of the view
   */
  public StepButton(String prop, ResourceBundle resources, Grid[] model,
      SimulationView[] display) {
    super(prop, resources, model, display);
    this.setOnAction(event -> stepSim(model[0], display[0]));
  }

  private void stepSim(Grid m, SimulationView d) {
    if (!Main.getIsRunning()) {
      m.updateGrid();
      for (List<CellView> row : d.getPhysicalCells()) {
        for (CellView c : row) {
          c.updateColor();
        }
      }
      d.updateGraph();
    }
  }
}
