package view.components;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import model.Grid;
import view.SimulationView;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The SimulationButton.java class is the superclass to be extended when creating a new
 * simulation button.
 * <p>
 * Assumptions - This class should be extended when creating new button subclasses to be added to
 * the simulation, and all of its abstract methods should be overwritten (if any).
 * <p>
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its
 * constructor.
 */
public abstract class SimulationButton extends Button {

  /**
   * Default constructor, sets id and label
   *
   * @param prop - String that represents id and property key for label text
   * @param resources - .properties file for grid
   * @param model - instance of the grid
   * @param display - instance of the view
   */
  public SimulationButton(String prop, ResourceBundle resources, Grid[] model, SimulationView[] display){
    String label = resources.getString(prop);
    this.setText(label);
    this.setId(prop);
  }
}
