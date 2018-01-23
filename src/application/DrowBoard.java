package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

import GameObjects.Board;
import GameObjects.Logic;
import GameObjects.Move;
import GameObjects.Side;


public class DrowBoard extends GridPane {
	 private Board board;
	 private Logic logic;

	 public DrowBoard(Board board) {
		 this.board = board;
		 this.logic = new Logic();
		 FXMLLoader fxmlLoader = new
			FXMLLoader(getClass().getResource("DrowBoard.fxml"));
		 fxmlLoader.setRoot(this);
		 fxmlLoader.setController(this);
		 try {
			 fxmlLoader.load();
		 } catch (IOException exception) {
			 throw new RuntimeException(exception);
		 }
	 }
	 
	 public void draw() {
		 this.getChildren().clear();
		 ArrayList<Move> bmoves = logic.allowedActions(board, Side.BLACK);
		 ArrayList<Move> wmoves = logic.allowedActions(board, Side.WHITE);

		 int height = (int)this.getPrefHeight();
		 int width = (int)this.getPrefWidth();
		 int cellHeight = height / board.getRow();
		 int cellWidth = width / board.getCol();
		 Tile[][] t = new Tile[board.getRow()][board.getCol()];
		 for (int i = 0; i < board.getRow(); i++) {
			 for (int j = 0; j < board.getCol(); j++) {
				t[i][j] = new Tile(board.get(i, j), cellWidth, cellHeight);
				Tile ti = t[i][j];
				t[i][j].setOnMouseClicked(event -> {
		            if (event.getButton() == MouseButton.PRIMARY) {
			            for (Move m: wmoves) {
			            	if (m.isEqual(getRowIndex(ti), getColumnIndex(ti))) {
			            		board.update(Side.WHITE, m);
			            		break;
			            	}
			            }
		            }
		            else if (event.getButton() == MouseButton.SECONDARY) {
			            for (Move m: bmoves) {
			            	if (m.isEqual(getRowIndex(ti), getColumnIndex(ti))) {
			            		board.update(Side.BLACK, m);
			            		break;
			            	}
			            }
		            }
		            
		            draw();
		        });
				this.add(ti, j, i);
		 	}	 
		 }
	}
}