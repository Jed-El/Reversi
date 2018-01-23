package application;

import java.net.URL;
import java.util.ResourceBundle;

import GameObjects.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {
	 @FXML
	 private HBox boardRoot;
	 private Board board;
	 
	 public GameController() {
		this.board = new Board(8, 8);
	 }
	 
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 DrowBoard mazeBoard = new DrowBoard(this.board);
		 mazeBoard.setPrefWidth(400);
		 mazeBoard.setPrefHeight(400);
		 boardRoot.getChildren().add(0, mazeBoard);
		 mazeBoard.draw();
		 boardRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
			 double boardNewWidth = newValue.doubleValue() - 200;
			 mazeBoard.setPrefWidth(boardNewWidth);
			 mazeBoard.draw();
			 });
		 boardRoot.heightProperty().addListener((observable, oldValue, newValue) -> {
			 mazeBoard.setPrefHeight(newValue.doubleValue());
			 mazeBoard.draw();
		 });
	 }
}
