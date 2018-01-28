package application;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import GameObjects.GameSession;
import GameObjects.Side;

public class DrowGame extends HBox {
	 private GameSession game;
    /**********************
	 * constructor
	 * @param game a GameSession
     *********************/
	 public DrowGame(GameSession game) {
		 this.game = game;
	 }
    /**********************
     * Function name: event
     * @param bg a BoardGrid
     * @param tile the clicked tile
     * @return the fitting event to the clicked tile 
     *********************/
	 private EventHandler<MouseEvent> event(BoardGrid bg, Tile tile) {
		 return (event -> {
				// send the move, and check if it was played
		        if (!this.game.playMove(bg.getTileRow(tile), bg.getTileCol(tile)))
		        	return;
		        draw();
		        // check if the game still running
		        if (this.game.currentPlayer() != Side.EMPTY)
		        	// if yes - return
		        	return;
		        // if not - alert result and exit
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Final Countdown");
			    // get a result string
			    alert.setHeaderText(this.game.finalCountdown());
			    alert.setContentText("There are no more possible moves. The game ended.");
			    alert.showAndWait();
			    // exit
			    System.exit(0);
			});
	 }
    /**********************
     * Function name: drawBoard
	 * function Operation: the function walk over the game board and build a fitting grid to draw.
     *********************/
	 private void drawBoard(){
		 BoardGrid bg = new BoardGrid();
		 // find the fitting size values to the tiles
		 int size = this.game.getBoard().size(),
				 cellHeight = (int)this.getPrefHeight() / size,
				 cellWidth = ((int)this.getPrefWidth() - 200) / size;
		 Tile[][] t = new Tile[size][size];
		 for (int i = 0; i < size; i++) {
			 for (int j = 0; j < size; j++) {
				// create a tile according to the fitting tile in the board
				t[i][j] = new Tile(this.game.getBoard().get(i, j), cellWidth, cellHeight);
				// set event on mouse click the tile
				t[i][j].setOnMouseClicked(event(bg, t[i][j]));
				// add the tile to the grid
				bg.add(t[i][j], j, i);
		 	}
		}
		// add grid to the children
		this.getChildren().add(0, bg);
	 }
    /**********************
     * Function name: drawInfo
	 * function Operation: the function draws the game info.
     *********************/
	 private void drawInfo() {
		VBox di = new VBox(10);
		// write the current player to make turn
		di.getChildren().add(0, new Label("Current player: " + this.game.currentPlayer()));
		// write black player score
		di.getChildren().add(1, new Label("Black player score: " +
				this.game.getPlayer(Side.BLACK).getScore()));
		// write white player score
		di.getChildren().add(2, new Label("White player score: " +
				this.game.getPlayer(Side.WHITE).getScore()));
		this.getChildren().add(1, di);
	}
    /**********************
     * Function name: draw
	 * function Operation: the function draw the game.
     *********************/
	 public void draw() {
		 this.getChildren().clear();
		 drawBoard();
		 drawInfo();
	 }	 
}