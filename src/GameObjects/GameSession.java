package GameObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class GameSession {
	private Board board;
	private Logic logic;
	private Player black;
	private Player white;
	// the side of the current turn
	private Side current;
	// the current moves allowed for this turn
	private ArrayList<Move> allowedActions;
    /**********************
	 * constructor
	 * @param size a number for board size.
	 * @param logic the Logic.
	 * @param first the first side to play.
	 * @param black a Color for black Player.
	 * @param white Color for white Player.
     *********************/
	public GameSession(int size, Side first, Color black, Color white) {
		this.board = new Board(size);
		this.logic = new Logic();
		this.current = first;
		this.black = new Player(Side.BLACK, black);
		this.white = new Player(Side.WHITE, white);
		this.allowedActions = logic.allowedActions(board, first);
	}
    /**********************
	 * Getter function for board.
	 * @return board.
     *********************/
	public Board getBoard() {
		return this.board;
	}
    /**********************
	 * A getter function for Player by Side.
	 * @param s a Side for the Player.
	 * @return black / white Player.
	 * function Operation: this function returns a Player according to the given Side.
     *********************/
	public Player getPlayer(Side s) {
		if (s == Side.BLACK)
			return this.black;
		if (s == Side.WHITE)
			return this.white;
		return null;
	}
    /**********************
	 * Getter function for current Player.
	 * @return current.
     *********************/
	public Side currentPlayer() {
		return this.current;
	}
    /*********************
     * Function name: checkMove
     * @param row the clicked tile row
     * @param col the clicked tile column
     * @return the fitting Move
     * Operation: the function search if the fitting Move is in allowedActions array,
     * if found return, else return null.
     *********************/
	private Move checkMove(int row, int col) {
		// check if the array is not null
		if (this.allowedActions == null)
			return null;
		// check if move is in the array
		for (Move move: this.allowedActions)
			if (move.isEqual(row, col))
				return move;
		// if not - return null
		return null;
	}
    /*********************
     * Function name: swapPlayer()
     * Operation: this function switches "current" to next Side to make turn,
     * and save the allowed moves to the next turn in "allowedActions".
     *********************/
	private void swapPlayer() {
		// try to switch twice (if there are no moves allowed)
		for (int i = 1; i <= 2; i++) {
			if (this.current == Side.BLACK)
				this.current = Side.WHITE;
			else if (this.current == Side.WHITE)
				this.current = Side.BLACK;
			// get the allowed moves to the next turn
			this.allowedActions = this.logic.allowedActions(this.board, this.current);
			// check if there are allowed moves
			if (this.allowedActions.size() > 0)
				// if yes - return
				return;
		}
		// after two switch - game end
		this.current = Side.EMPTY;
	}
    /*******************
     * Function name: playMove
     * function Operation: this function is the function that actually
     * coordinates a turn of a game. The function check if the move the player picked
     * is allowed, if it is not - return false, if it is - update the game's values and return true.
     *******************/
	public boolean playMove(int row, int col) {
		if (this.current == Side.EMPTY)
			return false;
		Move move = checkMove(row, col);
		// check if the clicked tile is an allowed move
		if (move == null)
			return false;
		// update the board
		this.board.update(this.current, move);
		// update players score value
		countdown();
		// switch to next player to make turn
		swapPlayer();
		return true;
	}
    /*********************
     * Function name: countdown()
     * Operation: this function is called to update the Players score value.
     *********************/
	public void countdown() {
		// set counters
		int bscore = 0, wscore = 0;
		// walk over the board
	    for (int i = 0; i < this.board.size(); i++)
		    for (int j = 0; j < this.board.size(); j++) {
		    	// Increase the counter of the side the tile belong to
		    	if (this.board.get(i, j) == Side.BLACK)
		    		bscore++;
		    	if (this.board.get(i, j) == Side.WHITE)
		    		wscore++;
		    }
	    // update the Players score value
	    this.black.setScore(bscore);
	    this.white.setScore(wscore);
	}
    /*********************
     * Function name: finalCountdown
     * @return string that announces who is the winner.
     * Operation: this function is called when no player has legal moves,
     * which means the game ended, check who is the winner and return a fitting string.
     *********************/
	public String finalCountdown() {
		// check which player has a better score
	    if (this.black.getScore() > this.white.getScore())
	        return "Black Player won!";
	    else if (this.black.getScore() < this.white.getScore())
	        return "white Player won!";
	    else
	        return "A draw!";
	}
}
