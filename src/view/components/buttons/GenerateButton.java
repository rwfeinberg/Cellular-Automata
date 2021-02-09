package view.components.buttons;

import java.util.List;
import java.util.ResourceBundle;
import model.Cell;
import model.Grid;
import view.CellView;
import view.SimulationView;
import view.components.SimulationButton;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The GenerateButton.java class is the class that represents the Randomizer button
 * explained in the README
 * <p>
 * Assumptions - This class extends SimulationButton.java
 * <p>
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its
 * constructor and methods.
 */
public class GenerateButton extends SimulationButton {

  /**
   * constructor, adds function
   *
   * @param prop      - String that represents id and property key for label text
   * @param resources - .properties file for grid
   * @param model     - instance of the grid
   * @param display   - instance of the view
   */
  public GenerateButton(String prop, ResourceBundle resources, Grid[] model,
      SimulationView[] display) {
    super(prop, resources, model, display);
    this.setOnAction(event -> randomizeCells(model, display));
  }

  private void randomizeCells(Grid[] model, SimulationView[] display) {
    List<List<Cell>> Cells = model[0].getGrid();
    for (List<Cell> row : Cells) {
      for (Cell cell : row) {
        cell.setMyTempState(0);
        int random = (int) Math.floor(Math.random() * 4);
        for (int i = 0; i < random; i++) {
          cell.rotateState();
        }
      }
    }
    for (List<Cell> row : Cells) {
      for (Cell cell : row) {
        cell.setTempToOld();
      }
    }
    for (List<CellView> row : display[0].getPhysicalCells()) {
      for (CellView c : row) {
        c.updateColor();
      }
    }
  }
}
