
public class GridNode implements Comparable {
	private int x;
	private int y;
	
	public GridNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
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
