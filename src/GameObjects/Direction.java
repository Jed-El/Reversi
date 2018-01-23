package GameObjects;

public enum Direction {
	up(0), down(1),
	right(2), left(3),
	downright(4), downleft(5),
	upright(6), upleft(7);
	
	private int value;  
	private Direction(int value) {  
		this.value=value;  
	}
	
	public int val() {  
		return this.value;  
	}
}
