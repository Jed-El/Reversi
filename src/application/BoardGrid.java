package application;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class BoardGrid extends GridPane {
    /**********************
     * Function name: getTileRow
     * @param t a Node child of this BoardGrid
     * @return the Node row index 
     *********************/
	public int getTileRow(Node t) {
		// using a static function to get the value and return it
		return getRowIndex(t);
	}
    /**********************
     * Function name: getTileCol
     * @param t a Node child of this BoardGrid
     * @return the Node column index 
     *********************/
	public int getTileCol(Node t) {
		// using a static function to get the value and return it
		return getColumnIndex(t);
	}
}
