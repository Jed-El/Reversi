package application;

import java.io.File;
import java.io.IOException;

import GameObjects.GameSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;


public class settingsController {
	//our starter options
	ObservableList<String> whoWillStart = FXCollections.observableArrayList("Black","White");
	//our board square size
	ObservableList<Integer> boardSizes = FXCollections.observableArrayList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
	@FXML
	private ChoiceBox<String> firstPlayer;
	@FXML
	private ColorPicker p1C;
	@FXML
	private ColorPicker p2C;
	@FXML
	private ChoiceBox<Integer> boardSize;
	@FXML
	/**
	 * init function.
	 */
	private void initialize() {
		File path = new File("gameConfig");
		try {
            //case file doesnt exist we need to create it with default values.
            if (!path.exists()) {
                GameSettings creation = new GameSettings();
                creation.save(path);
            }
            //loading our configurations from file.
            GameSettings config = GameSettings.loadFromFile(path);
            //loading our saved values into our current Gamesettings.
            firstPlayer.setValue(config.getFirst());
            firstPlayer.setItems(whoWillStart);
            p1C.setValue(config.getColor1());
            p2C.setValue(config.getColor2());
            boardSize.setValue(config.getSize());
            boardSize.setItems(boardSizes);
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * cancel changes and returns to game
	 * @param event - the button press event (cancel button).
	 */
	public void Cancel(ActionEvent event) {
		final Node source = (Node) event.getSource();
	    final Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	/**
	 * 
	 * This function saves the user settings config to the settings file
	 * and calls the cancel function to close the window, after the save button
	 */
	public void SaveChanges(ActionEvent event) {
		File path = new File("gameConfig");
		GameSettings newConfig = new GameSettings();
		newConfig.setFirst(firstPlayer.getValue());
		newConfig.setColor1(p1C.getValue());
		newConfig.setColor2(p2C.getValue());
		newConfig.setSize(boardSize.getValue());
		try {
		newConfig.save(path);
		} catch (Exception e) {
            e.printStackTrace();
		}
		Cancel(event);
	}
}
