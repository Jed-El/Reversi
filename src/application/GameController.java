package application;

import java.net.URL;
import java.util.ResourceBundle;

import GameObjects.GameSession;
import GameObjects.Side;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GameController implements Initializable {
	 @FXML
	 private HBox root;
	 
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 // create DrowGame
		 DrowGame dg = new DrowGame(new GameSession(8, Side.BLACK, Color.BLACK, Color.WHITE));
		 dg.setSpacing(10);
		 // set preferred size values
		 dg.setPrefWidth(550);
		 dg.setPrefHeight(400);
		 root.getChildren().add(0, dg);
		 dg.draw();
		 // set new size values after size window changed
		 root.widthProperty().addListener((observable, oldValue, newValue) -> {
			 dg.setPrefWidth(newValue.doubleValue());
			 dg.draw();
			 });
		 root.heightProperty().addListener((observable, oldValue, newValue) -> {
			 dg.setPrefHeight(newValue.doubleValue());
			 dg.draw();
		 });
	 }
}
