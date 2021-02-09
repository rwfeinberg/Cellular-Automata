package view.components.buttons;

import cellsociety.Main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextInputDialog;
import model.Cell;
import model.Grid;
import view.SimulationView;
import view.components.SimulationButton;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The Save Button.java class is the class that represents the Save button explained in
 * the README
 * <p>
 * Assumptions - This class extends SimulationButton.java
 * <p>
 * Dependencies - This class depends on Grid.java and SimulationView.java as parameters in its
 * constructor and methods, as well as Main.java for constants.
 */
public class SaveButton extends SimulationButton {

  private final String OUTPUT_DIRECTORY = Main.DATA_DIRECTORY+"SavedConfigurations";

  /**
   * Constructor, adds function
   *
   * @param prop - String that represents id and property key for label text
   * @param resources - .properties file for grid
   * @param model - instance of the grid
   * @param display - instance of the view
   */
  public SaveButton(String prop, ResourceBundle resources, Grid[] model,
      SimulationView[] display) {
    super(prop, resources, model, display);
    this.setOnAction(event -> saveSim(model[0]));
  }

  private void saveSim(Grid grid) {
    TextInputDialog title = new TextInputDialog("Enter title for file");
    title.showAndWait();
    TextInputDialog a = new TextInputDialog("Enter author");
    a.showAndWait();
    TextInputDialog d = new TextInputDialog("Enter description");
    d.showAndWait();

    String fileName = title.getEditor().getText();
    String author = a.getEditor().getText();
    String description = d.getEditor().getText();

    makeCSVFromList(grid.getGrid(), fileName);
    makePropertiesFile(grid, fileName, author, description);
  }

  private void makePropertiesFile(Grid grid, String fileName, String author, String description) {
    try( FileWriter writer = new FileWriter(new File(
        Main.SOURCE_DIRECTORY+Main.RESOURCES, fileName +".properties"))) {
      StringBuilder sb = new StringBuilder();

      String gametype = grid.getProperties().getProperty(Main.GAME_TYPE_KEY);
      sb.append(Main.GAME_TYPE_KEY+"="+gametype+"\n");

      sb.append(Main.TITLE_KEY+"="+fileName+"\n");

      sb.append(Main.AUTHOR_KEY+"="+author+"\n");

      sb.append(Main.DESCRIPTION_KEY+"="+description+"\n");

      String neighbor = grid.getProperties().getProperty(Main.NEIGHBOR_KEY);
      sb.append(Main.NEIGHBOR_KEY+"="+neighbor+"\n");

      String filePath = OUTPUT_DIRECTORY+"/"+fileName+Main.CONFIG_FILE_EXTENSION;
      System.out.println(filePath);
      sb.append(Main.FILE_NAME_KEY+"="+filePath);

      writer.write(sb.toString());
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void makeCSVFromList(List<List<Cell>> cells, String fileName) {
    try( FileWriter writer = new FileWriter(new File(OUTPUT_DIRECTORY, fileName+Main.CONFIG_FILE_EXTENSION))) {
      StringBuilder sb = new StringBuilder();

      int rowSize = 0;
      int colSize = cells.get(0).size();

      for (List<Cell> ignored : cells){ rowSize++; }

      sb.append(rowSize+","+colSize+"\n");

      for (List<Cell> row : cells){
        for (int i=0; i<row.size();i++){
          sb.append(row.get(i).getMyState());
          if (i < row.size()-1) {
            sb.append(",");
          }
        }
        sb.append("\n");
      }

      writer.write(sb.toString());
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
