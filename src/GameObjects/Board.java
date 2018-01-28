package GameObjects;

public class Board {
    //board information is saved in a 2D array of chars.
	private Side[][] playBoard;
	private int size;
	/***************************
	 * Function name: Board
	 * Arguments: size for board size.
	 * The Function operation : builder. allocates the reversi board according
	 * to user's choice of size. set's all the slots as EMPTY except for the central four.
	 ***************************/
	public Board(int size) {
		this.size = size;
	    int i,j;
	    playBoard = new Side [size][size];
	    //first we will set all array slots as EMPTY (empty slots will be
	    // represented as ' ' to maintain the board's shape.
	    for (i = 0; i < size; i++) {
	        for (j = 0; j < size; j++) {
	            playBoard[i][j] = Side.EMPTY;
	        }
	    }
	    playBoard[size/2 - 1][size/2 - 1] = Side.WHITE;
	    playBoard[size/2][size/2] = Side.WHITE;
	    playBoard[size/2 - 1][size/2] = Side.BLACK;
	    playBoard[size/2][size/2 - 1] = Side.BLACK;
	}
    /****************************
     * Function name: get(i, j)
     * this is a getter function.
     * @return the value in [i][j].
     ******************************/
	public Side get(int row, int col) {
		return this.playBoard[row][col];
	}
    /****************************
     * Function name: size
     * A getter functions
     * @return the size.
     ******************************/
	public int size() {
	    return this.size;
	}
	/******************************************
	* function name: set()
	* The Input: two numbers, for row and column, and new Side value.
	* The function operation: this is a setter function for playBoard[row][col].
	******************************************/
	public void set(int row, int col, Side s) {
		if ((row >= 0) && (row < this.size) &&
				(col >= 0) && (col < this.size))
			playBoard[row][col] = s;
	}
	/***********************
     * Function name: update
     * @param s the side
     * @param choice the Move of choice
     * Operation: this function changes the board according
     * the player's chosen move.
     ***********************/
	public void update(Side s, Move choice) {
		int i = choice.getR(), j = choice.getC();
		// change the chosen place
		playBoard[i][j] = s;
		// change the up direction
		for (int r = 1; r <= choice.getDir(Direction.up); r++) {
			playBoard[i - r][j] = s;
		}
		// change the down direction
		for (int r = 1; r <= choice.getDir(Direction.down); r++) {
			playBoard[i + r][j] = s;
		}
		// change the right direction
		for (int r = 1; r <= choice.getDir(Direction.right); r++) {
			playBoard[i][j + r] = s;
		}
		// change the left direction
		for (int r = 1; r <= choice.getDir(Direction.left); r++) {
			playBoard[i][j - r] = s;
		}
		// change the up-right direction
		for (int r = 1; r <= choice.getDir(Direction.upright); r++) {
			playBoard[i - r][j + r] = s;
		}
		// change the up-left direction
		for (int r = 1; r <= choice.getDir(Direction.upleft); r++) {
			playBoard[i - r][j - r] = s;
		}
		// change the down-right direction
		for (int r = 1; r <= choice.getDir(Direction.downright); r++) {
			playBoard[i + r][j + r] = s;
		}
		// change the down-left direction
		for (int r = 1; r <= choice.getDir(Direction.downleft); r++) {
			playBoard[i + r][j - r] = s;
		}
	}
}
