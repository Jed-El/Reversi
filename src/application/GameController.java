package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import GameObjects.GameSession;
import GameObjects.GameSettings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameController implements Initializable {
	 @FXML
	 private VBox root;
	 
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		// Set and add a menu bar
		setMenuBar();
		try {
			// Set and add a DrawGame
			setDrawGame();
		} catch (IOException e) {
	         e.printStackTrace();
	    }
	 }
	 
	 private void setMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu settings = new Menu("Settings");
		// Add an open setting window event
		settings.setOnAction(actionEvent -> {
	        try {
	        	Parent sRoot = FXMLLoader.load(getClass().getClassLoader().getResource("settings.fxml"));
	            Scene newScene = new Scene(sRoot, 450, 450);
	            newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
	            stage.setTitle("Settings");
	            stage.setScene(newScene);
	            stage.show();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		});
		menuBar.getMenus().add(settings);
		root.getChildren().add(0, menuBar);
	}
	 
	 private void setDrawGame() throws IOException {
		// Loading settings from file
		File path = new File("gameConfig");
        //case file doesn't exist we need to create it with default values.
        if (!path.exists()) {
            GameSettings creation = new GameSettings();
            creation.save(path);
        }
        //loading our configurations from file.
        GameSettings config = GameSettings.loadFromFile(path);
        // create DrowGame
		 DrawGame dg = new DrawGame(new GameSession(
				 config.getSize(), config.getFirst(), config.getColor1(), config.getColor2()));
		 dg.setSpacing(10);
		 // set preferred size values
		 dg.setPrefWidth(550);
		 dg.setPrefHeight(400);
		 dg.draw();
		 // set new size values after size window changed
		 root.widthProperty().addListener((observable, oldValue, newValue) -> {
			 dg.setPrefWidth(newValue.doubleValue());
			 dg.draw();
			 });
		 root.heightProperty().addListener((observable, oldValue, newValue) -> {
			 dg.setPrefHeight(newValue.doubleValue() - 30);
			 dg.draw();
		 });
		 root.getChildren().add(1, dg);
	 }
}
