
public class GridNode implements Comparable {
	private int x;
	private int y;
	private int costOfPath = 0;
	private GridNode caller;
	
	public GridNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public GridNode(int x, int y, GridNode caller) {
		this.x = x;
		this.y = y;
		this.caller = caller;
	}
	
	public GridNode getCaller() {
		return this.caller;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void addToCost(int cost) {
		this.costOfPath += cost;
	}
	
	public int getCost() {
		return this.costOfPath;
	}
	
	public boolean sameNode(GridNode someNode) {
		if(someNode.getX() == this.x && someNode.getY() == this.y)
			return true;
		return false;
	}
	
	public int[] getCoordinates() {
		return new int[] {x, y};
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
