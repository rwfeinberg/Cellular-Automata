package view;

import cellsociety.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The LanguageSplashScreen.java class represents the initial popup dialog that displays
 * when first starting the application. It is used to select a display language for the rest of the
 * simuation.
 * <p>
 * Assumptions - The language properties files should be located in a directory called /configs/
 * <p>
 * Dependencies - This class depends on Main.java for constants.
 */
public class LanguageSplashScreen {

  private File myDir;

  /**
   * Constructor, initializes directory of properties files
   */
  public LanguageSplashScreen(){
    myDir = new File(Main.SOURCE_DIRECTORY+Main.RESOURCES+"configs");
  }

  /**
   * This method actually creates the popup described, and stores the selected vaue to be used in SimulationView's createScene.
   * @return
   */
  public String makePopUp(){
    File[] fileList = myDir.listFiles();
    List<String> languageList = new ArrayList<>();
    for (File f : fileList){
      languageList.add(f.getName().replaceFirst(".properties", ""));
    }

    ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("English");
    choiceDialog.setHeaderText("Choose Language");
    choiceDialog.setTitle("Language");
    ObservableList<String> list = choiceDialog.getItems();
    list.addAll(languageList);
    choiceDialog.showAndWait();
    String choice = choiceDialog.getSelectedItem();
    return choice;
  }
}
