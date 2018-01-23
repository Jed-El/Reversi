package GameObjects;

public class Move {
	private int r;
	private int c;
	private int[] dir;
	/******************************************
	* Constructor
	* The Input: x row value, y column value,
	* d a vector with numbers of enemy's blocks that will change by the move, for every direction.
	******************************************/
	public Move(int r, int c, int[] dir) {
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
	/******************************************
	* function name: getR()
	* The Output: the move row value.
	* The function operation: this is a getter function for r.
	******************************************/
	public int getR() {return r;}
	/******************************************
	* function name: getC()
	* The Output: the move column value.
	* The function operation: this is a getter function for c.
	******************************************/
	public int getC() {return c;}
	/******************************************
	* function name: getDir()
	* The Input: d - a Direction.
	* The Output: a numbers of enemy's blocks that will change by the move, in dirction i
	* The function operation: this is a getter function for dir[i].
	******************************************/
	public int getDir(Direction d) {
		if ((d.val() < 8) && (d.val() >= 0))
			return dir[d.val()];
		return -1;
	}
   /************************
	* Function name : strMove()
	* The Output: the Move string, written in a conventional way (row, col).
     **********************/
	public String strMove() {
		return "(" + (r + 1) + ", " + (c + 1) + ")";
	}
    /************************
	* Function name : isEqual
	* The Input: r2 a value to compare to r, c2 a value to compare to c.
	* The Output: true if the given row & column values equal to this move values, false otherwise.
	* The function operation: compared r2 to r, and c2 to c.
     **********************/
	public boolean isEqual(int r2, int c2) {
	    if ((c2 == c) && (r2 == r)) {
	        return true;
	    }
	    return false;
	}
    /************************
	* Function name : isEqual
	* The Input: a pointer to another Move.
	* The Output: true if the given row & column values of the moves are equal, false otherwise.
	* The function operation: compared this->r to m2->r, and this->c to m2->c.
     **********************/
	public boolean isEqual(Move m2) {
	    if ((m2.getC() == c) && (m2.getR() == r)) {
	        return true;
	    }
	    return false;
	}

}