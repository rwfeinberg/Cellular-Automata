package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;

/**
 * @author Muazzam Khan-Noorpuri
 * <p>
 * Purpose - The CellView.java class represents the javaFX rectangle node associated with each
 * physical Cell.java present in the Grid. This class is the one displayed in the view and
 * represents the visual cells.
 * <p>
 * Assumptions - This class extends Rectangle.
 * <p>
 * Dependencies - This class depends on Cell.java in order to retrieve/store its respective cell in
 * the grid.
 */
public class CellView extends Rectangle {

  private Cell linkedCell;

  /**
   * Constructor, sets color, size, and action event to change color when clicked.
   *
   * @param width
   * @param height
   * @param cell
   */
  public CellView(double width, double height, Cell cell) {
    super(width, height);
    linkedCell = cell;
    this.setFill(Color.valueOf(cell.getColor()));
    this.setOnMouseClicked(mouseEvent -> {
      linkedCell.rotateState();
      linkedCell.setTempToOld();
      this.setFill(Color.valueOf(cell.getColor()));
    });
  }

  /**
   * This method is used to update the color of the rectangle whenever the animation is stepped or
   * the grid is randomized.
   */
  public void updateColor() {
    String color = linkedCell.getColor();
    this.setFill(Color.valueOf(color));
  }
}
