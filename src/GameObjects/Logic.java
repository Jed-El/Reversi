package GameObjects;

import java.util.ArrayList;

public class Logic {
	/********************
	 * Function name : checkDir
	 * @param b the board.
	 * @param i,j a given row & column values for first tile.
	 * @param d a direction to step to from the tile.
	 * @param r the number of steps.
	 * @return true if the the tile we get to after the steps is in the board, and false other-ways.
     ********************/
	private boolean checkDir(Board board, int i, int j, Direction d, int r) {
		switch (d) {
			case up:
				return (i - r >= 0);
			case down:
				return (i + r < board.size());
			case left:
				return (j - r >= 0);
			case right:
				return (j + r < board.size());
			case downleft:
				return (i + r < board.size()) && (j - r >= 0);
			case downright:
				return (i + r < board.size()) && (j + r < board.size());
			case upleft:
				return (i - r >= 0) && (j - r >= 0);
			case upright:
				return (i - r >= 0) && (j + r < board.size());
			default:
				return false;
		}	
	}
	/********************
	 * Function name : stepDir
	 * @param b the board.
	 * @param i,j a given row & column values for first tile.
	 * @param d a direction to step to from the tile.
	 * @param r the number of steps.
	 * @return the Side value in the tile we get to after the steps.
     ********************/
	private Side stepDir(Board board, int i, int j, Direction d, int r) {
		switch (d) {
			case up:
				return board.get(i - r, j);
			case down:
				return board.get(i + r, j);
			case left:
				return board.get(i, j - r);
			case right:
				return board.get(i, j + r);
			case downleft:
				return board.get(i + r, j - r);
			case downright:
				return board.get(i + r, j + r);
			case upleft:
				return board.get(i - r, j - r);
			case upright:
				return board.get(i - r, j + r);
			default:
				return null;
		}
	}
	/********************
	 * Function name : checkAction
	 * @param b the board.
	 * @param s the relevant side who wants to make a move.
	 * @param i,j a given row & column values to the move.
	 * @return a Move object if (i,j) is legal, NULL otherwise.
	 * Operation: the function checks given move according the
	 * standard logic of reversi which mean that a player can put
	 * his piece only by skipping over his opponent piece in a line
	 * from his piece to an empty slot.
     ********************/
	private Move checkAction(Board board, Side s, int i, int j) {
		if (board.get(i,j) == Side.EMPTY) {
			int changes[] = {0, 0, 0, 0, 0, 0, 0, 0};
			int count[] = {0, 0, 0, 0, 0, 0, 0, 0};
			// the loop walk over all the directions
			for (Direction dir : Direction.values()) {
				// the loop step in the current direction
				for (int r = 1; checkDir(board, i, j, dir, r); r++) {
					// get the current tile
					Side tile = (stepDir(board, i, j, dir, r));
					// check the current tile
					if ((tile != s) && (tile != Side.EMPTY))
						count[dir.val()]++;
					else {
						if (tile == s)
							// add to the total score of the move
							changes[dir.val()] = count[dir.val()];
						break;
					}
				}
			}
			// check if at least one move was found
			if ((changes[0] + changes[1] + changes[2] + changes[3] +
				changes[4] + changes[5] + changes[6] + changes[7]) > 0) {
				return new Move(i, j, changes);
			}
		}
		return null;
	}
    /********************
	 * Function name : allowedActions
	 * @param b the board.
	 * @param s the relevant side who wants to make a move.
	 * @return an ArrayList of the optional moves the player has.
	 * Operation: the function checks every move using checkAction function,
	 * and collects all the possible moves into an ArrayList.
     ********************/
	public ArrayList<Move> allowedActions(Board board, Side s) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				Move m = checkAction(board, s, i, j);
				if (m != null)
					moves.add(m);
			}
		}
		return moves;
	}
}