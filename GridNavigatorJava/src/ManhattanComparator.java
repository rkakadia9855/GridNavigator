import java.util.Comparator;

public class ManhattanComparator implements Comparator<GridNode> {
	
	GridNode goal;
	
	public ManhattanComparator(GridNode goal) {
		this.goal = goal;
	}

	@Override
	public int compare(GridNode o1, GridNode o2) {
		if(getTotalDistance(o1, goal) < getTotalDistance(o2, goal)) {
			return -1;
		}
		else if(getTotalDistance(o1, goal) > getTotalDistance(o2, goal)) {
			return 1;
		}
		return 0;
	}
	
	// manhattandistance + totalcostofpath
	private int getTotalDistance(GridNode o1, GridNode goal) {
		return (Math.abs(goal.getX() - o1.getX())) + (2 * (Math.abs(goal.getY() - o1.getY()))) + o1.getCost();
	}

}
