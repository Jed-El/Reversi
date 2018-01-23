package GameObjects;

public class Board {
    //board information is saved in a 2D array of chars.
	private Side[][] playBoard;
	private int row;
	private int col;
	/***************************
	 * Function name: Board
	 * Arguments: row and col counts
	 * The Function operation : builder. allocates the reversi board according
	 * to user's choice or rows and columns count. set's all the slots as EMPTY
	 * except for the central four.
	 ***************************/
	public Board(int row, int col) {
		this.row = row;
		this.col = col;
	    int i,j;
	    playBoard = new Side [row][col];
	    //first we will set all array slots as EMPTY (empty slots will be
	    // represented as ' ' to maintain the board's shape.
	    for (i = 0; i < row; i++) {
	        for (j = 0; j < col; j++) {
	            playBoard[i][j] = Side.EMPTY;
	        }
	    }
	    playBoard[row/2 - 1][col/2 - 1] = Side.WHITE;
	    playBoard[row/2][col/2] = Side.WHITE;
	    playBoard[row/2 - 1][col/2] = Side.BLACK;
	    playBoard[row/2][col/2 - 1] = Side.BLACK;
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
     * Function name: getRow
     * A getter functions
     * @return the rows number.
     ******************************/
	public int getRow() {
	    return row;
	}
    /****************************
     * Function name: getCol
     * A getter functions
     * @return the columns number.
     ******************************/
	public int getCol() {
	    return col;
	}
	/******************************************
	* function name: set()
	* The Input: two numbers, for row and column, and new Side value.
	* The function operation: this is a setter function for playBoard[row][col].
	******************************************/
	public void set(int row, int col, Side s) {
		if ((row >= 0) && (row < this.row) &&
				(col >= 0) && (col < this.col))
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
